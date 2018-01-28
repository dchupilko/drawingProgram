package com.dchupilko.shapes;

import org.apache.commons.lang3.Validate;

public class Rectangle implements IShape {
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;

    public Rectangle(Point upperLeft, Point lowerRight) {
        Validate.isTrue(lowerRight.compareTo(upperLeft) > 0, "Invalid coordinates");

        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
        this.upperRight = new Point(lowerRight.getX(), upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), lowerRight.getY());
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Point getLowerRight() {
        return lowerRight;
    }

    public void setLowerRight(Point lowerRight) {
        this.lowerRight = lowerRight;
    }

    public Point getUpperRight() {
        return upperRight;
    }

    public void setUpperRight(Point upperRight) {
        this.upperRight = upperRight;
    }

    public Point getLowerLeft() {
        return lowerLeft;
    }

    public void setLowerLeft(Point lowerLeft) {
        this.lowerLeft = lowerLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (!upperLeft.equals(rectangle.upperLeft)) return false;
        return lowerRight.equals(rectangle.lowerRight);
    }

    @Override
    public int hashCode() {
        int result = upperLeft.hashCode();
        result = 31 * result + lowerRight.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("from ")
                .append(getUpperLeft())
                .append(" to ")
                .append(getLowerRight());
        return sb.toString();
    }
}
