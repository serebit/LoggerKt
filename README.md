# Logkat
[![Download][bintray]](https://bintray.com/serebit/public/logkat)
[![Pipeline Status][gitlab-ci]](https://gitlab.com/serebit/logkat/pipelines)
[![License][license]](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Donate][paypal]](https://paypal.me/gdeadshot)

Logkat is a simple, lightweight Kotlin logger with an idiomatic runtime configurator. This is only meant to be used for my own projects, and as such, I make *no* guarantees for backwards compatibility.

## Usage
To write a log message, just invoke one of the methods on the `Logger` class. Each method corresponds to a different
`LogLevel`, from `TRACE` to `FATAL`.
```kotlin
val logger = Logger()
logger.trace("Received a message from the server.")
logger.debug("Sent a message to the server.")
logger.info("Disconnected from the server.")
logger.warning("Failed to resolve the server timeout.")
logger.error("The response from the server was incomplete.")
logger.fatal("Server connection halted unexpectedly.")
```

## Get Started
### Gradle (Groovy build script)
```gradle
repositories {
    jcenter()
}

dependencies {
    compile group: "com.serebit", name: "logkat-jvm", version: "0.4.6"
}
```
### Gradle (Kotlin build script)
```kts
repositories {
    jcenter()
}

dependencies {
    compile(group = "com.serebit", name = "logkat-jvm", version = "0.4.6")
}
```
### Maven
```xml
<repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com</url>
</repository>
```
```xml
<dependency>
    <groupId>com.serebit</groupId>
    <artifactId>logkat</artifactId>
    <version>0.4.5</version>
</dependency>
```

[bintray]: https://api.bintray.com/packages/serebit/public/logkat/images/download.svg "Download from Bintray"
[gitlab-ci]: https://gitlab.com/serebit/logkat/badges/master/build.svg "Pipeline Status"
[license]: https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg "License"
[paypal]: https://img.shields.io/badge/Donate-PayPal-blue.svg "Donate via PayPal"
