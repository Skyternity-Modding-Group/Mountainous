package com.cyber2000.mountainous.forge

import com.cyber2000.mountainous.ArchitecturySkeleton
import com.cyber2000.mountainous.client.ArchitecturySkeletonClient
import com.cyber2000.mountainous.world.gen.forge.Generator
import me.shedaniel.architectury.platform.forge.EventBuses
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.eventbus.EventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import java.util.function.Consumer

@Mod(ArchitecturySkeleton.MOD_ID)
object ArchitecturySkeletonForge {
    init {
        EventBuses.registerModEventBus(ArchitecturySkeleton.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(::onClientSetup)

        MinecraftForge.EVENT_BUS.addListener { event: BiomeLoadingEvent -> Generator.onBiomeLoad(event) }

        ArchitecturySkeleton.init();
        Registration.init(MOD_BUS);
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        ArchitecturySkeletonClient.init()
    }
}