package com.cyber2000.mountainous.init

import com.cyber2000.mountainous.ArchitecturySkeleton
import me.shedaniel.architectury.registry.RegistrySupplier
import net.minecraft.sound.SoundEvent
import java.util.function.Supplier

/**
 * Mod Items initialization class.
 *
 * @author Qboi123
 */
object ModSounds {
    val CLIFF_DISC: RegistrySupplier<SoundEvent> =
        register("cliff") { SoundEvent(com.cyber2000.mountainous.ArchitecturySkeleton.id("disc.promises")) }

    private fun <T : SoundEvent?> register(name: String, supplier: Supplier<T>): RegistrySupplier<SoundEvent> {
        return Registration.SOUNDS.register(name, supplier)
    }

    fun init() {}
}