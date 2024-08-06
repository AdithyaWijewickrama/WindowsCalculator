package com.calculate;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public class Number extends java.lang.Number {

    private String numberString;
    private NumberFormat numberFormat;
    private java.lang.Number number;

    public Number(String numberString, NumberFormat format) throws ParseException {
        this.numberString = numberString;
        this.numberFormat = format;
        number = format.getDecimalValue(numberString);
    }

    public Number(Double number, NumberFormat format) throws ParseException {
        this.numberString = format.formatNumber(number.toString());
        this.numberFormat = format;
        this.number = number;
    }

    public Number(Double number) {
        this.numberString = number.toString();
        this.numberFormat = NumberFormat.NORMAL_DECIMAL;
        this.number = number;
    }

    public static Number parseNumber(double number) {
        return new Number(number);
    }

    public static String parseToFormat(String number, NumberFormat current, NumberFormat parseTo) throws ParseException {
        return new Number(number, current).setNumberFormat(parseTo).getNumberString();
    }

    public String getNumberString() {
        return numberString;
    }

    public void setNumberString(String numberString) throws ParseException {
        this.numberString = numberString;
        this.number = numberFormat.format.parse(numberString);
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public Number setNumberFormat(NumberFormat numberFormat) {
        numberString = numberFormat.formatNumber(numberFormat.getConverted(number));
        this.numberFormat = numberFormat;
        return this;
    }

    public java.lang.Number getNumber() {
        return number;
    }

    public void setNumber(java.lang.Number number) {
        this.number = number;
    }

    public String getBinaryValue() {
        return Long.toBinaryString((long) number);
    }

    public String getHexaValue() {
        return Long.toHexString((long) number);
    }

    public Double getDecimalValue() {
        return number.doubleValue();
    }

    public String getOctalValue() {
        return Long.toOctalString((long) number);
    }

    @Override
    public int intValue() {
        return number.intValue();
    }

    @Override
    public long longValue() {
        return number.longValue();
    }

    @Override
    public float floatValue() {
        return number.longValue();
    }

    @Override
    public double doubleValue() {
        return number.doubleValue();
    }
    
    public void setValue(double value){
        number=value;
    }

    public Number add(Number n) {
        try {
            return new Number(number.doubleValue() + n.doubleValue(), numberFormat);
        } catch (ParseException ex) {
            Logger.getLogger(Number.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Number substract(Number n) {
        try {
            return new Number(number.doubleValue() - n.doubleValue(), numberFormat);
        } catch (ParseException ex) {
            Logger.getLogger(Number.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Number multiply(Number n) {
        try {
            return new Number(number.doubleValue() * n.doubleValue(), numberFormat);
        } catch (ParseException ex) {
            Logger.getLogger(Number.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Number divide(Number n) {
        try {
            return new Number(number.doubleValue() / n.doubleValue(), numberFormat);
        } catch (ParseException ex) {
            Logger.getLogger(Number.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws ParseException {
        Number n;
//        n = new Number("29263773.9889", NumberFormat.NORMAL_DECIMAL);
//        test(n);
//        n = new Number("1111 1111 1011", NumberFormat.GROUPING_BINARY);
//        test(n);
//        n = new Number("4 377 373", NumberFormat.GROUPING_OCTAL);
//        test(n);
//        n = new Number("5AB 7F52", NumberFormat.GROUPING_HEXA);
//        test(n);
//        n = new Number("-29263773", NumberFormat.NORMAL_DECIMAL);
//        test(n);
//        n = new Number("111111111011", NumberFormat.NORMAL_BINARY);
//        test(n);
//        n = new Number("4377373", NumberFormat.NORMAL_OCTAL);
//        test(n);
//        n = new Number("5AB7F52", NumberFormat.NORMAL_HEXA);
//        test(n);
        n = new Number("29263773.898", NumberFormat.NORMAL_DECIMAL).setNumberFormat(NumberFormat.GROUPING_DECIMAL);
        test(n);
//        n = new Number("111111111011", NumberFormat.NORMAL_BINARY).setNumberFormat(NumberFormat.GROUPING_BINARY);
//        test(n);
//        n = new Number("4377373", NumberFormat.NORMAL_OCTAL).setNumberFormat(NumberFormat.GROUPING_OCTAL);
//        test(n);
//        n = new Number("5AB7F52", NumberFormat.NORMAL_HEXA).setNumberFormat(NumberFormat.GROUPING_HEXA);
//        test(n);
//        System.out.println(parseNumber(-1.).getDecimalValue());
    }

    public static void test(Number n) {
        System.out.println("======================");
        System.out.println(n.getNumberString());

    }

    public boolean equalsTo(Number number) {
        return number.doubleValue() == this.number.doubleValue();
    }
}
