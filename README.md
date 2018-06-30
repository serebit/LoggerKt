# LoggerKt
[![Download][bintray]](https://bintray.com/serebit/Maven/loggerkt)
[![Pipeline Status][gitlab-ci]](https://travis-ci.org/serebit/LoggerKt)
[![License][license]](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Donate][paypal]](https://paypal.me/gdeadshot)

LoggerKt is a simple, lightweight Kotlin logger with no dependencies and an idiomatic runtime configurator.

## Usage
Logging is made as simple as possible. To write a log message, just invoke one of the methods on the `Logger` class. 
Each method corresponds to a different `LogLevel`, from `TRACE` to `FATAL`.
```kotlin
Logger.trace("Received a message from the server.")
Logger.debug("Sent a message to the server.")
Logger.info("Disconnected from the server.")
Logger.warning("Failed to resolve the server timeout.")
Logger.error("The response from the server was incomplete.")
Logger.fatal("Server connection halted unexpectedly.")
```

## Get Started
### Gradle (Groovy build script)
```gradle
repositories {
    jcenter()
}

dependencies {
    compile group: "com.serebit", name: "loggerkt", version: "0.2.0"
}
```
### Gradle (Kotlin build script)
```kts
repositories {
    jcenter()
}

dependencies {
    compile(group = "com.serebit", name = "loggerkt", version = "0.2.0")
}
```
### Maven
```xml
<repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>http://jcenter.bintray.com</url>
</repository>
```
```xml
<dependency>
    <groupId>com.serebit</groupId>
    <artifactId>loggerkt</artifactId>
    <version>0.2.0</version>
</dependency>
```

[bintray]: https://api.bintray.com/packages/serebit/Maven/loggerkt/images/download.svg "Download from Bintray"
[gitlab-ci]: https://gitlab.com/serebit/loggerkt/badges/master/build.svg "Pipeline Status"
[license]: https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg "License"
[paypal]: https://img.shields.io/badge/Donate-PayPal-blue.svg "Donate via PayPal"
