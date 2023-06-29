package com.github.ngoanh2n.blur.appium.driver;

import com.codeborne.selenide.WebDriverRunner;
import com.github.ngoanh2n.SetProperty;
import com.github.ngoanh2n.blur.Blur;
import com.github.ngoanh2n.blur.BlurConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author ngoanh2n
 */
@SetProperty(name = "blur.caps", value = "caps/android-emulator-native.yml")
@SetProperty(name = "selenide.browser", value = "com.github.ngoanh2n.blur.driver.AppiumDriverProvider")
public class AppiumDriverStartTest {
    @Test
    void openAppiumDriver() {
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
