package com.cyber2000.mountainous.blocks

import com.cyber2000.mountainous.init.BaseBlock
import com.cyber2000.mountainous.init.BaseSlabBlock
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.ToolType
import net.minecraft.block.Material
import net.minecraft.sound.BlockSoundGroup

class DimstoneSlabBlock : BaseSlabBlock(
    BlockProperties.of(Material.SOIL).tool(ToolType.PICKAXE, 1).strength(5.0f, 6.0f).sounds(
        BlockSoundGroup.STONE
    ).requiresTool()
)