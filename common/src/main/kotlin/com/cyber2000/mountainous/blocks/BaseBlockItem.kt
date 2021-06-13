package com.cyber2000.mountainous.blocks

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

open class BaseBlockItem(block: Block?, settings: Settings) : BlockItem(block, settings) {

    open fun removeFromBlockToItemMap(blockToItemMap: MutableMap<Block, Item>, itemIn: Item) {}
}