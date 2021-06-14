package com.skyternity.mountainous.items.fabric

import net.minecraft.item.Item
import net.minecraft.sound.SoundEvent

object MusicDiscImpl {
    @JvmStatic fun createMusicDisc(comparatorIndex: Int, soundEvent: () -> SoundEvent, settings: Item.Settings): Item {
        return CustomMusicDiscItem(comparatorIndex, soundEvent, settings)
    }
}