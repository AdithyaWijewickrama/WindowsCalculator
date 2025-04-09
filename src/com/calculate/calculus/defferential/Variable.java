package com.calculate.calculus.defferential;

import com.calculate.CNumber;
import java.math.BigDecimal;

/**
 *
 * @author AW Developer
 */
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
        super(new BigDecimal(number));
        this.name=name;
    }
    
}
