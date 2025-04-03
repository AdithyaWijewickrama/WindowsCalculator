package com.calculate.equation;

import com.tokenizing.Token;
import static com.tokenizing.Token.*;
import com.tokenizing.TokenType;
import com.calculate.CNumber;
import static java.lang.Math.E;
import static com.tokenizing.Token.LOG10;
import convert.Angle;

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
    public CNumber doTheMath() {
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
                return CNumber.parseNumber(Math.abs(value1.doTheMath().doubleValue()));
            } else if (function_ == CEILING) {
                return CNumber.parseNumber(Math.ceil(value1.doTheMath().doubleValue()));
            } else if (function_ == FLOOR) {
                return CNumber.parseNumber(Math.floor(value1.doTheMath().doubleValue()));
            } else if (function_ == CUBE) {
                return CNumber.parseNumber(Math.pow(value1.doTheMath().doubleValue(), 3));
            } else if (function_ == CUBEROOT) {
                return CNumber.parseNumber(Math.pow(value1.doTheMath().doubleValue(), 1 / 3));
            } else if (function_ == SQUARED) {
                return CNumber.parseNumber(Math.pow(value1.doTheMath().doubleValue(), 2));
            } else if (function_ == SQUREROOT) {
                return CNumber.parseNumber(Math.pow(value1.doTheMath().doubleValue(), 1 / 2));
            } else if (function_ == TENRAISED) {
                return CNumber.parseNumber(Math.pow(10, value1.doTheMath().doubleValue()));
            } else if (function_ == TWORAISED) {
                return CNumber.parseNumber(Math.pow(2, value1.doTheMath().doubleValue()));
            } else if (function_ == eRAISED) {
                return CNumber.parseNumber(Math.pow(E, value1.doTheMath().doubleValue()));
            } else if (function_ == LOG10) {
                return CNumber.parseNumber(Math.log10(value1.doTheMath().doubleValue()));
            } else if (function_ == LN) {
                return CNumber.parseNumber(Math.log1p(value1.doTheMath().doubleValue()));
            } else if (function_ == RECIPROCAL) {
                return CNumber.parseNumber(1 / (value1.doTheMath().doubleValue()));
            } else if (function_ == FACTORIAL) {
                return CNumber.parseNumber(Triaganometry.InverseHyperbolicFunctions.factorial(value1.doTheMath().doubleValue()));
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
