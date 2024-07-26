/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.calculator;

import com.Tokenizing.Token;
import com.Tokenizing.TokenList;
import com.Tokenizing.TokenType;
import com.calculate.Number;
import com.calculate.NumberFormat;
import com.calculate.equation.ExpressionEvaluator;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public abstract class CommonNumberPanel extends javax.swing.JPanel {

    protected TokenList tokenDigits = new TokenList();
    protected Token currentNumber;
    protected NumberFormat enteringFormat = NumberFormat.NORMAL_DECIMAL;
    protected NumberFormat showingFormat = NumberFormat.GROUPING_DECIMAL;
    protected static final Token ZEROTOKEN = new Token(TokenType.NUMBER, "0", Token.ZERO.number);

    public CommonNumberPanel(boolean showEquation) {
        initComponents();
        currentNumber = ZEROTOKEN;
        this.showEquation.setVisible(showEquation);
    }

    public static void setZero(TokenList t) {
        t.clear();
        t.addToken(ZEROTOKEN);
    }

    public abstract void newKey(char key);

    public abstract void parseToken(Token token) throws Exception;

    public abstract Number getValue(boolean equal);

    public Number getTypedNumber() {
        String number = "";
        for (Token t : tokenDigits.getTokenList()) {
            number += t.name;
        }
        try {
            return new Number(number, enteringFormat);
        } catch (ParseException ex) {
            Logger.getLogger(CommonNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void reset() {
        currentNumber = ZEROTOKEN;
        tokenDigits.clear();
        tokenDigits.addToken(ZEROTOKEN);
    }

    public void deleteLastTokens(TokenList t) {
        int scan = ExpressionEvaluator.scanFor(t, TokenType.OPARATOR);
        int len = t.size() - scan - 1;
        for (int i = 0; i < len; i++) {
            t.deleteToken(scan + 1);
        }
    }

    public void setNumber(Number number) {
        numberText.setText(number.setNumberFormat(showingFormat).getNumberString());
    }

    public void setEquation(TokenList tl) {
        String text = "";
        for (int i = 0; i < tl.size(); i++) {
            Token t = tl.tokenAt(i);
            if (t.type == TokenType.OPARATOR) {
                text += String.format(" %s ", t.toLocalString());
            } else if (t.equalsTo(Token.OPEN_PRANTHESIS)) {
                text += String.format(" %s ", t.toLocalString());
            } else if (t.equalsTo(Token.CLOSE_PRANTHESIS)) {
                text += String.format(" %s ", t.toLocalString());
            } else if (t.type == TokenType.NUMBER) {
                text += t.number.setNumberFormat(showingFormat).getNumberString();
            } else {
                text += t.toLocalString();
            }
        }
        text = text.trim();
        showEquation.setText(text);
    }

    public void error(Exception x) {

    }

    public void setTextSize() {
        int w = numberText.getWidth();
        int h = numberText.getHeight();
        int textWidth;
        int textHeight;
        Font f = numberText.getFont();
        for (int i = 0; i < 20; i++) {
            textWidth = numberText.getGraphics().getFontMetrics(numberText.getFont()).stringWidth(numberText.getText());
            textHeight = numberText.getGraphics().getFontMetrics(numberText.getFont()).getHeight();
            if (textWidth > w && f.getSize() <= 16) {
                break;
            }
            if (textWidth <= w && textWidth >= w - 20) {
                break;
            }
            if (textWidth < w) {
                if (textHeight <= h - 5 && textHeight >= h - 10) {
                    break;
                } else if (textHeight > h) {
                    f = f.deriveFont(f.getSize2D() - 1);
                } else {
                    f = f.deriveFont(f.getSize2D() + 1);
                }
            } else if (textWidth >= w) {
                f = f.deriveFont(f.getSize2D() - 1);

            }
            numberText.setFont(f);
        }
    }

    public Number getNumber() {
        try {
            return new Number(numberText.getText(), showingFormat);
        } catch (ParseException ex) {
            Logger.getLogger(CommonNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    public void addMemory(Number add) {
        setNumber(getNumber().add(add));
    }

    public void subMemory(Number add) {
        setNumber(getNumber().substract(add));
    }

    public abstract void addHistory(TokenList equation, Number answer);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        showEquation = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        numberText = new javax.swing.JTextField();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        jPanel2.setMinimumSize(new java.awt.Dimension(74, 40));
        jPanel2.setLayout(new java.awt.CardLayout(5, 5));

        showEquation.setEditable(false);
        showEquation.setFont(new java.awt.Font("Dubai", 0, 16)); // NOI18N
        showEquation.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        showEquation.setBorder(null);
        showEquation.setFocusable(false);
        showEquation.setMaximumSize(new java.awt.Dimension(2147483647, 28));
        jPanel2.add(showEquation, "card2");

        add(jPanel2);

        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(330, 50));
        jPanel1.setPreferredSize(new java.awt.Dimension(330, 50));
        jPanel1.setLayout(new java.awt.CardLayout(5, 5));

        numberText.setEditable(false);
        numberText.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        numberText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        numberText.setText("0");
        numberText.setBorder(null);
        numberText.setMargin(new java.awt.Insets(10, 10, 10, 10));
        numberText.setMaximumSize(new java.awt.Dimension(2147483647, 120));
        numberText.setMinimumSize(new java.awt.Dimension(320, 50));
        numberText.setPreferredSize(new java.awt.Dimension(320, 60));
        numberText.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                numberTextComponentResized(evt);
            }
        });
        numberText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numberTextKeyTyped(evt);
            }
        });
        jPanel1.add(numberText, "card2");

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void numberTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numberTextKeyTyped
        newKey(evt.getKeyChar());
    }//GEN-LAST:event_numberTextKeyTyped

    private void numberTextComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_numberTextComponentResized
        setTextSize();
    }//GEN-LAST:event_numberTextComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JTextField numberText;
    public javax.swing.JTextField showEquation;
    // End of variables declaration//GEN-END:variables

}
