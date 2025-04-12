package com.calculator.standard;

import com.amath.advacedmath.calculate.CNumber;
import com.amath.advacedmath.calculate.equation.ExpressionEvaluator;
import static com.amath.advacedmath.calculate.equation.ExpressionEvaluator.scanFor;
import com.amath.advacedmath.tokenizing.Token;
import com.amath.advacedmath.tokenizing.TokenList;
import com.amath.advacedmath.tokenizing.TokenType;
import static com.amath.advacedmath.tokenizing.TokenType.DIGIT;
import static com.amath.advacedmath.tokenizing.TokenType.FUNCTION_;
import static com.amath.advacedmath.tokenizing.TokenType._FUNCTION_;
import com.calculator.commoncalculator.CommonNumberPanel;
import com.formdev.flatlaf.FlatDarculaLaf;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public abstract class StandardNumberPanel extends CommonNumberPanel {

    private final TokenList show = new TokenList();
    private final TokenList enter = new TokenList();
    private TokenList tokensWithinSplitters = new TokenList();

    public StandardNumberPanel() {
        super(true);
        setSizeSector(6);
        setZero(tokenDigits);
    }

    public TokenType getLastTokenType() {
        return getLastToken() == null ? null : getLastToken().type;
    }

    public TokenType getLastShowedTokenType() {
        return getLastShowedToken() == null ? null : getLastShowedToken().type;
    }

    public Token getLastShowedToken() {
        return show.size() == 0 ? null : show.tokenAt(show.size() - 1);
    }

    public Token getLastToken() {
        return enter.size() == 0 ? null : enter.tokenAt(enter.size() - 1);
    }

    public void addToken(Token token) {
        tokensWithinSplitters.addToken(token);
    }

    public CNumber getValue(boolean equal) {
        TokenList list = show.getTokenListCopy();
        if (list.size() <= 0) {
            return null;
        }
        Token last = list.tokenAt(list.size() - 1);
        CNumber num = null;
        if (last != null) {
            switch (last.type) {
                case OPARATOR:
                case _FUNCTION_:
                    if (equal) {
                        list.addToken(currentNumber);
                    } else {
                        list.deleteToken(list.size() - 1);
                    }
                    break;
            }

            if (equal) {
                switch (last.type) {
                    case OPARATOR:
                        if (equal) {
                            show.addToken(currentNumber);
                        } else {
                            show.deleteToken(list.size() - 1);
                        }
                        break;
                }
                show.addToken(Token.EQUAL);
            }
        }
        try {
            num = new ExpressionEvaluator(list).evaluate();
        } catch (Exception ex) {
            error(ex);
            Logger.getLogger(StandardNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    public void deleteLastTokens(TokenList t) {
        int scan = ExpressionEvaluator.scanFor(t, TokenType.OPARATOR);
        int len = t.size() - scan - 1;
        for (int i = 0; i < len; i++) {
            t.deleteToken(scan + 1);
        }

    }

    private boolean isTokensEmpty() {
        return tokensWithinSplitters.size() == 0;
    }

    @Override
    public void newKey(char key) {
        try {
            Token t;
            if (Character.isDigit(key)) {
                t = Token.getTokenByName(Character.toString(key));
            } else {
                t = Token.getTokenByKey(key);
            }
            if (KeyEvent.VK_ENTER == key) {
                if (getLastShowedToken() == Token.EQUAL) {
                    int i = scanFor(show, TokenType.OPARATOR);
                    if (i != -1) {
                        Token o = show.tokenAt(i);
                        CNumber fx = new ExpressionEvaluator(show.split(0, show.size() - 1)).evaluate();
                        CNumber gx = new ExpressionEvaluator(show.split(i + 1, show.size() - 1)).evaluate();
                        show.clear();
                        show.addToken(new Token(TokenType.NUMBER, fx));
                        show.addToken(o);
                        show.addToken(new Token(TokenType.NUMBER, gx));
                        show.addToken(Token.EQUAL);
                        setEquation(show);
                        setNumber(fx);
                        currentNumber = new Token(TokenType.NUMBER, fx);
                        addHistory(show, currentNumber.number);
                    }
                } else {
                    parseToken(Token.EQUAL);
                }
            } else if (KeyEvent.VK_DELETE == key) {
                currentNumber = ZEROTOKEN;
                tokenDigits.clear();
                tokenDigits.addToken(ZEROTOKEN);
                setNumber(ZEROTOKEN.number);
            } else if (KeyEvent.VK_BACK_SPACE == key) {
                if (tokenDigits.size() >= 2) {
                    tokenDigits.deleteToken(tokenDigits.size() - 1);
                } else {
                    tokenDigits.clear();
                    tokenDigits.addToken(ZEROTOKEN);
                }
                setNumber(getTypedNumber());
            } else if (t != null) {
                if (Token.PLUS_OR_MINUS.equalsTo(t)) {
                    if (!ZEROTOKEN.equalsTo(tokenDigits.tokenAt(0))) {
                        if (Token.MINUS.equalsTo(tokenDigits.tokenAt(0))) {
                            tokenDigits.deleteToken(0);
                        } else {
                            tokenDigits.insertToken(Token.MINUS, 0);
                        }
                        CNumber num = getNumber().multiply(CNumber.parseNumber(-1));
                        setNumber(num);
                        currentNumber = new Token(TokenType.NUMBER, num);
                        parseToken(currentNumber);
                    }
                } else {
                    parseToken(t);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(StandardNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTextSize();
    }

    @Override
    public void parseToken(Token token) throws Exception {
        if (null != token.type) {
            if (!Token.EQUAL.equalsTo(token) && getLastShowedToken() == Token.EQUAL) {
                show.clear();
            }
            switch (token.type) {
                case DIGIT:

                    if (token.equalsTo(Token.DOT)) {
                        if (tokenDigits.find(token) != -1) {
                            break;
                        }
                        if (tokenDigits.size() == 0) {
                            tokenDigits.addToken(ZEROTOKEN);
                            tokenDigits.addToken(token);
                        } else {
                            tokenDigits.addToken(token);
                        }
                    }
                    if (tokenDigits.size() == 1 && tokenDigits.tokenAt(0).number.doubleValue() == 0) {
                        if (token == Token.DOT) {
                            tokenDigits.addToken(token);
                        } else {
                            tokenDigits.deleteToken(0);
                            tokenDigits.addToken(token);
                        }
                    } else if (token != Token.DOT) {
                        tokenDigits.addToken(token);
                    }
                    String number = "";
                    for (Token t : tokenDigits.getTokenList()) {
                        number += t.name;
                    }
                    currentNumber = new Token(TokenType.NUMBER, new CNumber(number, enteringFormat));
                    System.out.println(currentNumber.number.doubleValue());
                    setNumber(currentNumber.number);
                    parseToken(currentNumber);
                    break;
                case NUMBER:
                    currentNumber = token;
                    if (isTokensEmpty()) {
                        tokensWithinSplitters.addToken(token);
                        enter.addToken(token);
                    } else {
                        if (getLastTokenType() == TokenType.OPARATOR) {
                            enter.addToken(token);
                        } else if (getLastTokenType() == TokenType.NUMBER) {
                            enter.deleteToken(enter.size() - 1);
                            tokensWithinSplitters.deleteToken(tokensWithinSplitters.size() - 1);
                            enter.addToken(token);
                            tokensWithinSplitters.addToken(token);
                        } else if (getLastTokenType() == TokenType.FUNCTION_) {
                            tokensWithinSplitters.clear();
                        }
                    }
                    break;
                case OPARATOR:
                    if (getLastTokenType() == TokenType.OPARATOR) {
                        show.addTokens(tokensWithinSplitters);
                        CNumber value = getValue(false);
                        show.clear();
                        show.addToken(new Token(TokenType.NUMBER, value));
                        show.addToken(token);
                        enter.clear();
                        enter.addToken(new Token(TokenType.NUMBER, value));
                        enter.addToken(token);
                        tokenDigits.clear();
                        setEquation(show);
                        setNumber(value);
                    } else if (getLastTokenType() == TokenType.NUMBER) {
                        show.addTokens(tokensWithinSplitters);
                        tokensWithinSplitters.clear();
                        show.addToken(token);
                        enter.addToken(token);
                    } else if (getLastTokenType() == TokenType.FUNCTION_) {
                        deleteLastTokens(show);
                        show.addTokens(tokensWithinSplitters);
                        show.addToken(token);
                        enter.addToken(token);
                    }
                    tokensWithinSplitters.clear();
                    setZero(tokenDigits);
                    break;
                case FUNCTION_:
                    if (isTokensEmpty()) {
                        tokensWithinSplitters.addToken(currentNumber);
                    }
                    if (getLastTokenType() == null) {
                        show.addToken(token);
                        show.addTokens(tokensWithinSplitters.pranthesise());
                    }
                    if (getLastTokenType() == TokenType.OPARATOR) {
                        show.addToken(token);
                        show.addTokens(tokensWithinSplitters.pranthesise());
                    } else if (getLastTokenType() == TokenType.NUMBER) {
                        show.addToken(token);
                        show.addTokens(tokensWithinSplitters.pranthesise());
                    } else if (getLastTokenType() == TokenType.FUNCTION_) {
                        deleteLastTokens(show);
                        deleteLastTokens(enter);
                        show.addToken(token);
                        show.addTokens(tokensWithinSplitters.pranthesise());
                    }
                    tokensWithinSplitters = tokensWithinSplitters.pranthesise();
                    tokensWithinSplitters.insertToken(token, 0);
                    enter.addToken(token);
                    tokenDigits.clear();
                    tokenDigits.addToken(ZEROTOKEN);
                    break;
                default:
                    break;
            }
        }
        if (!(token.type == TokenType.NUMBER || token.type == TokenType.DIGIT)) {
            CNumber t = getValue(token.equalsTo(Token.EQUAL));
            if (t != null) {
                currentNumber = new Token(TokenType.NUMBER, t);
                System.out.println("ANSWER\t" + t.getNumberString());
                setNumber(t);
            }
            setEquation(show);
            if (Token.EQUAL.equalsTo(token)) {
                addHistory(show, t);
            }
        }

        if (show.size() > 0) {
            if (show.tokenAt(show.size() - 1).equalsTo(Token.EQUAL)) {
                if (token.equalsTo(Token.EQUAL)) {
                    tokenDigits.clear();
                    tokenDigits.addToken(ZEROTOKEN);
                }
            }
        }

        System.out.println(
                "[ENTER]" + enter.toLocalString());
        System.out.println(
                "[TOKENS]" + tokensWithinSplitters.toString());
        System.out.println(
                "[SHOW]" + show.toLocalString());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(StandardNumberPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        StandardNumberPanel p = new StandardNumberPanel() {
            @Override
            public void addHistory(TokenList equation, CNumber answer) {
                System.out.println("History" + equation.toLocalString());
                System.out.println("Answer" + equation);
            }
        };
        JFrame frame = new JFrame("Common number field");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new javax.swing.BoxLayout(frame.getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(p);
        frame.pack();
        new Thread(() -> {
            double d = 0;
            while (d < 300) {
                d++;
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(StandardNumberPanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.exit(0);
        }).start();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

}
