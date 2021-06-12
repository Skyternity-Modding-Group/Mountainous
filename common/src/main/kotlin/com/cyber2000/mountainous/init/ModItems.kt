package com.cyber2000.mountainous.init

import com.cyber2000.mountainous.items.*
import me.shedaniel.architectury.registry.RegistrySupplier
import net.minecraft.item.Item
import net.minecraft.item.MusicDiscItem
import java.util.function.Supplier

/**
 * Mod Items initialization class.
 *
 * @author Qboi123
 */
@Suppress("unused")
object ModItems {
    val GINGER: RegistrySupplier<GingerRoot> = register("ginger") { GingerRoot() }
    val GINGER_CANDY: RegistrySupplier<GingerChew> = register("ginger_candy") { GingerChew() }
    val HONEY_CANDY: RegistrySupplier<HoneyCandy> = register("honey_candy") { HoneyCandy() }
    val CLIFF_DISC: RegistrySupplier<MusicDiscItem> = register("cliff_disc") { createMusicDisc(4, { ModSounds.CLIFF_DISC.get() }, Item.Settings()) }
    private fun <T : Item?> register(name: String, supplier: Supplier<T>): RegistrySupplier<T> {
        return Registration.ITEMS.register(name, supplier)
    }

    fun init() {}
}