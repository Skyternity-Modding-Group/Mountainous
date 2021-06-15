package com.skyternity.mountainous.init

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.LightType
import net.minecraft.world.World
import net.minecraft.world.chunk.light.LightingProvider
import java.util.*
import java.util.function.Supplier

/**
 * Base spreadable block.
 *
 * @author Qboi
 */
abstract class BaseSpreadableBlock(properties: Settings) : Block(properties.ticksRandomly()), IBaseBlock {
    private var blockItem: Supplier<BlockItem>? = null

    /**
     * Gets if the spreadable block can survive at a specific location.
     *
     * @param
     * @author Qboi
     */
    private fun canSurvive(serverWorld: World, blockPos: BlockPos): Boolean {
        return getSkyVisibility(serverWorld, blockPos) < 9 &&
                getSkyVisibility(serverWorld, blockPos.up()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.east()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.west()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.north()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.south()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.up().up()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.up().up().up()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.up().up().up().up()) < 9
    }

    /**
     * Random ticking of the block, here's where the spreading happens.
     */
    override fun randomTick(blockState: BlockState, serverWorld: ServerWorld, blockPos: BlockPos, random: Random) {
        val canCurrentlySurvive = canSurvive(serverWorld, blockPos)
        // Can survive
        if (!canCurrentlySurvive) {
            // Turn back to dirt.
            serverWorld.setBlockState(blockPos, Blocks.DIRT.defaultState)
        } else {
            // Check sky visibility (light).
            if (getSkyVisibility(serverWorld, blockPos) < 9 &&
                getSkyVisibility(serverWorld, blockPos.up()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.east()) < 9 && // Also checks for the sides.
                getSkyVisibility(serverWorld, blockPos.west()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.north()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.south()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.up().up()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.up().up().up()) < 9 &&
                getSkyVisibility(serverWorld, blockPos.up().up().up().up()) < 9
            ) {
                // Random chance.
                if (random.nextInt(20) == 0) {
                    // Get the current default state.
                    val newState: BlockState = defaultState

                    // 3 times spreading.
                    for (i in 0..3) {
                        // Get random spread position.
                        val spreadPos = blockPos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1)

                        // Check for dirt block.
                        if (serverWorld.getBlockState(spreadPos).isOf(Blocks.DIRT)) {
                            // Check if can survive at spread position
                            if (canSurvive(serverWorld, spreadPos)) {
                                // Set new state.
                                serverWorld.setBlockState(spreadPos, newState)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getPlacementState(itemPlacementContext: ItemPlacementContext): BlockState? {
        val pos: BlockPos = itemPlacementContext.blockPos
        val world = itemPlacementContext.world
        return if (canSurvive(world, pos)) super.getPlacementState(itemPlacementContext)
        else Blocks.DIRT.defaultState
    }

    override fun setBlockItem(blockItem: Supplier<BlockItem>?) {
        this.blockItem = blockItem
    }

    override fun getBlockItem(): BlockItem {
        return blockItem!!.get()
    }

    companion object {

        /**
         * Gets the sky light, day/night cycle doesn't affect this.
         *
         * @param serverWorld the world to check for the sky light.
         * @param blockPos the position to check for the sky light.
         */
        fun getSkyVisibility(serverWorld: World, blockPos: BlockPos): Int {
            val lightingProvider: LightingProvider = serverWorld.chunkManager.lightingProvider
            return lightingProvider[LightType.SKY].getLightLevel(blockPos)
        }
    }
}