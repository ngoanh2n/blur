package com.github.ngoanh2n.blur.appium.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.AppiumDriverRunner;
import com.codeborne.selenide.commands.Util;
import com.codeborne.selenide.impl.WebElementSource;
import com.github.ngoanh2n.blur.appium.GestureExecution;
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

@ParametersAreNonnullByDefault
public class Swipe implements Command<SelenideElement> {
    /**
     * Default constructor.
     */
    public Swipe() { /**/ }

    //-------------------------------------------------------------------------------//

    public static void perform(Point source, Point destination) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.getX(), source.getY()))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), destination.getX(), destination.getY()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
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

    protected SelenideElement performToDirection(WebElement element, SwipeOptions.Direction options) {
        Point source = setSourcePointByRatio(element, options);
        Point destination = setDestinationPointByRatio(source, options);
        perform(source, destination);
        return (SelenideElement) element;
    }

    protected SelenideElement performToCoordinates(WebElement element, SwipeOptions.Coordinates options) {
        Point source = GestureExecution.getElementCenter(element);
        Point destination = options.getPoint();
        perform(source, destination);
        return (SelenideElement) element;
    }

    protected Point setSourcePointByRatio(WebElement element, SwipeOptions.Direction options) {
        double ratio = options.getSourceRatio();
        Point source = GestureExecution.getElementCenter(element);

        switch (options.getDirection()) {
            case LEFT:
            case RIGHT:
                source.x = GestureExecution.getElementX(element, ratio);
            case UP:
            case DOWN:
                source.y = GestureExecution.getElementY(element, ratio);
        }
        return source;
    }

    protected Point setDestinationPointByRatio(Point source, SwipeOptions.Direction options) {
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

    protected Dimension getScreenSize() {
        return AppiumDriverRunner.getMobileDriver().manage().window().getSize();
    }
}
