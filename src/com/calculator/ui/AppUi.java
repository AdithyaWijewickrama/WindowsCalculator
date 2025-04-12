package com.calculator.ui;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import javax.swing.LookAndFeel;

public class AppUi {

    public static Color APP_THEME = getAppColor();
    public static LookAndFeel lookAndFeel = getAppLookAndFeel();

    public static LookAndFeel getAppLookAndFeel() {
        return new FlatLightLaf();
    }

    public static Color getAppColor() {
        return Color.MAGENTA;
    }
}
