package com.calculate.number;

import java.math.BigDecimal;

public class Variable extends CNumber{
    private String name;

    public Variable(String name) {
        super(BigDecimal.ONE);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Variable(String name,double number) {
        super(number);
        this.name=name;
    }
}
