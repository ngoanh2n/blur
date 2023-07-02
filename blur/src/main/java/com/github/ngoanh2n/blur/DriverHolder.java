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
 * <em>Repository:</em>
 * <ul>
 *     <li><em>GitHub: <a href="https://github.com/ngoanh2n/blur">ngoanh2n/blur</a></em></li>
 *     <li><em>Maven: <a href="https://mvnrepository.com/artifact/com.github.ngoanh2n/blur">com.github.ngoanh2n:blur</a></em></li>
 * </ul>
 *
 * @author ngoanh2n
 * @since 2020
 */
class DriverHolder {
    private final long threadId;
    private final BlurConfig config;
    private final BlurDriver driver;
    private final Map<Long, Instance[]> drivers;

    DriverHolder(BlurConfig config, BlurDriver driver) {
        this.threadId = Thread.currentThread().getId();
        this.config = config;
        this.driver = driver;
        this.drivers = initWebDriverGrid();
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
            WebDriverInstance webDriverInstance = instance.getWebDriverInstance();
            return webDriverInstance != null ? webDriverInstance.webDriver() : createWebDriver(instance);
        }
    }

    void removeWebDriverInstances() {
        drivers.remove(threadId);
    }

    //-------------------------------------------------------------------------------//

    private Map<Long, Instance[]> initWebDriverGrid() {
        String[] browserNames = getBrowserNames();
        Instance[] instances = new Instance[browserNames.length];
        Map<Long, Instance[]> drivers = new ConcurrentHashMap<>(4);
        drivers.put(threadId, instances);

        IntStream.range(0, browserNames.length).forEach(browserId -> {
            String browserName = browserNames[browserId];
            instances[browserId] = new Instance(browserId, browserName);
        });
        return drivers;
    }

    private Instance getInstance(int browserId) {
        Instance[] instances = drivers.get(threadId);
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
        instance.setWebDriverInstance(new WebDriverInstance(config, webDriver, null, null));
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
        private WebDriverInstance webDriverInstance;

        private Instance(int browserId, String browserName) {
            this.browserId = browserId;
            this.browserName = browserName;
        }
    }
}
