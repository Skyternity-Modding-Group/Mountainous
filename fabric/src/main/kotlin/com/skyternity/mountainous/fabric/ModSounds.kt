package com.skyternity.mountainous.fabric

import com.skyternity.mountainous.Mountainous
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

/**
 * Mod Items initialization class.
 *
 * @author Qboi123
 */
object ModSounds {
    val CLIFF_DISC: SoundEvent = register(Mountainous.MOD_ID + ":cliff", SoundEvent(Mountainous.id("disc.promises")))

    @JvmStatic
    @Suppress("SameParameterValue")
    private fun <T : SoundEvent?> register(name: String, item: T): T {
        Registry.register(
            Registry.SOUND_EVENT,
            Identifier(name),
            item
        )
        return item;
    }

    @JvmStatic
    fun init() {}
}