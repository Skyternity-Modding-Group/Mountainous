@file:JvmName("MusicDisc")
package com.skyternity.mountainous.items

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.item.Item
import net.minecraft.sound.SoundEvent

@ExpectPlatform
fun createMusicDisc(comparatorIndex: Int, soundEvent: () -> SoundEvent, settings: Item.Settings): Item = throw AssertionError()