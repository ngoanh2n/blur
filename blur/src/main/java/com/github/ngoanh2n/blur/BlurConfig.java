package com.github.ngoanh2n.blur;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.CiReportUrl;
import com.github.ngoanh2n.PropsFile;
import org.openqa.selenium.MutableCapabilities;

import static com.codeborne.selenide.AssertionMode.STRICT;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.FileDownloadMode.HTTPGET;
import static com.codeborne.selenide.SelectorMode.CSS;

/**
 * Replace for {@link com.codeborne.selenide.impl.StaticConfig}
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public class BlurConfig extends SelenideConfig {
    private final PropsFile propsFile = new PropsFile("blur.properties");
    private String browser = getProperty("selenide.browser", CHROME);
    private boolean headless = Boolean.parseBoolean(getProperty("selenide.headless", "false"));
    private String remote = getProperty("selenide.remote", null);
    private String browserSize = getProperty("selenide.browserSize", "1366x768");
    private String browserVersion = getProperty("selenide.browserVersion", null);
    private String browserPosition = getProperty("selenide.browserPosition", null);
    private boolean driverManagerEnabled = Boolean.parseBoolean(getProperty("selenide.driverManagerEnabled", "true"));
    private boolean webdriverLogsEnabled = Boolean.parseBoolean(getProperty("selenide.webdriverLogsEnabled", "false"));
    private String browserBinary = getProperty("selenide.browserBinary", null);
    private String pageLoadStrategy = getProperty("selenide.pageLoadStrategy", "normal");
    private long pageLoadTimeout = Long.parseLong(getProperty("selenide.pageLoadTimeout", "30000"));
    private MutableCapabilities browserCapabilities = new MutableCapabilities();
    private String baseUrl = getProperty("selenide.baseUrl", "http://localhost:8080");
    private long timeout = Long.parseLong(getProperty("selenide.timeout", "4000"));
    private long pollingInterval = Long.parseLong(getProperty("selenide.pollingInterval", "200"));
    private boolean holdBrowserOpen = Boolean.parseBoolean(getProperty("selenide.holdBrowserOpen", "false"));
    private boolean reopenBrowserOnFail = Boolean.parseBoolean(getProperty("selenide.reopenBrowserOnFail", "true"));
    private boolean clickViaJs = Boolean.parseBoolean(getProperty("selenide.clickViaJs", "false"));
    private boolean screenshots = Boolean.parseBoolean(getProperty("selenide.screenshots", "true"));
    private boolean savePageSource = Boolean.parseBoolean(getProperty("selenide.savePageSource", "true"));
    private String reportsFolder = getProperty("selenide.reportsFolder", "build/reports/tests");
    private String downloadsFolder = getProperty("selenide.downloadsFolder", "build/downloads");
    private String reportsUrl = new CiReportUrl().getReportsUrl(getProperty("selenide.reportsUrl", null));
    private boolean fastSetValue = Boolean.parseBoolean(getProperty("selenide.fastSetValue", "false"));
    private TextCheck textCheck = TextCheck.valueOf(getProperty("selenide.textCheck", TextCheck.PARTIAL_TEXT.name()));
    private SelectorMode selectorMode = SelectorMode.valueOf(getProperty("selenide.selectorMode", CSS.name()));
    private AssertionMode assertionMode = AssertionMode.valueOf(getProperty("selenide.assertionMode", STRICT.name()));
    private FileDownloadMode fileDownload = FileDownloadMode.valueOf(getProperty("selenide.fileDownload", HTTPGET.name()));
    private boolean proxyEnabled = Boolean.parseBoolean(getProperty("selenide.proxyEnabled", "false"));
    private String proxyHost = getProperty("selenide.proxyHost", null);
    private int proxyPort = Integer.parseInt(getProperty("selenide.proxyPort", "0"));
    private long remoteReadTimeout = Long.parseLong(getProperty("selenide.remoteReadTimeout", "90000"));
    private long remoteConnectionTimeout = Long.parseLong(getProperty("selenide.remoteConnectionTimeout", "10000"));

    public String getProperty(String name) {
        return getProperty(name, null);
    }

    public String getProperty(String name, String defaultValue) {
        String value = propsFile.getProp(name, defaultValue);
        if (value != null && value.trim().isEmpty() && defaultValue == null) {
            return null;
        }
        return value;
    }

    @Override
    public String baseUrl() {
        return baseUrl;
    }

    public BlurConfig baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public long timeout() {
        return timeout;
    }

    public BlurConfig timeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    @Override
    public long pollingInterval() {
        return pollingInterval;
    }

    public BlurConfig pollingInterval(long pollingInterval) {
        this.pollingInterval = pollingInterval;
        return this;
    }

    @Override
    public boolean holdBrowserOpen() {
        return holdBrowserOpen;
    }

    public BlurConfig holdBrowserOpen(boolean holdBrowserOpen) {
        this.holdBrowserOpen = holdBrowserOpen;
        return this;
    }

    @Override
    public boolean reopenBrowserOnFail() {
        return reopenBrowserOnFail;
    }

    public BlurConfig reopenBrowserOnFail(boolean reopenBrowserOnFail) {
        this.reopenBrowserOnFail = reopenBrowserOnFail;
        return this;
    }

    @Override
    public boolean clickViaJs() {
        return clickViaJs;
    }

    public BlurConfig clickViaJs(boolean clickViaJs) {
        this.clickViaJs = clickViaJs;
        return this;
    }

    @Override
    public boolean screenshots() {
        return screenshots;
    }

    public BlurConfig screenshots(boolean screenshots) {
        this.screenshots = screenshots;
        return this;
    }

    @Override
    public boolean savePageSource() {
        return savePageSource;
    }

    public BlurConfig savePageSource(boolean savePageSource) {
        this.savePageSource = savePageSource;
        return this;
    }

    @Override
    public String reportsFolder() {
        return reportsFolder;
    }

    public BlurConfig reportsFolder(String reportsFolder) {
        this.reportsFolder = reportsFolder;
        return this;
    }

    @Override
    public String downloadsFolder() {
        return downloadsFolder;
    }

    public BlurConfig downloadsFolder(String downloadsFolder) {
        this.downloadsFolder = downloadsFolder;
        return this;
    }

    @Override
    public String reportsUrl() {
        return reportsUrl;
    }

    public BlurConfig reportsUrl(String reportsUrl) {
        this.reportsUrl = reportsUrl;
        return this;
    }

    @Override
    public boolean fastSetValue() {
        return fastSetValue;
    }

    @Override
    public TextCheck textCheck() {
        return textCheck;
    }

    public BlurConfig fastSetValue(boolean fastSetValue) {
        this.fastSetValue = fastSetValue;
        return this;
    }

    public BlurConfig textCheck(TextCheck textCheck) {
        this.textCheck = textCheck;
        return this;
    }

    @Override
    public SelectorMode selectorMode() {
        return selectorMode;
    }

    public BlurConfig selectorMode(SelectorMode selectorMode) {
        this.selectorMode = selectorMode;
        return this;
    }

    @Override
    public AssertionMode assertionMode() {
        return assertionMode;
    }

    public BlurConfig assertionMode(AssertionMode assertionMode) {
        this.assertionMode = assertionMode;
        return this;
    }

    @Override
    public FileDownloadMode fileDownload() {
        return fileDownload;
    }

    public BlurConfig fileDownload(FileDownloadMode fileDownload) {
        this.fileDownload = fileDownload;
        return this;
    }

    @Override
    public boolean proxyEnabled() {
        return proxyEnabled;
    }

    public BlurConfig proxyEnabled(boolean proxyEnabled) {
        this.proxyEnabled = proxyEnabled;
        return this;
    }

    @Override
    public String proxyHost() {
        return proxyHost;
    }

    public BlurConfig proxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
        return this;
    }

    @Override
    public int proxyPort() {
        return proxyPort;
    }

    public BlurConfig proxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }

    @Override
    public String browser() {
        return browser;
    }

    public BlurConfig browser(String browser) {
        this.browser = browser;
        return this;
    }

    @Override
    public boolean headless() {
        return headless;
    }

    public BlurConfig headless(boolean headless) {
        this.headless = headless;
        return this;
    }

    @Override
    public String remote() {
        return remote;
    }

    public BlurConfig remote(String remote) {
        this.remote = remote;
        return this;
    }

    @Override
    public String browserSize() {
        return browserSize;
    }

    public BlurConfig browserSize(String browserSize) {
        this.browserSize = browserSize;
        return this;
    }

    @Override
    public String browserVersion() {
        return browserVersion;
    }

    public BlurConfig browserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
        return this;
    }

    @Override
    public String browserPosition() {
        return browserPosition;
    }

    public BlurConfig browserPosition(String browserPosition) {
        this.browserPosition = browserPosition;
        return this;
    }

    @Override
    public boolean driverManagerEnabled() {
        return driverManagerEnabled;
    }

    public BlurConfig driverManagerEnabled(boolean driverManagerEnabled) {
        this.driverManagerEnabled = driverManagerEnabled;
        return this;
    }

    @Override
    public boolean webdriverLogsEnabled() {
        return webdriverLogsEnabled;
    }

    public BlurConfig webdriverLogsEnabled(boolean webdriverLogsEnabled) {
        this.webdriverLogsEnabled = webdriverLogsEnabled;
        return this;
    }

    @Override
    public String browserBinary() {
        return browserBinary;
    }

    public BlurConfig browserBinary(String browserBinary) {
        this.browserBinary = browserBinary;
        return this;
    }

    @Override
    public String pageLoadStrategy() {
        return pageLoadStrategy;
    }

    @Override
    public long pageLoadTimeout() {
        return pageLoadTimeout;
    }

    public BlurConfig pageLoadStrategy(String pageLoadStrategy) {
        this.pageLoadStrategy = pageLoadStrategy;
        return this;
    }

    public BlurConfig pageLoadTimeout(long pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
        return this;
    }

    @Override
    public MutableCapabilities browserCapabilities() {
        return browserCapabilities;
    }

    public BlurConfig browserCapabilities(MutableCapabilities browserCapabilities) {
        this.browserCapabilities = browserCapabilities;
        return this;
    }

    @Override
    public long remoteReadTimeout() {
        return remoteReadTimeout;
    }

    public BlurConfig remoteReadTimeout(long remoteReadTimeout) {
        this.remoteReadTimeout = remoteReadTimeout;
        return this;
    }

    @Override
    public long remoteConnectionTimeout() {
        return remoteConnectionTimeout;
    }

    public BlurConfig remoteConnectionTimeout(long remoteConnectionTimeout) {
        this.remoteConnectionTimeout = remoteConnectionTimeout;
        return this;
    }
}
