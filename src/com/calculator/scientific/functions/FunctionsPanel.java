package com.calculator.scientific.functions;

import static com.amath.advacedmath.tokenizing.Token.ABS;
import static com.amath.advacedmath.tokenizing.Token.ACOS;
import static com.amath.advacedmath.tokenizing.Token.ACOSH;
import static com.amath.advacedmath.tokenizing.Token.ACOT;
import static com.amath.advacedmath.tokenizing.Token.ACOTH;
import static com.amath.advacedmath.tokenizing.Token.ACSC;
import static com.amath.advacedmath.tokenizing.Token.ACSCH;
import static com.amath.advacedmath.tokenizing.Token.ASEC;
import static com.amath.advacedmath.tokenizing.Token.ASECH;
import static com.amath.advacedmath.tokenizing.Token.ASIN;
import static com.amath.advacedmath.tokenizing.Token.ASINH;
import static com.amath.advacedmath.tokenizing.Token.ATAN;
import static com.amath.advacedmath.tokenizing.Token.ATANH;
import static com.amath.advacedmath.tokenizing.Token.CEILING;
import static com.amath.advacedmath.tokenizing.Token.COS;
import static com.amath.advacedmath.tokenizing.Token.COSH;
import static com.amath.advacedmath.tokenizing.Token.COT;
import static com.amath.advacedmath.tokenizing.Token.COTH;
import static com.amath.advacedmath.tokenizing.Token.CSC;
import static com.amath.advacedmath.tokenizing.Token.CSCH;
import static com.amath.advacedmath.tokenizing.Token.DEGREES;
import static com.amath.advacedmath.tokenizing.Token.FLOOR;
import static com.amath.advacedmath.tokenizing.Token.SEC;
import static com.amath.advacedmath.tokenizing.Token.SECH;
import static com.amath.advacedmath.tokenizing.Token.SIN;
import static com.amath.advacedmath.tokenizing.Token.SINH;
import static com.amath.advacedmath.tokenizing.Token.TAN;
import static com.amath.advacedmath.tokenizing.Token.TANH;
import com.amath.advacedmath.tokenizing.TokenList;
import com.calculator.commoncalculator.button.ButtonLayout;
import com.calculator.commoncalculator.CommonKeyPanel;
import com.calculator.commoncalculator.CommonNumberPanel;
import com.calculator.ui.Ui;
import static com.calculator.commoncalculator.button.DefaultButtons.DMS;
import static com.calculator.commoncalculator.button.DefaultButtons.RAND;
import com.calculator.commoncalculator.popupPanel.PopupButton;
import com.calculator.commoncalculator.popupPanel.PopupPanel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FunctionsPanel extends javax.swing.JPanel {

    CommonKeyPanel triag;
    CommonKeyPanel atriag;
    CommonKeyPanel triagh;
    CommonKeyPanel atriagh;
    CommonKeyPanel other;
    PopupPanel triagDialog;
    PopupButton triagButton;
    PopupPanel otherDialog;
    PopupButton otherButton;

    public FunctionsPanel(CommonNumberPanel numberPanel) {
        initComponents();
        try {
            triag = new CommonKeyPanel(numberPanel, new TokenList(SIN, COS, TAN, CSC, SEC, COT), "###\n###", ButtonLayout.ROW);
            atriag = new CommonKeyPanel(numberPanel, new TokenList(ASIN, ACOS, ATAN, ACSC, ASEC, ACOT), "###\n###", ButtonLayout.ROW);
            triagh = new CommonKeyPanel(numberPanel, new TokenList(SINH, COSH, TANH, CSCH, SECH, COTH), "###\n###", ButtonLayout.ROW);
            atriagh = new CommonKeyPanel(numberPanel, new TokenList(ASINH, ACOSH, ATANH, ACSCH, ASECH, ACOTH), "###\n###", ButtonLayout.ROW);
            other = new CommonKeyPanel(numberPanel, new TokenList(ABS, FLOOR, CEILING, RAND, DMS, DEGREES), "###\n###", ButtonLayout.ROW);
            Ui.setSize(triag, 180, 100);
            Ui.setSize(atriag, 180, 100);
            Ui.setSize(triagh, 180, 100);
            Ui.setSize(atriagh, 180, 100);
            Ui.setSize(other, 180, 100);
        } catch (Exception ex) {
            Logger.getLogger(FunctionsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        triagDialog = new PopupPanel();
        otherDialog = new PopupPanel();
        Ui.setSize(triagDialog, 240, 100);
        Ui.setSize(otherDialog, 180, 100);
        triagButton = new PopupButton(triagDialog, PopupButton.ALIGN_RIGHT, "Triangnometry ▼");
        otherButton = new PopupButton(otherDialog, PopupButton.ALIGN_CENTER, "Functions ▼");
        triagButton.setBorder(null);
        otherButton.setBorder(null);
        Ui.setSize(triagButton, 130, 30);
        Ui.setSize(otherButton, 120, 30);
        add(triagButton);
        add(otherButton);
        triagDialog.addComponent(jPanel2);
        triagDialog.addComponent(triag);
        otherDialog.addComponent(other);
    }

    public void setTriagPanel() {
        boolean secnd = jToggleButton1.isSelected();
        boolean hyp = jToggleButton2.isSelected();
        CommonKeyPanel p;
        if (!secnd && !hyp) {
            p = triag;
        } else if (secnd && !hyp) {
            p = atriag;
        } else if (!secnd && hyp) {
            p = triagh;
        } else {
            p = atriagh;
        }
        System.out.println(secnd + " " + hyp);
        triagDialog.clearAll();
        triagDialog.addComponent(jPanel2);
        triagDialog.addComponent(p);
        triag.doLayout();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jToggleButton1.setText("2nd");
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setMaximumSize(new java.awt.Dimension(60, 50));
        jToggleButton1.setMinimumSize(new java.awt.Dimension(40, 30));
        jToggleButton1.setPreferredSize(new java.awt.Dimension(40, 30));
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTriagnoms(evt);
            }
        });
        jPanel2.add(jToggleButton1);

        jToggleButton2.setText("hyp");
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.setMaximumSize(new java.awt.Dimension(60, 50));
        jToggleButton2.setMinimumSize(new java.awt.Dimension(40, 30));
        jToggleButton2.setPreferredSize(new java.awt.Dimension(40, 30));
        jToggleButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTriagnoms(evt);
            }
        });
        jPanel2.add(jToggleButton2);

        setMaximumSize(new java.awt.Dimension(10000, 40));
        setMinimumSize(new java.awt.Dimension(310, 40));
        setPreferredSize(new java.awt.Dimension(310, 40));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
    }// </editor-fold>//GEN-END:initComponents

    private void setTriagnoms(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setTriagnoms
        setTriagPanel();
    }//GEN-LAST:event_setTriagnoms


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
