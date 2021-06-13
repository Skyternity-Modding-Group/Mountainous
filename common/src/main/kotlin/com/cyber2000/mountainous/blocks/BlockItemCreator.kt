@file:JvmName("BlockItemCreator")
package com.cyber2000.mountainous.blocks

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.block.Block

@ExpectPlatform
fun createBlockItem(block: () -> Block?): BaseBlockItem = throw AssertionError()