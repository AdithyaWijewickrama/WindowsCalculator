package com.calculate.equation;

import com.Tokenizing.Token;
import static com.Tokenizing.Token.DIVIDE;
import static com.Tokenizing.Token.MINUS;
import static com.Tokenizing.Token.RAISED;
import static com.Tokenizing.Token.PLUS;
import com.Tokenizing.TokenType;
import com.calculate.Number;
import static com.Tokenizing.Token.MULTIPLY;

/**
 *
 * @author AW Developer
 */
public class Oparator extends Calculate {

    Token oparator;

    public Oparator(Number v1, Token oparator, Number v2) {
        super(v1, TokenType.OPARATOR, v2);
        System.out.println(v2.doubleValue());
        System.out.println(v1.doubleValue());
        this.oparator = oparator;
    }

    @Override
    public Number doTheMath() {
        try {
            if (oparator == PLUS) {
                return Number.parseNumber(Double.sum(value1.doubleValue(), value2.doubleValue()));
            }
            if (oparator == MINUS) {
                return Number.parseNumber(Double.sum(value1.doubleValue(), -value2.doubleValue()));
            }
            if (oparator == RAISED) {
                return Number.parseNumber(Math.pow(value1.doubleValue(), value2.doubleValue()));
            }
            if (oparator == MULTIPLY) {
                return Number.parseNumber(value1.doubleValue() * value2.doubleValue());
            }
            if (oparator == DIVIDE) {
                return Number.parseNumber(value1.doubleValue() / value2.doubleValue());
            } else {
                throw new AssertionError();
            }
        } catch (AssertionError assertionError) {
        }
        return null;
    }
}
