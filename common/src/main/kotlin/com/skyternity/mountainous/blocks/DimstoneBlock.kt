package com.skyternity.mountainous.blocks

import com.skyternity.mountainous.init.BaseBlock
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.sound.BlockSoundGroup

class DimstoneBlock : BaseBlock(
    BlockProperties.of(Material.STONE, MaterialColor.BLACK).tool(ToolType.PICKAXE, 1).strength(5.0f, 6.0f).sounds(
        BlockSoundGroup.STONE
    ).requiresTool()
)