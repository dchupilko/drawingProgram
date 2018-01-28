package com.dchupilko.renderers.impl;

import com.dchupilko.constants.Constants;
import com.dchupilko.draw.Canvas;
import com.dchupilko.shapes.Point;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PointRendererTest {

    @Test
    public void render() {
        Canvas canvasMock = mock(Canvas.class);
        PointRenderer pointRenderer = spy(new PointRenderer(canvasMock));
        char[][] field = new char[20][4];
        when(canvasMock.getField()).thenReturn(field);
        Point point = new Point(3, 2);

        pointRenderer.render(point);

        verify(canvasMock).addPoint(point);
        Assert.assertEquals(Constants.COLOR_X, field[3][2]);
    }
}
