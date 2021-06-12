package com.cyber2000.mountainous.fabric.items

import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent

class CustomMusicDiscItemImpl(comparatorIndex: Int, soundEvent: () -> SoundEvent?, settings: Settings?) : MusicDiscItem(comparatorIndex, soundEvent.invoke(), settings) {
}