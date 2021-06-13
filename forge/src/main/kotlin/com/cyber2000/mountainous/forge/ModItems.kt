package com.cyber2000.mountainous.forge

import com.cyber2000.mountainous.items.*
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Rarity
import net.minecraftforge.fml.RegistryObject
import java.util.function.Supplier

/**
 * Mod Items initialization class.
 *
 * @author Qboi123
 */
object ModItems {
    val GINGER: RegistryObject<Item> = register("ginger") { GingerRoot() }
    val GINGER_CANDY: RegistryObject<Item> = register("ginger_candy") { GingerChew() }
    val HONEY_CANDY: RegistryObject<Item> = register("honey_candy") { HoneyCandy() }
    val CLIFF_DISC: RegistryObject<Item> = register("cliff_disc") {
        createMusicDisc(
            4, { ModSounds.CLIFF_DISC.get() }, Item.Settings().group(
                ItemGroup.MISC
            ).maxCount(1).rarity(Rarity.RARE)
        )
    }

    @JvmStatic
    private fun <T : Item?> register(name: String, supplier: Supplier<T>): RegistryObject<T> {
        return Registration.ITEMS.register(name, supplier)
    }

    @JvmStatic
    fun init() {}
}