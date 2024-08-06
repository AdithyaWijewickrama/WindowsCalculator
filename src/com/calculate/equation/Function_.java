package com.calculate.equation;

import Convert.Angle;
import com.tokenizing.Token;
import static com.tokenizing.Token.*;
import com.tokenizing.TokenType;
import com.calculate.Number;
import static java.lang.Math.E;
import static com.tokenizing.Token.LOG10;

/**
 *
 * @author AW Developer
 */
public class Function_ extends Calculate {

    Token function_;
    Angle angleType = Angle.radians;

    public Function_(Token function_, Calculate value) {
        super(value, TokenType.FUNCTION_);
        this.function_ = function_;
    }

    public Function_(Token function_, Calculate angle, Angle angleType) {
        super(angle, TokenType.FUNCTION_);
        this.function_ = function_;
        this.angleType = angleType;
    }

    @Override
    public Number doTheMath() {
        try {
            if (function_ == COS
                    || function_ == SIN
                    || function_ == TAN
                    || function_ == COSH
                    || function_ == SINH
                    || function_ == TANH
                    || function_ == ACOS
                    || function_ == ASIN
                    || function_ == ATAN
                    || function_ == ACOSH
                    || function_ == ASINH
                    || function_ == ATANH
                    || function_ == SEC
                    || function_ == CSC
                    || function_ == COT
                    || function_ == ASEC
                    || function_ == ACSC
                    || function_ == ACOT
                    || function_ == SECH
                    || function_ == CSCH) {
                return new Triaganometry(value1, function_, angleType).doTheMath();
            } else if (function_ == ABS) {
                return Number.parseNumber(Math.abs(value1.doTheMath().doubleValue()));
            } else if (function_ == CEILING) {
                return Number.parseNumber(Math.ceil(value1.doTheMath().doubleValue()));
            } else if (function_ == FLOOR) {
                return Number.parseNumber(Math.floor(value1.doTheMath().doubleValue()));
            } else if (function_ == CUBE) {
                return Number.parseNumber(Math.pow(value1.doTheMath().doubleValue(), 3));
            } else if (function_ == CUBEROOT) {
                return Number.parseNumber(Math.pow(value1.doTheMath().doubleValue(), 1 / 3));
            } else if (function_ == SQUARED) {
                return Number.parseNumber(Math.pow(value1.doTheMath().doubleValue(), 2));
            } else if (function_ == SQUREROOT) {
                return Number.parseNumber(Math.pow(value1.doTheMath().doubleValue(), 1 / 2));
            } else if (function_ == TENRAISED) {
                return Number.parseNumber(Math.pow(10, value1.doTheMath().doubleValue()));
            } else if (function_ == TWORAISED) {
                return Number.parseNumber(Math.pow(2, value1.doTheMath().doubleValue()));
            } else if (function_ == eRAISED) {
                return Number.parseNumber(Math.pow(E, value1.doTheMath().doubleValue()));
            } else if (function_ == LOG10) {
                return Number.parseNumber(Math.log10(value1.doTheMath().doubleValue()));
            } else if (function_ == LN) {
                return Number.parseNumber(Math.log1p(value1.doTheMath().doubleValue()));
            } else if (function_ == RECIPROCAL) {
                return Number.parseNumber(1 / (value1.doTheMath().doubleValue()));
            } else if (function_ == FACTORIAL) {
                return Number.parseNumber(Triaganometry.InverseHyperbolicFunctions.factorial(value1.doTheMath().doubleValue()));
            } else {
                System.out.println("fdmfskdf");
                makeError("Unsuppported token");
            }
        } catch (AssertionError ex) {
            System.err.println(ex);
            makeError("Assersion error");
        }
        return null;
    }

}
