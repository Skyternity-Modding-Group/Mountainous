package com.cyber2000.mountainous.items

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.item.Item
import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent

open class CustomMusicDiscItem(comparatorIndex: Int, soundEvent: () -> SoundEvent?, settings: Item.Settings?) : MusicDiscItem(comparatorIndex, soundEvent.invoke(), settings) {
    @ExpectPlatform
    override fun getSound(): SoundEvent = throw AssertionError()
}