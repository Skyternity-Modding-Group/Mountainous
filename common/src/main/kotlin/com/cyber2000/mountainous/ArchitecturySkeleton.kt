package com.cyber2000.mountainous

import com.cyber2000.mountainous.world.gen.loadFeatures
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object ArchitecturySkeleton {
    val LOGGER: Logger = LogManager.getLogger("Mountainous")
    const val MOD_ID = "mountainous"

    fun init() {
        printHelloWorld()
        loadFeatures()
    }

    fun id(path: String): Identifier {
        return Identifier(com.cyber2000.mountainous.ArchitecturySkeleton.MOD_ID, path)
    }
}