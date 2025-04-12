
package com.calculator.graphing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphPlotter extends JFrame {

    private JTextField functionField;
    private GraphPanel graphPanel;

    public GraphPlotter() {
        setTitle("Function Graph Plotter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        functionField = new JTextField("y=x^2-1");
        JButton plotButton = new JButton("Plot");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Function: "), BorderLayout.WEST);
        inputPanel.add(functionField, BorderLayout.CENTER);
        inputPanel.add(plotButton, BorderLayout.EAST);

        graphPanel = new GraphPanel();

        add(inputPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        Thread t = new Thread(graphPanel);
        t.start();
        plotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.addFunction(new BasicFunction(functionField.getText().replace("y=", ""),"x"));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GraphPlotter graphPlotter = new GraphPlotter();
                graphPlotter.setVisible(true);
            }
        });
    }
}
