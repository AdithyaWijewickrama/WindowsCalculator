package com.home;

import Convert.Angle;
import static Convert.Convert.angle;
import static Convert.Convert.area;
import static Convert.Convert.data;
import static Convert.Convert.length;
import static Convert.Convert.mass;
import static Convert.Convert.power;
import static Convert.Convert.presure;
import static Convert.Convert.speed;
import static Convert.Convert.temperature;
import static Convert.Convert.time;
import static Convert.Convert.volume;
import Convert.Data;
import Convert.Energy;
import Convert.Length;
import Convert.Mass;
import Convert.Power;
import Convert.Presure;
import Convert.Temperature;
import Convert.Time;
import Convert.Volume;
import java.awt.Font;
import javax.swing.JTextField;

public class Converts {

    public static Object getEnum(int type, String E) {
        int Idx = 0;
        String[] Model = getComboModel(type);
        for (int i = 0; i < Model.length; i++) {
            String string = Model[i];
            if (string.equals(E)) {
                Idx = i;
            }
        }
        return getEnumModel(type)[Idx];
    }

    public static Object[] getEnumModel(int type) {
        Object[] model;
        Object[][] m = null;
        switch (type) {
            case VOLUME:
                m = Volume.getAllVars();
                break;
            case ANGLE:
                m = Angle.getAllVars();
                break;
            case AREA:
                m = Length.getAllVars();
                break;
            case DATA:
                m = Data.getAllVars();
                break;
            case LENGTH:
                m = Length.getAllVars();
                break;
            case MASS:
                m = Mass.getAllVars();
                break;
            case TEMPERATURE:
                m = Temperature.getAllVars();
                break;
            case ENERGY:
                m = Energy.getAllVars();
                break;
            case TIME:
                m = Time.getAllVars();
                break;
            case PRESURE:
                m = Presure.getAllVars();
                break;
        }
        model = new Object[m.length];
        for (int i = 0; i < m.length; i++) {
            model[i] = m[i][1];
        }
        return model;
    }

    public static String[] getComboModel(int type) {
        String[] model;
        Object[][] m = null;
        switch (type) {
            case VOLUME:
                m = Volume.getAllVars();
                break;
            case ANGLE:
                m = Angle.getAllVars();
                break;
            case AREA:
                m = Length.getAllVars();
                break;
            case DATA:
                m = Data.getAllVars();
                break;
            case LENGTH:
                m = Length.getAllVars();
                break;
            case MASS:
                m = Mass.getAllVars();
                break;
            case TEMPERATURE:
                m = Temperature.getAllVars();
                break;
            case ENERGY:
                m = Energy.getAllVars();
                break;
            case TIME:
                m = Time.getAllVars();
                break;
            case PRESURE:
                m = Presure.getAllVars();
                break;
        }
        model = new String[m.length];
        for (int i = 0; i < m.length; i++) {
            model[i] = m[i][0].toString();
        }
        return model;
    }

    public static void getFocus(JTextField tf) {
        Font font = tf.getFont();
        tf.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
    }

    public static void forgetFocus(JTextField tf) {
        Font font = tf.getFont();
        tf.setFont(new Font(font.getName(), Font.PLAIN, font.getSize()));
    }

    public static double getConvertedValue(int type, double fVal, Object FU, Object SU, Object FU1, Object SU1) {
        if (type == SPEED) {
            return speed(0, (Length) FU, (Convert.Time) SU, (Length) FU1, (Convert.Time) SU1);
        } else {
            return power(fVal, (Energy) FU, (Convert.Time) SU, (Energy) FU1, (Convert.Time) SU1);
        }
    }

    public static double getConvertedValue(int type, double fVal, Object FU, Object SU) {
        double val = 0;
        switch (type) {
            case VOLUME:
                val = volume(fVal, (Volume) FU, (Volume) SU);
                break;
            case ANGLE:
                val = angle(fVal, (Angle) FU, (Angle) SU);
                break;
            case AREA:
                val = area(fVal, (Length) FU, (Length) SU);
                break;
            case DATA:
                val = data(fVal, (Data) FU, (Data) SU);
                break;
            case SPEED:
                break;
            case LENGTH:
                val = length(fVal, (Length) FU, (Length) SU);
                break;
            case MASS:
                val = mass(fVal, (Mass) FU, (Mass) SU);
                break;
            case TEMPERATURE:
                val = temperature(fVal, (Temperature) FU, (Temperature) SU);
                break;
            case ENERGY:
                break;
            case TIME:
                val = time(fVal, (Convert.Time) FU, (Convert.Time) SU);
                break;
            case POWER:
                val = power(fVal, (Power) FU, (Power) SU);
                break;
            case PRESURE:
                val = presure(fVal, (Presure) FU, (Presure) SU);
                break;
        }
        System.out.println("[Converting]" + fVal + " " + FU + "->" + val + " " + SU);
        return val;
    }

    public static final int VOLUME = 0;
    public static final int LENGTH = 1;
    public static final int MASS = 2;
    public static final int TEMPERATURE = 3;
    public static final int ENERGY = 4;
    public static final int AREA = 5;
    public static final int SPEED = 6;
    public static final int TIME = 7;
    public static final int POWER = 8;
    public static final int DATA = 9;
    public static final int PRESURE = 10;
    public static final int ANGLE = 11;

}
