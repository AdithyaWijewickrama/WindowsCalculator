package com.codes;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DateCalculation extends javax.swing.JPanel {

    public int type;

    public DateCalculation() {
        initComponents();
        String[] dates = new String[1000];
        for (int i = 0; i < dates.length; i++) {
            dates[i] = i + "";
        }
        jComboBox2.setModel(new DefaultComboBoxModel<>(dates));
        jComboBox3.setModel(new DefaultComboBoxModel<>(dates));
        jComboBox4.setModel(new DefaultComboBoxModel<>(dates));
        setType(0);
        ((JTextField) (fromDate.getDateEditor().getUiComponent())).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (type == 0) {
                    setDifference();
                } else if (type == 1) {
                    setAddSub();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (type == 0) {
                    setDifference();
                } else if (type == 1) {
                    setAddSub();
                }
            }
        });
        ((JTextField) (toDate.getDateEditor().getUiComponent())).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (type == 0) {
                    setDifference();
                } else if (type == 1) {
                    setAddSub();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (type == 0) {
                    setDifference();
                } else if (type == 1) {
                    setAddSub();
                }
            }
        });
    }

    public void setType(int type) {
        this.type = type;
        jPanel1.removeAll();
        switch (type) {
            case 0:
                jPanel1.add(jPanel2);
                jLabel2.setText("Difference");
                break;
            case 1:
                jPanel1.add(jPanel3);
                jLabel2.setText("Date");
                break;
        }
        jPanel1.repaint();
        jPanel1.validate();
    }

    public void setAddSub() {
        if (fromDate.getDate() != null) {
            boolean a = add.isSelected();
            Date d = fromDate.getDate();
            int y = d.getYear() + ((jComboBox2.getSelectedIndex()) * (a ? 1 : -1));
            int m = d.getMonth() + ((jComboBox3.getSelectedIndex()) * (a ? 1 : -1));
            int dy = d.getDate() + ((jComboBox4.getSelectedIndex()) * (a ? 1 : -1));
            jTextField1.setText(toNormal(new Date(y, m, dy)));
        }
    }

    public void setDifference() {
        if (fromDate.getDate() != null && toDate.getDate() != null) {
            long diff = getDifference(fromDate.getDate(), toDate.getDate());
            String ans;
            if (diff == 0) {
                ans = "Same dates";
            } else {
                long years = diff / 360;
                long months = (diff % 360) / 30;
                long weeks = (diff % 30) / 7;
                long days = diff % 7;
                ans = String.format("%s%s%s%s",
                        years > 0 ? years + " Years," : "",
                        months > 0 ? months + " Months," : "",
                        weeks > 0 ? weeks + " Weeks," : "",
                        days > 0 ? days + " Days" : "");
            }
            jTextField1.setText(ans);
        }
    }

    public long getDifference(Date d1, Date d2) {
        long days = (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24);
        return days < 0 ? days * -1 : days;
    }

    public static String toNormal(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,MMM dd,yyyy");
        return dateFormat.format(d);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        toDate = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        add = new javax.swing.JRadioButton();
        substract = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        fromDate = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("To");
        jLabel3.setAlignmentY(0.0F);
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        toDate.setAlignmentX(0.0F);
        toDate.setDate(Commons.date);
        toDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jPanel2.add(toDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 190, 31));

        jPanel3.setMaximumSize(new java.awt.Dimension(330, 32767));
        jPanel3.setMinimumSize(new java.awt.Dimension(330, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(330, 268));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        add.setSelected(true);
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel3.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        substract.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        substract.setText("Substract");
        substract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                substractActionPerformed(evt);
            }
        });
        jPanel3.add(substract, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jComboBox2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "999" }));
        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 81, -1));

        jLabel4.setText("Years");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jComboBox3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "999" }));
        jPanel3.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 80, -1));

        jLabel5.setText("Months");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        jComboBox4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "999" }));
        jPanel3.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 80, -1));

        jLabel6.setText("Days");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Difference between dates", "Add or substract dates" }));
        jComboBox1.setBorder(null);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("From");

        fromDate.setDate(Commons.date);
        fromDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setText("Date");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        setType(jComboBox1.getSelectedIndex());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        substract.setSelected(!add.isSelected());
    }//GEN-LAST:event_addActionPerformed

    private void substractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_substractActionPerformed
        add.setSelected(!substract.isSelected());
    }//GEN-LAST:event_substractActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JRadioButton add;
    public com.toedter.calendar.JDateChooser fromDate;
    public javax.swing.JComboBox<String> jComboBox1;
    public javax.swing.JComboBox<String> jComboBox2;
    public javax.swing.JComboBox<String> jComboBox3;
    public javax.swing.JComboBox<String> jComboBox4;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JRadioButton substract;
    public com.toedter.calendar.JDateChooser toDate;
    // End of variables declaration//GEN-END:variables
}
