package com.github.ngoanh2n.blur.appium;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.WebElement;

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
}
