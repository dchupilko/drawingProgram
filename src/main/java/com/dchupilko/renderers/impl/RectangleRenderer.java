package com.dchupilko.renderers.impl;

import com.dchupilko.constants.Constants;
import com.dchupilko.draw.Canvas;
import com.dchupilko.renderers.AbstractRenderer;
import com.dchupilko.shapes.Line;
import com.dchupilko.shapes.Rectangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RectangleRenderer extends AbstractRenderer<Rectangle> {
    private static final Logger LOGGER = LogManager.getLogger(RectangleRenderer.class);

    private LineRenderer lineRenderer;

    public RectangleRenderer(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Renders a rectangle on a canvas by drawing points at given coordinates.
     * @param rectangle Rectangle
     * @param color Color
     */
    @Override
    public void render(Rectangle rectangle, char color) {
        LOGGER.debug("Rendering rectangle: " + rectangle + " with color " + color);

        canvas.addRectangle(rectangle);
        lineRenderer = getLineRenderer(canvas);

        Line topSide = new Line(rectangle.getUpperLeft(), rectangle.getUpperRight());
        Line bottomSide = new Line(rectangle.getLowerLeft(), rectangle.getLowerRight());
        Line leftSide = new Line(rectangle.getUpperLeft(), rectangle.getLowerLeft());
        Line rightSide = new Line(rectangle.getUpperRight(), rectangle.getLowerRight());

        lineRenderer.render(topSide);
        lineRenderer.render(bottomSide);
        lineRenderer.render(leftSide);
        lineRenderer.render(rightSide);
    }

    @Override
    public void render(Rectangle rectangle) {
        render(rectangle, Constants.COLOR_X);
    }

    @Override
    public void renderAll() {
        for (Rectangle rectangle : canvas.getRectangles()) {
            render(rectangle);
        }
    }

    protected LineRenderer getLineRenderer(Canvas canvas) {
        return new LineRenderer(canvas);
    }
}
