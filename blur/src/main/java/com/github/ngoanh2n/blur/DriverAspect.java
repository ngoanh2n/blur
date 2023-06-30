package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.WebDriverContainer;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.ngoanh2n.Commons;
import com.google.common.collect.ImmutableList;
import io.qameta.allure.selenide.AllureSelenide;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

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
    private boolean facadeChanged = false;
    private final Logger log = LoggerFactory.getLogger(DriverAspect.class);

    /**
     * Default constructor.
     */
    public DriverAspect() { /**/ }

    /**
     * Intercept before {@link Selenide#open() Selenide.open(..)}.
     */
    @Before("execution(* com.codeborne.selenide.Selenide.open(..))")
    public void beforeOpen() {
        if (!facadeChanged) {
            BlurConfig config = new BlurConfig();
            BlurDriver driver = new BlurDriver(config);
            BlurContainer container = new BlurContainer(config, driver);

            WebDriverRunner.webdriverContainer = container;
            Commons.writeField(container, "config", config);
            Commons.writeField(Configuration.class, "defaults", config);
            SelenideLogger.addListener("Allure", new AllureSelenide());
            log.debug("Changed Selenide facade with: {}, {}, {}", config, driver, container);
        }
    }

    /**
     * Intercept around {@link WebDriverRunner WebDriverRunner.getSelenideDriver()}.
     */
    @Around("execution(* com.codeborne.selenide.WebDriverRunner.getSelenideDriver())")
    public Object aroundGetSelenideDriver(ProceedingJoinPoint joinPoint) throws Throwable {
        BlurContainer container = Blur.getContainer();
        BlurConfig config = container.config();
        BlurDriver driver = container.driver();
        return new SelenideDriver(config, driver);
    }

    /**
     * Intercept around {@link RemoteWebDriver#RemoteWebDriver(CommandExecutor, Capabilities) new RemoteWebDriver(..)}.
     */
    @Around("execution(* org.openqa.selenium.remote.RemoteWebDriver.init(..))")
    public Object aroundInitRemoteWebDriver(ProceedingJoinPoint joinPoint) throws Throwable {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof Capabilities) {
                Capabilities caps = (Capabilities) arg;
                Platform platform = caps.getPlatformName();

                try {
                    if (platform.is(Platform.IOS) || platform.is(Platform.ANDROID)) {
                        BlurConfig config = Blur.getContainer().config();
                        config.browserSize(null).pageLoadTimeout(-1);
                        log.debug("Instantiate AppiumDriver: Disable config [browserSize, pageLoadTimeout]");
                    }
                } catch (NullPointerException ignored) {
                    log.warn("Could not check platform from {}", caps);
                    return joinPoint.proceed();
                }
                break;
            }
        }
        return joinPoint.proceed();
    }

    /**
     * Intercept after returning {@link Selenide#open() Selenide.open(..)}.
     */
    @AfterReturning("execution(* com.codeborne.selenide.Selenide.open(..))")
    public void afterReturningOpen() {
        BlurCommands.refresh();
        if (!facadeChanged) {
            facadeChanged = true;
            Blur.switchToWebDriver(0);
        }
    }

    /**
     * Intercept after {@link WebDriverContainer#closeWebDriver() WebDriverContainer.closeWebDriver()}.
     */
    @After("execution(* com.codeborne.selenide.impl.WebDriverContainer.closeWebDriver())")
    public void afterCloseWebDriver() {
        facadeChanged = false;
        Blur.getContainer().resetDriverContainer();
    }

    //-------------------------------------------------------------------------------//

    private boolean isAppium(Capabilities capabilities) {
        Set<String> capabilityNames = capabilities.getCapabilityNames();
        List<String> automationNameKeys = ImmutableList.of("automationName", "appium:automationName");
        List<String> automationNameValues = ImmutableList.of("xcuitest", "uiautomator2", "windows", "mac2");

        if (capabilityNames.contains("platformName")) {
            Platform platform = capabilities.getPlatformName();
            if (platform.is(Platform.IOS) || platform.is(Platform.ANDROID)) {
                return true;
            }
        }
        for (String automationKey : automationNameKeys) {
            if (capabilityNames.contains(automationKey)) {
                String automationNameValue = (String) capabilities.getCapability(automationKey);
                return automationNameValues.contains(automationNameValue);
            }
        }
        return false;
    }
}
