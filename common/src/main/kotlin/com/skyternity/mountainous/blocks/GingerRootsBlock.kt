package com.skyternity.mountainous.blocks

import com.skyternity.mountainous.init.BaseSpreadableBlock
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.Material
import net.minecraft.sound.BlockSoundGroup

class GingerRootsBlock : BaseSpreadableBlock(
    BlockProperties.of(Material.SOIL).tool(ToolType.SHOVEL, 1).strength(5.0f, 6.0f).sounds(
        BlockSoundGroup.GRAVEL
    ).requiresTool()
)