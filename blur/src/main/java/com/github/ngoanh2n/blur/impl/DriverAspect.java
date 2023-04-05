package com.github.ngoanh2n.blur.impl;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.ngoanh2n.Commons;
import com.github.ngoanh2n.blur.Blur;
import com.github.ngoanh2n.blur.BlurConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
@Aspect
public class DriverAspect {
    private boolean facadeIntercepted = false;

    @Before("execution(* com.codeborne.selenide.Selenide.open(..))")
    public void beforeOpen() {
        if (!facadeIntercepted) {
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
        if (!facadeIntercepted) {
            Blur.switchToDriver(0);
            facadeIntercepted = true;
        }
    }
}
