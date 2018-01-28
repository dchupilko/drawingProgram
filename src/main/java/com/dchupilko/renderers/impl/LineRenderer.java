package com.dchupilko.renderers.impl;

import com.dchupilko.constants.Constants;
import com.dchupilko.draw.Canvas;
import com.dchupilko.renderers.AbstractRenderer;
import com.dchupilko.shapes.Line;
import com.dchupilko.shapes.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LineRenderer extends AbstractRenderer<Line> {
    private static final Logger LOGGER = LogManager.getLogger(LineRenderer.class);

    private PointRenderer pointRenderer;

    public LineRenderer(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Renders a line on a canvas by drawing points at given coordinates.
     * If the line is horizontal then Y coordinate is constant.
     * If the line is vertical then X coordinate is constant.
     * @param line Line
     * @param color Color
     */
    @Override
    public void render(Line line, char color) {
        LOGGER.debug("Rendering line " + line + " with color " + color);

        canvas.addLine(line);
        pointRenderer = getPointRenderer(canvas);

        if (line.isHorizontal()) {
            int y = line.getFrom().getY();
            for (int x = line.getFrom().getX(); x <= line.getTo().getX(); x++) {
                pointRenderer.render(new Point(x, y));
            }
        } else if (line.isVertical()) {
            int x = line.getFrom().getX();
            for (int y = line.getFrom().getY(); y <= line.getTo().getY(); y++) {
                pointRenderer.render(new Point(x, y));
            }
        }
    }

    @Override
    public void render(Line line) {
        render(line, Constants.COLOR_X);
    }

    @Override
    public void renderAll() {
        for (Line line : canvas.getLines()) {
            render(line);
        }
    }

    protected PointRenderer getPointRenderer(Canvas canvas) {
        return new PointRenderer(canvas);
    }
}
