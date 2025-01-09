/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calculate.calculus;

import com.calculate.*;
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
