package com.calculator.graphing;

import com.codes.test;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class FunctionPanel extends javax.swing.JPanel {

    public List<BasicFunction> functions = new ArrayList();

    public FunctionPanel() {
        initComponents();
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(50);
        addSingleFunctionPanel();
    }

    public void addSingleFunctionPanel() {
        SingleFunctionPanel functionPanel = new SingleFunctionPanel() {
            @Override
            public void addNewSingleFunctionPanel() {
                addSingleFunctionPanel();
            }

            public void removeFunction() {
                if (basicFunction != null) {
                    functions.remove(basicFunction);
                }
            }
        };
        jPanel1.add(functionPanel, BorderLayout.CENTER);
        functionPanel.jTextField1.requestFocus();
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public void setFunctions(List<BasicFunction> funcs) {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(350, 32767));
        setPreferredSize(new java.awt.Dimension(350, 300));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        test f = new test();
        GraphPanel gp = new GraphPanel();
        FunctionPanel fp = new FunctionPanel();
        f.add(gp);
        f.add(fp);
        new Thread(gp).start();
        f.setVisible(true);
        f.setSize(400, 400);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
