package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.ngoanh2n.Commons;
import io.qameta.allure.selenide.AllureSelenide;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
@Aspect
public class FacadeAspect {
    private boolean facadeIntercepted = false;

    @Before("execution(* com.codeborne.selenide.Selenide.open(..))")
    public void selenideOpen() {
        if (!facadeIntercepted) {
            BlurConfig config = new BlurConfig();
            BlurDriver driver = new BlurDriver(config);
            BlurContainer container = new BlurContainer(config);
            SelenideDriver selenideDriver = new SelenideDriver(config, driver);

            Commons.writeField(container, "config", config);
            Commons.writeField(Configuration.class, "defaults", config);
            Commons.writeField(WebDriverRunner.class, "webdriverContainer", container);
            Commons.writeField(WebDriverRunner.class, "staticSelenideDriver", selenideDriver);

            facadeIntercepted = true;
            SelenideLogger.addListener("Allure", new AllureSelenide());
        }
    }
}
