package com.skyternity.mountainous.forge

import com.skyternity.mountainous.Moutainous
import net.minecraft.sound.SoundEvent
import net.minecraftforge.fml.RegistryObject
import java.util.function.Supplier

/**
 * Mod Items initialization class.
 *
 * @author Qboi123
 */
object ModSounds {
    val CLIFF_DISC: RegistryObject<SoundEvent> =
        register("cliff") { SoundEvent(Moutainous.id("disc.promises")) }

    @JvmStatic
    @Suppress("SameParameterValue")
    private fun <T : SoundEvent?> register(name: String, supplier: Supplier<T>): RegistryObject<T> {
        return Registration.SOUNDS.register(name, supplier)
    }

    @JvmStatic
    fun init() {}
}