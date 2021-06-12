package com.cyber2000.mountainous.items

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

@Deprecated("Use {@link Item} instead.")
class ItemBase : Item(Settings().group(ItemGroup.MATERIALS))