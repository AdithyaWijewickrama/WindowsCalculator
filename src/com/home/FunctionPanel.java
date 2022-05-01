package com.home;

import com.codes.test;
import expression.Function;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import static window.GraphingPanel.COLOR;
import static window.GraphingPanel.FUNC;
import static window.GraphingPanel.STYLE;

public class FunctionPanel extends javax.swing.JPanel {

    public List<Object[]> functions = new ArrayList();

    public FunctionPanel() {
        initComponents();
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(50);
        addNew();
    }

    public void addNew() {
        SingleFunctionPanel panel = new SingleFunctionPanel() {
            @Override
            public void setFunction(Function fnc, Color c, char style) {
                Object[] func = new Object[3];
                func[FUNC] = fnc;
                func[COLOR] = Color.BLUE;
                func[STYLE] = '-';
                functions.add(func);
                setFunctions(functions);
                addNew();
                jPanel1.validate();
            }

            @Override
            public void setFuncColor(Color c) {

            }
        };
        panel.setAlignmentY(0);
        jPanel1.add(panel);
    }

    public void setFunctions(List funcs) {
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
        f.add(new FunctionPanel());
        f.setVisible(true);
        f.setSize(400, 400);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
