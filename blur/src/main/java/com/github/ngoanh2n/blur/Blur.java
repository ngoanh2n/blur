package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.WebDriverContainer;
import com.github.ngoanh2n.Resources;
import com.github.ngoanh2n.RuntimeError;
import com.github.ngoanh2n.YamlData;
import com.github.ngoanh2n.csv.CsvComparator;
import com.github.ngoanh2n.img.ImageComparator;
import com.github.ngoanh2n.wdc.WebDriverChecker;
import com.github.ngoanh2n.wds.WebDriverShooter;

/**
 * The starting point of Blur skeleton (<a href="https://github.com/ngoanh2n/blur">blur</a>).<br>
 * <br>
 *
 * <b>WebDriver</b>
 * <ul>
 *     <li>Prerequisites
 *          <ul>
 *              <li>1. Use {@link #open() Blur.open(..)} for opening the page or app.</li>
 *              <li>2. Use {@link #$(String) Blur.$(..)} for searching elements.</li>
 *          </ul>
 *     </li>
 *     <li>Utilities
 *          <ul>
 *              <li>{@link WebDriverShooter}: Take screenshot for page, frame, element (<a href="https://github.com/ngoanh2n/webdrivershooter">webdrivershooter</a>).</li>
 *              <li>{@link WebDriverChecker}: Check  WebDriver characteristics and environment (<a href="https://github.com/ngoanh2n/webdriverchecker">webdriverchecker</a>).</li>
 *          </ul>
 *     </li>
 * </ul>
 *
 * <b>Comparator</b>
 * <ul>
 *     <li>{@link CsvComparator}: Compare 2 CSV files (<a href="https://github.com/ngoanh2n/csv-comparator">csv-comparator</a>).</li>
 *     <li>{@link ImageComparator}: Compare 2 image files (<a href="https://github.com/ngoanh2n/image-comparator">image-comparator</a>).</li>
 * </ul>
 * <p>
 *
 * <b>Commons</b> (<a href="https://github.com/ngoanh2n/commons">commons</a>)
 * <ul>
 *     <li>{@link Resources}: Get Java resource files by resource name.</li>
 *     <li>{@link YamlData}: Read Yaml file to Java Bean, List of Java Bean, Map, List of Map.</li>
 * </ul>
 *
 * @author ngoanh2n
 * @see Selenide
 * @see Selectors
 * @see SelenideElement
 * @see Condition
 * @see CollectionCondition
 * @see WebDriverRunner
 */
public final class Blur extends Selenide {
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
     *     <li>Config: <em>selenide.browser</em>. {@link BlurConfig#browser()}</li>
     *     <li>Config: <em>selenide.otherBrowsers</em>. {@link BlurConfig#otherBrowsers()}</li>
     * </ul>
     * <p>
     * Example:
     * <pre>{@code
     * System.setProperty("selenide.browser", "chrome");
     * System.setProperty("selenide.otherBrowsers", "edge,firefox");
     *
     * Blur.open(URL);                 // Chrome
     *
     * Blur.switchToWebDriver(1);      // Edge
     * Blur.open(URL1);
     *
     * Blur.switchToWebDriver(2);      // Firefox
     * Blur.open(URL2);
     *
     * Blur.switchToWebDriver(0);      // Chrome
     * Blur.switchToWebDriver(1);      // Edge
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
