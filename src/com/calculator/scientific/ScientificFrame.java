package com.calculator.scientific;

import com.amath.advacedmath.calculate.CNumber;
import com.amath.advacedmath.tokenizing.TokenList;
import com.calculator.ui.Ui;
import com.calculator.commoncalculator.history.HistoryCell;
import com.calculator.commoncalculator.history.HistoryPanel;
import com.calculator.commoncalculator.memory.MemoryBar;
import com.calculator.commoncalculator.memory.MemoryCell;
import com.calculator.commoncalculator.memory.MemoryPanel;
import com.calculator.scientific.functions.FunctionsPanel;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ScientificFrame extends javax.swing.JPanel {

    public ScientificNumberPanel numberPanel;
    public FunctionsPanel functionsPanel;
    public ScientificKeyPanel keyPanel;
    public HistoryPanel historyPanel;
    public MemoryPanel memoryPanel;
    public MemoryBar memoryBar;

    public ScientificFrame() {
        initComponents();
        numberPanel = new ScientificNumberPanel(Boolean.TRUE) {
            @Override
            public void addHistory(TokenList equation, CNumber answer) {
                newHistory(equation, answer);
            }
        };
        functionsPanel=new FunctionsPanel(numberPanel);
        keyPanel = new ScientificKeyPanel(numberPanel);
        historyPanel = new HistoryPanel();
        memoryPanel = new MemoryPanel();
        memoryBar=new MemoryBar(memoryPanel, numberPanel);
        jTabbedPane1.add("History",historyPanel);
        jTabbedPane1.add("Memory",memoryPanel);
        mainPanel.add(numberPanel);
        mainPanel.add(memoryBar);
        JSeparator seperater = new JSeparator(SwingConstants.HORIZONTAL);
        Ui.setSize(seperater, 10000, 10);
        mainPanel.add(seperater);
        mainPanel.add(functionsPanel);
        mainPanel.add(keyPanel);
        numberPanel.setAlignmentY(0.f);
        memoryBar.setAlignmentY(0.f);
        keyPanel.setAlignmentY(0.f);
    }

    public void newHistory(TokenList tl, CNumber nu) {
        historyPanel.addHistory(new HistoryCell(tl, nu) {
            @Override
            public void historyItemClicked(TokenList equation, CNumber answer) {
                numberPanel.setEquation(equation);
                numberPanel.setNumber(answer);
            }
        });
    }

    public void newMemory(CNumber n) {
        memoryPanel.newMemory(new MemoryCell(n) {
            @Override
            public void memoryItemClicked(CNumber num) {
                numberPanel.setNumber(num);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidePanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        mainPanel = new javax.swing.JPanel();

        sidePanel.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        sidePanel.setMinimumSize(new java.awt.Dimension(230, 600));
        sidePanel.setPreferredSize(new java.awt.Dimension(230, 600));
        sidePanel.setLayout(new java.awt.CardLayout());
        sidePanel.add(jTabbedPane1, "card2");

        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        mainPanel.setMaximumSize(new java.awt.Dimension(10000, 10000));
        mainPanel.setMinimumSize(new java.awt.Dimension(300, 400));
        mainPanel.setPreferredSize(new java.awt.Dimension(300, 400));
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.PAGE_AXIS));
        add(mainPanel);
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ScientificFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Color background=new Color(30,30,30);
        Color background2=new Color(50,50,50);
        Color foreground=new Color(200,200,200);
        UIManager.put("Button.background", background2);
        UIManager.put("Button.foreground", foreground);
        UIManager.put("Button.focusedBorderColor", background);
        UIManager.put("TextField.foreground", foreground);
        UIManager.put("ToggleButton.foreground", foreground);
//        UIManager.put("Button.font", new Font("Dialog",1,18));
        UIManager.put("TextField.background", background);
        UIManager.put("ToggleButton.background", background);
        UIManager.put("ToggleButton.selectedBackground", new Color(0,8,10));
        UIManager.put("Panel.background", background);
        UIManager.put("Frame.background", background);
        ScientificFrame p = new ScientificFrame();
        JFrame frame = new JFrame("Common number field");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 300, 400);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new javax.swing.BoxLayout(frame.getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(p);
        frame.pack();
        new Thread(() -> {
            double d = 0;
            while (d < 300) {
                d++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ScientificFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.exit(0);
        }).start();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JPanel mainPanel;
    public javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
