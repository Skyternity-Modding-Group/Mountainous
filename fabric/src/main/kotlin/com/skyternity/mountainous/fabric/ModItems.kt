package com.skyternity.mountainous.fabric

import com.skyternity.mountainous.Mountainous
import com.skyternity.mountainous.items.*
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry

/**
 * Mod Items initialization class.
 *
 * @author Qboi123
 */
@Suppress("unused")
object ModItems {
    val GINGER: GingerRoot = register(Mountainous.MOD_ID + ":ginger", GingerRoot())
    val GINGER_CANDY: GingerChew = register(Mountainous.MOD_ID + ":ginger_candy", GingerChew())
    val HONEY_CANDY: HoneyCandy = register(Mountainous.MOD_ID + ":honey_candy", HoneyCandy())
    val CLIFF_DISC: Item = register(Mountainous.MOD_ID + ":cliff_disc", createMusicDisc(
            4, { ModSounds.CLIFF_DISC }, Item.Settings().group(
                ItemGroup.MISC
            ).maxCount(1).rarity(Rarity.RARE)
        ))

    @JvmStatic
    private fun <T : Item?> register(name: String, item: T): T {
        Registry.register(
            Registry.ITEM,
            Identifier(name),
            item
        )
        return item;
    }

    @JvmStatic
    fun init() {}
}