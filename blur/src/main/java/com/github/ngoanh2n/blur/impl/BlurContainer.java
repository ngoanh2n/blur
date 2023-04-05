package com.github.ngoanh2n.blur.impl;

import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.ngoanh2n.blur.BlurConfig;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Replace for {@link com.codeborne.selenide.impl.WebDriverThreadLocalContainer}
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class BlurContainer extends WebDriverThreadLocalContainer {
    private static final Logger log = LoggerFactory.getLogger(BlurContainer.class);
    private final BlurConfig config;
    private final DriverContainer container;

    public BlurContainer(BlurConfig config, BlurDriver driver) {
        super();
        this.config = config;
        this.container = new DriverContainer(config, driver);
    }

    //-------------------------------------------------------------------------------//

    public BlurConfig config() {
        return config;
    }

    public void switchToDriver(int index) {
        SelenideLogger.run("switchToDriver", String.valueOf(index), () -> {
            WebDriver driver = container.getDriver(index);
            this.setWebDriver(driver, getProxyServer());
            log.info("Switch to index={}, driver={}", index, driver);
        });
    }
}
