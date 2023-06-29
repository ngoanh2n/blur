package com.github.ngoanh2n.blur.driver;

import com.github.ngoanh2n.Property;
import com.github.ngoanh2n.YamlData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
public final class AppiumDriverService {
    private static final Logger log = LoggerFactory.getLogger(AppiumDriverService.class);
    private static final Property<String> capsResource = Property.ofString("blur.caps");

    public static WebDriver createDriver(@Nullable Capabilities otherCaps) {
        AppiumDriverLocalService service = startServer();
        Capabilities caps = readCaps().merge(otherCaps);
        return new AppiumDriver(service, caps);
    }

    public static Capabilities readCaps() {
        DesiredCapabilities caps = new DesiredCapabilities();
        Map<String, Object> providedCaps = YamlData.toMapFromResource(capsResource.getValue());
        providedCaps.forEach(caps::setCapability);

        System.getProperties().forEach((key, value) -> {
            if (String.valueOf(key).startsWith("appium:")) {
                caps.setCapability(String.valueOf(key), String.valueOf(value));
            }
        });
        return caps;
    }

    public static AppiumDriverLocalService startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .withArgument(() -> "--allow-insecure", "chromedriver_autodownload");
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        Runtime.getRuntime().addShutdownHook(new StopAppiumServerThread(service));

        if (!service.isRunning()) {
            service.start();
            log.debug("Appium server started");
        } else {
            log.debug("Appium server has started already");
        }
        return service;
    }

    private final static class StopAppiumServerThread extends Thread {
        private final AppiumDriverLocalService service;

        private StopAppiumServerThread(AppiumDriverLocalService service) {
            this.service = service;
        }

        @Override
        public void run() {
            if (service.isRunning()) {
                service.stop();
                log.debug("Appium server stopped");
            }
        }
    }
}