package com.calculate.equation;

import Convert.Convert;
import Convert.Angle;
import com.Tokenizing.Token;
import com.calculate.Number;
import static com.Tokenizing.Token.ACOS;
import static com.Tokenizing.Token.ACOSH;
import static com.Tokenizing.Token.ACOT;
import static com.Tokenizing.Token.ACOTH;
import static com.Tokenizing.Token.ACSC;
import static com.Tokenizing.Token.ACSCH;
import static com.Tokenizing.Token.ASEC;
import static com.Tokenizing.Token.ASECH;
import static com.Tokenizing.Token.ASIN;
import static com.Tokenizing.Token.ASINH;
import static com.Tokenizing.Token.ATAN;
import static com.Tokenizing.Token.ATANH;
import static com.Tokenizing.Token.COS;
import static com.Tokenizing.Token.COSH;
import static com.Tokenizing.Token.COT;
import static com.Tokenizing.Token.COTH;
import static com.Tokenizing.Token.CSC;
import static com.Tokenizing.Token.CSCH;
import static com.Tokenizing.Token.SEC;
import static com.Tokenizing.Token.SECH;
import static com.Tokenizing.Token.SIN;
import static com.Tokenizing.Token.SINH;
import static com.Tokenizing.Token.TAN;
import static com.Tokenizing.Token.TANH;
import static com.calculate.equation.Triaganometry.InverseHyperbolicFunctions.acosh;
import static com.calculate.equation.Triaganometry.InverseHyperbolicFunctions.asinh;
import static org.apache.commons.math3.util.FastMath.atanh;

/**
 *
 * @author AW Developer
 */
public final class Triaganometry extends Function_ {

    private Angle angleType;

    public Triaganometry(Number value, Token token, Angle angleType) {
        super(token, value);
        setAngleType(angleType);
    }

    public void setAngleType(Angle angleType) {
        this.angleType = angleType;
        setValue1(Number.parseNumber(Convert.angle(value1.doubleValue(), angleType, Angle.radians)));
        System.out.println(value1.doubleValue());
    }

    public Angle getAngleType() {
        return angleType;
    }

    @Override
    public Number doTheMath() {
        if (function_ == COS) {
            return Number.parseNumber(Math.cos(value1.doubleValue()));
        } else if (function_ == SIN) {
            return Number.parseNumber(Math.sin(value1.doubleValue()));
        } else if (function_ == TAN) {
            return Number.parseNumber(Math.tan(value1.doubleValue()));
        }else  if (function_ == COSH) {
            return Number.parseNumber(Math.cosh(value1.doubleValue()));
        } else if (function_ == SINH) {
            return Number.parseNumber(Math.sinh(value1.doubleValue()));
        } else if (function_ == TANH) {
            return Number.parseNumber(Math.tanh(value1.doubleValue()));
        } else if (function_ == ACOS) {
            return Number.parseNumber(Math.acos(value1.doubleValue()));
        } else if (function_ == ASIN) {
            return Number.parseNumber(Math.asin(value1.doubleValue()));
        } else if (function_ == ATAN) {
            return Number.parseNumber(Math.atan(value1.doubleValue()));
        } else if (function_ == SEC) {
            return Number.parseNumber(1 / Math.cos(value1.doubleValue()));
        } else if (function_ == CSC) {
            return Number.parseNumber(1 / Math.sin(value1.doubleValue()));
        } else if (function_ == COT) {
            return Number.parseNumber(1 / Math.tan(value1.doubleValue()));
        } else if (function_ == ASEC) {
            return Number.parseNumber(Math.acos(1 / value1.doubleValue()));
        } else if (function_ == ACSC) {
            return Number.parseNumber(Math.asin(1 / value1.doubleValue()));
        } else if (function_ == ACOT) {
            return Number.parseNumber(Math.atan(1 / value1.doubleValue()));
        } else if (function_ == SECH) {
            return Number.parseNumber(Math.cosh(1 / value1.doubleValue()));
        } else if (function_ == CSCH) {
            return Number.parseNumber(Math.sinh(1 / value1.doubleValue()));
        } else if (function_ == COTH) {
            return Number.parseNumber(Math.tanh(1 / value1.doubleValue()));
        } else if (function_ == ACOSH) {
            return Number.parseNumber(acosh(value1.doubleValue()));
        } else if (function_ == ASINH) {
            return Number.parseNumber(asinh(value1.doubleValue()));
        } else if (function_ == ATANH) {
            return Number.parseNumber(atanh(value1.doubleValue()));
        } else if (function_ == ASECH) {
            return Number.parseNumber(acosh(1 / value1.doubleValue()));
        } else if (function_ == ACSCH) {
            return Number.parseNumber(asinh(1 / value1.doubleValue()));
        } else if (function_ == ACOTH) {
            return Number.parseNumber(atanh(1 / value1.doubleValue()));
        }else{
            System.out.println("sasad");
            System.out.println("fsdfsdf");
        }

        return null;

    }

    static class InverseHyperbolicFunctions {

        public static double doubleFactorial(double v) {
            return v == -1 || v == 1 || v == 0 ? 1 : v * doubleFactorial(v - 2);
        }

        public static double factorial(double v) {
            return v == -1 || v == 1 || v == 0 ? 1 : v * doubleFactorial(v - 1);
        }

        public static double acosh(double x) {
            if (x < 1) {
                throw new IllegalArgumentException("x must be greater than 1");
            }
            double v = Math.log1p(x * 2);
            double term;
            int n = 1;
            do {
                term = doubleFactorial(2 * n - 1) / doubleFactorial(2 * n) / (2 * n * Math.pow(x, 2 * n));
                v -= term;
            } while (term >= 1e-10);
            return v;
        }

        public static double asinh(double x) {
            double v = 0;
            double term;
            int n = 0;
            do {
                term = Math.pow(-1, n) * factorial(n * 2) / Math.pow(4, n) / Math.pow(factorial(n), 2) / (2 * n + 1) * Math.pow(x, 2 * n + 1);
                v += term;
            } while (Math.abs(term) < 1e-10);
            return v;
        }

        public double atanh(double x) {
            if (Math.abs(x) >= 1) {
                throw new IllegalArgumentException("Absolute value of x must be less than or equal to 1");
            }
            double v = 0;
            double term;
            int n = 0;
            do {
                term = Math.pow(x, 2 * x + 1) / (2 * n + 1);
                v += term;
            } while (Math.abs(term) < 1e-10);
            return v;

        }
    }
}
