package com.cyber2000.mountainous.world.gen.forge

import com.cyber2000.mountainous.Mountainous
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = Mountainous.MOD_ID)
object Generator {
    @SubscribeEvent
    @JvmStatic
    fun onBiomeLoad(event: BiomeLoadingEvent) {
        if (!GenerationImpl.initialized) {
            GenerationImpl.initialized = true
            GenerationImpl.loadFeatureCache()
        }

        Mountainous.LOGGER.debug("Load Cache: ${GenerationImpl.LOAD_CACHE}")

        for (s in GenerationImpl.LOAD_CACHE) {
            if (s.first.test(event)) {
                s.second.invoke(event)
            }
        }
    }
}