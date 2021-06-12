@file:JvmName("MusicDisc")
package com.cyber2000.mountainous.items

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.item.Item
import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent

@ExpectPlatform
fun createMusicDisc(comparatorIndex: Int, soundEvent: () -> SoundEvent, settings: Item.Settings): MusicDiscItem = throw AssertionError()