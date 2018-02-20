# LoggerKt
[![Build Status](https://travis-ci.org/serebit/LoggerKt.svg?branch=master)](https://travis-ci.org/serebit/LoggerKt) [![Download](https://api.bintray.com/packages/serebit/Maven/loggerkt/images/download.svg)](https://bintray.com/serebit/Maven/loggerkt)

LoggerKt is a simple, lightweight Kotlin logger with no dependencies and an idiomatic runtime configurator. 

## System Requirements
LoggerKt is compatible with **Java 6** or newer, and requires **JDK 8** or newer to compile, as per Kotlin's default standards.

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
```kotlin
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
