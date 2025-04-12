package com.helper;

public class Trigonometry {

    public String angle = "deg";

    public Double calInverse(Double d, String opar) {
        Double ans = 0.0;
        switch (opar) {
            case "sin-1":
                ans = Math.asin(d);
                break;
            case "cos-1":
                ans = Math.acos(d);
                break;
            case "tan-1":
                ans = Math.atan(d);
                break;
            case "sinh-1":
                ans = Math.sinh(d);
                break;
            case "cosh-1":
                ans = Math.cosh(d);
                break;
            case "tanh-1":
                ans = Math.tanh(d);
                break;
            case "cosec-1":
                ans = 1 / Math.asin(d);
                break;
            case "sec-1":
                ans = 1 / Math.acos(d);
                break;
            case "cot-1":
                ans = 1 / Math.atan(d);
                break;
        }
//        return ans;
        return angle.equals("deg")?Math.toDegrees(ans):ans;
    }

    public Double cal(Double d, String Opar) {
        d=angle.equals("deg")?Math.toRadians(d):d;
        Double ans = 0.0;
        switch (Opar) {
            case "sin":
                ans = Math.sin(d);
                break;
            case "cos":
                ans = Math.cos(d);
                break;
            case "tan":
                ans = Math.tan(d);
                break;
            case "sinh":
                ans = Math.sinh(d);
                break;
            case "cosh":
                ans = Math.cosh(d);
                break;
            case "tanh":
                ans = Math.tanh(d);
                break;
            case "cosec":
                ans = 1 / Math.sin(d);
                break;
            case "sec":
                ans = 1 / Math.cos(d);
                break;
            case "cot":
                ans = 1 / Math.tan(d);
                break;

        }
        return ans;
    }

    public static String inverse(String s) {
        return s.contains("-1") ? s.replace("-1", "") : s.concat("-1");
    }
    public static void main(String[] args) {
        Trigonometry t = new Trigonometry();
        
        System.out.println(t.calInverse(1., "sin-1"));
    }
}
