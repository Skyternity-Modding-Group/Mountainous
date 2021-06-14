package com.skyternity.mountainous.init

import net.minecraft.block.Block
import net.minecraft.block.SlabBlock
import net.minecraft.item.BlockItem
import java.util.function.Supplier

abstract class BaseSlabBlock(properties: Settings?) : SlabBlock(properties), IBaseBlock {
    private var blockItem: Supplier<BlockItem>? = null
    override fun setBlockItem(blockItem: Supplier<BlockItem>?) {
        this.blockItem = blockItem
    }

    override fun getBlockItem(): BlockItem {
        return blockItem!!.get()
    }
}