package com.skyternity.mountainous.forge

import com.skyternity.mountainous.blocks.*
import com.skyternity.mountainous.init.IBaseBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraftforge.fml.RegistryObject
import java.util.concurrent.atomic.AtomicReference
import java.util.function.Supplier

@Suppress("unused", "MemberVisibilityCanBePrivate")
object ModBlocks {
    val DIMSTONE_BLOCK: RegistryObject<Block> = register("dimstone") { DimstoneBlock() }
    val DIMSTONE_BLOCK_SLAB: RegistryObject<Block> = register("dimstone_slab") { DimstoneSlabBlock() }
    val DIMSTONE_BLOCK_STAIRS: RegistryObject<Block> = register("dimstone_stairs") { DimstoneStairsBlock(DIMSTONE_BLOCK.get().defaultState) }
    val DIMSTONE_BLOCK_WALL: RegistryObject<Block> = registerDeco("dimstone_wall") { DimstoneWallBlock() }
    val POLISHED_DIMSTONE_BLOCK: RegistryObject<Block> = register("polished_dimstone") { DimstoneBlock() }
    val POLISHED_DIMSTONE_BLOCK_SLAB: RegistryObject<Block> = register("polished_dimstone_slab") { DimstoneSlabBlock() }
    val POLISHED_DIMSTONE_BLOCK_STAIRS: RegistryObject<Block> = register("polished_dimstone_stairs") { DimstoneStairsBlock(POLISHED_DIMSTONE_BLOCK.get().defaultState) }
    val POLISHED_DIMSTONE_BLOCK_WALL: RegistryObject<Block> = registerDeco("polished_dimstone_wall") { DimstoneWallBlock() }
    val POLISHED_DIMSTONE_BLOCK_BUTTON: RegistryObject<Block> = registerRedstone("polished_dimstone_button") { DimstoneButtonBlock() }
    val POLISHED_DIMSTONE_BLOCK_PRESSURE_PLATE: RegistryObject<Block> = registerRedstone("polished_dimstone_pressure_plate") { DimstonePressurePlateBlock() }
    val CHISELED_DIMSTONE_BLOCK: RegistryObject<Block> = register("chiseled_dimstone_bricks") { DimstoneBlock() }
    val DIMSTONE_BRICKS_BLOCK: RegistryObject<Block> = register("dimstone_bricks") { DimstoneBlock() }
    val DIMSTONE_BRICKS_BLOCK_SLAB: RegistryObject<Block> = register("dimstone_bricks_slab") { DimstoneSlabBlock() }
    val DIMSTONE_BRICKS_BLOCK_STAIRS: RegistryObject<Block> = register("dimstone_bricks_stairs") { DimstoneStairsBlock(DIMSTONE_BRICKS_BLOCK.get().defaultState) }
    val DIMSTONE_BRICKS_BLOCK_WALL: RegistryObject<Block> = registerDeco("dimstone_bricks_wall") { DimstoneWallBlock() }
    val GINGER_ROOTS_CROP_BLOCK: RegistryObject<Block> = registerNoItem("ginger_crop") { GrowableGingerRootsBlock() }
    val GINGER_ROOTS_BLOCK: RegistryObject<Block> = register("ginger_roots") {
        GingerRootsBlock {
            val get = GINGER_ROOTS_CROP_BLOCK.get()
            if (get is GrowableGingerRootsBlock) {
                return@GingerRootsBlock get
            }
            return@GingerRootsBlock null;
        }
    }

    @JvmStatic
    private fun <T : Block> register(name: String, supplier: Supplier<T>): RegistryObject<T> {
        val registryObject: AtomicReference<RegistryObject<T>> = AtomicReference<RegistryObject<T>>()
        val itemSupplier = Supplier<BlockItem> { BaseBlockItem(registryObject.get().get(), Item.Settings().group(ItemGroup.BUILDING_BLOCKS)) }
        registryObject.set(Registration.BLOCKS.register(name) {
            val t = supplier.get()
            if (t is IBaseBlock) {
                (t as IBaseBlock).setBlockItem(itemSupplier)
            }
            supplier.get()
        })
        Registration.ITEMS.register(name, itemSupplier)
        return registryObject.get()
    }

    @Suppress("SameParameterValue")
    @JvmStatic
    private fun <T : Block> registerNoItem(name: String, supplier: Supplier<T>): RegistryObject<T> {
        return Registration.BLOCKS.register(name, supplier)
    }

    @JvmStatic
    private fun <T : Block> registerRedstone(name: String, supplier: Supplier<T>): RegistryObject<T> {
        val registryObject: AtomicReference<RegistryObject<T>> = AtomicReference<RegistryObject<T>>()
        val itemSupplier = Supplier<BlockItem> { BaseBlockItem(registryObject.get().get(), Item.Settings().group(ItemGroup.REDSTONE)) }
        registryObject.set(Registration.BLOCKS.register(name) {
            val t = supplier.get()
            if (t is IBaseBlock) {
                (t as IBaseBlock).setBlockItem(itemSupplier)
            }
            supplier.get()
        })
        Registration.ITEMS.register(name, itemSupplier)
        return registryObject.get()
    }

    @JvmStatic
    private fun <T : Block> registerDeco(name: String, supplier: Supplier<T>): RegistryObject<T> {
        val registryObject: AtomicReference<RegistryObject<T>> = AtomicReference<RegistryObject<T>>()
        val itemSupplier = Supplier<BlockItem> { BaseBlockItem(registryObject.get().get(), Item.Settings().group(ItemGroup.DECORATIONS)) }
        registryObject.set(Registration.BLOCKS.register(name) {
            val t = supplier.get()
            if (t is IBaseBlock) {
                (t as IBaseBlock).setBlockItem(itemSupplier)
            }
            supplier.get()
        })
        Registration.ITEMS.register(name, itemSupplier)
        return registryObject.get()
    }

    @JvmStatic
    fun init() {}
}