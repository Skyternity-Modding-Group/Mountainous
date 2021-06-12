package com.cyber2000.mountainous.init

import com.cyber2000.mountainous.blocks.BlockItemBase
import com.cyber2000.mountainous.blocks.DimstoneBlock
import com.cyber2000.mountainous.blocks.GingerRootsBlock
import me.shedaniel.architectury.registry.RegistrySupplier
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import java.util.concurrent.atomic.AtomicReference
import java.util.function.Supplier

object ModBlocks {
    val DIMSTONE_BLOCK: RegistrySupplier<DimstoneBlock> = register("dimstone") { DimstoneBlock() }
    val POLISHED_DIMSTONE_BLOCK: RegistrySupplier<DimstoneBlock> = register("polished_dimstone") { DimstoneBlock() }
    val CHISELED_DIMSTONE_BLOCK: RegistrySupplier<DimstoneBlock> =
        register("chiseled_dimstone_bricks") { DimstoneBlock() }
    val DIMSTONE_BRICKS_BLOCK: RegistrySupplier<DimstoneBlock> = register("dimstone_bricks") { DimstoneBlock() }
    val GINGER_ROOTS_BLOCK: RegistrySupplier<GingerRootsBlock> = register("ginger_roots") { GingerRootsBlock() }

    private fun <T : Block?> register(name: String, supplier: Supplier<T>): RegistrySupplier<T> {
        val registryObject: AtomicReference<RegistrySupplier<T>> = AtomicReference<RegistrySupplier<T>>()
        val itemSupplier = Supplier<BlockItem> { BlockItemBase(registryObject.get().get()) }
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

    fun init() {}
}
