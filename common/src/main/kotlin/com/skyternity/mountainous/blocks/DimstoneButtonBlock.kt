package com.skyternity.mountainous.blocks

import com.skyternity.mountainous.init.*
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.sound.BlockSoundGroup

class DimstoneButtonBlock() : BaseStoneButtonBlock(
    BlockProperties.of(Material.SUPPORTED).tool(ToolType.PICKAXE, 1).noCollision().strength(0.5f).sounds(
        BlockSoundGroup.STONE
    )
)