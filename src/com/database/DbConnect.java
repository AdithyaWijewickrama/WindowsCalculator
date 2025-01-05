package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DbConnect {

    public static Connection connect() {

        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:CalculatorDB.sqlite");
        } catch (Exception e) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, e);

            JOptionPane.showMessageDialog(null, e);
        }

        return conn;

    }

}
