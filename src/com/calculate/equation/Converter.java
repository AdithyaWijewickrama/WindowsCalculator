package com.calculate.equation;

import Convert.Angle;
import Convert.Convert;
import Convert.Data;
import Convert.Energy;
import Convert.Length;
import Convert.Mass;
import Convert.Power;
import Convert.Presure;
import Convert.Temperature;
import Convert.Time;
import Convert.Volume;

/**
 *
 * @author AW Developer
 */
public class Converter extends {

    public Converter() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         * 4: return
         *  */
        // </editor-fold>
    }

    public static double speed(double val, Length flen, Time ftime, Length slen, Time stime) {
       return Convert.speed(val, flen, ftime, slen, stime);
    }

    public static double length(double val, Length flen, Length slen) {
        return Convert.length(val, flen, slen);
    }

    public static double angle(double val, Angle fAng, Angle sAng) {
        return Convert.an4
    }

    public static double temperature(double val, Temperature fTemp, Temperature sTemp) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_2
         * 1: invokevirtual #5                  // Method Convert/Temperature.getStr:()Ljava/lang/String;
         * 4: astore        4
         * 6: aload_3
         * 7: invokevirtual #5                  // Method Convert/Temperature.getStr:()Ljava/lang/String;
         * 10: astore        5
         * 12: getstatic     #6                  // Field Convert/Temperature.C:LConvert/Temperature;
         * 15: invokevirtual #5                  // Method Convert/Temperature.getStr:()Ljava/lang/String;
         * 18: astore        6
         * 20: getstatic     #7                  // Field Convert/Temperature.F:LConvert/Temperature;
         * 23: invokevirtual #5                  // Method Convert/Temperature.getStr:()Ljava/lang/String;
         * 26: astore        7
         * 28: getstatic     #8                  // Field Convert/Temperature.K:LConvert/Temperature;
         * 31: invokevirtual #5                  // Method Convert/Temperature.getStr:()Ljava/lang/String;
         * 34: astore        8
         * 36: aload         4
         * 38: aload         6
         * 40: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 43: ifeq          72
         * 46: aload         5
         * 48: aload         7
         * 50: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 53: ifeq          72
         * 56: dload_0
         * 57: ldc2_w        #10                 // double 9.0d
         * 60: dmul
         * 61: ldc2_w        #12                 // double 5.0d
         * 64: ddiv
         * 65: ldc2_w        #14                 // double 32.0d
         * 68: dadd
         * 69: goto          244
         * 72: aload         4
         * 74: aload         6
         * 76: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 79: ifeq          100
         * 82: aload         5
         * 84: aload         8
         * 86: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 89: ifeq          100
         * 92: dload_0
         * 93: ldc2_w        #16                 // double 273.15d
         * 96: dadd
         * 97: goto          244
         * 100: aload         4
         * 102: aload         7
         * 104: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 107: ifeq          136
         * 110: aload         5
         * 112: aload         6
         * 114: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 117: ifeq          136
         * 120: dload_0
         * 121: ldc2_w        #14                 // double 32.0d
         * 124: dsub
         * 125: ldc2_w        #12                 // double 5.0d
         * 128: dmul
         * 129: ldc2_w        #10                 // double 9.0d
         * 132: ddiv
         * 133: goto          244
         * 136: aload         4
         * 138: aload         7
         * 140: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 143: ifeq          178
         * 146: aload         5
         * 148: aload         8
         * 150: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 153: ifeq          178
         * 156: dload_0
         * 157: getstatic     #7                  // Field Convert/Temperature.F:LConvert/Temperature;
         * 160: getstatic     #6                  // Field Convert/Temperature.C:LConvert/Temperature;
         * 163: invokestatic  #18                 // Method temperature:(DLConvert/Temperature;LConvert/Temperature;)D
         * 166: getstatic     #6                  // Field Convert/Temperature.C:LConvert/Temperature;
         * 169: getstatic     #8                  // Field Convert/Temperature.K:LConvert/Temperature;
         * 172: invokestatic  #18                 // Method temperature:(DLConvert/Temperature;LConvert/Temperature;)D
         * 175: goto          244
         * 178: aload         4
         * 180: aload         8
         * 182: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 185: ifeq          206
         * 188: aload         5
         * 190: aload         6
         * 192: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 195: ifeq          206
         * 198: dload_0
         * 199: ldc2_w        #16                 // double 273.15d
         * 202: dsub
         * 203: goto          244
         * 206: aload         4
         * 208: aload         8
         * 210: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 213: ifeq          243
         * 216: aload         5
         * 218: aload         7
         * 220: invokevirtual #9                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
         * 223: ifeq          243
         * 226: dload_0
         * 227: ldc2_w        #16                 // double 273.15d
         * 230: dsub
         * 231: getstatic     #6                  // Field Convert/Temperature.C:LConvert/Temperature;
         * 234: getstatic     #7                  // Field Convert/Temperature.F:LConvert/Temperature;
         * 237: invokestatic  #18                 // Method temperature:(DLConvert/Temperature;LConvert/Temperature;)D
         * 240: goto          244
         * 243: dload_0
         * 244: dstore_0
         * 245: dload_0
         * 246: dreturn
         *  */
        // </editor-fold>
    }

    public static double time(double val, Time fTime, Time sTime) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_2
         * 2: invokevirtual #3                  // Method Convert/Time.getVal:()D
         * 5: aload_3
         * 6: invokevirtual #3                  // Method Convert/Time.getVal:()D
         * 9: ddiv
         * 10: dmul
         * 11: dreturn
         *  */
        // </editor-fold>
    }

    public static double data(double val, Data fD, Data sD) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_2
         * 2: invokevirtual #19                 // Method Convert/Data.getVal:()D
         * 5: aload_3
         * 6: invokevirtual #19                 // Method Convert/Data.getVal:()D
         * 9: ddiv
         * 10: dmul
         * 11: dreturn
         *  */
        // </editor-fold>
    }

    public static double energy(double val, Energy fE, Energy sE) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_2
         * 2: invokevirtual #20                 // Method Convert/Energy.getVal:()D
         * 5: aload_3
         * 6: invokevirtual #20                 // Method Convert/Energy.getVal:()D
         * 9: ddiv
         * 10: dmul
         * 11: dreturn
         *  */
        // </editor-fold>
    }

    public static double power(double val, Power fP, Power sP) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_2
         * 2: invokevirtual #21                 // Method Convert/Power.getVal:()D
         * 5: aload_3
         * 6: invokevirtual #21                 // Method Convert/Power.getVal:()D
         * 9: ddiv
         * 10: dmul
         * 11: dreturn
         *  */
        // </editor-fold>
    }

    public static double power(double val, Energy fE, Time fT, Energy sE, Time sT) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_2
         * 2: invokevirtual #20                 // Method Convert/Energy.getVal:()D
         * 5: aload_3
         * 6: invokevirtual #3                  // Method Convert/Time.getVal:()D
         * 9: ddiv
         * 10: dmul
         * 11: aload         4
         * 13: invokevirtual #20                 // Method Convert/Energy.getVal:()D
         * 16: aload         5
         * 18: invokevirtual #3                  // Method Convert/Time.getVal:()D
         * 21: ddiv
         * 22: ddiv
         * 23: dreturn
         *  */
        // </editor-fold>
    }

    public static double volume(double val, Volume fV, Volume sV) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_3
         * 2: invokevirtual #22                 // Method Convert/Volume.getVal:()D
         * 5: aload_2
         * 6: invokevirtual #22                 // Method Convert/Volume.getVal:()D
         * 9: ddiv
         * 10: dmul
         * 11: dreturn
         *  */
        // </editor-fold>
    }

    public static double area(double val, Length flen, Length slen) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_2
         * 2: getfield      #23                 // Field Convert/Length.LEN:D
         * 5: ldc2_w        #24                 // double 2.0d
         * 8: invokestatic  #26                 // Method Programmer/Decimal.pow:(DD)D
         * 11: aload_3
         * 12: getfield      #23                 // Field Convert/Length.LEN:D
         * 15: ldc2_w        #24                 // double 2.0d
         * 18: invokestatic  #26                 // Method Programmer/Decimal.pow:(DD)D
         * 21: ddiv
         * 22: dmul
         * 23: dreturn
         *  */
        // </editor-fold>
    }

    public static double presure(double val, Presure fP, Presure sP) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_2
         * 2: invokevirtual #27                 // Method Convert/Presure.getPRES:()D
         * 5: aload_3
         * 6: invokevirtual #27                 // Method Convert/Presure.getPRES:()D
         * 9: ddiv
         * 10: dmul
         * 11: dreturn
         *  */
        // </editor-fold>
    }

    public static double mass(double val, Mass fP, Mass sP) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_0
         * 1: aload_2
         * 2: invokevirtual #28                 // Method Convert/Mass.getVal:()D
         * 5: aload_3
         * 6: invokevirtual #28                 // Method Convert/Mass.getVal:()D
         * 9: ddiv
         * 10: dmul
         * 11: dreturn
         *  */
        // </editor-fold>
    }
}
