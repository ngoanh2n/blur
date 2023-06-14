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
 * Tap on the element for Appium.
 *
 * @author ngoanh2n
 * @see Click
 * @see AppiumClick
 */
@ParametersAreNonnullByDefault
public class Tap implements Command<Void> {
    @Nullable
    @Override
    public Void execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
        AppiumClick click = new AppiumClick();
        AppiumClickOptions options = AppiumClickOptions.tap();
        click.execute(proxy, locator, new Object[]{options});
        return null;
    }
}
