package com.cyber2000.mountainous.init

import net.minecraft.item.BlockItem
import java.util.function.Supplier

interface IBaseBlock {
    fun setBlockItem(blockItem: Supplier<BlockItem>?)
    fun getBlockItem(): BlockItem
}