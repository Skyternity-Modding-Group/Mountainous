package com.skyternity.mountainous.blocks

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item

open class BaseBlockItem(block: Block?, settings: Settings) : BlockItem(block, settings) {

    open fun removeFromBlockToItemMap(blockToItemMap: MutableMap<Block, Item>, itemIn: Item) {}
}