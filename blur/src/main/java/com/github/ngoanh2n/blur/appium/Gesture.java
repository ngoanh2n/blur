package com.github.ngoanh2n.blur.appium;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.AppiumDriverRunner;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

/**
 * Modeling a gesture and utils to deal with element for Appium.<br><br>
 *
 * <b>Method</b>
 * <ul>
 *     <li>iOS: <a href="https://github.com/appium/appium-xcuitest-driver/blob/master/docs/execute-methods.md">execute-methods</a></li>
 *     <li>Android: <a href="https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md">mobile-gestures</a></li>
 * </ul>
 * <em>Repository:</em>
 * <ul>
 *     <li><em>GitHub: <a href="https://github.com/ngoanh2n/blur">ngoanh2n/blur</a></em></li>
 *     <li><em>Maven: <a href="https://mvnrepository.com/artifact/com.github.ngoanh2n/blur">com.github.ngoanh2n:blur</a></em></li>
 * </ul>
 *
 * @author ngoanh2n
 * @since 2020
 */
@ParametersAreNonnullByDefault
public abstract class Gesture {
    public static Object execute(Gesture gesture) {
        return execute(gesture.method(), gesture.args());
    }

    public static Object execute(String method, Map<String, Object> args) {
        AppiumDriver driver = AppiumDriverRunner.getMobileDriver();
        return driver.executeScript(method, args);
    }

    public static String getElementId(WebElement element) {
        WebElement target = element;
        if (element instanceof SelenideElement) {
            target = ((SelenideElement) element).getWrappedElement();
        }
        return ((RemoteWebElement) target).getId();
    }

    public static int getElementX(WebElement element, double ratio) {
        int x = element.getLocation().x;
        int w = element.getSize().width;
        return (int) (x + w * ratio);
    }

    public static int getElementY(WebElement element, double ratio) {
        int y = element.getLocation().y;
        int h = element.getSize().height;
        return (int) (y + h * ratio);
    }

    public static Point getElementCenter(WebElement element) {
        int x = (int) (element.getLocation().x + element.getSize().width * 0.5);
        int y = (int) (element.getLocation().y + element.getSize().height * 0.5);
        return new Point(x, y);
    }

    //-------------------------------------------------------------------------------//

    public abstract String method();

    public abstract Map<String, Object> args();
}
