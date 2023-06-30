package com.github.ngoanh2n.blur.driver;

import com.github.ngoanh2n.RuntimeError;
import com.github.ngoanh2n.SetProperty;
import com.github.ngoanh2n.blur.AbstractTest;
import com.github.ngoanh2n.blur.Blur;
import com.github.ngoanh2n.blur.Constant.Browser;
import com.github.ngoanh2n.blur.Constant.Capabilities;
import com.github.ngoanh2n.wdc.WebDriverChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

/**
 * @author ngoanh2n
 */
public class WebDriverSwitchTest extends AbstractTest {
    @Test
    @SetProperty(name = "selenide.browser", value = Browser.CHROME)
    @SetProperty(name = "selenide.otherBrowsers", value = Browser.EDGE)
    void chromeAndEdge() throws Exception {
        switchAndCheck(WebDriverChecker::isChrome, WebDriverChecker::isEdge);
    }

    @Test
    @SetProperty(name = "blur.caps", value = Capabilities.ANDROID_CHROME)
    @SetProperty(name = "selenide.browser", value = Browser.APPIUM)
    @SetProperty(name = "selenide.otherBrowsers", value = Browser.EDGE)
    void androidChromeAndEdge() throws Exception {
        switchAndCheck(WebDriverChecker::isAndroidBrowser, WebDriverChecker::isEdge);
    }

    //-------------------------------------------------------------------------------//

    private void switchAndCheck(Callable<Boolean> checkDriver1, Callable<Boolean> checkDriver2) throws Exception {
        Blur.open();
        Assertions.assertTrue(checkDriver1.call());

        Blur.switchToWebDriver(1);
        Blur.open();
        Assertions.assertTrue(checkDriver2.call());

        Blur.switchToWebDriver(0);
        Assertions.assertTrue(checkDriver1.call());

        Assertions.assertThrows(RuntimeError.class, () -> Blur.switchToWebDriver(2));
    }
}
