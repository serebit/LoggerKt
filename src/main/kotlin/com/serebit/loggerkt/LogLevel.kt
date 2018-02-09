package com.serebit.loggerkt

/**
 * The level at which a message is output. Each LogLevel has a different level of severity, ranging from [TRACE] to
 * [ERROR]. In addition, each LogLevel has an assigned [ansiColorTransform] for use with
 * [ConsoleWriter][com.serebit.loggerkt.writers.ConsoleWriter].
 */
enum class LogLevel(internal val ansiColorTransform: (String) -> String) {
    /**
     * A log level for capturing application flow and keeping track of program execution. Not shown by default.
     */
    TRACE(String::ansiGray),
    /**
     * A log level for general debugging events. Not shown by default.
     */
    DEBUG(String::ansiGray),
    /**
     * A log level for informational purposes. Shown by default.
     */
    INFO(String::ansiWhite),
    /**
     * A log level for events that may lead to an error. Shown by default.
     */
    WARNING(String::ansiYellow),
    /**
     * A log level for errors in the application that may be recovered from. Shown by default.
     */
    ERROR(String::ansiRed),
    /**
     * A log level for severe errors that will cause the application to crash or hang. Shown by default.
     */
    FATAL(String::ansiRedBackground);
}