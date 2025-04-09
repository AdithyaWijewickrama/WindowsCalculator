/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.tokenizing;

import com.calculate.number.CNumber;

/**
 *
 * @author AW Developer
 */
public class Token {

    /**
     * CONSTANT
     */
    public static Token PI = new Token(TokenType.NUMBER, "π",'π', CNumber.parseNumber(Math.PI));
    /**
     * CONSTANT--eēėê℮
     */
    public static Token e = new Token(TokenType.NUMBER, "℮",'℮', CNumber.parseNumber(Math.E));
    /**
     * VARIABLE
     */
    public static Token x = new Token(TokenType.INDEPENDENT, "x", 'x');
    /**
     * VARIABLE
     */
    public static Token y = new Token(TokenType.DEPENDENT, "y", 'y');

    //NUMBERS×{}÷•¹
    /**
     * DIGIT
     */
    public static Token ZERO = new Token(TokenType.DIGIT, "0", '0', CNumber.parseNumber(0));
    /**
     * DIGIT
     */
    public static Token ONE = new Token(TokenType.DIGIT, "1", '1', CNumber.parseNumber(1));
    /**
     * DIGIT
     */
    public static Token TWO = new Token(TokenType.DIGIT, "2", '2', CNumber.parseNumber(2));
    /**
     * DIGIT
     */
    public static Token THREE = new Token(TokenType.DIGIT, "3", '3', CNumber.parseNumber(3));
    /**
     * DIGIT
     */
    public static Token FOUR = new Token(TokenType.DIGIT, "4", '4', CNumber.parseNumber(4));
    /**
     * DIGIT
     */
    public static Token FIVE = new Token(TokenType.DIGIT, "5", '5', CNumber.parseNumber(5));
    /**
     * DIGIT
     */
    public static Token SIX = new Token(TokenType.DIGIT, "6", '6', CNumber.parseNumber(6));
    /**
     * DIGIT
     */
    public static Token SEVEN = new Token(TokenType.DIGIT, "7", '7', CNumber.parseNumber(7));
    /**
     * DIGIT
     */
    public static Token EIGHT = new Token(TokenType.DIGIT, "8", '8', CNumber.parseNumber(8));
    /**
     * DIGIT
     */
    public static Token NINE = new Token(TokenType.DIGIT, "9", '9', CNumber.parseNumber(9));
    /**
     * DIGIT
     */
    public static Token DOT = new Token(TokenType.DIGIT, ".", '.');
    /**
     * DIGIT
     */
    public static Token A = new Token(TokenType.DIGIT, "A", 'A');
    /**
     * DIGIT
     */
    public static Token B = new Token(TokenType.DIGIT, "B", 'B');
    /**
     * DIGIT
     */
    public static Token C = new Token(TokenType.DIGIT, "C", 'C');
    /**
     * DIGIT
     */
    public static Token D = new Token(TokenType.DIGIT, "D", 'D');
    /**
     * DIGIT
     */
    public static Token E = new Token(TokenType.DIGIT, "E", 'E');
    /**
     * DIGIT
     */
    public static Token F = new Token(TokenType.DIGIT, "F", 'F');
    //OPARATORS
    /**
     * OPARATOR
     */
    public static Token PLUS = new Token(TokenType.OPARATOR, "+", '+');
    /**
     * OPARATOR
     */
    public static Token MINUS = new Token(TokenType.OPARATOR, "-", '-');
    /**
     * OPARATOR
     */
    public static Token DIVIDE = new Token(TokenType.OPARATOR, "/", '/');
    /**
     * OPARATOR
     */
    public static Token MULTIPLY = new Token(TokenType.OPARATOR, "*", '*');
    /**
     * OPARATOR
     */
    public static Token RAISED = new Token(TokenType.OPARATOR, "^", '^');
    /**
     * SYMBOL
     */
    public static Token OPEN_PRANTHESIS = new Token(TokenType.SYMBOL, "(", '(');
    /**
     * SYMBOL
     */
    public static Token CLOSE_PRANTHESIS = new Token(TokenType.SYMBOL, ")", ')');
    /**
     * SYMBOL
     */
    public static Token COMMA = new Token(TokenType.SYMBOL, ",", ',');
    /**
     * SYMBOL
     */
    public static Token PLUS_OR_MINUS = new Token(TokenType.SYMBOL, "±", '{');
    /**
     * SYMBOL
     */
    public static Token EQUAL = new Token(TokenType.SYMBOL, "=", '=');
    //FUNCTIONS
    //need one value to do the math
    /**
     * FUNCTION_
     */
    public static Token RECIPROCAL = new Token(TokenType.FUNCTION_, "1/", 'r');
    /**
     * FUNCTION_
     */
    public static Token SQUARED = new Token(TokenType.FUNCTION_, "sqr", 'q');
    /**
     * FUNCTION_
     */
    public static Token SQUREROOT = new Token(TokenType.FUNCTION_, "√", '@');
    /**
     * FUNCTION_
     */
    public static Token ABS = new Token(TokenType.FUNCTION_, "abs", 'a');
    /**
     * FUNCTION_
     */
    public static Token FLOOR = new Token(TokenType.FUNCTION_, "floor", 'f');
    /**
     * FUNCTION_
     */
    public static Token CEILING = new Token(TokenType.FUNCTION_, "ceil",'\'');
    /**
     * FUNCTION_
     */
    public static Token FACTORIAL = new Token(TokenType.FUNCTION_, "fact", '!');
    /**
     * FUNCTION_
     */
    public static Token TENRAISED = new Token(TokenType.FUNCTION_, "10^", 'x');
    /**
     * FUNCTION_
     */
    public static Token TWORAISED = new Token(TokenType.FUNCTION_, "2^", 'g');
    /**
     * FUNCTION_
     */
    public static Token CUBE = new Token(TokenType.FUNCTION_, "cube", '#');
    /**
     * FUNCTION_
     */
    public static Token CUBEROOT = new Token(TokenType.FUNCTION_, "cuberoot", 'b');
    /**
     * FUNCTION_
     */
    public static Token LOG10 = new Token(TokenType.FUNCTION_, "log10", 'l');
    /**
     * FUNCTION_
     */
    public static Token LN = new Token(TokenType.FUNCTION_, "ln", 'n');
    /**
     * FUNCTION_
     */
    public static Token DEGREES = new Token(TokenType.FUNCTION_, "degrees", 'O');
    /**
     * FUNCTION_
     */
    public static Token eRAISED = new Token(TokenType.FUNCTION_, "e^", 'e');
    /**
     * FUNCTION_
     */
    public static Token SIN = new Token(TokenType.FUNCTION_, "sin", 's');
    /**
     * FUNCTION_
     */
    public static Token COS = new Token(TokenType.FUNCTION_, "cos", 'c');
    /**
     * FUNCTION_
     */
    public static Token TAN = new Token(TokenType.FUNCTION_, "tan", 't');
    /**
     * FUNCTION_
     */
    public static Token SEC = new Token(TokenType.FUNCTION_, "sec", 'u');
    /**
     * FUNCTION_
     */
    public static Token CSC = new Token(TokenType.FUNCTION_, "csc", 'i');
    /**
     * FUNCTION_
     */
    public static Token COT = new Token(TokenType.FUNCTION_, "cot", 'j');
    /**
     * FUNCTION_
     */
    public static Token ASIN = new Token(TokenType.FUNCTION_, "asin", 'S');
    /**
     * FUNCTION_
     */
    public static Token ACOS = new Token(TokenType.FUNCTION_, "acos", 'V');
    /**
     * FUNCTION_
     */
    public static Token ATAN = new Token(TokenType.FUNCTION_, "atan", 'T');
    /**
     * FUNCTION_
     */
    public static Token ASEC = new Token(TokenType.FUNCTION_, "asec", 'U');
    /**
     * FUNCTION_
     */
    public static Token ACSC = new Token(TokenType.FUNCTION_, "acsc", 'I');
    /**
     * FUNCTION_
     */
    public static Token ACOT = new Token(TokenType.FUNCTION_, "acot", 'J');
    /**
     * FUNCTION_
     */
    static int i=0;
    public static Token SINH = new Token(TokenType.FUNCTION_, "sinh",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token COSH = new Token(TokenType.FUNCTION_, "cosh",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token TANH = new Token(TokenType.FUNCTION_, "tanh",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token SECH = new Token(TokenType.FUNCTION_, "sech",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token CSCH = new Token(TokenType.FUNCTION_, "csch",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token COTH = new Token(TokenType.FUNCTION_, "coth",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token ASINH = new Token(TokenType.FUNCTION_, "asinh",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token ACOSH = new Token(TokenType.FUNCTION_, "acosh",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token ATANH = new Token(TokenType.FUNCTION_, "atanh",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token ASECH = new Token(TokenType.FUNCTION_, "asech",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token ACSCH = new Token(TokenType.FUNCTION_, "acsch",(char)i++);
    /**
     * FUNCTION_
     */
    public static Token ACOTH = new Token(TokenType.FUNCTION_, "acoth",(char)i++);
    //need two values to do the math
    /**
     * _FUNCTION_
     */
    public static Token MODULO = new Token(TokenType._FUNCTION_, "Mod", '%');
    /**
     * _FUNCTION_
     */
    public static Token YROOT = new Token(TokenType._FUNCTION_, "yroot", 'Y');
    /**
     * _FUNCTION_
     */
    public static Token LOGBASEY = new Token(TokenType._FUNCTION_, "log", 'L');
    /**
     * _FUNCTION_
     */
    public static Token AND = new Token(TokenType._FUNCTION_, "AND", '&');
    /**
     * _FUNCTION_
     */
    public static Token OR = new Token(TokenType._FUNCTION_, "OR", '|');
    /**
     * _FUNCTION_
     */
    public static Token NOT = new Token(TokenType._FUNCTION_, "NOT", '!');
    /**
     * _FUNCTION_
     */
    public static Token NAND = new Token(TokenType._FUNCTION_, "AND", '&');
    /**
     * _FUNCTION_
     */
    public static Token XOR = new Token(TokenType._FUNCTION_, "XOR", ']');
    /**
     * _FUNCTION_
     */
    public static Token NOR = new Token(TokenType._FUNCTION_, "NOR", '[');
    /**
     * _FUNCTION_
     */
    public static Token LSH = new Token(TokenType._FUNCTION_, "Lsh", '?');
    /**
     * _FUNCTION_
     */
    public static Token RSH = new Token(TokenType._FUNCTION_, "Rsh", ':');

    public TokenType type;
    public String name;
    public char key;
    public CNumber number;

    /**
     * For constants
     *
     * @param type
     * @param name
     * @param n
     */
    public Token(TokenType type, String name, CNumber n) {
        this.type = type;
        this.name = name;
        number = n;
    }

    public Token(TokenType type, String name, char key) {
        this.type = type;
        this.name = name;
        this.key = key;
    }

    public Token(TokenType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * For numbers
     *
     * @param type
     * @param n
     */
    public Token(TokenType type, CNumber n) {
        this.type = type;
        number = n;
    }

    public Token(Token t) {
        name = t.name;
        type = t.type;
        number = t.number;
        key = t.key;
    }

    public Token(TokenType type, String name, char key, CNumber number) {
        this.name = name;
        this.type = type;
        this.key = key;
        this.number = number;
    }
    
    public Token(CNumber n){
        this(TokenType.NUMBER, n);
    }

    public void updateToken(TokenType type, String name, char key, CNumber number) {
        this.name = name;
        this.type = type;
        this.key = key;
        this.number = number;
    }

    public static Token[] values() {
        return new Token[]{
            PI, e, x, y,
            ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DOT,
            PLUS, MINUS, DIVIDE, MULTIPLY, RAISED, OPEN_PRANTHESIS, CLOSE_PRANTHESIS, COMMA, EQUAL, PLUS_OR_MINUS,
            RECIPROCAL, SQUARED, SQUREROOT, ABS, FLOOR, CEILING, FACTORIAL, TENRAISED, TWORAISED, CUBE, CUBEROOT, LOG10, LN, eRAISED,
            SIN, COS, TAN, SEC, CSC, COT, ASIN, ACOS, ATAN, ASEC, ACSC, ACOT, SINH, COSH, TANH, SECH, CSCH, COTH, ASINH, ACOSH, ATANH, ASECH, ACSCH, ACOTH,
            MODULO, YROOT, LOGBASEY};
    }

    public static TokenList getTokensByTokenType(TokenType type) {
        TokenList list = null;
        for (Token token : values()) {
            if (token.type == type) {
                if (list == null) {
                    list = new TokenList();
                }
                list.addToken(token);
            }
        }
        return list;
    }

    public static Token getTokenByKey(char key) {
        for (Token t : values()) {
            if (t.key == key) {
                return t;
            }
        }
        return null;
    }

    public static Token getTokenByName(String name) {
        for (Token t : values()) {
            if (t.name == null) {
                continue;
            }
            if (t.name.equals(name)) {
                return t;
            }
        }
        return null;
    }

    public String toString() {
        switch (type) {
            case NUMBER:
                return String.format("<NUMBER-%s>", number.getNumberString());
            default:
                return String.format("<%s-%s>", type.toString(), name);
        }
    }

    public boolean equalsTo(Token t) {
        if (t == null) {
            return false;
        }
        if (t.type == TokenType.NUMBER || t.type == TokenType.DIGIT) {
            if (".".equals(name)) {
                if (".".equals(t.name)) {
                    return true;
                }
            }
            if (t.type != type) {
                return false;
            }
            if (t.number == null) {
                if (number != null) {
                    return false;
                }
            }
            return t.type == type && t.number.equalsTo(number);
        }
        return t.name.equals(name) && t.type == type;
    }

    public String toLocalString() {
        switch (type) {
            case NUMBER:
                return String.format("%s", number.getNumberString());
            default:
                return String.format("%s", name);
        }
    }
}
