package com.Tokenizing;

import com.calculate.equation.ExpressionEvaluator;
import com.calculate.Number;
import com.calculate.Variable;
import com.calculate.VariableList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public class TokenParser extends TokenList {

    private String exp;
    private VariableList variableList;

    public TokenParser(String exp) throws Exception {
        super();
        this.exp = exp;
        variableList = new VariableList();
        parse();
    }

    public TokenParser(TokenList tokenList) throws Exception {
        super(tokenList.tokenList);
        variableList = new VariableList();
        updateTokens(tokenList);
    }

    public final void updateTokens(TokenList tokenList) throws Exception {
        checkPranthesis(tokenList);
        substituteVars();
        ignoreZero();
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setTokenList(TokenList tokenList) {
        setTokenList(tokenList.tokenList);
        try {
            updateTokens(this);
        } catch (Exception ex) {
            Logger.getLogger(TokenParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void parse() throws Exception {
        tokenize();
        updateTokens(this);
    }

    public static void checkTokens(TokenParser tokenParser) {
        String number = "";
        for (Token t : tokenParser.tokenList) {
            if (t != null) {
                if (t.type == TokenType.DIGIT) {
                    number += t.name.charAt(0);
                } else {
                    if (!number.equals("")) {
                        tokenParser.addToken(new Token(TokenType.NUMBER, Number.parseNumber(Double.parseDouble(number))));
                        number = "";
                    }
                    tokenParser.addToken(t);
                }
            }
        }
    }

    public TokenList tokenize() {
        System.out.println("Tokenizing started************************************");
        System.out.println("\t" + toLocalString().replaceAll("\n", "\n\t"));
        String number = "";
        String name = "";
        Token t;
        for (int i = 0; i < exp.length(); i++) {
            Character c = exp.charAt(i);
            t = Token.getTokenByName(c.toString());
            if (t != null) {
                if (t.type == TokenType.DIGIT) {
                    number += c;
                } else {
                    if (!number.equals("")) {
                        addToken(new Token(TokenType.NUMBER, Number.parseNumber(Double.parseDouble(number))));
                        number = "";
                    } else if (!name.equals("")) {
                        Token t1 = Token.getTokenByName(name);
                        if (t1 != null) {
                            addToken(t1);
                        } else {
                            for (int j = 0; j < name.length(); j++) {
                                String var = Character.toString(name.charAt(j));
                                if (variableList.get(var) == null) {
                                    variableList.add(new Variable(var));
                                }
                                addToken(new Token(TokenType.VARIABLE, var, variableList.get(var)));
                                if (j < name.length() - 1 || (j == name.length() - 1 && !isExpSplitter(t))) {
                                    addToken(Token.MULTIPLY);
                                }
                            }
                        }
                        name = "";
                    }
                    addToken(t);
                }
            } else {
                if (Character.isAlphabetic(c)) {
                    name += c;
                }
            }
        }
        if (!number.equals("")) {
            addToken(new Token(TokenType.NUMBER, Number.parseNumber(Double.valueOf(number))));
        } else if (!name.equals("")) {
            Token t1 = Token.getTokenByName(name);
            if (t1 != null) {
                addToken(t1);
            } else {
                for (int j = 0; j < name.length(); j++) {
                    Character c1 = name.charAt(j);
                    addToken(new Token(TokenType.VARIABLE, c1.toString()));
                    if (j < name.length() - 1) {
                        addToken(Token.MULTIPLY);
                    }
                }
            }
        }
        System.out.println("Done..\n\t" + toLocalString().replaceAll("\n", "\n\t"));

        return this;
    }

    public final void substituteVars() {
        System.out.println("Variable substituting...");
        System.out.println("\t" + toString().replaceAll("\n", "\n\t"));
        int i = 0;
        do {
            Token t = new Token(tokenAt(i));
//                if (tokenAt(i) == Token.MINUS) {
//                    if (next(t, i)) {
//                        if (t.type != TokenType.NUMBER && t.type != TokenType.OPARATOR && t.type != TokenType.SYMBOL) {
//                            deleteToken(i);
//                            insertToken(Token.MULTIPLY, i);
//                            insertToken(Token.CLOSE_PRANTHESIS, i);
//                            insertToken(new Token(TokenType.NUMBER, Number.parseNumber(-1.)), i);
//                            insertToken(Token.OPEN_PRANTHESIS, i);
//                            i += 4;
//                        }
//                    }
//                } else {
//                    i++;
//                }
            if (t.type == TokenType.VARIABLE || t.type == TokenType.INDEPENDENT || t.type == TokenType.DEPENDENT || t.type == TokenType.NUMBER) {
                if (next(t, i)) {
                    if (!isExpSplitter(t) && t.type != TokenType.OPARATOR) {
                        insertToken(Token.MULTIPLY, i + 1);
                        i += 2;
                    }
                }
                if (previous(t, i)) {
                    if (!isExpSplitterInRevers(t) && t.type != TokenType.OPARATOR) {
                        insertToken(Token.MULTIPLY, i);
                    }
                }
            }
            i++;
        } while (i < size());
        System.out.println("Done..\n\t" + toLocalString().replaceAll("\n", "\n\t"));

    }

    public void checkPranthesis(TokenList list) throws Exception {
        int p = 0;
        int i = 0;
        System.out.println("Pranthesis check-------");
        System.out.println("\t" + toLocalString().replaceAll("\n", "\n\t"));

        do {
            Token t = new Token(list.tokenAt(i));
            if (t == Token.OPEN_PRANTHESIS) {
                p++;
            } else if (t == Token.CLOSE_PRANTHESIS) {
                p--;
                if (next(t, i)) {
                    if (!isExpSplitter(t)) {
                        list.insertToken(Token.MULTIPLY, i + 1);
                    }
                }
            }
            i++;
        } while (i < list.size());
        if (p > 0) {
            throw new Error("You opened too may pranthesis");
        } else if (p < 0) {
            throw new Error("You closed too may pranthesis");
        }
        System.out.println("Done..\n\t" + toLocalString().replaceAll("\n", "\n\t"));
    }

    public void ignoreZero() {
        System.out.println("Ignoring zero values");
        System.out.println("\t" + toLocalString().replaceAll("\n", "\n\t"));

        int i = 0;
        while (size() - 1 > i) {
            Token t = tokenAt(i);
            if (t.type == TokenType.NUMBER) {
                OUTER:
                if (t.number.doubleValue() == 0.) {
                    if (next(t, i)) {
                        if (t.equalsTo(Token.RAISED)) {
                            int size = ExpressionEvaluator.getParameter(this, i + 2).size();
                            for (int j = 0; j < size + 2; j++) {
                                deleteToken(i);
                            }
                            insertTokens(new TokenList(Token.OPEN_PRANTHESIS, new Token(TokenType.NUMBER, Number.parseNumber(1.)), Token.CLOSE_PRANTHESIS), i);
                            break OUTER;
                        }
                    }
                    if (i < size() - 2) {
                        multiplyBy0ExpressionAt(this, i + 1);
                    }
                    if (i > 0) {
                        i = multiplyBy0ExpressionInReverseAt(this, i - 1);
                    }
                    System.out.println("\tIndex: " + i);
                }
            }
            i++;
        }
//        Token zero=new Token(TokenType.NUMBER, Number.parseNumber(0.));
//        Token one=new Token(TokenType.NUMBER, Number.parseNumber(1.));
//        deleteAll(Token.MINUS,zero);
//        deleteAll(Token.PLUS,zero);
//        deleteAll(zero,Token.MINUS);
//        deleteAll(zero,Token.PLUS);
//        deleteAll(zero,Token.DIVIDE);
//        deleteAll(Token.MULTIPLY,one);
//        deleteAll(Token.MULTIPLY,Token.OPEN_PRANTHESIS,one,Token.CLOSE_PRANTHESIS);
//        deleteAll(Token.OPEN_PRANTHESIS,one,Token.CLOSE_PRANTHESIS,Token.MULTIPLY);
        System.out.println("Done..\n\t" + toLocalString().replaceAll("\n", "\n\t"));
    }

    public static int multiplyBy0ExpressionInReverseAt(TokenList tl, int end) {
        int i = end / 1;
        Token t;
        int p = 0;
        while (-1 <= i) {
            t = tl.tokenAt(i);
            if (t == Token.CLOSE_PRANTHESIS) {
                p++;
            } else if (t == Token.OPEN_PRANTHESIS) {
                p--;
            }
            if (p == 0 && isExpSplitterInRevers(t)) {
                if (t.equalsTo(Token.OPEN_PRANTHESIS)) {
                    tl.deleteToken(i--);
                } else {
                    i++;
                }
                System.out.println("\tBreaking " + t);
                break;
            } else if (Math.abs(p) == 1 && t.equalsTo(Token.OPEN_PRANTHESIS)) {
                i++;
                System.out.println("\tBreaking " + t);
                break;
            }
            tl.deleteToken(i);
            i--;
        }
        return i;
    }

    public static int multiplyBy0ExpressionAt(TokenList tl, int start) {
        int i = start / 1;
        Token t;
        int p = 0;
        while (i < tl.size()) {
            t = tl.tokenAt(i);
            if (t == Token.CLOSE_PRANTHESIS) {
                p++;
            } else if (t == Token.OPEN_PRANTHESIS) {
                p--;
            }
            if (p == 0 && isExpSplitter(t)) {
                if (t.equalsTo(Token.CLOSE_PRANTHESIS)) {
                    tl.deleteToken(i);
                }
                System.out.println("\tBreaking " + t);
                break;
            } else if (Math.abs(p) == 1 && t.equalsTo(Token.CLOSE_PRANTHESIS)) {
                System.out.println("\tBreaking " + t);
                break;
            }
            tl.deleteToken(i);
        }
        return i;
    }

    public static boolean isExpSplitter(Token t) {
        return t.equalsTo(Token.MINUS) || t.equalsTo(Token.PLUS) || t.equalsTo(Token.CLOSE_PRANTHESIS) || t.equalsTo(Token.COMMA);
    }

    public static boolean isExpSplitterInRevers(Token t) {
        return t.equalsTo(Token.RAISED) || t.equalsTo(Token.DIVIDE) || t.equalsTo(Token.MINUS) || t.equalsTo(Token.PLUS) || t.equalsTo(Token.OPEN_PRANTHESIS) || t.equalsTo(Token.COMMA);
    }

    public boolean hasVariables() {
        return !variableList.isEmpty();
    }

    public void assignVariables() {
        for (int i = 0; i < size(); i++) {
            Token t = tokenAt(i);
            if (t.type == TokenType.VARIABLE) {
                replace(i, new Token(TokenType.NUMBER, t.number));
            }
        }
    }

    public void deleteAll(Token... t) {
        for (int i = 0; i < size(); i++) {
            OUTER:
            if (size() - i - 1 >= t.length) {
                for (int j = 0; j < t.length; j++) {
                    if (!tokenAt(i + j).equalsTo(t[j])) {
                        break OUTER;
                    }
                }
                for (Token t1 : t) {
                    deleteToken(i);
                }
            }
        }
    }

    public VariableList getVariableList() {
        return variableList;
    }

    public void setVariableList(VariableList variableList) {
        this.variableList = variableList;
    }

    @Override
    public String toString() {
        String s = "";
        for (Token next : tokenList) {
            s += next.toString();
        }
        return String.format("Expression:%s\nToken Llist:%s", exp, s);
    }

    public String toLocalString() {
        String s = "";
        for (Token next : tokenList) {
            s += next.toLocalString();
        }
        return String.format("Expression:%s\nToken Llist:%s", exp, s);
    }

    public static void main(String[] args) {
        try {
            TokenParser tp = new TokenParser("sin(x)0^(212)+(cosh(x))");
            try {
                tp.parse();
            } catch (Exception ex) {
                Logger.getLogger(TokenParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(tp);
        } catch (Exception ex) {
            Logger.getLogger(TokenParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
