package com.github.ngoanh2n.blur.driver;

import com.codeborne.selenide.WebDriverRunner;
import com.github.ngoanh2n.RuntimeError;
import com.github.ngoanh2n.blur.Blur;
import com.github.ngoanh2n.wdc.WebDriverChecker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class DriverSwitchTest {
    @BeforeAll
    static void setup() {
        System.setProperty("selenide.browser", "edge");
        System.setProperty("selenide.otherBrowsers", "chrome");
    }

    @Test
    void switchToWebDriver() {
        Blur.open();
        Assertions.assertTrue(WebDriverChecker.isEdge());

        Blur.switchToWebDriver(1);
        Blur.open();
        Assertions.assertTrue(WebDriverChecker.isChrome());

        Blur.switchToWebDriver(0);
        Assertions.assertTrue(WebDriverChecker.isEdge());

        Assertions.assertThrows(RuntimeError.class, () -> Blur.switchToWebDriver(2));
    }

    @AfterEach
    void closeWD() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
