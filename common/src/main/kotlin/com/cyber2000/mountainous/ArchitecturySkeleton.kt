package com.cyber2000.mountainous

import com.cyber2000.mountainous.init.Registration
import net.minecraft.util.Identifier

object ArchitecturySkeleton {
    const val MOD_ID = "mountainous"

    fun init() {
        com.cyber2000.mountainous.printHelloWorld()
        Registration.init();
    }

    fun id(path: String): Identifier {
        return Identifier(com.cyber2000.mountainous.ArchitecturySkeleton.MOD_ID, path)
    }

    @JvmStatic
    fun identifier(path: String): Identifier {
        return Identifier(com.cyber2000.mountainous.ArchitecturySkeleton.MOD_ID, path)
    }
}