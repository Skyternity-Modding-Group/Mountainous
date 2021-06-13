package com.cyber2000.mountainous.fabric

import com.cyber2000.mountainous.ArchitecturySkeleton
import com.cyber2000.mountainous.client.ArchitecturySkeletonClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer

@Suppress("unused")
object ArchitecturySkeletonFabric: ModInitializer {
    override fun onInitialize() {
        ArchitecturySkeleton.init()
        Registration.init();
    }
}

@Suppress("unused")
@Environment(EnvType.CLIENT)
object ArchitecturySkeletonFabricClient: ClientModInitializer {
    override fun onInitializeClient() {
        ArchitecturySkeletonClient.init()
    }
}