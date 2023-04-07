package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.CiReportUrl;
import com.github.ngoanh2n.Prop;
import com.github.ngoanh2n.PropsFile;
import com.github.ngoanh2n.RuntimeError;
import org.openqa.selenium.MutableCapabilities;

import static com.codeborne.selenide.AssertionMode.STRICT;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.FileDownloadMode.HTTPGET;
import static com.codeborne.selenide.SelectorMode.CSS;
import static com.codeborne.selenide.TextCheck.PARTIAL_TEXT;

/**
 * Replace for {@link com.codeborne.selenide.SelenideConfig}
 * <p>
 * Priority order to get the value of property: JVM System Property > blur.properties > default value
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class BlurConfig extends SelenideConfig {
    private final PropsFile propsFile = new PropsFile("blur.properties");

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
        return getValueOf(browser);
    }

    public boolean headless() {
        return getValueOf(headless);
    }

    public String remote() {
        return getValueOf(remote);
    }

    public String browserSize() {
        return getValueOf(browserSize);
    }

    public String browserVersion() {
        return getValueOf(browserVersion);
    }

    public String browserPosition() {
        return getValueOf(browserPosition);
    }

    public boolean driverManagerEnabled() {
        return getValueOf(driverManagerEnabled);
    }

    public boolean webdriverLogsEnabled() {
        return getValueOf(webdriverLogsEnabled);
    }

    public String browserBinary() {
        return getValueOf(browserBinary);
    }

    public String pageLoadStrategy() {
        return getValueOf(pageLoadStrategy);
    }

    public long pageLoadTimeout() {
        return getValueOf(pageLoadTimeout);
    }

    public MutableCapabilities browserCapabilities() {
        return browserCapabilities;
    }

    public String baseUrl() {
        return getValueOf(baseUrl);
    }

    public long timeout() {
        return getValueOf(timeout);
    }

    public long pollingInterval() {
        return getValueOf(pollingInterval);
    }

    public boolean holdBrowserOpen() {
        return getValueOf(holdBrowserOpen);
    }

    public boolean reopenBrowserOnFail() {
        return getValueOf(reopenBrowserOnFail);
    }

    public boolean clickViaJs() {
        return getValueOf(clickViaJs);
    }

    public boolean screenshots() {
        return getValueOf(screenshots);
    }

    public boolean savePageSource() {
        return getValueOf(savePageSource);
    }

    public String reportsFolder() {
        return getValueOf(reportsFolder);
    }

    public String downloadsFolder() {
        return getValueOf(downloadsFolder);
    }

    public String reportsUrl() {
        String value = getValueOf(reportsUrl);
        return new CiReportUrl().getReportsUrl(value);
    }

    public boolean fastSetValue() {
        return getValueOf(fastSetValue);
    }

    public TextCheck textCheck() {
        return getValueOf(textCheck);
    }

    public SelectorMode selectorMode() {
        return getValueOf(selectorMode);
    }

    public AssertionMode assertionMode() {
        return getValueOf(assertionMode);
    }

    public FileDownloadMode fileDownload() {
        return getValueOf(fileDownload);
    }

    public boolean proxyEnabled() {
        return getValueOf(proxyEnabled);
    }

    public String proxyHost() {
        return getValueOf(proxyHost);
    }

    public int proxyPort() {
        return getValueOf(proxyPort);
    }

    public long remoteReadTimeout() {
        return getValueOf(remoteReadTimeout);
    }

    public long remoteConnectionTimeout() {
        return getValueOf(remoteConnectionTimeout);
    }

    public String otherBrowsers() {
        return getValueOf(otherBrowsers);
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


    //-------------------------------------------------------------------------------//

    @SuppressWarnings("unchecked")
    private <T> T getValueOf(Prop<T> prop) {
        String name = prop.getName();
        Class<T> type = prop.getType();
        String val = propsFile.getProp(name);

        if (val.isEmpty()) {
            return prop.getDefaultValue();
        }

        if (type == String.class) return (T) val;
        if (type == Byte.class) return (T) Byte.valueOf(val);
        if (type == Long.class) return (T) Long.valueOf(val);
        if (type == Short.class) return (T) Short.valueOf(val);
        if (type == Float.class) return (T) Float.valueOf(val);
        if (type == Double.class) return (T) Double.valueOf(val);
        if (type == Integer.class) return (T) Integer.valueOf(val);
        if (type == Boolean.class) return (T) Boolean.valueOf(val);
        if (type == TextCheck.class) return (T) TextCheck.valueOf(val);
        if (type == SelectorMode.class) return (T) SelectorMode.valueOf(val);
        if (type == AssertionMode.class) return (T) AssertionMode.valueOf(val);
        if (type == FileDownloadMode.class) return (T) FileDownloadMode.valueOf(val);
        throw new RuntimeError("Type " + type.getTypeName() + " cannot be parsed");
    }
}
