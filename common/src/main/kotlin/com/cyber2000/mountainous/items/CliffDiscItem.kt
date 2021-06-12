package com.cyber2000.mountainous.items

import com.cyber2000.mountainous.init.ModSounds
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Formatting
import net.minecraft.util.Rarity
import net.minecraft.world.World

class CliffDiscItem :
    CustomMusicDiscItem(
        4,
        { ModSounds.CLIFF_DISC.get() },
        Settings().group(ItemGroup.MISC).rarity(Rarity.RARE).maxCount(1)
    ) {

    override fun appendTooltip(
        itemStack: ItemStack?,
        world: World?,
        list: MutableList<Text>?,
        tooltipContext: TooltipContext?
    ) {
        list?.add(this.description.formatted(Formatting.GRAY))
    }

    override fun getDescription(): MutableText {
        return TranslatableText("$translationKey.desc")
    }
}