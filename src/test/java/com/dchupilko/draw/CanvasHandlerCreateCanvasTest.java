package com.dchupilko.draw;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;

import static com.dchupilko.constants.Constants.*;
import static org.junit.Assert.*;

public class CanvasHandlerCreateCanvasTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    CanvasHandler canvasHandler = new CanvasHandler();

    @Test
    public void createCanvas() {
        Canvas canvas = canvasHandler.createCanvas(20, 4);

        assertEquals(20, canvas.getWidth());
        assertEquals(4, canvas.getHeight());
    }

    @Test
    public void createCanvas_incorrectCanvasSize() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(CANVAS_WIDTH_AND_HEIGHT_MUST_BE_POSITIVE);

        canvasHandler.createCanvas(-5, 0);
    }
}
