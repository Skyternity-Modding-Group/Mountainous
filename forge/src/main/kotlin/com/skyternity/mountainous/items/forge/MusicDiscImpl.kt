package com.skyternity.mountainous.items.forge

import net.minecraft.item.Item
import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent
import java.lang.AssertionError

object MusicDiscImpl {
    @JvmStatic fun createMusicDisc(comparatorIndex: Int, soundEvent: () -> SoundEvent, settings: Item.Settings): Item {
        return MusicDiscItem(comparatorIndex, soundEvent, settings)
    }
}