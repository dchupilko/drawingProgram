package com.dchupilko.renderers.impl;

import com.dchupilko.draw.Canvas;
import com.dchupilko.shapes.Line;
import com.dchupilko.shapes.Point;
import com.dchupilko.shapes.Rectangle;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class RectangleRendererTest {

    @Test
    public void render() {
        Canvas canvasMock = mock(Canvas.class);
        RectangleRenderer rectangleRenderer = spy(new RectangleRenderer(canvasMock));
        LineRenderer lineRendererMock = mock(LineRenderer.class);
        doReturn(lineRendererMock).when(rectangleRenderer).getLineRenderer(canvasMock);
        Rectangle rectangle = new Rectangle(new Point(13, 0), new Point(17, 2));

        rectangleRenderer.render(rectangle);

        verify(canvasMock).addRectangle(rectangle);
        verify(lineRendererMock).render(new Line(new Point(13, 0), new Point(17, 0)));
        verify(lineRendererMock).render(new Line(new Point(13, 0), new Point(13, 2)));
        verify(lineRendererMock).render(new Line(new Point(13, 2), new Point(17, 2)));
        verify(lineRendererMock).render(new Line(new Point(17, 0), new Point(17, 2)));
    }
}
