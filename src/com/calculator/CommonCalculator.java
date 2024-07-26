/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.calculator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author AW Developer
 */
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
