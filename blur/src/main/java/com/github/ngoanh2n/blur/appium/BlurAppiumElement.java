package com.github.ngoanh2n.blur.appium;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represent a Appium element.<br><br>
 *
 * <em>Repository:</em>
 * <ul>
 *     <li><em>GitHub: <a href="https://github.com/ngoanh2n/blur">ngoanh2n/blur</a></em></li>
 *     <li><em>Maven: <a href="https://mvnrepository.com/artifact/com.github.ngoanh2n/blur">com.github.ngoanh2n:blur</a></em></li>
 * </ul>
 *
 * @author ngoanh2n
 * @see WebElement
 * @see SelenideElement
 * @see SelenideAppiumElement
 * @since 2020
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
    @Nonnull
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
    @Nonnull
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
    @Nonnull
    @CanIgnoreReturnValue
    BlurAppiumElement longPress();

    @Nonnull
    @CanIgnoreReturnValue
    BlurAppiumElement swipe(SwipeOptions options);
}
