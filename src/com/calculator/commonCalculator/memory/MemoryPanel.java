package com.calculator.commoncalculator.memory;

import com.calculate.number.CNumber;
import com.helper.test;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class MemoryPanel extends javax.swing.JPanel {

    public static ArrayList<MemoryCell> cells = new ArrayList<>();
    public static int CURRENT_CELL = 0;

    public MemoryPanel() {
        initComponents();
    }

    public void newMemory(MemoryCell cell) {
        jLabel1.setVisible(false);
        cells.add(cell);
        cell.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                System.out.println("dsfsfd");
            }
        });
        jPanel1.add(cell);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public static void increment() {
        if (!cells.isEmpty()) {
            cells.get(0).increment();
        }
    }

    public void clearAllMemory() {
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.repaint();
        jPanel1.doLayout();
        jLabel1.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(265, 2147483647));
        setMinimumSize(new java.awt.Dimension(265, 16));
        setPreferredSize(new java.awt.Dimension(265, 0));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                formComponentRemoved(evt);
            }
        });
        setLayout(new java.awt.CardLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(300, 32767));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(270, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(270, 400));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel1.setText("There's nothing saved in memory");
        jLabel1.setAlignmentX(0.5F);
        jPanel1.add(jLabel1);

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1, "card3");
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
        MemoryPanel hp = new MemoryPanel();
        f.getContentPane().add(hp);
        for (int i = 0; i < 30; i++) {
            hp.newMemory(new MemoryCell(CNumber.parseNumber(0)) {
                @Override
                public void memoryItemClicked(CNumber num) {
                    System.out.println("clicked");
                }
            });
        }
        hp.setVisible(true);
        f.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
