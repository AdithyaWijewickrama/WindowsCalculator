package com.calculator.ui.memory;

import static com.database.Sql.getValueS;
import java.sql.SQLException;

public class Previous {

     public static String CALCULATOR_TYPE=getValueS("SELECT value FROM defaults WHERE name='caltype'");
    public static int WIDTH=Integer.parseInt(getValueS("SELECT value FROM defaults WHERE name='width'"));
    public static int HEIGHT=Integer.parseInt(getValueS("SELECT value FROM defaults WHERE name='height'"));
    public static int X_ON_SCREEN=Integer.parseInt(getValueS("SELECT value FROM defaults WHERE name='xOnScreen'"));
    public static int Y_ON_SCREEN=Integer.parseInt(getValueS("SELECT value FROM defaults WHERE name='yOnScreen'"));

    public static void updateData() throws SQLException {
        CALCULATOR_TYPE = getValueS("SELECT value FROM defaults WHERE name='caltype'");
        WIDTH = Integer.parseInt(getValueS("SELECT value FROM defaults WHERE name='width'"));
        HEIGHT = Integer.parseInt(getValueS("SELECT value FROM defaults WHERE name='height'"));
        X_ON_SCREEN = Integer.parseInt(getValueS("SELECT value FROM defaults WHERE name='xOnScreen'"));
        Y_ON_SCREEN = Integer.parseInt(getValueS("SELECT value FROM defaults WHERE name='yOnScreen'"));
    }
}
