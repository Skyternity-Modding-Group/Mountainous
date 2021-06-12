package com.cyber2000.mountainous.init

import me.shedaniel.architectury.registry.DeferredRegister
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.sound.SoundEvent
import net.minecraft.util.registry.Registry

object Registration {
    val ITEMS: DeferredRegister<Item> = DeferredRegister.create("mountainous", Registry.ITEM_KEY)
    val BLOCKS: DeferredRegister<Block> = DeferredRegister.create("mountainous", Registry.BLOCK_KEY)
    val SOUNDS: DeferredRegister<SoundEvent> = DeferredRegister.create("mountainous", Registry.SOUND_EVENT_KEY)

    fun init() {
        ModItems.init()
        ModBlocks.init()
        ModSounds.init()
        ITEMS.register()
        BLOCKS.register()
        SOUNDS.register()
    }
}