package com.serebit.loggerkt

enum class Level(internal val ansiColorTransform: (String) -> String) {
    TRACE(String::ansiGray),
    DEBUG(String::ansiGray),
    INFO(String::ansiWhite),
    WARNING(String::ansiYellow),
    ERROR(String::ansiRed);
}