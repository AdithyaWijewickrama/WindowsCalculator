package com.calculate.equation;

import com.Tokenizing.Token;
import com.Tokenizing.TokenList;
import com.Tokenizing.TokenType;
import com.calculate.Number;

/**
 *
 * @author AW Developer
 */
public abstract class Calculate {
    protected Number value1;
    protected Number value2;
    private TokenType type;

    public Calculate(Number value1, TokenType type, Number value2) {
        this.value1 = value1;
        this.value2 = value2;
        this.type = type;
    }
    public Calculate(Number value, TokenType type) {
        this.value1 = value;
        this.type = type;
    }

    public Number getValue1() {
        return value1;
    }

    public void setValue1(Number value1) {
        this.value1 = value1;
    }

    public Number getValue2() {
        return value2;
    }

    public void setValue2(Number value2){
        this.value2 = value2;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
    
    public abstract Number doTheMath();
    
    public void makeError(String msg){
        
    }
}
