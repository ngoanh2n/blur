package com.github.ngoanh2n.blur.aspect;

import com.github.ngoanh2n.EnabledIfProperty;
import com.github.ngoanh2n.SetProperty;
import com.github.ngoanh2n.blur.AbstractTest;
import com.github.ngoanh2n.blur.Blur;
import com.github.ngoanh2n.blur.Constant.Browser;
import com.github.ngoanh2n.blur.Constant.Capabilities;
import com.github.ngoanh2n.wdc.WebDriverChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * @author ngoanh2n
 */
public class WebDriverStartTest extends AbstractTest {
    @Test
    @Order(1)
    @SetProperty(name = "blur.browser", value = Browser.SAFARI)
    @EnabledIfProperty(name = "test.os", value = "macos")
    void safari() throws Exception {
        startAndCheck(WebDriverChecker::isSafari);
    }

    @Test
    @Order(2)
    @SetProperty(name = "blur.browser", value = Browser.CHROME)
    @EnabledIfProperty(name = "test.os", value = {"macos", "linux", "windows"})
    void chrome() throws Exception {
        startAndCheck(WebDriverChecker::isChrome);
    }

    @Test
    @Order(3)
    @SetProperty(name = "blur.browser", value = Browser.EDGE)
    @EnabledIfProperty(name = "test.os", value = {"macos", "linux", "windows"})
    void edge() throws Exception {
        startAndCheck(WebDriverChecker::isEdge);
    }

    @Test
    @Order(4)
    @SetProperty(name = "blur.browser", value = Browser.FIREFOX)
    @EnabledIfProperty(name = "test.os", value = {"macos", "linux", "windows"})
    void firefox() throws Exception {
        startAndCheck(WebDriverChecker::isFirefox);
    }

    @Test
    @Order(5)
    @SetProperty(name = "blur.appium.capabilities", value = Capabilities.IOS_SAFARI)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @EnabledIfProperty(name = "test.os", value = "macos")
    void iOSSafari() throws Exception {
        startAndCheck(WebDriverChecker::isIOSBrowser);
    }

    @Test
    @Order(6)
    @SetProperty(name = "blur.appium.capabilities", value = Capabilities.IOS_NATIVE)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @EnabledIfProperty(name = "test.os", value = "macos")
    void iOSNative() throws Exception {
        startAndCheck(WebDriverChecker::isIOSNative);
    }

    @Test
    @Order(7)
    @SetProperty(name = "blur.appium.capabilities", value = Capabilities.ANDROID_CHROME)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @EnabledIfProperty(name = "test.os", value = {"macos", "linux", "windows"})
    void androidChrome() throws Exception {
        startAndCheck(WebDriverChecker::isAndroidBrowser);
    }

    @Test
    @Order(8)
    @SetProperty(name = "blur.appium.capabilities", value = Capabilities.ANDROID_NATIVE)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @EnabledIfProperty(name = "test.os", value = {"macos", "linux", "windows"})
    void androidNative() throws Exception {
        startAndCheck(WebDriverChecker::isAndroidNative);
    }

    @Test
    @Order(9)
    @SetProperty(name = "blur.appium.capabilities", value = Capabilities.MACOS_NATIVE)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @EnabledIfProperty(name = "test.os", value = "macos")
    void macOSNative() throws Exception {
        startAndCheck(WebDriverChecker::isMacOSNative);
    }

    @Test
    @Order(10)
    @SetProperty(name = "blur.appium.capabilities", value = Capabilities.WINDOWS_NATIVE)
    @SetProperty(name = "blur.browser", value = Browser.APPIUM)
    @EnabledIfProperty(name = "test.os", value = "windows")
    void windowsNative() throws Exception {
        String appWorkingDirCapability = "appium:appWorkingDir";
        String appWorkingDir = new File("build/resources/test").getAbsolutePath();

        System.setProperty(appWorkingDirCapability, appWorkingDir);
        startAndCheck(WebDriverChecker::isWindowsNative);

        System.clearProperty(appWorkingDirCapability);
    }

    //-------------------------------------------------------------------------------//

    private void startAndCheck(Callable<Boolean> checkDriver) throws Exception {
        Blur.open();
        Assertions.assertNotNull(Blur.getConfig());
        Assertions.assertTrue(checkDriver.call());
    }
}
