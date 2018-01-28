package com.dchupilko.shapes;

public class Line implements IShape {
    private Point from;
    private Point to;

    public Line(Point from, Point to) {
        if (from.compareTo(to) <= 0) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
    }

    public boolean isVertical() {
        return from.getX() == to.getX();
    }

    public boolean isHorizontal() {
        return from.getY() == to.getY();
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!from.equals(line.from)) return false;
        return to.equals(line.to);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("from ")
                .append(getFrom())
                .append(" to ")
                .append(getTo());
        return sb.toString();
    }
}
