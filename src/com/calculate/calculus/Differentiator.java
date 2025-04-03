package com.calculate.calculus;

import com.calculate.equation.ExpressionEvaluator;
import com.tokenizing.Token;
import com.tokenizing.TokenList;
import com.tokenizing.TokenParser;
import com.tokenizing.TokenType;
import com.calculate.CNumber;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public final class Differentiator extends TokenParser {

    private ExpressionEvaluator expEv;

    public Differentiator(String exp) throws Exception {
        super(exp);
        parse();
    }

    public Differentiator(TokenList tokens) throws Exception {
        super(tokens);
    }

    public TokenList differentiate(int coefficient) throws Exception {
        if(coefficient <0){
            throw new RuntimeException("Cannot have a negetive coefficient");
        }
        if (coefficient == 0) {
            return this;
        }
        if (hasIndependent()||hasDependent()) {
            setTokenList(parseTokens(this));
            return differentiate(coefficient - 1);
        } else {
            TokenList t = new TokenList();
            t.addToken(new Token(TokenType.NUMBER, CNumber.parseNumber(0.)));
            setTokenList(t);
            return t;
        }
    }

    public CNumber evaluateAt(CNumber x) throws Exception {
        if (expEv == null) {
            expEv = new ExpressionEvaluator(this);
        }
        return expEv.evaluateAt(x);
    }

    private TokenList parseTokens(TokenList tokens) throws ParseException {
        System.out.println("Token parsing started...");
        System.out.println("\t" + tokens.toLocalString().replaceAll("\n", "\n\t"));
        int index;
        Token token;
        TokenList list = new TokenList();
        DifferentialCalculus c = null;
        index = scanFor(tokens, Token.PLUS);
        if (index != -1) {
            token = tokens.tokenAt(index);
            c = new Operator((tokens.split(0, index)), token, (tokens.split(index + 1, tokens.size())));
        } else {
            index = scanFor(tokens, Token.MINUS);
            if (index != -1) {
                token = tokens.tokenAt(index);
                c = new Operator((tokens.split(0, index)), token, (tokens.split(index + 1, tokens.size())));
            } else {
                index = scanFor(tokens, TokenType.OPARATOR);
                if (index != -1) {
                    token = tokens.tokenAt(index);
                    c = new Operator((tokens.split(0, index)), token, (tokens.split(index + 1, tokens.size())));
                } else {
                    index = scanFor(tokens, TokenType.FUNCTION_);
                    if (index != -1) {
                        token = tokens.tokenAt(index);
                        c = new Function_(token, (getParameters(tokens, index + 1).get(0)));
                    } else {
                        index = scanFor(tokens, TokenType._FUNCTION_);
                        if (index != -1) {
                            token = tokens.tokenAt(index);
                            ArrayList<TokenList> params = getParameters(tokens, index + 1);
                            c = new _Function_(token, (params.get(0)), (params.get(1)));
                        }
                    }
                }
            }
        }
        if (c != null) {
            list.addTokens(c.doTheMath());
        } else {
            if (tokens.tokenAt(0) == Token.OPEN_PRANTHESIS && tokens.tokenAt(tokens.size() - 1) == Token.CLOSE_PRANTHESIS) {
                tokens.deleteToken(0);
                tokens.deleteToken(tokens.size() - 1);
                list.addTokens(parseTokens(tokens));
            } else {
                token = tokens.tokenAt(0);
                switch (token.type) {
                    case NUMBER:
                    case DIGIT:
                    case VARIABLE:
                        list.addToken(new Token(TokenType.NUMBER, CNumber.parseNumber(0.)));
                        break;
                    case INDEPENDENT:
                        list.addToken(new Token(TokenType.NUMBER, CNumber.parseNumber(1.)));
                        break;
                    case DY_BY_DX:
                        token.number=token.number.add(CNumber.parseNumber(1));
                        list.addToken(token);
                        break;
                    case DEPENDENT:
                        list.addToken(new Token(TokenType.DY_BY_DX,"dx/dy", CNumber.parseNumber(1.)));
                        break;
                    default:
                        throw new Error("Unparsable token for " + tokens);
                }
            }
        }
        System.out.println("Done...");
        System.out.println("\t" + tokens.toLocalString().replaceAll("\n", "\n\t"));
        return list;
    }

    public int scanFor(TokenList tokens, TokenType type) {
        int pranthasis = 0;
        for (int i = tokens.size() - 1; i >= 0; i--) {
            Token token = tokens.tokenAt(i);
            if (token == Token.OPEN_PRANTHESIS) {
                pranthasis++;
            }
            if (token == Token.CLOSE_PRANTHESIS) {
                pranthasis--;
            }
            if (pranthasis == 0 && token.type == type) {
                return i;
            }
        }
        return -1;
    }

    public int scanFor(TokenList tokens, Token token) {
        int pranthasis = 0;
        for (int i = tokens.size() - 1; i >= 0; i--) {
            Token t = tokens.tokenAt(i);
            if (t == Token.OPEN_PRANTHESIS) {
                pranthasis++;
            }
            if (t == Token.CLOSE_PRANTHESIS) {
                pranthasis--;
            }
            if (pranthasis == 0 && t == token) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<TokenList> getParameters(TokenList tokens, int i) {
        ArrayList<TokenList> params = new ArrayList<>();
        TokenList p = getParameter(tokens, i);
        System.out.println("Parameter...");
        System.out.println("\t" + p.toLocalString().replaceAll("\n", "\n\t"));
        while (true) {
            int j = scanFor(p, Token.COMMA);
            if (j != -1) {
                params.add(p.split(j + 1, p.size()));
                p.deleteToken(j);
                if (p.size() != 0) {
                    p = p.split(0, j);
                }
                System.out.println("\t" + p.toLocalString().replaceAll("\n", "\n\t"));
            } else {
                params.add(p);
                System.out.println("Done...");
                System.out.println("\t" + p.toLocalString().replaceAll("\n", "\n\t"));
                return params;
            }
        }
    }

    public TokenList getParameter(TokenList tokenList, int j) {
        int pranthasis = 0;
        TokenList tokens = new TokenList();
        for (int i = j; i < tokenList.size(); i++) {
            Token token = tokenList.tokenAt(i);
            if (token == Token.OPEN_PRANTHESIS) {
                if (pranthasis == 0) {
                    pranthasis++;
                    continue;
                }
                pranthasis++;
            } else if (token == Token.CLOSE_PRANTHESIS) {
                pranthasis--;
                if (pranthasis == 0) {
                    return tokens;
                }
            }
            tokens.addToken(token);
            if (pranthasis == 0) {
                return tokens;
            }
        }
        return null;

    }

    public static void main(String[] args) {
        Differentiator t;
        Scanner s = new Scanner(System.in);
        String cmd;
        OUTER:
        do {
            System.out.print("\nGive an function to differentiate:\n\t"+"Differentiator.java:218");
            cmd = s.nextLine();
            if (cmd.isEmpty()) {
                continue;
            }
            try {
                t = new Differentiator(cmd);
                t.differentiate(1);
//                System.out.print("\nValue at/(n for next space for extit):\n\t");
//                cmd = s.nextLine();
//                switch (cmd) {
//                    case "c":
//                        break OUTER;
//                    case " ":
//                        break;
//                }
//                CNumber n = new CNumber(cmd, com.calculate.NumberFormat.NORMAL_DECIMAL);
//                CNumber n1 = t.evaluateAt(n);
//                System.out.print("\nValue at x=" + n.getNumberString() + ":\n\t");

//                System.out.print(n1.doubleValue());
            } catch (ParseException ex) {
                Logger.getLogger(Differentiator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Differentiator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

}
