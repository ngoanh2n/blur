package com.github.ngoanh2n.blur.driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class AppiumDriverService {
    private static final Logger log = LoggerFactory.getLogger(AppiumDriverService.class);

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
