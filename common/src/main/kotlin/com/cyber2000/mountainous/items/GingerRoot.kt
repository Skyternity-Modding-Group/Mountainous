package com.cyber2000.mountainous.items

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.FoodComponent

class GingerRoot : net.minecraft.item.Item(
    Settings()
        .group(net.minecraft.item.ItemGroup.FOOD)
        .food(
            FoodComponent.Builder()
                .hunger(3)
                .saturationModifier(1.2f)
                .statusEffect(StatusEffectInstance(StatusEffects.SATURATION, 150, 0), 0.9f)
                .build()
        )
)