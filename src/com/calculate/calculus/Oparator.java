package com.calculate.calculus;

import com.tokenizing.Token;
import static com.tokenizing.Token.*;
import com.tokenizing.TokenList;
import com.tokenizing.TokenType;
import com.calculate.equation.ExpressionEvaluator;
import com.calculate.CNumber;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public TokenList getExpression() {
        TokenList tk = new TokenList();
        tk.addTokens(fx);
        tk.addToken(RAISED);
        tk.addTokens(gx.pranthesise());
        return tk;
    }

    @Override
    public TokenList doTheMath() {
        TokenList tk = new TokenList();
        try {
            if (oparator == RAISED) {
                if (!gx.hasIndependent() && !gx.hasDependent() && (fx.hasIndependent() || fx.hasDependent())) {
                    CNumber n = new ExpressionEvaluator(gx).evaluate();
                    tk.addToken(new Token(n));
                    tk.addToken(MULTIPLY);
                    tk.addTokens(fx.pranthesise());
                    n = n.substract(CNumber.parseNumber(1));
                    if (n.doubleValue() != 1) {
                        tk.addToken(RAISED);
                        tk.addToken(new Token(n));
                    }
                    tk.addToken(MULTIPLY);
                    tk.addTokens(new Differentiator(fx).differentiate(1));
                } else if (!fx.hasIndependent() && !fx.hasDependent() && (gx.hasIndependent() || gx.hasDependent())) {
                    CNumber n = new ExpressionEvaluator(fx).evaluate();
                    tk.addToken(LN);
                    tk.addToken(OPEN_PRANTHESIS);
                    tk.addToken(new Token(n));
                    tk.addToken(CLOSE_PRANTHESIS);
                    tk.addToken(MULTIPLY);
                    tk.addTokens(gx.pranthesise());
                    tk.addToken(MULTIPLY);
                    tk.addTokens(new Differentiator(gx).differentiate(1));
                } else {
                    tk.addTokens(fx);
                    tk.addToken(RAISED);
                    tk.addTokens(gx.pranthesise());
                    tk.addToken(MULTIPLY);
                    tk.addToken(Token.OPEN_PRANTHESIS);
                    tk.addTokens(new Differentiator(gx).differentiate(1));
                    tk.addToken(MULTIPLY);
                    tk.addToken(Token.LN);
                    tk.addTokens(fx.pranthesise());
                    tk.addToken(PLUS);
                    tk.addTokens(gx);
                    tk.addToken(MULTIPLY);
                    tk.addTokens(new Differentiator(fx).differentiate(1));
                    tk.addToken(DIVIDE);
                    tk.addTokens(fx.pranthesise());
                    tk.addToken(Token.CLOSE_PRANTHESIS);
                }
            } else if (oparator == MULTIPLY) {
                if ((fx.hasIndependent() || fx.hasDependent()) && (gx.hasIndependent() || gx.hasDependent())) {
                    tk.addTokens(new Differentiator(fx).differentiate(1));
                    tk.addToken(MULTIPLY);
                    tk.addTokens(gx);
                    tk.addToken(PLUS);
                    tk.addTokens(new Differentiator(gx).differentiate(1));
                    tk.addToken(MULTIPLY);
                    tk.addTokens(fx);
                } else {
                    if (!(fx.hasIndependent() || fx.hasDependent())) {
                        CNumber evaluate = new ExpressionEvaluator(fx).evaluate();
                        Token etoken = new Token(TokenType.NUMBER, evaluate);
                        if (evaluate.doubleValue() == 0) {
                            tk.addToken(etoken);
                        } else {
                            tk.addToken(etoken);
                            tk.addToken(MULTIPLY);
                            tk.addTokens(new Differentiator(gx).differentiate(1));
                        }
                    } else if (!(gx.hasIndependent() || gx.hasDependent())) {
                        CNumber evaluate = new ExpressionEvaluator(gx).evaluate();
                        Token etoken = new Token(TokenType.NUMBER, evaluate);
                        if (evaluate.doubleValue() == 0) {
                            tk.addToken(etoken);
                        } else {
                            tk.addToken(etoken);
                            tk.addToken(MULTIPLY);
                            tk.addTokens(new Differentiator(fx).differentiate(1));
                        }
                    }
                }
            } else if (oparator == DIVIDE) {
                tk.addToken(Token.OPEN_PRANTHESIS);
                tk.addTokens(new Differentiator(fx).differentiate(1));
                tk.addToken(MULTIPLY);
                tk.addTokens(gx);
                tk.addToken(MINUS);
                tk.addTokens(new Differentiator(gx).differentiate(1));
                tk.addToken(MULTIPLY);
                tk.addTokens(fx);
                tk.addToken(Token.CLOSE_PRANTHESIS);
                tk.addToken(DIVIDE);
                tk.addTokens(gx.pranthesise());
                tk.addToken(RAISED);
                tk.addToken(TWO);
            } else {
                tk.addTokens((new Differentiator(fx).differentiate(1)));
                tk.addToken(oparator);
                tk.addTokens((new Differentiator(gx).differentiate(1)));
            }

        } catch (Exception ex) {
            makeError(ex);
        }
        System.out.println("[OPARATION DONE]" + tk.toLocalString());
        return tk;
    }

    public static void main(String[] args) {
        try {
            Differentiator d = new Differentiator("x^-1");
            TokenList dt = d.differentiate(1);
            System.out.println(dt.toLocalString());
        } catch (Exception ex) {
            Logger.getLogger(Oparator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
