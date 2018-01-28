package com.dchupilko.renderers.impl;

import com.dchupilko.constants.Constants;
import com.dchupilko.draw.Canvas;
import com.dchupilko.renderers.AbstractRenderer;
import com.dchupilko.shapes.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FillAreaRenderer extends AbstractRenderer<Point> {
    private static final Logger LOGGER = LogManager.getLogger(FillAreaRenderer.class);

    private PointRenderer pointRenderer;

    public FillAreaRenderer(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Renders a filled area on a canvas by Forest Fire algorithm.
     * @link https://en.wikipedia.org/wiki/Flood_fill#The_algorithm
     * @param point Point around which to start filling
     * @param color Color to fill
     */
    @Override
    public void render(Point point, char color) {
        LOGGER.debug("Filling area around point " + point + " with color " + color);

        char originalColor = getCurrentColor(point);
        canvas.addAreaToFill(point, color);
        pointRenderer = getPointRenderer(canvas);

        boolean[][] hits = new boolean[canvas.getWidth()][canvas.getHeight()];
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);

        while (!queue.isEmpty()) {
            Point p = queue.remove();
            if (hits[p.getX()][p.getY()]) {
                continue;
            }

            char currentColor = getCurrentColor(p);
            if (currentColor != originalColor) {
                continue;
            }

            pointRenderer.render(p, color);
            hits[p.getX()][p.getY()] = true;

            validateAndAddToQueue(new Point(p.getX(), p.getY() - 1), queue, hits); // up
            validateAndAddToQueue(new Point(p.getX(), p.getY() + 1), queue, hits); // down
            validateAndAddToQueue(new Point(p.getX() - 1, p.getY()), queue, hits); // left
            validateAndAddToQueue(new Point(p.getX() + 1, p.getY()), queue, hits); // right
        }
    }

    @Override
    public void render(Point point) {
        render(point, Constants.COLOR_X);
    }

    @Override
    public void renderAll() {
        for (Map.Entry<Point, Character> entry : canvas.getAreasToFill().entrySet()) {
            render(entry.getKey(), entry.getValue());
        }
    }

    protected char getCurrentColor(Point p) {
        return canvas.getField()[p.getX()][p.getY()];
    }

    private void validateAndAddToQueue(Point point, Queue<Point> queue, boolean[][] hits) {
        if (canvas.isValidPoint(point) && !hits[point.getX()][point.getY()]) {
            queue.add(point);
        }
    }

    protected PointRenderer getPointRenderer(Canvas canvas) {
        return new PointRenderer(canvas);
    }
}
