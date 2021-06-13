package com.cyber2000.mountainous.world.gen.forge

import com.cyber2000.mountainous.ArchitecturySkeleton
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = ArchitecturySkeleton.MOD_ID)
object Generator {
    @SubscribeEvent
    @JvmStatic
    fun onBiomeLoad(event: BiomeLoadingEvent) {
        if (!GenerationImpl.initialized) {
            GenerationImpl.initialized = true
            GenerationImpl.loadFeatureCache()
        }

        ArchitecturySkeleton.LOGGER.debug("Load Cache: ${GenerationImpl.LOAD_CACHE}")

        for (s in GenerationImpl.LOAD_CACHE) {
            if (s.first.test(event)) {
                s.second.invoke(event)
            }
        }
    }
}