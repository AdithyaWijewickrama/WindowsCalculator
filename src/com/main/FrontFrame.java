package com.main;

import Programmer.Base;
import static Programmer.Base.OCT;
import Programmer.Decimal;
import com.codes.DateCalculation;
import com.codes.Scifi;
import com.codes.Trigonometry;
import com.codes.Ui;
import com.database.DbConnect;
import static com.database.Sql.Execute;
import static com.database.Sql.ExecuteSQL;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.main.Converts.forgetFocus;
import static com.main.Converts.getComboModel;
import static com.main.Converts.getFocus;
import static com.main.Graphical.getUIColor;
import static com.main.Graphical.getUIColorType;
import grapher.GraphPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.commons.math3.special.Gamma;

public final class FrontFrame extends javax.swing.JFrame {

    public static Color APP_THEME = new Color(0, 102, 51);
    public static Color APP_HOVER = Color.gray;
    static final double DEFONTSIZE = 24;
    static final FlatLaf LIGHT = new FlatLightLaf();
    static final FlatLaf DARK = new FlatDarkLaf();
    Double firstNum = null;
    Double secondNum = null;
    String arithExp = null;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    static boolean AUTOIMPORT = true;
    static boolean ALWAYSONTOP = true;
    static boolean HISTORY = false;
    static boolean scifi_notation = false;
    public boolean startNew = false;
    public Double value = 0.;
    String COLOR = "Wight";
    Prog prog = new Prog(Base.DEC);
    JTextField calTxt;
    public static final char PLUS = '+';
    public static final char SUB = '-';
    public static final char MULT = '*';
    public static final char DIV = '÷';
    public Trigonometry trig;
    public HistryPanel histryPanel;
    public MemoryPanel memoryPanel;
    public DateCalculation dateCalculation;
    public GraphPanel graphingPanel;
    public FunctionPanel functionPanel;
    MouseListener listerner1;

    public FrontFrame() {
        initComponents();
        trig = new Trigonometry();
        conn = DbConnect.connect();
        histryPanel = new HistryPanel();
        memoryPanel = new MemoryPanel();
        dateCalculation = new DateCalculation();
        functionPanel = new FunctionPanel();
        graphingPanel = new GraphPanel();
        jPanel2.add(graphingPanel);
        jScrollPane3.setViewportView(histryPanel);
        jScrollPane4.setViewportView(memoryPanel);
        jScrollPane3.getHorizontalScrollBar().setUnitIncrement(100);
        jScrollPane4.getHorizontalScrollBar().setUnitIncrement(100);
        this.calTxt = cal;
        MemoryPanel.calTxt = calTxt;
        MemoryPanel.eqtTxt = eqtTxt;
        calTxt.requestFocus();
        calTxt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    value = Double.valueOf(calTxt.getText());
                    if (Double.isInfinite(value) || Double.isNaN(value)) {
                        throw new NumberFormatException();
                    }
                    if (firstNum == null) {
                        firstNum = value;
                    } else if (secondNum == null) {
                        secondNum = value;
                    }
                    setFornt();
                } catch (NumberFormatException ex) {
                    value = 0.;
                    startNew = true;
                }
            }
        });
        setBackgrounds(this.getBackground());
        navigation(false);
        hisAndMem.setVisible(false);
        setSize(360, 570);
        setImages(COLOR);
        for (Component c : navBtns.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                btn.addActionListener((ActionEvent e) -> {
                    setCalType(btn.getText());
                    for (Component c1 : navBtns.getComponents()) {
                        if (c1 instanceof JButton) {
                            ((JButton) c1).setSelected(false);
                        }
                    }
                    btn.setSelected(true);
                    if (getCalType().idx >= 5) {
                        int type = getCalType().idx - 5;
                        boolean b = false;
                        if (type != Converts.SPEED && type != Converts.POWER) {
                            unitSelection1.setModel(new DefaultComboBoxModel<>(getComboModel(type)));
                            unitSelection2.setModel(new DefaultComboBoxModel<>(getComboModel(type)));
                        } else {
                            b = true;
                            unitSelection1.setModel(new DefaultComboBoxModel<>(getComboModel(Converts.LENGTH)));
                            unitSelection2.setModel(new DefaultComboBoxModel<>(getComboModel(Converts.LENGTH)));
                            ConCombo3.setModel(new DefaultComboBoxModel<>(getComboModel(Converts.TIME)));
                            ConCombo4.setModel(new DefaultComboBoxModel<>(getComboModel(Converts.TIME)));

                        }
                        ConCombo4.setVisible(b);
                        ConCombo3.setVisible(b);
                        per.setVisible(b);
                        per1.setVisible(b);
                        setConvertedValue(Double.valueOf(calTxt.getText()), type, getOpasite());
                    }
                    navigation(false);
                });
            }
        }
        jButton2.doClick();
        jScrollPane5.getVerticalScrollBar().setUnitIncrement(100);
        setProgHover(() -> {
            setPogrammer(Base.DEC);
        }, decBar, decTxt, decLabel);
        setProgHover(() -> {
            setPogrammer(Base.BIN);
        }, binBar, binTxt, binLabel);
        setProgHover(() -> {
            setPogrammer(Base.OCT);
        }, octBar, octTxt, octLabel);
        setProgHover(() -> {
            setPogrammer(Base.HEX);
        }, hexBar, hexTxt, hexLabel);
    }

    public void setProgHover(Runnable click, JComponent... co) {
        Color cl = co[0].getBackground();
        boolean clc = false;
        for (JComponent c : co) {
            c.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    click.run();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    for (JComponent c1 : co) {
                        c1.setBackground(Color.lightGray);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!clc) {
                        for (JComponent c1 : co) {
                            c1.setBackground(cl);
                        }
                    }
                }
            });
        }
    }

    public void setFornt() {
        Ui.adaptLabelFont(calTxt, 11, 40);
    }

    public void setApearence(FlatLaf LAF) {
        try {
            UIManager.setLookAndFeel(LAF);
            this.dispose();
            conn.close();
            new FrontFrame().setVisible(true);
        } catch (UnsupportedLookAndFeelException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void setBackgrounds(Color color) {
        this.setIconImage(setImage("src\\Images\\Logo.png", 30, 30).getImage());
        if (UIManager.getLookAndFeel().getName().contains("Dark")) {
            COLOR = "Wight";
        } else {
            COLOR = "Black";
        }
        trigonometry.setBackground(color);
        DecimalTypes.setBackground(color);
        logorithms.setBackground(color);
        convertsText2.setBackground(color);
        convertsText1.setBackground(color);
        unitSelection1.setBackground(color);
        unitSelection2.setBackground(color);
        ConCombo3.setBackground(color);
        ConCombo4.setBackground(color);
    }

    public void setImages(String Color) {
//        sqr.setIcon-(setImage("src\\Images\\Square-" + COLOR + ".png", 38, 38));
//        root.setIcon(setImage("src\\Images\\Root-" + COLOR + ".png", 38, 38));
//        fact.setIcon(setImage("src\\Images\\Factorial-" + COLOR + ".png", 38, 38));
//        history.setIcon(setImage("src\\Images\\History-" + COLOR + ".png", 38, 38));
    }

    public ImageIcon setImage(String ImagePath, int Width, int Height) {
        ImageIcon Imge;
        Imge = new ImageIcon(ImagePath);
        java.awt.Image img = Imge.getImage();
        java.awt.Image newimg = img.getScaledInstance(Width, Height, 25);
        ImageIcon imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    public void setFrame() {
        trigonometry.setVisible(false);
        funcs.setVisible(false);
    }

    public double findtheRoot(double num, double root) {
        double Number = Math.pow(num, 1 / root);
        return Number;
    }

    public static double logN(double val, double base) {
        return Math.log(val) / Math.log(base);
    }

    public Double calculate() {
        Double ans = 0.;
        switch (arithExp) {
            case "+":
                ans = firstNum + secondNum;
                break;
            case "-":
                ans = firstNum - secondNum;
                break;
            case "*":
                ans = firstNum * secondNum;
                break;
            case "÷":
                ans = firstNum / secondNum;
                break;
            case "pow":
                ans = Math.pow(firstNum, secondNum);
                break;
            case "root":
                ans = findtheRoot(firstNum, secondNum);
                break;
            case "log base":
                ans = logN(firstNum, secondNum);
                break;
        }
        System.out.println("[Calculating]" + firstNum + "" + arithExp + "" + secondNum + "=" + ans);
        return ans;
    }

    public String calculateTrig(String opar) {
        Double ans = opar.contains("-1") ? trig.calInverse(firstNum, opar) : trig.cal(firstNum, opar);
        String Eqt = "" + opar + "(" + Exact(firstNum) + ") = " + Exact(ans) + "";
        setEqt(Eqt);
        return Exact(ans);
    }

    public String CalculateProg(String opr) {
        String ans = "0";
        double Num = 0;
        if (!calTxt.getText().equals("")) {
            Num = Double.valueOf(calTxt.getText());
            switch (Base.valueOf(opr)) {
                case BIN:
                    ans = Decimal.DecimaltoNBase(Num, Base.BIN);
                    break;
                case HEX:
                    ans = Decimal.DecimaltoNBase(Num, Base.HEX);
                    break;
                case OCT:
                    ans = Decimal.DecimaltoNBase(Num, Base.OCT);
                    break;
            }
        }
        equation2.setText(equation.getText());
        equation.setText("" + opr + "(" + Exact(Num) + ") = " + ans + "");
        return ans;
    }

    public static String Exact(Double val) {
        String Value;
        Value = Double.toString(val);
        if (val % 1 == 0) {
            return BigDecimal.valueOf(val).toBigInteger().toString();
        }
        return Value;
    }

    public static String Exact(String value) {
        Double val;
        try {
            val = Double.parseDouble(value);
            return Exact(val);
        } catch (NumberFormatException ex) {
            return value;
        }
    }

    public void setValue(String d) {
        if (scifi_notation && getCalType().name.equals("Scientific")) {
            calTxt.setText(Scifi.getSciFiNotation(Double.valueOf(d)));
        } else {
            calTxt.setText(Exact(d));
        }
        calTxt.requestFocus();
    }

    public void setValue(Double d) {
        System.out.println(getCalType().toString());
        if (scifi_notation && getCalType().name.equals("Scientific")) {
            calTxt.setText(Scifi.getSciFiNotation(d));
        } else {
            calTxt.setText(Exact(d));
        }
    }

    public String getFirstNum(Double fn, Double sn, String arithExp) {
        return (Exact(fn) + " " + arithExp + " " + Exact(sn) + " = ");
    }

    public void setFirstNum(Double fn, Double sn, String arithExp) {
        eqtTxt.setText(getFirstNum(fn, sn, arithExp));
    }

    public String getFirstNum(Double d, String arithExp) {
        return (Exact(d) + " " + arithExp);
    }

    public void setFirstNum(Double d, String arithExp) {
        eqtTxt.setText(getFirstNum(d, arithExp));
    }

    public void DBload() {
        String sql = "select AlwaysOnTop,AutoImport from ComTable";
        try {
            rs = ExecuteSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(FrontFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ALWAYSONTOP = rs.getString("AlwaysOnTop").equals("true");
            AUTOIMPORT = rs.getString("AutoImport").equals("true");
        } catch (SQLException x) {
            JOptionPane.showMessageDialog(null, x);
        }
        sql = "select * from Equation";
        try {
            rs = ExecuteSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(FrontFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChecks();
//        HistoryTable.setModel(DbUtils.resultSetToTableModel(rs));
//        TableColumnModel ColumnModel = HistoryTable.getColumnModel();
//        ColumnModel.getColumn(0).setPreferredWidth(200);
//        ColumnModel.getColumn(1).setPreferredWidth(100);
    }

    public void setChecks() {
        autoSave.setSelected(AUTOIMPORT);
        AOTop.setSelected(ALWAYSONTOP);
        this.setAlwaysOnTop(ALWAYSONTOP);
    }

    public void importEquation(String Equation, String Answer) {
        if (AUTOIMPORT) {
            String sql = "insert into Equation (Equation,Answer) values('" + Equation + "','" + Answer + "')";
            try {
                Execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(FrontFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBload();
        }
    }

    public void setPanel() {

    }

    public Integer root(Integer num, Integer root) {
        Integer ans;
        ans = num * (1 / root);
        return ans;
    }

    public Double getDecimal(String bin, int ragex) {
        Double num = 0.;
        char Cbin[] = bin.toCharArray();
        double Index1 = 0;
        for (int Index = bin.length() - 1; Index >= 0; Index--) {
            num = num + (int) (Math.pow(ragex, Index1)) * Cbin[Index];
            Index1++;
        }
        return num;
    }

    public CalType getCalType() {
        int i = 0;
        for (Component c : navBtns.getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                if (btn.isSelected()) {
                    return new CalType(
                            btn.getText(),
                            i
                    );
                }
                i++;
            }
        }
        return null;
    }

    class CalType {

        String name;
        int idx;

        public CalType(String name, int idx) {
            this.name = name;
            this.idx = idx;
        }

        public CalType() {
            this(null, -1);
        }

        @Override
        public String toString() {
            return String.format("[NAME]%s\n[INDEX]%d", name, idx);
        }

    }

    public void digitPressed(String key) {
        if (value == 0) {
            setValue(key);
        } else {
            setValue(calTxt.getText() + key);
        }
        switch (getCalType().name) {
            case "Standard":
            case "Scientific":
                if (arithExp == null) {
                    firstNum = value;
                } else {
                    secondNum = value;
                }
                break;
            case "Programmer":
                break;
            case "Graphing":
                String t = key;
                calTxt.setText(t);
                break;
            case "Volume":
            case "Length":
            case "Weight & mass":
            case "Temperature":
            case "Energy":
            case "Area":
            case "Speed":
            case "Time":
            case "Power":
            case "Data":
            case "Presure":
            case "Angle":
                break;
        }
        calTxt.requestFocus();
    }

    public void arithmeticPressed(String key) {
        switch (getCalType().name) {
            case "Standard":
            case "Scientific":
                if (arithExp == null && firstNum != null) {
                    arithExp = key;
                    setFirstNum(value, arithExp);
                    calTxt.setText("0");
                } else if (arithExp != null && firstNum != null && secondNum != null) {
                    Double val = calculate();
                    setFirstNum(val, key);
                    setValue(0.);
                    arithExp = key;
                    firstNum = val;
                    secondNum = null;
                }
                break;
            case "Programmer":
                break;
            case "Volume":
            case "Length":
            case "Weight & mass":
            case "Temperature":
            case "Energy":
            case "Area":
            case "Speed":
            case "Time":
            case "Power":
            case "Data":
            case "Presure":
            case "Angle":
            default:
                break;
        }
        System.out.println("[f num]" + firstNum);
        System.out.println("[arith]" + arithExp);
        System.out.println("[s num]" + secondNum);
        calTxt.requestFocus();
    }

    public void funcPressed(String key, Double val) {
        Double ans = 0.;
        String func = key;
        boolean constn = false;
        boolean parenthesis = true;
        boolean pre = true;
        switch (key) {
            case "pow":
                ans = val * val;
                break;
            case "1/x":
                func = "rcpl";
                ans = 1 / val;
                break;
            case "sqrt":
                ans = findtheRoot(val, 2);
                break;
            case "10^":
                ans = Math.pow(10, val);
                break;
            case "log":
                ans = Math.log10(val);
                break;
            case "e":
                constn = true;
                ans = Math.E;
                break;
            case "pi":
                constn = true;
                ans = Math.PI;
                break;
            case "abs":
                ans = val < 0 ? -1 * val : val;
                break;
            case "^3":
                ans = Math.pow(val, 3);
                break;
            case "3√":
                ans = findtheRoot(val, 3);
                break;
            case "2^":
                ans = Math.pow(2, val);
                break;
            case "e^":
                ans = Math.pow(Math.PI, val);
                break;
            case "n!":
                func = "fact";
                ans = Gamma.gamma(val);
                break;
            case "floor":
                ans = Math.floor(val);
                break;
            case "ceil":
                ans = Math.ceil(val);
                break;
            case "round":
                constn = true;
                ans = BigDecimal.valueOf(val).round(MathContext.DECIMAL32).doubleValue();
                break;
            case "sin":
                ans = Math.sin(val);
                break;
            case "cos":
                ans = Math.cos(val);
                break;
            case "tan":
                ans = Math.tan(val);
                break;
            case "sinh":
                ans = Math.sinh(val);
                break;
            case "cosh":
                ans = Math.cosh(val);
                break;
            case "tanh":
                ans = Math.tanh(val);
                break;
            case "sin" + EXPONENTNEGATE:
            case "csc":
                ans = 1 / Math.sin(val);
                break;
            case "cos" + EXPONENTNEGATE:
            case "sec":
                ans = 1 / Math.cos(val);
                break;
            case "tan" + EXPONENTNEGATE:
            case "cot":
                ans = 1 / Math.tan(val);
                break;
            case "sinh" + EXPONENTNEGATE:
            case "csch":
                ans = 1 / Math.sinh(val);
                break;
            case "cosh" + EXPONENTNEGATE:
            case "sech":
                ans = 1 / Math.cosh(val);
                break;
            case "tanh" + EXPONENTNEGATE:
            case "coth":
                ans = 1 / Math.tanh(val);
                break;
            case "deg":
                if (val % 1 > 0) {
                    ans += val - (val % 1);
                    String s[] = {"", "", ""};
                    int i = 0;
                    for (char d : (val % 1 + "").replace("0.", "").toCharArray()) {
                        s[i] = s[i].concat(d + "");
                    }
                } else {
                    ans = val;
                }
                break;
            case "%":
                constn = true;
                if (firstNum != null && secondNum != null) {
                    ans = firstNum * secondNum / 100;
                }
                break;
        }
        if (!constn) {
            String eqt = arithExp == null ? ((pre ? func : "") + (parenthesis ? "(" + Exact(val) + ")" : Exact(val)) + (!pre ? func : "") + " = ")
                    : eqtTxt.getText() + " " + (pre ? func : "") + (parenthesis ? "(" + Exact(val) + ")" : Exact(val)) + (!pre ? func : "");
            setEqt(eqt);

        }
        setValue(ans);
        if (firstNum != null) {
            if (arithExp != null) {
                secondNum = ans;
            } else {
                firstNum = ans;
            }
        }

        calTxt.requestFocus();
    }

    public void watch() {
        if (firstNum != null) {
            if (arithExp != null) {
                secondNum = value;
            } else {
                firstNum = value;
            }
        }
    }

    public Double Fatorial(Double Val) {
        Double fact = Math.abs(Val);
        if (Val == 0.0) {
            System.out.println("zero or null");
            return 0.0;
        } else {
            while (true) {
                fact = fact * (Val - 1);
                Val--;
                System.out.println(fact);
                if (Val == 1) {
                    break;
                }
            }
        }
        return fact;
    }

    public void TriLogDec(boolean b) {
        trigonometry.setEnabled(b);
        DecimalTypes.setEnabled(b);
        logorithms.setEnabled(b);
    }

    public void ArithOpars(boolean b) {
        ArithOpars.setEnabled(b);
    }

    public Prog setPogrammer(Base base) {
        Prog p = new Prog(base);
        switch (base) {
            case BIN:
                break;
            case DEC:
                break;
            case HEX:
                break;
            case OCT:
                break;
        }
        return p;
    }

    public ArrayList<Object> asArrayList(Object[] l) {
        ArrayList<Object> al = new ArrayList<>();
        al.addAll(Arrays.asList(l));
        return al;
    }

    public ArrayList<Object> asArrayList(List<Object> l) {
        ArrayList<Object> al = new ArrayList<>();
        l.forEach((ele) -> {
            al.add(ele);
        });
        return al;
    }

    public void setHisMem(boolean b) {
        hisAndMem.setVisible(b);
        histryBtn.setVisible(b);
        memoryBar.setVisible(b);
    }

    public void setCalType(String key) {
        CalType.setText(key);
        Buttons r0 = new Buttons();
        Buttons r1 = new Buttons();
        Buttons r2 = new Buttons();
        Buttons r3 = new Buttons();
        Buttons opar = new Buttons();
        ArrayList<Container> panels = new ArrayList<>();
        ArrayList<Container> mPanels = new ArrayList<>();
        mPanels.add(Navigation);
        mPanels.add(calculator);
        mPanels.add(hisAndMem);
        JPanel calPanel = pad1;
        calculator.removeAll();
        setHisMem(false);
        switch (key) {
            case "Standard":
                setHisMem(true);
                panels.add(topBar);
                panels.add(showBar);
                panels.add(memoryBar);
                panels.add(keyBar);
                r0.visible = false;
                r1.btns.addAll(Arrays.asList(new JButton[]{
                    pers, reciprocal, b1, b4, b7, b10
                }));
                r2.btns.addAll(Arrays.asList(new JButton[]{
                    ce, power, b2, b5, b8, b0
                }));
                r3.btns.addAll(Arrays.asList(new JButton[]{
                    c, root, b3, b6, b9, dot
                }));
                opar.btns.addAll(Arrays.asList(new JButton[]{
                    clear, multiply, sub, plus, div, equal
                }));
                break;
            case "Scientific":
                setHisMem(true);
                panels.add(topBar);
                panels.add(showBar);
                panels.add(formatBar);
                panels.add(memoryBar);
                panels.add(trigFuncs);
                panels.add(trigonometry);
                panels.add(funcs);
                panels.add(keyBar);
                r0.visible = true;
                r0.btns.addAll(Arrays.asList(new JButton[]{
                    power, root, powerY, powerOf10, log10, in
                }));
                r1.btns.addAll(Arrays.asList(new JButton[]{
                    pi, reciprocal, bracL, b7, b4, b1, b10
                }));
                r2.btns.addAll(Arrays.asList(new JButton[]{
                    e, abs, bracR, b8, b5, b2, b0
                }));
                r3.btns.addAll(Arrays.asList(new JButton[]{
                    ce, exp, fact, b9, b6, b3, dot
                }));
                opar.btns.addAll(Arrays.asList(new JButton[]{
                    clear, mod, div, multiply, sub, plus, equal
                }));
                break;
            case "Programmer":
                setHisMem(true);
                panels.add(topBar);
                panels.add(showBar);
                panels.add(programmerShowBar);
                panels.add(programmerKeyboards);
                panels.add(programmerBitwise);
                panels.add(keyBar);
                r0.btns.addAll(Arrays.asList(new JButton[]{
                    ba, bb, bc, bd, be, bf
                }));
                r1.btns.addAll(Arrays.asList(new JButton[]{
                    shiftL, bracL, b7, b4, b1, b10
                }));
                r2.btns.addAll(Arrays.asList(new JButton[]{
                    shiftR, bracR, b8, b5, b2, b0
                }));
                r3.btns.addAll(Arrays.asList(new JButton[]{
                    ce, pers, b9, b6, b3, dot
                }));
                opar.btns.addAll(Arrays.asList(new JButton[]{
                    clear, div, multiply, sub, plus, equal
                }));
                break;
            case "Date calculation":
                setHisMem(false);
                r0.visible = false;
                r1.visible = false;
                r2.visible = false;
                r3.visible = false;
                opar.visible = false;
                panels.add(topBar);
                panels.add(dateCalculation);
                calPanel = null;
                break;
            case "Graphing":
                setHisMem(false);
                for (int i = 0; i < mPanels.size(); i++) {
                    mPanels.remove(i);
                }
                mPanels.add(Navigation);
                mPanels.add(graphingPanel);
                mPanels.add(calculator);

                panels.add(topBar);
                panels.add(functionPanel);
                panels.add(funcs);
                panels.add(keyBar);
                r0.btns.addAll(Arrays.asList(new JButton[]{
                    power, root, powerY, powerOf10, log10, in
                }));
                r1.btns.addAll(Arrays.asList(new JButton[]{
                    pi, reciprocal, bracL, b7, b4, b1, b10
                }));
                r2.btns.addAll(Arrays.asList(new JButton[]{
                    e, abs, bracR, b8, b5, b2, b0
                }));
                r3.btns.addAll(Arrays.asList(new JButton[]{
                    ce, exp, fact, b9, b6, b3, dot
                }));
                opar.btns.addAll(Arrays.asList(new JButton[]{
                    clear, mod, div, multiply, sub, plus, equal
                }));
                break;
            case "Volume":
            case "Length":
            case "Weight & mass":
            case "Temperature":
            case "Energy":
            case "Area":
            case "Speed":
            case "Time":
            case "Power":
            case "Data":
            case "Presure":
            case "Angle":
                setHisMem(false);
                r0.visible = false;
                r1.btns.addAll(Arrays.asList(new JButton[]{
                    empty(), b7, b4, b1, empty()
                }));
                r2.btns.addAll(Arrays.asList(new JButton[]{
                    ce, b8, b5, b2, b0
                }));
                r3.btns.addAll(Arrays.asList(new JButton[]{
                    clear, b9, b6, b3, dot
                }));
                opar.visible = false;
                calPanel = jPanel10;
                break;
        }
        getContentPane().removeAll();
        for (Container mPanel : mPanels) {
            getContentPane().add(mPanel);
        }
        validate();
        repaint();

        row0.setVisible(r0.visible);
        row1.setVisible(r1.visible);
        row2.setVisible(r2.visible);
        row3.setVisible(r3.visible);
        oparaters.setVisible(opar.visible);

        row0.removeAll();
        row1.removeAll();
        row2.removeAll();
        row3.removeAll();
        oparaters.removeAll();
        showBar.removeAll();

        row0.add(sci2nd);
        for (JButton btn : r0.btns) {
            row0.add(btn);
        }
        for (JButton btn : r1.btns) {
            row1.add(btn);
        }
        for (JButton btn : r2.btns) {
            row2.add(btn);
        }
        for (JButton btn : r3.btns) {
            row3.add(btn);
        }
        for (JButton btn : opar.btns) {
            oparaters.add(btn);
        }
//        calculator.remove(showBar);
        if (calPanel != null) {
            showBar.add(calPanel);
        }
        for (Container panel : panels) {
            calculator.add(panel);
        }
        setFrame();
    }

    public void clear() {
        calTxt.setText("0");
        eqtTxt.setText("");
        equation.setText("");
        equation2.setText("");
        arithExp = null;
        firstNum = null;
        secondNum = null;
        calTxt.requestFocus();
    }

    JButton empty() {
        JButton b = new JButton();
        b.setBackground(calculator.getBackground());
        b.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b.setFocusable(false);
        b.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b.setMinimumSize(new java.awt.Dimension(40, 30));
        b.setPreferredSize(new java.awt.Dimension(70, 47));
        return b;
    }

    public JTextField getOpasite() {
        if (calTxt == convertsText2) {
            return convertsText1;
        } else if (calTxt == convertsText1) {
            return convertsText2;
        }
        return calTxt;
    }

    public void setConvertedValue(double val, int type, JTextField conText) {
        if (type != Converts.SPEED && type != Converts.POWER) {
            JComboBox combo1;
            JComboBox combo2;
            if (SelConCombo == 1) {
                combo1 = unitSelection1;
                combo2 = unitSelection2;
            } else {
                combo1 = unitSelection2;
                combo2 = unitSelection1;
            }
            val = Converts.getConvertedValue(type, val, Converts.getEnum(type, combo1.getSelectedItem().toString()), Converts.getEnum(type, combo2.getSelectedItem().toString()));
            conText.setText(Exact(val));
        } else {
            JComboBox combo1;
            JComboBox combo2;
            JComboBox combo3;
            JComboBox combo4;
            if (SelConCombo == 1) {
                combo1 = unitSelection1;
                combo2 = ConCombo3;
                combo3 = unitSelection2;
                combo4 = ConCombo4;
            } else {
                combo1 = unitSelection2;
                combo2 = ConCombo4;
                combo3 = unitSelection1;
                combo4 = ConCombo3;
            }
            val = Converts.getConvertedValue(type, val, Converts.getEnum(type, combo1.getSelectedItem().toString()), Converts.getEnum(type, combo2.getSelectedItem().toString()), Converts.getEnum(type, combo3.getSelectedItem().toString()), Converts.getEnum(type, combo4.getSelectedItem().toString()));
            conText.setText(Exact(val));
        }
    }

    public boolean isAnOparator(char key) {
        return key == PLUS || key == DIV || key == SUB || key == MULT;
    }

    public boolean isNegate(double val) {
        return val >= 0;
    }

    public void setEqt(String Eqt) {
        eqtTxt.setText(Eqt);
    }

    public void navigation(boolean flag) {
        Navigation.setVisible(flag);
        doLayout();
        validate();
        repaint();
        jButton3.setVisible(!flag);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        otherPad = new javax.swing.JPanel();
        DecimalTypes = new javax.swing.JComboBox<>();
        ArithOpars = new javax.swing.JToolBar();
        jToolBar4 = new javax.swing.JToolBar();
        sqr = new javax.swing.JButton();
        logorithms = new javax.swing.JComboBox<>();
        del = new javax.swing.JButton();
        mainTab = new javax.swing.JTabbedPane();
        Standard = new javax.swing.JPanel();
        equation2 = new javax.swing.JTextField();
        equation = new javax.swing.JTextField();
        Message = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        autoSave = new javax.swing.JCheckBoxMenuItem();
        AOTop = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel8 = new javax.swing.JPanel();
        programmerKeyboards = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        programmerShowBar = new javax.swing.JPanel();
        hexBar = new javax.swing.JPanel();
        hexLabel = new javax.swing.JLabel();
        hexTxt = new javax.swing.JTextField();
        hexSelected = new javax.swing.JSeparator();
        decBar = new javax.swing.JPanel();
        decLabel = new javax.swing.JLabel();
        decTxt = new javax.swing.JTextField();
        decSelected = new javax.swing.JSeparator();
        octBar = new javax.swing.JPanel();
        octLabel = new javax.swing.JLabel();
        octTxt = new javax.swing.JTextField();
        octSelected = new javax.swing.JSeparator();
        binBar = new javax.swing.JPanel();
        binLabel = new javax.swing.JLabel();
        binTxt = new javax.swing.JTextField();
        binSelected = new javax.swing.JSeparator();
        progFuncs = new javax.swing.JPanel();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        programmerBitwise = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        and = new javax.swing.JButton();
        or = new javax.swing.JButton();
        not = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        nand = new javax.swing.JButton();
        nor = new javax.swing.JButton();
        xor = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        unitSelection2 = new javax.swing.JComboBox<>();
        per = new javax.swing.JLabel();
        ConCombo3 = new javax.swing.JComboBox<>();
        convertsText2 = new javax.swing.JTextField();
        unitSelection1 = new javax.swing.JComboBox<>();
        per1 = new javax.swing.JLabel();
        ConCombo4 = new javax.swing.JComboBox<>();
        convertsText1 = new javax.swing.JTextField();
        otherKeys = new javax.swing.JPanel();
        bEmpty = new javax.swing.JButton();
        sci2nd = new javax.swing.JToggleButton();
        powerY = new javax.swing.JButton();
        powerOf10 = new javax.swing.JButton();
        log10 = new javax.swing.JButton();
        in = new javax.swing.JButton();
        e = new javax.swing.JButton();
        abs = new javax.swing.JButton();
        power3 = new javax.swing.JButton();
        root3 = new javax.swing.JButton();
        rootY = new javax.swing.JButton();
        powerOf2 = new javax.swing.JButton();
        logXY = new javax.swing.JButton();
        powerOfe = new javax.swing.JButton();
        pi = new javax.swing.JButton();
        fact = new javax.swing.JButton();
        bracL = new javax.swing.JButton();
        bracR = new javax.swing.JButton();
        exp = new javax.swing.JButton();
        mod = new javax.swing.JButton();
        shiftL = new javax.swing.JButton();
        shiftR = new javax.swing.JButton();
        funcs = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        round = new javax.swing.JButton();
        rand = new javax.swing.JButton();
        rand2 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        floor = new javax.swing.JButton();
        ceil = new javax.swing.JButton();
        rand1 = new javax.swing.JButton();
        trigonometry = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        cos = new javax.swing.JButton();
        sin = new javax.swing.JButton();
        tan = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        cosec = new javax.swing.JButton();
        sec = new javax.swing.JButton();
        cot = new javax.swing.JButton();
        bitshift = new javax.swing.JPopupMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem4 = new javax.swing.JRadioButtonMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        binKeyPanel24 = new javax.swing.JPanel();
        bin97 = new javax.swing.JButton();
        bin98 = new javax.swing.JButton();
        bin99 = new javax.swing.JButton();
        bin100 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        binKeyPanel25 = new javax.swing.JPanel();
        bin101 = new javax.swing.JButton();
        bin102 = new javax.swing.JButton();
        bin103 = new javax.swing.JButton();
        bin104 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        binKeyPanel26 = new javax.swing.JPanel();
        bin105 = new javax.swing.JButton();
        bin106 = new javax.swing.JButton();
        bin107 = new javax.swing.JButton();
        bin108 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        binKeyPanel27 = new javax.swing.JPanel();
        bin109 = new javax.swing.JButton();
        bin110 = new javax.swing.JButton();
        bin111 = new javax.swing.JButton();
        bin112 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        binKeyPanel20 = new javax.swing.JPanel();
        bin81 = new javax.swing.JButton();
        bin82 = new javax.swing.JButton();
        bin83 = new javax.swing.JButton();
        bin84 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        binKeyPanel21 = new javax.swing.JPanel();
        bin85 = new javax.swing.JButton();
        bin86 = new javax.swing.JButton();
        bin87 = new javax.swing.JButton();
        bin88 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        binKeyPanel22 = new javax.swing.JPanel();
        bin89 = new javax.swing.JButton();
        bin90 = new javax.swing.JButton();
        bin91 = new javax.swing.JButton();
        bin92 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        binKeyPanel23 = new javax.swing.JPanel();
        bin93 = new javax.swing.JButton();
        bin94 = new javax.swing.JButton();
        bin95 = new javax.swing.JButton();
        bin96 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        binKeyPanel4 = new javax.swing.JPanel();
        bin17 = new javax.swing.JButton();
        bin18 = new javax.swing.JButton();
        bin19 = new javax.swing.JButton();
        bin20 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        binKeyPanel5 = new javax.swing.JPanel();
        bin21 = new javax.swing.JButton();
        bin22 = new javax.swing.JButton();
        bin23 = new javax.swing.JButton();
        bin24 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        binKeyPanel6 = new javax.swing.JPanel();
        bin25 = new javax.swing.JButton();
        bin26 = new javax.swing.JButton();
        bin27 = new javax.swing.JButton();
        bin28 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        binKeyPanel7 = new javax.swing.JPanel();
        bin29 = new javax.swing.JButton();
        bin30 = new javax.swing.JButton();
        bin31 = new javax.swing.JButton();
        bin32 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        binKeyPanel3 = new javax.swing.JPanel();
        bin13 = new javax.swing.JButton();
        bin14 = new javax.swing.JButton();
        bin15 = new javax.swing.JButton();
        bin16 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        binKeyPanel2 = new javax.swing.JPanel();
        bin9 = new javax.swing.JButton();
        bin10 = new javax.swing.JButton();
        bin11 = new javax.swing.JButton();
        bin12 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        binKeyPanel1 = new javax.swing.JPanel();
        bin5 = new javax.swing.JButton();
        bin6 = new javax.swing.JButton();
        bin7 = new javax.swing.JButton();
        bin8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        binKeyPanel0 = new javax.swing.JPanel();
        bin1 = new javax.swing.JButton();
        bin2 = new javax.swing.JButton();
        bin3 = new javax.swing.JButton();
        bin4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Navigation = new javax.swing.JPanel();
        Navigation.setVisible(false);
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        navBtns = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        calculator = new javax.swing.JPanel();
        topBar = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        CalType = new javax.swing.JLabel();
        histryBtn = new javax.swing.JButton();
        showBar = new javax.swing.JPanel();
        pad1 = new javax.swing.JPanel();
        eqtTxt = new javax.swing.JTextField();
        cal = new javax.swing.JTextField();
        formatBar = new javax.swing.JPanel();
        jToggleButton3 = new javax.swing.JToggleButton();
        jButton29 = new javax.swing.JButton();
        memoryBar = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        trigFuncs = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        keyBar = new javax.swing.JPanel();
        row0 = new javax.swing.JPanel();
        ba = new javax.swing.JButton();
        bb = new javax.swing.JButton();
        bc = new javax.swing.JButton();
        bd = new javax.swing.JButton();
        be = new javax.swing.JButton();
        bf = new javax.swing.JButton();
        row1 = new javax.swing.JPanel();
        pers = new javax.swing.JButton();
        reciprocal = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        row2 = new javax.swing.JPanel();
        ce = new javax.swing.JButton();
        power = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b0 = new javax.swing.JButton();
        row3 = new javax.swing.JPanel();
        c = new javax.swing.JButton();
        root = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        dot = new javax.swing.JButton();
        oparaters = new javax.swing.JPanel();
        clear = new javax.swing.JButton();
        multiply = new javax.swing.JButton();
        sub = new javax.swing.JButton();
        plus = new javax.swing.JButton();
        div = new javax.swing.JButton();
        equal = new javax.swing.JButton();
        hisAndMem = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jButton30 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();

        otherPad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DecimalTypes.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        DecimalTypes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bin", "Hex", "Oct" }));
        DecimalTypes.setBorder(null);
        DecimalTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DecimalTypesActionPerformed(evt);
            }
        });
        otherPad.add(DecimalTypes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        ArithOpars.setBorder(null);
        ArithOpars.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ArithOpars.setRollover(true);
        otherPad.add(ArithOpars, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 42, 160));

        jToolBar4.setBorder(null);
        jToolBar4.setRollover(true);

        sqr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sqr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Square-Black.png"))); // NOI18N
        sqr.setToolTipText("X to the power of x");
        sqr.setBorder(null);
        sqr.setMaximumSize(new java.awt.Dimension(40, 35));
        sqr.setMinimumSize(new java.awt.Dimension(35, 35));
        sqr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sqrActionPerformed(evt);
            }
        });
        jToolBar4.add(sqr);

        otherPad.add(jToolBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 40));

        logorithms.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        logorithms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Log", "AntLog" }));
        logorithms.setBorder(null);
        logorithms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logorithmsActionPerformed(evt);
            }
        });
        otherPad.add(logorithms, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        del.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        del.setText("DEL");
        del.setBorder(null);
        del.setMaximumSize(new java.awt.Dimension(1000, 1000));
        del.setMinimumSize(new java.awt.Dimension(15, 15));
        del.setPreferredSize(new java.awt.Dimension(50, 35));
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });
        otherPad.add(del, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 41, 60, 28));

        mainTab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        mainTab.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        mainTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainTabMouseClicked(evt);
            }
        });

        equation2.setEditable(false);
        equation2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        equation2.setForeground(new java.awt.Color(153, 153, 153));
        equation2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        equation2.setBorder(null);
        equation2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equation2ActionPerformed(evt);
            }
        });

        equation.setEditable(false);
        equation.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        equation.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        equation.setBorder(null);
        equation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equationMouseClicked(evt);
            }
        });
        equation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StandardLayout = new javax.swing.GroupLayout(Standard);
        Standard.setLayout(StandardLayout);
        StandardLayout.setHorizontalGroup(
            StandardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StandardLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(StandardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equation)
                    .addComponent(equation2))
                .addGap(10, 10, 10))
        );
        StandardLayout.setVerticalGroup(
            StandardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StandardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(equation, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(equation2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        mainTab.addTab("tab1", Standard);

        Message.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jMenu1.setText("OPTIONS");

        autoSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        autoSave.setSelected(true);
        autoSave.setText("Auto Save to History");
        autoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoSaveActionPerformed(evt);
            }
        });
        jMenu1.add(autoSave);

        AOTop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        AOTop.setSelected(true);
        AOTop.setText("Always on top");
        AOTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AOTopActionPerformed(evt);
            }
        });
        jMenu1.add(AOTop);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("TOOLS");

        jMenu3.setText("Apearence");

        jMenuItem1.setText("Dark");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Light");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        programmerKeyboards.setMaximumSize(new java.awt.Dimension(151516516, 50));

        jButton36.setBackground(getUIColor());
        jButton36.setText("MS");
        jButton36.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton36.setPreferredSize(new java.awt.Dimension(85, 40));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setBackground(getUIColor());
        jButton37.setText("QWORD");
        jButton37.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton37.setPreferredSize(new java.awt.Dimension(85, 40));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jToggleButton4.setText("123");

        jToggleButton5.setText("01");

        javax.swing.GroupLayout programmerKeyboardsLayout = new javax.swing.GroupLayout(programmerKeyboards);
        programmerKeyboards.setLayout(programmerKeyboardsLayout);
        programmerKeyboardsLayout.setHorizontalGroup(
            programmerKeyboardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(programmerKeyboardsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        programmerKeyboardsLayout.setVerticalGroup(
            programmerKeyboardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(programmerKeyboardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jToggleButton4)
                .addComponent(jToggleButton5))
        );

        jPanel8.add(programmerKeyboards);

        programmerShowBar.setMaximumSize(new java.awt.Dimension(32767, 120));
        programmerShowBar.setMinimumSize(new java.awt.Dimension(330, 0));
        programmerShowBar.setLayout(new javax.swing.BoxLayout(programmerShowBar, javax.swing.BoxLayout.PAGE_AXIS));

        hexBar.setMaximumSize(new java.awt.Dimension(32767, 30));
        hexBar.setMinimumSize(new java.awt.Dimension(60, 25));
        hexBar.setPreferredSize(new java.awt.Dimension(490, 25));

        hexLabel.setText("HEX");

        hexTxt.setEditable(false);
        hexTxt.setText("0");
        hexTxt.setBorder(null);

        hexSelected.setBackground(APP_THEME);
        hexSelected.setOrientation(javax.swing.SwingConstants.VERTICAL);
        hexSelected.setOpaque(true);

        javax.swing.GroupLayout hexBarLayout = new javax.swing.GroupLayout(hexBar);
        hexBar.setLayout(hexBarLayout);
        hexBarLayout.setHorizontalGroup(
            hexBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hexBarLayout.createSequentialGroup()
                .addComponent(hexSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hexLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hexTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        hexBarLayout.setVerticalGroup(
            hexBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hexBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(hexBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(hexSelected)
                    .addComponent(hexLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hexTxt))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        programmerShowBar.add(hexBar);

        decBar.setMaximumSize(new java.awt.Dimension(32767, 30));
        decBar.setMinimumSize(new java.awt.Dimension(60, 25));
        decBar.setPreferredSize(new java.awt.Dimension(490, 25));

        decLabel.setText("DEC");

        decTxt.setEditable(false);
        decTxt.setText("0");
        decTxt.setBorder(null);

        decSelected.setBackground(APP_THEME);
        decSelected.setOrientation(javax.swing.SwingConstants.VERTICAL);
        decSelected.setOpaque(true);

        javax.swing.GroupLayout decBarLayout = new javax.swing.GroupLayout(decBar);
        decBar.setLayout(decBarLayout);
        decBarLayout.setHorizontalGroup(
            decBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decBarLayout.createSequentialGroup()
                .addComponent(decSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        decBarLayout.setVerticalGroup(
            decBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(decBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(decSelected)
                    .addComponent(decLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(decTxt))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        programmerShowBar.add(decBar);

        octBar.setMaximumSize(new java.awt.Dimension(32767, 30));
        octBar.setMinimumSize(new java.awt.Dimension(60, 25));
        octBar.setPreferredSize(new java.awt.Dimension(490, 25));

        octLabel.setText("OCT");

        octTxt.setEditable(false);
        octTxt.setText("0");
        octTxt.setBorder(null);

        octSelected.setBackground(APP_THEME);
        octSelected.setOrientation(javax.swing.SwingConstants.VERTICAL);
        octSelected.setOpaque(true);

        javax.swing.GroupLayout octBarLayout = new javax.swing.GroupLayout(octBar);
        octBar.setLayout(octBarLayout);
        octBarLayout.setHorizontalGroup(
            octBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(octBarLayout.createSequentialGroup()
                .addComponent(octSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(octLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(octTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );
        octBarLayout.setVerticalGroup(
            octBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(octBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(octBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(octSelected)
                    .addComponent(octLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(octTxt))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        programmerShowBar.add(octBar);

        binBar.setMaximumSize(new java.awt.Dimension(32767, 30));
        binBar.setMinimumSize(new java.awt.Dimension(60, 25));
        binBar.setPreferredSize(new java.awt.Dimension(490, 25));

        binLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        binLabel.setText("BIN");

        binTxt.setEditable(false);
        binTxt.setText("0");
        binTxt.setBorder(null);

        binSelected.setBackground(APP_THEME);
        binSelected.setOrientation(javax.swing.SwingConstants.VERTICAL);
        binSelected.setOpaque(true);

        javax.swing.GroupLayout binBarLayout = new javax.swing.GroupLayout(binBar);
        binBar.setLayout(binBarLayout);
        binBarLayout.setHorizontalGroup(
            binBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binBarLayout.createSequentialGroup()
                .addComponent(binSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(binLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(binTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
        );
        binBarLayout.setVerticalGroup(
            binBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(binBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(binTxt)
                    .addComponent(binLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(binSelected))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        programmerShowBar.add(binBar);

        jPanel8.add(programmerShowBar);

        progFuncs.setMaximumSize(new java.awt.Dimension(151516516, 40));

        jButton32.setBackground(getUIColor());
        jButton32.setText("Bitwise");
        jButton32.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton32.setPreferredSize(new java.awt.Dimension(106, 40));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setBackground(getUIColor());
        jButton33.setText("Bit shift");
        jButton33.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton33.setPreferredSize(new java.awt.Dimension(85, 40));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout progFuncsLayout = new javax.swing.GroupLayout(progFuncs);
        progFuncs.setLayout(progFuncsLayout);
        progFuncsLayout.setHorizontalGroup(
            progFuncsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progFuncsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        progFuncsLayout.setVerticalGroup(
            progFuncsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel8.add(progFuncs);

        programmerBitwise.setAlignmentX(0.0F);
        programmerBitwise.setAlignmentY(0.0F);
        programmerBitwise.setMaximumSize(new java.awt.Dimension(180, 90));
        programmerBitwise.setMinimumSize(new java.awt.Dimension(180, 90));
        programmerBitwise.setPreferredSize(new java.awt.Dimension(180, 90));
        programmerBitwise.setLayout(new javax.swing.BoxLayout(programmerBitwise, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel20.setAlignmentX(1.0F);
        jPanel20.setMinimumSize(new java.awt.Dimension(210, 45));
        jPanel20.setPreferredSize(new java.awt.Dimension(210, 45));
        jPanel20.setLayout(new javax.swing.BoxLayout(jPanel20, javax.swing.BoxLayout.LINE_AXIS));

        and.setBackground(getUIColor());
        and.setText("AND");
        and.setAlignmentX(1.0F);
        and.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        and.setMaximumSize(new java.awt.Dimension(60, 45));
        and.setMinimumSize(new java.awt.Dimension(60, 45));
        and.setPreferredSize(new java.awt.Dimension(70, 45));
        and.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andActionPerformed(evt);
            }
        });
        jPanel20.add(and);

        or.setBackground(getUIColor());
        or.setText("OR");
        or.setAlignmentX(1.0F);
        or.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        or.setMaximumSize(new java.awt.Dimension(60, 45));
        or.setMinimumSize(new java.awt.Dimension(60, 45));
        or.setPreferredSize(new java.awt.Dimension(70, 45));
        or.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orActionPerformed(evt);
            }
        });
        jPanel20.add(or);

        not.setBackground(getUIColor());
        not.setText("NOT");
        not.setAlignmentX(1.0F);
        not.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        not.setMaximumSize(new java.awt.Dimension(60, 45));
        not.setMinimumSize(new java.awt.Dimension(60, 45));
        not.setPreferredSize(new java.awt.Dimension(60, 45));
        not.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notActionPerformed(evt);
            }
        });
        jPanel20.add(not);

        programmerBitwise.add(jPanel20);

        jPanel21.setAlignmentX(1.0F);
        jPanel21.setMinimumSize(new java.awt.Dimension(210, 45));
        jPanel21.setPreferredSize(new java.awt.Dimension(210, 45));
        jPanel21.setLayout(new javax.swing.BoxLayout(jPanel21, javax.swing.BoxLayout.LINE_AXIS));

        nand.setBackground(getUIColor());
        nand.setText("NAND");
        nand.setAlignmentX(1.0F);
        nand.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        nand.setMaximumSize(new java.awt.Dimension(60, 45));
        nand.setMinimumSize(new java.awt.Dimension(60, 45));
        nand.setPreferredSize(new java.awt.Dimension(70, 45));
        nand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nandActionPerformed(evt);
            }
        });
        jPanel21.add(nand);

        nor.setBackground(getUIColor());
        nor.setText("NOR");
        nor.setAlignmentX(1.0F);
        nor.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        nor.setMaximumSize(new java.awt.Dimension(60, 45));
        nor.setMinimumSize(new java.awt.Dimension(60, 45));
        nor.setPreferredSize(new java.awt.Dimension(70, 45));
        nor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                norActionPerformed(evt);
            }
        });
        jPanel21.add(nor);

        xor.setBackground(getUIColor());
        xor.setText("XOR");
        xor.setAlignmentX(1.0F);
        xor.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        xor.setMaximumSize(new java.awt.Dimension(60, 45));
        xor.setMinimumSize(new java.awt.Dimension(60, 45));
        xor.setPreferredSize(new java.awt.Dimension(70, 45));
        xor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xorActionPerformed(evt);
            }
        });
        jPanel21.add(xor);

        programmerBitwise.add(jPanel21);

        jPanel8.add(programmerBitwise);

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.PAGE_AXIS));

        unitSelection2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        unitSelection2.setBorder(null);
        unitSelection2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitSelection2ActionPerformed(evt);
            }
        });
        jPanel10.add(unitSelection2);

        per.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        per.setText("per");
        jPanel10.add(per);

        ConCombo3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        ConCombo3.setBorder(null);
        ConCombo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConCombo3ActionPerformed(evt);
            }
        });
        jPanel10.add(ConCombo3);

        convertsText2.setEditable(false);
        convertsText2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        convertsText2.setText("0");
        convertsText2.setBorder(null);
        convertsText2.setPreferredSize(new java.awt.Dimension(410, 32));
        convertsText2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                convertsText2MouseClicked(evt);
            }
        });
        convertsText2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                convertsText2KeyTyped(evt);
            }
        });
        jPanel10.add(convertsText2);

        unitSelection1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        unitSelection1.setBorder(null);
        unitSelection1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitSelection1ActionPerformed(evt);
            }
        });
        jPanel10.add(unitSelection1);

        per1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        per1.setText("per");
        jPanel10.add(per1);

        ConCombo4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        ConCombo4.setBorder(null);
        ConCombo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConCombo4ActionPerformed(evt);
            }
        });
        jPanel10.add(ConCombo4);

        convertsText1.setEditable(false);
        convertsText1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        convertsText1.setText("0");
        convertsText1.setBorder(null);
        convertsText1.setPreferredSize(new java.awt.Dimension(410, 32));
        convertsText1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                convertsText1MouseClicked(evt);
            }
        });
        convertsText1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                convertsText1KeyTyped(evt);
            }
        });
        jPanel10.add(convertsText1);

        bEmpty.setBackground(calculator.getBackground());
        bEmpty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bEmpty.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        bEmpty.setFocusable(false);
        bEmpty.setMaximumSize(new java.awt.Dimension(1000, 1000));
        bEmpty.setMinimumSize(new java.awt.Dimension(40, 30));
        bEmpty.setName("b7"); // NOI18N
        bEmpty.setPreferredSize(new java.awt.Dimension(70, 47));
        bEmpty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEmptyActionPerformed(evt);
            }
        });
        otherKeys.add(bEmpty);

        sci2nd.setBackground(getUIColor());
        sci2nd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sci2nd.setText("2ⁿᵈ");
        sci2nd.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        sci2nd.setMaximumSize(new java.awt.Dimension(1000, 1000));
        sci2nd.setMinimumSize(new java.awt.Dimension(40, 30));
        sci2nd.setPreferredSize(new java.awt.Dimension(70, 47));
        sci2nd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sci2ndActionPerformed(evt);
            }
        });
        otherKeys.add(sci2nd);

        powerY.setBackground(getUIColor());
        powerY.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        powerY.setText("xʸ");
        powerY.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        powerY.setMaximumSize(new java.awt.Dimension(1000, 1000));
        powerY.setMinimumSize(new java.awt.Dimension(40, 30));
        powerY.setName("b7"); // NOI18N
        powerY.setPreferredSize(new java.awt.Dimension(70, 47));
        powerY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerYActionPerformed(evt);
            }
        });
        otherKeys.add(powerY);

        powerOf10.setBackground(getUIColor());
        powerOf10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        powerOf10.setText("10ˣ");
        powerOf10.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        powerOf10.setMaximumSize(new java.awt.Dimension(1000, 1000));
        powerOf10.setMinimumSize(new java.awt.Dimension(40, 30));
        powerOf10.setName("b7"); // NOI18N
        powerOf10.setPreferredSize(new java.awt.Dimension(70, 47));
        powerOf10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerOf10ActionPerformed(evt);
            }
        });
        otherKeys.add(powerOf10);

        log10.setBackground(getUIColor());
        log10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        log10.setText("log");
        log10.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        log10.setMaximumSize(new java.awt.Dimension(1000, 1000));
        log10.setMinimumSize(new java.awt.Dimension(40, 30));
        log10.setName("b7"); // NOI18N
        log10.setPreferredSize(new java.awt.Dimension(70, 47));
        log10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log10ActionPerformed(evt);
            }
        });
        otherKeys.add(log10);

        in.setBackground(getUIColor());
        in.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        in.setText("In");
        in.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        in.setMaximumSize(new java.awt.Dimension(1000, 1000));
        in.setMinimumSize(new java.awt.Dimension(40, 30));
        in.setName("b7"); // NOI18N
        in.setPreferredSize(new java.awt.Dimension(70, 47));
        in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inActionPerformed(evt);
            }
        });
        otherKeys.add(in);

        e.setBackground(getUIColor());
        e.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        e.setText("e");
        e.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        e.setMaximumSize(new java.awt.Dimension(1000, 1000));
        e.setMinimumSize(new java.awt.Dimension(40, 30));
        e.setName("b7"); // NOI18N
        e.setPreferredSize(new java.awt.Dimension(70, 47));
        e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eActionPerformed(evt);
            }
        });
        otherKeys.add(e);

        abs.setBackground(getUIColor());
        abs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        abs.setText("|x|");
        abs.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        abs.setMaximumSize(new java.awt.Dimension(1000, 1000));
        abs.setMinimumSize(new java.awt.Dimension(40, 30));
        abs.setName("b7"); // NOI18N
        abs.setPreferredSize(new java.awt.Dimension(70, 47));
        abs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                absActionPerformed(evt);
            }
        });
        otherKeys.add(abs);

        power3.setBackground(getUIColor());
        power3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        power3.setText("x³");
        power3.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        power3.setMaximumSize(new java.awt.Dimension(1000, 1000));
        power3.setMinimumSize(new java.awt.Dimension(40, 30));
        power3.setName("b7"); // NOI18N
        power3.setPreferredSize(new java.awt.Dimension(70, 47));
        power3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                power3ActionPerformed(evt);
            }
        });
        otherKeys.add(power3);

        root3.setBackground(getUIColor());
        root3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        root3.setText("³√x");
        root3.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        root3.setMaximumSize(new java.awt.Dimension(1000, 1000));
        root3.setMinimumSize(new java.awt.Dimension(40, 30));
        root3.setName("b7"); // NOI18N
        root3.setPreferredSize(new java.awt.Dimension(70, 47));
        root3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                root3ActionPerformed(evt);
            }
        });
        otherKeys.add(root3);

        rootY.setBackground(getUIColor());
        rootY.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rootY.setText("ʸ√x");
        rootY.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        rootY.setMaximumSize(new java.awt.Dimension(1000, 1000));
        rootY.setMinimumSize(new java.awt.Dimension(40, 30));
        rootY.setName("b7"); // NOI18N
        rootY.setPreferredSize(new java.awt.Dimension(70, 47));
        rootY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootYActionPerformed(evt);
            }
        });
        otherKeys.add(rootY);

        powerOf2.setBackground(getUIColor());
        powerOf2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        powerOf2.setText("2ˣ");
        powerOf2.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        powerOf2.setMaximumSize(new java.awt.Dimension(1000, 1000));
        powerOf2.setMinimumSize(new java.awt.Dimension(40, 30));
        powerOf2.setName("b7"); // NOI18N
        powerOf2.setPreferredSize(new java.awt.Dimension(70, 47));
        powerOf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerOf2ActionPerformed(evt);
            }
        });
        otherKeys.add(powerOf2);

        logXY.setBackground(getUIColor());
        logXY.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logXY.setText("log b x");
        logXY.setToolTipText("");
        logXY.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        logXY.setMaximumSize(new java.awt.Dimension(1000, 1000));
        logXY.setMinimumSize(new java.awt.Dimension(40, 30));
        logXY.setName("b7"); // NOI18N
        logXY.setPreferredSize(new java.awt.Dimension(70, 47));
        logXY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logXYActionPerformed(evt);
            }
        });
        otherKeys.add(logXY);

        powerOfe.setBackground(getUIColor());
        powerOfe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        powerOfe.setText("e ˣ");
        powerOfe.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        powerOfe.setMaximumSize(new java.awt.Dimension(1000, 1000));
        powerOfe.setMinimumSize(new java.awt.Dimension(40, 30));
        powerOfe.setName("b7"); // NOI18N
        powerOfe.setPreferredSize(new java.awt.Dimension(70, 47));
        powerOfe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerOfeActionPerformed(evt);
            }
        });
        otherKeys.add(powerOfe);

        pi.setBackground(getUIColor());
        pi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pi.setText("π");
        pi.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        pi.setMaximumSize(new java.awt.Dimension(1000, 1000));
        pi.setMinimumSize(new java.awt.Dimension(40, 30));
        pi.setPreferredSize(new java.awt.Dimension(70, 47));
        pi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piActionPerformed(evt);
            }
        });
        otherKeys.add(pi);

        fact.setBackground(getUIColor());
        fact.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fact.setText("n!");
        fact.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        fact.setMaximumSize(new java.awt.Dimension(1000, 1000));
        fact.setMinimumSize(new java.awt.Dimension(40, 30));
        fact.setName("b7"); // NOI18N
        fact.setPreferredSize(new java.awt.Dimension(70, 47));
        fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factActionPerformed(evt);
            }
        });
        otherKeys.add(fact);

        bracL.setBackground(getUIColor());
        bracL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bracL.setText("(");
        bracL.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        bracL.setMaximumSize(new java.awt.Dimension(1000, 1000));
        bracL.setMinimumSize(new java.awt.Dimension(40, 30));
        bracL.setName("b7"); // NOI18N
        bracL.setPreferredSize(new java.awt.Dimension(70, 47));
        bracL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bracLActionPerformed(evt);
            }
        });
        otherKeys.add(bracL);

        bracR.setBackground(getUIColor());
        bracR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bracR.setText(")");
        bracR.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        bracR.setMaximumSize(new java.awt.Dimension(1000, 1000));
        bracR.setMinimumSize(new java.awt.Dimension(40, 30));
        bracR.setName("b7"); // NOI18N
        bracR.setPreferredSize(new java.awt.Dimension(70, 47));
        bracR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bracRActionPerformed(evt);
            }
        });
        otherKeys.add(bracR);

        exp.setBackground(getUIColor());
        exp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exp.setText("exp");
        exp.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        exp.setMaximumSize(new java.awt.Dimension(1000, 1000));
        exp.setMinimumSize(new java.awt.Dimension(40, 30));
        exp.setName("b7"); // NOI18N
        exp.setPreferredSize(new java.awt.Dimension(70, 47));
        exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expActionPerformed(evt);
            }
        });
        otherKeys.add(exp);

        mod.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mod.setText("mod");
        mod.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        mod.setMaximumSize(new java.awt.Dimension(1000, 1000));
        mod.setMinimumSize(new java.awt.Dimension(40, 30));
        mod.setName("b7"); // NOI18N
        mod.setPreferredSize(new java.awt.Dimension(70, 47));
        mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modActionPerformed(evt);
            }
        });
        otherKeys.add(mod);

        shiftL.setBackground(getUIColor());
        shiftL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        shiftL.setText("<<");
        shiftL.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        shiftL.setMaximumSize(new java.awt.Dimension(1000, 1000));
        shiftL.setMinimumSize(new java.awt.Dimension(40, 30));
        shiftL.setName("b7"); // NOI18N
        shiftL.setPreferredSize(new java.awt.Dimension(70, 47));
        shiftL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftLActionPerformed(evt);
            }
        });
        otherKeys.add(shiftL);

        shiftR.setBackground(getUIColor());
        shiftR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        shiftR.setText(">>");
        shiftR.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        shiftR.setMaximumSize(new java.awt.Dimension(1000, 1000));
        shiftR.setMinimumSize(new java.awt.Dimension(40, 30));
        shiftR.setName("b7"); // NOI18N
        shiftR.setPreferredSize(new java.awt.Dimension(70, 47));
        shiftR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftRActionPerformed(evt);
            }
        });
        otherKeys.add(shiftR);

        funcs.setAlignmentX(0.0F);
        funcs.setAlignmentY(0.0F);
        funcs.setMaximumSize(new java.awt.Dimension(450, 90));
        funcs.setMinimumSize(new java.awt.Dimension(450, 90));
        funcs.setPreferredSize(new java.awt.Dimension(450, 90));
        funcs.setLayout(new javax.swing.BoxLayout(funcs, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel16.setAlignmentX(0.0F);
        jPanel16.setMaximumSize(new java.awt.Dimension(210, 45));
        jPanel16.setMinimumSize(new java.awt.Dimension(210, 45));
        jPanel16.setPreferredSize(new java.awt.Dimension(210, 45));
        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.LINE_AXIS));

        round.setBackground(getUIColor());
        round.setText("round");
        round.setAlignmentX(0.5F);
        round.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        round.setMaximumSize(new java.awt.Dimension(70, 45));
        round.setMinimumSize(new java.awt.Dimension(40, 30));
        round.setName("b7"); // NOI18N
        round.setPreferredSize(new java.awt.Dimension(70, 47));
        round.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundActionPerformed(evt);
            }
        });
        jPanel16.add(round);

        rand.setBackground(getUIColor());
        rand.setText("rand");
        rand.setAlignmentX(0.5F);
        rand.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        rand.setMaximumSize(new java.awt.Dimension(70, 45));
        rand.setMinimumSize(new java.awt.Dimension(40, 30));
        rand.setName("b7"); // NOI18N
        rand.setPreferredSize(new java.awt.Dimension(70, 47));
        rand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randActionPerformed(evt);
            }
        });
        jPanel16.add(rand);

        rand2.setBackground(getUIColor());
        rand2.setText("→dms");
        rand2.setAlignmentX(0.5F);
        rand2.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        rand2.setMaximumSize(new java.awt.Dimension(70, 45));
        rand2.setMinimumSize(new java.awt.Dimension(40, 30));
        rand2.setName("b7"); // NOI18N
        rand2.setPreferredSize(new java.awt.Dimension(70, 47));
        rand2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rand2ActionPerformed(evt);
            }
        });
        jPanel16.add(rand2);

        funcs.add(jPanel16);

        jPanel17.setAlignmentX(0.0F);
        jPanel17.setMinimumSize(new java.awt.Dimension(210, 45));
        jPanel17.setPreferredSize(new java.awt.Dimension(210, 45));
        jPanel17.setLayout(new javax.swing.BoxLayout(jPanel17, javax.swing.BoxLayout.LINE_AXIS));

        floor.setBackground(getUIColor());
        floor.setText("|_|");
        floor.setAlignmentX(0.5F);
        floor.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        floor.setMaximumSize(new java.awt.Dimension(70, 45));
        floor.setMinimumSize(new java.awt.Dimension(40, 30));
        floor.setName("b7"); // NOI18N
        floor.setPreferredSize(new java.awt.Dimension(70, 47));
        floor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floorActionPerformed(evt);
            }
        });
        jPanel17.add(floor);

        ceil.setBackground(getUIColor());
        ceil.setText("|‾|");
        ceil.setAlignmentX(0.5F);
        ceil.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        ceil.setMaximumSize(new java.awt.Dimension(70, 45));
        ceil.setMinimumSize(new java.awt.Dimension(40, 30));
        ceil.setName("b7"); // NOI18N
        ceil.setPreferredSize(new java.awt.Dimension(70, 47));
        ceil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ceilActionPerformed(evt);
            }
        });
        jPanel17.add(ceil);

        rand1.setBackground(getUIColor());
        rand1.setText("→deg");
        rand1.setAlignmentX(0.5F);
        rand1.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        rand1.setMaximumSize(new java.awt.Dimension(70, 45));
        rand1.setMinimumSize(new java.awt.Dimension(40, 30));
        rand1.setName("b7"); // NOI18N
        rand1.setPreferredSize(new java.awt.Dimension(70, 47));
        rand1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rand1ActionPerformed(evt);
            }
        });
        jPanel17.add(rand1);

        funcs.add(jPanel17);

        trigonometry.setAlignmentY(0.0F);
        trigonometry.setMaximumSize(new java.awt.Dimension(450, 90));
        trigonometry.setMinimumSize(new java.awt.Dimension(450, 90));
        trigonometry.setPreferredSize(new java.awt.Dimension(450, 90));
        trigonometry.setLayout(new javax.swing.BoxLayout(trigonometry, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel18.setAlignmentX(0.0F);
        jPanel18.setMinimumSize(new java.awt.Dimension(210, 45));
        jPanel18.setPreferredSize(new java.awt.Dimension(210, 45));
        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.LINE_AXIS));

        jToggleButton1.setBackground(getUIColor());
        jToggleButton1.setText("2nd");
        jToggleButton1.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jToggleButton1.setMaximumSize(new java.awt.Dimension(60, 45));
        jToggleButton1.setMinimumSize(new java.awt.Dimension(60, 45));
        jToggleButton1.setPreferredSize(new java.awt.Dimension(70, 45));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel18.add(jToggleButton1);

        cos.setBackground(getUIColor());
        cos.setText("cos");
        cos.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        cos.setMaximumSize(new java.awt.Dimension(60, 45));
        cos.setMinimumSize(new java.awt.Dimension(60, 45));
        cos.setPreferredSize(new java.awt.Dimension(70, 45));
        cos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cosActionPerformed(evt);
            }
        });
        jPanel18.add(cos);

        sin.setBackground(getUIColor());
        sin.setText("sin");
        sin.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        sin.setMaximumSize(new java.awt.Dimension(60, 45));
        sin.setMinimumSize(new java.awt.Dimension(60, 45));
        sin.setPreferredSize(new java.awt.Dimension(70, 45));
        sin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinActionPerformed(evt);
            }
        });
        jPanel18.add(sin);

        tan.setBackground(getUIColor());
        tan.setText("tan");
        tan.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        tan.setMaximumSize(new java.awt.Dimension(60, 45));
        tan.setMinimumSize(new java.awt.Dimension(60, 45));
        tan.setPreferredSize(new java.awt.Dimension(70, 45));
        tan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanActionPerformed(evt);
            }
        });
        jPanel18.add(tan);

        trigonometry.add(jPanel18);

        jPanel19.setAlignmentX(0.0F);
        jPanel19.setMinimumSize(new java.awt.Dimension(210, 45));
        jPanel19.setPreferredSize(new java.awt.Dimension(210, 45));
        jPanel19.setLayout(new javax.swing.BoxLayout(jPanel19, javax.swing.BoxLayout.LINE_AXIS));

        jToggleButton2.setBackground(getUIColor());
        jToggleButton2.setText("hyp");
        jToggleButton2.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jToggleButton2.setMaximumSize(new java.awt.Dimension(60, 45));
        jToggleButton2.setMinimumSize(new java.awt.Dimension(60, 45));
        jToggleButton2.setPreferredSize(new java.awt.Dimension(70, 45));
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel19.add(jToggleButton2);

        cosec.setBackground(getUIColor());
        cosec.setText("csc");
        cosec.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        cosec.setMaximumSize(new java.awt.Dimension(60, 45));
        cosec.setMinimumSize(new java.awt.Dimension(60, 45));
        cosec.setPreferredSize(new java.awt.Dimension(70, 45));
        cosec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cosecActionPerformed(evt);
            }
        });
        jPanel19.add(cosec);

        sec.setBackground(getUIColor());
        sec.setText("sec");
        sec.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        sec.setMaximumSize(new java.awt.Dimension(60, 45));
        sec.setMinimumSize(new java.awt.Dimension(60, 45));
        sec.setPreferredSize(new java.awt.Dimension(70, 45));
        sec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secActionPerformed(evt);
            }
        });
        jPanel19.add(sec);

        cot.setBackground(getUIColor());
        cot.setText("cot");
        cot.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        cot.setMaximumSize(new java.awt.Dimension(60, 45));
        cot.setMinimumSize(new java.awt.Dimension(60, 45));
        cot.setPreferredSize(new java.awt.Dimension(70, 45));
        cot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cotActionPerformed(evt);
            }
        });
        jPanel19.add(cot);

        trigonometry.add(jPanel19);

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Arithmetic Shift");
        bitshift.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Logical Shift");
        bitshift.add(jRadioButtonMenuItem2);

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("Rotate Circular Shift");
        bitshift.add(jRadioButtonMenuItem3);

        jRadioButtonMenuItem4.setSelected(true);
        jRadioButtonMenuItem4.setText("Rotate through Carry Circular Shift");
        bitshift.add(jRadioButtonMenuItem4);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel25.setLayout(new javax.swing.BoxLayout(jPanel25, javax.swing.BoxLayout.LINE_AXIS));

        binKeyPanel24.setMinimumSize(new java.awt.Dimension(80, 55));

        bin97.setBackground(jPanel12.getBackground());
        bin97.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin97.setText("0");
        bin97.setBorder(null);
        bin97.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin98.setBackground(jPanel12.getBackground());
        bin98.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin98.setText("0");
        bin98.setBorder(null);
        bin98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin99.setBackground(jPanel12.getBackground());
        bin99.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin99.setText("0");
        bin99.setBorder(null);
        bin99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin100.setBackground(jPanel12.getBackground());
        bin100.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin100.setText("0");
        bin100.setBorder(null);
        bin100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("60");

        javax.swing.GroupLayout binKeyPanel24Layout = new javax.swing.GroupLayout(binKeyPanel24);
        binKeyPanel24.setLayout(binKeyPanel24Layout);
        binKeyPanel24Layout.setHorizontalGroup(
            binKeyPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel24Layout.createSequentialGroup()
                        .addComponent(bin100, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin99, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin98, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin97, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel24Layout.setVerticalGroup(
            binKeyPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin100)
                    .addComponent(bin99)
                    .addComponent(bin98)
                    .addComponent(bin97))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addContainerGap())
        );

        jPanel25.add(binKeyPanel24);

        binKeyPanel25.setMinimumSize(new java.awt.Dimension(80, 55));

        bin101.setBackground(jPanel12.getBackground());
        bin101.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin101.setText("0");
        bin101.setBorder(null);
        bin101.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin102.setBackground(jPanel12.getBackground());
        bin102.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin102.setText("0");
        bin102.setBorder(null);
        bin102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin103.setBackground(jPanel12.getBackground());
        bin103.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin103.setText("0");
        bin103.setBorder(null);
        bin103.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin104.setBackground(jPanel12.getBackground());
        bin104.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin104.setText("0");
        bin104.setBorder(null);
        bin104.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("56");

        javax.swing.GroupLayout binKeyPanel25Layout = new javax.swing.GroupLayout(binKeyPanel25);
        binKeyPanel25.setLayout(binKeyPanel25Layout);
        binKeyPanel25Layout.setHorizontalGroup(
            binKeyPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel25Layout.createSequentialGroup()
                        .addComponent(bin104, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin103, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin102, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin101, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel25Layout.setVerticalGroup(
            binKeyPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin104)
                    .addComponent(bin103)
                    .addComponent(bin102)
                    .addComponent(bin101))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addContainerGap())
        );

        jPanel25.add(binKeyPanel25);

        binKeyPanel26.setMinimumSize(new java.awt.Dimension(80, 55));

        bin105.setBackground(jPanel12.getBackground());
        bin105.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin105.setText("0");
        bin105.setBorder(null);
        bin105.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin106.setBackground(jPanel12.getBackground());
        bin106.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin106.setText("0");
        bin106.setBorder(null);
        bin106.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin107.setBackground(jPanel12.getBackground());
        bin107.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin107.setText("0");
        bin107.setBorder(null);
        bin107.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin108.setBackground(jPanel12.getBackground());
        bin108.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin108.setText("0");
        bin108.setBorder(null);
        bin108.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("52");

        javax.swing.GroupLayout binKeyPanel26Layout = new javax.swing.GroupLayout(binKeyPanel26);
        binKeyPanel26.setLayout(binKeyPanel26Layout);
        binKeyPanel26Layout.setHorizontalGroup(
            binKeyPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel26Layout.createSequentialGroup()
                        .addComponent(bin108, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin107, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin106, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin105, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel26Layout.setVerticalGroup(
            binKeyPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin108)
                    .addComponent(bin107)
                    .addComponent(bin106)
                    .addComponent(bin105))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addContainerGap())
        );

        jPanel25.add(binKeyPanel26);

        binKeyPanel27.setMinimumSize(new java.awt.Dimension(80, 55));

        bin109.setBackground(jPanel12.getBackground());
        bin109.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin109.setText("0");
        bin109.setBorder(null);
        bin109.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin110.setBackground(jPanel12.getBackground());
        bin110.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin110.setText("0");
        bin110.setBorder(null);
        bin110.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin111.setBackground(jPanel12.getBackground());
        bin111.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin111.setText("0");
        bin111.setBorder(null);
        bin111.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin112.setBackground(jPanel12.getBackground());
        bin112.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin112.setText("0");
        bin112.setBorder(null);
        bin112.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("48");

        javax.swing.GroupLayout binKeyPanel27Layout = new javax.swing.GroupLayout(binKeyPanel27);
        binKeyPanel27.setLayout(binKeyPanel27Layout);
        binKeyPanel27Layout.setHorizontalGroup(
            binKeyPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel27Layout.createSequentialGroup()
                        .addComponent(bin112, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin111, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin110, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin109, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel27Layout.setVerticalGroup(
            binKeyPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin112)
                    .addComponent(bin111)
                    .addComponent(bin110)
                    .addComponent(bin109))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addContainerGap())
        );

        jPanel25.add(binKeyPanel27);

        jPanel1.add(jPanel25);

        jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.LINE_AXIS));

        binKeyPanel20.setMinimumSize(new java.awt.Dimension(80, 55));

        bin81.setBackground(jPanel12.getBackground());
        bin81.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin81.setText("0");
        bin81.setBorder(null);
        bin81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin82.setBackground(jPanel12.getBackground());
        bin82.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin82.setText("0");
        bin82.setBorder(null);
        bin82.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin83.setBackground(jPanel12.getBackground());
        bin83.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin83.setText("0");
        bin83.setBorder(null);
        bin83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin84.setBackground(jPanel12.getBackground());
        bin84.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin84.setText("0");
        bin84.setBorder(null);
        bin84.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("44");

        javax.swing.GroupLayout binKeyPanel20Layout = new javax.swing.GroupLayout(binKeyPanel20);
        binKeyPanel20.setLayout(binKeyPanel20Layout);
        binKeyPanel20Layout.setHorizontalGroup(
            binKeyPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel20Layout.createSequentialGroup()
                        .addComponent(bin84, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin83, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin82, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin81, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel20Layout.setVerticalGroup(
            binKeyPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin84)
                    .addComponent(bin83)
                    .addComponent(bin82)
                    .addComponent(bin81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );

        jPanel24.add(binKeyPanel20);

        binKeyPanel21.setMinimumSize(new java.awt.Dimension(80, 55));

        bin85.setBackground(jPanel12.getBackground());
        bin85.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin85.setText("0");
        bin85.setBorder(null);
        bin85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin86.setBackground(jPanel12.getBackground());
        bin86.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin86.setText("0");
        bin86.setBorder(null);
        bin86.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin87.setBackground(jPanel12.getBackground());
        bin87.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin87.setText("0");
        bin87.setBorder(null);
        bin87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin88.setBackground(jPanel12.getBackground());
        bin88.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin88.setText("0");
        bin88.setBorder(null);
        bin88.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("40");

        javax.swing.GroupLayout binKeyPanel21Layout = new javax.swing.GroupLayout(binKeyPanel21);
        binKeyPanel21.setLayout(binKeyPanel21Layout);
        binKeyPanel21Layout.setHorizontalGroup(
            binKeyPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel21Layout.createSequentialGroup()
                        .addComponent(bin88, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin87, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin86, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin85, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel21Layout.setVerticalGroup(
            binKeyPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin88)
                    .addComponent(bin87)
                    .addComponent(bin86)
                    .addComponent(bin85))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );

        jPanel24.add(binKeyPanel21);

        binKeyPanel22.setMinimumSize(new java.awt.Dimension(80, 55));

        bin89.setBackground(jPanel12.getBackground());
        bin89.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin89.setText("0");
        bin89.setBorder(null);
        bin89.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin90.setBackground(jPanel12.getBackground());
        bin90.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin90.setText("0");
        bin90.setBorder(null);
        bin90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin91.setBackground(jPanel12.getBackground());
        bin91.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin91.setText("0");
        bin91.setBorder(null);
        bin91.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin92.setBackground(jPanel12.getBackground());
        bin92.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin92.setText("0");
        bin92.setBorder(null);
        bin92.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("36");

        javax.swing.GroupLayout binKeyPanel22Layout = new javax.swing.GroupLayout(binKeyPanel22);
        binKeyPanel22.setLayout(binKeyPanel22Layout);
        binKeyPanel22Layout.setHorizontalGroup(
            binKeyPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel22Layout.createSequentialGroup()
                        .addComponent(bin92, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin91, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin90, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin89, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel22Layout.setVerticalGroup(
            binKeyPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin92)
                    .addComponent(bin91)
                    .addComponent(bin90)
                    .addComponent(bin89))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addContainerGap())
        );

        jPanel24.add(binKeyPanel22);

        binKeyPanel23.setMinimumSize(new java.awt.Dimension(80, 55));

        bin93.setBackground(jPanel12.getBackground());
        bin93.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin93.setText("0");
        bin93.setBorder(null);
        bin93.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin94.setBackground(jPanel12.getBackground());
        bin94.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin94.setText("0");
        bin94.setBorder(null);
        bin94.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin95.setBackground(jPanel12.getBackground());
        bin95.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin95.setText("0");
        bin95.setBorder(null);
        bin95.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin96.setBackground(jPanel12.getBackground());
        bin96.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin96.setText("0");
        bin96.setBorder(null);
        bin96.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("32");

        javax.swing.GroupLayout binKeyPanel23Layout = new javax.swing.GroupLayout(binKeyPanel23);
        binKeyPanel23.setLayout(binKeyPanel23Layout);
        binKeyPanel23Layout.setHorizontalGroup(
            binKeyPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel23Layout.createSequentialGroup()
                        .addComponent(bin96, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin95, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin94, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin93, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel23Layout.setVerticalGroup(
            binKeyPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin96)
                    .addComponent(bin95)
                    .addComponent(bin94)
                    .addComponent(bin93))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addContainerGap())
        );

        jPanel24.add(binKeyPanel23);

        jPanel1.add(jPanel24);

        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        binKeyPanel4.setMinimumSize(new java.awt.Dimension(80, 55));

        bin17.setBackground(jPanel12.getBackground());
        bin17.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin17.setText("0");
        bin17.setBorder(null);
        bin17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin18.setBackground(jPanel12.getBackground());
        bin18.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin18.setText("0");
        bin18.setBorder(null);
        bin18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin19.setBackground(jPanel12.getBackground());
        bin19.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin19.setText("0");
        bin19.setBorder(null);
        bin19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin20.setBackground(jPanel12.getBackground());
        bin20.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin20.setText("0");
        bin20.setBorder(null);
        bin20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("28");

        javax.swing.GroupLayout binKeyPanel4Layout = new javax.swing.GroupLayout(binKeyPanel4);
        binKeyPanel4.setLayout(binKeyPanel4Layout);
        binKeyPanel4Layout.setHorizontalGroup(
            binKeyPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel4Layout.createSequentialGroup()
                        .addComponent(bin20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin19, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin18, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin17, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel4Layout.setVerticalGroup(
            binKeyPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin20)
                    .addComponent(bin19)
                    .addComponent(bin18)
                    .addComponent(bin17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel14.add(binKeyPanel4);

        binKeyPanel5.setMinimumSize(new java.awt.Dimension(80, 55));

        bin21.setBackground(jPanel12.getBackground());
        bin21.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin21.setText("0");
        bin21.setBorder(null);
        bin21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin22.setBackground(jPanel12.getBackground());
        bin22.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin22.setText("0");
        bin22.setBorder(null);
        bin22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin23.setBackground(jPanel12.getBackground());
        bin23.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin23.setText("0");
        bin23.setBorder(null);
        bin23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin24.setBackground(jPanel12.getBackground());
        bin24.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin24.setText("0");
        bin24.setBorder(null);
        bin24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("24");

        javax.swing.GroupLayout binKeyPanel5Layout = new javax.swing.GroupLayout(binKeyPanel5);
        binKeyPanel5.setLayout(binKeyPanel5Layout);
        binKeyPanel5Layout.setHorizontalGroup(
            binKeyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel5Layout.createSequentialGroup()
                        .addComponent(bin24, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin23, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin22, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin21, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel5Layout.setVerticalGroup(
            binKeyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin24)
                    .addComponent(bin23)
                    .addComponent(bin22)
                    .addComponent(bin21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jPanel14.add(binKeyPanel5);

        binKeyPanel6.setMinimumSize(new java.awt.Dimension(80, 55));

        bin25.setBackground(jPanel12.getBackground());
        bin25.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin25.setText("0");
        bin25.setBorder(null);
        bin25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin26.setBackground(jPanel12.getBackground());
        bin26.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin26.setText("0");
        bin26.setBorder(null);
        bin26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin27.setBackground(jPanel12.getBackground());
        bin27.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin27.setText("0");
        bin27.setBorder(null);
        bin27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin28.setBackground(jPanel12.getBackground());
        bin28.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin28.setText("0");
        bin28.setBorder(null);
        bin28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("20");

        javax.swing.GroupLayout binKeyPanel6Layout = new javax.swing.GroupLayout(binKeyPanel6);
        binKeyPanel6.setLayout(binKeyPanel6Layout);
        binKeyPanel6Layout.setHorizontalGroup(
            binKeyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel6Layout.createSequentialGroup()
                        .addComponent(bin28, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin27, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin26, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin25, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel6Layout.setVerticalGroup(
            binKeyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin28)
                    .addComponent(bin27)
                    .addComponent(bin26)
                    .addComponent(bin25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jPanel14.add(binKeyPanel6);

        binKeyPanel7.setMinimumSize(new java.awt.Dimension(80, 55));

        bin29.setBackground(jPanel12.getBackground());
        bin29.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin29.setText("0");
        bin29.setBorder(null);
        bin29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin30.setBackground(jPanel12.getBackground());
        bin30.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin30.setText("0");
        bin30.setBorder(null);
        bin30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin31.setBackground(jPanel12.getBackground());
        bin31.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin31.setText("0");
        bin31.setBorder(null);
        bin31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin32.setBackground(jPanel12.getBackground());
        bin32.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin32.setText("0");
        bin32.setBorder(null);
        bin32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("16");

        javax.swing.GroupLayout binKeyPanel7Layout = new javax.swing.GroupLayout(binKeyPanel7);
        binKeyPanel7.setLayout(binKeyPanel7Layout);
        binKeyPanel7Layout.setHorizontalGroup(
            binKeyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel7Layout.createSequentialGroup()
                        .addComponent(bin32, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin31, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin30, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin29, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel7Layout.setVerticalGroup(
            binKeyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin32)
                    .addComponent(bin31)
                    .addComponent(bin30)
                    .addComponent(bin29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        jPanel14.add(binKeyPanel7);

        jPanel1.add(jPanel14);

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        binKeyPanel3.setMinimumSize(new java.awt.Dimension(80, 55));

        bin13.setBackground(jPanel12.getBackground());
        bin13.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin13.setText("0");
        bin13.setBorder(null);
        bin13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin14.setBackground(jPanel12.getBackground());
        bin14.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin14.setText("0");
        bin14.setBorder(null);
        bin14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin15.setBackground(jPanel12.getBackground());
        bin15.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin15.setText("0");
        bin15.setBorder(null);
        bin15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin16.setBackground(jPanel12.getBackground());
        bin16.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin16.setText("0");
        bin16.setBorder(null);
        bin16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("12");

        javax.swing.GroupLayout binKeyPanel3Layout = new javax.swing.GroupLayout(binKeyPanel3);
        binKeyPanel3.setLayout(binKeyPanel3Layout);
        binKeyPanel3Layout.setHorizontalGroup(
            binKeyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel3Layout.createSequentialGroup()
                        .addComponent(bin16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel3Layout.setVerticalGroup(
            binKeyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin16)
                    .addComponent(bin15)
                    .addComponent(bin14)
                    .addComponent(bin13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jPanel12.add(binKeyPanel3);

        binKeyPanel2.setMinimumSize(new java.awt.Dimension(80, 55));

        bin9.setBackground(jPanel12.getBackground());
        bin9.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin9.setText("0");
        bin9.setBorder(null);
        bin9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin10.setBackground(jPanel12.getBackground());
        bin10.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin10.setText("0");
        bin10.setBorder(null);
        bin10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin11.setBackground(jPanel12.getBackground());
        bin11.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin11.setText("0");
        bin11.setBorder(null);
        bin11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin12.setBackground(jPanel12.getBackground());
        bin12.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin12.setText("0");
        bin12.setBorder(null);
        bin12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("8");

        javax.swing.GroupLayout binKeyPanel2Layout = new javax.swing.GroupLayout(binKeyPanel2);
        binKeyPanel2.setLayout(binKeyPanel2Layout);
        binKeyPanel2Layout.setHorizontalGroup(
            binKeyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel2Layout.createSequentialGroup()
                        .addComponent(bin12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel2Layout.setVerticalGroup(
            binKeyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin12)
                    .addComponent(bin11)
                    .addComponent(bin10)
                    .addComponent(bin9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jPanel12.add(binKeyPanel2);

        binKeyPanel1.setMinimumSize(new java.awt.Dimension(80, 55));

        bin5.setBackground(jPanel12.getBackground());
        bin5.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin5.setText("0");
        bin5.setBorder(null);
        bin5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin6.setBackground(jPanel12.getBackground());
        bin6.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin6.setText("0");
        bin6.setBorder(null);
        bin6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin7.setBackground(jPanel12.getBackground());
        bin7.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin7.setText("0");
        bin7.setBorder(null);
        bin7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin8.setBackground(jPanel12.getBackground());
        bin8.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin8.setText("0");
        bin8.setBorder(null);
        bin8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("4");

        javax.swing.GroupLayout binKeyPanel1Layout = new javax.swing.GroupLayout(binKeyPanel1);
        binKeyPanel1.setLayout(binKeyPanel1Layout);
        binKeyPanel1Layout.setHorizontalGroup(
            binKeyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel1Layout.createSequentialGroup()
                        .addComponent(bin8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel1Layout.setVerticalGroup(
            binKeyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin8)
                    .addComponent(bin7)
                    .addComponent(bin6)
                    .addComponent(bin5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jPanel12.add(binKeyPanel1);

        binKeyPanel0.setMinimumSize(new java.awt.Dimension(80, 55));

        bin1.setBackground(jPanel12.getBackground());
        bin1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin1.setText("0");
        bin1.setBorder(null);
        bin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin2.setBackground(jPanel12.getBackground());
        bin2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin2.setText("0");
        bin2.setBorder(null);
        bin2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin3.setBackground(jPanel12.getBackground());
        bin3.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin3.setText("0");
        bin3.setBorder(null);
        bin3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        bin4.setBackground(jPanel12.getBackground());
        bin4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        bin4.setText("0");
        bin4.setBorder(null);
        bin4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBinKey(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("0");

        javax.swing.GroupLayout binKeyPanel0Layout = new javax.swing.GroupLayout(binKeyPanel0);
        binKeyPanel0.setLayout(binKeyPanel0Layout);
        binKeyPanel0Layout.setHorizontalGroup(
            binKeyPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, binKeyPanel0Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(binKeyPanel0Layout.createSequentialGroup()
                        .addComponent(bin4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        binKeyPanel0Layout.setVerticalGroup(
            binKeyPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(binKeyPanel0Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(binKeyPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bin4)
                    .addComponent(bin3)
                    .addComponent(bin2)
                    .addComponent(bin1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel12.add(binKeyPanel0);

        jPanel1.add(jPanel12);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setBackground(new java.awt.Color(0, 153, 153));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(338, 530));
        setState(2);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        Navigation.setBackground(getUIColor());
        Navigation.setMaximumSize(new java.awt.Dimension(265, 32816));
        Navigation.setMinimumSize(new java.awt.Dimension(265, 0));
        Navigation.setPreferredSize(new java.awt.Dimension(265, 0));

        jButton1.setBackground(getUIColor());
        jButton1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jButton1.setIcon(Graphical.getImage("src\\Images\\Navigation-"+getUIColorType()+".png",15,10));
        jButton1.setToolTipText("Close Navigation");
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

        jScrollPane5.setBackground(getUIColor());
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setMaximumSize(new java.awt.Dimension(270, 32816));
        jScrollPane5.setMinimumSize(new java.awt.Dimension(270, 60));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(270, 220));

        navBtns.setBackground(getUIColor());
        navBtns.setMaximumSize(new java.awt.Dimension(270, 845));
        navBtns.setMinimumSize(new java.awt.Dimension(270, 845));
        navBtns.setPreferredSize(new java.awt.Dimension(270, 845));
        navBtns.setLayout(new javax.swing.BoxLayout(navBtns, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("  Calculator");
        jLabel3.setMaximumSize(new java.awt.Dimension(260, 50));
        jLabel3.setMinimumSize(new java.awt.Dimension(260, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jLabel3);

        jButton2.setBackground(getUIColor());
        jButton2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton2.setText("Standard");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton2.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton2.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton2.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton2);

        jButton12.setBackground(getUIColor());
        jButton12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton12.setText("Scientific");
        jButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton12.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton12.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton12.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton12.setPreferredSize(new java.awt.Dimension(260, 50));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        navBtns.add(jButton12);

        jButton34.setBackground(getUIColor());
        jButton34.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton34.setText("Graphing");
        jButton34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton34.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton34.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton34.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton34.setPreferredSize(new java.awt.Dimension(260, 50));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        navBtns.add(jButton34);

        jButton13.setBackground(getUIColor());
        jButton13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton13.setText("Programmer");
        jButton13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton13.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton13.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton13.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton13.setPreferredSize(new java.awt.Dimension(260, 50));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        navBtns.add(jButton13);

        jButton35.setBackground(getUIColor());
        jButton35.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton35.setText("Date calculation");
        jButton35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton35.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton35.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton35.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton35.setPreferredSize(new java.awt.Dimension(260, 50));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        navBtns.add(jButton35);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("  Convertor");
        jLabel4.setMaximumSize(new java.awt.Dimension(260, 50));
        jLabel4.setMinimumSize(new java.awt.Dimension(260, 50));
        jLabel4.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jLabel4);

        jButton14.setBackground(getUIColor());
        jButton14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton14.setText("Volume");
        jButton14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton14.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton14.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton14.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton14.setPreferredSize(new java.awt.Dimension(260, 50));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        navBtns.add(jButton14);

        jButton15.setBackground(getUIColor());
        jButton15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton15.setText("Length");
        jButton15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton15.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton15.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton15.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton15.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton15);

        jButton16.setBackground(getUIColor());
        jButton16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton16.setText("Weight and Mass");
        jButton16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton16.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton16.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton16.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton16.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton16);

        jButton17.setBackground(getUIColor());
        jButton17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton17.setText("Temperature");
        jButton17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton17.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton17.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton17.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton17.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton17);

        jButton18.setBackground(getUIColor());
        jButton18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton18.setText("Energy");
        jButton18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton18.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton18.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton18.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton18.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton18);

        jButton19.setBackground(getUIColor());
        jButton19.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton19.setText("Area");
        jButton19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton19.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton19.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton19.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton19.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton19);

        jButton20.setBackground(getUIColor());
        jButton20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton20.setText("Speed");
        jButton20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton20.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton20.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton20.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton20.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton20);

        jButton21.setBackground(getUIColor());
        jButton21.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton21.setText("Time");
        jButton21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton21.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton21.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton21.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton21.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton21);

        jButton22.setBackground(getUIColor());
        jButton22.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton22.setText("Power");
        jButton22.setToolTipText("");
        jButton22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton22.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton22.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton22.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton22.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton22);

        jButton23.setBackground(getUIColor());
        jButton23.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton23.setText("Data");
        jButton23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton23.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton23.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton23.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton23.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton23);

        jButton24.setBackground(getUIColor());
        jButton24.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton24.setText("Presure");
        jButton24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton24.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton24.setMaximumSize(new java.awt.Dimension(260, 50));
        jButton24.setMinimumSize(new java.awt.Dimension(260, 50));
        jButton24.setPreferredSize(new java.awt.Dimension(260, 50));
        navBtns.add(jButton24);

        jButton25.setBackground(getUIColor());
        jButton25.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton25.setText("Angle");
        jButton25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton25.setMargin(new java.awt.Insets(0, 12, 0, 5));
        jButton25.setMaximumSize(new java.awt.Dimension(260, 40));
        jButton25.setMinimumSize(new java.awt.Dimension(260, 40));
        jButton25.setPreferredSize(new java.awt.Dimension(250, 40));
        navBtns.add(jButton25);

        jScrollPane5.setViewportView(navBtns);

        jButton26.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton26.setText("   About");
        jButton26.setBorder(null);
        jButton26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton26.setMargin(new java.awt.Insets(0, 5, 0, 5));

        javax.swing.GroupLayout NavigationLayout = new javax.swing.GroupLayout(Navigation);
        Navigation.setLayout(NavigationLayout);
        NavigationLayout.setHorizontalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, Short.MAX_VALUE)
        );
        NavigationLayout.setVerticalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(Navigation);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(jPanel2);

        calculator.setMinimumSize(new java.awt.Dimension(300, 460));
        calculator.setPreferredSize(new java.awt.Dimension(335, 460));
        calculator.setLayout(new javax.swing.BoxLayout(calculator, javax.swing.BoxLayout.PAGE_AXIS));

        topBar.setMaximumSize(new java.awt.Dimension(32767, 40));
        topBar.setMinimumSize(new java.awt.Dimension(320, 40));
        topBar.setPreferredSize(new java.awt.Dimension(320, 40));

        jButton3.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jButton3.setIcon(Graphical.getImage("src\\Images\\Navigation-"+getUIColorType()+".png",15,10));
        jButton3.setToolTipText("Open Navigation");
        jButton3.setBorder(null);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton3.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton3.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        CalType.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        CalType.setText("Standard");
        CalType.setMaximumSize(new java.awt.Dimension(250, 30));
        CalType.setMinimumSize(new java.awt.Dimension(250, 30));
        CalType.setPreferredSize(new java.awt.Dimension(250, 30));

        histryBtn.setText("H");
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

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CalType, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(histryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(histryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CalType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        calculator.add(topBar);

        showBar.setMaximumSize(new java.awt.Dimension(32816, 140));
        showBar.setPreferredSize(new java.awt.Dimension(32816, 140));
        showBar.setLayout(new javax.swing.BoxLayout(showBar, javax.swing.BoxLayout.PAGE_AXIS));

        pad1.setLayout(new javax.swing.BoxLayout(pad1, javax.swing.BoxLayout.PAGE_AXIS));

        eqtTxt.setEditable(false);
        eqtTxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        eqtTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        eqtTxt.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground(), 4));
        eqtTxt.setMargin(new java.awt.Insets(10, 10, 10, 10));
        eqtTxt.setMaximumSize(new java.awt.Dimension(32816, 50));
        eqtTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eqtTxtActionPerformed(evt);
            }
        });
        pad1.add(eqtTxt);

        cal.setEditable(false);
        cal.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        cal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cal.setText("0");
        cal.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground(), 4));
        cal.setMargin(new java.awt.Insets(10, 10, 10, 10));
        cal.setMaximumSize(new java.awt.Dimension(2147483647, 120));
        cal.setMinimumSize(new java.awt.Dimension(320, 52));
        cal.setPreferredSize(new java.awt.Dimension(320, 60));
        cal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                calKeyTyped(evt);
            }
        });
        pad1.add(cal);

        showBar.add(pad1);

        calculator.add(showBar);

        formatBar.setMaximumSize(new java.awt.Dimension(151516516, 50));

        jToggleButton3.setBackground(getUIColor());
        jToggleButton3.setText("F-E");
        jToggleButton3.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jToggleButton3.setPreferredSize(new java.awt.Dimension(47, 40));
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jButton29.setBackground(getUIColor());
        jButton29.setText("Deg");
        jButton29.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton29.setPreferredSize(new java.awt.Dimension(52, 40));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formatBarLayout = new javax.swing.GroupLayout(formatBar);
        formatBar.setLayout(formatBarLayout);
        formatBarLayout.setHorizontalGroup(
            formatBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatBarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        formatBarLayout.setVerticalGroup(
            formatBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        calculator.add(formatBar);

        memoryBar.setMaximumSize(new java.awt.Dimension(151516516, 50));
        memoryBar.setLayout(new javax.swing.BoxLayout(memoryBar, javax.swing.BoxLayout.LINE_AXIS));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText("MC");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton6.setMaximumSize(new java.awt.Dimension(48, 30));
        jButton6.setMinimumSize(new java.awt.Dimension(48, 30));
        jButton6.setPreferredSize(new java.awt.Dimension(48, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        memoryBar.add(jButton6);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("M+");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton7.setMaximumSize(new java.awt.Dimension(48, 30));
        jButton7.setMinimumSize(new java.awt.Dimension(48, 30));
        jButton7.setPreferredSize(new java.awt.Dimension(48, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        memoryBar.add(jButton7);

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setText("MR");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton8.setMaximumSize(new java.awt.Dimension(48, 30));
        jButton8.setMinimumSize(new java.awt.Dimension(48, 30));
        jButton8.setPreferredSize(new java.awt.Dimension(48, 30));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        memoryBar.add(jButton8);

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setText("M-");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton9.setMaximumSize(new java.awt.Dimension(48, 30));
        jButton9.setMinimumSize(new java.awt.Dimension(48, 30));
        jButton9.setPreferredSize(new java.awt.Dimension(48, 30));
        memoryBar.add(jButton9);

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setText("MS");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton10.setMaximumSize(new java.awt.Dimension(48, 30));
        jButton10.setMinimumSize(new java.awt.Dimension(48, 30));
        jButton10.setPreferredSize(new java.awt.Dimension(48, 30));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        memoryBar.add(jButton10);

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton11.setText("M▼");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton11.setMaximumSize(new java.awt.Dimension(48, 30));
        jButton11.setMinimumSize(new java.awt.Dimension(48, 30));
        jButton11.setPreferredSize(new java.awt.Dimension(48, 30));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        memoryBar.add(jButton11);

        calculator.add(memoryBar);

        trigFuncs.setMaximumSize(new java.awt.Dimension(151516516, 50));

        jButton27.setBackground(getUIColor());
        jButton27.setText("Trigonometry");
        jButton27.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton27.setPreferredSize(new java.awt.Dimension(106, 40));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setBackground(getUIColor());
        jButton28.setText("Functions");
        jButton28.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        jButton28.setPreferredSize(new java.awt.Dimension(85, 40));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout trigFuncsLayout = new javax.swing.GroupLayout(trigFuncs);
        trigFuncs.setLayout(trigFuncsLayout);
        trigFuncsLayout.setHorizontalGroup(
            trigFuncsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trigFuncsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        trigFuncsLayout.setVerticalGroup(
            trigFuncsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        calculator.add(trigFuncs);

        keyBar.setLayout(new javax.swing.BoxLayout(keyBar, javax.swing.BoxLayout.LINE_AXIS));

        row0.setLayout(new javax.swing.BoxLayout(row0, javax.swing.BoxLayout.PAGE_AXIS));

        ba.setBackground(getUIColor());
        ba.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ba.setText("A");
        ba.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        ba.setMaximumSize(new java.awt.Dimension(1000, 1000));
        ba.setMinimumSize(new java.awt.Dimension(40, 30));
        ba.setName("b7"); // NOI18N
        ba.setPreferredSize(new java.awt.Dimension(70, 47));
        ba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baActionPerformed(evt);
            }
        });
        row0.add(ba);

        bb.setBackground(getUIColor());
        bb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bb.setText("B");
        bb.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        bb.setMaximumSize(new java.awt.Dimension(1000, 1000));
        bb.setMinimumSize(new java.awt.Dimension(40, 30));
        bb.setName("b4"); // NOI18N
        bb.setPreferredSize(new java.awt.Dimension(70, 47));
        bb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbActionPerformed(evt);
            }
        });
        row0.add(bb);

        bc.setBackground(getUIColor());
        bc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bc.setText("C");
        bc.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        bc.setMaximumSize(new java.awt.Dimension(1000, 1000));
        bc.setMinimumSize(new java.awt.Dimension(40, 30));
        bc.setName("b1"); // NOI18N
        bc.setPreferredSize(new java.awt.Dimension(70, 47));
        bc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcActionPerformed(evt);
            }
        });
        row0.add(bc);

        bd.setBackground(getUIColor());
        bd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bd.setText("D");
        bd.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        bd.setMaximumSize(new java.awt.Dimension(1000, 1000));
        bd.setMinimumSize(new java.awt.Dimension(40, 30));
        bd.setName("b4"); // NOI18N
        bd.setPreferredSize(new java.awt.Dimension(70, 47));
        bd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdActionPerformed(evt);
            }
        });
        row0.add(bd);

        be.setBackground(getUIColor());
        be.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        be.setText("E");
        be.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        be.setMaximumSize(new java.awt.Dimension(1000, 1000));
        be.setMinimumSize(new java.awt.Dimension(40, 30));
        be.setName("b7"); // NOI18N
        be.setPreferredSize(new java.awt.Dimension(70, 47));
        be.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beActionPerformed(evt);
            }
        });
        row0.add(be);

        bf.setBackground(getUIColor());
        bf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bf.setText("F");
        bf.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        bf.setMaximumSize(new java.awt.Dimension(1000, 1000));
        bf.setMinimumSize(new java.awt.Dimension(40, 30));
        bf.setName("b7"); // NOI18N
        bf.setPreferredSize(new java.awt.Dimension(70, 47));
        bf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bfActionPerformed(evt);
            }
        });
        row0.add(bf);

        keyBar.add(row0);

        row1.setLayout(new javax.swing.BoxLayout(row1, javax.swing.BoxLayout.PAGE_AXIS));

        pers.setBackground(getUIColor());
        pers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pers.setText("%");
        pers.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        pers.setMaximumSize(new java.awt.Dimension(1000, 1000));
        pers.setMinimumSize(new java.awt.Dimension(40, 30));
        pers.setName("b7"); // NOI18N
        pers.setPreferredSize(new java.awt.Dimension(70, 47));
        pers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                persActionPerformed(evt);
            }
        });
        row1.add(pers);

        reciprocal.setBackground(getUIColor());
        reciprocal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reciprocal.setText("1/X");
        reciprocal.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        reciprocal.setMaximumSize(new java.awt.Dimension(1000, 1000));
        reciprocal.setMinimumSize(new java.awt.Dimension(40, 30));
        reciprocal.setName("b4"); // NOI18N
        reciprocal.setPreferredSize(new java.awt.Dimension(70, 47));
        reciprocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reciprocalActionPerformed(evt);
            }
        });
        row1.add(reciprocal);

        b1.setBackground(getUIColor());
        b1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b1.setText("1");
        b1.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b1.setMinimumSize(new java.awt.Dimension(40, 30));
        b1.setName("b1"); // NOI18N
        b1.setPreferredSize(new java.awt.Dimension(70, 47));
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        row1.add(b1);

        b4.setBackground(getUIColor());
        b4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b4.setText("4");
        b4.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b4.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b4.setMinimumSize(new java.awt.Dimension(40, 30));
        b4.setName("b4"); // NOI18N
        b4.setPreferredSize(new java.awt.Dimension(70, 47));
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        row1.add(b4);

        b7.setBackground(getUIColor());
        b7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b7.setText("7");
        b7.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b7.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b7.setMinimumSize(new java.awt.Dimension(40, 30));
        b7.setName("b7"); // NOI18N
        b7.setPreferredSize(new java.awt.Dimension(70, 47));
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        row1.add(b7);

        b10.setBackground(getUIColor());
        b10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b10.setText("+/-");
        b10.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b10.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b10.setMinimumSize(new java.awt.Dimension(40, 30));
        b10.setName("b7"); // NOI18N
        b10.setPreferredSize(new java.awt.Dimension(70, 47));
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });
        row1.add(b10);

        keyBar.add(row1);

        row2.setLayout(new javax.swing.BoxLayout(row2, javax.swing.BoxLayout.Y_AXIS));

        ce.setBackground(getUIColor());
        ce.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ce.setText("CE");
        ce.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        ce.setMaximumSize(new java.awt.Dimension(1000, 1000));
        ce.setMinimumSize(new java.awt.Dimension(40, 30));
        ce.setName("b8"); // NOI18N
        ce.setPreferredSize(new java.awt.Dimension(70, 47));
        ce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ceActionPerformed(evt);
            }
        });
        row2.add(ce);

        power.setBackground(getUIColor());
        power.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        power.setText("X²");
        power.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        power.setMaximumSize(new java.awt.Dimension(1000, 1000));
        power.setMinimumSize(new java.awt.Dimension(40, 30));
        power.setName("b5"); // NOI18N
        power.setPreferredSize(new java.awt.Dimension(70, 47));
        power.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerActionPerformed(evt);
            }
        });
        row2.add(power);

        b2.setBackground(getUIColor());
        b2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b2.setText("2");
        b2.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b2.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b2.setMinimumSize(new java.awt.Dimension(40, 30));
        b2.setName("b2"); // NOI18N
        b2.setPreferredSize(new java.awt.Dimension(70, 47));
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        row2.add(b2);

        b5.setBackground(getUIColor());
        b5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b5.setText("5");
        b5.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b5.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b5.setMinimumSize(new java.awt.Dimension(40, 30));
        b5.setName("b5"); // NOI18N
        b5.setPreferredSize(new java.awt.Dimension(70, 47));
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        row2.add(b5);

        b8.setBackground(getUIColor());
        b8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b8.setText("8");
        b8.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b8.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b8.setMinimumSize(new java.awt.Dimension(40, 30));
        b8.setName("b8"); // NOI18N
        b8.setPreferredSize(new java.awt.Dimension(70, 47));
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        row2.add(b8);

        b0.setBackground(getUIColor());
        b0.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b0.setText("0");
        b0.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b0.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b0.setMinimumSize(new java.awt.Dimension(40, 30));
        b0.setName("b0"); // NOI18N
        b0.setPreferredSize(new java.awt.Dimension(70, 47));
        b0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b0ActionPerformed(evt);
            }
        });
        row2.add(b0);

        keyBar.add(row2);

        row3.setLayout(new javax.swing.BoxLayout(row3, javax.swing.BoxLayout.PAGE_AXIS));

        c.setBackground(getUIColor());
        c.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        c.setText("C");
        c.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        c.setMaximumSize(new java.awt.Dimension(1000, 1000));
        c.setMinimumSize(new java.awt.Dimension(40, 30));
        c.setName("b9"); // NOI18N
        c.setPreferredSize(new java.awt.Dimension(70, 47));
        c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cActionPerformed(evt);
            }
        });
        row3.add(c);

        root.setBackground(getUIColor());
        root.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        root.setText("²√X");
        root.setToolTipText("X Root");
        root.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        root.setMaximumSize(new java.awt.Dimension(1000, 1000));
        root.setMinimumSize(new java.awt.Dimension(40, 30));
        root.setPreferredSize(new java.awt.Dimension(70, 47));
        root.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootActionPerformed(evt);
            }
        });
        row3.add(root);

        b9.setBackground(getUIColor());
        b9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b9.setText("9");
        b9.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b9.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b9.setMinimumSize(new java.awt.Dimension(40, 30));
        b9.setName("b9"); // NOI18N
        b9.setPreferredSize(new java.awt.Dimension(70, 47));
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });
        row3.add(b9);

        b6.setBackground(getUIColor());
        b6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b6.setText("6");
        b6.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b6.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b6.setMinimumSize(new java.awt.Dimension(40, 30));
        b6.setName("b6"); // NOI18N
        b6.setPreferredSize(new java.awt.Dimension(70, 47));
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        row3.add(b6);

        b3.setBackground(getUIColor());
        b3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        b3.setText("3");
        b3.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        b3.setMaximumSize(new java.awt.Dimension(1000, 1000));
        b3.setMinimumSize(new java.awt.Dimension(40, 30));
        b3.setName("b3"); // NOI18N
        b3.setPreferredSize(new java.awt.Dimension(70, 47));
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        row3.add(b3);

        dot.setBackground(getUIColor());
        dot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dot.setText(".");
        dot.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        dot.setMaximumSize(new java.awt.Dimension(1000, 1000));
        dot.setMinimumSize(new java.awt.Dimension(40, 30));
        dot.setName("bdot"); // NOI18N
        dot.setPreferredSize(new java.awt.Dimension(70, 47));
        dot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dotActionPerformed(evt);
            }
        });
        row3.add(dot);

        keyBar.add(row3);

        oparaters.setPreferredSize(new java.awt.Dimension(70, 310));
        oparaters.setLayout(new javax.swing.BoxLayout(oparaters, javax.swing.BoxLayout.PAGE_AXIS));

        clear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        clear.setText("clear");
        clear.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        clear.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        clear.setMaximumSize(new java.awt.Dimension(1000, 1000));
        clear.setMinimumSize(new java.awt.Dimension(40, 30));
        clear.setPreferredSize(new java.awt.Dimension(70, 47));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        oparaters.add(clear);

        multiply.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        multiply.setText("x");
        multiply.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        multiply.setMaximumSize(new java.awt.Dimension(1000, 1000));
        multiply.setMinimumSize(new java.awt.Dimension(40, 30));
        multiply.setPreferredSize(new java.awt.Dimension(70, 47));
        multiply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplyActionPerformed(evt);
            }
        });
        oparaters.add(multiply);

        sub.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        sub.setText("-");
        sub.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        sub.setMaximumSize(new java.awt.Dimension(1000, 1000));
        sub.setMinimumSize(new java.awt.Dimension(40, 30));
        sub.setPreferredSize(new java.awt.Dimension(70, 47));
        sub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subActionPerformed(evt);
            }
        });
        oparaters.add(sub);

        plus.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        plus.setText("+");
        plus.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        plus.setMaximumSize(new java.awt.Dimension(1000, 1000));
        plus.setMinimumSize(new java.awt.Dimension(40, 30));
        plus.setPreferredSize(new java.awt.Dimension(70, 47));
        plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusActionPerformed(evt);
            }
        });
        oparaters.add(plus);

        div.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        div.setText("÷");
        div.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        div.setMaximumSize(new java.awt.Dimension(1000, 1000));
        div.setMinimumSize(new java.awt.Dimension(40, 30));
        div.setPreferredSize(new java.awt.Dimension(70, 47));
        div.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divActionPerformed(evt);
            }
        });
        oparaters.add(div);

        equal.setBackground(new java.awt.Color(0, 39, 149));
        equal.setFont(new java.awt.Font("Tahoma", 0, 35)); // NOI18N
        equal.setText("=");
        equal.setBorder(javax.swing.BorderFactory.createLineBorder(calculator.getBackground()));
        equal.setMaximumSize(new java.awt.Dimension(1000, 1000));
        equal.setMinimumSize(new java.awt.Dimension(40, 30));
        equal.setPreferredSize(new java.awt.Dimension(70, 47));
        equal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equalActionPerformed(evt);
            }
        });
        oparaters.add(equal);

        keyBar.add(oparaters);

        calculator.add(keyBar);

        getContentPane().add(calculator);

        hisAndMem.setMaximumSize(new java.awt.Dimension(350, 32767));
        hisAndMem.setPreferredSize(new java.awt.Dimension(330, 548));
        hisAndMem.setLayout(new javax.swing.BoxLayout(hisAndMem, javax.swing.BoxLayout.LINE_AXIS));

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(330, 32767));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(330, 62));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(330, 64));

        jPanel11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel11.setMaximumSize(new java.awt.Dimension(330, 32802));
        jPanel11.setMinimumSize(new java.awt.Dimension(330, 400));
        jPanel11.setPreferredSize(new java.awt.Dimension(330, 38));
        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.PAGE_AXIS));

        jScrollPane3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jScrollPane3.setMaximumSize(new java.awt.Dimension(350, 32767));
        jScrollPane3.setMinimumSize(new java.awt.Dimension(350, 400));
        jPanel11.add(jScrollPane3);

        jPanel6.setMaximumSize(new java.awt.Dimension(32767, 35));
        jPanel6.setPreferredSize(new java.awt.Dimension(263, 35));

        jButton30.setText("Delete");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addComponent(jButton30)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel11.add(jPanel6);

        jTabbedPane1.addTab("Histry", jPanel11);

        jPanel13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel13.setMaximumSize(new java.awt.Dimension(330, 32802));
        jPanel13.setMinimumSize(new java.awt.Dimension(330, 400));
        jPanel13.setPreferredSize(new java.awt.Dimension(330, 38));
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.PAGE_AXIS));

        jScrollPane4.setMaximumSize(new java.awt.Dimension(330, 32767));
        jScrollPane4.setMinimumSize(new java.awt.Dimension(330, 20));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(330, 3));
        jPanel13.add(jScrollPane4);

        jPanel9.setMaximumSize(new java.awt.Dimension(32767, 35));
        jPanel9.setPreferredSize(new java.awt.Dimension(263, 35));

        jButton31.setText("Delete");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 262, Short.MAX_VALUE)
                .addComponent(jButton31))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel9);

        jTabbedPane1.addTab("Memory", jPanel13);

        hisAndMem.add(jTabbedPane1);

        getContentPane().add(hisAndMem);

        setBounds(0, 0, 975, 489);
    }// </editor-fold>//GEN-END:initComponents

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        digitPressed("2");
    }//GEN-LAST:event_b2ActionPerformed

    private void divActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divActionPerformed
        arithmeticPressed("÷");
    }//GEN-LAST:event_divActionPerformed

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed

    }//GEN-LAST:event_delActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        digitPressed("1");
    }//GEN-LAST:event_b1ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        digitPressed("3");
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        digitPressed("4");
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        digitPressed("5");
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        digitPressed("6");
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        digitPressed("7");
    }//GEN-LAST:event_b7ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        digitPressed("9");
    }//GEN-LAST:event_b9ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        digitPressed("8");
    }//GEN-LAST:event_b8ActionPerformed

    private void b0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b0ActionPerformed
        digitPressed("0");
    }//GEN-LAST:event_b0ActionPerformed

    private void dotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dotActionPerformed
        Message.setText("");
        int Index = 0;
        for (char Char : calTxt.getText().toCharArray()) {
            if (Char == '.') {
                Index++;
            }
        }
        if (Index == 0) {
            calTxt.setText(calTxt.getText() + ".");
        } else {
            Message.setText("Action cannot be completed!");
        }
        calTxt.requestFocus();
    }//GEN-LAST:event_dotActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        calKeyTyped(evt);
    }//GEN-LAST:event_formKeyTyped

    private void equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equalActionPerformed
        Double val = 0.;
        if (firstNum != null && arithExp != null && secondNum != null) {
            val = calculate();
            setFirstNum(firstNum, secondNum, arithExp);
            histryPanel.newCell(getFirstNum(firstNum, secondNum, arithExp), Exact(val));
        } else if (firstNum != null && arithExp != null) {
            secondNum = 0.;
            val = calculate();
        } else if (firstNum != null) {
            val = firstNum;
            setFirstNum(val, "=");
        }
        firstNum = val;
        arithExp = null;
        secondNum = null;
        setValue(val);
        calTxt.requestFocus();
    }//GEN-LAST:event_equalActionPerformed

    private void plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusActionPerformed
        arithmeticPressed("+");
    }//GEN-LAST:event_plusActionPerformed

    private void subActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subActionPerformed
        arithmeticPressed("-");
    }//GEN-LAST:event_subActionPerformed

    private void multiplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplyActionPerformed
        arithmeticPressed("*");
    }//GEN-LAST:event_multiplyActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        if (value != 0) {
            setValue(value.toString().subSequence(0, value.toString().length() - 1).toString());
        }
    }//GEN-LAST:event_clearActionPerformed

    private void sqrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sqrActionPerformed

    }//GEN-LAST:event_sqrActionPerformed

    private void equationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equationActionPerformed

    }//GEN-LAST:event_equationActionPerformed

    private void histryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histryBtnActionPerformed

    }//GEN-LAST:event_histryBtnActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        calTxt.requestFocus();
    }//GEN-LAST:event_formMouseClicked

    private void equationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equationMouseClicked
    }//GEN-LAST:event_equationMouseClicked

    private void DecimalTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DecimalTypesActionPerformed
//        String SelIt = DecimalTypes.getSelectedItem().toString();
//        String ans;
//        switch (SelIt) {
//            case BIN:
//                ans = CalculateProg(BIN);
//                calTxt.setText(ans);
//                calTxt.requestFocus();
//                break;
//            case HEX:
//                CalculateProg(HEX);
//                calTxt.requestFocus();
//                break;
//            case OCT:
//                ans = CalculateProg(OCT);
//                calTxt.setText(ans);
//                calTxt.requestFocus();
//                break;
//        }
    }//GEN-LAST:event_DecimalTypesActionPerformed

    private void rootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rootActionPerformed
        funcPressed("sqrt", value);
    }//GEN-LAST:event_rootActionPerformed

    private void logorithmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logorithmsActionPerformed
        String SelIt = logorithms.getSelectedItem().toString();
        String ans;
        switch (SelIt) {
            case LOG:
                calculateTrig(LOG);
                calTxt.requestFocus();
                break;
            case ANTILOG:
                calculateTrig(ANTILOG);
                calTxt.requestFocus();
                break;

        }
    }//GEN-LAST:event_logorithmsActionPerformed

    private void mainTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainTabMouseClicked

    }//GEN-LAST:event_mainTabMouseClicked

    private void calKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_calKeyTyped
        Message.setText("");
        String text = calTxt.getText();
        char key = evt.getKeyChar();
        switch (key) {
            case '1':
                b1.doClick();
                break;
            case '2':
                b2.doClick();
                break;
            case '3':
                b3.doClick();
                break;
            case '4':
                b4.doClick();
                break;
            case '5':
                b5.doClick();
                break;
            case '6':
                b6.doClick();
                break;
            case '7':
                b7.doClick();
                break;
            case '8':
                b8.doClick();
                break;
            case '9':
                b9.doClick();
                break;
            case '0':
                b0.doClick();
                break;
            case '+':
                plus.doClick();
                break;
            case '-':
                sub.doClick();
                break;
            case '*':
                multiply.doClick();
                break;
            case '/':
                div.doClick();
                break;
            case '.':
                dot.doClick();
                break;
            case KeyEvent.VK_BACK_SPACE:
                evt.consume();
                if (text.length() > 1) {
                    calTxt.setText(text.substring(0, text.length() - 1));
                } else if (text.length() == 1) {
                    calTxt.setText("0");
                }
                break;
            case KeyEvent.VK_ENTER:
                equal.doClick();
                break;
        }
    }//GEN-LAST:event_calKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        navigation(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        navigation(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void convertsText2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_convertsText2MouseClicked
        getFocus(convertsText2);
        forgetFocus(convertsText1);
        calTxt = convertsText2;
        SelConCombo = 1;
    }//GEN-LAST:event_convertsText2MouseClicked

    private void convertsText1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_convertsText1MouseClicked
        SelConCombo = 2;
        getFocus(convertsText1);
        forgetFocus(convertsText2);
        calTxt = convertsText1;
    }//GEN-LAST:event_convertsText1MouseClicked

    private void convertsText2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_convertsText2KeyTyped
        calKeyTyped(evt);
        setConvertedValue(Double.valueOf(calTxt.getText()), getCalType().idx - 5, convertsText1);
    }//GEN-LAST:event_convertsText2KeyTyped

    private void convertsText1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_convertsText1KeyTyped
        calKeyTyped(evt);
        setConvertedValue(Double.valueOf(calTxt.getText()), getCalType().idx - 5, convertsText2);
    }//GEN-LAST:event_convertsText1KeyTyped

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        cal.setText(Exact(-1 * Double.valueOf(cal.getText())));
        watch();
    }//GEN-LAST:event_b10ActionPerformed

    private void equation2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equation2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equation2ActionPerformed

    private void unitSelection2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitSelection2ActionPerformed
        setConvertedValue(Double.valueOf(calTxt.getText()), getCalType().idx - 5, getOpasite());
    }//GEN-LAST:event_unitSelection2ActionPerformed

    private void unitSelection1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitSelection1ActionPerformed
        setConvertedValue(Double.valueOf(calTxt.getText()), getCalType().idx - 5, getOpasite());
    }//GEN-LAST:event_unitSelection1ActionPerformed

    private void ConCombo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConCombo3ActionPerformed
        setConvertedValue(Double.valueOf(calTxt.getText()), getCalType().idx - 5, getOpasite());
    }//GEN-LAST:event_ConCombo3ActionPerformed

    private void ConCombo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConCombo4ActionPerformed
        setConvertedValue(Double.valueOf(calTxt.getText()), getCalType().idx - 5, getOpasite());
    }//GEN-LAST:event_ConCombo4ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int Confirm = JOptionPane.showConfirmDialog(null, "This wil restart the programme\nAre you sure want to continue!");
        if (Confirm == 0) {
            setApearence(LIGHT);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int Confirm = JOptionPane.showConfirmDialog(null, "This wil restart the programme\nAre you sure want to continue!");
        if (Confirm == 0) {
            setApearence(DARK);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void AOTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AOTopActionPerformed
        ALWAYSONTOP = !ALWAYSONTOP;
        try {
            Execute("update ComTable set AlwaysOnTop='" + ALWAYSONTOP + "'");
        } catch (SQLException ex) {
            Logger.getLogger(FrontFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ALWAYSONTOP) {
            Message.setText("Always on top enabeld!");
        } else {
            Message.setText("Always on top disabeld!");
        }
        this.setAlwaysOnTop(ALWAYSONTOP);
        setChecks();
    }//GEN-LAST:event_AOTopActionPerformed

    private void autoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoSaveActionPerformed
        AUTOIMPORT = !AUTOIMPORT;
        try {
            Execute("update ComTable set AutoImport='" + AUTOIMPORT + "'");
        } catch (SQLException ex) {
            Logger.getLogger(FrontFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (AUTOIMPORT) {
            Message.setText("Auto save enabeld!");
        } else {
            Message.setText("Auto save disabeld!");
        }
        setChecks();
    }//GEN-LAST:event_autoSaveActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int width = getWidth();
        int height = getHeight();
        boolean t = false;
        if (width >= 560) {
            hisAndMem.setVisible(true);
        } else {
            hisAndMem.setVisible(false);
        }
    }//GEN-LAST:event_formComponentResized

    private void eqtTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eqtTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eqtTxtActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void persActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_persActionPerformed
        funcPressed("%", value);
    }//GEN-LAST:event_persActionPerformed

    private void ceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceActionPerformed
        setValue(0.);
    }//GEN-LAST:event_ceActionPerformed

    private void cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cActionPerformed
        setValue(0.);
        eqtTxt.setText("");
        firstNum = 0.;
    }//GEN-LAST:event_cActionPerformed

    private void reciprocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reciprocalActionPerformed
        funcPressed("1/x", value);
    }//GEN-LAST:event_reciprocalActionPerformed

    private void powerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerActionPerformed
        funcPressed("pow", value);
    }//GEN-LAST:event_powerActionPerformed

    private void baActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_baActionPerformed

    private void bbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bbActionPerformed

    private void bcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bcActionPerformed

    private void bdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bdActionPerformed

    private void beActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beActionPerformed

    private void bfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bfActionPerformed

    private void cosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cosActionPerformed
        funcPressed(cos.getText(), value);
    }//GEN-LAST:event_cosActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        JButton[] btns = new JButton[]{
            sin, cos, tan, cosec, sec, cot
        };
        for (JButton btn : btns) {
            String bt = btn.getText();
            btn.setText(!jToggleButton1.isSelected() ? bt.replace(EXPONENTNEGATE + EXPONENT1, "") : btn.getText() + EXPONENTNEGATE + EXPONENT1);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        trigonometry.setVisible(!trigonometry.isVisible());
        funcs.setVisible(false);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        jButton29.setText(jButton29.getText().equals("Deg") ? "Rad" : "Deg");
        trig.angle = jButton29.getText().toLowerCase();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        scifi_notation = jToggleButton3.isSelected();
        setValue(value);
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        memoryBar.removeAll();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        setValue(MemoryPanel.memStore);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        MemoryPanel.memStore = value;
        memoryPanel.newCell(value);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void piActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piActionPerformed
        funcPressed("pi", value);
    }//GEN-LAST:event_piActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        hisAndMem.remove(histryPanel);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void powerYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerYActionPerformed
        arithmeticPressed("^");
    }//GEN-LAST:event_powerYActionPerformed

    private void powerOf10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerOf10ActionPerformed
        funcPressed("10^", value);
    }//GEN-LAST:event_powerOf10ActionPerformed

    private void log10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log10ActionPerformed
        funcPressed("log", value);
    }//GEN-LAST:event_log10ActionPerformed

    private void inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inActionPerformed

    private void eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eActionPerformed
        funcPressed("e", 0.);
    }//GEN-LAST:event_eActionPerformed

    private void absActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_absActionPerformed
        funcPressed("abs", value);
    }//GEN-LAST:event_absActionPerformed

    private void power3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_power3ActionPerformed
        funcPressed("^3", value);
    }//GEN-LAST:event_power3ActionPerformed

    private void root3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_root3ActionPerformed
        funcPressed("3√", value);
    }//GEN-LAST:event_root3ActionPerformed

    private void rootYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rootYActionPerformed
        arithmeticPressed("root");
    }//GEN-LAST:event_rootYActionPerformed

    private void powerOf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerOf2ActionPerformed
        funcPressed("2^", value);
    }//GEN-LAST:event_powerOf2ActionPerformed

    private void logXYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logXYActionPerformed
        arithmeticPressed("log base");
    }//GEN-LAST:event_logXYActionPerformed

    private void powerOfeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerOfeActionPerformed
        funcPressed("e^", value);
    }//GEN-LAST:event_powerOfeActionPerformed

    private void sci2ndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sci2ndActionPerformed
        JButton[] b = sci2nd.isSelected() ? new JButton[]{
            power3, root3, rootY, powerOf2, logXY, powerOfe
        } : new JButton[]{
            power, root, powerY, powerOf10, log10, in
        };
        row0.removeAll();
        row0.add(sci2nd);
        for (JButton btn : b) {
            row0.add(btn);
        }
        row0.repaint();
        row0.validate();
    }//GEN-LAST:event_sci2ndActionPerformed

    private void factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factActionPerformed
        funcPressed("n!", value);
    }//GEN-LAST:event_factActionPerformed

    private void bracLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bracLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bracLActionPerformed

    private void bracRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bracRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bracRActionPerformed

    private void expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expActionPerformed
        setValue(value);
    }//GEN-LAST:event_expActionPerformed

    private void modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modActionPerformed

    private void cosecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cosecActionPerformed
        funcPressed(cosec.getText(), value);
    }//GEN-LAST:event_cosecActionPerformed

    public static final String EXPONENT1 = "¹";
    public static final String EXPONENT2 = "²";
    public static final String EXPONENT3 = "³";
    public static final String EXPONENTNEGATE = "⁻";

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        JButton[] btns = new JButton[]{
            sin, cos, tan, cosec, sec, cot
        };

        for (JButton btn : btns) {
            String bt = btn.getText();
            String main = btn.getText().replace("h", "").replace(EXPONENTNEGATE + EXPONENT1, "");
            btn.setText(jToggleButton2.isSelected()
                    ? jToggleButton1.isSelected()
                    ? main + "h" + EXPONENTNEGATE + EXPONENT1
                    : main + "h"
                    : jToggleButton1.isSelected()
                    ? main + EXPONENTNEGATE + EXPONENT1
                    : main);
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void floorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floorActionPerformed
        funcPressed("floor", value);
    }//GEN-LAST:event_floorActionPerformed

    private void ceilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceilActionPerformed
        funcPressed("ceil", value);
    }//GEN-LAST:event_ceilActionPerformed

    private void bEmptyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEmptyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bEmptyActionPerformed

    private void roundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundActionPerformed
        funcPressed("round", value);
    }//GEN-LAST:event_roundActionPerformed

    private void randActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randActionPerformed
        setValue(new Random().nextDouble());
    }//GEN-LAST:event_randActionPerformed

    private void rand1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rand1ActionPerformed
        funcPressed("deg", value);
    }//GEN-LAST:event_rand1ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        memoryPanel.removeAll();
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        funcs.setVisible(!funcs.isVisible());
        trigonometry.setVisible(false);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void rand2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rand2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rand2ActionPerformed

    private void sinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinActionPerformed
        funcPressed(sin.getText(), value);
    }//GEN-LAST:event_sinActionPerformed

    private void tanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanActionPerformed
        funcPressed(tan.getText(), value);
    }//GEN-LAST:event_tanActionPerformed

    private void secActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secActionPerformed
        funcPressed(sec.getText(), value);
    }//GEN-LAST:event_secActionPerformed

    private void cotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cotActionPerformed
        funcPressed(cot.getText(), value);
    }//GEN-LAST:event_cotActionPerformed

    private void shiftLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shiftLActionPerformed

    private void shiftRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shiftRActionPerformed

    private void andActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_andActionPerformed

    private void orActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orActionPerformed

    private void notActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notActionPerformed

    private void nandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nandActionPerformed

    private void norActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_norActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_norActionPerformed

    private void xorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xorActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        bitshift.show(jButton33, 0, jButton33.getWidth());
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        String[] s = new String[]{
            "QWORD", "DWORD", "WORD", "BYTE"
        };
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            if (jButton37.getText().equals(s1)) {
                if (i > s.length - 1) {
                    jButton37.setText(s[i + 1]);
                } else {
                    jButton37.setText(s[0]);
                }
            }
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void setBinKey(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setBinKey
        JButton b = (JButton) evt.getSource();
        b.setText(b.getText().equals("0") ? "1" : "0");
        b.setForeground(b.getText().equals("0") ? b.getParent().getBackground() : APP_THEME);
    }//GEN-LAST:event_setBinKey

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        setCalType(jButton34.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        setCalType(jButton35.getText());
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    public void setBinKey(JButton b) {

    }

    private int SelConCombo;

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrontFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrontFrame().setVisible(true);
            }
        });
    }

    public class Buttons {

        public boolean visible;
        public ArrayList<JButton> btns;
        public ArrayList<ActionListener> listeners;

        public Buttons() {
            this(true, new ArrayList(), new ArrayList());
        }

        public Buttons(boolean visible, ArrayList<JButton> btns, ArrayList<ActionListener> listeners) {
            this.visible = visible;
            this.btns = btns;
            this.listeners = listeners;
        }

        public Buttons(ArrayList<JButton> btns, ArrayList<ActionListener> listeners) {
            this.btns = btns;
            this.listeners = listeners;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public ArrayList<JButton> getBtns() {
            return btns;
        }

        public void setBtns(ArrayList<JButton> btns) {
            this.btns = btns;
        }

        public ArrayList<ActionListener> getListeners() {
            return listeners;
        }

        public void setListeners(ArrayList<ActionListener> listeners) {
            this.listeners = listeners;
        }

    }

    public class Prog extends Programmer.BaseN {

        private Double answer;
        private Double firstnum;
        private Double secondnum;
        private int numType;
        private String Opar;
        protected static final String PLUS = "+";
        protected static final String SUB = "-";
        protected static final String MULT = "x";
        protected static final String DIV = "/";
        protected static final int BIN = 2;
        protected static final int HEX = 16;
        protected static final int OCT = 8;
        protected static final int DEC = 10;

        public Prog(Base b) {
            super(b);
            this.numType = b.BASE;
            firstnum = getDecimal("0", numType);
            secondnum = getDecimal("0", numType);
        }

        public void setValues(String fnum, String snum, int numType, String Opar) {
            this.numType = numType;
            firstnum = getDecimal(fnum, numType);
            secondnum = getDecimal(snum, numType);
            this.Opar = Opar;
        }

        public void resetVals() {
            numType = DEC;
            firstnum = null;
            secondnum = null;
            Opar = null;
        }

        public String getAns(int numType) {
            String NTans = null;
            switch (base) {
                case BIN:
                    NTans = DecimaltoNBase(doMath(), 2);
                    break;
                case HEX:
                    NTans = DecimaltoNBase(doMath(), 16);
                    break;
                case OCT:
                    NTans = DecimaltoNBase(doMath(), 8);
                    break;
            }
            return NTans;
        }

        public Double doMath() {
            switch (Opar) {
                case PLUS:
                    answer = add();
                    break;
                case SUB:
                    answer = sub();
                    break;
                case MULT:
                    answer = mul();
                    break;
                case DIV:
                    answer = div();
                    break;
            }
            return answer;
        }

        public final Double getDecimal(String bin, int ragex) {
            Double num = 0.;
            char Number[] = bin.toCharArray();
            char arr[] = ("abcdefABCDEF").toCharArray();
            String nums[] = new String[Number.length];
            for (int i = 0; i < Number.length; i++) {
                nums[i] = Character.toString(Number[i]);
            }
            double Index1 = 0;
            for (int Index = bin.length() - 1; Index >= 0; Index--) {
                for (char c : arr) {
                    if (c == Number[Index]) {
                        nums[Index] = Integer.toString(Integer.parseUnsignedInt(Character.toString(Number[Index]), ragex));
                    }
                }
//            System.out.println(num + " + " + (Math.pow(ragex, Index1)) + " * " + Integer.parseInt(nums[Index]) + " = " + (num + (Math.pow(ragex, Index1)) * Integer.parseInt(nums[Index])));
                num = num + (Math.pow(ragex, Index1)) * Integer.parseInt(nums[Index]);
                Index1++;
            }
            return num;
        }

        public String getNumType(Integer num, int numType) {
            String number = null;
            switch (numType) {
                case BIN:
                    number = Integer.toBinaryString(num);
                    break;
                case HEX:
                    number = Integer.toHexString(num);
                    break;
                case OCT:
                    number = Integer.toOctalString(num);
                    break;
            }
            return number;
        }

        public String DecimaltoNBase(Double Val, Integer ragex) {
            String dec = "";
            while (true) {
                String cc = Double.toString((Val % ragex));
                if (Val < Integer.MAX_VALUE) {
                    Val = (double) (Val.intValue() / ragex);
                } else {
                    Val = getInteger(Val / ragex.doubleValue());
                }
                boolean b = true;
                Double arr[] = {10., 11., 12., 13., 14., 15.};
                if (ragex == 16) {
                    for (Double arr1 : arr) {
                        if (cc.equals(Double.toString(arr1))) {
                            cc = Integer.toHexString(arr1.intValue());
                            b = false;
                            break;
                        }
                    }
                }
                if (b) {
                    cc = Integer.toString((int) (Double.parseDouble(cc)));
                }
                dec = String.join("", cc, dec);
                if (0 >= Val.intValue()) {
                    break;
                }
            }
            return dec.toUpperCase();
        }

        public Double getInteger(Double Val) {
            String vl = Double.toString(Val);
            char cc[] = vl.toCharArray();
            boolean b = false;
            int Index = 0;
            for (int i = 0; i < cc.length; i++) {
                if (cc[i] == 'E') {
                    b = true;
                    Index = i++;
                    break;
                }
            }
            if (b) {
                String vlBeforeE = String.valueOf(cc, 0, Index);
                String vlAfterE = String.valueOf(cc, Index + 1, cc.length - vlBeforeE.length() - 1);
                String vlAfterDot = String.valueOf(cc, 2, Index - 2);
                if (vlAfterDot.length() <= Integer.parseInt(vlAfterE)) {
                    return Val;
                } else {
                    vl = String.copyValueOf(cc, 0, Integer.parseInt(vlAfterE) + 2);
                    return Double.parseDouble(vl + "E" + Integer.parseInt(vlAfterE));
                }
            } else {
                return Val;
            }

        }

        private Double add() {
            return firstnum + secondnum;
        }

        private Double sub() {
            return firstnum - secondnum;
        }

        private Double mul() {
            return firstnum * secondnum;
        }

        private Double div() {
            return firstnum / secondnum;
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem AOTop;
    private javax.swing.JToolBar ArithOpars;
    private javax.swing.JLabel CalType;
    private javax.swing.JComboBox<String> ConCombo3;
    private javax.swing.JComboBox<String> ConCombo4;
    private javax.swing.JComboBox<String> DecimalTypes;
    private javax.swing.JLabel Message;
    private javax.swing.JPanel Navigation;
    private javax.swing.JPanel Standard;
    private javax.swing.JButton abs;
    private javax.swing.JButton and;
    private javax.swing.JCheckBoxMenuItem autoSave;
    private javax.swing.JButton b0;
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton bEmpty;
    private javax.swing.JButton ba;
    private javax.swing.JButton bb;
    private javax.swing.JButton bc;
    private javax.swing.JButton bd;
    private javax.swing.JButton be;
    private javax.swing.JButton bf;
    private javax.swing.JButton bin1;
    private javax.swing.JButton bin10;
    private javax.swing.JButton bin100;
    private javax.swing.JButton bin101;
    private javax.swing.JButton bin102;
    private javax.swing.JButton bin103;
    private javax.swing.JButton bin104;
    private javax.swing.JButton bin105;
    private javax.swing.JButton bin106;
    private javax.swing.JButton bin107;
    private javax.swing.JButton bin108;
    private javax.swing.JButton bin109;
    private javax.swing.JButton bin11;
    private javax.swing.JButton bin110;
    private javax.swing.JButton bin111;
    private javax.swing.JButton bin112;
    private javax.swing.JButton bin12;
    private javax.swing.JButton bin13;
    private javax.swing.JButton bin14;
    private javax.swing.JButton bin15;
    private javax.swing.JButton bin16;
    private javax.swing.JButton bin17;
    private javax.swing.JButton bin18;
    private javax.swing.JButton bin19;
    private javax.swing.JButton bin2;
    private javax.swing.JButton bin20;
    private javax.swing.JButton bin21;
    private javax.swing.JButton bin22;
    private javax.swing.JButton bin23;
    private javax.swing.JButton bin24;
    private javax.swing.JButton bin25;
    private javax.swing.JButton bin26;
    private javax.swing.JButton bin27;
    private javax.swing.JButton bin28;
    private javax.swing.JButton bin29;
    private javax.swing.JButton bin3;
    private javax.swing.JButton bin30;
    private javax.swing.JButton bin31;
    private javax.swing.JButton bin32;
    private javax.swing.JButton bin4;
    private javax.swing.JButton bin5;
    private javax.swing.JButton bin6;
    private javax.swing.JButton bin7;
    private javax.swing.JButton bin8;
    private javax.swing.JButton bin81;
    private javax.swing.JButton bin82;
    private javax.swing.JButton bin83;
    private javax.swing.JButton bin84;
    private javax.swing.JButton bin85;
    private javax.swing.JButton bin86;
    private javax.swing.JButton bin87;
    private javax.swing.JButton bin88;
    private javax.swing.JButton bin89;
    private javax.swing.JButton bin9;
    private javax.swing.JButton bin90;
    private javax.swing.JButton bin91;
    private javax.swing.JButton bin92;
    private javax.swing.JButton bin93;
    private javax.swing.JButton bin94;
    private javax.swing.JButton bin95;
    private javax.swing.JButton bin96;
    private javax.swing.JButton bin97;
    private javax.swing.JButton bin98;
    private javax.swing.JButton bin99;
    private javax.swing.JPanel binBar;
    private javax.swing.JPanel binKeyPanel0;
    private javax.swing.JPanel binKeyPanel1;
    private javax.swing.JPanel binKeyPanel2;
    private javax.swing.JPanel binKeyPanel20;
    private javax.swing.JPanel binKeyPanel21;
    private javax.swing.JPanel binKeyPanel22;
    private javax.swing.JPanel binKeyPanel23;
    private javax.swing.JPanel binKeyPanel24;
    private javax.swing.JPanel binKeyPanel25;
    private javax.swing.JPanel binKeyPanel26;
    private javax.swing.JPanel binKeyPanel27;
    private javax.swing.JPanel binKeyPanel3;
    private javax.swing.JPanel binKeyPanel4;
    private javax.swing.JPanel binKeyPanel5;
    private javax.swing.JPanel binKeyPanel6;
    private javax.swing.JPanel binKeyPanel7;
    private javax.swing.JLabel binLabel;
    private javax.swing.JSeparator binSelected;
    private javax.swing.JTextField binTxt;
    private javax.swing.JPopupMenu bitshift;
    private javax.swing.JButton bracL;
    private javax.swing.JButton bracR;
    private javax.swing.JButton c;
    private javax.swing.JTextField cal;
    private javax.swing.JPanel calculator;
    private javax.swing.JButton ce;
    private javax.swing.JButton ceil;
    private javax.swing.JButton clear;
    private javax.swing.JTextField convertsText1;
    private javax.swing.JTextField convertsText2;
    private javax.swing.JButton cos;
    private javax.swing.JButton cosec;
    private javax.swing.JButton cot;
    private javax.swing.JPanel decBar;
    private javax.swing.JLabel decLabel;
    private javax.swing.JSeparator decSelected;
    private javax.swing.JTextField decTxt;
    private javax.swing.JButton del;
    private javax.swing.JButton div;
    private javax.swing.JButton dot;
    private javax.swing.JButton e;
    private javax.swing.JTextField eqtTxt;
    private javax.swing.JButton equal;
    private javax.swing.JTextField equation;
    private javax.swing.JTextField equation2;
    private javax.swing.JButton exp;
    private javax.swing.JButton fact;
    private javax.swing.JButton floor;
    private javax.swing.JPanel formatBar;
    private javax.swing.JPanel funcs;
    private javax.swing.JPanel hexBar;
    private javax.swing.JLabel hexLabel;
    private javax.swing.JSeparator hexSelected;
    private javax.swing.JTextField hexTxt;
    private javax.swing.JPanel hisAndMem;
    private javax.swing.JButton histryBtn;
    private javax.swing.JButton in;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JPanel keyBar;
    private javax.swing.JButton log10;
    private javax.swing.JButton logXY;
    private javax.swing.JComboBox<String> logorithms;
    private javax.swing.JTabbedPane mainTab;
    private javax.swing.JPanel memoryBar;
    private javax.swing.JButton mod;
    private javax.swing.JButton multiply;
    private javax.swing.JButton nand;
    private javax.swing.JPanel navBtns;
    private javax.swing.JButton nor;
    private javax.swing.JButton not;
    private javax.swing.JPanel octBar;
    private javax.swing.JLabel octLabel;
    private javax.swing.JSeparator octSelected;
    private javax.swing.JTextField octTxt;
    private javax.swing.JPanel oparaters;
    private javax.swing.JButton or;
    private javax.swing.JPanel otherKeys;
    private javax.swing.JPanel otherPad;
    private javax.swing.JPanel pad1;
    private javax.swing.JLabel per;
    private javax.swing.JLabel per1;
    private javax.swing.JButton pers;
    private javax.swing.JButton pi;
    private javax.swing.JButton plus;
    private javax.swing.JButton power;
    private javax.swing.JButton power3;
    private javax.swing.JButton powerOf10;
    private javax.swing.JButton powerOf2;
    private javax.swing.JButton powerOfe;
    private javax.swing.JButton powerY;
    private javax.swing.JPanel progFuncs;
    private javax.swing.JPanel programmerBitwise;
    private javax.swing.JPanel programmerKeyboards;
    private javax.swing.JPanel programmerShowBar;
    private javax.swing.JButton rand;
    private javax.swing.JButton rand1;
    private javax.swing.JButton rand2;
    private javax.swing.JButton reciprocal;
    private javax.swing.JButton root;
    private javax.swing.JButton root3;
    private javax.swing.JButton rootY;
    private javax.swing.JButton round;
    private javax.swing.JPanel row0;
    private javax.swing.JPanel row1;
    private javax.swing.JPanel row2;
    private javax.swing.JPanel row3;
    private javax.swing.JToggleButton sci2nd;
    private javax.swing.JButton sec;
    private javax.swing.JButton shiftL;
    private javax.swing.JButton shiftR;
    private javax.swing.JPanel showBar;
    private javax.swing.JButton sin;
    private javax.swing.JButton sqr;
    private javax.swing.JButton sub;
    private javax.swing.JButton tan;
    private javax.swing.JPanel topBar;
    private javax.swing.JPanel trigFuncs;
    private javax.swing.JPanel trigonometry;
    private javax.swing.JComboBox<String> unitSelection1;
    private javax.swing.JComboBox<String> unitSelection2;
    private javax.swing.JButton xor;
    // End of variables declaration//GEN-END:variables
}
