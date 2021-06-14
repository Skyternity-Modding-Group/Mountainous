package com.skyternity.mountainous.fabric

import com.skyternity.mountainous.Mountainous
import com.skyternity.mountainous.blocks.*
import com.skyternity.mountainous.init.IBaseBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


@Suppress("MemberVisibilityCanBePrivate", "unused")
object ModBlocks {
    val DIMSTONE_BLOCK: Block = register(Mountainous.MOD_ID + ":dimstone", DimstoneBlock())
    val DIMSTONE_SLAB: Block = register(Mountainous.MOD_ID + ":dimstone_slab", DimstoneSlabBlock())
    val DIMSTONE_STAIRS: Block = register(Mountainous.MOD_ID + ":dimstone_stairs", DimstoneStairsBlock(DIMSTONE_BLOCK.defaultState))
    val DIMSTONE_WALL: Block = registerDeco(Mountainous.MOD_ID + ":dimstone_wall", DimstoneWallBlock())
    val POLISHED_DIMSTONE_BLOCK: Block = register(Mountainous.MOD_ID + ":polished_dimstone", DimstoneBlock())
    val POLISHED_DIMSTONE_SLAB: Block = register(Mountainous.MOD_ID + ":polished_dimstone_slab", DimstoneSlabBlock())
    val POLISHED_DIMSTONE_STAIRS: Block = register(Mountainous.MOD_ID + ":polished_dimstone_stairs", DimstoneStairsBlock(POLISHED_DIMSTONE_BLOCK.defaultState))
    val POLISHED_DIMSTONE_WALL: Block = registerDeco(Mountainous.MOD_ID + ":polished_dimstone_wall", DimstoneWallBlock())
    val POLISHED_DIMSTONE_BUTTON: Block = registerRedstone(Mountainous.MOD_ID + ":polished_dimstone_button", DimstoneButtonBlock())
    val POLISHED_DIMSTONE_PRESSURE_PLATE: Block = registerRedstone(Mountainous.MOD_ID + ":polished_dimstone_pressure_plate", DimstonePressurePlateBlock())
    val CHISELED_DIMSTONE_BLOCK: Block = register(Mountainous.MOD_ID + ":chiseled_dimstone_bricks", DimstoneBlock())
    val DIMSTONE_BRICKS_BLOCK: Block = register(Mountainous.MOD_ID + ":dimstone_bricks", DimstoneBlock())
    val DIMSTONE_BRICKS_SLAB: Block = register(Mountainous.MOD_ID + ":dimstone_bricks_slab", DimstoneSlabBlock())
    val DIMSTONE_BRICKS_STAIRS: Block = register(Mountainous.MOD_ID + ":dimstone_bricks_stairs", DimstoneStairsBlock(DIMSTONE_BRICKS_BLOCK.defaultState))
    val DIMSTONE_BRICKS_WALL: Block = registerDeco(Mountainous.MOD_ID + ":dimstone_bricks_wall", DimstoneWallBlock())
    val GINGER_ROOTS_BLOCK: Block = register(Mountainous.MOD_ID + ":ginger_roots", GingerRootsBlock())

    @JvmStatic
    private fun <T : Block> register(name: String, t: T): T {
        val blockItem = BlockItem(t, FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS))
        Registry.register(Registry.BLOCK, Identifier(name), t)
        Registry.register(
            Registry.ITEM,
            Identifier(name),
            blockItem
        )

        if (t is IBaseBlock) {
            (t as IBaseBlock).setBlockItem { blockItem }
        }

        return t
    }

    @JvmStatic
    private fun <T : Block> registerRedstone(name: String, t: T): T {
        val blockItem = BlockItem(t, FabricItemSettings().group(ItemGroup.REDSTONE))
        Registry.register(Registry.BLOCK, Identifier(name), t)
        Registry.register(
            Registry.ITEM,
            Identifier(name),
            blockItem
        )

        if (t is IBaseBlock) {
            (t as IBaseBlock).setBlockItem { blockItem }
        }

        return t
    }

    @JvmStatic
    private fun <T : Block> registerDeco(name: String, t: T): T {
        val blockItem = BlockItem(t, FabricItemSettings().group(ItemGroup.DECORATIONS))
        Registry.register(Registry.BLOCK, Identifier(name), t)
        Registry.register(
            Registry.ITEM,
            Identifier(name),
            blockItem
        )

        if (t is IBaseBlock) {
            (t as IBaseBlock).setBlockItem { blockItem }
        }

        return t
    }

    @JvmStatic
    fun init() {}
}
