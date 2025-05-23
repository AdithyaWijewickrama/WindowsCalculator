package com.calculator.grapher;

import com.calculator.programmer.ProgrammerFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GraphingFrame extends javax.swing.JPanel {

    GraphPanel graphPanel;
    FunctionPanel functionPanel;
    Thread graghingPanelThread;

    public GraphingFrame() {
        initComponents();
        graphPanel = new GraphPanel();
        functionPanel=new FunctionPanel();
        graphPanel.setAlignmentY(0);
        functionPanel.setAlignmentY(0);
        functionPanel.setMaximumSize(new Dimension(400, 10000));
        graghingPanelThread = new Thread(graphPanel);
        add(graphPanel);
        add(functionPanel);
    }

    public GraphPanel getGraphPanel(){
        return graphPanel;
    }

    public Thread getGraghingPanelThread() {
        return graghingPanelThread;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setAlignmentX(0.0F);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ProgrammerFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        GraphingFrame p = new GraphingFrame();
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
                    Logger.getLogger(ProgrammerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.exit(0);
        }).start();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            frame.setVisible(true);
            p.graghingPanelThread.start();
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
