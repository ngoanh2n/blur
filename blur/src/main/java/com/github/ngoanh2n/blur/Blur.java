package com.github.ngoanh2n.blur;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.WebDriverContainer;
import com.github.ngoanh2n.RuntimeError;
import com.github.ngoanh2n.blur.impl.BlurContainer;

import static com.codeborne.selenide.WebDriverRunner.webdriverContainer;

/**
 * The starting point of Blur skeleton.
 * <p>
 * You should follow the steps: <br>
 * 1. Use {@link #open(String)} for opening the tested application page. <br>
 * 2. Use {@link #$(String)} for searching web elements.
 *
 * @author Ho Huu Ngoan (ngoanh2n@gmail.com)
 */
public final class Blur extends Selenide {
    public static BlurConfig getConfig() {
        return container().config();
    }

    public static BlurDriver getDriver() {
        return container().driver();
    }

    public static void switchToWebDriver(int index) {
        container().switchToWebDriver(index);
    }

    private static BlurContainer container() {
        WebDriverContainer container = webdriverContainer;
        if (container instanceof BlurContainer) {
            return (BlurContainer) container;
        }
        throw new RuntimeError("You have to call Blur.open(..) first");
    }
}
