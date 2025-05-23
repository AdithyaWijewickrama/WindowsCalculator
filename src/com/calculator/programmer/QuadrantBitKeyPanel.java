package com.calculator.programmer;

import com.calculator.ui.Ui;
import static com.calculator.ui.AppUi.APP_THEME;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;

/**
 *
 * @author AW Developer
 */
public class QuadrantBitKeyPanel extends javax.swing.JPanel {

    private final int exponent;

    public QuadrantBitKeyPanel(int exponent) {
        this.exponent = exponent;
        initComponents();
        jLabel1.setText(exponent + "");
    }

    public void setBit(int i, int bit) {
        if(i<0||i>4||(bit!=0&&bit!=1)){
            return;
        }
        JButton b;
        switch (i) {
            case 0:
                b = bin1;
                break;
            case 1:
                b = bin2;
                break;
            case 2:
                b = bin3;
                break;
            case 3:
                b = bin4;
                break;
            default:
                throw new AssertionError();
        }
        b.setText(bit + "");
        switch (bit) {
            case 0:
                b.setForeground((Color) UIManager.get("Buttton.foreground"));
                break;
            case 1:
                b.setForeground(Ui.SPECIAL_COLOR);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public String getBinaryString(){
        return bin1.getText()+bin2.getText()+bin3.getText()+bin4.getText();
    }
    
    public String getBinaryValue(){
        return getBinaryString()+"0".repeat(exponent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        binKeyPanel0 = new javax.swing.JPanel();
        bin1 = new javax.swing.JButton();
        bin2 = new javax.swing.JButton();
        bin3 = new javax.swing.JButton();
        bin4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        binKeyPanel0.setMinimumSize(new java.awt.Dimension(80, 55));

        bin1.setBackground(jPanel12.getBackground());
        bin1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin1.setText("0");
        bin1.setBorder(null);
        bin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bin1ActionPerformed(evt);
            }
        });

        bin2.setBackground(jPanel12.getBackground());
        bin2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin2.setText("0");
        bin2.setBorder(null);
        bin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bin2ActionPerformed(evt);
            }
        });

        bin3.setBackground(jPanel12.getBackground());
        bin3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin3.setText("0");
        bin3.setBorder(null);
        bin3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bin3ActionPerformed(evt);
            }
        });

        bin4.setBackground(jPanel12.getBackground());
        bin4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin4.setText("0");
        bin4.setBorder(null);
        bin4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bin4setBinKey(evt);
            }
        });
        bin4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bin4ActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("0");

        javax.swing.GroupLayout binKeyPanel0Layout = new javax.swing.GroupLayout(binKeyPanel0);
        binKeyPanel0.setLayout(binKeyPanel0Layout);
        binKeyPanel0Layout.setHorizontalGroup(
            binKeyPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel0Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(binKeyPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel0Layout.createSequentialGroup()
                        .addComponent(bin4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(bin3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(bin2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(bin1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        binKeyPanel0Layout.setVerticalGroup(
            binKeyPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel0Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin4)
                    .addComponent(bin3)
                    .addComponent(bin2)
                    .addComponent(bin1))
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel12.add(binKeyPanel0);

        add(jPanel12);
    }// </editor-fold>//GEN-END:initComponents

    private void bin4setBinKey(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bin4setBinKey
        JButton b = (JButton) evt.getSource();
        b.setText(b.getText().equals("0") ? "1" : "0");
        b.setForeground(b.getText().equals("0") ? b.getParent().getBackground() : APP_THEME);
    }//GEN-LAST:event_bin4setBinKey

    private void bin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bin1ActionPerformed
        setBit(0, ((JButton) evt.getSource()).getText().equals("1") ? 0 : 1);
    }//GEN-LAST:event_bin1ActionPerformed

    private void bin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bin2ActionPerformed
        setBit(1, ((JButton) evt.getSource()).getText().equals("1") ? 0 : 1);
    }//GEN-LAST:event_bin2ActionPerformed

    private void bin3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bin3ActionPerformed
        setBit(2, ((JButton) evt.getSource()).getText().equals("1") ? 0 : 1);
    }//GEN-LAST:event_bin3ActionPerformed

    private void bin4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bin4ActionPerformed
        setBit(4, ((JButton) evt.getSource()).getText().equals("1") ? 0 : 1);
    }//GEN-LAST:event_bin4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bin1;
    private javax.swing.JButton bin2;
    private javax.swing.JButton bin3;
    private javax.swing.JButton bin4;
    private javax.swing.JPanel binKeyPanel0;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel12;
    // End of variables declaration//GEN-END:variables
}
