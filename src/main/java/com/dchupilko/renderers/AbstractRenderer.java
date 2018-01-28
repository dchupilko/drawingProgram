package com.dchupilko.renderers;

import com.dchupilko.draw.Canvas;
import com.dchupilko.shapes.IShape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractRenderer<T extends IShape> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractRenderer.class);

    protected Canvas canvas;

    public abstract void render(T shape, char color);

    public abstract void render(T shape);

    public abstract void renderAll();

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
