package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Replace for com.codeborne.selenide.impl.StaticDriver (Cloned from Selenide 6.15.0).
 *
 * @author ngoanh2n
 */
public class BlurDriver implements Driver {
    private final BlurConfig config;

    /**
     * Default constructor.
     *
     * @param config The implementation of {@link Config com.codeborne.selenide.Config}.
     */
    public BlurDriver(BlurConfig config) {
        this.config = config;
    }

    //-------------------------------------------------------------------------------//

    @Override
    @CheckReturnValue
    @Nonnull
    public Config config() {
        return config;
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public Browser browser() {
        return new Browser(config.browser(), config.headless());
    }

    @Override
    @CheckReturnValue
    public boolean hasWebDriverStarted() {
        return WebDriverRunner.hasWebDriverStarted();
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver getWebDriver() {
        return WebDriverRunner.getWebDriver();
    }

    @Override
    @CheckReturnValue
    @Nullable
    public SelenideProxyServer getProxy() {
        return WebDriverRunner.getSelenideProxy();
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver getAndCheckWebDriver() {
        return WebDriverRunner.getAndCheckWebDriver();
    }

    @Override
    @CheckReturnValue
    @Nullable
    public DownloadsFolder browserDownloadsFolder() {
        return WebDriverRunner.getBrowserDownloadsFolder();
    }

    @Override
    public void close() {
        WebDriverRunner.closeWebDriver();
    }
}
