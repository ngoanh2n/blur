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
 * Modeling a command and utils to deal with element for Appium.<br><br>
 *
 * <b>Method</b>
 * <ul>
 *     <li>iOS: <a href="https://github.com/appium/appium-xcuitest-driver/blob/master/docs/execute-methods.md">execute-methods</a></li>
 *     <li>Android: <a href="https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md">mobile-gestures</a></li>
 * </ul>
 *
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
public abstract class AppiumCommand {
    /**
     * Execute an Appium command.
     *
     * @param command A modeling command.
     * @return The result after executed.
     */
    public static Object execute(AppiumCommand command) {
        return execute(command.method(), command.args());
    }

    /**
     * Execute an Appium command.
     *
     * @param method A basic or gesture method of the command.
     * @param args   Arguments of the command.
     * @return The result after executed.
     */
    public static Object execute(String method, Map<String, Object> args) {
        AppiumDriver driver = AppiumDriverRunner.getMobileDriver();
        return driver.executeScript(method, args);
    }

    /**
     * Get ID of element.
     *
     * @param element The element to get ID.
     * @return ID of element.
     */
    public static String getElementId(WebElement element) {
        WebElement target = element;
        if (element instanceof SelenideElement) {
            target = ((SelenideElement) element).getWrappedElement();
        }
        return ((RemoteWebElement) target).getId();
    }

    /**
     * Get centered point of element.
     *
     * @param element The element to get XY coordinates.
     * @return XY coordinates of element.
     */
    public static Point getElementCenter(WebElement element) {
        int x = getElementX(element, 0.5);
        int y = getElementY(element, 0.5);
        return new Point(x, y);
    }

    /**
     * Get X coordinate of element.
     *
     * @param element The element to get X coordinate.
     * @param ratio   The ratio to adjust X coordinate on X-axis is bounded by the width of element.<br>
     *                If {@code ratio} is equal to 0.5 then X coordinate at center.<br>
     *                If {@code ratio} is less than 0.5 then X coordinate is in the {@code left} part.<br>
     *                If {@code ratio} is greater than 0.5 then X coordinate is in the {@code right} part.
     * @return X coordinate of element.
     */
    public static int getElementX(WebElement element, double ratio) {
        int x = element.getLocation().getX();
        int w = element.getSize().getWidth();
        return (int) (x + w * ratio);
    }

    /**
     * Get Y coordinate of element.
     *
     * @param element The element to get Y coordinate.
     * @param ratio   The ratio to adjust Y coordinate on Y-axis is bounded by the width of element.<br>
     *                If {@code ratio} is equal to 0.5 then Y coordinate at center.<br>
     *                If {@code ratio} is less than 0.5 then Y coordinate is in the {@code up} part.<br>
     *                If {@code ratio} is greater than 0.5 then Y coordinate is in the {@code down} part.
     * @return Y coordinate of element.
     */
    public static int getElementY(WebElement element, double ratio) {
        int y = element.getLocation().getY();
        int h = element.getSize().getHeight();
        return (int) (y + h * ratio);
    }

    //-------------------------------------------------------------------------------//

    /**
     * A basic or gesture method of the command.
     *
     * @return A method.
     */
    public abstract String method();

    /**
     * Arguments of the command.
     *
     * @return An Appium command args.
     */
    public abstract Map<String, Object> args();
}
