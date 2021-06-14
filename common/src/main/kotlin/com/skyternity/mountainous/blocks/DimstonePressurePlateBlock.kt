package com.skyternity.mountainous.blocks

import com.skyternity.mountainous.init.BasePressurePlateBlock
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.sound.BlockSoundGroup

class DimstonePressurePlateBlock() : BasePressurePlateBlock(ActivationRule.MOBS,
    BlockProperties.of(Material.STONE, MaterialColor.BLACK).tool(ToolType.PICKAXE, 1).requiresTool().noCollision().strength(0.5F).sounds(
        BlockSoundGroup.STONE
    )
)