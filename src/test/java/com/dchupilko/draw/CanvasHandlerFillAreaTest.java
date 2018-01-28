package com.dchupilko.draw;

import com.dchupilko.renderers.impl.FillAreaRenderer;
import com.dchupilko.shapes.Point;
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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CanvasHandlerFillAreaTest {
    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Spy
    private CanvasHandler canvasHandler = new CanvasHandler();

    @Mock
    private FillAreaRenderer fillAreaRendererMock;

    @Before
    public void setUp() {
        Canvas canvas = new Canvas(20, 4);
        doReturn(canvas).when(canvasHandler).getCanvas();
        doReturn(fillAreaRendererMock).when(canvasHandler).getFillAreaRenderer(canvas);
    }

    @Test
    public void fillArea() {
        Point point = canvasHandler.fillArea(10, 3, COLOR_O);

        assertEquals(9, point.getX());
        assertEquals(2, point.getY());
        verify(fillAreaRendererMock).render(point, COLOR_O);
    }

    @Test
    public void fillArea_createCanvasFirst() {
        doReturn(null).when(canvasHandler).getCanvas();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(PLEASE_CREATE_CANVAS_FIRST);

        canvasHandler.fillArea(10, 3, COLOR_O);
    }

    @Test
    public void fill_coordinatesMustBePositive() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(COORDINATES_MUST_BE_POSITIVE);

        canvasHandler.fillArea(10, 0, COLOR_O);
    }

    @Test
    public void fillArea_pointIsOutOfCanvasBounds() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(String.format(COORDINATE_IS_OUT_OF_CANVAS_BOUNDS, 10, 7));

        canvasHandler.fillArea(10, 7, COLOR_O);
    }
}
