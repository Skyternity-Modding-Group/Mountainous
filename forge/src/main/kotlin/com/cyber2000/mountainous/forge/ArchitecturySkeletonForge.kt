package com.cyber2000.mountainous.forge

import com.cyber2000.mountainous.ArchitecturySkeleton
import com.cyber2000.mountainous.client.ArchitecturySkeletonClient
import me.shedaniel.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(ArchitecturySkeleton.MOD_ID)
object ArchitecturySkeletonForge {
    init {
        EventBuses.registerModEventBus(ArchitecturySkeleton.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(::onClientSetup)

        ArchitecturySkeleton.init();
        Registration.init(MOD_BUS);
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        ArchitecturySkeletonClient.init()
    }
}