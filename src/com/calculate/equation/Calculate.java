package com.calculate.equation;

import com.tokenizing.TokenType;
import com.calculate.number.CNumber;

public abstract class Calculate {
    protected Calculate value1;
    protected Calculate value2;
    private TokenType type;

    public Calculate(Calculate value1, TokenType type, Calculate value2) {
        this.value1 = value1;
        this.value2 = value2;
        this.type = type;
    }
    public Calculate(Calculate value, TokenType type) {
        this.value1 = value;
        this.type = type;
    }

    public Calculate getValue1() {
        return value1;
    }

    public void setValue1(Calculate value1) {
        this.value1 = value1;
    }

    public Calculate getValue2() {
        return value2;
    }

    public void setValue2(Calculate value2){
        this.value2 = value2;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
    
    public abstract CNumber doTheMath();
    
    public void makeError(String msg){
        
    }
}
