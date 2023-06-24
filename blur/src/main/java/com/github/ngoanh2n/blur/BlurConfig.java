package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.CiReportUrl;
import com.github.ngoanh2n.PropertiesFile;
import com.github.ngoanh2n.Property;
import org.openqa.selenium.MutableCapabilities;

import static com.codeborne.selenide.AssertionMode.STRICT;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.FileDownloadMode.HTTPGET;
import static com.codeborne.selenide.SelectorMode.CSS;
import static com.codeborne.selenide.TextCheck.PARTIAL_TEXT;

/**
 * Replace for {@link com.codeborne.selenide.SelenideConfig} (Cloned from Selenide 6.15.0).
 * <p>
 * Priority order to get the value of property:
 * <ul>
 *     <li>{@code JVM System Property}
 *     <li>{@code blur.properties}
 *     <li>{@code Default value of Property<?>}
 * </ul>
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
public class BlurConfig extends SelenideConfig {
    private final PropertiesFile propertiesFile = new PropertiesFile("blur.properties");

    private final Property<String> browser = new Property<>("selenide.browser", String.class, CHROME);
    private final Property<Boolean> headless = new Property<>("selenide.headless", Boolean.class, false);
    private final Property<String> remote = new Property<>("selenide.remote", String.class, null);
    private final Property<String> browserSize = new Property<>("selenide.browserSize", String.class, "1366x768");
    private final Property<String> browserVersion = new Property<>("selenide.browserVersion", String.class, null);
    private final Property<String> browserPosition = new Property<>("selenide.browserPosition", String.class, null);
    private final Property<Boolean> driverManagerEnabled = new Property<>("selenide.driverManagerEnabled", Boolean.class, true);
    private final Property<Boolean> webdriverLogsEnabled = new Property<>("selenide.webdriverLogsEnabled", Boolean.class, false);
    private final Property<String> browserBinary = new Property<>("selenide.browserBinary", String.class, null);
    private final Property<String> pageLoadStrategy = new Property<>("selenide.pageLoadStrategy", String.class, "normal");
    private final Property<Long> pageLoadTimeout = new Property<>("selenide.pageLoadTimeout", Long.class, 30000L);
    private final Property<String> baseUrl = new Property<>("selenide.baseUrl", String.class, "http://localhost:8080");
    private final Property<Long> timeout = new Property<>("selenide.timeout", Long.class, 4000L);
    private final Property<Long> pollingInterval = new Property<>("selenide.pollingInterval", Long.class, 200L);
    private final Property<Boolean> holdBrowserOpen = new Property<>("selenide.holdBrowserOpen", Boolean.class, false);
    private final Property<Boolean> reopenBrowserOnFail = new Property<>("selenide.reopenBrowserOnFail", Boolean.class, true);
    private final Property<Boolean> clickViaJs = new Property<>("selenide.clickViaJs", Boolean.class, false);
    private final Property<Boolean> screenshots = new Property<>("selenide.screenshots", Boolean.class, true);
    private final Property<Boolean> savePageSource = new Property<>("selenide.savePageSource", Boolean.class, true);
    private final Property<String> reportsFolder = new Property<>("selenide.reportsFolder", String.class, "build/reports/tests");
    private final Property<String> downloadsFolder = new Property<>("selenide.downloadsFolder", String.class, "build/downloads");
    private final Property<String> reportsUrl = new Property<>("selenide.reportsUrl", String.class, null);
    private final Property<Boolean> fastSetValue = new Property<>("selenide.fastSetValue", Boolean.class, false);
    private final Property<TextCheck> textCheck = new Property<>("selenide.textCheck", TextCheck.class, PARTIAL_TEXT);
    private final Property<SelectorMode> selectorMode = new Property<>("selenide.selectorMode", SelectorMode.class, CSS);
    private final Property<AssertionMode> assertionMode = new Property<>("selenide.assertionMode", AssertionMode.class, STRICT);
    private final Property<FileDownloadMode> fileDownload = new Property<>("selenide.fileDownload", FileDownloadMode.class, HTTPGET);
    private final Property<Boolean> proxyEnabled = new Property<>("selenide.proxyEnabled", Boolean.class, false);
    private final Property<String> proxyHost = new Property<>("selenide.proxyHost", String.class, null);
    private final Property<Integer> proxyPort = new Property<>("selenide.proxyPort", Integer.class, 0);
    private final Property<Long> remoteReadTimeout = new Property<>("selenide.remoteReadTimeout", Long.class, 90000L);
    private final Property<Long> remoteConnectionTimeout = new Property<>("selenide.remoteConnectionTimeout", Long.class, 10000L);
    private final Property<String> otherBrowsers = new Property<>("selenide.otherBrowsers", String.class, null);
    private MutableCapabilities browserCapabilities = new MutableCapabilities();

    /**
     * Default constructor.
     */
    public BlurConfig() { /**/ }

    //-------------------------------------------------------------------------------//

    public String browser() {
        return propertiesFile.getProperty(browser);
    }

    public boolean headless() {
        return propertiesFile.getProperty(headless);
    }

    public String remote() {
        return propertiesFile.getProperty(remote);
    }

    public String browserSize() {
        return propertiesFile.getProperty(browserSize);
    }

    public String browserVersion() {
        return propertiesFile.getProperty(browserVersion);
    }

    public String browserPosition() {
        return propertiesFile.getProperty(browserPosition);
    }

    public boolean driverManagerEnabled() {
        return propertiesFile.getProperty(driverManagerEnabled);
    }

    public boolean webdriverLogsEnabled() {
        return propertiesFile.getProperty(webdriverLogsEnabled);
    }

    public String browserBinary() {
        return propertiesFile.getProperty(browserBinary);
    }

    public String pageLoadStrategy() {
        return propertiesFile.getProperty(pageLoadStrategy);
    }

    public long pageLoadTimeout() {
        return propertiesFile.getProperty(pageLoadTimeout);
    }

    public MutableCapabilities browserCapabilities() {
        return browserCapabilities;
    }

    public String baseUrl() {
        return propertiesFile.getProperty(baseUrl);
    }

    public long timeout() {
        return propertiesFile.getProperty(timeout);
    }

    public long pollingInterval() {
        return propertiesFile.getProperty(pollingInterval);
    }

    public boolean holdBrowserOpen() {
        return propertiesFile.getProperty(holdBrowserOpen);
    }

    public boolean reopenBrowserOnFail() {
        return propertiesFile.getProperty(reopenBrowserOnFail);
    }

    public boolean clickViaJs() {
        return propertiesFile.getProperty(clickViaJs);
    }

    public boolean screenshots() {
        return propertiesFile.getProperty(screenshots);
    }

    public boolean savePageSource() {
        return propertiesFile.getProperty(savePageSource);
    }

    public String reportsFolder() {
        return propertiesFile.getProperty(reportsFolder);
    }

    public String downloadsFolder() {
        return propertiesFile.getProperty(downloadsFolder);
    }

    public String reportsUrl() {
        String value = propertiesFile.getProperty(reportsUrl);
        return new CiReportUrl().getReportsUrl(value);
    }

    public boolean fastSetValue() {
        return propertiesFile.getProperty(fastSetValue);
    }

    public TextCheck textCheck() {
        return propertiesFile.getProperty(textCheck);
    }

    public SelectorMode selectorMode() {
        return propertiesFile.getProperty(selectorMode);
    }

    public AssertionMode assertionMode() {
        return propertiesFile.getProperty(assertionMode);
    }

    public FileDownloadMode fileDownload() {
        return propertiesFile.getProperty(fileDownload);
    }

    public boolean proxyEnabled() {
        return propertiesFile.getProperty(proxyEnabled);
    }

    public String proxyHost() {
        return propertiesFile.getProperty(proxyHost);
    }

    public int proxyPort() {
        return propertiesFile.getProperty(proxyPort);
    }

    public long remoteReadTimeout() {
        return propertiesFile.getProperty(remoteReadTimeout);
    }

    public long remoteConnectionTimeout() {
        return propertiesFile.getProperty(remoteConnectionTimeout);
    }

    /**
     * Get other browsers.
     *
     * @return Other browsers.
     */
    public String otherBrowsers() {
        return propertiesFile.getProperty(otherBrowsers);
    }

    //-------------------------------------------------------------------------------//

    public BlurConfig browser(String browser) {
        this.browser.setValue(browser);
        return this;
    }

    public BlurConfig headless(boolean headless) {
        this.headless.setValue(headless);
        return this;
    }

    public BlurConfig remote(String remote) {
        this.remote.setValue(remote);
        return this;
    }

    public BlurConfig browserSize(String browserSize) {
        this.browserSize.setValue(browserSize);
        return this;
    }

    public BlurConfig browserVersion(String browserVersion) {
        this.browserVersion.setValue(browserVersion);
        return this;
    }

    public BlurConfig browserPosition(String browserPosition) {
        this.browserPosition.setValue(browserPosition);
        return this;
    }

    public BlurConfig driverManagerEnabled(boolean driverManagerEnabled) {
        this.driverManagerEnabled.setValue(driverManagerEnabled);
        return this;
    }

    public BlurConfig webdriverLogsEnabled(boolean webdriverLogsEnabled) {
        this.webdriverLogsEnabled.setValue(webdriverLogsEnabled);
        return this;
    }

    public BlurConfig browserBinary(String browserBinary) {
        this.browserBinary.setValue(browserBinary);
        return this;
    }

    public BlurConfig pageLoadStrategy(String pageLoadStrategy) {
        this.pageLoadStrategy.setValue(pageLoadStrategy);
        return this;
    }

    public BlurConfig pageLoadTimeout(long pageLoadTimeout) {
        this.pageLoadTimeout.setValue(pageLoadTimeout);
        return this;
    }

    public BlurConfig browserCapabilities(MutableCapabilities browserCapabilities) {
        this.browserCapabilities = browserCapabilities;
        return this;
    }

    public BlurConfig baseUrl(String baseUrl) {
        this.baseUrl.setValue(baseUrl);
        return this;
    }

    public BlurConfig timeout(long timeout) {
        this.timeout.setValue(timeout);
        return this;
    }

    public BlurConfig pollingInterval(long pollingInterval) {
        this.pollingInterval.setValue(pollingInterval);
        return this;
    }

    public BlurConfig holdBrowserOpen(boolean holdBrowserOpen) {
        this.holdBrowserOpen.setValue(holdBrowserOpen);
        return this;
    }

    public BlurConfig reopenBrowserOnFail(boolean reopenBrowserOnFail) {
        this.reopenBrowserOnFail.setValue(reopenBrowserOnFail);
        return this;
    }

    public BlurConfig clickViaJs(boolean clickViaJs) {
        this.clickViaJs.setValue(clickViaJs);
        return this;
    }

    public BlurConfig screenshots(boolean screenshots) {
        this.screenshots.setValue(screenshots);
        return this;
    }

    public BlurConfig savePageSource(boolean savePageSource) {
        this.savePageSource.setValue(savePageSource);
        return this;
    }

    public BlurConfig reportsFolder(String reportsFolder) {
        this.reportsFolder.setValue(reportsFolder);
        return this;
    }

    public BlurConfig downloadsFolder(String downloadsFolder) {
        this.downloadsFolder.setValue(downloadsFolder);
        return this;
    }

    public BlurConfig reportsUrl(String reportsUrl) {
        this.reportsUrl.setValue(reportsUrl);
        return this;
    }

    public BlurConfig fastSetValue(boolean fastSetValue) {
        this.fastSetValue.setValue(fastSetValue);
        return this;
    }

    public BlurConfig textCheck(TextCheck textCheck) {
        this.textCheck.setValue(textCheck);
        return this;
    }

    public BlurConfig selectorMode(SelectorMode selectorMode) {
        this.selectorMode.setValue(selectorMode);
        return this;
    }

    public BlurConfig assertionMode(AssertionMode assertionMode) {
        this.assertionMode.setValue(assertionMode);
        return this;
    }

    public BlurConfig fileDownload(FileDownloadMode fileDownload) {
        this.fileDownload.setValue(fileDownload);
        return this;
    }

    public BlurConfig proxyEnabled(boolean proxyEnabled) {
        this.proxyEnabled.setValue(proxyEnabled);
        return this;
    }

    public BlurConfig proxyHost(String proxyHost) {
        this.proxyHost.setValue(proxyHost);
        return this;
    }

    public BlurConfig proxyPort(int proxyPort) {
        this.proxyPort.setValue(proxyPort);
        return this;
    }

    public BlurConfig remoteReadTimeout(long remoteReadTimeout) {
        this.remoteReadTimeout.setValue(remoteReadTimeout);
        return this;
    }

    public BlurConfig remoteConnectionTimeout(long remoteConnectionTimeout) {
        this.remoteConnectionTimeout.setValue(remoteConnectionTimeout);
        return this;
    }

    /**
     * Set other browsers to this config.
     *
     * @param otherBrowsers Browsers to set.
     * @return The current {@link BlurConfig}.
     */
    public BlurConfig otherBrowsers(String otherBrowsers) {
        this.otherBrowsers.setValue(otherBrowsers);
        return this;
    }
}
