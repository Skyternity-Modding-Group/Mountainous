package com.skyternity.mountainous.items

import com.skyternity.mountainous.forge.ModBlocks
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.AliasedBlockItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item

class GingerRoot : Item(
    Settings()
        .group(net.minecraft.item.ItemGroup.FOOD)
        .food(FoodComponent.Builder()
            .hunger(3)
            .saturationModifier(1.2f)
            .statusEffect(StatusEffectInstance(StatusEffects.SATURATION, 150, 0), 0.9f)
            .build()
        )
)