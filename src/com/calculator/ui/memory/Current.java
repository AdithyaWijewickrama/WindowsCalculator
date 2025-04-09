package com.calculator.ui.memory;

import com.database.Sql;
import java.sql.SQLException;

public class Current {
     public static String CALCULATOR_TYPE=Previous.CALCULATOR_TYPE;
    public static int WIDTH=Previous.WIDTH;
    public static int HEIGHT=Previous.HEIGHT;
    public static int X_ON_SCREEN=Previous.X_ON_SCREEN;
    public static int Y_ON_SCREEN=Previous.Y_ON_SCREEN;
    
    public static void updateData() throws SQLException{
        Sql.Execute("UPDATE defaults SET value="+Sql.getCovered(CALCULATOR_TYPE)+" WHERE name='type'");
        Sql.Execute("UPDATE defaults SET value="+Sql.getCovered(Double.toString(WIDTH))+" WHERE name='width'");
        Sql.Execute("UPDATE defaults SET value="+Sql.getCovered(Double.toString(HEIGHT))+" WHERE name='height'");
        Sql.Execute("UPDATE defaults SET value="+Sql.getCovered(Double.toString(X_ON_SCREEN))+" WHERE name='xOnScreen'");
        Sql.Execute("UPDATE defaults SET value="+Sql.getCovered(Double.toString(Y_ON_SCREEN))+" WHERE name='yOnScreen'");
    }
}
