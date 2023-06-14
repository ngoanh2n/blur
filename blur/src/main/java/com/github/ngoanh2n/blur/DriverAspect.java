package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.ngoanh2n.Commons;
import io.qameta.allure.selenide.AllureSelenide;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.codeborne.selenide.WebDriverRunner.webdriverContainer;

/**
 * @author ngoanh2n
 */
@Aspect
public class DriverAspect {
    private boolean facadeUpdated = false;

    @Before("execution(* com.codeborne.selenide.Selenide.open(..))")
    public void beforeOpen() {
        if (!facadeUpdated) {
            BlurConfig config = new BlurConfig();
            BlurDriver driver = new BlurDriver(config);
            BlurContainer container = new BlurContainer(config, driver);
            SelenideDriver selenideDriver = new SelenideDriver(config, driver);

            Commons.writeField(container, "config", config);
            Commons.writeField(Configuration.class, "defaults", config);
            Commons.writeField(WebDriverRunner.class, "webdriverContainer", container);
            Commons.writeField(WebDriverRunner.class, "staticSelenideDriver", selenideDriver);

            SelenideLogger.addListener("Allure", new AllureSelenide());
        }
    }

    @After("execution(* com.codeborne.selenide.Selenide.open(..))")
    public void afterOpen() {
        if (!facadeUpdated) {
            facadeUpdated = true;
            Blur.switchToWebDriver(0);
        }
        BlurCommands.refresh();
    }

    @After("execution(* com.codeborne.selenide.impl.WebDriverContainer.closeWebDriver(..))")
    public void afterCloseWebDriver() {
        facadeUpdated = false;
        BlurContainer container = (BlurContainer) webdriverContainer;
        container.resetDriverContainer();
    }
}
