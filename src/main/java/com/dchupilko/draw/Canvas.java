package com.dchupilko.draw;

import com.dchupilko.shapes.Line;
import com.dchupilko.shapes.Point;
import com.dchupilko.shapes.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Canvas {
    private int width;
    private int height;
    private char[][] field;

    private List<Point> points = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private List<Rectangle> rectangles = new ArrayList<>();
    private Map<Point, Character> areasToFill = new HashMap<>();

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.field = new char[width][height];
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void addRectangle(Rectangle rectangle) {
        rectangles.add(rectangle);
    }

    public void addAreaToFill(Point point, char color) {
        areasToFill.put(point, color);
    }

    /**
     * Validates whether the specified point fits in canvas bounds
     * @param point Point
     * @return True if point fits in canvas bounds
     */
    public boolean isValidPoint(Point point) {
        return point.getX() >= 0 &&
                point.getY() >= 0 &&
                point.getX() < width &&
                point.getY() < height;
    }

    private String printCanvas() {
        StringBuilder sb = new StringBuilder();

        for (int w = 0; w < width+2; w++) {
            sb.append('-');
        }
        sb.append("\n");

        for (int h = 0; h < height; h++) {
            sb.append('|');
            for (int w = 0; w < width; w++) {
                sb.append(field[w][h] == '\0' ? " " : field[w][h]);
            }
            sb.append("|\n");
        }

        for (int w = 0; w < width+2; w++) {
            sb.append('-');
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return printCanvas();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getField() {
        return field;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public Map<Point, Character> getAreasToFill() {
        return areasToFill;
    }
}
