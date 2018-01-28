package com.dchupilko.constants;

public class Constants {
    public static final char COLOR_X = 'x';
    public static final char COLOR_O = 'o';

    public static final String PLEASE_CREATE_CANVAS_FIRST = "Please create canvas first";
    public static final String CANVAS_WIDTH_AND_HEIGHT_MUST_BE_POSITIVE = "Canvas width and height must be positive";
    public static final String COORDINATES_MUST_BE_POSITIVE = "Coordinates must be positive";
    public static final String COORDINATE_IS_OUT_OF_CANVAS_BOUNDS = "Coordinate (%s,%s) is out of canvas bounds";
    public static final String ONLY_HORIZONTAL_AND_VERTICAL_LINES_ARE_SUPPORTED = "Only horizontal or vertical lines are supported";
    public static final String UNRECOGNIZED_INPUT = "Unrecognized input";

    public static final String WELCOME_MSG = "This is a Drawing Program. Enter a command or press ? for help.";
    public static final String LINE_PREFIX = "Enter command: ";
    public static final String README =
            "C w h           Creates a new canvas of width w and height h.\n" +
            "L x1 y1 x2 y2   Creates a new line from (x1,y1) to (x2,y2).\n" +
            "R x1 y1 x2 y2   Creates a new rectangle with upper left corner (x1,y1) and lower right corner (x2,y2).\n" +
            "B x y c         Fills the entire area connected to (x,y) with \"color\" c.\n" +
            "Q               Quits the program.";
}
