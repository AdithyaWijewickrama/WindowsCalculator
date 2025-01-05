package com.calculator.commonCalculator.popupPanel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;

/**
 *
 * @author AW Developer
 */
public class PopupButton extends javax.swing.JButton {

    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;
    private PopupPanel popup;
    private int align;

    public PopupButton(PopupPanel popup, int align, String text, Icon icon) {
        super(text, icon);
        this.popup = popup;
        this.align = align;
        initComponents();
    }

    public PopupButton(PopupPanel popup, int align, String text) {
        super(text);
        this.popup = popup;
        this.align = align;
        initComponents();
    }

    public PopupButton(PopupPanel popup) {
        this();
        this.popup = popup;
        initComponents();
    }

    public PopupButton() {
        this.align = CENTER;
        initComponents();
    }

    public PopupPanel getPopup() {
        return popup;
    }

    public void setPopup(PopupPanel popup) {
        if (this.popup != null) {
            if (this.popup.isShowing()) {
                try {
                    throw new Exception("Cannot change the window while its showing");
                } catch (Exception ex) {
                    Logger.getLogger(PopupButton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.popup = popup;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
        if (this.popup.isShowing()) {
            int x = getLocationOnScreen().x;
            int w = getWidth();
            int h = getHeight();
            int y = getLocationOnScreen().y + h;
            switch (align) {
                case ALIGN_LEFT:
                    x = x - popup.getWidth() - w;
                    break;
                case ALIGN_CENTER:
                    x = x + w / 2 - popup.getWidth() / 2;
                    break;
            }
            System.out.println(x+" "+y);
            popup.setLocation(x, y);
        }
    }
                     
    private void initComponents() {
        setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formActionPerformed(evt);
            }
        });
    }               

    private void formActionPerformed(java.awt.event.ActionEvent evt) {                                     
        popup.setVisible(true);
        setAlign(align);
    }                                              
}
