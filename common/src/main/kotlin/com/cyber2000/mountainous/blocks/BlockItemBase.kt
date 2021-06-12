package com.cyber2000.mountainous.blocks

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroup

class BlockItemBase(block: Block?) : BlockItem(block, Settings().group(ItemGroup.BUILDING_BLOCKS)) {
}