package com.cyber2000.mountainous.init

import com.cyber2000.mountainous.items.CliffDiscItem
import com.cyber2000.mountainous.items.GingerChew
import com.cyber2000.mountainous.items.GingerRoot
import com.cyber2000.mountainous.items.HoneyCandy
import me.shedaniel.architectury.registry.RegistrySupplier
import net.minecraft.item.Item
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
    val CLIFF_DISC: RegistrySupplier<CliffDiscItem> = register("cliff_disc") { CliffDiscItem() }
    private fun <T : Item?> register(name: String, supplier: Supplier<T>): RegistrySupplier<T> {
        return Registration.ITEMS.register(name, supplier)
    }

    fun init() {}
}