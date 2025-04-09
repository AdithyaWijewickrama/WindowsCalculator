package com.calculate.number;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import programmer.WordSize;

public class CNumber {
//max 2^33219

    private String numberString;
    private NumberFormat numberFormat;
    private BigDecimal number;
    private WordSize wordSize = null;

    /**
     * <p>
     * CNumber is a class to handle with different number formats and radixes.
     * You have to give a number and the format of the given number. Number
     * Formats can be any of these:</p>
     * GROUPING_DECIMAL<br>
     * GROUPING_BINARY<br>
     * GROUPING_HEXA<br>
     * GROUPING_OCTAL<br>
     * NORMAL_BINARY<br>
     * NORMAL_HEXA<br>
     * NORMAL_OCTAL<br>
     * NORMAL_DECIMAL<br>
     *
     * <p>
     * Then you can do basic arithmetic operations and raising to a power. And
     * all operations available on BigDecimal class.
     *
     * @see com.calculate.number.NumberFormat
     * @param numberInFormat
     * @param format
     * @throws ParseException
     */
    public CNumber(String numberInFormat, NumberFormat format) throws Exception {
        this.numberString = numberInFormat;
        this.numberFormat = format;
        this.number = format.convRadixToDecimal(numberInFormat, null);
//        System.out.println("Decimal:" + number);
    }

    public CNumber(String numberInFormat, NumberFormat format, WordSize wordSize) throws Exception {
        this.numberString = numberInFormat;
        this.wordSize = wordSize;
        this.numberFormat = format;
        this.number = format.convRadixToDecimal(numberInFormat, wordSize);
//        System.out.println("Decimal:" + number);
    }

    public CNumber(double number) {
        this(new BigDecimal(number));
    }

    public CNumber(BigDecimal number) {
        this.numberString = number.toString();
        this.numberFormat = NumberFormat.NORMAL_DECIMAL;
        this.number = number;
    }

    public static CNumber parseNumber(double number) {
        return new CNumber(BigDecimal.valueOf(number));
    }

    public static String parseToFormat(String number, NumberFormat current, NumberFormat parseTo) throws Exception {
        return new CNumber(number, current).setNumberFormat(parseTo).getNumberString();
    }

    public String getNumberString() {
        return numberString;
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public CNumber setNumberFormat(NumberFormat numberFormat) throws Exception {
        numberString = numberFormat.convDecimalToRadix(number, wordSize);
//        if (this.numberFormat.formatType.equals(FormatType.NORMAL) && numberFormat.formatType.equals(FormatType.GROUPING)) {
//            numberString = numberFormat.groupNumber(numberString);
//        }
        this.numberFormat = numberFormat;
        return this;
    }

    public double doubleValue() {
        return number.doubleValue();
    }

    public java.lang.Number getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public void setNumber(CNumber number) {
        this.numberString = number.numberString;
        this.numberFormat = number.numberFormat;
        this.number = number.number;
    }

    public String getBinaryValue() {
        try {
            return numberFormat.convDecimalToRadix(number, wordSize);
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getHexaValue() {
        try {
            return numberFormat.convDecimalToRadix(number, wordSize);
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getDecimalValue() {
        return number.toString();
    }

    public String getOctalValue() {
        try {
            return numberFormat.convDecimalToRadix(number, wordSize);
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CNumber add(CNumber n) {
        try {
            return new CNumber(number.add(n.number)).setNumberFormat(numberFormat);
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CNumber substract(CNumber n) {
        try {
            return new CNumber(number.subtract(n.number)).setNumberFormat(numberFormat);
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CNumber multiply(CNumber n) {
        try {
            return new CNumber(number.multiply(n.number)).setNumberFormat(numberFormat);
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CNumber divide(CNumber n) {
        try {
            return new CNumber(number.divide(n.number, MathContext.DECIMAL128)).setNumberFormat(numberFormat);
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(Long.toUnsignedString(33333, 32));
        try {
//            System.out.println(new CNumber("-2215", NumberFormat.NORMAL_DECIMAL, WordSize.WORD));
//        System.out.println(NORMAL_BINARY.convRadixToDecimal(NORMAL_BINARY.convDecimalToRadix(new BigDecimal("-2215"), WordSize.WORD), WordSize.WORD));
//        System.out.println(NORMAL_BINARY.convRadixToDecimal("10111100001", WordSize.WORD).toString());
            CNumber n = new CNumber("9275", NumberFormat.NORMAL_DECIMAL);
            System.out.println("add:" + n.add(n).number);
            System.out.println("substract:" + n.substract(n).number);
            System.out.println("multiply:" + n.multiply(n).number);
            System.out.println("divide:" + n.divide(n).number);

            for (NumberFormat f : NumberFormat.values()) {
                System.out.println(f.toString());
                n.setNumberFormat(f);
                test(n);
            }
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void test(CNumber n) {
        System.out.println(n.getNumberString());
        System.out.println("======================");

    }

    public boolean equalsTo(CNumber number) {
        return number.number.compareTo(this.number) == 0;
    }
}
