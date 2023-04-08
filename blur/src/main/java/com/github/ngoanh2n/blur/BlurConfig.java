package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.CiReportUrl;
import com.github.ngoanh2n.Prop;
import com.github.ngoanh2n.PropFile;
import org.openqa.selenium.MutableCapabilities;

import static com.codeborne.selenide.AssertionMode.STRICT;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.FileDownloadMode.HTTPGET;
import static com.codeborne.selenide.SelectorMode.CSS;
import static com.codeborne.selenide.TextCheck.PARTIAL_TEXT;

/**
 * Replace for {@link com.codeborne.selenide.SelenideConfig}. Cloned from the latest Selenide: 6.12.4.
 * <p>
 * Priority order to get the value of property:
 * <ul>
 *     <li>{@code JVM System Property}
 *     <li>{@code blur.properties}
 *     <li>{@code Default value of Prop<?>}
 * </ul>
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class BlurConfig extends SelenideConfig {
    private final PropFile propFile = new PropFile("blur.properties");

    private final Prop<String> browser = new Prop<>("selenide.browser", String.class, CHROME);
    private final Prop<Boolean> headless = new Prop<>("selenide.headless", Boolean.class, false);
    private final Prop<String> remote = new Prop<>("selenide.remote", String.class, null);
    private final Prop<String> browserSize = new Prop<>("selenide.browserSize", String.class, "1366x768");
    private final Prop<String> browserVersion = new Prop<>("selenide.browserVersion", String.class, null);
    private final Prop<String> browserPosition = new Prop<>("selenide.browserPosition", String.class, null);
    private final Prop<Boolean> driverManagerEnabled = new Prop<>("selenide.driverManagerEnabled", Boolean.class, true);
    private final Prop<Boolean> webdriverLogsEnabled = new Prop<>("selenide.webdriverLogsEnabled", Boolean.class, false);
    private final Prop<String> browserBinary = new Prop<>("selenide.browserBinary", String.class, null);
    private final Prop<String> pageLoadStrategy = new Prop<>("selenide.pageLoadStrategy", String.class, "normal");
    private final Prop<Long> pageLoadTimeout = new Prop<>("selenide.pageLoadTimeout", Long.class, 30000L);
    private MutableCapabilities browserCapabilities = new MutableCapabilities();

    private final Prop<String> baseUrl = new Prop<>("selenide.baseUrl", String.class, "http://localhost:8080");
    private final Prop<Long> timeout = new Prop<>("selenide.timeout", Long.class, 4000L);
    private final Prop<Long> pollingInterval = new Prop<>("selenide.pollingInterval", Long.class, 200L);
    private final Prop<Boolean> holdBrowserOpen = new Prop<>("selenide.holdBrowserOpen", Boolean.class, false);
    private final Prop<Boolean> reopenBrowserOnFail = new Prop<>("selenide.reopenBrowserOnFail", Boolean.class, true);
    private final Prop<Boolean> clickViaJs = new Prop<>("selenide.clickViaJs", Boolean.class, false);
    private final Prop<Boolean> screenshots = new Prop<>("selenide.screenshots", Boolean.class, true);

    private final Prop<Boolean> savePageSource = new Prop<>("selenide.savePageSource", Boolean.class, true);
    private final Prop<String> reportsFolder = new Prop<>("selenide.reportsFolder", String.class, "build/reports/tests");
    private final Prop<String> downloadsFolder = new Prop<>("selenide.downloadsFolder", String.class, "build/downloads");
    private final Prop<String> reportsUrl = new Prop<>("selenide.reportsUrl", String.class, null);
    private final Prop<Boolean> fastSetValue = new Prop<>("selenide.fastSetValue", Boolean.class, false);
    private final Prop<TextCheck> textCheck = new Prop<>("selenide.textCheck", TextCheck.class, PARTIAL_TEXT);
    private final Prop<SelectorMode> selectorMode = new Prop<>("selenide.selectorMode", SelectorMode.class, CSS);
    private final Prop<AssertionMode> assertionMode = new Prop<>("selenide.assertionMode", AssertionMode.class, STRICT);
    private final Prop<FileDownloadMode> fileDownload = new Prop<>("selenide.fileDownload", FileDownloadMode.class, HTTPGET);
    private final Prop<Boolean> proxyEnabled = new Prop<>("selenide.proxyEnabled", Boolean.class, false);
    private final Prop<String> proxyHost = new Prop<>("selenide.proxyHost", String.class, null);
    private final Prop<Integer> proxyPort = new Prop<>("selenide.proxyPort", Integer.class, 0);
    private final Prop<Long> remoteReadTimeout = new Prop<>("selenide.remoteReadTimeout", Long.class, 90000L);
    private final Prop<Long> remoteConnectionTimeout = new Prop<>("selenide.remoteConnectionTimeout", Long.class, 10000L);

    private final Prop<String> otherBrowsers = new Prop<>("selenide.otherBrowsers", String.class, null);

    //-------------------------------------------------------------------------------//

    public String browser() {
        return this.propFile.getPropValue(browser);
    }

    public boolean headless() {
        return this.propFile.getPropValue(headless);
    }

    public String remote() {
        return this.propFile.getPropValue(remote);
    }

    public String browserSize() {
        return this.propFile.getPropValue(browserSize);
    }

    public String browserVersion() {
        return this.propFile.getPropValue(browserVersion);
    }

    public String browserPosition() {
        return this.propFile.getPropValue(browserPosition);
    }

    public boolean driverManagerEnabled() {
        return this.propFile.getPropValue(driverManagerEnabled);
    }

    public boolean webdriverLogsEnabled() {
        return this.propFile.getPropValue(webdriverLogsEnabled);
    }

    public String browserBinary() {
        return this.propFile.getPropValue(browserBinary);
    }

    public String pageLoadStrategy() {
        return this.propFile.getPropValue(pageLoadStrategy);
    }

    public long pageLoadTimeout() {
        return this.propFile.getPropValue(pageLoadTimeout);
    }

    public MutableCapabilities browserCapabilities() {
        return browserCapabilities;
    }

    public String baseUrl() {
        return this.propFile.getPropValue(baseUrl);
    }

    public long timeout() {
        return this.propFile.getPropValue(timeout);
    }

    public long pollingInterval() {
        return this.propFile.getPropValue(pollingInterval);
    }

    public boolean holdBrowserOpen() {
        return this.propFile.getPropValue(holdBrowserOpen);
    }

    public boolean reopenBrowserOnFail() {
        return this.propFile.getPropValue(reopenBrowserOnFail);
    }

    public boolean clickViaJs() {
        return this.propFile.getPropValue(clickViaJs);
    }

    public boolean screenshots() {
        return this.propFile.getPropValue(screenshots);
    }

    public boolean savePageSource() {
        return this.propFile.getPropValue(savePageSource);
    }

    public String reportsFolder() {
        return this.propFile.getPropValue(reportsFolder);
    }

    public String downloadsFolder() {
        return this.propFile.getPropValue(downloadsFolder);
    }

    public String reportsUrl() {
        String value = this.propFile.getPropValue(reportsUrl);
        return new CiReportUrl().getReportsUrl(value);
    }

    public boolean fastSetValue() {
        return this.propFile.getPropValue(fastSetValue);
    }

    public TextCheck textCheck() {
        return this.propFile.getPropValue(textCheck);
    }

    public SelectorMode selectorMode() {
        return this.propFile.getPropValue(selectorMode);
    }

    public AssertionMode assertionMode() {
        return this.propFile.getPropValue(assertionMode);
    }

    public FileDownloadMode fileDownload() {
        return this.propFile.getPropValue(fileDownload);
    }

    public boolean proxyEnabled() {
        return this.propFile.getPropValue(proxyEnabled);
    }

    public String proxyHost() {
        return this.propFile.getPropValue(proxyHost);
    }

    public int proxyPort() {
        return this.propFile.getPropValue(proxyPort);
    }

    public long remoteReadTimeout() {
        return this.propFile.getPropValue(remoteReadTimeout);
    }

    public long remoteConnectionTimeout() {
        return this.propFile.getPropValue(remoteConnectionTimeout);
    }

    public String otherBrowsers() {
        return this.propFile.getPropValue(otherBrowsers);
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

    public BlurConfig otherBrowsers(String otherBrowsers) {
        this.otherBrowsers.setValue(otherBrowsers);
        return this;
    }
}
