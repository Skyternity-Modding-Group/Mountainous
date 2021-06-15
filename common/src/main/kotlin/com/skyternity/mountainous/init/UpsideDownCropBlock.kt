package com.skyternity.mountainous.init

import net.minecraft.block.BlockState
import net.minecraft.block.CropBlock
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldView

open class UpsideDownCropBlock protected constructor(settings: Settings?) : CropBlock(settings) {
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("")
    override fun canPlantOnTop(blockState: BlockState, blockView: BlockView, blockPos: BlockPos): Boolean {
        return super.canPlantOnTop(blockState, blockView, blockPos)
    }

    override fun canPlaceAt(blockState: BlockState, worldView: WorldView, blockPos: BlockPos): Boolean {
        val blockPos1 = blockPos.up()
        return worldView.getBlockState(blockPos1).isSideSolidFullSquare(worldView, blockPos1, Direction.DOWN) && this.canPlantHangBelow(worldView.getBlockState(blockPos1), worldView, blockPos1)
    }

    override fun getOutlineShape(blockState: BlockState?, blockView: BlockView?, blockPos: BlockPos?, shapeContext: ShapeContext?): VoxelShape {
        return AGE_TO_SHAPE[(blockState!!.get(this.ageProperty) as Int)]
    }

    open fun canPlantHangBelow(blockState: BlockState, worldView: WorldView, blockPos: BlockPos): Boolean = blockPos.y in 1..256

    companion object {
        val AGE_TO_SHAPE: Array<VoxelShape> = arrayOf(
            createCuboidShape(0.0, 16.0, 0.0, 16.0, 12.0, 16.0),
            createCuboidShape(0.0, 16.0, 0.0, 16.0, 12.0, 16.0),
            createCuboidShape(0.0, 16.0, 0.0, 16.0, 8.0, 16.0),
            createCuboidShape(0.0, 16.0, 0.0, 16.0, 8.0, 16.0),
            createCuboidShape(0.0, 16.0, 0.0, 16.0, 4.0, 16.0),
            createCuboidShape(0.0, 16.0, 0.0, 16.0, 4.0, 16.0),
            createCuboidShape(0.0, 16.0, 0.0, 16.0, 0.0, 16.0),
            createCuboidShape(0.0, 16.0, 0.0, 16.0, 0.0, 16.0))
    }
}