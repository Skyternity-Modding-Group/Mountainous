@file:Suppress("DEPRECATION")

package com.cyber2000.mountainous.world.gen.forge

import com.cyber2000.mountainous.Mountainous
import com.cyber2000.mountainous.forge.ModBlocks
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.structure.rule.BlockMatchRuleTest
import net.minecraft.structure.rule.RuleTest
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.CountConfig
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraftforge.event.world.BiomeLoadingEvent
import java.util.*
import java.util.function.Predicate

@Suppress("SameParameterValue", "unused")
object GenerationImpl {
    private lateinit var LOADER: () -> Unit
    internal var initialized: Boolean = false
    internal var LOAD_CACHE: MutableList<Pair<Predicate<BiomeLoadingEvent>, (BiomeLoadingEvent) -> Unit>> = ArrayList()

    private val DIRT: RuleTest = BlockMatchRuleTest(Blocks.DIRT)

    @Suppress("SameParameterValue")
    private fun getOreFeature(count: Int, bottomOffset: Int, topOffset: Int, max: Int, filler: RuleTest, defaultBlockstate: BlockState, size: Int): ConfiguredFeature<*, *> {
        val configuredFeature: ConfiguredFeature<OreFeatureConfig, *> = Feature.ORE.configure(OreFeatureConfig(filler, defaultBlockstate, size))
        return configuredFeature.decorate(Decorator.COUNT.configure(CountConfig(count))) // Count
            .decorate(Decorator.RANGE.configure(RangeDecoratorConfig(bottomOffset, topOffset, max)))
    }

    private fun exportFeature(id: Identifier, predicate: Predicate<BiomeLoadingEvent>, generationStep: GenerationStep.Feature, configuredFeature: ConfiguredFeature<*, *>) {
        val oreWoolOverworld: RegistryKey<ConfiguredFeature<*, *>> = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, id)

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreWoolOverworld.value, configuredFeature)

        LOAD_CACHE.add(Pair(predicate) { event: BiomeLoadingEvent -> addFeature0(event, generationStep, configuredFeature) })
    }

    private fun addFeature0(event: BiomeLoadingEvent, generationStep: GenerationStep.Feature, configuredFeature: ConfiguredFeature<*, *>) {
        event.generation.feature(generationStep, configuredFeature)
    }

    internal fun loadFeatureCache() {
        Mountainous.LOGGER.debug("Loader: $LOADER")
        LOADER.invoke()
    }

    @JvmStatic
    fun loadFeatures() {
        LOADER = {
            Mountainous.LOGGER.debug("Getting features.")
            val dimstoneGen = getOreFeature(14, 10, 5, 90, OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.DIMSTONE_BLOCK.get().defaultState, 29)
            val gingerRootsGen = getOreFeature(55, 55, 5, 130, DIRT, ModBlocks.GINGER_ROOTS_BLOCK.get().defaultState, 3)

            Mountainous.LOGGER.debug("Exporting features.")
            exportFeature(
                Mountainous.id("ore_dimstone"),
                { context: BiomeLoadingEvent -> context.category == Biome.Category.EXTREME_HILLS},
                GenerationStep.Feature.UNDERGROUND_ORES, dimstoneGen
            )
            exportFeature(
                Mountainous.id("ore_ginger_roots"),
                { context: BiomeLoadingEvent -> context.category == Biome.Category.EXTREME_HILLS},
                GenerationStep.Feature.UNDERGROUND_ORES, gingerRootsGen
            )
        }
    }
}

