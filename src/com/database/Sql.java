package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import net.proteanit.sql.DbUtils;

public class Sql {
public static Connection conn=DbConnect.connect(); 
    public static String getCovered(String str) {
        return "\"" + str + "\"";
    }

    public static String getCoveredt(String str) {
        return "`" + str + "`";
    }

    public static String getCoveredLike(String str) {
        return "\"%" + str + "%\"";
    }

    public static void Execute(String sql) throws SQLException {
        PreparedStatement pst1 = conn.prepareStatement(sql);
        pst1.execute();
    }

    public static ImageIcon getImage(String sql) {
        try {
            ResultSet r = ExecuteSQL(sql);
            if (r.next()) {
                return new ImageIcon(r.getBytes(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//
//    public static File getTempImage(String s) {
//        try {
//            ResultSet r = ExecuteSQL(sql);
//            File f = null;
//            if (r.next()) {
//                f = File.createTempFile("SMS TempFiles(Do not delete)", ".jpg");
//                f.deleteOnExit();
//                ImageIO.write(ImageWriter.getImage(new ImageIcon(r.getBytes(1))), "jpg", f);
//            }
//            return f;
//        } catch (SQLException | IOException ex) {
//            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public static void insertFile(PreparedStatement pst, File f)
//            throws FileNotFoundException, IOException, SQLException {
//        if (f.exists()) {
//            pst.setBytes(1, getBytes(f).toByteArray());
//            pst.execute();
//        }
//    }

    public static byte[] getFile(String sql) {
        try {
            ResultSet r = ExecuteSQL(sql);
            if (r.next()) {
                return r.getBytes(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<Object> getRow(String sql) {
        try {
            List<List<Object>> list = DbUtils.resultSetToNestedList(ExecuteSQL(sql));
            return list.isEmpty() ? null : list.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Object getValue(String sql) {
        List<List<Object>> list;
        try {
            list = DbUtils.resultSetToNestedList(ExecuteSQL(sql));
            return list.isEmpty() ? null : list.get(0).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getValueS(String sql) {
        Object val = getValue(sql);
        return val == null ? null : val.toString();
    }

    public static List<Object> getColumn(String sql, int columnIdx, Connection conn) {
        System.out.println(sql);
        System.out.println(sql);
        try {
            ResultSet rst = ExecuteSQL(sql);
            List<Object> col = new ArrayList<>();
            DbUtils.resultSetToNestedList(rst).forEach((ele) -> {
                col.add(((List)ele).get(columnIdx));
            });
            return col;
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet ExecuteSQL(String sql) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        return rs;
    }

}
