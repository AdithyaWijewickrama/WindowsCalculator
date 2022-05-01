/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home;

import com.codes.test;
import expression.Function;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import parser.ExpressionParser;

/**
 *
 * @author adith
 */
public class SingleFunctionPanel extends javax.swing.JPanel {

    public Color color = Color.GRAY;
    public Function function = null;
    public int id=-1;

    public SingleFunctionPanel() {
        initComponents();
        setPanelColor(color);
        add(jPanel2);
        setEnable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton14 = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel2.setMaximumSize(new java.awt.Dimension(300, 220));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 220));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 220));

        jToggleButton1.setBackground(new java.awt.Color(51, 51, 255));
        jToggleButton1.setBorder(null);
        jToggleButton1.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton1);

        jToggleButton2.setBackground(new java.awt.Color(153, 153, 255));
        jToggleButton2.setBorder(null);
        jToggleButton2.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton2);

        jToggleButton3.setBackground(new java.awt.Color(153, 51, 255));
        jToggleButton3.setBorder(null);
        jToggleButton3.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton3);

        jToggleButton4.setBackground(new java.awt.Color(0, 153, 0));
        jToggleButton4.setBorder(null);
        jToggleButton4.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton4);

        jToggleButton5.setBackground(new java.awt.Color(0, 204, 0));
        jToggleButton5.setBorder(null);
        jToggleButton5.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton5);

        jToggleButton6.setBackground(new java.awt.Color(0, 255, 102));
        jToggleButton6.setBorder(null);
        jToggleButton6.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton6);

        jToggleButton7.setBackground(new java.awt.Color(102, 102, 102));
        jToggleButton7.setBorder(null);
        jToggleButton7.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton7);

        jToggleButton8.setBackground(new java.awt.Color(204, 0, 0));
        jToggleButton8.setBorder(null);
        jToggleButton8.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton8);

        jToggleButton9.setBackground(new java.awt.Color(255, 0, 51));
        jToggleButton9.setBorder(null);
        jToggleButton9.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton9);

        jToggleButton10.setBackground(new java.awt.Color(204, 0, 102));
        jToggleButton10.setBorder(null);
        jToggleButton10.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton10);

        jToggleButton11.setBackground(new java.awt.Color(255, 255, 0));
        jToggleButton11.setBorder(null);
        jToggleButton11.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton11);

        jToggleButton12.setBackground(new java.awt.Color(255, 153, 0));
        jToggleButton12.setBorder(null);
        jToggleButton12.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton12);

        jToggleButton13.setBackground(new java.awt.Color(153, 102, 0));
        jToggleButton13.setBorder(null);
        jToggleButton13.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton13);

        jToggleButton14.setBackground(new java.awt.Color(0, 0, 0));
        jToggleButton14.setBorder(null);
        jToggleButton14.setPreferredSize(new java.awt.Dimension(35, 35));
        jToggleButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                colorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                colorBtnMouseExited(evt);
            }
        });
        jToggleButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setColor(evt);
            }
        });
        jPanel1.add(jToggleButton14);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Line options");

        jLabel5.setText("Color");

        jLabel6.setText("Style");

        jComboBox1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "_________________________", "-----------------------------------------", ".................................................." }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setMaximumSize(new java.awt.Dimension(330, 50));
        setMinimumSize(new java.awt.Dimension(330, 50));
        setPreferredSize(new java.awt.Dimension(330, 50));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 50));
        jPanel3.setMinimumSize(new java.awt.Dimension(330, 50));
        jPanel3.setPreferredSize(new java.awt.Dimension(330, 50));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jButton3.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("f");
        jButton3.setBorder(null);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton3.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton3.setName("1"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(50, 50));
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
        jPanel3.add(jButton3);

        jTextField1.setBackground(getBackground());
        jTextField1.setFont(new java.awt.Font("Footlight MT Light", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setBorder(null);
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

        jButton1.setBackground(jPanel3.getBackground());
        jButton1.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setBorder(null);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
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
        jPanel3.add(jButton1);

        jButton2.setBackground(jPanel3.getBackground());
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("@");
        jButton2.setBorder(null);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton2.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton2.setPreferredSize(new java.awt.Dimension(30, 30));
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
        jPanel3.add(jButton2);

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents

    public void setEnable() {
        jPanel3.removeAll();
        jPanel2.setVisible(false);
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

    public void setFuncStyle(int id,char c) {
        //override code
    }

    public void setFuncColor(int id,Color c) {
        //override code
    }

    public void removeFunc(int id) {

    }

    public void setFuncVisible(int id,boolean b) {

    }

    public boolean funcIsVisible() {
        return jButton3.getName().equals("1");
    }

    public char getFunctionStyle() {
        return jComboBox1.getSelectedItem().toString().charAt(0);
    }

    public int setFunction(int id,Function fnc, Color c, char style,boolean vis) {
        return 0;
    }

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained

    }//GEN-LAST:event_jTextField1FocusGained

    private void colorBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorBtnMouseEntered
        ((JComponent) evt.getSource()).setBorder(new LineBorder(FrontFrame.APP_HOVER));
    }//GEN-LAST:event_colorBtnMouseEntered

    private void colorBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorBtnMouseExited
        ((JComponent) evt.getSource()).setBorder(null);
    }//GEN-LAST:event_colorBtnMouseExited

    private void setColor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setColor
        JToggleButton btn = (JToggleButton) evt.getSource();
        color = btn.getBackground();
        for (Component comp : jPanel1.getComponents()) {
            JToggleButton btn1 = (JToggleButton) comp;
            btn1.setSelected(false);
        }
        btn.setBorder(new LineBorder(FrontFrame.APP_HOVER));
        setFuncColor(id,color);
        jButton3.setBackground(color);
    }//GEN-LAST:event_setColor

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        setFuncStyle(id,jComboBox1.getSelectedItem().toString().charAt(0));
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBorder(new LineBorder(color));
        jButton1.setForeground(color);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBorder(new LineBorder(color));
        jButton2.setForeground(color);
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
        jPanel3.setBorder(new LineBorder(color));
        jButton3.setText("");
        jButton3.setIcon(funcIsVisible() ? Images.Images.HIDEPSW.getImage(30, 30) : Images.Images.SHOWPSW.getImage(30, 30));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jPanel3.setBorder(null);
        jButton3.setText("f");
        jButton3.setIcon(null);
    }//GEN-LAST:event_jButton3MouseExited

    private void jTextField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseEntered
        jPanel3.setBorder(new LineBorder(color));
    }//GEN-LAST:event_jTextField1MouseEntered

    private void jTextField1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseExited
        jPanel3.setBorder(null);
    }//GEN-LAST:event_jTextField1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removeFunc(id);
        this.getParent().remove(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setFuncVisible(id,funcIsVisible());
        jButton3.setName(funcIsVisible() ? "0" : "1");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jPanel2.setVisible(!jPanel2.isVisible());
        MouseListener l = new MouseAdapter() {
            boolean b = false;

            @Override
            public void mouseExited(MouseEvent e) {
                jPanel2.setVisible(!(e.getX() <= 0 || e.getY() <= 0 || e.getX() >= jPanel2.getWidth() || e.getY() >= jPanel2.getHeight()));
                if (!jPanel2.isVisible()) {
                    setMaximumSize(new java.awt.Dimension(330, 50));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                b = true;
            }

        };
        validate();
        if (jPanel2.isVisible()) {
            setMaximumSize(new java.awt.Dimension(330, 275));
            jPanel2.addMouseListener(l);
        } else {
            setMaximumSize(new java.awt.Dimension(330, 50));
        }
        repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String funcTxt = jTextField1.getText();
        if (!funcTxt.equals("")) {
            ExpressionParser parser = new ExpressionParser();
            function = parser.parse(funcTxt);
            if (function != null) {
                id=setFunction(id,function, color, getFunctionStyle(),funcIsVisible());
            }
        }
        setEnable();
    }//GEN-LAST:event_jTextField1ActionPerformed
    public static void main(String[] args) {
        test f = new test();
        f.add(new SingleFunctionPanel());
        f.setVisible(true);
        f.setSize(400, 400);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JComboBox<String> jComboBox1;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JToggleButton jToggleButton1;
    public javax.swing.JToggleButton jToggleButton10;
    public javax.swing.JToggleButton jToggleButton11;
    public javax.swing.JToggleButton jToggleButton12;
    public javax.swing.JToggleButton jToggleButton13;
    public javax.swing.JToggleButton jToggleButton14;
    public javax.swing.JToggleButton jToggleButton2;
    public javax.swing.JToggleButton jToggleButton3;
    public javax.swing.JToggleButton jToggleButton4;
    public javax.swing.JToggleButton jToggleButton5;
    public javax.swing.JToggleButton jToggleButton6;
    public javax.swing.JToggleButton jToggleButton7;
    public javax.swing.JToggleButton jToggleButton8;
    public javax.swing.JToggleButton jToggleButton9;
    // End of variables declaration//GEN-END:variables
}
