package com.helper;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Commons {

    public static Date date = Calendar.getInstance().getTime();

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

    public static void showMsg(String e) {
        JOptionPane.showMessageDialog(null, e);
    }

    public static void copyPasteFile(String CurrentPath, String NewPath) {
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

    public static String currentDate() {
        return String.format("%04d-%02d-%02d", date.getYear() + 1900, date.getMonth() + 1, date.getDate());
    }

    public static String currentTime() {
        return String.format("%02d:%02d:%02d", date.getHours(), date.getMinutes(), date.getSeconds());
    }

    public static void openDocument(String Path_Name) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + Path_Name);

    }

    public static ImageIcon getImage(String imagePath, int w, int h) {
        ImageIcon Imge;
        if (imagePath != null) {
            Imge = new ImageIcon(imagePath);
        } else {
            throw new RuntimeException("Image path is null");
        }
        if (w == 0) {
            w = Imge.getIconWidth();
        }
        if (h == 0) {
            h = Imge.getIconHeight();
        }
        java.awt.Image img = Imge.getImage();
        java.awt.Image newimg = img.getScaledInstance(w, h, 25);
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
