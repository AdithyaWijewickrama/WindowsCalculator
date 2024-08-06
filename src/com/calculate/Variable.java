/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calculate;

/**
 *
 * @author AW Developer
 */
public class Variable extends Number{
    private String name;

    public Variable(String name) {
        super(1.);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Variable(String name,Double number) {
        super(number);
        this.name=name;
    }
}
