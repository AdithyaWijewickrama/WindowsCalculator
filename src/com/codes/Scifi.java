/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codes;

import static com.home.FrontFrame.Exact;

/**
 *
 * @author adith
 */
public class Scifi {

    public static String getSciFiNotation(double d) {
        String s = "0.e+0";
        if(d==0)
            return s;
        if((d+"").contains("E")){
            return (d+"").toLowerCase();
        }
        if (d > 1) {
            double l0 = d % 1;
            double g0 = d - l0;
            s = Exact(d).replace(".", "").replaceFirst(Exact(g0).charAt(0) + "", Exact(g0).charAt(0) + ".") + "e+" + (Exact(g0).length() - 1);
        } else if (d < 1) {
            String l = (d % 1 + "").replace(".", "");
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
