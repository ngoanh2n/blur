package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.WebDriverContainer;
import com.github.ngoanh2n.*;
import com.github.ngoanh2n.csv.CsvComparator;
import com.github.ngoanh2n.img.ImageComparator;
import com.github.ngoanh2n.wdc.WebDriverChecker;
import com.github.ngoanh2n.wds.WebDriverShooter;
import org.openqa.selenium.By;

/**
 * The starting point of Blur skeleton.<br><br>
 *
 * <b>WebDriver</b><br>
 * You have to use {@link Selenide#open() Blur.open(..)} for opening the page or app first.
 * <ul>
 *     <li>{@link Selectors}<br>
 *          Define a locator by a locating strategy.
 *         <pre>{@code
 *             By by = Selectors.byXpath(..);
 *             By by = Selectors.withText(..);
 *             ...
 *         }</pre>
 *     </li>
 *     <li>{@link Selenide#$(By) Blur.$(..)}<br>
 *          Find element/elements by a locator.
 *          <pre>{@code
 *              SelenideElement element = Blur.$(..);
 *              ElementsCollection elements = Blur.$$(..);
 *              ...
 *          }</pre>
 *     </li>
 *     <li>{@link Condition}<br>
 *          Define a condition to check element.
 *          <pre>{@code
 *              Condition condition = Condition.enabled;
 *              Condition condition = Condition.exactText(..);
 *              ...
 *          }</pre>
 *     </li>
 *     <li>{@link SelenideElement}<br>
 *          Interact with element.
 *          <pre>{@code
 *              SelenideElement element = element.dragAndDrop(..);
 *              SelenideElement element = element.shouldBe(condition);
 *              ...
 *          }</pre>
 *     </li>
 *     <li>{@link CollectionCondition}<br>
 *          Define a collection condition to check elements.
 *          <pre>{@code
 *              CollectionCondition condition = CollectionCondition.size(..);
 *              CollectionCondition condition = CollectionCondition.texts(..);
 *              ...
 *          }</pre>
 *     </li>
 *     <li>{@link ElementsCollection}<br>
 *          Interact with list of elements.
 *          <pre>{@code
 *              ElementsCollection elements = elements.filter(condition);
 *              ElementsCollection elements = elements.shouldBe(condition);
 *              ...
 *          }</pre>
 *     </li>
 *     <li>{@link WebDriverChecker}<br>
 *          Check WebDriver characteristics and environment.
 *          <pre>{@code
 *              boolean result = WebDriverChecker.isIOS(..);
 *              boolean result = WebDriverChecker.isSafari(..);
 *              ...
 *          }</pre>
 *     </li>
 *     <li>{@link WebDriverShooter}<br>
 *          Take screenshot with WebDriver and comparison.
 *          <pre>{@code
 *              Screenshot screenshot = WebDriverShooter.page(..);
 *              Screenshot screenshot = WebDriverShooter.frame(..);
 *              Screenshot screenshot = WebDriverShooter.element(..);
 *          }</pre>
 *     </li>
 * </ul>
 *
 * <b>Comparator</b>
 * <ul>
 *     <li>{@link CsvComparator}<br>
 *          Compare 2 CSV files.
 *          <pre>{@code
 *              CsvComparisonResult result = CsvComparator.compare(expectedCSV, actualCSV);
 *              CsvComparisonResult result = CsvComparator.compare(expectedCSV, actualCSV, options);
 *          }</pre>
 *     </li>
 *     <li>{@link ImageComparator}<br>
 *          Compare 2 image files.
 *          <pre>{@code
 *              ImageComparisonResult result = ImageComparator.compare(expectedImage, actualImage);
 *              ImageComparisonResult result = ImageComparator.compare(expectedImage, actualImage, options);
 *          }</pre>
 *     </li>
 * </ul>
 *
 * <b>Commons</b>
 * <ul>
 *     <li>{@link Resources}<br>
 *          Find and read Java resources.
 *          <pre>{@code
 *              File file = Resources.getFile("file.json");
 *              String content = Resources.getContent("file.yml");
 *              InputStream is = Resources.getInputStream("file.png");
 *          }</pre>
 *     </li>
 *     <li>{@link YamlData}<br>
 *          Read Yaml file to {@code Map}, {@code List of Maps}, {@code Model}, {@code List of Models}.
 *          <pre>{@code
 *              public class User extends YamlData {}
 *              ---
 *              User user = new User().fromResource("user.yml").toModel();
 *              List<User> users = new User().fromResource("users.yml").toModels();
 *              ...
 *          }</pre>
 *     </li>
 *     <li>{@link Property}<br>
 *          Represent a JVM system property.
 *          <pre>{@code
 *              Property property = Property.ofString(..);
 *              Property property = Property.ofBoolean(..);
 *              ...
 *          }</pre>
 *     </li>
 *     <li>{@link PropertiesFile}<br>
 *          Read properties file.
 *          <pre>{@code
 *              PropertiesFile propertiesFile = new PropertiesFile("file.properties");
 *              String value = propertiesFile.getProperty("keyName");
 *          }</pre>
 *     </li>
 *     <li>{@link AllureEnvironment}<br>
 *          Write {@code environment.properties} to Allure results directory.
 *          <pre>{@code
 *              AllureEnvironment.write(properties);
 *              AllureEnvironment.write("file1.properties", "file2.properties");
 *          }</pre>
 *     </li>
 *     <li>{@link EnabledIfProperty @EnabledIfProperty}<br>
 *          Signal that the annotated test class or test method is enabled.
 *          <pre>{@code
 *              public class MyTest {
 *                  &#064;Test
 *                  &#064;EnabledIfProperty(name = "browser", value = "safari")
 *                  public void test () {}
 *              }
 *          }</pre>
 *     </li>
 *     <li>{@link SetProperty @SetProperty}<br>
 *          Set JVM system property for the annotated test class or test method.
 *          <pre>{@code
 *              &#064;SetProperty(name = "browser", value = "safari")
 *              public class MyTest {}
 *              ---
 *              public class MyTest {
 *                  &#064;Test
 *                  &#064;SetProperty(name = "browser", value = "safari")
 *                  public void test () {}
 *              }
 *          }</pre>
 *     </li>
 * </ul>
 *
 * <em>Repository:</em>
 * <ul>
 *     <li><em>GitHub: <a href="https://github.com/ngoanh2n/blur">ngoanh2n/blur</a></em></li>
 *     <li><em>Maven: <a href="https://mvnrepository.com/artifact/com.github.ngoanh2n/blur">com.github.ngoanh2n:blur</a></em></li>
 * </ul>
 *
 * @author ngoanh2n
 * @see Selenide
 * @see WebDriverRunner
 * @since 2020
 */
public final class Blur extends Selenide {
    /**
     * Default constructor.
     */
    private Blur() { /**/ }

    /**
     * Get the current {@link Config}.
     *
     * @return The implementation of {@link Config com.codeborne.selenide.Config}.
     */
    public static BlurConfig getConfig() {
        return getContainer().config();
    }

    /**
     * Get the current {@link Driver}.
     *
     * @return The implementation of {@link Driver com.codeborne.selenide.Driver}.
     */
    public static BlurDriver getDriver() {
        return getContainer().driver();
    }

    /**
     * Switch to a WebDriver among the list of browsers.<br>
     * Browser list is combined via:
     * <ul>
     *     <li>
     *         Config <em>selenide.browser</em>
     *         <ul>
     *             <li>Getter: {@link BlurConfig#browser() BlurConfig#browser()}</li>
     *             <li>Setter: {@link BlurConfig#browser(String) BlurConfig#browser(String)}</li>
     *         </ul>
     *     </li>
     *     <li>
     *         Config <em>selenide.otherBrowsers</em>
     *         <ul>
     *             <li>Getter: {@link BlurConfig#otherBrowsers() BlurConfig#otherBrowsers()}</li>
     *             <li>Setter: {@link BlurConfig#otherBrowsers(String) BlurConfig#otherBrowsers(String)}</li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * <b>Example:</b>
     * <pre>{@code
     *      System.setProperty("selenide.browser", "chrome");
     *      System.setProperty("selenide.otherBrowsers", "edge,firefox");
     *
     *      Blur.open(URL);                 // Chrome
     *
     *      Blur.switchToWebDriver(1);      // Edge
     *      Blur.open(URL1);
     *
     *      Blur.switchToWebDriver(2);      // Firefox
     *      Blur.open(URL2);
     *
     *      Blur.switchToWebDriver(0);      // Chrome
     *      Blur.switchToWebDriver(1);      // Edge
     * }</pre>
     *
     * @param index The index in browser list (0-based indexing).
     */
    public static void switchToWebDriver(int index) {
        getContainer().switchToWebDriver(index);
    }

    /**
     * Get the current {@link WebDriverContainer}.
     *
     * @return The implementation of {@link WebDriverContainer com.codeborne.selenide.WebDriverContainer}.
     */
    public static BlurContainer getContainer() {
        if (WebDriverRunner.webdriverContainer instanceof BlurContainer) {
            return (BlurContainer) WebDriverRunner.webdriverContainer;
        }
        throw new RuntimeError("You have to call Blur.open(..) first");
    }
}
