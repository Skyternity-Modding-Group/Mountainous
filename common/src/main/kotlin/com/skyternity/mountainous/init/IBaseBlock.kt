package com.skyternity.mountainous.init

import net.minecraft.item.BlockItem
import java.util.function.Supplier

interface IBaseBlock {
    fun setBlockItem(blockItem: Supplier<BlockItem>?)
    fun getBlockItem(): BlockItem
}