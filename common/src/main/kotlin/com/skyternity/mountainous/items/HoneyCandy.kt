package com.skyternity.mountainous.items

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.FoodComponent

class HoneyCandy : net.minecraft.item.Item(
    Settings()
        .group(net.minecraft.item.ItemGroup.FOOD)
        .food(
            FoodComponent.Builder()
                .hunger(5)
                .saturationModifier(1.2f)
                .statusEffect(StatusEffectInstance(StatusEffects.REGENERATION, 450, 0), 0.9f)
                .build()
        )
)