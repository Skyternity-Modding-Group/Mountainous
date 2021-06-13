package com.cyber2000.mountainous.blocks

import com.cyber2000.mountainous.init.BaseBlock
import com.cyber2000.mountainous.init.BaseSlabBlock
import com.cyber2000.mountainous.init.BaseStairsBlock
import com.cyber2000.mountainous.init.BaseWallBlock
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.sound.BlockSoundGroup

class DimstoneWallBlock() : BaseWallBlock(
    BlockProperties.of(Material.STONE, MaterialColor.BLACK).tool(ToolType.PICKAXE, 1).strength(5.0f, 6.0f).sounds(
        BlockSoundGroup.STONE
    ).requiresTool()
)