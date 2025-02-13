package com.main;

import com.calculator.dateCalculation.DateCalculation;
import com.calculator.graphing.Graphical;
import static com.calculator.graphing.Graphical.getUIColor;
import com.database.UIMemory.Previous;
import com.formdev.flatlaf.FlatDarkLaf;
import static com.calculator.graphing.Graphical.getUIColorType;
import com.calculator.graphing.GraphingFrame;
import com.calculator.programmer.ProgrammerFrame;
import com.calculator.scientific.ScientificFrame;
import com.calculator.standard.StandardFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.PopupMenu;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author AW Developer
 */
public class Main extends javax.swing.JFrame {

    public static final int MARGIN = 5;
    public static final int TOP_HEIGHT = 40;
    public static Dimension maxSize = new Dimension(6000, 6000);
    public static Dimension minSize = new Dimension(400, 600);
    private final StandardFrame standardFrame=new StandardFrame();
    private final ScientificFrame scientificFrame=new ScientificFrame();
    private final ProgrammerFrame programmerFrame=new ProgrammerFrame();
    private final GraphingFrame graphingFrame=new GraphingFrame();
    private final DateCalculation dateCalculation=new DateCalculation();

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        ButtonGroup group = new ButtonGroup();
        group.add(jToggleButton1);
        group.add(jToggleButton2);
        group.add(jToggleButton3);
        group.add(jToggleButton4);
        group.add(jToggleButton5);
        group.add(jToggleButton6);
        group.add(jToggleButton7);
        group.add(jToggleButton8);
        group.add(jToggleButton9);
        group.add(jToggleButton10);
        group.add(jToggleButton11);
        group.add(jToggleButton12);
        group.add(jToggleButton13);
        group.add(jToggleButton14);
        group.add(jToggleButton15);
        group.add(jToggleButton16);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        Navigation = new javax.swing.JPanel();
        Navigation.setVisible(false);
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        navBtns = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton14 = new javax.swing.JToggleButton();
        jToggleButton15 = new javax.swing.JToggleButton();
        jToggleButton16 = new javax.swing.JToggleButton();
        jToggleButton17 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        topBar = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        CalType = new javax.swing.JLabel();
        histryBtn = new javax.swing.JButton();
        calculatorPanel = new javax.swing.JPanel();

        jDialog1.setUndecorated(true);
        jDialog1.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                jDialog1WindowLostFocus(evt);
            }
        });
        jDialog1.getContentPane().setLayout(new java.awt.CardLayout());

        Navigation.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        Navigation.setAlignmentY(0.0F);
        Navigation.setMaximumSize(new java.awt.Dimension(250, 32816));
        Navigation.setMinimumSize(new java.awt.Dimension(250, 0));
        Navigation.setPreferredSize(new java.awt.Dimension(250, 390));
        Navigation.setLayout(new javax.swing.BoxLayout(Navigation, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton1.setBackground(getUIColor());
        jButton1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jButton1.setIcon(Graphical.getImage("src\\Images\\Navigation-"+getUIColorType()+".png",15,10));
        jButton1.setToolTipText("Close Navigation");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(null);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        Navigation.add(jPanel3);

        jScrollPane5.setBackground(getUIColor());
        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setMaximumSize(new java.awt.Dimension(270, 32816));
        jScrollPane5.setMinimumSize(new java.awt.Dimension(270, 60));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(270, 220));

        navBtns.setMaximumSize(new java.awt.Dimension(270, 845));
        navBtns.setMinimumSize(new java.awt.Dimension(270, 845));
        navBtns.setPreferredSize(new java.awt.Dimension(270, 845));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("  Calculator");
        jLabel3.setMaximumSize(new java.awt.Dimension(265, 40));
        jLabel3.setMinimumSize(new java.awt.Dimension(265, 40));
        jLabel3.setPreferredSize(new java.awt.Dimension(265, 40));
        navBtns.add(jLabel3);

        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton1.setText("Standard");
        jToggleButton1.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton1.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton1.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton1);

        jToggleButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton2.setText("Scientific");
        jToggleButton2.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton2.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton2.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton2);

        jToggleButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton3.setText("Graphing");
        jToggleButton3.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton3.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton3.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton3.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton3);

        jToggleButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton4.setText("Date Calculation");
        jToggleButton4.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton4.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton4.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton4.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton4);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("  Convertor");
        jLabel4.setMaximumSize(new java.awt.Dimension(265, 40));
        jLabel4.setMinimumSize(new java.awt.Dimension(265, 40));
        jLabel4.setPreferredSize(new java.awt.Dimension(265, 40));
        navBtns.add(jLabel4);

        jToggleButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton5.setText("Currency");
        jToggleButton5.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton5.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton5.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton5.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton5);

        jToggleButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton6.setText("Volume");
        jToggleButton6.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton6.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton6.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton6.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton6);

        jToggleButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton7.setText("Length");
        jToggleButton7.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton7.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton7.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton7.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton7);

        jToggleButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton8.setText("Weight & Mass");
        jToggleButton8.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton8.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton8.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton8.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton8);

        jToggleButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton9.setText("Temperature");
        jToggleButton9.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton9.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton9.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton9.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton9);

        jToggleButton10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton10.setText("Energy");
        jToggleButton10.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton10.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton10.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton10.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton10ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton10);

        jToggleButton11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton11.setText("Area");
        jToggleButton11.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton11.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton11.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton11.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton11ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton11);

        jToggleButton12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton12.setText("Speed");
        jToggleButton12.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton12.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton12.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton12.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton12ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton12);

        jToggleButton13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton13.setText("Time");
        jToggleButton13.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton13.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton13.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton13.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton13ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton13);

        jToggleButton14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton14.setText("Power");
        jToggleButton14.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton14.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton14.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton14.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton14ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton14);

        jToggleButton15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton15.setText("Data");
        jToggleButton15.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton15.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton15.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton15.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton15ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton15);

        jToggleButton16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton16.setText("Presure");
        jToggleButton16.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton16.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton16.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton16.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton16ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton16);

        jToggleButton17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToggleButton17.setText("Angle");
        jToggleButton17.setBorder(new com.formdev.flatlaf.ui.FlatRoundBorder());
        jToggleButton17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton17.setMaximumSize(new java.awt.Dimension(265, 40));
        jToggleButton17.setMinimumSize(new java.awt.Dimension(265, 40));
        jToggleButton17.setPreferredSize(new java.awt.Dimension(265, 40));
        jToggleButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton17ActionPerformed(evt);
            }
        });
        navBtns.add(jToggleButton17);

        jScrollPane5.setViewportView(navBtns);

        Navigation.add(jScrollPane5);

        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 40));

        jButton26.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton26.setText("   About");
        jButton26.setBorder(null);
        jButton26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton26.setMargin(new java.awt.Insets(0, 5, 0, 5));
        jButton26.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton26.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton26.setPreferredSize(new java.awt.Dimension(270, 40));
        jPanel2.add(jButton26);

        Navigation.add(jPanel2);

        jDialog1.getContentPane().add(Navigation, "card2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setMaximumSize(maxSize);
        setMinimumSize(minSize);
        setPreferredSize(new Dimension(Previous.WIDTH,Previous.HEIGHT));
        getContentPane().setLayout(new java.awt.CardLayout(MARGIN, MARGIN));

        mainPanel.setMaximumSize(new Dimension(maxSize.width-MARGIN*2,maxSize.height-MARGIN*2));
        mainPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        mainPanel.setPreferredSize(new Dimension(Previous.WIDTH-MARGIN*2,Previous.HEIGHT-MARGIN*2));
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.PAGE_AXIS));

        topBar.setAlignmentY(0.0F);
        topBar.setMaximumSize(new java.awt.Dimension(10000, 60));
        topBar.setMinimumSize(new java.awt.Dimension(310, 60));
        topBar.setPreferredSize(new Dimension(Previous.WIDTH-MARGIN*2,TOP_HEIGHT));
        topBar.setLayout(new java.awt.CardLayout());

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jButton3.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jButton3.setIcon(Graphical.getImage("src\\Images\\Navigation-"+getUIColorType()+".png",15,10));
        jButton3.setToolTipText("Open Navigation");
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorder(null);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(50, 30));
        jButton3.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton3.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jPanel1.setAlignmentY(0.0F);
        jPanel1.setMaximumSize(new java.awt.Dimension(10, 30));
        jPanel1.setMinimumSize(new java.awt.Dimension(10, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(10, 30));
        jPanel4.add(jPanel1);

        CalType.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        CalType.setText("Standard");
        CalType.setAlignmentY(0.0F);
        CalType.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        CalType.setMaximumSize(new java.awt.Dimension(10000, 30));
        CalType.setMinimumSize(new java.awt.Dimension(250, 30));
        CalType.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel4.add(CalType);

        histryBtn.setText("H");
        histryBtn.setAlignmentX(1.0F);
        histryBtn.setAlignmentY(0.0F);
        histryBtn.setBorder(null);
        histryBtn.setFocusable(false);
        histryBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        histryBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        histryBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        histryBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        histryBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        histryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                histryBtnActionPerformed(evt);
            }
        });
        jPanel4.add(histryBtn);

        topBar.add(jPanel4, "card2");

        mainPanel.add(topBar);

        calculatorPanel.setLayout(new java.awt.CardLayout());
        mainPanel.add(calculatorPanel);

        getContentPane().add(mainPanel, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jDialog1.setLocation(getLocationOnScreen().x + 8, getLocationOnScreen().y + 33);
        jDialog1.setSize(270, getHeight() - 38);
        jDialog1.setVisible(true);
        jScrollPane5.getVerticalScrollBar().setUnitIncrement(10);
        jScrollPane5.getVerticalScrollBar().setBackground(new Color(30, 30, 30));
        jScrollPane5.getVerticalScrollBar().revalidate();
        jScrollPane5.getVerticalScrollBar().repaint();
        System.out.println(jScrollPane5.getVerticalScrollBar().getBounds());
        jScrollPane5.revalidate();
        jScrollPane5.repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void histryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histryBtnActionPerformed

    }//GEN-LAST:event_histryBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jDialog1.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDialog1WindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog1WindowLostFocus
        jDialog1.dispose();
    }//GEN-LAST:event_jDialog1WindowLostFocus

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        setFrame(SCIENTIFIC);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        setFrame(STANDARD);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        setFrame(CURRENCY);
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        setFrame(VOLUME);
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        setFrame(WEIGHT_AND_MASS);
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed
        setFrame(TEMPERATURE);
    }//GEN-LAST:event_jToggleButton9ActionPerformed

    private void jToggleButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton11ActionPerformed
        setFrame(AREA);
    }//GEN-LAST:event_jToggleButton11ActionPerformed

    private void jToggleButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton12ActionPerformed
        setFrame(SPEED);
    }//GEN-LAST:event_jToggleButton12ActionPerformed

    private void jToggleButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton14ActionPerformed
        setFrame(POWER);
    }//GEN-LAST:event_jToggleButton14ActionPerformed

    private void jToggleButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton15ActionPerformed
        setFrame(DATA);
    }//GEN-LAST:event_jToggleButton15ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        setFrame(GRAPHING);
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        setFrame(DATE_CALCULATION);
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        setFrame(VOLUME);
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton10ActionPerformed
        setFrame(ENERGY);
    }//GEN-LAST:event_jToggleButton10ActionPerformed

    private void jToggleButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton13ActionPerformed
        setFrame(TIME);
    }//GEN-LAST:event_jToggleButton13ActionPerformed

    private void jToggleButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton16ActionPerformed
        setFrame(PRESURE);
    }//GEN-LAST:event_jToggleButton16ActionPerformed

    private void jToggleButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton17ActionPerformed
        setFrame(ANGLE);
    }//GEN-LAST:event_jToggleButton17ActionPerformed

    public void setFrame(int type) {
        calculatorPanel.removeAll();
        switch (type) {
            case STANDARD:
                calculatorPanel.add(standardFrame);
                break;
            case SCIENTIFIC:
                calculatorPanel.add(scientificFrame);
                break;
            case PROGRAMMER:
                calculatorPanel.add(programmerFrame);
                break;
            case GRAPHING:
                calculatorPanel.add(graphingFrame);
                break;
            case DATE_CALCULATION:
                calculatorPanel.add(dateCalculation);
                break;
            case CURRENCY:
            case VOLUME:
            case LENGTH:
            case WEIGHT_AND_MASS:
            case TEMPERATURE:
            case ENERGY:
            case AREA:
            case SPEED:
            case TIME:
            case POWER:
            case DATA:
            case PRESURE:
            case ANGLE:
                break;
            default:
                throw new AssertionError();
        }
        calculatorPanel.revalidate();
        calculatorPanel.repaint();
    }

    public static final int STANDARD = 0;
    public static final int SCIENTIFIC = 1;
    public static final int GRAPHING = 2;
    public static final int PROGRAMMER = 3;
    public static final int DATE_CALCULATION = 4;
    public static final int CURRENCY = 5;
    public static final int VOLUME = 6;
    public static final int LENGTH = 7;
    public static final int WEIGHT_AND_MASS = 8;
    public static final int TEMPERATURE = 9;
    public static final int ENERGY = 10;
    public static final int AREA = 11;
    public static final int SPEED = 12;
    public static final int TIME = 13;
    public static final int POWER = 14;
    public static final int DATA = 15;
    public static final int PRESURE = 16;
    public static final int ANGLE = 17;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Color background = new Color(30, 30, 30);
        Color background2 = new Color(50, 50, 50);
        Color foreground = new Color(200, 200, 200);
        UIManager.put("Button.background", background2);
        UIManager.put("Label.background", background2);
        UIManager.put("ScrollPane.background", background2);
        UIManager.put("Button.foreground", foreground);
        UIManager.put("TextField.foreground", foreground);
        UIManager.put("ToggleButton.foreground", foreground);
        UIManager.put("Button.font", new Font("Dialog", Font.PLAIN, 18));
        UIManager.put("TextField.background", background);
        UIManager.put("ToggleButton.background", background);
        UIManager.put("ToggleButton.selectedBackground", background2);
        UIManager.put("ToggleButton.selectedBorder", background2);
        UIManager.put("Panel.background", background);
        UIManager.put("Frame.background", background);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CalType;
    private javax.swing.JPanel Navigation;
    private javax.swing.JPanel calculatorPanel;
    private javax.swing.JButton histryBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton17;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navBtns;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
