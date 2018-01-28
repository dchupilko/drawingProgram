package com.dchupilko.draw;

import com.dchupilko.renderers.impl.LineRenderer;
import com.dchupilko.shapes.Line;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.dchupilko.constants.Constants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CanvasHandlerDrawLineTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Spy
    CanvasHandler canvasHandler = new CanvasHandler();

    @Mock
    LineRenderer lineRendererMock;

    @Before
    public void setUp() {
        Canvas canvas = new Canvas(20, 4);
        doReturn(canvas).when(canvasHandler).getCanvas();
        doReturn(lineRendererMock).when(canvasHandler).getLineRenderer(canvas);
    }

    @Test
    public void drawLine_horizontalLine() {
        Line line = canvasHandler.drawLine(1, 2, 6, 2);

        assertEquals(0, line.getFrom().getX());
        assertEquals(1, line.getFrom().getY());
        assertEquals(5, line.getTo().getX());
        assertEquals(1, line.getTo().getY());
        assertTrue(line.isHorizontal());
        verify(lineRendererMock).render(line);
    }

    @Test
    public void drawLine_verticalLine() {
        Line line = canvasHandler.drawLine(2, 3, 2, 4);

        assertEquals(1, line.getFrom().getX());
        assertEquals(2, line.getFrom().getY());
        assertEquals(1, line.getTo().getX());
        assertEquals(3, line.getTo().getY());
        assertTrue(line.isVertical());
        verify(lineRendererMock).render(line);
    }

    @Test
    public void drawLine_swapEndPoints() {
        Line line = canvasHandler.drawLine(6, 2, 1, 2);

        assertEquals(0, line.getFrom().getX());
        assertEquals(1, line.getFrom().getY());
        assertEquals(5, line.getTo().getX());
        assertEquals(1, line.getTo().getY());
        assertTrue(line.isHorizontal());
        verify(lineRendererMock).render(line);
    }

    @Test
    public void drawLine_createCanvasFirst() {
        doReturn(null).when(canvasHandler).getCanvas();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(PLEASE_CREATE_CANVAS_FIRST);

        canvasHandler.drawLine(2, 3, 2, 4);
    }

    @Test
    public void drawLine_coordinatesMustBePositive() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(COORDINATES_MUST_BE_POSITIVE);

        canvasHandler.drawLine(-2, 3, 0, 4);
    }

    @Test
    public void drawLine_firstPointIsOutOfCanvasBounds() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(String.format(COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, 30, 5));

        canvasHandler.drawLine(30, 5, 3, 8);
    }

    @Test
    public void drawLine_secondPointIsOutOfCanvasBounds() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(String.format(COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, 3, 80));

        canvasHandler.drawLine(3, 2, 3, 80);
    }

    @Test
    public void drawLine_onlyHorizontalAndVerticalAreSupported() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ONLY_HORIZONTAL_AND_VERTICAL_LINES_ARE_SUPPORTED);

        canvasHandler.drawLine(1, 2, 3, 4);
    }
}
