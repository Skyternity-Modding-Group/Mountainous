package com.skyternity.mountainous.blocks

import com.skyternity.mountainous.init.BaseStairsBlock
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.sound.BlockSoundGroup

class DimstoneStairsBlock(state: BlockState) : BaseStairsBlock(
    state, BlockProperties.of(Material.STONE, MaterialColor.BLACK).tool(ToolType.PICKAXE, 1).strength(5.0f, 6.0f).sounds(
        BlockSoundGroup.STONE
    ).requiresTool()
)