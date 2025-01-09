package com.calculator.graphing;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Graphical {
    public static ImageIcon getImage(String path,int w,int h){
        ImageIcon i1 = new ImageIcon(path);
        Image im1 = i1.getImage();
        Image im2 = im1.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(im2);
    }
    public static String getUIColorType(){
        return UIManager.getLookAndFeel()instanceof FlatDarkLaf?"white":"black";
    }
    
    public static Color getUIColor(){
        return !getUIColorType().equals("black")?new Color(50,50,50):new Color(255,255,255);
    }
}
