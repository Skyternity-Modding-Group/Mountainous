@file:Suppress("DEPRECATION")

package com.cyber2000.mountainous.items.fabric

import com.google.common.collect.Maps
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Blocks
import net.minecraft.block.JukeboxBlock
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent
import net.minecraft.stat.Stats
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.jetbrains.annotations.Nullable

class CustomMusicDiscItem : MusicDiscItem {

    @Deprecated("")
    private val sound: SoundEvent?
    private val soundSupplier: () -> SoundEvent?



    @Deprecated("")
    constructor(comparatorIndex: Int, soundEvent: SoundEvent?, p_i48475_3_: Settings?) : super(comparatorIndex, EMPTY_SOUND,  p_i48475_3_) {
        sound = soundEvent
        MUSIC_DISCS[sound] = this
        soundSupplier = { sound }
    }

    constructor(comparatorValue: Int, soundSupplier: () -> SoundEvent, builder: Settings?) : super(comparatorValue, EMPTY_SOUND, builder) {
        sound = null
        this.soundSupplier = soundSupplier
    }

    override fun useOnBlock(p_195939_1_: ItemUsageContext): ActionResult {
        val world: World = p_195939_1_.getWorld()
        val blockpos: BlockPos = p_195939_1_.getBlockPos()
        val blockstate = world.getBlockState(blockpos)
        return if (blockstate.isOf(Blocks.JUKEBOX) && !blockstate.get(JukeboxBlock.HAS_RECORD)) {
            val itemstack: ItemStack = p_195939_1_.getStack()
            if (!world.isClient) {
                (Blocks.JUKEBOX as JukeboxBlock).setRecord(
                    world,
                    blockpos,
                    blockstate,
                    itemstack
                )
                world.syncWorldEvent(null as PlayerEntity?, 1010, blockpos, getRawId(this))
                itemstack.decrement(1)
                val playerentity: PlayerEntity? = p_195939_1_.getPlayer()
                if (playerentity != null) {
                    playerentity.incrementStat(Stats.PLAY_RECORD)
                }
            }
            ActionResult.success(world.isClient)
        } else {
            ActionResult.PASS
        }
    }

    @Environment(EnvType.CLIENT)
    override fun appendTooltip(
        p_77624_1_: ItemStack?,
        @Nullable p_77624_2_: World?,
        p_77624_3_: MutableList<Text?>,
        p_77624_4_: TooltipContext?
    ) {
        p_77624_3_.add(description.formatted(Formatting.GRAY))
    }

    @Environment(EnvType.CLIENT)
    override fun getSound(): SoundEvent? {
        return soundSupplier.invoke()
    }

    companion object {
        @Deprecated("")
        private val MUSIC_DISCS: MutableMap<SoundEvent?, CustomMusicDiscItem> = Maps.newHashMap()

        @Suppress("unused")
        @Nullable
        @Environment(EnvType.CLIENT)
        fun bySound(p_185074_0_: SoundEvent?): CustomMusicDiscItem? {
            return MUSIC_DISCS[p_185074_0_]
        }

        val EMPTY_SOUND: SoundEvent = SoundEvent(Identifier("null", "null"))
    }
}