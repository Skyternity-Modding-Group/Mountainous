package com.cyber2000.mountainous.fabric

import com.cyber2000.mountainous.Mountainous
import com.cyber2000.mountainous.client.MountainousClient
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