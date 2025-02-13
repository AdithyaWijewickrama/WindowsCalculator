package com.calculate;

import Programmer.Base;
import static com.calculate.FormatType.*;
import com.calculator.programmer.wordSize.WordSize;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static final ArrayList<String> DIGITS = new ArrayList(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"));

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
//        System.out.println(this.toString() + "\"unformated:" + unformatedNumber + "\"");
        if (unformatedNumber.equals("0")) {
            return "0";
        }
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
    public String ungroupNumber(String formatedNumber) {
//        System.out.println("sep:'"+format.getDecimalFormatSymbols().getGroupingSeparator()+"'");
//        System.out.println("num:"+formatedNumber);
        return formatedNumber.replace(Character.toString(format.getDecimalFormatSymbols().getGroupingSeparator()), "");
    }

    /**
     * Decimal value of given number
     *
     * @param formatedNumber
     * @param wordSize
     * @return
     * @throws ParseException
     */
    public BigDecimal convRadixToDecimal(String formatedNumber, WordSize wordSize) throws Exception {
        String parseNumber = ungroupNumber(formatedNumber);
//        System.out.println("Parsed Number:" + parseNumber);
        if (radix == 10) {
            if (wordSize == null) {
                return new BigDecimal(parseNumber);
            } else {
                return NORMAL_BINARY.convRadixToDecimal(NORMAL_BINARY.convDecimalToRadix(new BigDecimal(parseNumber), wordSize), wordSize);
            }
        }
        BigDecimal radixBig = BigDecimal.valueOf(radix);
        BigDecimal number = new BigDecimal(0);
        if (wordSize == null) {
            int pow = 0;
            for (int i = parseNumber.length() - 1; i >= 0; i--) {
                String dgt = Character.toString(parseNumber.charAt(i));
                if (DIGITS.indexOf(dgt) >= radix) {
                    throw new NumberFormatException("For input digit \"" + dgt + "\" under radix " + radix);
                }
                number = number.add(radixBig.pow(pow++).multiply(new BigDecimal(DIGITS.indexOf(dgt))));
            }
        } else {
            BigDecimal dec = convRadixToDecimal(parseNumber, null);
//            System.out.println(dec);
            String bin = radix == 2 ? parseNumber : NORMAL_BINARY.convDecimalToRadix(dec, null);
            if (bin.length() < wordSize.BITS) {
                return dec;
            }
            char[] trimedBin = new char[wordSize.BITS];
            bin.getChars(bin.length() - wordSize.BITS, bin.length(), trimedBin, 0);
//            System.out.println(trimedBin);
            number = NORMAL_BINARY.convRadixToDecimal(String.valueOf(trimedBin), null);
            if (trimedBin[0] == '1') {
                number = number.subtract(BigDecimal.ONE);
//                System.out.println(number);
                bin = NORMAL_BINARY.convDecimalToRadix(number, null);
                bin = "0".repeat(wordSize.BITS - bin.length()) + bin;
//                System.out.println(bin);
                for (int i = 0; i < wordSize.BITS; i++) {
                    trimedBin[i] = bin.charAt(i) == '1' ? '0' : '1';
                }
                number = NORMAL_BINARY.convRadixToDecimal(String.valueOf(trimedBin), null).multiply(BigDecimal.valueOf(-1));
            }
//            System.out.println(trimedBin);
        }
        return number;
    }

    public String convDecimalToRadix(BigDecimal number, WordSize wordSize) throws Exception {
        if (number.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }
        String num = "";
        if (radix == 10) {
            num = number.toString();
        } else if (wordSize == null) {
            if (number.compareTo(BigDecimal.ZERO) == -1) {
                String binary = NORMAL_BINARY.convDecimalToRadix(number.abs(), null);
                for (WordSize ws : WordSize.values()) {
                    if (ws.BITS >= binary.length()) {
                        wordSize = ws;
                        break;
                    }
                }
                if (wordSize == null) {
                    throw new IllegalArgumentException("Can not parse negetive decimals to base '" + radix + "' numbers when word size is null");
                } else {
                    num = convDecimalToRadix(number, wordSize);
                }
            } else {
                BigDecimal radixBig = BigDecimal.valueOf(radix);
                String n = "";
                if (number.equals(0) || number.compareTo(BigDecimal.ZERO) == -1 || number.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0) {
                    num = number.toString();
                } else {
                    while (number.compareTo(BigDecimal.ZERO) == 1) {
                        n += DIGITS.get(number.remainder(radixBig).intValue());
                        number = number.divide(radixBig, MathContext.DECIMAL64).subtract(number.remainder(radixBig).divide(radixBig, MathContext.DECIMAL64));
                    }
                    num = new StringBuffer(n).reverse().toString();
                }
            }
        } else if (wordSize.compare(number.longValue()) == 0) {
            if (number.compareTo(BigDecimal.ZERO) == -1) {
                number = number.abs();
                String binary = NORMAL_BINARY.convDecimalToRadix(number, null);
                char[] bin = ("0".repeat(wordSize.BITS - binary.length()) + binary).toCharArray();
                for (int i = 0; i < wordSize.BITS; i++) {
                    bin[i] = bin[i] == '1' ? '0' : '1';
                }
                binary = addBin(String.valueOf(bin), "1");
                num = binary.substring(binary.length() - wordSize.BITS, binary.length());
//                number = NORMAL_BINARY.convRadixToDecimal(String.valueOf(bin), null).add(BigDecimal.ONE);
//                num= NORMAL_BINARY.convDecimalToRadix(number, null);
            } else {
                num = convDecimalToRadix(number, null);
            }
        } else {
            if (number.compareTo(BigDecimal.ZERO) == -1) {
                String binary = NORMAL_BINARY.convDecimalToRadix(number, null);
                num = NORMAL_BINARY.convDecimalToRadix(number, null).substring(binary.length() - wordSize.BITS, binary.length());
            } else {
                String binary = NORMAL_BINARY.convDecimalToRadix(number, null);
                num = convDecimalToRadix(NORMAL_BINARY.convRadixToDecimal(binary.substring(binary.length() - wordSize.BITS, binary.length()), null), null);
            }
        }
        if (formatType.equals(FormatType.GROUPING)) {
            return groupNumber(num);
        } else {
            return num;
        }
    }

    public static String addBin(String bin1, String bin2) {
        char[] b1 = new StringBuffer(bin1).reverse().toString().toCharArray();
        char[] b2 = new StringBuffer(bin2).reverse().toString().toCharArray();
        int r = 0;
        String addBin = "";
        for (int i = 0; i < Math.max(bin1.length(), bin2.length()); i++) {
            boolean b11 = b1.length > i ? b1[i] == '1' : false;
            boolean b22 = b2.length > i ? b2[i] == '1' : false;
            r += ((b11 ? 1 : 0) + (b22 ? 1 : 0));
            addBin += "" + r % 2;
            r = (r - (r % 2)) / 2;
        }
        if (r != 0) {
            addBin += "" + r % 2;
        }
        return new StringBuffer(addBin).reverse().toString();
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
