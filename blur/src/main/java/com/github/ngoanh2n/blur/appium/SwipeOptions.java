package com.github.ngoanh2n.blur.appium;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The options for swiping.
 * <ul>
 *     <li>Swipe to a specified target:
 *          <ul>
 *              <li>{@link SwipeOptions#to(int, int) SwipeOptions.to(x, y)}</li>
 *              <li>{@link SwipeOptions#to(Point) SwipeOptions.to(point)}</li>
 *              <li>{@link SwipeOptions#to(WebElement) SwipeOptions.to(element)}</li>
 *          </ul>
 *     </li>
 *     <li>Swipe to a specified direction:
 *          <ul>
 *              <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(SwipeDirection.UP)}</li>
 *              <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(SwipeDirection.DOWN)}</li>
 *              <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(SwipeDirection.LEFT)}</li>
 *              <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(SwipeDirection.RIGHT)}</li>
 *          </ul>
 *     </li>
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
public abstract class SwipeOptions {
    /**
     * Specify a target point for swiping to.
     *
     * @param point The point to swipe to.
     * @return The current options.
     */
    @Nonnull
    @CheckReturnValue
    public static Coordinates to(Point point) {
        return new Coordinates(point);
    }

    /**
     * Specify a target coordinates for swiping to.
     *
     * @param x The X coordinate to swipe to.
     * @param y The Y coordinate to swipe to.
     * @return The current options.
     */
    @Nonnull
    @CheckReturnValue
    public static Coordinates to(int x, int y) {
        return to(new Point(x, y));
    }

    /**
     * Specify a target element for swiping to.
     *
     * @param element The element to swipe to.
     * @return The current options.
     */
    @Nonnull
    @CheckReturnValue
    public static Coordinates to(WebElement element) {
        return to(AppiumCommand.getElementCenter(element));
    }

    /**
     * Specify a direction for swiping to.
     *
     * @param direction The direction to swipe to.<br>
     *                  Direction: UP, DOWN, LEFT, RIGHT.
     * @return The current options.
     */
    @Nonnull
    @CheckReturnValue
    public static Direction to(SwipeDirection direction) {
        return new Direction(direction);
    }

    //-------------------------------------------------------------------------------//

    /**
     * Coordinates options for swiping to.
     */
    public final static class Coordinates extends SwipeOptions {
        private final Point point;

        private Coordinates(Point point) {
            this.point = point;
        }

        /**
         * Get the target point to swipe to.
         *
         * @return The point that specified.
         */
        public Point getPoint() {
            return point;
        }

        /**
         * To string for {@link Coordinates}.
         *
         * @return A string representation of {@link Coordinates}.
         */
        @Override
        public String toString() {
            return String.format("%s, %s", point.getX(), point.getY());
        }
    }

    /**
     * Direction options for swiping to.
     */
    public final static class Direction extends SwipeOptions {
        private final SwipeDirection direction;
        private double sourceRatio;
        private double destinationRatio;

        private Direction(SwipeDirection direction) {
            this.direction = direction;
            this.sourceRatio = 0.5;
            this.destinationRatio = 0.5;
        }

        /**
         * Set ratio for swiping source.
         *
         * @param value Desired ratio to specify.
         * @return The current options.
         */
        public Direction withSourceRatio(double value) {
            this.sourceRatio = value;
            return this;
        }

        /**
         * Set ratio for swiping destination.
         *
         * @param value Desired ratio to specify.
         * @return The current options.
         */
        public Direction withDestinationRatio(double value) {
            this.destinationRatio = value;
            return this;
        }

        /**
         * Get the target direction to swipe to.
         *
         * @return The direction that specified.
         */
        public SwipeDirection getDirection() {
            return direction;
        }

        /**
         * Get ratio of swiping source.
         *
         * @return The ratio that specified.
         */
        public double getSourceRatio() {
            return sourceRatio;
        }

        /**
         * Get ratio of swiping destination.
         *
         * @return The ratio that specified.
         */
        public double getDestinationRatio() {
            return destinationRatio;
        }

        /**
         * To string for {@link SwipeDirection}.
         *
         * @return A string representation of {@link SwipeDirection}.
         */
        @Override
        public String toString() {
            return String.format("%s with ratio [%s, %s]", direction, sourceRatio, destinationRatio);
        }
    }
}
