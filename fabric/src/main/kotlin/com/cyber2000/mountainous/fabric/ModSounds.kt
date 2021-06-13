package com.cyber2000.mountainous.fabric

import com.cyber2000.mountainous.ArchitecturySkeleton
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

/**
 * Mod Items initialization class.
 *
 * @author Qboi123
 */
object ModSounds {
    val CLIFF_DISC: SoundEvent = register(ArchitecturySkeleton.MOD_ID + ":cliff", SoundEvent(ArchitecturySkeleton.id("disc.promises")))

    @Suppress("SameParameterValue")
    private fun <T : SoundEvent?> register(name: String, item: T): T {
        Registry.register(
            Registry.SOUND_EVENT,
            Identifier(name),
            item
        )
        return item;
    }

    fun init() {}
}