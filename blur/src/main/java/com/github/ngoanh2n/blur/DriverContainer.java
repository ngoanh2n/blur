package com.github.ngoanh2n.blur;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.impl.WebDriverInstance;
import com.github.ngoanh2n.RuntimeError;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author ngoanh2n
 */
class DriverContainer {
    private final BlurConfig config;
    private final BlurDriver driver;
    private final Map<Long, Instance[]> drivers;

    DriverContainer(BlurConfig config, BlurDriver driver) {
        this.config = config;
        this.driver = driver;
        this.drivers = initDriverGrid();
    }

    //-------------------------------------------------------------------------------//

    WebDriver getWebDriver(int browserId) {
        Instance instance = getInstance(browserId);
        if (instance == null) {
            String[] browserNames = getBrowserNames();
            String browserIds = Arrays.toString(IntStream.range(0, browserNames.length).toArray());
            throw new RuntimeError("Index of webdriver is " + browserIds + " instead of " + browserId);
        } else {
            config.browser(instance.getBrowserName());
            WebDriverInstance webDriverInstance = instance.getDriverInstance();
            return webDriverInstance != null ? webDriverInstance.webDriver() : createWebDriver(instance);
        }
    }

    void resetDrivers(long threadId) {
        drivers.remove(threadId);
    }

    //-------------------------------------------------------------------------------//

    private Map<Long, Instance[]> initDriverGrid() {
        String[] browserNames = getBrowserNames();
        Instance[] instances = new Instance[browserNames.length];
        Map<Long, Instance[]> drivers = new ConcurrentHashMap<>(4);
        drivers.put(Thread.currentThread().getId(), instances);

        IntStream.range(0, browserNames.length).forEach(browserId -> {
            String browserName = browserNames[browserId];
            instances[browserId] = new Instance(browserId, browserName);
        });
        return drivers;
    }

    private Instance getInstance(int browserId) {
        Instance[] instances = drivers.get(Thread.currentThread().getId());
        return Arrays.stream(instances)
                .filter(instance -> instance.getBrowserId() == browserId)
                .findFirst().orElse(null);
    }

    private String[] getBrowserNames() {
        String browser = config.browser();
        String others = config.otherBrowsers();

        if (others == null || others.isEmpty()) {
            return ArrayUtils.insert(0, new String[]{}, browser);
        }
        String[] otherBrowsers = others.split(",");
        return ArrayUtils.insert(0, otherBrowsers, browser);
    }

    private WebDriver createWebDriver(Instance instance) {
        WebDriver webDriver = instance.getBrowserId() == 0
                ? new SelenideDriver(config, driver).getWebDriver()
                : new SelenideDriver(config).getAndCheckWebDriver();
        instance.setDriverInstance(new WebDriverInstance(config, webDriver, null, null));
        return webDriver;
    }

    //-------------------------------------------------------------------------------//

    private static final class Instance {
        @Getter
        private final int browserId;

        @Getter
        private final String browserName;

        @Getter
        @Setter
        private WebDriverInstance driverInstance;

        private Instance(int browserId, String browserName) {
            this.browserId = browserId;
            this.browserName = browserName;
        }
    }
}
