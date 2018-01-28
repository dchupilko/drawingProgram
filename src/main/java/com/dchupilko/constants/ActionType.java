package com.dchupilko.constants;

public enum ActionType {
    DEFAULT_VALUE('\0'),
    CREATE_CANVAS('C'),
    DRAW_LINE('L'),
    DRAW_RECTANGLE('R'),
    FILL_AREA('B'),
    QUIT('Q'),
    HELP('?');

    private char type;

    ActionType(char action) {
        this.type = action;
    }

    public char getType() {
        return type;
    }

    public static ActionType getValueByChar(char c) {
        for (ActionType actionType : values()) {
            if (actionType.getType() == c) {
                return actionType;
            }
        }
        return DEFAULT_VALUE;
    }
}
