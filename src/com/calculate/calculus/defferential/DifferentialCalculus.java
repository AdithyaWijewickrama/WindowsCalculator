package com.calculate.calculus.defferential;

import com.tokenizing.TokenList;
import com.tokenizing.TokenParser;
import com.tokenizing.TokenType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public abstract class DifferentialCalculus {

    protected TokenList fx;
    protected TokenList gx;
    protected TokenParser parser;
    private TokenType type;

    public DifferentialCalculus(TokenList fx, TokenType type, TokenList gx) {
        this.fx = fx;
        this.gx = gx;
        this.type = type;
    }

    public DifferentialCalculus(TokenList fx, TokenType type) {
        System.out.println(type.name()+"++++++++++++++++");
        System.out.println(type.name()+" "+fx);
        this.fx = fx;
        this.type = type;
    }

    public TokenList getValue1() {
        return fx;
    }

    public void setValue1(TokenList value1) {
        this.fx = value1;
    }

    public TokenList getValue2() {
        return gx;
    }

    public void setValue2(TokenList value2) {
        this.gx = value2;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public abstract TokenList doTheMath();
    //Override

    public void makeError(String msg) {
        System.err.println(msg);
    }

    void makeError(Exception ex) {
        Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex);
    }
}
