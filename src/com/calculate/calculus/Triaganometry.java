package com.calculate.calculus;

import com.tokenizing.Token;
import static com.tokenizing.Token.*;
import com.tokenizing.TokenList;
import convert.Angle;

/**
 *
 * @author AW Developer
 */
public final class Triaganometry extends Function_ {

    public Triaganometry(TokenList fx, Token token, Angle angleType) {
        super(token, fx);
        setAngleType(angleType);
    }

    public Triaganometry(TokenList fx, Token token) {
        super(token, fx);
        setAngleType(Angle.radians);
    }

    public void setAngleType(Angle angleType) {
        this.angleType = angleType;
    }

    public Angle getAngleType() {
        return angleType;
    }

    @Override
    public TokenList doTheMath() {
        TokenList t = null;
        try {
            t = new TokenList();
            if (function_ == COS) {
                t.addToken(MINUS);
                t.addToken(SIN);
                t.addTokens(fx.pranthesise());
            } else if (function_ == SIN) {
                t.addToken(COS);
                t.addTokens(fx.pranthesise());
            } else if (function_ == TAN) {
                t.addToken(SEC);
                t.addTokens(fx.pranthesise());
                t.addToken(RAISED);
                t.addToken(TWO);
            } else if (function_ == SEC) {
                t.addToken(SEC);
                t.addTokens(fx.pranthesise());
                t.addToken(MULTIPLY);
                t.addToken(TAN);
                t.addTokens(fx.pranthesise());
            } else if (function_ == CSC) {
                t.addToken(MINUS);
                t.addToken(CSC);
                t.addTokens(fx.pranthesise());
                t.addToken(MULTIPLY);
                t.addToken(COT);
                t.addTokens(fx.pranthesise());
            } else if (function_ == COT) {
                t.addToken(MINUS);
                t.addToken(CSC);
                t.addTokens(fx.pranthesise());
                t.addToken(RAISED);
                t.addToken(TWO);
            } else if (function_ == ACOS) {
                t = new Triaganometry(fx, ASIN).doTheMath();
                t.insertToken(MINUS, 0);
            } else if (function_ == ASIN) {
                t.addToken(RECIPROCAL);
                t.addToken(SQUREROOT);
                t.addToken(OPEN_PRANTHESIS);
                t.addTokens(fx.pranthesise());
                t.addToken(RAISED);
                t.addToken(TWO);
                t.addToken(MINUS);
                t.addToken(ONE);
                t.addToken(CLOSE_PRANTHESIS);
            } else if (function_ == ATAN) {
                t.addToken(RECIPROCAL);
                t.addToken(OPEN_PRANTHESIS);
                t.addTokens(fx.pranthesise());
                t.addToken(RAISED);
                t.addToken(TWO);
                t.addToken(PLUS);
                t.addToken(ONE);
                t.addToken(CLOSE_PRANTHESIS);
            } else if (function_ == ASEC) {
                t.addToken(RECIPROCAL);
                t.addToken(OPEN_PRANTHESIS);
                t.addToken(SQUREROOT);
                t.addToken(OPEN_PRANTHESIS);
                t.addTokens(fx.pranthesise());
                t.addToken(RAISED);
                t.addToken(TWO);
                t.addToken(MINUS);
                t.addToken(ONE);
                t.addToken(CLOSE_PRANTHESIS);
                t.addToken(MULTIPLY);
                t.addTokens(fx.pranthesise());
                t.addToken(CLOSE_PRANTHESIS);
            } else if (function_ == ACSC) {
                t = new Triaganometry(fx, ASEC).doTheMath();
                t.insertToken(MINUS, 0);
            } else if (function_ == ACOT) {
                t = new Triaganometry(fx, ATAN).doTheMath();
                t.insertToken(MINUS, 0);
            } else if (function_ == COSH) {

            } else if (function_ == SINH) {

            } else if (function_ == TANH) {

            } else if (function_ == SECH) {

            } else if (function_ == CSCH) {

            } else if (function_ == COTH) {

            } else if (function_ == ACOSH) {

            } else if (function_ == ASINH) {

            } else if (function_ == ATANH) {

            } else if (function_ == ASECH) {

            } else if (function_ == ACSCH) {

            } else if (function_ == ACOTH) {

            } else {
                return null;
            }

            t.addToken(MULTIPLY);
            t.addTokens(new Differentiator(fx).differentiate(1).pranthesise());
        } catch (Exception ex) {
            makeError(ex);
        }
        return t;
    }
}
