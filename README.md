# LoggerKt
[![Download](https://api.bintray.com/packages/serebit/Maven/loggerkt/images/download.svg)](https://bintray.com/serebit/Maven/loggerkt)
[![Build Status](https://img.shields.io/travis/serebit/LoggerKt.svg)](https://travis-ci.org/serebit/LoggerKt)
[![Codacy Grade](https://img.shields.io/codacy/grade/4d9ef218ebde4807bb58d6aba7a61772.svg)](https://app.codacy.com/app/serebit/LoggerKt)
[![License](https://img.shields.io/github/license/serebit/loggerkt.svg)](https://github.com/serebit/loggerkt/tree/master/LICENSE.md)

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
