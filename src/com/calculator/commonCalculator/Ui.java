package com.calculator.commonCalculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Ui {

    public static Color APP_HOVER = new Color(51,51,51);
    public static Color SPECIAL_COLOR = new Color(0,155,210);

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
                r = r + word + "<br>";
                word = "";
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
        Dimension size = new Dimension(10,20);
        try {
            FontMetrics fm = l.getGraphics().getFontMetrics(f);
            size.width = fm.stringWidth(l.getText());
            size.height = fm.getHeight();
        } catch (Exception e) {
        }
        return size;
    }

    public static void setNumberTexting(JTextField t, JButton b) {
        b.setVisible(false);
        b.setBorder(null);
        t.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                t.setSelectionStart(0);
                t.setSelectionEnd(t.getText().length());
                b.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Double.valueOf(t.getText());
                } catch (NumberFormatException ex) {
                    for (ActionListener l : t.getActionListeners()) {
                        l.actionPerformed(null);
                    }
                }
                b.setVisible(false);
            }
        });
        t.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Dimension textSize = getTextSize(t, t.getFont());
                textSize.setSize(textSize.width + 20, textSize.height + 4);
                t.setPreferredSize(textSize);
                t.setMinimumSize(textSize);
                t.setMaximumSize(textSize);
                t.getParent().getParent().revalidate();
                t.getParent().getParent().repaint();
            }
        });
        b.addActionListener((ActionEvent e) -> {
            t.setText("0");
        });
    }

    public static void setSize(JComponent comp, int w, int h) {
        comp.setPreferredSize(new Dimension(w, h));
        comp.setMaximumSize(new Dimension(w, h));
        comp.setMinimumSize(new Dimension(w, h));
    }
    public static void setSize(JDialog comp, int w, int h) {
        comp.setPreferredSize(new Dimension(w, h));
        comp.setMaximumSize(new Dimension(w, h));
        comp.setMinimumSize(new Dimension(w, h));
    }
    public static void setSize(Frame comp, int w, int h) {
        comp.setPreferredSize(new Dimension(w, h));
        comp.setMaximumSize(new Dimension(w, h));
        comp.setMinimumSize(new Dimension(w, h));
    }

    public static int wrapHeight(JTextArea textArea) {
        String s = textArea.getText();
        int tw=textArea.getWidth()-textArea.getInsets().left-textArea.getInsets().right;
        int w = textArea.getGraphics().getFontMetrics(textArea.getFont()).stringWidth(s);
        int h = textArea.getGraphics().getFontMetrics(textArea.getFont()).getHeight();
        if (tw <= 0) {
            return -1;
        }
        return h > 0 ? h * ((int) Math.ceil(w / tw)) : h;
    }

}
