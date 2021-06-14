package com.skyternity.mountainous.init

import net.minecraft.block.*
import net.minecraft.item.BlockItem
import java.util.function.Supplier

abstract class BasePressurePlateBlock(activationRule: ActivationRule, properties: Settings?) : PressurePlateBlock(activationRule, properties), IBaseBlock {
    private var blockItem: Supplier<BlockItem>? = null
    override fun setBlockItem(blockItem: Supplier<BlockItem>?) {
        this.blockItem = blockItem
    }

    override fun getBlockItem(): BlockItem {
        return blockItem!!.get()
    }
}