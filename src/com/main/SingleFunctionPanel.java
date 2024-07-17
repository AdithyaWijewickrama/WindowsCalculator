/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import com.codes.test;
import expression.Function;
import grapher.BasicFunction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import javax.swing.border.LineBorder;
import parser.ExpressionParser;

/**
 *
 * @author adith
 */
public class SingleFunctionPanel extends javax.swing.JPanel {

    public Color color;
    public Function function;
    public BasicFunction basicFunction;
    public static int totalFunctions = -1;
    public LineOptions lineOptions;
    public static Dictionary<Character,Double> variable;
    public final static List<Object> colorFunctions = Arrays.asList(new Color[]{
        new Color(51, 51, 255),
        new Color(0, 107, 0),
        new Color(249, 16, 26),
        new Color(221, 177, 0),
        new Color(29, 172, 149),
        new Color(0, 186, 56),
        new Color(255, 22, 108),
        new Color(255, 96, 15),
        new Color(102, 0, 204),
        new Color(0, 102, 102),
        new Color(153, 0, 102),
        new Color(153, 102, 0),
        new Color(102, 102, 102),
        new Color(0, 0, 0),});

    public SingleFunctionPanel() {
        totalFunctions++;
        initComponents();
        setPanelColor(color);
        setEnable();
        lineOptions = new LineOptions(this, false) {
            @Override
            public void setColor(Color color) {
                setFunctionColor(color);
            }

            @Override
            public void setStyle(char s) {
                setFunctionStyle(s);
            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setFocusCycleRoot(true);
        setFocusTraversalPolicyProvider(true);
        setMaximumSize(new java.awt.Dimension(700000, 40));
        setMinimumSize(new java.awt.Dimension(330, 40));
        setPreferredSize(new java.awt.Dimension(330, 40));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        setLayout(new java.awt.CardLayout());

        jPanel4.setAlignmentX(0.0F);
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jButton3.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("f");
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorder(null);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(0);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton3.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton3.setName("1"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(40, 40));
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

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel3.setMinimumSize(new java.awt.Dimension(210, 40));
        jPanel3.setPreferredSize(new java.awt.Dimension(330, 40));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jTextField1.setBackground(getBackground());
        jTextField1.setFont(new java.awt.Font("Footlight MT Light", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setAlignmentX(0.0F);
        jTextField1.setAlignmentY(0.0F);
        jTextField1.setBorder(null);
        jTextField1.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField1MouseExited(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField1);

        jPanel4.add(jPanel3);

        jButton1.setBackground(jPanel3.getBackground());
        jButton1.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(null);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton1.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton1.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton2.setBackground(jPanel3.getBackground());
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("@");
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton2.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton2.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        add(jPanel4, "card2");
    }// </editor-fold>//GEN-END:initComponents

    public void setEnable() {
        jPanel3.removeAll();
        if (function == null) {
            jPanel3.add(jButton3);
            jPanel3.add(jTextField1);
            jPanel3.add(jButton1);
            setPanelColor(FrontFrame.APP_HOVER);
        } else {
            jPanel3.add(jButton3);
            jPanel3.add(jTextField1);
            jPanel3.add(jButton1);
            jPanel3.add(jButton2);
            setPanelColor(color);
        }
    }

    public void setPanelColor(Color c) {
        jButton3.setBackground(c);
        jButton2.setForeground(Color.WHITE);
        jButton1.setForeground(Color.WHITE);
        color = c;
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
        if (this.getParent() != null) {
            this.getParent().add(new SingleFunctionPanel());
        }
    }

    private void addVariable(char name,double d){
        if(variable.get(d)==null){
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

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained

    }//GEN-LAST:event_jTextField1FocusGained

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBorder(new LineBorder(color));
        jButton1.setForeground(color);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBorder(new LineBorder(color));
//        jButton2.setForeground(color);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBorder(null);
        jButton2.setForeground(Color.WHITE);
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBorder(null);
        jButton1.setForeground(Color.WHITE);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        setBorder(new LineBorder(color, 2, true));
        jButton3.setText("");
        jButton3.setIcon(functionIsVisible() ? Images.Images.HIDEPSW.getImage(30, 30) : Images.Images.SHOWPSW.getImage(30, 30));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jPanel3.setBorder(null);
        jButton3.setText("f");
        jButton3.setIcon(null);
    }//GEN-LAST:event_jButton3MouseExited

    private void jTextField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseEntered

    }//GEN-LAST:event_jTextField1MouseEntered

    private void jTextField1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseExited
        jPanel3.setBorder(null);
    }//GEN-LAST:event_jTextField1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removeFunction();
        totalFunctions--;
        this.getParent().remove(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setFunctionVisible(functionIsVisible());
        jButton3.setName(functionIsVisible() ? "0" : "1");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        lineOptions.setBounds(getWidth()/2-lineOptions.getPreferredSize().width, getHeight(), lineOptions.getPreferredSize().width, lineOptions.getPreferredSize().height);
        lineOptions.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String funcTxt = jTextField1.getText();
        if (!funcTxt.equals("")) {
            ExpressionParser parser = new ExpressionParser();
            function = parser.parse(funcTxt);

            if (function != null) {
                basicFunction = new BasicFunction(funcTxt, "x");
                basicFunction.setColor(color);
                basicFunction.setStroke(lineOptions.getStyle());
                if (parser.hasVariables()) {
                    Dictionary<Character, Double> variables = parser.getVariables();
                    variables.keys().asIterator().forEachRemaining(v -> {
                        addVariable(v, variables.get(v));
                    });
                }
                setFunction(basicFunction);
            }
        } else {

        }
        setEnable();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setBorder(new LineBorder(color));
    }//GEN-LAST:event_formMouseEntered
    public static void main(String[] args) {
        test f = new test();
        f.add(new SingleFunctionPanel(),BorderLayout.CENTER);
        f.setVisible(true);
        f.setSize(400, 400);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
