package com.calculate.calculus;

import com.Tokenizing.Token;
import static com.Tokenizing.Token.*;
import com.Tokenizing.TokenList;
import com.Tokenizing.TokenType;
import com.calculate.equation.ExpressionEvaluator;
import com.calculate.Number;

/**
 *
 * @author AW Developer
 */
public class Oparator extends Calculus {

    Token oparator;

    public Oparator(TokenList f1, Token oparator, TokenList f2) {
        super(f1, TokenType.OPARATOR, f2);
        this.oparator = oparator;
    }

    @Override
    public TokenList doTheMath() {
        TokenList tk = new TokenList();
        try {
            if (oparator == RAISED) {
                tk.addTokens(fx);
                tk.addToken(RAISED);
                tk.addTokens(gx.pranthesise());
                tk.addToken(MULTIPLY);
                tk.addToken(Token.OPEN_PRANTHESIS);
                tk.addTokens(new Differentiator(gx).differentiate(2));
                tk.addToken(MULTIPLY);
                tk.addToken(Token.LN);
                tk.addTokens(fx.pranthesise());
                tk.addToken(PLUS);
                tk.addTokens(gx);
                tk.addToken(MULTIPLY);
                tk.addTokens(new Differentiator(fx).differentiate(2));
                tk.addToken(DIVIDE);
                tk.addTokens(fx.pranthesise());
                tk.addToken(Token.CLOSE_PRANTHESIS);
            } else if (oparator == MULTIPLY) {
                if ((fx.hasIndependent() || fx.hasDependent()) && (gx.hasIndependent() || gx.hasDependent())) {
                    tk.addTokens(new Differentiator(fx).differentiate(2));
                    tk.addToken(MULTIPLY);
                    tk.addTokens(gx);
                    tk.addToken(PLUS);
                    tk.addTokens(new Differentiator(gx).differentiate(2));
                    tk.addToken(MULTIPLY);
                    tk.addTokens(fx);
                } else {
                    if (!(fx.hasIndependent() || fx.hasDependent())) {
                        Number evaluate = new ExpressionEvaluator(fx).evaluate();
                        Token etoken = new Token(TokenType.NUMBER, evaluate);
                        if (evaluate.doubleValue() == 0) {
                            tk.addToken(etoken);
                        } else {
                            tk.addToken(etoken);
                            tk.addToken(MULTIPLY);
                            tk.addTokens(new Differentiator(gx).differentiate(2));
                        }
                    } else if (!(gx.hasIndependent() || gx.hasDependent())) {
                        Number evaluate = new ExpressionEvaluator(gx).evaluate();
                        Token etoken = new Token(TokenType.NUMBER, evaluate);
                        if (evaluate.doubleValue() == 0) {
                            tk.addToken(etoken);
                        } else {
                            tk.addToken(etoken);
                            tk.addToken(MULTIPLY);
                            tk.addTokens(new Differentiator(fx).differentiate(2));
                        }
                    }
                }
            } else if (oparator == DIVIDE) {
                tk.addToken(Token.OPEN_PRANTHESIS);
                tk.addTokens(new Differentiator(fx).differentiate(2));
                tk.addToken(MULTIPLY);
                tk.addTokens(gx);
                tk.addToken(MINUS);
                tk.addTokens(new Differentiator(gx).differentiate(2));
                tk.addToken(MULTIPLY);
                tk.addTokens(fx);
                tk.addToken(Token.CLOSE_PRANTHESIS);
                tk.addToken(DIVIDE);
                tk.addTokens(gx.pranthesise());
                tk.addToken(RAISED);
                tk.addToken(TWO);
            } else {
                tk.addTokens((new Differentiator(fx).differentiate(2)));
                tk.addToken(oparator);
                tk.addTokens((new Differentiator(gx).differentiate(2)));
            }

        } catch (Exception ex) {
            makeError(ex);
        }
        System.out.println("[OPARATION DONE]" + tk.toLocalString());
        return tk;
    }
}
