package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.CiReportUrl;
import com.github.ngoanh2n.PropertiesFile;
import com.github.ngoanh2n.Property;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.MutableCapabilities;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

import static com.codeborne.selenide.AssertionMode.STRICT;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.FileDownloadMode.HTTPGET;
import static com.codeborne.selenide.SelectorMode.CSS;
import static com.codeborne.selenide.TextCheck.PARTIAL_TEXT;

/**
 * Replace for {@link com.codeborne.selenide.SelenideConfig}.
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

    private final Property<String> browser = Property.ofString("blur.browser", CHROME);
    private final Property<Boolean> headless = Property.ofBoolean("blur.headless", false);
    private final Property<String> remote = Property.ofString("blur.remote");
    private final Property<String> browserSize = Property.ofString("blur.browserSize", "1366x768");
    private final Property<String> browserVersion = Property.ofString("blur.browserVersion");
    private final Property<String> browserPosition = Property.ofString("blur.browserPosition");
    private final Property<Boolean> webdriverLogsEnabled = Property.ofBoolean("blur.webdriverLogsEnabled", false);
    private final Property<String> browserBinary = Property.ofString("blur.browserBinary");
    private final Property<String> pageLoadStrategy = Property.ofString("blur.pageLoadStrategy", "normal");
    private final Property<Long> pageLoadTimeout = Property.ofLong("blur.pageLoadTimeout", 30000L);
    private MutableCapabilities browserCapabilities = new MutableCapabilities();

    private final Property<String> baseUrl = Property.ofString("blur.baseUrl", "http://localhost:8080");
    private final Property<Long> timeout = Property.ofLong("blur.timeout", 4000L);
    private final Property<Long> pollingInterval = Property.ofLong("blur.pollingInterval", 200L);
    private final Property<Boolean> holdBrowserOpen = Property.ofBoolean("blur.holdBrowserOpen", false);
    private final Property<Boolean> reopenBrowserOnFail = Property.ofBoolean("blur.reopenBrowserOnFail", true);
    private final Property<Boolean> clickViaJs = Property.ofBoolean("blur.clickViaJs", false);
    private final Property<Boolean> screenshots = Property.ofBoolean("blur.screenshots", true);

    private final Property<Boolean> savePageSource = Property.ofBoolean("blur.savePageSource", true);
    private final Property<String> reportsFolder = Property.ofString("blur.reportsFolder", "build/reports/tests");
    private final Property<String> downloadsFolder = Property.ofString("blur.downloadsFolder", "build/downloads");
    private final Property<String> reportsUrl = Property.ofString("blur.reportsUrl");
    private final Property<Boolean> fastSetValue = Property.ofBoolean("blur.fastSetValue", false);
    private final Property<TextCheck> textCheck = new Property<>("blur.textCheck", TextCheck.class, PARTIAL_TEXT);
    private final Property<SelectorMode> selectorMode = new Property<>("blur.selectorMode", SelectorMode.class, CSS);
    private final Property<AssertionMode> assertionMode = new Property<>("blur.assertionMode", AssertionMode.class, STRICT);
    private final Property<FileDownloadMode> fileDownload = new Property<>("blur.fileDownload", FileDownloadMode.class, HTTPGET);
    private final Property<Boolean> proxyEnabled = Property.ofBoolean("blur.proxyEnabled", false);
    private final Property<String> proxyHost = Property.ofString("blur.proxyHost");
    private final Property<Integer> proxyPort = Property.ofInteger("blur.proxyPort", 0);
    private final Property<Long> remoteReadTimeout = Property.ofLong("blur.remoteReadTimeout", 90000L);
    private final Property<Long> remoteConnectionTimeout = Property.ofLong("blur.remoteConnectionTimeout", 10000L);

    private final Property<String> otherBrowsers = Property.ofString("blur.otherBrowsers");

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

    //-------------------------------------------------------------------------------//

    /**
     * Convert all configs as properties.
     *
     * @return {@link Properties}.
     */
    public Properties asProperties() {
        Properties properties = new Properties();
        Field[] fs = FieldUtils.getAllFields(this.getClass());

        Arrays.stream(fs).forEach(f -> {
            if (f.getType() == Property.class) {
                f.setAccessible(true);
                try {
                    Property<?> p = (Property<?>) f.get(this);
                    properties.put(p.getName(), Objects.toString(p.getValue()));
                } catch (IllegalAccessException | NullPointerException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return properties;
    }

    /**
     * Get other browsers.
     *
     * @return Other browsers.
     */
    public String otherBrowsers() {
        return propertiesFile.getProperty(otherBrowsers);
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
