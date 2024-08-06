package com.calculate.equation;

import com.tokenizing.Token;
import com.tokenizing.TokenList;
import com.tokenizing.TokenParser;
import com.tokenizing.TokenType;
import com.calculate.Number;
import com.calculate.Root;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public class ExpressionEvaluator extends  TokenParser{

//    private TokenList tokens;
    
    public Number x=new Number(0.);
    private Calculate parsedTokens;
    
    
    public ExpressionEvaluator(String exp) throws Exception {
        super(exp);
        parsedTokens = doOparations(this);
    }
    public ExpressionEvaluator(TokenList exp) throws Exception {
        super(exp);
        parsedTokens = doOparations(this);
    }

    public Number evaluate() {
        try {
            if (!hasIndependent()) {
                return parsedTokens.doTheMath();
            } else {
                throw new NoSuchFieldException("No value for " + Token.x.name + " given");
            }
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Number evaluateAt(Number independant) {
        try {
            if (hasIndependent()) {
                x.setNumber(independant);
                return parsedTokens.doTheMath();
            } else {
                throw new NoSuchFieldException("No independant variable " + Token.x.name + " given");
            }
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void parseTokens() throws ParseException{
        parsedTokens=doOparations(this);
    }
    
    private Calculate doOparations(TokenList tokens) throws ParseException {
        print("Token parsing started....");
        print("\t"+tokens.toLocalString().replaceAll("\n", "\n\t"));

        int index;
        Token token;
        Calculate c = null;
        index = scanFor(tokens, Token.PLUS);
        if (index != -1) {
            token = tokens.tokenAt(index);
            c = new Oparator(doOparations(tokens.split(0, index)), token, doOparations(tokens.split(index + 1, tokens.size())));
        } else {
            index = scanFor(tokens, Token.MINUS);
            if (index != -1) {
                token = tokens.tokenAt(index);
                c = new Oparator(doOparations(tokens.split(0, index)), token, doOparations(tokens.split(index + 1, tokens.size())));
            } else {
                index = scanFor(tokens, TokenType.OPARATOR);
                if (index != -1) {
                    token = tokens.tokenAt(index);
                    c = new Oparator(doOparations(tokens.split(0, index)), token, doOparations(tokens.split(index + 1, tokens.size())));
                } else {
                    index = scanFor(tokens, TokenType.FUNCTION_);
                    if (index != -1) {
                        token = tokens.tokenAt(index);
                        c = new Function_(token, doOparations(getParameter(tokens, index + 1)));
                    } else {
                        index = scanFor(tokens, TokenType._FUNCTION_);
                        if (index != -1) {
                            token = tokens.tokenAt(index);
                            ArrayList<TokenList> params = getParameters(tokens, index + 1);
                            c = new _Function_(token, doOparations(params.get(0)), doOparations(params.get(1)));
                        }
                    }
                }
            }
        }
        if (c != null) {
            return c;
        } else {
            final Number num;
            print(tokens);
            if (tokens.tokenAt(0) == Token.OPEN_PRANTHESIS && tokens.tokenAt(tokens.size() - 1) == Token.CLOSE_PRANTHESIS) {
                tokens.deleteToken(0);
                tokens.deleteToken(tokens.size() - 1);
                return doOparations(tokens);
            } else {
                token = tokens.tokenAt(0);
                switch (token.type) {
                    case NUMBER:
                        num = token.number;
                        break;
                    case DIGIT:
                        num = token.number;
                        break;
                    case VARIABLE:
                        num = token.number;
                        break;
                    case INDEPENDENT:
                        return new Root(x);
                    case DEPENDENT:
                        num=null;
                        break;
                    default:
                        throw new Error("Unparsable token for " + tokens);
                }
            }
            return new Root(num);
        }
    }

    public static int scanFor(TokenList tokens, Token token) {
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

    public static int scanFor(TokenList tokens, TokenType type) {
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

    public static ArrayList<TokenList> getParameters(TokenList tokens, int i) {
        int pranthasis = 0;
        ArrayList<Token> tokenSet = new ArrayList<>();
        ArrayList<TokenList> params = new ArrayList<>();
        for (int j = i; j < tokens.size(); j++) {
            Token token = tokens.tokenAt(j);
            if (token == Token.OPEN_PRANTHESIS) {
                pranthasis++;
            } else if (token == Token.CLOSE_PRANTHESIS) {
                pranthasis--;
            } else if (pranthasis == 1 && token == Token.COMMA) {
                params.add(new TokenList(tokenSet));
                tokenSet = new ArrayList<>();
            }
            if (pranthasis == 0) {
                params.add(new TokenList(tokenSet));
                print("Parameters------------------");
                print("\t"+params);
                return params;
            }
            if (!(token == Token.COMMA || token == Token.OPEN_PRANTHESIS || token == Token.CLOSE_PRANTHESIS)) {
                tokenSet.add(token);
            }
        }
        return null;

    }

    public static TokenList getParameter(TokenList tokenList, int j) {
        int pranthasis = 0;
        TokenList tokens = new TokenList();
        for (int i = j; i < tokenList.size(); i++) {
            Token token = tokenList.tokenAt(i);
            if (token == Token.OPEN_PRANTHESIS) {
                pranthasis++;
            } else if (token == Token.CLOSE_PRANTHESIS) {
                pranthasis--;
            }
            tokens.addToken(token);
            if (pranthasis == 0) {
                print("Parameter------------");
                print("\t"+tokens);
                return tokens;
            }
        }
        return null;

    }
    

    public static void main(String[] args) {
        try {
            //        print(new ExpressionEvaluator("log(x^2,8)").evaluateAt(Number.parseNumber(2.)).doubleValue());
            ExpressionEvaluator e = new ExpressionEvaluator("x^3-1");
            e.parseTokens();
            System.out.println(e.parsedTokens);
            for (int i = 0; i < 10; i++) {
                print(e.evaluateAt(Number.parseNumber(i)).doubleValue());
            }
        } catch (Exception ex) {
            Logger.getLogger(ExpressionEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
