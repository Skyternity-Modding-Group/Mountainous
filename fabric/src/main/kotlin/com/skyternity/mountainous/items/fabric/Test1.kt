package com.skyternity.mountainous.items.fabric

import java.util.function.BiConsumer

object Test1 {
    val consumer: BiConsumer<String, Int>
        get() = BiConsumer { s: String, i: Int -> println(s.length == i) }
}