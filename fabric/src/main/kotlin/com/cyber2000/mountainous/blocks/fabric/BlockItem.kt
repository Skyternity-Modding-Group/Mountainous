@file:Suppress("DEPRECATION")

package com.cyber2000.mountainous.blocks.fabric

import com.cyber2000.mountainous.blocks.BaseBlockItem
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.advancement.criterion.Criteria
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.state.property.Property
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockItem(@Deprecated("") private val block: () -> Block?) :
    BaseBlockItem(null) {

    override fun useOnBlock(p_195939_1_: ItemUsageContext): ActionResult {
        val actionResult = this.place(ItemPlacementContext(p_195939_1_))
        return if (!actionResult.isAccepted && this.isFood) this.use(
            p_195939_1_.world,
            p_195939_1_.player,
            p_195939_1_.hand
        ).result else actionResult
    }

    override fun place(p_195942_1_: ItemPlacementContext): ActionResult {
        return if (!p_195942_1_.canPlace()) {
            ActionResult.FAIL
        } else {
            val blockItemUseContext = getPlacementContext(p_195942_1_)
            run {
                val placementState = getPlacementState(blockItemUseContext)
                if (placementState == null) {
                    ActionResult.FAIL
                } else if (!this.place(blockItemUseContext, placementState)) {
                    ActionResult.FAIL
                } else {
                    val pos = blockItemUseContext.blockPos
                    val world = blockItemUseContext.world
                    val player = blockItemUseContext.player
                    val stack = blockItemUseContext.stack
                    var worldBlockState = world.getBlockState(pos)
                    val block = worldBlockState.block
                    if (block === placementState.block) {
                        worldBlockState = placeFromTag(pos, world, stack, worldBlockState)
                        postPlacement(pos, world, player, stack, worldBlockState)
                        block.onPlaced(world, pos, worldBlockState, player, stack)
                        if (player is ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger(
                                player as ServerPlayerEntity?,
                                pos,
                                stack
                            )
                        }
                    }
                    val soundGroup = worldBlockState.soundGroup
                    world.playSound(
                        player,
                        pos,
                        getPlaceSound(worldBlockState),
                        SoundCategory.BLOCKS,
                        (soundGroup.getVolume() + 1.0f) / 2.0f,
                        soundGroup.getPitch() * 0.8f
                    )
                    if (player == null || !player.abilities.creativeMode) {
                        stack.decrement(1)
                    }
                    ActionResult.success(world.isClient)
                }
            }
        }
    }

    override fun getPlaceSound(state: BlockState): SoundEvent {
        return state.soundGroup.placeSound
    }

    override fun getPlacementContext(p_219984_1_: ItemPlacementContext): ItemPlacementContext {
        return p_219984_1_
    }

    override fun postPlacement(
        p_195943_1_: BlockPos,
        p_195943_2_: World,
        p_195943_3_: PlayerEntity?,
        p_195943_4_: ItemStack,
        p_195943_5_: BlockState
    ): Boolean {
        return writeTagToBlockEntity(p_195943_2_, p_195943_3_, p_195943_1_, p_195943_4_)
    }

    override fun getPlacementState(p_195945_1_: ItemPlacementContext): BlockState? {
        val blockstate = getBlock()!!.getPlacementState(p_195945_1_)
        return if (blockstate != null && canPlace(p_195945_1_, blockstate)) blockstate else null
    }

    private fun placeFromTag(
        p_219985_1_: BlockPos,
        p_219985_2_: World,
        p_219985_3_: ItemStack,
        p_219985_4_: BlockState
    ): BlockState {
        var blockstate = p_219985_4_
        val tag = p_219985_3_.tag
        if (tag != null) {
            val tagCompound = tag.getCompound("BlockStateTag")
            val stateManager = p_219985_4_.block.stateManager
            for (s in tagCompound.keys) {
                val property = stateManager.getProperty(s)
                if (property != null) {
                    val s1 = tagCompound[s]!!.asString()
                    blockstate = with(blockstate, property, s1)
                }
            }
        }
        if (blockstate !== p_219985_4_) {
            p_219985_2_.setBlockState(p_219985_1_, blockstate, 2)
        }
        return blockstate
    }

    override fun canPlace(p_195944_1_: ItemPlacementContext, p_195944_2_: BlockState): Boolean {
        val player = p_195944_1_.player
        val shapeContext = if (player == null) ShapeContext.absent() else ShapeContext.of(player)
        return (!checkStatePlacement() || p_195944_2_.canPlaceAt(
            p_195944_1_.world,
            p_195944_1_.blockPos
        )) && p_195944_1_.world.canPlace(p_195944_2_, p_195944_1_.blockPos, shapeContext)
    }

    override fun checkStatePlacement(): Boolean {
        return true
    }

    override fun place(p_195941_1_: ItemPlacementContext, p_195941_2_: BlockState): Boolean {
        return p_195941_1_.world.setBlockState(p_195941_1_.blockPos, p_195941_2_, 11)
    }

    override fun getTranslationKey(): String {
        return getBlock()!!.translationKey
    }

    override fun appendStacks(p_150895_1_: ItemGroup, p_150895_2_: DefaultedList<ItemStack>) {
        if (this.isIn(p_150895_1_)) {
            getBlock()!!.addStacksForDisplay(p_150895_1_, p_150895_2_)
        }
    }

    @Environment(EnvType.CLIENT)
    override fun appendTooltip(
        p_77624_1_: ItemStack,
        p_77624_2_: World?,
        p_77624_3_: List<Text>,
        p_77624_4_: TooltipContext
    ) {
        super.appendTooltip(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_)
        getBlock()?.appendTooltip(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_)
    }

    override fun getBlock(): Block? {
        return try {
            val block = this.block.invoke()
            block
        } catch (t: Throwable) {
            null
        }
    }

    @Suppress("unused")
    private val blockRaw: Block?
        get() = this.block.invoke()

    override fun appendBlocks(p_195946_1_: MutableMap<Block, Item>, p_195946_2_: Item) {
        p_195946_1_[getBlock()!!] = p_195946_2_
    }

    override fun removeFromBlockToItemMap(blockToItemMap: MutableMap<Block, Item>, itemIn: Item) {
        blockToItemMap.remove(getBlock())
    }

    companion object {
        private fun <T : Comparable<T>?> with(
            state: BlockState,
            property: Property<T>,
            name: String
        ): BlockState {
            return property.parse(name).map { p_219986_2_: T -> state.with(property, p_219986_2_) }
                .orElse(state)
        }

        fun writeTagToBlockEntity(
            world: World,
            player: PlayerEntity?,
            pos: BlockPos,
            stack: ItemStack
        ): Boolean {
            val server = world.server
            return if (server == null) {
                false
            } else {
                val tag = stack.getSubTag("BlockEntityTag")
                if (tag != null) {
                    val blockEntity = world.getBlockEntity(pos)
                    if (blockEntity != null) {
                        if (!world.isClient && blockEntity.copyItemDataRequiresOperator() && (player == null || !player.isCreativeLevelTwoOp)) {
                            return false
                        }
                        val transformedTag = blockEntity.toTag(CompoundTag())
                        val tagCopy = transformedTag.copy()
                        transformedTag.copyFrom(tag)
                        transformedTag.putInt("x", pos.x)
                        transformedTag.putInt("y", pos.y)
                        transformedTag.putInt("z", pos.z)
                        if (transformedTag != tagCopy) {
                            blockEntity.fromTag(world.getBlockState(pos), transformedTag)
                            blockEntity.markDirty()
                            return true
                        }
                    }
                }
                false
            }
        }
    }
}