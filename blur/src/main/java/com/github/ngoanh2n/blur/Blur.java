package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * The starting point of Blur skeleton.
 * <p>
 * You should follow the steps: <br>
 * 1. Use {@link #open(String)} for opening the tested application page. <br>
 * 2. Use {@link #$(String)} for searching web elements.
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
@CanIgnoreReturnValue
public final class Blur extends Selenide {
    public static BlurConfig getConfig() {
        return container().config();
    }

    private static BlurContainer container() {
        return (BlurContainer) WebDriverRunner.webdriverContainer;
    }
}
