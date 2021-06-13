@file:Suppress("DEPRECATION")

package com.cyber2000.mountainous.blocks.forge

import com.cyber2000.mountainous.blocks.BaseBlockItem
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
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

class BlockItem(@Deprecated("") private val block: () -> Block) :
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
            val placementContext = getPlacementContext(p_195942_1_)
            run {
                val blockstate = getPlacementState(placementContext)
                if (blockstate == null) {
                    ActionResult.FAIL
                } else if (!this.place(placementContext, blockstate)) {
                    ActionResult.FAIL
                } else {
                    val blockPos = placementContext.blockPos
                    val world = placementContext.world
                    val playerEntity = placementContext.player
                    val itemStack = placementContext.stack
                    var blockState1 = world.getBlockState(blockPos)
                    val block = blockState1.block
                    if (block === blockstate.block) {
                        blockState1 = placeFromTag(blockPos, world, itemStack, blockState1)
                        postPlacement(blockPos, world, playerEntity, itemStack, blockState1)
                        block.onPlaced(world, blockPos, blockState1, playerEntity, itemStack)
                        if (playerEntity is ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger(
                                playerEntity as ServerPlayerEntity?,
                                blockPos,
                                itemStack
                            )
                        }
                    }
                    val soundGroup = blockState1.getSoundType(world, blockPos, p_195942_1_.player)
                    world.playSound(
                        playerEntity,
                        blockPos,
                        this.getPlaceSound(blockState1),
                        SoundCategory.BLOCKS,
                        (soundGroup.getVolume() + 1.0f) / 2.0f,
                        soundGroup.getPitch() * 0.8f
                    )
                    if (playerEntity == null || !playerEntity.abilities.creativeMode) {
                        itemStack.decrement(1)
                    }
                    ActionResult.success(world.isClient)
                }
            }
        }
    }

    override fun getPlaceSound(state: BlockState?): SoundEvent {
        return state!!.soundGroup.placeSound
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
        val compoundNbt = p_219985_3_.tag
        if (compoundNbt != null) {
            val compoundNbt1 = compoundNbt.getCompound("BlockStateTag")
            val stateContainer = p_219985_4_.block.stateManager
            for (s in compoundNbt1.keys) {
                val property = stateContainer.getProperty(s)
                if (property != null) {
                    val s1 = compoundNbt1[s]!!.asString()
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
        val playerEntity = p_195944_1_.player
        val selectionContext = if (playerEntity == null) ShapeContext.absent() else ShapeContext.of(playerEntity)
        return (!checkStatePlacement() || p_195944_2_.canPlaceAt(
            p_195944_1_.world,
            p_195944_1_.blockPos
        )) && p_195944_1_.world.canPlace(p_195944_2_, p_195944_1_.blockPos, selectionContext)
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

    @OnlyIn(Dist.CLIENT)
    override fun appendTooltip(
        p_77624_1_: ItemStack,
        p_77624_2_: World?,
        p_77624_3_: List<Text>,
        p_77624_4_: TooltipContext
    ) {
        super.appendTooltip(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_)
        getBlock()!!.appendTooltip(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_)
    }

    override fun getBlock(): Block? {
        return blockRaw.delegate.get()
    }

    private val blockRaw: Block
        get() = this.block.invoke()

    override fun appendBlocks(p_195946_1_: MutableMap<Block, Item>, p_195946_2_: Item) {
        p_195946_1_[getBlock()!!] = p_195946_2_
    }

    override fun removeFromBlockToItemMap(blockToItemMap: MutableMap<Block, Item>, itemIn: Item) {
        blockToItemMap.remove(getBlock())
    }

    companion object {
        private fun <T : Comparable<T>?> with(
            p_219988_0_: BlockState,
            p_219988_1_: Property<T>,
            p_219988_2_: String
        ): BlockState {
            return p_219988_1_.parse(p_219988_2_).map { p_219986_2_: T -> p_219988_0_.with(p_219988_1_, p_219986_2_) }
                .orElse(p_219988_0_)
        }

        fun writeTagToBlockEntity(
            world: World,
            player: PlayerEntity?,
            blockPos: BlockPos,
            itemStack: ItemStack
        ): Boolean {
            val minecraftServer = world.server
            return if (minecraftServer == null) {
                false
            } else {
                val tag = itemStack.getSubTag("BlockEntityTag")
                if (tag != null) {
                    val tileentity = world.getBlockEntity(blockPos)
                    if (tileentity != null) {
                        if (!world.isClient && tileentity.copyItemDataRequiresOperator() && (player == null || !player.isCreativeLevelTwoOp)) {
                            return false
                        }
                        val tagTransformed = tileentity.toTag(CompoundTag())
                        val tagCopy = tagTransformed.copy()
                        tagTransformed.copyFrom(tag)
                        tagTransformed.putInt("x", blockPos.x)
                        tagTransformed.putInt("y", blockPos.y)
                        tagTransformed.putInt("z", blockPos.z)
                        if (tagTransformed != tagCopy) {
                            tileentity.fromTag(world.getBlockState(blockPos), tagTransformed)
                            tileentity.markDirty()
                            return true
                        }
                    }
                }
                false
            }
        }
    }
}