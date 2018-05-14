@file:JvmName("OutputDecorator")

package com.serebit.loggerkt

private const val ESCAPE = '\u001B'
private const val RESET = "$ESCAPE[0m"
private const val ANSI_RED_BACKGROUND = 41
private const val ANSI_RED_TEXT = 31
private const val ANSI_YELLOW_TEXT = 33
private const val ANSI_GRAY_TEXT = 37
private const val ANSI_WHITE_TEXT = 97

private fun String.color(colorCode: Int): String = "$ESCAPE[${colorCode}m$this$RESET"
internal val String.ansiRedBackground get() = color(ANSI_RED_BACKGROUND)
internal val String.ansiRed get() = color(ANSI_RED_TEXT)
internal val String.ansiYellow get() = color(ANSI_YELLOW_TEXT)
internal val String.ansiGray get() = color(ANSI_GRAY_TEXT)
internal val String.ansiWhite get() = color(ANSI_WHITE_TEXT)
