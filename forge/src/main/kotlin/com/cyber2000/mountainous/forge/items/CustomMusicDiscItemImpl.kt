package com.cyber2000.mountainous.forge.items

import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent

class CustomMusicDiscItemImpl(i: Int, soundEvent: () -> SoundEvent?, settings: Settings?) : MusicDiscItem(i, soundEvent, settings) {

}