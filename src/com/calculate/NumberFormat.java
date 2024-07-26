package com.calculate;

import static com.calculate.FormatType.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Arrays;

/**
 *
 * @author AW Developer
 */
enum FormatType{
    GROUPING,
    NORMAL;
}

public enum NumberFormat {
    GROUPING_DECIMAL(GROUPING,10, "###,###,###,###,###,###,###,###,###,###,###.##########################", ','),
    GROUPING_BINARY(GROUPING,2, "####,####,####,####,####,####,####,####,####,####,####,####,####,####,####", ' '),
    GROUPING_HEXA(GROUPING,16, "####,####,####,####", ' '),
    GROUPING_OCTAL(GROUPING,8, "###,###,###,###,###,###,###", ' '),
    NORMAL_BINARY(NORMAL,2, "#"),
    NORMAL_HEXA(NORMAL,16, "#"),
    NORMAL_OCTAL(NORMAL,8, "#"),
    NORMAL_DECIMAL(NORMAL,10, "#############.#########");
    public final DecimalFormat format;
    public final FormatType formatType;
    public final int radix;

    private NumberFormat(FormatType formatType,int radix, String format) {
        this.format = new DecimalFormat(format);
        this.radix = radix;
        this.formatType = formatType;
    }

    private NumberFormat(FormatType formatType,int radix, String format, char groupingSep) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(groupingSep);
        this.radix = radix;
        this.format = new DecimalFormat(format,symbols);
        this.formatType = null;
    }

    /**
     * Format number to given number format
     *
     * @param number
     * @return
     */
    public String formatNumber(String number) {
        if (radix < 10) {
            return format.format(Long.parseLong(number));
        } else if (radix == 10) {
            return format.format(Double.parseDouble(number));
        } else {
            number = number.replaceAll(Character.toString(format.getDecimalFormatSymbols().getGroupingSeparator()), "");
            String[] n = (formatType==NORMAL?NORMAL_BINARY:GROUPING_BINARY).format.format(Double.parseDouble("1".repeat(number.length()))).split("");
            System.out.println(Arrays.toString(n));
            int j = 0;
            for (int i = 0; i < n.length; i++) {
                if (n[i].equals("1")) {
                    n[i] = Character.toString(number.charAt(j++));
                }
            }
            number="";
            for (String chr : n) {
                number+=chr;
            }
            return number;
        }
    }

    /**
     *  Decimal value of given number
     * @param formatedNumber
     * @return
     * @throws ParseException
     */
    public java.lang.Number getDecimalValue(String formatedNumber) throws ParseException {
        if (radix < 10) {
            return Long.valueOf(format.parse(formatedNumber).toString(), radix);
        } else if (radix == 10) {
            return format.parse(formatedNumber);
        } else {
            return Long.valueOf(formatedNumber.replaceAll(Character.toString(format.getDecimalFormatSymbols().getGroupingSeparator()), ""), radix);
        }
    }
    
    public String getConverted(java.lang.Number number){
        char[] digits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String n="";
        double val=number.doubleValue();
        if(val==0||val<0||Math.abs(val%1)>0){
            return Double.toString(val);
        }
        while(val>0){
            n+=Character.toString(digits[(int)(val%radix)]);
            val=val/radix-((val%radix)/radix);
        }
        return new StringBuffer(n).reverse().toString();
    }
}
