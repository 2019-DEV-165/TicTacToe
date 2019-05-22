package com.katatictactoe;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GameBoardPosition {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({INDEX_TOP_LEFT, INDEX_TOP_MIDDLE, INDEX_TOP_RIGHT, INDEX_CENTER_LEFT, INDEX_CENTER_MIDDLE, INDEX_CENTER_RIGHT, INDEX_BOTTOM_LEFT, INDEX_BOTTOM_MIDDLE, INDEX_BOTTOM_RIGHT})

    public @interface Position {

    }

    public static final int INDEX_TOP_LEFT = 0;
    public static final int INDEX_TOP_MIDDLE = 1;
    public static final int INDEX_TOP_RIGHT = 2;
    public static final int INDEX_CENTER_LEFT = 3;
    public static final int INDEX_CENTER_MIDDLE = 4;
    public static final int INDEX_CENTER_RIGHT = 5;
    public static final int INDEX_BOTTOM_LEFT = 6;
    public static final int INDEX_BOTTOM_MIDDLE = 7;
    public static final int INDEX_BOTTOM_RIGHT = 8;
}
