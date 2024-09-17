package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Replace for {@link WebDriverThreadLocalContainer}.<br><br>
 *
 * <em>Repository:</em>
 * <ul>
 *     <li><em>GitHub: <a href="https://github.com/ngoanh2n/blur">ngoanh2n/blur</a></em></li>
 *     <li><em>Maven: <a href="https://mvnrepository.com/artifact/com.github.ngoanh2n/blur">com.github.ngoanh2n:blur</a></em></li>
 * </ul>
 *
 * @author ngoanh2n
 * @since 2020
 */
@ParametersAreNonnullByDefault
public class BlurContainer extends WebDriverThreadLocalContainer {
    private static final Logger log = LoggerFactory.getLogger(BlurContainer.class);
    private final BlurConfig config;
    private final BlurDriver driver;
    private final DriverHolder holder;

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
        this.holder = new DriverHolder(config, driver);
    }

    //-------------------------------------------------------------------------------//

    /**
     * Get the current {@link Config}.
     *
     * @return The implementation of {@link Config com.codeborne.selenide.Config}.
     */
    public BlurConfig getConfig() {
        return config;
    }

    /**
     * Get the current {@link Driver}.
     *
     * @return The implementation of {@link Driver com.codeborne.selenide.Driver}.
     */
    public BlurDriver getDriver() {
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
     * System.setProperty("blur.browser", "chrome");
     * System.setProperty("blur.otherBrowsers", "edge,firefox");
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
            WebDriver webDriver = holder.getWebDriver(index);
            SelenideProxyServer proxyServer = config.proxyEnabled() ? getProxyServer() : null;
            setWebDriver(webDriver, proxyServer);
            log.info("Switched to webdriver -> {}", webDriver);
        });
    }

    //-------------------------------------------------------------------------------//

    void removeWebDriverInstances() {
        holder.removeWebDriverInstances();
    }
}
