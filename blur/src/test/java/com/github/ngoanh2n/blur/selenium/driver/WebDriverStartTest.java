package com.github.ngoanh2n.blur.selenium.driver;

import com.codeborne.selenide.WebDriverRunner;
import com.github.ngoanh2n.blur.Blur;
import com.github.ngoanh2n.blur.BlurConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author ngoanh2n
 */
public class WebDriverStartTest {
    @Test
    void openWebDriver() {
        Blur.open();
        BlurConfig config = Blur.getConfig();
        Assertions.assertNotNull(config);
    }

    @AfterEach
    void closeWebDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
