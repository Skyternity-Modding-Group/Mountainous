package com.skyternity.mountainous.forge

import com.skyternity.mountainous.Moutainous
import com.skyternity.mountainous.client.MoutainousClient
import com.skyternity.mountainous.world.gen.forge.Generator
import me.shedaniel.architectury.platform.forge.EventBuses
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.eventbus.EventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import java.util.function.Consumer

@Mod(Moutainous.MOD_ID)
object MoutainousForge {
    init {
        EventBuses.registerModEventBus(Moutainous.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(::onClientSetup)

        MinecraftForge.EVENT_BUS.addListener { event: BiomeLoadingEvent -> Generator.onBiomeLoad(event) }

        Moutainous.init();
        Registration.init(MOD_BUS);
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        MoutainousClient.init()
    }
}