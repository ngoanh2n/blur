package com.github.ngoanh2n.blur;

import com.codeborne.selenide.WebDriverRunner;
import com.github.ngoanh2n.SetProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author ngoanh2n
 */
@SetProperty(name = "blur.timeout", value = "5000")
@SetProperty(name = "blur.remoteConnectionTimeout", value = "10000")
@SetProperty(name = "blur.remoteReadTimeout", value = "30000")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractTest {
    @AfterEach
    public void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
