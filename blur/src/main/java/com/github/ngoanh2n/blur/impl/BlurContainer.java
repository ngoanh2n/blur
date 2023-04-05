package com.github.ngoanh2n.blur;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.impl.WebDriverInstance;
import com.codeborne.selenide.impl.WebDriverThreadLocalContainer;
import com.github.ngoanh2n.RuntimeError;
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
 * Replace for {@link com.codeborne.selenide.impl.WebDriverThreadLocalContainer}
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class BlurContainer extends WebDriverThreadLocalContainer {
    private static final Logger log = LoggerFactory.getLogger(Blur.class);

    //-------------------------------------------------------------------------------//

    private final BlurConfig config;
    private final BlurDriver driver;
    private final Map<Long, ContainerInstance[]> container;

    public BlurContainer(BlurConfig config, BlurDriver driver) {
        super();
        this.config = config;
        this.driver = driver;
        this.container = new ConcurrentHashMap<>(4);
        this.createContainer();
    }

    //-------------------------------------------------------------------------------//

    public BlurConfig config() {
        return config;
    }

    public void switchToDriver(int index) {
        ContainerInstance instance = getDriverInstance(index);
        WebDriverInstance wdInstance = instance.getWdInstance();

        if (wdInstance != null) {
            WebDriver webDriver = wdInstance.webDriver();
            setWebDriver(webDriver, getProxyServer());
        } else {
            WebDriver webDriver;
            SelenideDriver selenideDriver;

            if (index == 0) {
                selenideDriver = new SelenideDriver(config, driver);
                webDriver = selenideDriver.getWebDriver();
            } else {
                config.browser(instance.getBrowser());
                selenideDriver = new SelenideDriver(config);
                webDriver = selenideDriver.getAndCheckWebDriver();
            }

            setWebDriver(webDriver, getProxyServer());
            instance.setWdInstance(new WebDriverInstance(config, webDriver, getProxyServer(), getBrowserDownloadsFolder()));
        }
    }

    //-------------------------------------------------------------------------------//

    private void createContainer() {
        String[] browsers = getBrowsers();
        int browserTotal = browsers.length;
        ContainerInstance[] instances = new ContainerInstance[browserTotal];
        container.put(currentThread().getId(), instances);

        IntStream.range(0, browserTotal).forEach(index -> {
            String browser = browsers[index];
            instances[index] = new ContainerInstance(index, browser);
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

    private ContainerInstance getDriverInstance(int index) {
        ContainerInstance[] instances = container.get(currentThread().getId());
        return Arrays.stream(instances).filter(instance -> instance.getIndex() == index).findFirst().orElseThrow(() -> {
            String msg = String.format("Browser %s in [%s, %s]", index, 0, instances.length - 1);
            log.error(msg);
            return new RuntimeError(msg);
        });
    }
}
