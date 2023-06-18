package com.github.ngoanh2n.blur.appium;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represent a Appium element.
 *
 * @author ngoanh2n
 * @see WebElement
 * @see SelenideElement
 * @see SelenideAppiumElement
 */
@ParametersAreNonnullByDefault
public interface BlurAppiumElement extends SelenideAppiumElement {
    /**
     * Tap on the element.<br>
     * Use sequence of actions for {@link PointerInput} with the W3C <a href="https://www.w3.org/TR/webdriver/#actions">Action commands</a>.<br>
     * Equivalent to {@link #click(ClickOptions) click(AppiumClickOptions.tap())}
     *
     * @return The current element.
     * @see com.github.ngoanh2n.blur.appium.commands.Tap
     */
    @CanIgnoreReturnValue
    BlurAppiumElement tap();

    /**
     * Double tap on the element.<br>
     * Use sequence of actions for {@link PointerInput} with the W3C <a href="https://www.w3.org/TR/webdriver/#actions">Action commands</a>.<br>
     * Equivalent to {@link #click(ClickOptions) click(AppiumClickOptions.doubleTap())}<br>
     * Similar to {@link #doubleClick()}. Overridden to use for Appium.
     *
     * @return The current element.
     * @see com.github.ngoanh2n.blur.appium.commands.DoubleTap
     */
    @CanIgnoreReturnValue
    BlurAppiumElement doubleTap();

    /**
     * Long press on the element.<br>
     * Use sequence of actions for {@link PointerInput} with the W3C <a href="https://www.w3.org/TR/webdriver/#actions">Action commands</a>.<br>
     * Equivalent to {@link #click(ClickOptions) click(AppiumClickOptions.longPress())}
     *
     * @return The current element.
     * @see com.github.ngoanh2n.blur.appium.commands.LongPress
     */
    @CanIgnoreReturnValue
    BlurAppiumElement longPress();
}