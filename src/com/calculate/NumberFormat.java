package com.calculate;

import Programmer.Base;
import static com.calculate.FormatType.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author AW Developer
 */
enum FormatType {
    GROUPING,
    NORMAL;
}

public enum NumberFormat {
    GROUPING_DECIMAL(GROUPING, 10, "###,###,###,###,###,###,###,###,###,###,###.##########################", ','),
    GROUPING_BINARY(GROUPING, 2, "####,####,####,####,####,####,####,####,####,####,####,####,####,####,####", ' '),
    GROUPING_HEXA(GROUPING, 16, "####,####,####,####", ' '),
    GROUPING_OCTAL(GROUPING, 8, "###,###,###,###,###,###,###", ' '),
    NORMAL_BINARY(NORMAL, 2, "#"),
    NORMAL_HEXA(NORMAL, 16, "#"),
    NORMAL_OCTAL(NORMAL, 8, "#"),
    NORMAL_DECIMAL(NORMAL, 10, "#############.#########");
    public final DecimalFormat format;
    public final FormatType formatType;
    public final int radix;
    public static final ArrayList<String> DIGITS = new ArrayList(Arrays.asList("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"));

    private NumberFormat(FormatType formatType, int radix, String format) {
        this.format = new DecimalFormat(format);
        this.radix = radix;
        this.formatType = formatType;
    }

    private NumberFormat(FormatType formatType, int radix, String format, char groupingSep) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(groupingSep);
        this.radix = radix;
        this.format = new DecimalFormat(format, symbols);
        this.formatType = formatType;
    }

    /**
     * Group number to given number format
     * 
     * @param unformatedNumber
     * @return Grouped number or return given string if format type is grouping
     */
    public String groupNumber(String unformatedNumber) {
        if (radix < 10) {
            return format.format(new BigDecimal(unformatedNumber));
        } else if (radix == 10) {
            return format.format(new BigDecimal(unformatedNumber));
        } else {
            String[] n = GROUPING_BINARY.format.format(Double.parseDouble("1".repeat(unformatedNumber.length()))).split("");
            int j = 0;
            for (int i = 0; i < n.length; i++) {
                if (n[i].equals("1")) {
                    n[i] = Character.toString(unformatedNumber.charAt(j++));
                }
            }
            unformatedNumber = "";
            for (String chr : n) {
                unformatedNumber += chr;
            }
            return unformatedNumber;
        }
    }

    /**
     * Ungroup number to normal format
     * 
     * @param formatedNumber
     * @return Ungroup number or return given string if format type is normal
     */
    public String ungroupNumber(String formatedNumber){
        return formatedNumber.replaceAll(Character.toString(format.getDecimalFormatSymbols().getGroupingSeparator()), "");
    }
    
    /**
     * Decimal value of given number
     *
     * @param formatedNumber
     * @return
     * @throws ParseException
     */
    public BigDecimal convRadixToDecimal(String formatedNumber) throws Exception {
        String parseNumber = ungroupNumber(formatedNumber);
        System.out.println("Parsed Number:"+parseNumber);
        if (radix == 10) {
            return new BigDecimal(parseNumber);
        }
        BigDecimal radixBig = BigDecimal.valueOf(radix);
        BigDecimal number = new BigDecimal(0);
        int pow = 0;
        for (int i = parseNumber.length() - 1; i >= 0; i--) {
            String dgt=Character.toString(parseNumber.charAt(i));
            if(DIGITS.indexOf(dgt)>=radix){
                throw new NumberFormatException("For input digit \""+dgt+"\" under radix "+radix);
            }
            number=number.add(radixBig.pow(pow++).multiply(new BigDecimal(DIGITS.indexOf(dgt))));
        }
        return number;
    }

    public static String convDecimalToRadix(BigDecimal number, int radix) {
        BigDecimal radixBig = BigDecimal.valueOf(radix);
        String n = "";
        if (number.equals(0) || number.compareTo(BigDecimal.ZERO) == -1 || number.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0) {
            return number.toString();
        }
        while (number.compareTo(BigDecimal.ZERO) == 1) {
            n += DIGITS.get(number.remainder(radixBig).intValue());
            number = number.divide(radixBig, MathContext.DECIMAL64).subtract(number.remainder(radixBig).divide(radixBig, MathContext.DECIMAL64));
        }
        return new StringBuffer(n).reverse().toString();
    }

    public static NumberFormat getGroupingNumberFormat(Base b) {
        switch (b) {
            case BIN:
                return GROUPING_BINARY;
            case DEC:
                return GROUPING_DECIMAL;
            case HEX:
                return GROUPING_HEXA;
            case OCT:
                return GROUPING_OCTAL;
            default:
                throw new AssertionError();
        }
    }

    public static NumberFormat getNormalNumberFormat(Base b) {
        switch (b) {
            case BIN:
                return NORMAL_BINARY;
            case DEC:
                return NORMAL_DECIMAL;
            case HEX:
                return NORMAL_HEXA;
            case OCT:
                return NORMAL_OCTAL;
            default:
                throw new AssertionError();
        }
    }
}
