package com.calculate.calculus;

import com.tokenizing.Token;
import static com.tokenizing.Token.*;
import com.tokenizing.TokenList;
import com.tokenizing.TokenType;

/**
 *
 * @author AW Developer
 */
public class _Function_ extends Calculus {

    Token _function_;

    public _Function_(Token _func, TokenList v1, TokenList v2) {
        super(v1, TokenType._FUNCTION_, v2);
        this._function_ = _func;
    }

    @Override
    public TokenList doTheMath() {
        TokenList t = new TokenList();
        try {
            if (_function_ == YROOT) {
                t = new Oparator(gx, RAISED, fx.reciprocal()).doTheMath();
            } else if (_function_ == LOGBASEY) {
                t.addToken(OPEN_PRANTHESIS);
                t.addTokens(gx.reciprocal());
                t.addToken(MULTIPLY);
                t.addTokens(new Differentiator(gx).differentiate(1));
                t.addToken(MULTIPLY);
                t.addToken(LN);
                t.addTokens(fx.pranthesise());
                t.addToken(MINUS);
                t.addTokens(fx.reciprocal());
                t.addToken(MULTIPLY);
                t.addTokens(new Differentiator(fx).differentiate(1));
                t.addToken(MULTIPLY);
                t.addToken(LN);
                t.addTokens(gx.pranthesise());
                t.addToken(CLOSE_PRANTHESIS);
                t.addToken(DIVIDE);
                t.addToken(LN);
                t.addTokens(gx.pranthesise());
                t.addToken(RAISED);
                t.addToken(TWO);
            } else {
                throw new AssertionError();
            }
        } catch (Exception ex) {
            makeError("Function" + _function_ + " is not valid");
        }
        return t;
    }
}
