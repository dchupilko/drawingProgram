package com.dchupilko.draw;

import com.dchupilko.renderers.impl.RectangleRenderer;
import com.dchupilko.shapes.Rectangle;
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

public class CanvasHandlerDrawRectangleTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Spy
    private CanvasHandler canvasHandler = new CanvasHandler();

    @Mock
    private RectangleRenderer rectangleRendererMock;

    @Before
    public void setUp() {
        Canvas canvas = new Canvas(20, 4);
        doReturn(canvas).when(canvasHandler).getCanvas();
        doReturn(rectangleRendererMock).when(canvasHandler).getRectangleRenderer(canvas);
    }

    @Test
    public void drawRectangle() {
        Rectangle rectangle = canvasHandler.drawRectangle(14, 1, 18, 3);

        assertEquals(13, rectangle.getUpperLeft().getX());
        assertEquals(0, rectangle.getUpperLeft().getY());
        assertEquals(17, rectangle.getLowerRight().getX());
        assertEquals(2, rectangle.getLowerRight().getY());
        assertEquals(13, rectangle.getLowerLeft().getX());
        assertEquals(2, rectangle.getLowerLeft().getY());
        assertEquals(17, rectangle.getUpperRight().getX());
        assertEquals(0, rectangle.getUpperRight().getY());
        verify(rectangleRendererMock).render(rectangle);
    }

    @Test
    public void drawRectangle_createCanvasFirst() {
        doReturn(null).when(canvasHandler).getCanvas();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(PLEASE_CREATE_CANVAS_FIRST);

        canvasHandler.drawRectangle(14, 1, 18, 3);
    }

    @Test
    public void drawRectangle_coordinatesMustBePositive() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(COORDINATES_MUST_BE_POSITIVE);

        canvasHandler.drawRectangle(-5, 1, 0, 3);
    }

    @Test
    public void drawRectangle_firstPointIsOutOfCanvasBounds() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(String.format(COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, 14, 5));

        canvasHandler.drawRectangle(14, 5, 18, 3);
    }

    @Test
    public void drawRectangle_secondPointIsOutOfCanvasBounds() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(String.format(COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, 25, 3));

        canvasHandler.drawRectangle(14, 1, 25, 3);
    }
}
