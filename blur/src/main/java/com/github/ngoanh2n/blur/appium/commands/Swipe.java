package com.github.ngoanh2n.blur.appium.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.AppiumDriverRunner;
import com.codeborne.selenide.commands.Util;
import com.codeborne.selenide.impl.WebElementSource;
import com.github.ngoanh2n.blur.appium.AppiumCommand;
import com.github.ngoanh2n.blur.appium.SwipeOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.time.Duration;
import java.util.Collections;

/**
 * Swipe the element to a specified target or direction for Appium.<br><br>
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
public class Swipe implements Command<SelenideElement> {
    /**
     * Default constructor.
     */
    public Swipe() { /**/ }

    //-------------------------------------------------------------------------------//

    /**
     * Swipe from point to other point.
     *
     * @param source      The point to swipe.
     * @param destination The point to swipe to.
     */
    public static void perform(Point source, Point destination) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        PointerInput.Origin origin = PointerInput.Origin.viewport();
        PointerInput.MouseButton mouseButton = PointerInput.MouseButton.MIDDLE;

        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), origin, source.getX(), source.getY()))
                .addAction(finger.createPointerDown(mouseButton.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), origin, destination.getX(), destination.getY()))
                .addAction(finger.createPointerUp(mouseButton.asArg()));
        AppiumDriverRunner.getMobileDriver().perform(Collections.singletonList(sequence));
    }

    //-------------------------------------------------------------------------------//

    @Nullable
    @Override
    public SelenideElement execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
        Object options = Util.firstOf(args);
        if (Util.firstOf(args) instanceof SwipeOptions.Direction) {
            return performToDirection(proxy, Util.cast(options));
        } else {
            return performToCoordinates(proxy, Util.cast(options));
        }
    }

    private SelenideElement performToDirection(WebElement element, SwipeOptions.Direction options) {
        Point source = setSourcePointByRatio(element, options);
        Point destination = setDestinationPointByRatio(source, options);
        perform(source, destination);
        return (SelenideElement) element;
    }

    private SelenideElement performToCoordinates(WebElement element, SwipeOptions.Coordinates options) {
        Point source = AppiumCommand.getElementCenter(element);
        Point destination = options.getPoint();
        perform(source, destination);
        return (SelenideElement) element;
    }

    private Point setSourcePointByRatio(WebElement element, SwipeOptions.Direction options) {
        double ratio = options.getSourceRatio();
        Point source = AppiumCommand.getElementCenter(element);

        switch (options.getDirection()) {
            case LEFT:
            case RIGHT:
                source.x = AppiumCommand.getElementX(element, ratio);
            case UP:
            case DOWN:
                source.y = AppiumCommand.getElementY(element, ratio);
        }
        return source;
    }

    private Point setDestinationPointByRatio(Point source, SwipeOptions.Direction options) {
        int left;
        double ratio = options.getDestinationRatio();
        Point destination = new Point(source.getX(), source.getY());

        switch (options.getDirection()) {
            case LEFT:
                left = source.getX();
                destination.x = (int) ((1 - ratio) * left);
            case RIGHT:
                left = getScreenSize().getWidth() - source.getX();
                destination.x = source.getX() + (int) (ratio * left);
            case UP:
                left = source.getY();
                destination.y = (int) ((1 - ratio) * left);
            case DOWN:
                left = getScreenSize().getHeight() - source.getY();
                destination.y = source.getY() + (int) (ratio * left);
        }
        return destination;
    }

    private Dimension getScreenSize() {
        return AppiumDriverRunner.getMobileDriver().manage().window().getSize();
    }
}
