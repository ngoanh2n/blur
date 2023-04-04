package com.github.ngoanh2n.blur.driver;

import com.codeborne.selenide.WebDriverRunner;
import com.github.ngoanh2n.blur.Blur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class DriverStartTest {
    @Test
    void open() {
        Blur.open();
        Assertions.assertNotNull(Blur.getConfig());
    }

    @AfterEach
    void closeWD() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
