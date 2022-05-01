package com.home;

import com.codes.test;
import expression.Function;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import window.GraphingPanel;
import static window.GraphingPanel.COLOR;
import static window.GraphingPanel.FUNC;
import static window.GraphingPanel.STYLE;
import static window.GraphingPanel.VISIBLE;

public class FunctionPanel extends javax.swing.JPanel {

    public List<Object[]> functions = new ArrayList();

    public FunctionPanel() {
        initComponents();
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(50);
        addNew();
    }

    public void addNew() {
        SingleFunctionPanel panel = new SingleFunctionPanel() {
            Object[] func = new Object[4];

            @Override
            public int setFunction(int id, Function fnc, Color c, char style,boolean vis) {
                func[FUNC] = fnc;
                func[COLOR] = c;
                func[STYLE] = style;
                func[VISIBLE] = true;
                if (id != -1) {
                    functions.remove(id);
                }
                if (id == functions.size() - 1) {
                    addNew();
                }
                functions.add(func);
                setFunctions(functions);
                jPanel1.validate();
                return functions.size() - 1;
            }

            @Override
            public void setFuncColor(int id,Color c) {
                functions.get(id)[COLOR]=c;
            }

            @Override
            public void setFuncStyle(int id, char c) {
                functions.get(id)[STYLE]=c;
            }

            @Override
            public void setFuncVisible(int id, boolean b) {
                functions.get(id)[VISIBLE]=b;
            }
        };
        panel.setAlignmentY(0);
        jPanel1.add(panel);
    }

    public void setFunctions(List<Object[]> funcs) {
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
        GraphingPanel gp=new GraphingPanel();
        FunctionPanel fp = new FunctionPanel(){
            @Override
            public void setFunctions(List<Object[]> funcs) {
                gp.functions=(ArrayList<Object[]>) funcs;
            }
            
        };
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
