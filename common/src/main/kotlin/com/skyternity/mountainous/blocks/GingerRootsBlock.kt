package com.skyternity.mountainous.blocks

import com.skyternity.mountainous.init.BaseSpreadableBlock
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BoneMealItem
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class GingerRootsBlock(private val crop: () -> GrowableGingerRootsBlock?) : BaseSpreadableBlock(BlockProperties.of(Material.SOIL).tool(ToolType.SHOVEL, 1).strength(0.65f).sounds(BlockSoundGroup.GRAVEL).requiresTool()) {

    private var cropInstance: GrowableGingerRootsBlock? = null

    @Suppress("UNUSED_PARAMETER")
    private fun canSpawnCrop(blockState: BlockState, serverWorld: ServerWorld, blockPos: BlockPos): Boolean {
        return blockState.isAir && blockPos.y > 0 && blockPos.y < 255 && blockState.block !is GrowableGingerRootsBlock
    }

    @Suppress("DEPRECATION")
    override fun randomTick(blockState: BlockState, serverWorld: ServerWorld, blockPos: BlockPos, random: Random) {
        super.randomTick(blockState, serverWorld, blockPos, random)

        if (cropInstance == null) {
            val invoke: GrowableGingerRootsBlock? = crop.invoke()
            cropInstance = invoke
        }

        if (random.nextInt(2) == 0) {
            val below: BlockPos = blockPos.down()
            if (canSpawnCrop(serverWorld.getBlockState(below), serverWorld, below)) {
                val cropState: BlockState = cropInstance!!.defaultState
                if (serverWorld.getBlockState(below).isAir) {
                    serverWorld.setBlockState(below, cropState)
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun onUse(blockState: BlockState, world: World, blockPos: BlockPos, playerEntity: PlayerEntity, hand: Hand?, blockHitResult: BlockHitResult): ActionResult {
        if (playerEntity.getStackInHand(hand).item is BoneMealItem) {
            val below: BlockPos = blockPos.down()
            if (!world.isClient) {
                if (world is ServerWorld) {
                    if (canSpawnCrop(world.getBlockState(below), world, below)) {
                        val cropState: BlockState = cropInstance!!.defaultState
                        if (world.getBlockState(below).isAir) {
                            world.setBlockState(below, cropState)
                            return ActionResult.SUCCESS
                        }
                    } else {
                        return ActionResult.FAIL
                    }
                } else {
                    return ActionResult.PASS
                }
            } else {
                return ActionResult.PASS
            }
        }
        return super.onUse(blockState, world, blockPos, playerEntity, hand, blockHitResult)
    }
}