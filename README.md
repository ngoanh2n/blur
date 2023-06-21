[![GitHub forks](https://img.shields.io/github/forks/ngoanh2n/blur.svg?style=social&label=Fork&maxAge=2592000)](https://github.com/ngoanh2n/blur/network/members/)
[![GitHub stars](https://img.shields.io/github/stars/ngoanh2n/blur.svg?style=social&label=Star&maxAge=2592000)](https://github.com/ngoanh2n/blur/stargazers/)
[![GitHub watchers](https://img.shields.io/github/watchers/ngoanh2n/blur.svg?style=social&label=Watch&maxAge=2592000)](https://github.com/ngoanh2n/blur/watchers/)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.ngoanh2n/blur/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.ngoanh2n/blur)
[![javadoc](https://javadoc.io/badge2/com.github.ngoanh2n/blur/javadoc.svg)](https://javadoc.io/doc/com.github.ngoanh2n/blur)
[![GitHub release](https://img.shields.io/github/release/ngoanh2n/blur.svg)](https://github.com/ngoanh2n/blur/releases/)
[![badge-jdk](https://img.shields.io/badge/jdk-11-blue.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![License: MIT](https://img.shields.io/badge/License-MIT-blueviolet.svg)](https://opensource.org/licenses/MIT)

**Table of Contents**
<!-- TOC -->
* [Blur](#blur)
  * [Lib](#lib)
  * [Module](#module)
* [Declaration](#declaration)
  * [Gradle](#gradle)
  * [Maven](#maven)
* [Usage](#usage)
<!-- TOC -->

# Blur
## Lib
| Name       | Version  | Repository                                                                         |
|:-----------|:---------|:-----------------------------------------------------------------------------------|
| `Selenium` | `4.10.0` | [SeleniumHQ/selenium/java](https://github.com/SeleniumHQ/selenium/tree/trunk/java) |
| `Appium`   | `8.3.0`  | [appium/java-client](https://github.com/appium/java-client)                        |
| `Selenide` | `6.15.0` | [selenide/selenide](https://github.com/selenide/selenide)                          |
| `JUnit5`   | `5.9.1`  | [junit-team/junit5](https://github.com/junit-team/junit5)                          |
| `Allure`   | `2.21.0` | [allure-framework/allure-java](https://github.com/allure-framework/allure-java)    |

## Module
The modules are built in separated artifacts.

| Name               | Version | Repository                                                                |
|:-------------------|---------|:--------------------------------------------------------------------------|
| `Commons`          | `1.1.3` | [ngoanh2n/commons](https://github.com/ngoanh2n/commons)                   |
| `CSV Comparator`   | `1.5.2` | [ngoanh2n/csv-comparator](https://github.com/ngoanh2n/csv-comparator)     |
| `Image Comparator` | `1.0.1` | [ngoanh2n/image-comparator](https://github.com/ngoanh2n/image-comparator) |
| `WebDriverChecker` | `2.6.0` | [ngoanh2n/webdriverchecker](https://github.com/ngoanh2n/webdriverchecker) |
| `WebDriverShooter` | `1.0.1` | [ngoanh2n/webdrivershooter](https://github.com/ngoanh2n/webdrivershooter) |

# Declaration
## Gradle
Add to `build.gradle`.
```gradle
implementation("com.github.ngoanh2n:blur:0.0.0")
```

## Maven
Add to `pom.xml`.
```xml
<dependency>
    <groupId>com.github.ngoanh2n</groupId>
    <artifactId>blur</artifactId>
    <version>0.0.0</version>
</dependency>
```

# Usage
