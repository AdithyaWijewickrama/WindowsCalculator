package com.calculate.equation;

import com.Tokenizing.Token;
import static com.Tokenizing.Token.LOGBASEY;
import static com.Tokenizing.Token.MODULO;
import static com.Tokenizing.Token.YROOT;
import com.Tokenizing.TokenType;
import com.calculate.Number;

/**
 *
 * @author AW Developer
 */
public class _Function_ extends Calculate {

    Token _function_;

    public _Function_(Token _func, Number v1, Number v2) {
        super(v1, TokenType._FUNCTION_, v2);
        this._function_ = _func;
    }

    @Override
    public Number doTheMath() {
        try {
            if (_function_ == YROOT) {
                return Number.parseNumber(Math.pow(value1.doubleValue(), value2.doubleValue()));
            }
            if (_function_ == MODULO) {
                return Number.parseNumber(value1.doubleValue() % value2.doubleValue());
            }
            if (_function_ == LOGBASEY) {
                return Number.parseNumber(Math.log(value2.doubleValue()) / Math.log(value1.doubleValue()));
            } else {
                throw new AssertionError();
            }
        } catch (AssertionError assertionError) {
            makeError("Function"+_function_+" is not valid");
        }
        return null;
    }
}
