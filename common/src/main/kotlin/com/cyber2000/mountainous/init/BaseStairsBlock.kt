package com.cyber2000.mountainous.init

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SlabBlock
import net.minecraft.block.StairsBlock
import net.minecraft.item.BlockItem
import java.util.function.Supplier

abstract class BaseStairsBlock(blockState: BlockState, properties: Settings?) : StairsBlock(blockState, properties), IBaseBlock {
    private var blockItem: Supplier<BlockItem>? = null
    override fun setBlockItem(blockItem: Supplier<BlockItem>?) {
        this.blockItem = blockItem
    }

    override fun getBlockItem(): BlockItem {
        return blockItem!!.get()
    }
}