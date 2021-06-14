package com.skyternity.mountainous.items

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.FoodComponent

class GingerChew : net.minecraft.item.Item(
    Settings()
        .group(net.minecraft.item.ItemGroup.FOOD)
        .food(
            FoodComponent.Builder()
                .hunger(5)
                .saturationModifier(1.2f)
                .statusEffect(StatusEffectInstance(StatusEffects.SATURATION, 1100, 0), 0.9f)
                .build()
        )
)