@file:JvmName("PlatformInterface")

package com.cyber2000.mountainous.items

import dev.architectury.injectables.annotations.ExpectPlatform

@ExpectPlatform
fun printHelloWorld(): Unit = throw AssertionError()