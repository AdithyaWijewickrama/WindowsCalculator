package com.calculate;

import static Programmer.Base.BIN;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public class CNumber {

    private String numberString;
    private NumberFormat numberFormat;
    private BigDecimal number;

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
     * @see com.calculate.NumberFormat
     * @param numberInFormat
     * @param format
     * @throws ParseException
     */
    public CNumber(String numberInFormat, NumberFormat format) throws Exception {
        this.numberString = numberInFormat;
        this.numberFormat = format;
        this.number = format.convRadixToDecimal(numberInFormat);
        System.out.println("Decimal:" + number);
    }

    public CNumber(double number){
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

    public CNumber setNumberFormat(NumberFormat numberFormat) {
        numberString = NumberFormat.convDecimalToRadix(number, numberFormat.radix);
        if (numberFormat.formatType.equals(FormatType.GROUPING)) {
            numberString = numberFormat.groupNumber(numberString);
        }
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
        return NumberFormat.convDecimalToRadix(number, 2);
    }

    public String getHexaValue() {
        return NumberFormat.convDecimalToRadix(number, 16);
    }

    public String getDecimalValue() {
        return number.toString();
    }

    public String getOctalValue() {
        return NumberFormat.convDecimalToRadix(number, 8);
    }

    public CNumber add(CNumber n) {
        return new CNumber(number.add(n.number)).setNumberFormat(numberFormat);
    }

    public CNumber substract(CNumber n) {
        return new CNumber(number.subtract(n.number)).setNumberFormat(numberFormat);
    }

    public CNumber multiply(CNumber n) {
        return new CNumber(number.multiply(n.number)).setNumberFormat(numberFormat);
    }

    public CNumber divide(CNumber n) {
        return new CNumber(number.divide(n.number, MathContext.DECIMAL128)).setNumberFormat(numberFormat);
    }

    public static void main(String[] args) throws ParseException {
        try {
            CNumber n = new CNumber("1 0000", NumberFormat.GROUPING_BINARY);
            for (NumberFormat f : NumberFormat.values()) {
                n.setNumberFormat(f);
                test(n);
            }
        } catch (Exception ex) {
            Logger.getLogger(CNumber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void test(CNumber n) {
        System.out.println("======================");
        System.out.println(n.getNumberString());
    }

    public boolean equalsTo(CNumber number) {
        return number.number.compareTo(this.number) == 0;
    }
}
