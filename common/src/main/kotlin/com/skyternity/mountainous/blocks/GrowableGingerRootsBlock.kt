package com.skyternity.mountainous.blocks

import com.skyternity.mountainous.init.UpsideDownCropBlock
import net.minecraft.block.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldView

open class GrowableGingerRootsBlock : UpsideDownCropBlock(Settings.of(Material.PLANT, MaterialColor.BROWN_TERRACOTTA).breakInstantly().nonOpaque().noCollision()) {
    override fun canPlantHangBelow(blockState: BlockState, worldView: WorldView, blockPos: BlockPos): Boolean {
        return blockState.block == Blocks.STONE && super.canPlantHangBelow(blockState, worldView, blockPos)
    }
}