package com.calculator.converter.convert;

public enum Units{
    deci(.1), centi(.01), mili(0.1E-3), micro(0.1E-6), nano(0.1E-9), piko(0.1E-9), femto(0.1E-12), ato(0.1E-15), deka(0.1E-3), hect(0.1E-3),
    kilo(1E3), Mega(1E6), Giga(1E9), Tera(1E12), Peta(1E15), Exa(1E18), Zeta(1E21), Yotta(1E24), kibi(1E27), mebi(1E30), gibi(1E33), tebi(1E36), pebi(1E39), exbi(1E42),
    zebi(1E45), yobi(1E3);
    public final double UNIT;

    private Units(double unit) {
        UNIT = unit;
    }

    public double getVal() {
        return UNIT;
    }

    public static Object[][] getAllUnits() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: invokestatic  #8                  // Method values:()[LConvert/Units;
         * 3: astore_0
         * 4: aload_0
         * 5: arraylength
         * 6: iconst_2
         * 7: multianewarray #9,  2             // class "[[Ljava/lang/Object;"
         * 11: astore_1
         * 12: iconst_0
         * 13: istore_2
         * 14: invokestatic  #8                  // Method values:()[LConvert/Units;
         * 17: astore_3
         * 18: aload_3
         * 19: arraylength
         * 20: istore        4
         * 22: iconst_0
         * 23: istore        5
         * 25: iload         5
         * 27: iload         4
         * 29: if_icmpge     67
         * 32: aload_3
         * 33: iload         5
         * 35: aaload
         * 36: astore        6
         * 38: aload_1
         * 39: iload_2
         * 40: aaload
         * 41: iconst_0
         * 42: aload         6
         * 44: aastore
         * 45: aload_1
         * 46: iload_2
         * 47: aaload
         * 48: iconst_1
         * 49: aload         6
         * 51: invokevirtual #10                 // Method getVal:()D
         * 54: invokestatic  #11                 // Method java/lang/Double.valueOf:(D)Ljava/lang/Double;
         * 57: aastore
         * 58: iinc          2, 1
         * 61: iinc          5, 1
         * 64: goto          25
         * 67: aload_1
         * 68: areturn
         *  */
        // </editor-fold>
    }
}
