package com.github.ngoanh2n.blur.appium;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The options for swiping.
 * <ul>
 *     <li>{@link SwipeOptions#to(Point) SwipeOptions.to(..)}:<br>
 *          Swipe to a specified target:
 *          <ul>
 *              <li>{@link SwipeOptions#to(int, int) SwipeOptions.to(x, y)}</li>
 *              <li>{@link SwipeOptions#to(Point) SwipeOptions.to(point)}</li>
 *              <li>{@link SwipeOptions#to(WebElement) SwipeOptions.to(element)}</li>
 *          </ul>
 *     </li>
 *     <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(direction)}:<br>
 *          Swipe to a specified direction.
 *          <ul>
 *              <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(SwipeDirection.LEFT)}</li>
 *              <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(SwipeDirection.UP)}</li>
 *              <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(SwipeDirection.RIGHT)}</li>
 *              <li>{@link SwipeOptions#to(SwipeDirection) SwipeOptions.to(SwipeDirection.DOWN)}</li>
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
    @Nonnull
    @CheckReturnValue
    public static Coordinates to(Point point) {
        return new Coordinates(point);
    }

    @Nonnull
    @CheckReturnValue
    public static Coordinates to(int x, int y) {
        return to(new Point(x, y));
    }

    @Nonnull
    @CheckReturnValue
    public static Coordinates to(WebElement element) {
        return to(Gesture.getElementCenter(element));
    }

    @Nonnull
    @CheckReturnValue
    public static Direction to(SwipeDirection direction) {
        return new Direction(direction);
    }

    //-------------------------------------------------------------------------------//

    public final static class Coordinates extends SwipeOptions {
        private final Point point;

        private Coordinates(Point point) {
            this.point = point;
        }

        public Point getPoint() {
            return point;
        }
    }

    public final static class Direction extends SwipeOptions {
        private final static double DEFAULT_SRC_RATIO = 0.5;
        private final static double DEFAULT_DST_RATIO = 0.5;
        private final SwipeDirection direction;
        private double srcRatio;
        private double dstRatio;

        private Direction(SwipeDirection direction) {
            this.direction = direction;
            this.srcRatio = DEFAULT_SRC_RATIO;
            this.dstRatio = DEFAULT_DST_RATIO;
        }

        public Direction withSourceRatio(double value) {
            this.srcRatio = value;
            return this;
        }

        public Direction withDestinationRatio(double value) {
            this.dstRatio = value;
            return this;
        }

        public SwipeDirection getDirection() {
            return direction;
        }

        public double getSourceRatio() {
            return srcRatio;
        }

        public double getDestinationRatio() {
            return dstRatio;
        }
    }
}
