package com.skyternity.mountainous.blocks

import com.skyternity.mountainous.init.BaseSpreadableBlock
import com.skyternity.mountainous.init.UpsideDownCropBlock
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.block.ShapeContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldView
import java.util.*

open class GrowableGingerRootsBlock : UpsideDownCropBlock(Settings.of(Material.PLANT, MaterialColor.BROWN_TERRACOTTA).breakInstantly().nonOpaque().noCollision()) {
    override fun canPlantHangBelow(blockState: BlockState, worldView: WorldView, blockPos: BlockPos): Boolean {
        return blockState.block is GingerRootsBlock && super.canPlantHangBelow(blockState, worldView, blockPos)
    }

    override fun getOutlineShape(blockState: BlockState?, blockView: BlockView?, blockPos: BlockPos?, shapeContext: ShapeContext?): VoxelShape {
        return AGE_TO_SHAPE[(blockState!!.get(this.ageProperty) as Int)]
    }

    override fun randomTick(blockState: BlockState, serverWorld: ServerWorld, blockPos: BlockPos, random: Random) {
        if (BaseSpreadableBlock.getSkyVisibility(serverWorld, blockPos) < 9) {
            val i = getAge(blockState)
            if (i < this.maxAge) {
                val f = getAvailableMoisture(this, serverWorld, blockPos)
                if (random.nextInt(6.25f.toInt() + 1) == 0) {
                    serverWorld.setBlockState(blockPos, withAge(i + 1), 2)
                }
            }
        }
    }

    companion object {
        val AGE_TO_SHAPE: Array<VoxelShape> = arrayOf(
            createCuboidShape(2.0, 16.0, 2.0, 14.0, 14.0, 14.0),
            createCuboidShape(2.0, 16.0, 2.0, 14.0, 14.0, 14.0),
            createCuboidShape(2.0, 16.0, 2.0, 14.0, 12.0, 14.0),
            createCuboidShape(2.0, 16.0, 2.0, 14.0, 12.0, 14.0),
            createCuboidShape(2.0, 16.0, 2.0, 14.0, 10.0, 14.0),
            createCuboidShape(2.0, 16.0, 2.0, 14.0, 10.0, 14.0),
            createCuboidShape(2.0, 16.0, 2.0, 14.0, 7.0, 14.0),
            createCuboidShape(2.0, 16.0, 2.0, 14.0, 7.0, 14.0))
    }
}
