package com.dchupilko.shapes;

public class Point implements IShape {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Checks which of the points is closer to the point of origin (0, 0)
     * @param another Another point
     * @return 0 if points are equal, 1 if another point is closer, -1 is this point is closer
     */
    public int compareTo(Point another) {
        if (this == another || (this.getX() == another.getX() && this.getY() == another.getY())) {
            return 0;
        } else if (this.getX() > another.getX() && this.getY() >= another.getY() ||
                    this.getX() >= another.getX() && this.getY() > another.getY()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("(")
                .append(getX())
                .append(",")
                .append(getY())
                .append(")");
        return sb.toString();
    }
}
