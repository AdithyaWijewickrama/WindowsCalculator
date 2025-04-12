package com.calculator.commoncalculator.history;

import com.amath.advacedmath.calculate.CNumber;
import com.amath.advacedmath.tokenizing.TokenList;
import com.helper.test;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;

public class HistoryPanel extends javax.swing.JPanel {

    public int width = 325;
    public ArrayList<JPanel> cells;

    public HistoryPanel() {
        initComponents();
        cells = new ArrayList<>();
    }

    public void addHistory(HistoryCell cell) {
        add(cell);
        validate();
        repaint();
        doLayout();
    }

    public void addHistory(String equatoin, String answer) {
        noCells.setVisible(false);
        jPanel1.add(new HistoryCell(null, null) {
            @Override
            public void historyItemClicked(TokenList equation, CNumber answer) {
            }

        });
        jPanel1.validate();
        jPanel1.repaint();
        jPanel1.doLayout();
    }

    public void clear() {
        noCells.setVisible(true);
        jPanel1.removeAll();
        jPanel1.validate();
        jPanel1.repaint();
    }

    public void removeCell(JPanel p) {
        this.remove(p);
        if (getComponents().length == 1) {
            clear();
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        noCells = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

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
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        noCells.setText("There's no histry yet");
        noCells.setAlignmentX(0.5F);
        add(noCells);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(300, 32767));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(230, 400));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 400));
        jScrollPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jScrollPane1ComponentResized(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(300, 10000));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        if (getComponentCount() > 0) {
            noCells.setVisible(false);
        } else {
            noCells.setVisible(true);
        }
    }//GEN-LAST:event_formComponentAdded

    private void formComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentRemoved
        if (getComponentCount() > 0) {
            noCells.setVisible(false);
        } else {
            noCells.setVisible(true);
        }
    }//GEN-LAST:event_formComponentRemoved

    private void jScrollPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane1ComponentResized
        jPanel1.setMaximumSize(new Dimension(jScrollPane1.getSize().width, 100000));
////        jPanel1.setSize(new Dimension(jScrollPane1.getSize().width, 100000));
//        jPanel1.setSize(new Dimension(jScrollPane1.getSize().width, 100000));
    }//GEN-LAST:event_jScrollPane1ComponentResized

    public static void main(String[] args) {
        test f = new test();
        HistoryPanel hp = new HistoryPanel();
        f.getContentPane().add(hp);
        for (int i = 0; i < 30; i++) {
            hp.addHistory("1+2=", "3");
        }
        hp.setVisible(true);
        f.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPopupMenu jPopupMenu1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel noCells;
    // End of variables declaration//GEN-END:variables
}
