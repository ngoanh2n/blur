package com.github.ngoanh2n.blur.impl;

import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.ngoanh2n.blur.BlurConfig;
import com.github.ngoanh2n.blur.BlurDriver;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Replace for {@link WebDriverThreadLocalContainer}. Cloned from the latest Selenide: 6.12.4.
 *
 * @author ngoanh2n
 */
@ParametersAreNonnullByDefault
public class BlurContainer extends WebDriverThreadLocalContainer {
    private static final Logger log = LoggerFactory.getLogger(BlurContainer.class);
    private final BlurConfig config;
    private final BlurDriver driver;
    private final DriverContainer container;

    public BlurContainer(BlurConfig config, BlurDriver driver) {
        super();
        this.config = config;
        this.driver = driver;
        this.container = new DriverContainer(config, driver);
    }

    //-------------------------------------------------------------------------------//

    public BlurConfig config() {
        return config;
    }

    public BlurDriver driver() {
        return driver;
    }

    public void switchToWebDriver(int index) {
        SelenideLogger.run("switchToWebDriver", String.valueOf(index), () -> {
            WebDriver webDriver = container.getWebDriver(index);
            setWebDriver(webDriver, getProxyServer());
            log.info("Switched to webdriver -> {}", webDriver);
        });
    }

    void resetDriverContainer() {
        container.resetDrivers(Thread.currentThread().getId());
    }
}
