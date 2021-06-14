@file:JvmName("PlatformInterface")

package com.skyternity.mountainous

import dev.architectury.injectables.annotations.ExpectPlatform

@ExpectPlatform
fun printHelloWorld(): Unit = throw AssertionError()