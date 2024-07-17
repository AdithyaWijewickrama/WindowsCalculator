package com.main;

import com.codes.test;
import static com.main.FrontFrame.APP_HOVER;
import static com.main.FrontFrame.Exact;
import static com.main.MemoryPanel.memStore;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class MemoryPanel extends javax.swing.JPanel {

    public static double memStore = 0;
    public static JTextField calTxt;
    public static JTextField eqtTxt;
    public MouseListener listener;
    public Double val = 0.;
    public Double cval = 0.;

    public MemoryPanel() {
        initComponents();
    }

    public void newCell(double val) {
        memStore = val;
        add(new Cell(val).newCell());
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
            System.out.println("jkkds");
            clear();
        }
        validate();
        repaint();
        doLayout();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        jLabel1.setText("There's nothing saved in memory");
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
        MemoryPanel hp = new MemoryPanel();
        f.getContentPane().add(hp);
        for (int i = 0; i < 3; i++) {
            hp.newCell(687.);
        }
        hp.setVisible(true);
        f.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    class Cell extends JPanel {

        int w = 325;
        double val;
        double cval;
        JTextField tf;
        JPanel p;
        JButton mc;
        JButton mAdd;
        JButton mSub;

        public Cell(double val) {
            this.val = val;
        }

        public JPanel newCell() {
            JPopupMenu popup = new javax.swing.JPopupMenu();
            JMenuItem copy = new javax.swing.JMenuItem("Copy");
            JMenuItem mad = new javax.swing.JMenuItem("M+ Add to memmory item");
            JMenuItem msub = new javax.swing.JMenuItem("M- Substract from memory item");
            JMenuItem mclear = new javax.swing.JMenuItem("MC Clear memory item");

            popup.add(mclear);
            popup.add(mad);
            popup.add(msub);
            popup.add(copy);
            copy.addActionListener((ActionEvent e) -> {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(val + ""), new StringSelection(val + ""));
            });
            mad.addActionListener((ActionEvent e) -> {
                memoryAdd();
            });
            msub.addActionListener((ActionEvent e) -> {
                memorySub();
            });
            mclear.addActionListener((ActionEvent e) -> {
                removeCell(this);
            });
            MouseListener l = new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBorder(new LineBorder(APP_HOVER, 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBorder(new LineBorder(getBackground(), 4));
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    clicked();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popup.show((Component) e.getSource(), e.getX(), e.getY());
                    }
                }
            };
            tf = new JTextField("0");
            p = new JPanel();
            mc = new JButton();
            mAdd = new JButton();
            mSub = new JButton();
            setMaximumSize(new java.awt.Dimension(w, 100));
            setMinimumSize(new java.awt.Dimension(w, 100));
            setPreferredSize(new java.awt.Dimension(w, 100));
            setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
            addMouseListener(l);
            mc.addMouseListener(l);
            mAdd.addMouseListener(l);
            mSub.addMouseListener(l);
            tf.setEditable(false);
            tf.setBorder(null);
            tf.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
            tf.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            tf.addMouseListener(l);
            add(tf);
            setValue(val);

            p.setAlignmentX(0.0F);
            p.setMaximumSize(new java.awt.Dimension(w, 45));
            p.setMinimumSize(new java.awt.Dimension(w, 45));
            p.setPreferredSize(new java.awt.Dimension(w, 45));
            p.setLayout(new javax.swing.BoxLayout(p, javax.swing.BoxLayout.LINE_AXIS));
            add(p);
            mc.setText("MC");
            mc.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mclear.doClick();
                }
            });
            p.add(mc);

            mAdd.setText("M+");
            mAdd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    memoryAdd();
                }
            });
            p.add(mAdd);

            mSub.setText("M-");
            mSub.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    memorySub();
                }
            });
            mc.setMaximumSize(new Dimension(40, 30));
            mAdd.setMaximumSize(new Dimension(40, 30));
            mSub.setMaximumSize(new Dimension(40, 30));
            mc.setBorder(new LineBorder(p.getBackground()));
            mAdd.setBorder(new LineBorder(p.getBackground()));
            mSub.setBorder(new LineBorder(p.getBackground()));
            p.add(mSub);
            p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
            p.setAlignmentX(0.0f);

            return this;
        }

        public void clicked() {
            if (calTxt != null) {
                MemoryPanel.calTxt.setText(cval + "");
            }
        }

        public void setValue(Double val) {
            cval = val;
            tf.setText(Exact(val) + " ");
        }

        public void memoryAdd() {
            setValue(cval + memStore);
        }

        public void memorySub() {
            setValue(cval - memStore);
        }

    }
}
