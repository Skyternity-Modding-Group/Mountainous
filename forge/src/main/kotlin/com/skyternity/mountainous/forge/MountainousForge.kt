package com.skyternity.mountainous.forge

import com.skyternity.mountainous.Mountainous
import com.skyternity.mountainous.client.MountainousClient
import com.skyternity.mountainous.world.gen.forge.Generator
import me.shedaniel.architectury.platform.forge.EventBuses
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Mountainous.MOD_ID)
object MountainousForge {
    init {
        EventBuses.registerModEventBus(Mountainous.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(::onClientSetup)

        MinecraftForge.EVENT_BUS.addListener { event: BiomeLoadingEvent -> Generator.onBiomeLoad(event) }

        Mountainous.init();
        Registration.init(MOD_BUS);
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        MountainousClient.init()
    }
}