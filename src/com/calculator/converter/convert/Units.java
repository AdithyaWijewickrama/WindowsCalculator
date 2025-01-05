package com.calculator.converter.convert;

import java.util.Dictionary;
import java.util.Hashtable;

public enum Units{
    deci(.1), centi(.01), mili(1E-3), micro(1E-6), nano(1E-9), piko(1E-12), femto(1E-15), ato(1E-18), deka(1E-3), hect(1E-3),
    kilo(1E3), Mega(1E6), Giga(1E9), Tera(1E12), Peta(1E15), Exa(1E18), Zeta(1E21), Yotta(1E24), kibi(1E27), mebi(1E30), gibi(1E33), tebi(1E36), pebi(1E39), exbi(1E42),
    zebi(1E45), yobi(1E48);
    public final double UNIT;

    private Units(double unit) {
        UNIT = unit;
    }

    public double getVal() {
        return UNIT;
    }

    public static Dictionary getAllUnits() {
        Dictionary dic=new Hashtable();
        for(Units u:Units.values()){
            dic.put(u.toString(), u.getVal());
        }
        return dic;
    }
}
