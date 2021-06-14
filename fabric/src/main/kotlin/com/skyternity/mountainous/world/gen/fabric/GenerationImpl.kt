@file:Suppress("DEPRECATION")

package com.cyber2000.mountainous.world.gen.fabric

import com.cyber2000.mountainous.Mountainous
import com.cyber2000.mountainous.fabric.ModBlocks
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
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
import java.util.function.Predicate
import net.fabricmc.fabric.api.biome.v1.BiomeModifications.addFeature as addFeature


object GenerationImpl {
    private val DIMSTONE_GEN = Feature.ORE
        .configure(OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.DIMSTONE_BLOCK.defaultState, 33))
        .rangeOf(80).spreadHorizontally().repeat(10)

    private val DIRT: RuleTest = object : BlockMatchRuleTest(Blocks.DIRT) {
    }

    @Suppress("SameParameterValue")
    private fun getOreFeature(count: Int, bottomOffset: Int, topOffset: Int, max: Int, filler: RuleTest, defaultBlockstate: BlockState, size: Int): ConfiguredFeature<*, *> {
        val configuredFeature: ConfiguredFeature<OreFeatureConfig, *> = Feature.ORE.configure(OreFeatureConfig(filler, defaultBlockstate, size))
        return configuredFeature.decorate(Decorator.COUNT.configure(CountConfig(count))) // Count
            .decorate(Decorator.RANGE.configure(RangeDecoratorConfig(bottomOffset, topOffset, max)));
    }

    private fun exportFeature(id: Identifier, predicate: Predicate<BiomeSelectionContext>, generationStep: GenerationStep.Feature, configuredFeature: ConfiguredFeature<*, *>) {
        val registryKey: RegistryKey<ConfiguredFeature<*, *>> = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, id)

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, registryKey.value, configuredFeature)
        addFeature(predicate, generationStep, registryKey)
    }

    @JvmStatic
    fun loadFeatures() {
        val dimstoneGen = getOreFeature(14, 10, 5, 90, OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.DIMSTONE_BLOCK.defaultState, 29)
        val gingerRootsGen = getOreFeature(55, 55, 5, 130, DIRT, ModBlocks.GINGER_ROOTS_BLOCK.defaultState, 3)

        exportFeature(
            Mountainous.id("ore_dimstone"),
            { context: BiomeSelectionContext -> context.biome.category == Biome.Category.EXTREME_HILLS},
            GenerationStep.Feature.UNDERGROUND_ORES, dimstoneGen
        )
        exportFeature(
            Mountainous.id("ore_ginger_roots"),
            { context: BiomeSelectionContext -> context.biome.category == Biome.Category.EXTREME_HILLS},
            GenerationStep.Feature.UNDERGROUND_ORES, gingerRootsGen
        )
    }
}