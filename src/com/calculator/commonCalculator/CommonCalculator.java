package com.calculator.commoncalculator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CommonCalculator {

    public String name;
    public ImageIcon icon;
    public JPanel numberPanel;
    public JPanel keyPanel;

    public CommonCalculator(String name, ImageIcon icon) {
        this.name = name;
        this.icon = icon;
    }

}
