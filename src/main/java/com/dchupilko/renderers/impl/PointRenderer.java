package com.dchupilko.renderers.impl;

import com.dchupilko.constants.Constants;
import com.dchupilko.draw.Canvas;
import com.dchupilko.renderers.AbstractRenderer;
import com.dchupilko.shapes.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PointRenderer extends AbstractRenderer<Point> {
    private static final Logger LOGGER = LogManager.getLogger(PointRenderer.class);

    public PointRenderer(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Renders a point on a canvas by specified coordinates and color
     * @param point Point
     * @param color Color
     */
    @Override
    public void render(Point point, char color) {
        LOGGER.debug("Rendering point " + point + " with color " + color);

        canvas.addPoint(point);
        canvas.getField()[point.getX()][point.getY()] = color;
    }

    @Override
    public void render(Point point) {
        render(point, Constants.COLOR_X);
    }

    @Override
    public void renderAll() {
        for (Point point : canvas.getPoints()) {
            render(point);
        }
    }
}
