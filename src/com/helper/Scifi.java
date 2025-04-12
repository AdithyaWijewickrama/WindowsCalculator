package com.helper;

import java.math.BigDecimal;

/**
 *
 * @author adith
 */
public class Scifi {
    
    public static String Exact(BigDecimal val) {
        String extVal=val.toString();
        if (val.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
            return val.toBigInteger().toString();
        }
        return extVal;
    }

    public static String getSciFiNotation(BigDecimal d) {
        String s = "0.e+0";
        if(d.equals(BigDecimal.ZERO))
            return s;
        if((d+"").contains("E")){
            return (d+"").toLowerCase();
        }
        if (d.compareTo(BigDecimal.ONE)==1) {
            BigDecimal l0 = d.remainder(BigDecimal.ONE);
            BigDecimal g0 = d.min(l0);
            s = Exact(d).replace(".", "").replaceFirst(Exact(g0).charAt(0) + "", Exact(g0).charAt(0) + ".") + "e+" + (Exact(g0).length() - 1);
        } else{
            String l = (d.remainder(BigDecimal.ONE) + "").replace(".", "");
            for (char c : l.toCharArray()) {
                if ((c + "").equals("0")) {
                    l = l.replace("0", "");
                }
            }
            s = l.charAt(0) + "." + l.substring(1) + "e-" + (l.length() - 1);
        }
        return s;
    }
    public static void main(String[] args) {
        System.out.println(Double.valueOf(2.19843289324284e+3));
    }
}
