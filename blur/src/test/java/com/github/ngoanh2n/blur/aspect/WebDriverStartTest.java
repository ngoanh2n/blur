package com.github.ngoanh2n.blur.aspect;

import com.github.ngoanh2n.SetProperty;
import com.github.ngoanh2n.blur.AbstractTest;
import com.github.ngoanh2n.blur.Blur;
import com.github.ngoanh2n.blur.Constant.Browser;
import com.github.ngoanh2n.blur.Constant.Capabilities;
import com.github.ngoanh2n.wdc.WebDriverChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

/**
 * @author ngoanh2n
 */
public class WebDriverStartTest extends AbstractTest {
    @Test
    @Order(1)
    @SetProperty(name = "selenide.browser", value = Browser.CHROME)
    void chrome() throws Exception {
        startAndCheck(WebDriverChecker::isChrome);
    }

    @Test
    @Order(3)
    @SetProperty(name = "blur.caps", value = Capabilities.ANDROID_NATIVE)
    @SetProperty(name = "selenide.browser", value = Browser.APPIUM)
    void androidNative() throws Exception {
        startAndCheck(WebDriverChecker::isAndroidNative);
    }

    //-------------------------------------------------------------------------------//

    private void startAndCheck(Callable<Boolean> checkDriver) throws Exception {
        Blur.open();
        Assertions.assertNotNull(Blur.getConfig());
        Assertions.assertTrue(checkDriver.call());
    }
}
