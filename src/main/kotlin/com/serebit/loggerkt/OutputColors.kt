@file:JvmName("OutputDecorator")

package com.serebit.loggerkt

private const val ESCAPE = '\u001B'
private const val RESET = "$ESCAPE[0m"

private fun String.color(colorCode: Int): String = "$ESCAPE[${colorCode}m$this$RESET"
internal val String.ansiRed get() = color(31)
internal val String.ansiYellow get() = color(33)
internal val String.ansiGray get() = color(37)
internal val String.ansiWhite get() = color(97)