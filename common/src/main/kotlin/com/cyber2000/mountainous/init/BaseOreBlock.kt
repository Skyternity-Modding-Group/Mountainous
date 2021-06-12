package com.cyber2000.mountainous.init

import net.minecraft.block.OreBlock
import net.minecraft.item.BlockItem
import java.util.function.Supplier

abstract class BaseOreBlock(properties: Settings?) : OreBlock(properties), IBaseBlock {
    private var blockItem: Supplier<BlockItem>? = null
    override fun setBlockItem(blockItem: Supplier<BlockItem>?) {
        this.blockItem = blockItem
    }

    override fun getBlockItem(): BlockItem {
        return blockItem!!.get()
    }
}