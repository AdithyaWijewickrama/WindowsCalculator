package com.calculator.commoncalculator.button;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;

public enum ButtonUi {
    NUMBER(Color.BLACK, Font.getFont(""), true, null),
    OPARATOR(Color.BLACK, Font.getFont(""), true, null),
    ACTION(Color.BLACK, Font.getFont(""), true, null);
    public final Color BACKGROUND;
    public final Font FONT;
    public final boolean FONT_RESIZING;
    public final Border BORDER;

    private ButtonUi(Color background, Font font, boolean fontResizing, Border border) {
        BACKGROUND = background;
        FONT = font;
        FONT_RESIZING = fontResizing;
        BORDER = border;
    }
}
