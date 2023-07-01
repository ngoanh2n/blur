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

import java.io.File;
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
    @Order(2)
    @SetProperty(name = "blur.caps", value = Capabilities.ANDROID_CHROME)
    @SetProperty(name = "selenide.browser", value = Browser.APPIUM)
    void androidChrome() throws Exception {
        startAndCheck(WebDriverChecker::isAndroidBrowser);
    }

    @Test
    @Order(3)
    @SetProperty(name = "blur.caps", value = Capabilities.ANDROID_NATIVE)
    @SetProperty(name = "selenide.browser", value = Browser.APPIUM)
    void androidNative() throws Exception {
        startAndCheck(WebDriverChecker::isAndroidNative);
    }

    @Test
    @Order(4)
    @SetProperty(name = "blur.caps", value = Capabilities.WINDOWS_NATIVE)
    @SetProperty(name = "selenide.browser", value = Browser.APPIUM)
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
