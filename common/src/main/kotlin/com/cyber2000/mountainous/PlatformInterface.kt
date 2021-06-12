@file:JvmName("PlatformInterface")

package com.cyber2000.mountainous

import dev.architectury.injectables.annotations.ExpectPlatform

@ExpectPlatform
fun printHelloWorld(): Unit = throw AssertionError()