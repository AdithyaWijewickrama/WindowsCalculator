package com.calculate.equation;

import com.tokenizing.Token;
import static com.tokenizing.Token.DIVIDE;
import static com.tokenizing.Token.MINUS;
import static com.tokenizing.Token.RAISED;
import static com.tokenizing.Token.PLUS;
import com.tokenizing.TokenType;
import com.calculate.Number;
import static com.tokenizing.Token.MULTIPLY;

/**
 *
 * @author AW Developer
 */
public class Oparator extends Calculate {

    Token oparator;

    public Oparator(Calculate v1, Token oparator, Calculate v2) {
        super(v1, TokenType.OPARATOR, v2);
        this.oparator = oparator;
    }

    @Override
    public Number doTheMath() {
        try {
            if (oparator == PLUS) {
                return Number.parseNumber(Double.sum(value1.doTheMath().doubleValue(), value2.doTheMath().doubleValue()));
            }
            if (oparator == MINUS) {
                return Number.parseNumber(Double.sum(value1.doTheMath().doubleValue(), -value2.doTheMath().doubleValue()));
            }
            if (oparator == RAISED) {
                return Number.parseNumber(Math.pow(value1.doTheMath().doubleValue(), value2.doTheMath().doubleValue()));
            }
            if (oparator == MULTIPLY) {
                return Number.parseNumber(value1.doTheMath().doubleValue() * value2.doTheMath().doubleValue());
            }
            if (oparator == DIVIDE) {
                return Number.parseNumber(value1.doTheMath().doubleValue() / value2.doTheMath().doubleValue());
            } else {
                throw new AssertionError();
            }
        } catch (AssertionError assertionError) {
        }
        return null;
    }
}
