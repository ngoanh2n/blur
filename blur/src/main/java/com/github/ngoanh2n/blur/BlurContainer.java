package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Replace for {@link WebDriverThreadLocalContainer} (Cloned from Selenide 6.15.0).
 *
 * @author ngoanh2n
 */
@ParametersAreNonnullByDefault
public class BlurContainer extends WebDriverThreadLocalContainer {
    private static final Logger log = LoggerFactory.getLogger(BlurContainer.class);
    private final BlurConfig config;
    private final BlurDriver driver;
    private final DriverContainer container;

    /**
     * Default constructor.
     *
     * @param config The implementation of {@link Config com.codeborne.selenide.Config}.
     * @param driver The implementation of {@link Driver com.codeborne.selenide.Driver}.
     */
    public BlurContainer(BlurConfig config, BlurDriver driver) {
        super();
        this.config = config;
        this.driver = driver;
        this.container = new DriverContainer(config, driver);
    }

    //-------------------------------------------------------------------------------//

    /**
     * Get the current {@link Config}.
     *
     * @return The implementation of {@link Config com.codeborne.selenide.Config}.
     */
    public BlurConfig config() {
        return config;
    }

    /**
     * Get the current {@link Driver}.
     *
     * @return The implementation of {@link Driver com.codeborne.selenide.Driver}.
     */
    public BlurDriver driver() {
        return driver;
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
    public void switchToWebDriver(int index) {
        SelenideLogger.run("switchToWebDriver", String.valueOf(index), () -> {
            WebDriver webDriver = container.getWebDriver(index);
            setWebDriver(webDriver, getProxyServer());
            log.info("Switched to webdriver -> {}", webDriver);
        });
    }

    //-------------------------------------------------------------------------------//

    void resetDriverContainer() {
        container.resetDrivers(Thread.currentThread().getId());
    }
}
