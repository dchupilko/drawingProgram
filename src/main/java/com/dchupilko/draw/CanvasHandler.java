package com.dchupilko.draw;

import com.dchupilko.constants.Constants;
import com.dchupilko.renderers.impl.FillAreaRenderer;
import com.dchupilko.renderers.impl.LineRenderer;
import com.dchupilko.renderers.impl.RectangleRenderer;
import com.dchupilko.shapes.Line;
import com.dchupilko.shapes.Point;
import com.dchupilko.shapes.Rectangle;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CanvasHandler {
    private static final Logger LOGGER = LogManager.getLogger(CanvasHandler.class);

    private Canvas canvas;

    /**
     * Create an empty canvas with specified dimensions
     * @param width Canvas width
     * @param height Canvas height
     * @return Created canvas
     */
    protected Canvas createCanvas(int width, int height) {
        LOGGER.debug("createCanvas start, width=" + width + ", height=" + height);

        Validate.isTrue(width > 0 && height > 0, Constants.CANVAS_WIDTH_AND_HEIGHT_MUST_BE_POSITIVE);

        canvas = new Canvas(width, height);
        return canvas;
    }

    /**
     * Draws a line on canvas between specified start and end points
     * @param x1 X coordinate of start point
     * @param y1 Y coordinate of start point
     * @param x2 X coordinate of end point
     * @param y2 Y coordinate of end point
     * @return Created line
     */
    protected Line drawLine(int x1, int y1, int x2, int y2) {
        LOGGER.debug("drawLine start, x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2);

        Validate.notNull(getCanvas(), Constants.PLEASE_CREATE_CANVAS_FIRST);
        Validate.isTrue(x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0, Constants.COORDINATES_MUST_BE_POSITIVE);

        Point point1 = new Point(x1 - 1, y1 - 1);
        Validate.isTrue(getCanvas().isValidPoint(point1), String.format(Constants.COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, x1, y1));

        Point point2 = new Point(x2 - 1, y2 - 1);
        Validate.isTrue(getCanvas().isValidPoint(point2), String.format(Constants.COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, x2, y2));

        Line line = new Line(point1, point2);
        Validate.isTrue(line.isHorizontal() || line.isVertical(), Constants.ONLY_HORIZONTAL_AND_VERTICAL_LINES_ARE_SUPPORTED);

        getLineRenderer(getCanvas()).render(line);

        return line;
    }

    /**
     * Draws a rectangle on canvas between specified upper left and lower right corners
     * @param x1 X coordinate of upper left corner
     * @param y1 Y coordinate of upper left corner
     * @param x2 X coordinate of lower right conver
     * @param y2 Y coordinate of lower right corner
     * @return Created rectangle
     */
    protected Rectangle drawRectangle(int x1, int y1, int x2, int y2) {
        LOGGER.debug("drawRectangle start, x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2);

        Validate.notNull(getCanvas(), Constants.PLEASE_CREATE_CANVAS_FIRST);
        Validate.isTrue(x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0, Constants.COORDINATES_MUST_BE_POSITIVE);

        Point point1 = new Point(x1 - 1, y1 - 1);
        Validate.isTrue(getCanvas().isValidPoint(point1), String.format(Constants.COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, x1, y1));

        Point point2 = new Point(x2 - 1, y2 - 1);
        Validate.isTrue(getCanvas().isValidPoint(point2), String.format(Constants.COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, x2, y2));

        Rectangle rectangle = new Rectangle(point1, point2);

        getRectangleRenderer(getCanvas()).render(rectangle);

        return rectangle;
    }

    /**
     * Flood fills an area on canvas around specified point with color
     * @param x X coordinate of a point
     * @param y Y coordinate of a point
     * @param color Color represented by a single character
     * @return Created point
     */
    protected Point fillArea(int x, int y, char color) {
        LOGGER.debug("fillArea start, x=" + x + ", y=" + y + ", color=" + color);

        Validate.notNull(getCanvas(), Constants.PLEASE_CREATE_CANVAS_FIRST);
        Validate.isTrue(x > 0 && y > 0, Constants.COORDINATES_MUST_BE_POSITIVE);

        Point point = new Point(x - 1, y - 1);
        Validate.isTrue(getCanvas().isValidPoint(point), String.format(Constants.COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, x, y));

        getFillAreaRenderer(getCanvas()).render(point, color);

        return point;
    }

    protected Canvas getCanvas() {
        return canvas;
    }

    protected LineRenderer getLineRenderer(Canvas canvas) {
        return new LineRenderer(canvas);
    }

    protected RectangleRenderer getRectangleRenderer(Canvas canvas) {
        return new RectangleRenderer(canvas);
    }

    protected FillAreaRenderer getFillAreaRenderer(Canvas canvas) {
        return new FillAreaRenderer(canvas);
    }
}
