package com.github.ngoanh2n.blur;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;

/**
 * @author ngoanh2n
 */
public abstract class AbstractTest {
    @AfterEach
    public void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
