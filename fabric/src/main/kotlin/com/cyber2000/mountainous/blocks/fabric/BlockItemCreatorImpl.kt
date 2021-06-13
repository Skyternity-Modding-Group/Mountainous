package com.cyber2000.mountainous.blocks.fabric

import com.cyber2000.mountainous.blocks.BaseBlockItem
import com.cyber2000.mountainous.blocks.fabric.BlockItem
import net.minecraft.block.Block
import net.minecraft.item.Item

object BlockItemCreatorImpl {
    @JvmStatic fun createBlockItem(blockLambda: () -> Block?): BaseBlockItem {
        return BlockItem(blockLambda)
    }
}