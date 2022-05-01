package com.codes;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Ui {

    public static String wrapText(String string, int maxLineWidth, String align, JTextField tf) {
        String returnString = "<html> <p align=\"" + align + "\">";
        FontMetrics metrics = tf.getFontMetrics(new Font("Dialog", Font.PLAIN, 15));
        int lineWidth = 0;
        StringTokenizer tokenizer = new StringTokenizer(string, " ");
        while (tokenizer.hasMoreElements()) {
            String word = (String) tokenizer.nextElement();
            int stringWidth = metrics.stringWidth(word);
            if (stringWidth + lineWidth >= maxLineWidth) {
                returnString = (returnString + "<br>" + word);
                lineWidth = 0;
            } else {
                returnString = (returnString + " " + word + "");
            }
            lineWidth += stringWidth;
        }
        returnString = (returnString + "</p></html>");
        return returnString;
    }

    public static String wrapText(String string, int maxLineWidth, String align) {
        String r = "";
        String word = "";
        for (char c : string.toCharArray()) {
            word = word + c;
            if (word.length() > maxLineWidth) {
                r = r+ word+"<br>";
                word="";
            }
        }
        r = ("<html> <p align=\"" + align + "\">" + r + "</p></html>");
        System.out.println(r);
        return r;
    }

    public static void adaptLabelFont(JTextField l, int min, int max) {
        if (l.getGraphics() == null) {
            return;
        }
        int currFontSize = l.getFont().getSize();
        Rectangle r = l.getBounds();
        r.x = 0;
        r.y = 0;
        int fontSize = Math.max(min, currFontSize);
        Font f = l.getFont();
        Rectangle r1 = new Rectangle(getTextSize(l, l.getFont()));
        while (!r.contains(r1)) {
            fontSize--;
            if (fontSize <= min) {
                break;
            }
            r1 = new Rectangle(getTextSize(l, f.deriveFont(f.getStyle(), fontSize)));
        }
        Rectangle r2 = new Rectangle();
        while (fontSize < max) {
            r2.setSize(getTextSize(l, f.deriveFont(f.getStyle(), fontSize + 1)));
            if (!r.contains(r2)) {
                break;
            }
            fontSize++;
        }
        l.setFont(f.deriveFont(f.getStyle(), fontSize));
        l.repaint();
    }

    public static Dimension getTextSize(JTextField l, Font f) {
        Dimension size = new Dimension();
        FontMetrics fm = l.getGraphics().getFontMetrics(f);
        size.width = fm.stringWidth(l.getText());
        size.height = fm.getHeight();
        return size;
    }
}
