package com.cyber2000.mountainous.blocks

import com.cyber2000.mountainous.init.*
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.sound.BlockSoundGroup

class DimstonePressurePlateBlock() : BasePressurePlateBlock(ActivationRule.MOBS,
    BlockProperties.of(Material.STONE, MaterialColor.BLACK).tool(ToolType.PICKAXE, 1).requiresTool().noCollision().strength(0.5F).sounds(
        BlockSoundGroup.STONE
    )
)