package com.dchupilko.renderers.impl;

import com.dchupilko.draw.Canvas;
import com.dchupilko.shapes.Line;
import com.dchupilko.shapes.Point;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LineRendererTest {

    @Test
    public void render() {
        Canvas canvasMock = mock(Canvas.class);
        LineRenderer lineRenderer = spy(new LineRenderer(canvasMock));
        PointRenderer pointRendererMock = mock(PointRenderer.class);
        doReturn(pointRendererMock).when(lineRenderer).getPointRenderer(canvasMock);
        Line line = new Line(new Point(0, 1), new Point(5, 1));

        lineRenderer.render(line);

        verify(canvasMock).addLine(line);
        verify(pointRendererMock).render(new Point(0, 1));
        verify(pointRendererMock).render(new Point(1, 1));
        verify(pointRendererMock).render(new Point(2, 1));
        verify(pointRendererMock).render(new Point(3, 1));
        verify(pointRendererMock).render(new Point(4, 1));
        verify(pointRendererMock).render(new Point(5, 1));
    }
}
