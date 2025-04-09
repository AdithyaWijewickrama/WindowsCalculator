package com.calculator.programmer;

import com.tokenizing.Token;
import com.tokenizing.TokenList;
import com.tokenizing.TokenType;
import com.calculate.CNumber;
import com.calculate.NumberFormat;
import com.calculate.equation.ExpressionEvaluator;
import static com.calculate.equation.ExpressionEvaluator.scanFor;
import com.calculator.commoncalculator.CommonNumberPanel;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import programmer.Base;

/**
 *
 * @author AW Developer
 */
public abstract class ProgrammerNumberPanel extends CommonNumberPanel {

    private TokenList tokensWithinSplitters = new TokenList();
    private TokenList enter = new TokenList();
    private TokenList show = new TokenList();
    private int pranthesis = 0;

    public ProgrammerNumberPanel(boolean showEquation) {
        super(showEquation);
        setSizeSector(7);
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

    @Override
    public void newKey(char key) {
        try {
            Token t;
            if (Character.isDigit(key)) {
                t = Token.getTokenByName(Character.toString(key));
            } else {
                t = Token.getTokenByKey(key);
            }
//            System.out.println(key);
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
                        setNumberToRadixPanel(currentNumber.number);
                        currentNumber = new Token(TokenType.NUMBER, fx);
                        addHistory(show, fx);
                    }
                } else {
                    parseToken(Token.EQUAL);
                }
            } else if (KeyEvent.VK_DELETE == key) {
                currentNumber = ZEROTOKEN;
                tokenDigits.clear();
                tokenDigits.addToken(ZEROTOKEN);
                setNumber(ZEROTOKEN.number);
                setNumberToRadixPanel(currentNumber.number);
            } else if (KeyEvent.VK_BACK_SPACE == key) {
                if (tokenDigits.size() >= 2) {
                    tokenDigits.deleteToken(tokenDigits.size() - 1);
                } else {
                    tokenDigits.clear();
                    tokenDigits.addToken(ZEROTOKEN);
                }
//                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+getTypedNumber().getNumberString()+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                setNumber(getTypedNumber());
                setNumberToRadixPanel(currentNumber.number);
            } else if (t != null) {
                parseToken(t);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProgrammerNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTextSize();
    }

    @Override
    public void reset() {
//        currentNumber = ZEROTOKEN;
//        show.clear();
//        tokensWithinSplitters.clear();
//        enter.clear();
//        tokenDigits.clear();
//        tokenDigits.addToken(ZEROTOKEN);
    }

    @Override
    public void parseToken(Token token) throws Exception {
        if (null != token.type) {
            switch (token.type) {
                case DIGIT:
                    if (getLastShowedToken() == Token.EQUAL) {
                        show.clear();
                    }
//                    System.out.println(tokenDigits);
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
                    setNumber(currentNumber.number);
                    setNumberToRadixPanel(currentNumber.number);
                    parseToken(new Token(TokenType.NUMBER, new CNumber(number, enteringFormat)));
                    break;
                case NUMBER:
                    currentNumber = token;
                    if (isTokensEmpty()) {
                        tokensWithinSplitters.addToken(token);
                        enter.addToken(token);
                    } else {
                        if (getLastTokenType() == TokenType.OPARATOR) {
                            enter.addToken(token);
                        } else if (getLastTokenType() == TokenType._FUNCTION_) {
                        } else if (getLastTokenType() == TokenType.NUMBER) {
                            enter.deleteToken(enter.size() - 1);
                            tokensWithinSplitters.deleteToken(tokensWithinSplitters.size() - 1);
                            enter.addToken(token);
                        } else if (getLastTokenType() == TokenType.FUNCTION_) {
                            tokensWithinSplitters.clear();
                        } else if (getLastToken() == Token.OPEN_PRANTHESIS) {
                            tokensWithinSplitters.deleteToken(tokensWithinSplitters.size() - 1);
                        } else if (getLastToken() == Token.COMMA) {
                            tokensWithinSplitters.deleteToken(tokensWithinSplitters.size() - 1);
                        }
                        if (getLastTokenType() != TokenType._FUNCTION_) {
                            tokensWithinSplitters.addToken(token);
                        }
                    }
                    break;
                case _FUNCTION_:
                    if (getLastTokenType() == TokenType.FUNCTION_) {
                        deleteLastTokens(show);
                        deleteLastTokens(enter);
                    }
                    if (isTokensEmpty()) {
                        tokensWithinSplitters.addToken(token);
                        tokensWithinSplitters.addToken(Token.OPEN_PRANTHESIS);
                        tokensWithinSplitters.addToken(currentNumber);
                        tokensWithinSplitters.addToken(Token.COMMA);
                        show.addTokens(tokensWithinSplitters);
                        enter.addToken(token);
                    } else {
                        tokensWithinSplitters.insertToken(Token.OPEN_PRANTHESIS, 0);
                        tokensWithinSplitters.insertToken(token, 0);
                        tokensWithinSplitters.addToken(Token.COMMA);
                        show.addTokens(tokensWithinSplitters);
                        enter.addToken(token);
                    }
                    pranthesis++;
                    tokensWithinSplitters = new TokenList();
                    break;
                case OPARATOR:
                    if (getLastTokenType() == TokenType.OPARATOR) {
                        enter.deleteToken(enter.size() - 1);
                        show.deleteToken(show.size() - 1);
                        show.addToken(token);
                        enter.addToken(token);
                    } else if (getLastTokenType() == TokenType.NUMBER) {
                        show.addTokens(tokensWithinSplitters);
                        tokensWithinSplitters = new TokenList();
                        tokensWithinSplitters.addToken(ZEROTOKEN);
                        show.addToken(token);
                        enter.addToken(token);
                    } else if (getLastTokenType() == TokenType.FUNCTION_) {
                        deleteLastTokens(show);
                        show.addTokens(tokensWithinSplitters);
                        show.addToken(token);
                        enter.addToken(token);
                    } else if (getLastTokenType() == TokenType._FUNCTION_) {
                        if (isTokensEmpty()) {
                            tokensWithinSplitters.addToken(currentNumber);
                        }
                        enter.addTokens(tokensWithinSplitters);
                        show.addTokens(tokensWithinSplitters);
                        show.addToken(Token.CLOSE_PRANTHESIS);
                        enter.addToken(Token.CLOSE_PRANTHESIS);
                        show.addToken(token);
                        enter.addToken(token);
                        pranthesis--;
                    } else if (getLastToken() == Token.CLOSE_PRANTHESIS) {
                        show.addToken(token);
                        enter.addToken(token);
                    } else if (getLastToken() == Token.OPEN_PRANTHESIS) {
                        show.addTokens(tokensWithinSplitters);
                        enter.addTokens(tokensWithinSplitters);
                        show.addToken(token);
                        enter.addToken(token);
                    }
                    tokensWithinSplitters = new TokenList();
                    tokenDigits.clear();
                    tokenDigits.addToken(ZEROTOKEN);
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
                    } else if (getLastTokenType() == TokenType._FUNCTION_) {
                        int scan = ExpressionEvaluator.scanFor(show, getLastToken());
                        show.addToken(token);
                        show.addTokens(tokensWithinSplitters.pranthesise());
                        tokensWithinSplitters = new TokenList();
                    }
                    tokensWithinSplitters = tokensWithinSplitters.pranthesise();
                    tokensWithinSplitters.insertToken(token, 0);
                    enter.addToken(token);
                    tokenDigits.clear();
                    tokenDigits.addToken(ZEROTOKEN);
                    break;
                case SYMBOL:
                    if (token == Token.OPEN_PRANTHESIS) {
                        if (getLastShowedTokenType() == TokenType.NUMBER) {
                            show.addToken(Token.MULTIPLY);
                        } else if (getLastShowedToken() == Token.CLOSE_PRANTHESIS) {
                            show.addToken(Token.MULTIPLY);
                        } else if (getLastTokenType() == TokenType._FUNCTION_) {
                        }
                        tokensWithinSplitters = new TokenList();
                        tokenDigits = new TokenList();
                        show.addToken(Token.OPEN_PRANTHESIS);
                        enter.addToken(Token.OPEN_PRANTHESIS);
                        pranthesis++;
                    } else if (token == Token.CLOSE_PRANTHESIS) {
                        if (pranthesis > 0) {
                            pranthesis--;
                            if (null != getLastTokenType()) {
                                switch (getLastTokenType()) {
                                    case OPARATOR:
                                    case _FUNCTION_:
                                        if (isTokensEmpty()) {
                                            tokensWithinSplitters.addToken(currentNumber);
                                        }
                                    case NUMBER:
                                    case FUNCTION_:
                                        show.addTokens(tokensWithinSplitters);
                                        show.addToken(token);
                                        enter.addToken(token);
                                        break;
                                    default:
                                        if (getLastToken() == Token.CLOSE_PRANTHESIS) {
                                            show.addToken(token);
                                        }
                                        break;
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if (!(token.type == TokenType.NUMBER || token.type == TokenType.DIGIT || token == Token.OPEN_PRANTHESIS || pranthesis != 0)) {
            CNumber t = getValue(token.equalsTo(Token.EQUAL));
            if (t != null) {
                currentNumber = new Token(TokenType.NUMBER, t);
//                System.out.println("ANSWER\t" + t.getNumberString());
                setNumber(t);
                setNumberToRadixPanel(currentNumber.number);
            }
            setEquation(show);
            if (Token.EQUAL.equalsTo(token)) {
                addHistory(show, t);
            }
        } else if (token == Token.OPEN_PRANTHESIS) {
            currentNumber = ZEROTOKEN;
            setNumber(currentNumber.number);
            setNumberToRadixPanel(currentNumber.number);
            setEquation(show);
        } else if (pranthesis != 0) {
            setEquation(show);
        }
        if (show.size() > 0) {
            if (show.tokenAt(show.size() - 1).equalsTo(Token.EQUAL)) {
                if (token.equalsTo(Token.EQUAL)) {
                    tokenDigits.clear();
                    tokenDigits.addToken(ZEROTOKEN);
                }
            }
        }
//        System.out.println("[ENTER]" + enter.toLocalString());
//        System.out.println("[TOKENS]" + tokensWithinSplitters.toString());
//        System.out.println("[SHOW]" + show.toLocalString());
    }

    @Override
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
            if (last == Token.COMMA) {
                list.addToken(currentNumber);
            }
            for (int i = 0; i < pranthesis; i++) {
                list.addToken(Token.CLOSE_PRANTHESIS);
            }

            if (equal) {
                switch (last.type) {
                    case OPARATOR:
                    case _FUNCTION_:
                        if (equal) {
                            show.addToken(currentNumber);
                        } else {
                            show.deleteToken(list.size() - 1);
                        }
                        break;
                }
                if (last == Token.COMMA) {
                    show.addToken(currentNumber);
                }
                for (int i = 0; i < pranthesis; i++) {
                    show.addToken(Token.CLOSE_PRANTHESIS);
                }
                show.addToken(Token.EQUAL);
            }
        }
        try {
            num = new ExpressionEvaluator(list).evaluate();
        } catch (Exception ex) {
            error(ex);
            Logger.getLogger(ProgrammerNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    @Override
    public void deleteLastTokens(TokenList t) {
        int scan = ExpressionEvaluator.scanFor(t, TokenType.OPARATOR);
        int len = t.size() - scan - 1;
        for (int i = 0; i < len; i++) {
            t.deleteToken(scan + 1);
        }

    }

    @Override
    public void setNumber(CNumber number) {
        try {
            numberText.setText(number.setNumberFormat(showingFormat).getNumberString());
            setNumberToRadixPanel(number);
        } catch (Exception ex) {
            Logger.getLogger(ProgrammerNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isTokensEmpty() {
        return tokensWithinSplitters.size() == 0;
    }

    public void setRadix(Base radix) {
        enteringFormat = NumberFormat.getNormalNumberFormat(radix);
        showingFormat = NumberFormat.getGroupingNumberFormat(radix);
        setEquation(show);
        setNumber(currentNumber.number);
        setNumberToRadixPanel(currentNumber.number);
        reset();
    }

    public abstract void setNumberToRadixPanel(CNumber number);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ProgrammerNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProgrammerNumberPanel p = new ProgrammerNumberPanel(true) {
            @Override
            public void addHistory(TokenList equation, CNumber answer) {
            }

            @Override
            public void setNumberToRadixPanel(CNumber number) {
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
                    Logger.getLogger(ProgrammerNumberPanel.class.getName()).log(Level.SEVERE, null, ex);
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
