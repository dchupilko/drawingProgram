package com.dchupilko.renderers.impl;

import com.dchupilko.draw.Canvas;
import com.dchupilko.shapes.Line;
import com.dchupilko.shapes.Point;
import org.junit.Test;

import static com.dchupilko.constants.Constants.*;
import static org.mockito.Mockito.*;

public class FillAreaRendererTest {

    @Test
    public void render_onEmptyCanvas() {
        Canvas canvas = spy(new Canvas(5, 5));
        FillAreaRenderer fillAreaRenderer = spy(new FillAreaRenderer(canvas));
        PointRenderer pointRendererMock = mock(PointRenderer.class);
        doReturn(pointRendererMock).when(fillAreaRenderer).getPointRenderer(canvas);
        Point point = new Point(0, 0);

        fillAreaRenderer.render(point, COLOR_O);

        verify(canvas).addAreaToFill(point, COLOR_O);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                verify(pointRendererMock).render(new Point(i, j), COLOR_O);
            }
        }
    }

    @Test
    public void render_onCanvasWithLine() {
        Canvas canvas = spy(new Canvas(5, 5));
        FillAreaRenderer fillAreaRenderer = spy(new FillAreaRenderer(canvas));
        PointRenderer pointRendererMock = mock(PointRenderer.class);
        doReturn(pointRendererMock).when(fillAreaRenderer).getPointRenderer(canvas);
        Point point = new Point(0, 0);
        LineRenderer lineRenderer = new LineRenderer(canvas);

        lineRenderer.render(new Line(new Point(0, 2), new Point(4, 2)));
        fillAreaRenderer.render(point, COLOR_O);

        verify(canvas).addAreaToFill(point, COLOR_O);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                verify(pointRendererMock).render(new Point(i, j), COLOR_O);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                verify(pointRendererMock, never()).render(new Point(i, j), COLOR_O);
            }
        }
    }
}
