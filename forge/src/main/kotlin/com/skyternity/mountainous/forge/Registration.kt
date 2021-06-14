package com.skyternity.mountainous.forge

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.sound.SoundEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object Registration {
    val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, "mountainous")
    val BLOCKS: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, "mountainous")
    val SOUNDS: DeferredRegister<SoundEvent> = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "mountainous")
    fun init(eventBus: IEventBus?) {
        ModItems.init()
        ModBlocks.init()
        ModSounds.init()
        ITEMS.register(eventBus)
        BLOCKS.register(eventBus)
        SOUNDS.register(eventBus)
    }
}