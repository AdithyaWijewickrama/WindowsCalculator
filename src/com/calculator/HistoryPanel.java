package com.calculator;

import com.codes.test;
import static com.main.FrontFrame.APP_HOVER;
import static com.calculator.MemoryPanel.calTxt;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class HistoryPanel extends javax.swing.JPanel {

    public int width = 325;

    public HistoryPanel() {
        initComponents();
    }

    public void newCell(String t1, String t2) {
        add(getCell(t1, t2));
        validate();
        repaint();
        doLayout();
    }

    public void clear() {
        removeAll();
        add(jLabel1);
        jLabel1.setVisible(true);
        validate();
        repaint();
        doLayout();
    }

    public void removeCell(JPanel p) {
        this.remove(p);
        if (getComponents().length == 1) {
            clear();
        }
    }

    public JPanel getCell(String t1, String t2) {
        JPanel p = new JPanel();
        JPopupMenu popup = new javax.swing.JPopupMenu();
        JMenuItem copy = new javax.swing.JMenuItem();
        JMenuItem delete = new javax.swing.JMenuItem();
        copy.addActionListener((ActionEvent e) -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(t2), new StringSelection(t2));
        });
        copy.setText("Copy");
        popup.add(copy);

        delete.setText("Delete");
        popup.add(delete);
        delete.addActionListener((ActionEvent e) -> {
            removeCell(p);
        });
        JTextField ef = new javax.swing.JTextField();
        JTextField af = new javax.swing.JTextField();
        MouseListener l = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                p.setBorder(new LineBorder(APP_HOVER, 1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                p.setBorder(new LineBorder(getBackground(), 4));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(calTxt!=null)
                    MemoryPanel.calTxt.setText(t2 + "");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popup.show((Component) e.getSource(), e.getX(), e.getY());
                }
            }

        };

        ef.addMouseListener(l);
        af.addMouseListener(l);
        p.setMaximumSize(new java.awt.Dimension(width, 75));
        p.setMinimumSize(new java.awt.Dimension(width, 75));
        p.setPreferredSize(new java.awt.Dimension(width, 75));
        p.setLayout(new javax.swing.BoxLayout(p, javax.swing.BoxLayout.PAGE_AXIS));
        p.setBorder(new LineBorder(getBackground(), 4));

        ef.setEditable(false);
        ef.setBorder(null);
        ef.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ef.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ef.setText(t1 + " ");

        p.add(ef);

        af.setEditable(false);
        af.setBorder(null);
        af.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        af.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        af.setText(t2 + " ");
        p.add(af);
        return p;
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("Copy");
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Delete");
        jPopupMenu1.add(jMenuItem2);

        setMaximumSize(new java.awt.Dimension(265, 2147483647));
        setMinimumSize(new java.awt.Dimension(265, 26));
        setPreferredSize(new java.awt.Dimension(265, 26));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                formComponentRemoved(evt);
            }
        });

        jLabel1.setText("There's no histry yet");
        add(jLabel1);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        if (getComponentCount() > 0) {
            jLabel1.setVisible(false);
        } else {
            jLabel1.setVisible(true);
        }
    }//GEN-LAST:event_formComponentAdded

    private void formComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentRemoved
        if (getComponentCount() > 0) {
            jLabel1.setVisible(false);
        } else {
            jLabel1.setVisible(true);
        }
    }//GEN-LAST:event_formComponentRemoved

    public static void main(String[] args) {
        test f = new test();
        HistoryPanel hp = new HistoryPanel();
        f.getContentPane().add(hp);
        for (int i = 0; i < 3; i++) {
            hp.newCell("1+2=", "3");
        }
        hp.setVisible(true);
        f.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel1;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}
