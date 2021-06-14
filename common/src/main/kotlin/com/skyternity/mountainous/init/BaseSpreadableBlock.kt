package com.skyternity.mountainous.init

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.server.world.ServerWorld
import net.minecraft.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldView
import java.util.*
import java.util.function.Supplier

abstract class BaseSpreadableBlock(properties: Settings) : Block(properties.ticksRandomly()), IBaseBlock {
    private var blockItem: Supplier<BlockItem>? = null

    private fun canSurvive(blockState: BlockState, worldView: WorldView, blockPos: BlockPos): Boolean {
        val blockPos2 = blockPos.up()
        val blockState2 = worldView.getBlockState(blockPos2)

        return blockState2.fluidState.level != 8 && worldView.getLightLevel(blockPos.up()) < 9
    }

    private fun canSpread(blockState: BlockState, worldView: WorldView, blockPos: BlockPos): Boolean {
        val blockPos2 = blockPos.up()
        return canSurvive(blockState, worldView, blockPos) && !worldView.getFluidState(blockPos2).isIn(FluidTags.WATER)
    }

    override fun randomTick(blockState: BlockState, serverWorld: ServerWorld, blockPos: BlockPos, random: Random) {
        val canSurvive = canSurvive(blockState, serverWorld, blockPos)
        if (!canSurvive) {
            serverWorld.setBlockState(blockPos, Blocks.DIRT.defaultState)
        } else {
            if (serverWorld.getLightLevel(blockPos) < 9 &&
                serverWorld.getLightLevel(blockPos.up()) < 9 &&
                serverWorld.getLightLevel(blockPos.up().up()) < 9 &&
                serverWorld.getLightLevel(blockPos.up().up().up()) < 9 &&
                serverWorld.getLightLevel(blockPos.up().up().up().up()) < 9 &&
                serverWorld.getLightLevel(blockPos.up().up().up().up().up()) < 9 &&
                serverWorld.getLightLevel(blockPos.up().up().up().up().up().up()) < 9
            ) {
                if (random.nextInt(20) == 0) {
                    val blockState2: BlockState = defaultState
                    for (i in 0..3) {
                        val blockPos2 = blockPos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1)
                        if (serverWorld.getBlockState(blockPos2).isOf(Blocks.DIRT)) {
                            serverWorld.setBlockState(blockPos2, blockState2)
                        }
                    }
                }
            }
        }
    }

    override fun setBlockItem(blockItem: Supplier<BlockItem>?) {
        this.blockItem = blockItem
    }

    override fun getBlockItem(): BlockItem {
        return blockItem!!.get()
    }
}