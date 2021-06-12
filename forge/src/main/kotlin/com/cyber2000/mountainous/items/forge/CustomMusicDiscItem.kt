package com.cyber2000.mountainous.items.forge

import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent

class CustomMusicDiscItem(comparatorIndex: Int, soundEvent: () -> SoundEvent?, settings: Settings?) : MusicDiscItem(comparatorIndex, soundEvent, settings) {

}