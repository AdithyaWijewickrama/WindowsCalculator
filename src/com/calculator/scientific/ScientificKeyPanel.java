package com.calculator.scientific;

import com.amath.advacedmath.tokenizing.Token;
import static com.amath.advacedmath.tokenizing.Token.ABS;
import static com.amath.advacedmath.tokenizing.Token.CLOSE_PRANTHESIS;
import static com.amath.advacedmath.tokenizing.Token.CUBE;
import static com.amath.advacedmath.tokenizing.Token.CUBEROOT;
import static com.amath.advacedmath.tokenizing.Token.DIVIDE;
import static com.amath.advacedmath.tokenizing.Token.DOT;
import static com.amath.advacedmath.tokenizing.Token.EIGHT;
import static com.amath.advacedmath.tokenizing.Token.EQUAL;
import static com.amath.advacedmath.tokenizing.Token.FACTORIAL;
import static com.amath.advacedmath.tokenizing.Token.FIVE;
import static com.amath.advacedmath.tokenizing.Token.FOUR;
import static com.amath.advacedmath.tokenizing.Token.LN;
import static com.amath.advacedmath.tokenizing.Token.LOG10;
import static com.amath.advacedmath.tokenizing.Token.LOGBASEY;
import static com.amath.advacedmath.tokenizing.Token.MINUS;
import static com.amath.advacedmath.tokenizing.Token.MODULO;
import static com.amath.advacedmath.tokenizing.Token.MULTIPLY;
import static com.amath.advacedmath.tokenizing.Token.NINE;
import static com.amath.advacedmath.tokenizing.Token.ONE;
import static com.amath.advacedmath.tokenizing.Token.OPEN_PRANTHESIS;
import static com.amath.advacedmath.tokenizing.Token.PLUS;
import static com.amath.advacedmath.tokenizing.Token.PLUS_OR_MINUS;
import static com.amath.advacedmath.tokenizing.Token.RAISED;
import static com.amath.advacedmath.tokenizing.Token.SEVEN;
import static com.amath.advacedmath.tokenizing.Token.SIX;
import static com.amath.advacedmath.tokenizing.Token.SQUARED;
import static com.amath.advacedmath.tokenizing.Token.SQUREROOT;
import static com.amath.advacedmath.tokenizing.Token.TENRAISED;
import static com.amath.advacedmath.tokenizing.Token.THREE;
import static com.amath.advacedmath.tokenizing.Token.TWO;
import static com.amath.advacedmath.tokenizing.Token.TWORAISED;
import static com.amath.advacedmath.tokenizing.Token.YROOT;
import static com.amath.advacedmath.tokenizing.Token.ZERO;
import static com.amath.advacedmath.tokenizing.Token.e;
import static com.amath.advacedmath.tokenizing.Token.PI;
import static com.amath.advacedmath.tokenizing.Token.eRAISED;
import com.amath.advacedmath.tokenizing.TokenList;
import com.amath.advacedmath.tokenizing.TokenType;
import com.calculator.commoncalculator.button.ButtonLayout;
import com.calculator.commoncalculator.CommonKeyPanel;
import com.calculator.commoncalculator.CommonNumberPanel;
import static com.calculator.commoncalculator.button.DefaultButtons.BACK;
import static com.calculator.commoncalculator.button.DefaultButtons.C;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public final class ScientificKeyPanel extends javax.swing.JPanel {

    CommonKeyPanel keyPanel;
    CommonKeyPanel keyPanel_;
    CommonKeyPanel keyPanel_2;
    CommonNumberPanel numberPanel;
    TokenList buttons_ = new TokenList(
            SQUARED,
            SQUREROOT,
            RAISED,
            TENRAISED,
            LOG10,
            LN);
    TokenList buttons_2 = new TokenList(
            CUBE,
            CUBEROOT,
            YROOT,
            TWORAISED,
            LOGBASEY,
            eRAISED);
    TokenList buttons = new TokenList(
            PI,
            new Token(TokenType.FUNCTION_, "1/x", 'r'),
            OPEN_PRANTHESIS,
            SEVEN,
            FOUR,
            ONE,
            PLUS_OR_MINUS,
            e,
            ABS,
            CLOSE_PRANTHESIS,
            EIGHT,
            FIVE,
            TWO,
            ZERO,
            C,
            new Token(TokenType.FUNCTION_, "exp", 'Q'),
            FACTORIAL,
            NINE,
            SIX,
            THREE,
            DOT,
            BACK,
            MODULO,
            DIVIDE,
            MULTIPLY,
            MINUS,
            PLUS,
            EQUAL);
    String pattern = ""
            + "####\n"
            + "####\n"
            + "####\n"
            + "####\n"
            + "####\n"
            + "####\n"
            + "####\n";
    String pattern_ = ""
            + "#\n"
            + "#\n"
            + "#\n"
            + "#\n"
            + "#\n"
            + "#\n";

    /**
     * Creates new form StandardKeyPanel
     */
    public ScientificKeyPanel(CommonNumberPanel numberPanel) {
        this.numberPanel = numberPanel;
        initComponents();
        try {
            keyPanel = new CommonKeyPanel(numberPanel, buttons, pattern, ButtonLayout.COLUMN);
            keyPanel_ = new CommonKeyPanel(numberPanel, buttons_, pattern_, ButtonLayout.COLUMN);
            keyPanel_2 = new CommonKeyPanel(numberPanel, buttons_2, pattern_, ButtonLayout.COLUMN);
        } catch (Exception ex) {
            Logger.getLogger(ScientificKeyPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        keyPanel_.addBlocks();
        keyPanel_2.addBlocks();
        keyPanel.addBlock(new JPanel(), 0);//only for initialize, replace when secondaryButtons method called
        secondaryButtons(false);
        add(keyPanel);
    }

    public void secondaryButtons(boolean selected) {
        if(jToggleButton1.getParent()!=null)jToggleButton1.getParent().remove(jToggleButton1);
        CommonKeyPanel p = selected ? keyPanel_2 : keyPanel_;
        p.getBlocks().get(0).add(jToggleButton1, 0);
        keyPanel.getBlocks().set(0,p.getBlocks().get(0));
        keyPanel.addBlocks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();

        jToggleButton1.setText("2nd");
        jToggleButton1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jToggleButton1.setMinimumSize(new java.awt.Dimension(40, 30));
        jToggleButton1.setPreferredSize(new java.awt.Dimension(70, 47));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        setMaximumSize(new java.awt.Dimension(10000, 10000));
        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(300, 300));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    private boolean key2ndEnabled = false;
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        secondaryButtons(key2ndEnabled);
        key2ndEnabled=!key2ndEnabled;
    }//GEN-LAST:event_jToggleButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
