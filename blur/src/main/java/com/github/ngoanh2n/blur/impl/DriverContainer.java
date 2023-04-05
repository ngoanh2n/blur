package com.github.ngoanh2n.blur.impl;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.impl.WebDriverInstance;
import com.github.ngoanh2n.RuntimeError;
import com.github.ngoanh2n.blur.BlurConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
class DriverContainer {
    private static final Logger log = LoggerFactory.getLogger(DriverContainer.class);
    private final BlurConfig config;
    private final BlurDriver driver;
    private final Map<Long, Instance[]> instanceThreads;

    DriverContainer(BlurConfig config, BlurDriver driver) {
        this.config = config;
        this.driver = driver;
        this.instanceThreads = new ConcurrentHashMap<>(4);
        this.createInstances();
    }

    //-------------------------------------------------------------------------------//

    WebDriver getDriver(int index) {
        DriverContainer.Instance instance = getInstance(index);
        if (instance == null) {
            String browsers = Arrays.toString(getBrowsers());
            String msg = String.format("Illegal index=%s, browsers=%s", index, browsers);
            throw new RuntimeError(msg);
        } else {
            WebDriverInstance wdi = instance.getWdi();
            if (wdi != null) {
                return wdi.webDriver();
            } else {
                WebDriver wd;
                if (instance.getIndex() == 0) {
                    wd = new SelenideDriver(config, driver).getWebDriver();
                } else {
                    config.browser(instance.getBrowser());
                    wd = new SelenideDriver(config).getAndCheckWebDriver();
                }

                wdi = new WebDriverInstance(config, wd, null, null);
                instance.setWdi(wdi);
                log.info("Get driver={}, browser={}", index, instance.getBrowser());
                return wd;
            }
        }
    }

    private Instance getInstance(int index) {
        Instance[] instances = instanceThreads.get(currentThread().getId());
        return Arrays.stream(instances).filter(instance -> instance.getIndex() == index).findFirst().orElse(null);
    }

    private void createInstances() {
        String[] browsers = getBrowsers();
        int browserTotal = browsers.length;
        Instance[] instances = new Instance[browserTotal];
        instanceThreads.put(currentThread().getId(), instances);

        IntStream.range(0, browserTotal).forEach(index -> {
            String browser = browsers[index];
            instances[index] = new Instance(index, browser);
        });
    }

    private String[] getBrowsers() {
        String browser = config.getProperty("selenide.browser");
        String browsersValue = config.getProperty("selenide.browsers");

        if (browsersValue.isEmpty()) {
            return ArrayUtils.insert(0, new String[]{}, browser);
        }
        String[] browsers = browsersValue.split(",");
        return ArrayUtils.insert(0, browsers, browser);
    }

    private static final class Instance {
        @Getter
        final String browser;
        @Getter
        private final int index;
        @Getter
        @Setter
        WebDriverInstance wdi;

        private Instance(int index, String browser) {
            this.index = index;
            this.browser = browser;
        }
    }
}
