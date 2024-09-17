package com.github.ngoanh2n.blur.driver;

import com.codeborne.selenide.WebDriverProvider;
import com.github.ngoanh2n.Property;
import com.github.ngoanh2n.YamlData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Start a AppiumDriver with the capabilities.<br>
 * Procedure steps:
 * <ol>
 *     <li>Start Appium server</li>
 *     <li>Read Appium capabilities</li>
 *     <li>Start Appium driver</li>
 *     <li>Stop Appium server</li>
 * </ol>
 *
 * <b>Specify capabilities</b><br>
 * Use system property {@code blur.appium.capabilities} to specify which capabilities are used for {@link AppiumDriver}.<br><br>
 *
 * <b>Specify browser</b><br>
 * Use system property {@code selenide.browser} to ask Selenide to start {@link AppiumDriver}.<br><br>
 *
 * <b>Example</b>
 * <ol>
 *     <li>Specify capabilities resource</li>
 *     <li>Ask Selenide to start {@link AppiumDriver} via {@link #createDriver(Capabilities) AppiumDriverProvider.createDriver(capabilities)}
 *     </li>
 * </ol>
 *
 * <pre>{@code
 *      // Representing how Capabilities is located inside the project structure
 *      src
 *      ├── main
 *      └── test
 *      	├── java
 *      	└── resources
 *      		├── log4j.properties
 *      		└── android-chrome-emulator.yml
 * }</pre>
 * <pre>{@code
 *      // Demo test codes
 *      &#064;Test
 *      public void test() {
 *          System.setProperty("blur.appium.capabilities", "android-chrome-emulator.yml");
 *          System.setProperty("blur.browser", "com.github.ngoanh2n.blur.driver.AppiumDriverProvider");
 *          Blur.open();
 *      }
 * }</pre><br>
 *
 * <em>Repository:</em>
 * <ul>
 *     <li><em>GitHub: <a href="https://github.com/ngoanh2n/blur">ngoanh2n/blur</a></em></li>
 *     <li><em>Maven: <a href="https://mvnrepository.com/artifact/com.github.ngoanh2n/blur">com.github.ngoanh2n:blur</a></em></li>
 * </ul>
 *
 * @author ngoanh2n
 * @since 2020
 */
@ParametersAreNonnullByDefault
public class AppiumDriverProvider implements WebDriverProvider {
    private static final Logger log = LoggerFactory.getLogger(AppiumDriverProvider.class);
    private static final Property<String> capabilitiesResource = Property.ofString("blur.appium.capabilities");

    /**
     * Start Appium driver with capabilities from Yaml file in resources folder.
     *
     * @param otherCapabilities The capabilities for merging to Appium capabilities from Yaml file.
     * @return A {@link AppiumDriver}.
     */
    public static WebDriver startDriver(@Nullable Capabilities otherCapabilities) {
        AppiumDriverLocalService service = startServer();
        Capabilities capabilities = readCapabilities().merge(otherCapabilities);
        return new AppiumDriver(service, capabilities);
    }

    /**
     * Start Appium server and register a new virtual-machine shutdown hook for stopping server automatically.
     *
     * @return A {@link AppiumServiceBuilder}.
     */
    public static AppiumDriverLocalService startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress(AppiumServiceBuilder.BROADCAST_IP4_ADDRESS)
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error:warn")
                .withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload");
        return startServer(builder);
    }

    /**
     * Start Appium server and register a new virtual-machine shutdown hook for stopping server automatically.
     *
     * @param builder To set server information and arguments.
     * @return A {@link AppiumDriverLocalService}.
     */
    public static AppiumDriverLocalService startServer(AppiumServiceBuilder builder) {
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        Runtime.getRuntime().addShutdownHook(new StopAppiumServerThread(service));

        if (!service.isRunning()) {
            service.start();
            log.debug("Appium server started");
        } else {
            log.debug("Appium server has started already");
        }
        //service.clearOutPutStreams();
        return service;
    }

    /**
     * Read Appium capabilities from Yaml file in {@code resources} folder.<br>
     * Use system property {@code blur.appium.capabilities} to specify capabilities resource.<br><br>
     *
     * @return A {@link Capabilities}.
     */
    public static Capabilities readCapabilities() {
        String capabilitiesFile = capabilitiesResource.getValue();
        MutableCapabilities capabilities = new MutableCapabilities();
        YamlData.toMapFromResource(capabilitiesFile).forEach(capabilities::setCapability);

        System.getProperties().forEach((key, value) -> {
            if (String.valueOf(key).startsWith("appium:")) {
                capabilities.setCapability(String.valueOf(key), String.valueOf(value));
            }
        });
        return capabilities;
    }

    //-------------------------------------------------------------------------------//

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return startDriver(capabilities);
    }

    //-------------------------------------------------------------------------------//

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
