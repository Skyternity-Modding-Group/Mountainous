package com.cyber2000.mountainous.blocks

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

open class BaseBlockItem(block: Block?) : BlockItem(block, Settings().group(ItemGroup.BUILDING_BLOCKS)) {

    open fun removeFromBlockToItemMap(blockToItemMap: MutableMap<Block, Item>, itemIn: Item) {}
}