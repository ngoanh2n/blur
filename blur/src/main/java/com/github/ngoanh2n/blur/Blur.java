package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.WebDriverRunner;

/**
 * The starting point of Blur skeleton.
 * <p>
 * You should follow the steps: <br>
 * 1. Use {@link #initialize()} for overriding {@linkplain SelenideConfig} of Selenide facade.<br>
 * 2. Use {@link #open(String)} for opening the tested application page. <br>
 * 3. Use {@link #$(String)} for searching web elements.
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public final class Blur extends Selenide {
    /**
     * The first starting point in your tests.
     */
    public static void initialize() {
        Config config = new BlurConfig();
        WebDriverRunner.webdriverContainer = new BlurContainer(config);
    }
}
