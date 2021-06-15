package com.skyternity.mountainous

import com.skyternity.mountainous.world.gen.loadFeatures
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Mountainous {
    val LOGGER: Logger = LogManager.getLogger("Mountainous")
    const val MOD_ID = "mountainous"

    fun init() {
        printHelloWorld()
        loadFeatures()
    }

    fun id(path: String): Identifier {
        return Identifier(MOD_ID, path)
    }
}