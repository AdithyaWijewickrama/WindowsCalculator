package com.Tokenizing;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author AW Developer
 */
public class TokenList {

    protected ArrayList<Token> tokenList;

    public TokenList() {
        tokenList = new ArrayList<>();
    }

    public boolean hasDependent() {
        for (Iterator<Token> tl = iterator(); tl.hasNext();) {
            if (tl.next().type == TokenType.DEPENDENT) {
                return true;
            }
        }
        return false;
    }

    public boolean hasIndependent() {
        for (Iterator<Token> tl = iterator(); tl.hasNext();) {
            if (tl.next().type == TokenType.INDEPENDENT) {
                return true;
            }
        }
        return false;
    }

    public Token next(int i) {
        return (size() - 1 > i) ? tokenAt(i + 1) : null;
    }

    public Token previous(int i) {
        return i > 0 ? tokenAt(i - 1) : null;
    }

    public boolean next(Token t, int i) {
        Token t1 = next(i);
        if (t1 == null) {
            return false;
        }
        t.updateToken(t1.type,t1.name, t1.key, t1.number);
        return true;
    }

    public boolean previous(Token t, int i) {
        Token t1 = previous(i);
        if (t1 == null) {
            return false;
        }
        t.updateToken(t1.type,t1.name, t1.key, t1.number);
        return true;
    }

    public TokenList(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }
    
    public TokenList(Token ...t){
        this();
        for (Token token : t) {
            tokenList.add(token);
        }
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    public TokenList getTokenListCopy() {
        ArrayList<Token> t = new ArrayList<>();
        for (Token token : tokenList) {
            t.add(token);
        }
        return new TokenList(t);
    }

    public void setTokenList(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public void addToken(Token t) {
        tokenList.add(t);
    }

    public static TokenList pranthesiseThis(TokenList tl) {
        tl.insertToken(Token.OPEN_PRANTHESIS, 0);
        tl.addToken(Token.CLOSE_PRANTHESIS);
        return tl;
    }

    public TokenList pranthesise() {
        TokenList t = getTokenListCopy();
        t.insertToken(Token.OPEN_PRANTHESIS, 0);
        t.addToken(Token.CLOSE_PRANTHESIS);
        return t;
    }

    public TokenList reciprocal() {
        TokenList t = getTokenListCopy();
        t = t.pranthesise();
        t.insertToken(Token.RECIPROCAL, 0);
        return t;
    }

    public static TokenList reciprocalThis(TokenList t) {
        t.pranthesise();
        t.insertToken(Token.RECIPROCAL, 0);
        return t;
    }

    public void addTokens(TokenList tl) {
        if(tl==null)return;
        for (Iterator<Token> iterator = tl.iterator(); iterator.hasNext();) {
            Token t = iterator.next();
            tokenList.add(t);
        }
    }

    public Token tokenAt(int i) {
        return tokenList.get(i);
    }
    
    public int find(Token token){
        for (int i=0;i<size();i++) {
            Token t=tokenAt(i);
            if(t.equals(token))return i;
        }
        return -1;
    }

    public void clear() {
        int size = tokenList.size();
        for (int i = 0; i < size; i++) {
            tokenList.remove(0);
        }
    }

    public int size() {
        return tokenList.size();
    }

    public void deleteToken(int i) {
        System.out.println("\t[TOKEN DELETED] " + tokenAt(i));
        tokenList.remove(i);
    }

    public void replace(int i, Token t) {
        deleteToken(i);
        insertToken(t, i);
    }

    public TokenList split(int start, int end) {
        TokenList tl = new TokenList();
        for (int i = start; i < end; i++) {
            tl.addToken(tokenAt(i));
        }
        return tl;
    }

    public void insertToken(Token t, int i) {
        tokenList.add(i, t);
    }

    public void insertTokens(TokenList t, int i) {
        for (int j = t.size() - 1; j >= 0; j--) {
            tokenList.add(i, t.tokenAt(j));
        }
    }

    public Iterator<Token> iterator() {
        return tokenList.iterator();
    }

    public TokenList reverseIterator() {
        TokenList tl = new TokenList();
        for (int i = tokenList.size() - 1; i >= 0; i--) {
            tl.addToken(tokenList.get(i));
        }
        return tl;
    }

    @Override
    public String toString() {
        String s = "";
        for (Token next : tokenList) {
            s += next.toString();
        }
        return s;
    }

    public String toLocalString() {
        String s = "";
        for (Token next : tokenList) {
            s += next.toLocalString();
        }
        return s;
    }

    public static void main(String[] args) {
        TokenList t1 = new TokenList();
        t1.addToken(Token.x);
        t1.addToken(Token.PLUS);
        t1.addToken(Token.ONE);
        Token t = new Token(TokenType.DIGIT, "jf");
        t1.previous(t, 1);
        System.out.println(t);
    }
}
