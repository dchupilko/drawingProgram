package com.dchupilko.draw;

import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;

import java.util.Scanner;

import static com.dchupilko.constants.Constants.*;
import static com.dchupilko.constants.TestConstants.*;
import static org.mockito.Mockito.*;

public class InputParserTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().muteForSuccessfulTests();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void parseInput_shouldCreateCanvas() {
        Scanner scanner = new Scanner(CREATE_CANVAS);
        InputParser parser = spy(new InputParser());
        CanvasHandler canvasHandler = spy(new CanvasHandler());
        doReturn(canvasHandler).when(parser).getCanvasHandler();

        parser.parseInput(scanner);

        verify(canvasHandler).createCanvas(20, 4);
        Assert.assertEquals(CANVAS_20_4, systemOutRule.getLog().replaceAll(LINE_PREFIX, ""));
    }

    @Test
    public void parseInput_shouldDrawLine() {
        Scanner scanner = new Scanner(CREATE_CANVAS +
                NEW_LINE +
                DRAW_LINE_HORIZ);
        InputParser parser = spy(new InputParser());
        CanvasHandler canvasHandler = spy(new CanvasHandler());
        doReturn(canvasHandler).when(parser).getCanvasHandler();

        parser.parseInput(scanner);

        verify(canvasHandler).createCanvas(20, 4);
        verify(canvasHandler).drawLine(1, 2, 6, 2);
        Assert.assertEquals(CANVAS_20_4 + LINE_1_2_6_2, systemOutRule.getLog().replaceAll(LINE_PREFIX, ""));
    }

    @Test
    public void parseInput_shouldDrawTwoLines() {
        Scanner scanner = new Scanner(CREATE_CANVAS +
                NEW_LINE +
                DRAW_LINE_HORIZ +
                NEW_LINE +
                DRAW_LINE_VERT);
        InputParser parser = spy(new InputParser());
        CanvasHandler canvasHandler = spy(new CanvasHandler());
        doReturn(canvasHandler).when(parser).getCanvasHandler();

        parser.parseInput(scanner);

        verify(canvasHandler).createCanvas(20, 4);
        verify(canvasHandler).drawLine(1, 2, 6, 2);
        verify(canvasHandler).drawLine(6, 3, 6, 4);
        Assert.assertEquals(CANVAS_20_4 + LINE_1_2_6_2 + LINE_6_3_6_4, systemOutRule.getLog().replaceAll(LINE_PREFIX, ""));
    }

    @Test
    public void parseInput_shouldDrawTwoLinesAndRectangle() {
        Scanner scanner = new Scanner(CREATE_CANVAS +
                NEW_LINE +
                DRAW_LINE_HORIZ +
                NEW_LINE +
                DRAW_LINE_VERT +
                NEW_LINE +
                DRAW_RECTANGLE);
        InputParser parser = spy(new InputParser());
        CanvasHandler canvasHandler = spy(new CanvasHandler());
        doReturn(canvasHandler).when(parser).getCanvasHandler();

        parser.parseInput(scanner);

        verify(canvasHandler).createCanvas(20, 4);
        verify(canvasHandler).drawLine(1, 2, 6, 2);
        verify(canvasHandler).drawLine(6, 3, 6, 4);
        verify(canvasHandler).drawRectangle(14, 1, 18, 3);
        Assert.assertEquals(CANVAS_20_4 + LINE_1_2_6_2 + LINE_6_3_6_4 + RECT_14_1_18_3, systemOutRule.getLog().replaceAll(LINE_PREFIX, ""));
    }

    @Test
    public void parseInput_shouldDrawTwoLinesAndRectangleAndFillArea() {
        Scanner scanner = new Scanner(CREATE_CANVAS +
                NEW_LINE +
                DRAW_LINE_HORIZ +
                NEW_LINE +
                DRAW_LINE_VERT +
                NEW_LINE +
                DRAW_RECTANGLE +
                NEW_LINE +
                FILL_AREA);
        InputParser parser = spy(new InputParser());
        CanvasHandler canvasHandler = spy(new CanvasHandler());
        doReturn(canvasHandler).when(parser).getCanvasHandler();

        parser.parseInput(scanner);

        verify(canvasHandler).createCanvas(20, 4);
        verify(canvasHandler).drawLine(1, 2, 6, 2);
        verify(canvasHandler).drawLine(6, 3, 6, 4);
        verify(canvasHandler).drawRectangle(14, 1, 18, 3);
        verify(canvasHandler).fillArea(10, 3, COLOR_O);
        Assert.assertEquals(CANVAS_20_4 + LINE_1_2_6_2 + LINE_6_3_6_4 + RECT_14_1_18_3 + FILL_10_3_O, systemOutRule.getLog().replaceAll(LINE_PREFIX, ""));
    }

    @Test
    public void parseInput_shouldQuit() {
        Scanner scanner = new Scanner(QUIT);
        InputParser parser = spy(new InputParser());

        exit.expectSystemExit();
        parser.parseInput(scanner);
    }

    @Test
    public void parseInput_unrecognizedAction() {
        Scanner scanner = new Scanner(UNKNOWN_ACTION);
        InputParser parser = spy(new InputParser());

        parser.parseInput(scanner);

        Assert.assertEquals(UNRECOGNIZED_INPUT + NEW_LINE, systemOutRule.getLog().replaceAll(LINE_PREFIX, ""));
    }
}
