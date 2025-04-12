package com.codes;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Commons {

    public static Date date = Calendar.getInstance().getTime();
    static int month;
    static int day;
    static int year;
    static int Second;
    static int Minute;
    static int Hour;
    static PreparedStatement pst = null;
    static ResultSet rs = null;
    static String defaultImage = "src\\Images\\WhiteBackground.png";
    public static final String APPLICATIONNAME = "Calculator";

    public static void CopytoClipboard(String text) {
        java.awt.datatransfer.Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection sel = new StringSelection(text);
        clip.setContents(sel, sel);
    }

    public static int showConfMsg(String e) {
        return JOptionPane.showConfirmDialog(null, e);
    }

    public static void showMsg(Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }

    public static int getInt(String val) {
        try {
            Integer.parseInt(val);
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double getDouble(String val) {
        try {
            Double.parseDouble(val);
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static boolean tableIsSelected(JTable table) {
        if (table.getSelectedRow() == -1) {
            return false;
        } else {
            return true;
        }
    }

    public static void showMsg(String e) {
        JOptionPane.showMessageDialog(null, e);
    }

    public static void CopyPasteFile(String CurrentPath, String NewPath) {
        try {
            FileInputStream in = new FileInputStream(CurrentPath);
            FileOutputStream out = new FileOutputStream(NewPath);
            BufferedOutputStream bout;
            try (BufferedInputStream bin = new BufferedInputStream(in)) {
                bout = new BufferedOutputStream(out);
                int bi = 0;
                while (bi != -1) {
                    bi = bin.read();
                    bout.write(bi);
                }
            }
            bout.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static String CurrentDate() {
        return String.format("%04d-%02d-%02d", date.getYear() + 1900, date.getMonth() + 1, date.getDate());
    }

    public static Date currentDate() {
        return date;
    }

    public static String CurrentTime() {
        return String.format("%02d:%02d:%02d", date.getHours(), date.getMinutes(), date.getSeconds());
    }

    public static void Document(String Path_Name) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + Path_Name);

    }

    public static ImageIcon getImage(String ImagePath, int Width, int Height) {
        ImageIcon Imge;
        if (ImagePath != null) {
            Imge = new ImageIcon(ImagePath);
        } else {
            Imge = new ImageIcon(defaultImage);
        }
        if (Width == 0) {
            Width = Imge.getIconWidth();
        }
        if (Height == 0) {
            Height = Imge.getIconHeight();
        }
        java.awt.Image img = Imge.getImage();
        java.awt.Image newimg = img.getScaledInstance(Width, Height, 25);
        ImageIcon i = new ImageIcon(newimg);
        return i;
    }

    public static ImageIcon getImage(ImageIcon Image, int Width, int Height) {
        if (Width == 0) {
            Width = Image.getIconWidth();
        }
        if (Height == 0) {
            Height = Image.getIconHeight();
        }
        java.awt.Image img = Image.getImage();
        java.awt.Image newimg = img.getScaledInstance(Width, Height, 25);
        ImageIcon i = new ImageIcon(newimg);
        return i;
    }

}
