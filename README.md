[![GitHub forks](https://img.shields.io/github/forks/ngoanh2n/blur.svg?style=social&label=Fork&maxAge=2592000)](https://github.com/ngoanh2n/blur/network/members/)
[![GitHub stars](https://img.shields.io/github/stars/ngoanh2n/blur.svg?style=social&label=Star&maxAge=2592000)](https://github.com/ngoanh2n/blur/stargazers/)
[![GitHub watchers](https://img.shields.io/github/watchers/ngoanh2n/blur.svg?style=social&label=Watch&maxAge=2592000)](https://github.com/ngoanh2n/blur/watchers/)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.ngoanh2n/blur/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.ngoanh2n/blur)
[![javadoc](https://javadoc.io/badge2/com.github.ngoanh2n/blur/javadoc.svg)](https://javadoc.io/doc/com.github.ngoanh2n/blur)
[![GitHub release](https://img.shields.io/github/release/ngoanh2n/blur.svg)](https://github.com/ngoanh2n/blur/releases/)
[![badge-jdk](https://img.shields.io/badge/jdk-17-blue.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![License: MIT](https://img.shields.io/badge/License-MIT-blueviolet.svg)](https://opensource.org/licenses/MIT)

**Table of Contents**
<!-- TOC -->
* [Blur](#blur)
  * [Lib](#lib)
  * [Module](#module)
* [Declaration](#declaration)
  * [Gradle](#gradle)
  * [Maven](#maven)
  * [Skeleton](#skeleton)
* [Usage](#usage)
  * [WebDriver](#webdriver)
  * [Comparator](#comparator)
  * [Commons](#commons)
<!-- TOC -->

# Blur
## Lib
The main libs are used for `blur`.

| Name       | Version  | Repository                                                                         |
|:-----------|:---------|:-----------------------------------------------------------------------------------|
| `Selenium` | `4.24.0` | [SeleniumHQ/selenium/java](https://github.com/SeleniumHQ/selenium/tree/trunk/java) |
| `Appium`   | `9.3.0`  | [appium/java-client](https://github.com/appium/java-client)                        |
| `Selenide` | `7.4.3`  | [selenide/selenide](https://github.com/selenide/selenide)                          |
| `Allure`   | `2.29.0` | [allure-framework/allure-java](https://github.com/allure-framework/allure-java)    |
| `JUnit5`   | `5.11.0` | [junit-team/junit5](https://github.com/junit-team/junit5)                          |

## Module
The modules are built in separated artifacts.

| Name               | Version  | Repository                                                                |
|:-------------------|----------|:--------------------------------------------------------------------------|
| `Commons`          | `1.6.0`  | [ngoanh2n/commons](https://github.com/ngoanh2n/commons)                   |
| `CSV Comparator`   | `1.10.0` | [ngoanh2n/csv-comparator](https://github.com/ngoanh2n/csv-comparator)     |
| `Image Comparator` | `1.5.0`  | [ngoanh2n/image-comparator](https://github.com/ngoanh2n/image-comparator) |
| `WebDriverChecker` | `2.8.0`  | [ngoanh2n/webdriverchecker](https://github.com/ngoanh2n/webdriverchecker) |
| `WebDriverShooter` | `1.1.1`  | [ngoanh2n/webdrivershooter](https://github.com/ngoanh2n/webdrivershooter) |

# Declaration
- Case 1: Declare `blur` as a dependency in [build.gradle](#gradle) or [pom.xml](#maven)
- Case 2: Create a new module inside [Skeleton](#skeleton) and compile `blur` in `build.gradle` of module

## Gradle
Add to `build.gradle`.
```gradle
implementation("com.github.ngoanh2n:blur:0.2.0")
```

## Maven
Add to `pom.xml`.
```xml
<dependency>
    <groupId>com.github.ngoanh2n</groupId>
    <artifactId>blur</artifactId>
    <version>0.2.0</version>
</dependency>
```

## Skeleton
Refer to [Open project and create module](docs/1-open-project-and-create-module.md).

# Usage
## WebDriver
You have to use `Blur.open(..)` for opening the page or app first.

- `com.codeborne.selenide.Selectors`<br>
  Define a locator by a locating strategy.
  ```java
  By by = Selectors.byXpath(..);
  By by = Selectors.withText(..);
  ...
  ```

- `com.github.ngoanh2n.blur.Blur.$(..)`<br>
Find element/elements by a locator.
  ```java
  SelenideElement element = Blur.$(..);
  ElementsCollection elements = Blur.$$(..);
  ...
  ```

- `com.codeborne.selenide.Condition`<br>
  Define a condition to check element.
  ```java
  Condition condition = Condition.enabled;
  Condition condition = Condition.exactText(..);
  ...
  ```

- `com.codeborne.selenide.SelenideElement`<br>
  Interact with element.
  ```java
  SelenideElement element = element.dragAndDrop(..);
  SelenideElement element = element.shouldBe(condition);
  ...
  ```

- `com.codeborne.selenide.CollectionCondition`<br>
  Define a collection condition to check elements.
  ```java
  CollectionCondition condition = CollectionCondition.size(..);
  CollectionCondition condition = CollectionCondition.texts(..);
  ...
  ```

- `com.codeborne.selenide.ElementsCollection`<br>
  Interact with list of elements.
  ```java
  ElementsCollection elements = elements.filter(condition);
  ElementsCollection elements = elements.shouldBe(condition);
  ...
  ```

- `com.github.ngoanh2n.wdc.WebDriverChecker`<br>
  Check WebDriver characteristics and environment.
  ```java
  boolean result = WebDriverChecker.isIOS(..);
  boolean result = WebDriverChecker.isSafari(..);
  ...
  ```

- `com.github.ngoanh2n.wds.WebDriverShooter`<br>
  Take screenshot with WebDriver and comparison.
  ```java
  Screenshot screenshot = WebDriverShooter.page(..);
  Screenshot screenshot = WebDriverShooter.frame(..);
  Screenshot screenshot = WebDriverShooter.element(..);
  ```

## Comparator
- `com.github.ngoanh2n.csv.CsvComparator`<br>
  Compare 2 CSV files.
  ```java
  CsvComparisonResult result = CsvComparator.compare(expectedCSV, actualCSV);
  CsvComparisonResult result = CsvComparator.compare(expectedCSV, actualCSV, options);
  ```

- `com.github.ngoanh2n.img.ImageComparator`<br>
  Compare 2 image files.
  ```java
  ImageComparisonResult result = ImageComparator.compare(expectedImage, actualImage);
  ImageComparisonResult result = ImageComparator.compare(expectedImage, actualImage, options);
  ```

## Commons
- `com.github.ngoanh2n.Resources`<br>
  Find and read Java resources.
  ```java
  File file = Resources.getFile("file.json");
  String content = Resources.getContent("file.yml");
  InputStream is = Resources.getInputStream("file.png");
  ```

- `com.github.ngoanh2n.YamlData`<br>
  Read Yaml file to `Map`, `List of Maps`, `Model`, `List of Models`.
  ```java
  public class User extends YamlData {}
  ---
  User user = new User().fromResource("user.yml").toModel();
  List<User> users = new User().fromResource("users.yml").toModels();
  ...
  ```

- `com.github.ngoanh2n.Property`<br>
  Represent a JVM system property.
  ```java
  Property property = Property.ofString(..);
  Property property = Property.ofBoolean(..);
  ...
  ```

- `com.github.ngoanh2n.PropertiesFile`<br>
  Read properties file.
  ```java
  PropertiesFile propertiesFile = new PropertiesFile("file.properties");
  String value = propertiesFile.getProperty("keyName");
  ```

- `com.github.ngoanh2n.AllureEnvironment`<br>
  Write `environment.properties` to Allure results directory.
  ```java
  AllureEnvironment.write(properties);
  AllureEnvironment.write("file1.properties", "file2.properties");
  ```

- `com.github.ngoanh2n.EnabledIfProperty`<br>
  Signal that the annotated test class or test method is enabled.
  ```java
  public class MyTest {
      @Test
      @EnabledIfProperty(name = "browser", value = "safari")
      public void test () {}
  }
  ```

- `com.github.ngoanh2n.SetProperty`<br>
  Set JVM system property for the annotated test class or test method.
  ```java
  @SetProperty(name = "browser", value = "safari")
  public class MyTest {}
  ---
  public class MyTest {
      @Test
      @SetProperty(name = "browser", value = "safari")
      public void test () {}
  }
  ```
