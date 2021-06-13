package com.cyber2000.mountainous.fabric

import com.cyber2000.mountainous.ArchitecturySkeleton
import com.cyber2000.mountainous.items.*
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
    val GINGER: GingerRoot = register(ArchitecturySkeleton.MOD_ID + ":ginger", GingerRoot())
    val GINGER_CANDY: GingerChew = register(ArchitecturySkeleton.MOD_ID + ":ginger_candy", GingerChew())
    val HONEY_CANDY: HoneyCandy = register(ArchitecturySkeleton.MOD_ID + ":honey_candy", HoneyCandy())
    val CLIFF_DISC: Item = register(ArchitecturySkeleton.MOD_ID + ":cliff_disc", createMusicDisc(
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