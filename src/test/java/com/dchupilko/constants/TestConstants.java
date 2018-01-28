package com.dchupilko.constants;

public class TestConstants {
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final String CREATE_CANVAS = "C 20 4";
    public static final String DRAW_LINE_HORIZ = "L 1 2 6 2";
    public static final String DRAW_LINE_VERT = "L 6 3 6 4";
    public static final String DRAW_RECTANGLE = "R 14 1 18 3";
    public static final String FILL_AREA = "B 10 3 o";
    public static final String QUIT = "Q";
    public static final String UNKNOWN_ACTION = "W";

    public static final String CANVAS_20_4 =
            "----------------------\n" +
            "|                    |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------\r\n";

    public static final String LINE_1_2_6_2 =
            "----------------------\n" +
            "|                    |\n" +
            "|xxxxxx              |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------\r\n";

    public static final String LINE_6_3_6_4 =
            "----------------------\n" +
            "|                    |\n" +
            "|xxxxxx              |\n" +
            "|     x              |\n" +
            "|     x              |\n" +
            "----------------------\r\n";

    public static final String RECT_14_1_18_3 =
            "----------------------\n" +
            "|             xxxxx  |\n" +
            "|xxxxxx       x   x  |\n" +
            "|     x       xxxxx  |\n" +
            "|     x              |\n" +
            "----------------------\r\n";

    public static final String FILL_10_3_O =
            "----------------------\n" +
            "|oooooooooooooxxxxxoo|\n" +
            "|xxxxxxooooooox   xoo|\n" +
            "|     xoooooooxxxxxoo|\n" +
            "|     xoooooooooooooo|\n" +
            "----------------------\r\n";
}
