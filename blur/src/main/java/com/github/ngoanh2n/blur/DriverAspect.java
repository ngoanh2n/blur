package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.WebDriverContainer;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.ngoanh2n.Commons;
import io.qameta.allure.selenide.AllureSelenide;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.SuppressAjWarnings;

/**
 * Intercept Selenide for invoking some modifications and additions.<br><br>
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
@Aspect
@SuppressAjWarnings
public class DriverAspect {
    private boolean facadeUpdated = false;

    /**
     * Intercept before running {@link Selenide#open() com.codeborne.selenide.Selenide.open(..)}.
     */
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

    /**
     * Intercept after running {@link Selenide#open() com.codeborne.selenide.Selenide.open(..)}.
     */
    @After("execution(* com.codeborne.selenide.Selenide.open(..))")
    public void afterOpen() {
        if (!facadeUpdated) {
            facadeUpdated = true;
            Blur.switchToWebDriver(0);
        }
        BlurCommands.refresh();
    }

    /**
     * Intercept after running {@link WebDriverContainer#closeWebDriver() com.codeborne.selenide.impl.WebDriverContainer.closeWebDriver(..)}.
     */
    @After("execution(* com.codeborne.selenide.impl.WebDriverContainer.closeWebDriver(..))")
    public void afterCloseWebDriver() {
        facadeUpdated = false;
        BlurContainer container = (BlurContainer) WebDriverRunner.webdriverContainer;
        container.resetDriverContainer();
    }
}
