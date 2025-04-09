/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calculator.grapher;

import com.calculate.equation.ExpressionEvaluator;
import com.helper.test;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author adith
 */
public class SingleFunctionPanel extends javax.swing.JPanel {

    public Color color = new Color(102, 102, 102);
    public BasicFunction basicFunction;
    public static int totalFunctions = -1;
    public static ArrayList<SingleFunctionPanel> functions = new ArrayList<>();
    public LineOptions lineOptions;
    private boolean focusGained = false;
    private boolean placeholder = true;
    public static Dictionary<Character, Double> variable = new Hashtable<>();
    public final static List<Color> colorFunctions = Arrays.asList(new Color[]{
        new Color(0, 100, 200),
        new Color(20, 200, 0),
        new Color(230, 20, 30),
        new Color(255, 185, 0),
        new Color(0, 190, 190),
        new Color(0, 200, 100),
        new Color(230, 0, 140),
        new Color(250, 100, 10),
        new Color(0, 130, 85),
        new Color(200, 30, 110),
        new Color(140, 90, 50),
        new Color(90, 90, 90),
        new Color(100, 0, 200),
        new Color(0, 0, 0),});
    public static final String PLACEHOLDER = "Enter an expression";

    public SingleFunctionPanel() {

        totalFunctions++;
        initComponents();
        setPanelColor(color);
        setEnable();
        lineOptions = new LineOptions() {
            @Override
            public void setColor(Color c) {
                setFunctionColor(c);
                setPanelColor(c);
            }

            @Override
            public void setStyle(char s) {
                setFunctionStyle(s);
            }
        };
        functions.add(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new JTextField(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                if(getText().equals("")&&placeholder){
                    Graphics2D g2=(Graphics2D)g.create();
                    g2.setFont(new Font("Yu Gothic UI",Font.PLAIN,14) );
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getDisabledTextColor());
                    g2.drawString(PLACEHOLDER, 0, 25);
                    g2.dispose();
                }
            }
        };
        ;
        jPanel7 = new javax.swing.JPanel();
        clear = new javax.swing.JButton();
        options = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setFocusCycleRoot(true);
        setFocusTraversalPolicyProvider(true);
        setMaximumSize(new java.awt.Dimension(700000, 45));
        setMinimumSize(new java.awt.Dimension(330, 45));
        setPreferredSize(new java.awt.Dimension(330, 45));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        setLayout(new java.awt.CardLayout(2, 2));

        jPanel4.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jPanel4.setAlignmentX(0.0F);
        jPanel4.setPreferredSize(new java.awt.Dimension(330, 45));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jButton3.setBackground(new java.awt.Color(232, 17, 32));
        jButton3.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("f");
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorder(null);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(0);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.setMaximumSize(new java.awt.Dimension(48, 48));
        jButton3.setMinimumSize(new java.awt.Dimension(48, 48));
        jButton3.setName("1"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(48, 48));
        jButton3.setVerifyInputWhenFocusTarget(false);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 50));
        jPanel3.setMinimumSize(new java.awt.Dimension(210, 50));
        jPanel3.setPreferredSize(new java.awt.Dimension(330, 50));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEnter(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(evt);
            }
        });
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jPanel5.setBackground(jTextField1.getBackground());
        jPanel5.setMaximumSize(new java.awt.Dimension(10, 48));
        jPanel5.setMinimumSize(new java.awt.Dimension(10, 48));
        jPanel5.setPreferredSize(new java.awt.Dimension(10, 48));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEnter(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(evt);
            }
        });
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));
        jPanel3.add(jPanel5);

        jTextField1.setFont(new java.awt.Font("Times New Roman", 3, 15)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setAlignmentX(0.0F);
        jTextField1.setAlignmentY(0.0F);
        jTextField1.setBorder(null);
        jTextField1.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jTextField1.setMaximumSize(new java.awt.Dimension(2147483647, 48));
        jTextField1.setMinimumSize(new java.awt.Dimension(150, 48));
        jTextField1.setPreferredSize(new java.awt.Dimension(150, 48));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEnter(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField1);

        jPanel7.setBackground(jTextField1.getBackground());
        jPanel7.setMaximumSize(new java.awt.Dimension(10, 48));
        jPanel7.setMinimumSize(new java.awt.Dimension(10, 48));
        jPanel7.setPreferredSize(new java.awt.Dimension(10, 48));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7mouseEnter(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7mouseExit(evt);
            }
        });
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));
        jPanel3.add(jPanel7);

        jPanel4.add(jPanel3);

        clear.setBackground(jPanel3.getBackground());
        clear.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setText("X");
        clear.setAlignmentY(0.0F);
        clear.setBorder(null);
        clear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clear.setMargin(new java.awt.Insets(0, 0, 0, 0));
        clear.setMaximumSize(new java.awt.Dimension(48, 48));
        clear.setMinimumSize(new java.awt.Dimension(48, 48));
        clear.setPreferredSize(new java.awt.Dimension(48, 48));
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEnter(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(evt);
            }
        });
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel4.add(clear);

        options.setBackground(jPanel3.getBackground());
        options.setForeground(new java.awt.Color(255, 255, 255));
        options.setText("@");
        options.setAlignmentY(0.0F);
        options.setBorder(null);
        options.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        options.setMargin(new java.awt.Insets(0, 0, 0, 0));
        options.setMaximumSize(new java.awt.Dimension(48, 48));
        options.setMinimumSize(new java.awt.Dimension(48, 48));
        options.setPreferredSize(new java.awt.Dimension(48, 48));
        options.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEnter(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(evt);
            }
        });
        options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsActionPerformed(evt);
            }
        });
        jPanel4.add(options);

        delete.setBackground(jPanel3.getBackground());
        delete.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("$");
        delete.setAlignmentY(0.0F);
        delete.setBorder(null);
        delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delete.setMargin(new java.awt.Insets(0, 0, 0, 0));
        delete.setMaximumSize(new java.awt.Dimension(48, 48));
        delete.setMinimumSize(new java.awt.Dimension(48, 48));
        delete.setPreferredSize(new java.awt.Dimension(48, 48));
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEnter(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(evt);
            }
        });
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel4.add(delete);

        add(jPanel4, "card2");
    }// </editor-fold>//GEN-END:initComponents

    public void setEnable(JComponent... c) {
        clear.setVisible(false);
        options.setVisible(false);
        delete.setVisible(false);
        jTextField1.revalidate();
        jTextField1.repaint();
        if (c == null) {
            return;
        }
        for (JComponent cm : c) {
            cm.setVisible(true);
        }
        revalidate();
        repaint();
    }

    public void setPanelColor(Color c) {
        jButton3.setBackground(c);
    }

    public void setFunctionStyle(char c) {
        //override code
    }

    public void setFunctionColor(Color c) {
        //override code
    }

    public void setFunction(BasicFunction fnc) {
        //override code
    }

    public void removeFunction() {
        //override code
    }

    public void setFunctionVisible(boolean b) {
        //override code
    }

    public void addNewSingleFunctionPanel() {
    }

    private void addVariable(char name, double d) {
        if (variable.get(d) == null) {
            addVariablePanel(new VariablePanel());
        }
        variable.put(name, d);
    }

    public void addVariablePanel(VariablePanel panel) {

    }

    public boolean functionIsVisible() {
        return jButton3.getName().equals("1");
    }

    public char getFunctionStyle() {
        return lineOptions.getStyle();
    }

    public boolean isLast() {
        return functions.indexOf(this) == functions.size() - 1;
    }

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        placeholder = true;
        if (isLast()) {
            jPanel4.setBorder(new LineBorder(color, 1, false));
        } else {
            if (!jTextField1.getText().equals("")) {
                setEnable(clear);
            } else {
                setEnable();
            }
        }
        jPanel4.setBorder(new LineBorder(color, 1, false));
        FlatRoundBorder border = new com.formdev.flatlaf.ui.FlatRoundBorder() {
            protected int getArc(Component c) {
                return 20;
            }
        };
        border.paintBorder(this, getGraphics().create(), 0, 0, getWidth(), getHeight());
        focusGained = true;
    }//GEN-LAST:event_jTextField1FocusGained

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setText("");
        jButton3.setIcon(functionIsVisible() ? Images.Images.HIDEPSW.getImage(30, 30) : Images.Images.SHOWPSW.getImage(30, 30));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jPanel3.setBorder(null);
        jButton3.setText("f");
        jButton3.setIcon(null);
    }//GEN-LAST:event_jButton3MouseExited

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        jTextField1.setText("");

    }//GEN-LAST:event_clearActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setFunctionVisible(functionIsVisible());
        jButton3.setName(functionIsVisible() ? "0" : "1");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsActionPerformed
        Point location = getLocationOnScreen();
        if (getWidth() > 330) {
            lineOptions.setLocation(location.x + getWidth() / 2 - lineOptions.getPreferredSize().width / 2, location.y + 50);
        } else {
            lineOptions.setLocation(location.x + getWidth() - lineOptions.getPreferredSize().width, location.y + 50);
        }
        lineOptions.setSize(lineOptions.getPreferredSize().width, lineOptions.getPreferredSize().height);
        lineOptions.setVisible(true);
    }//GEN-LAST:event_optionsActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        if (13 < functions.size()) {
            return;
        }
        if (isLast()) {
            color = (Color) colorFunctions.get(functions.size() - 1);
            setPanelColor(color);
        }
        if (!getText().equals("")) {
            try {
                basicFunction = new BasicFunction(getText(), "x");
                basicFunction.setColor(color);
                basicFunction.setStroke(lineOptions.getStyle());
                setFunction(basicFunction);
            } catch (Exception ex) {
                Logger.getLogger(SingleFunctionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (isLast()) {
            addNewSingleFunctionPanel();
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
    }//GEN-LAST:event_formMouseEntered


    private void mouseEnter(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseEnter
        if (!isLast()) {
            if (!focusGained) {
                setEnable(options, delete);
            } else {
                setEnable(clear);
            }
        }
        placeholder = true;
        jPanel4.setBorder(new LineBorder(color, 1, false));
    }//GEN-LAST:event_mouseEnter

    private String getText() {
        return jTextField1.getText();
    }

    private void mouseExit(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseExit
        if (!isLast()) {
            if (!focusGained) {
                jPanel4.setBorder(null);
                setEnable();
            }
            placeholder = focusGained;
        }
    }//GEN-LAST:event_mouseExit

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        jPanel4.setBorder(null);
        if (!isLast()) {
            placeholder = false;
        }
        setEnable();
        focusGained = false;
    }//GEN-LAST:event_jTextField1FocusLost

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        removeFunction();
        functions.remove(this);
    }//GEN-LAST:event_deleteActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        setEnable(clear);
        if (isLast()) {
            color = (Color) colorFunctions.get(totalFunctions);
            setPanelColor(color);
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jPanel7mouseEnter(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7mouseEnter
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7mouseEnter

    private void jPanel7mouseExit(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7mouseExit
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7mouseExit
    public static void main(String[] args) {
        test f = new test();
        f.add(new SingleFunctionPanel(), BorderLayout.CENTER);
        f.setVisible(true);
        f.setSize(400, 400);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton clear;
    public javax.swing.JButton delete;
    public javax.swing.JButton jButton3;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JButton options;
    // End of variables declaration//GEN-END:variables
}
