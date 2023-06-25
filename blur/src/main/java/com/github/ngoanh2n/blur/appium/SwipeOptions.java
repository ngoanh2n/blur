package com.github.ngoanh2n.blur.appium;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

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
        return to(GestureExecution.getElementCenter(element));
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
