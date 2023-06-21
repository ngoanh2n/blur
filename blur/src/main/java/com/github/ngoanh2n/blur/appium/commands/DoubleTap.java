package com.github.ngoanh2n.blur.appium.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.AppiumClickOptions;
import com.codeborne.selenide.appium.commands.AppiumClick;
import com.codeborne.selenide.commands.Click;
import com.codeborne.selenide.impl.WebElementSource;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Double tap on the element for Appium.<br><br>
 *
 * <em>Repository:</em>
 * <ul>
 *     <li><em>GitHub: <a href="https://github.com/ngoanh2n/blur">ngoanh2n/blur</a></em></li>
 *     <li><em>Maven: <a href="https://mvnrepository.com/artifact/com.github.ngoanh2n/blur">com.github.ngoanh2n:blur</a></em></li>
 * </ul>
 *
 * @author ngoanh2n
 * @see Click
 * @see AppiumClick
 * @since 2020
 */
@ParametersAreNonnullByDefault
public class DoubleTap implements Command<Void> {
    @Nullable
    @Override
    public Void execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
        AppiumClick click = new AppiumClick();
        AppiumClickOptions options = AppiumClickOptions.doubleTap();
        click.execute(proxy, locator, new Object[]{options});
        return null;
    }
}
