package com.skyternity.mountainous.fabric

import com.skyternity.mountainous.Mountainous
import com.skyternity.mountainous.client.MountainousClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer

@Suppress("unused")
object MountainousFabric: ModInitializer {
    override fun onInitialize() {
        Mountainous.init()
        Registration.init();
    }
}

@Suppress("unused")
@Environment(EnvType.CLIENT)
object MountainousFabricClient: ClientModInitializer {
    override fun onInitializeClient() {
        MountainousClient.init()
    }
}