package com.calculator.commoncalculator;

import com.tokenizing.Token;
import com.tokenizing.TokenList;
import com.tokenizing.TokenType;
import com.calculate.number.CNumber;
import com.calculate.number.NumberFormat;
import com.calculate.equation.ExpressionEvaluator;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public abstract class CommonNumberPanel extends javax.swing.JPanel {

    protected TokenList tokenDigits = new TokenList();
    protected Token currentNumber;
    public NumberFormat enteringFormat = NumberFormat.NORMAL_DECIMAL;
    public NumberFormat showingFormat = NumberFormat.GROUPING_DECIMAL;
    public static final Token ZEROTOKEN = new Token(TokenType.NUMBER, "0", Token.ZERO.number);

    public CommonNumberPanel(boolean showEquation) {
        initComponents();
        currentNumber = ZEROTOKEN;
        this.showEquation.setVisible(showEquation);
    }

    public void setSizeSector(int scaleFactor) {
        addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                if (getParent() != null) {
                    getParent().addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                            setPreferredSize(new Dimension(getSize().width, getParent().getHeight() / scaleFactor + 30));
                            setMinimumSize(new Dimension(getSize().width, getParent().getHeight() / scaleFactor + 30));
                            setTextSize();
                        }
                    });
                }
            }
        });
    }

    public static void setZero(TokenList t) {
        t.clear();
        t.addToken(ZEROTOKEN);
    }

    public abstract void newKey(char key);

    public abstract void parseToken(Token token) throws Exception;

    public abstract CNumber getValue(boolean equal);

    public CNumber getTypedNumber() {
        String number = "";
        for (Token t : tokenDigits.getTokenList()) {
            number += t.name;
        }
        try {
            return new CNumber(number, enteringFormat);
        } catch (Exception ex) {
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

    public void setNumber(CNumber number) {
        try {
            numberText.setText(number.setNumberFormat(showingFormat).getNumberString());
        } catch (Exception ex) {
            Logger.getLogger(CommonNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                try {
                    text += t.number.setNumberFormat(showingFormat).getNumberString();
                } catch (Exception ex) {
                    Logger.getLogger(CommonNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        int h = numberText.getHeight() - numberText.getHeight() / 3;
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

    public CNumber getNumber() {
        try {
            return new CNumber(numberText.getText(), showingFormat);
        } catch (Exception ex) {
            Logger.getLogger(CommonNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addMemory(CNumber add) {
        try {
            setNumber(getNumber().add(add));
        } catch (Exception ex) {
            Logger.getLogger(CommonNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subMemory(CNumber add) {
        try {
            setNumber(getNumber().substract(add));
        } catch (Exception ex) {
            Logger.getLogger(CommonNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public abstract void addHistory(TokenList equation, CNumber answer);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showEquation = new javax.swing.JTextField();
        numberText = new javax.swing.JTextField();

        setAlignmentY(1.0F);
        setMaximumSize(new java.awt.Dimension(10000, 1000));
        setMinimumSize(new java.awt.Dimension(300, 100));
        setPreferredSize(new java.awt.Dimension(300, 100));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        showEquation.setEditable(false);
        showEquation.setFont(new java.awt.Font("Dubai", 0, 16)); // NOI18N
        showEquation.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        showEquation.setAlignmentY(1.0F);
        showEquation.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        showEquation.setFocusable(false);
        showEquation.setMaximumSize(new java.awt.Dimension(10000, 30));
        showEquation.setMinimumSize(new java.awt.Dimension(300, 30));
        showEquation.setPreferredSize(new java.awt.Dimension(300, 30));
        add(showEquation);

        numberText.setEditable(false);
        numberText.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        numberText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        numberText.setText("0");
        numberText.setAlignmentY(1.0F);
        numberText.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        numberText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        numberText.setMargin(new java.awt.Insets(0, 0, 0, 10));
        numberText.setMaximumSize(new java.awt.Dimension(10000, 500));
        numberText.setMinimumSize(new java.awt.Dimension(300, 60));
        numberText.setPreferredSize(new java.awt.Dimension(300, 60));
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
        add(numberText);
    }// </editor-fold>//GEN-END:initComponents

    private void numberTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numberTextKeyTyped
        newKey(evt.getKeyChar());
    }//GEN-LAST:event_numberTextKeyTyped

    private void numberTextComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_numberTextComponentResized

        setTextSize();
    }//GEN-LAST:event_numberTextComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField numberText;
    public javax.swing.JTextField showEquation;
    // End of variables declaration//GEN-END:variables

}
