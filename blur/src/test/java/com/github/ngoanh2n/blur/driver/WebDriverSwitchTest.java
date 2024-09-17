package com.github.ngoanh2n.blur.driver;

import com.github.ngoanh2n.EnabledIfProperty;
import com.github.ngoanh2n.RuntimeError;
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
public class WebDriverSwitchTest extends AbstractTest {
    @Test
    @Order(1)
    @SetProperty(name = "blur.browser", value = Browser.SAFARI)
    @SetProperty(name = "blur.otherBrowsers", value = Browser.FIREFOX)
    @EnabledIfProperty(name = "test.os", value = "macos")
    void safariAndFirefox() throws Exception {
        switchAndCheck(WebDriverChecker::isSafari, WebDriverChecker::isFirefox);
    }

    @Test
    @Order(2)
    @SetProperty(name = "blur.appiumCapabilities", value = Capabilities.IOS_SAFARI)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @SetProperty(name = "blur.otherBrowsers", value = Browser.FIREFOX)
    @EnabledIfProperty(name = "test.os", value = "macos")
    void iOSSafariAndFirefox() throws Exception {
        switchAndCheck(WebDriverChecker::isIOSBrowser, WebDriverChecker::isFirefox);
    }

    @Test
    @Order(3)
    @SetProperty(name = "blur.appiumCapabilities", value = Capabilities.IOS_NATIVE)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @SetProperty(name = "blur.otherBrowsers", value = Browser.SAFARI)
    @EnabledIfProperty(name = "test.os", value = "macos")
    void iOSNativeAndSafari() throws Exception {
        switchAndCheck(WebDriverChecker::isIOSNative, WebDriverChecker::isSafari);
    }

    @Test
    @Order(4)
    @SetProperty(name = "blur.browser", value = Browser.CHROME)
    @SetProperty(name = "blur.otherBrowsers", value = Browser.EDGE)
    @EnabledIfProperty(name = "test.os", value = {"windows", "linux"})
    void chromeAndEdge() throws Exception {
        switchAndCheck(WebDriverChecker::isChrome, WebDriverChecker::isEdge);
    }

    @Test
    @Order(5)
    @SetProperty(name = "blur.appiumCapabilities", value = Capabilities.ANDROID_CHROME)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @SetProperty(name = "blur.otherBrowsers", value = Browser.EDGE)
    @EnabledIfProperty(name = "test.os", value = {"windows", "linux"})
    void androidChromeAndEdge() throws Exception {
        switchAndCheck(WebDriverChecker::isAndroidBrowser, WebDriverChecker::isEdge);
    }

    @Test
    @Order(6)
    @SetProperty(name = "blur.appiumCapabilities", value = Capabilities.ANDROID_NATIVE)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @SetProperty(name = "blur.otherBrowsers", value = Browser.CHROME)
    @EnabledIfProperty(name = "test.os", value = {"windows", "linux"})
    void androidNativeAndChrome() throws Exception {
        switchAndCheck(WebDriverChecker::isAndroidNative, WebDriverChecker::isChrome);
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
