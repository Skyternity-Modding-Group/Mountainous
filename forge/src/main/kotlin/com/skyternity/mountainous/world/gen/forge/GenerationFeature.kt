package com.skyternity.mountainous.world.gen.forge

import net.minecraftforge.event.world.BiomeLoadingEvent
import java.util.function.Consumer
import java.util.function.Predicate

data class GenerationFeature(val predicate: Predicate<BiomeLoadingEvent>, val consumer: Consumer<BiomeLoadingEvent>) {

}
