/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calculator.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowFocusListener;

public class DialogPopupExample extends JPanel {
    private final JDialog popupDialog;

    public DialogPopupExample(JFrame parentFrame) {
        setLayout(new BorderLayout());

        // Add base components
        JPanel basePanel = new JPanel();
        basePanel.setPreferredSize(new Dimension(400, 300));
        basePanel.setBackground(Color.LIGHT_GRAY);
        basePanel.setLayout(null);

        JButton button = new JButton("Show Popup");
        button.setBounds(150, 50, 120, 30);
        basePanel.add(button);

        add(basePanel, BorderLayout.CENTER);

        // Create popup dialog
        popupDialog = new JDialog(parentFrame, "Popup", false);
        popupDialog.setSize(200, 100);
        popupDialog.setLocationRelativeTo(parentFrame);
        popupDialog.setLayout(new BorderLayout());
        popupDialog.setUndecorated(true); // Remove title bar and buttons

        // Add child components to the popup dialog
        JLabel label = new JLabel("Popup Content");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        popupDialog.add(label, BorderLayout.CENTER);

        // Add window focus listener to dispose when clicking outside
        popupDialog.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                popupDialog.dispose();
            }
        });

        // Add action listener to the button
        button.addActionListener(e -> showPopup());
    }

    public void showPopup() {
        popupDialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dialog Popup Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DialogPopupExample(frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}