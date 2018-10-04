# Logkat
[![Download][bintray]](https://bintray.com/serebit/public/logkat)
[![Pipeline Status][gitlab-ci]](https://gitlab.com/serebit/logkat/pipelines)
[![License][license]](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Donate][paypal]](https://paypal.me/gdeadshot)

Logkat is a simple, lightweight Kotlin logger with no dependencies and an idiomatic runtime configurator.

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
The Logger class can also be instantiated, which is the recommended method of logging for libraries. To instantiate 
the logger, just use the default constructor.
```kotlin
val logger = Logger()
logger.error("Test class was not loaded correctly.")
```

## Get Started
### Gradle (Groovy build script)
```gradle
repositories {
    jcenter()
    maven { url "https://dl.bintray.com/serebit/public" }
}

dependencies {
    compile group: "com.serebit", name: "logkat", version: "0.4.1"
}
```
### Gradle (Kotlin build script)
```kts
repositories {
    jcenter()
    maven("https://dl.bintray.com/serebit/public")
}

dependencies {
    compile(group = "com.serebit", name = "logkat", version = "0.4.1")
}
```
### Maven
```xml
<repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com</url>
</repository>
<repository>
    <id>serebit-public</id>
    <url>https://bintray.com/serebit/public</url>
</repository>
```
```xml
<dependency>
    <groupId>com.serebit</groupId>
    <artifactId>logkat</artifactId>
    <version>0.4.1</version>
</dependency>
```

[bintray]: https://api.bintray.com/packages/serebit/public/logkat/images/download.svg "Download from Bintray"
[gitlab-ci]: https://gitlab.com/serebit/logkat/badges/master/build.svg "Pipeline Status"
[license]: https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg "License"
[paypal]: https://img.shields.io/badge/Donate-PayPal-blue.svg "Donate via PayPal"
