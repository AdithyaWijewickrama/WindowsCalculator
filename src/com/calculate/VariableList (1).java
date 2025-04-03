package com.calculate;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author AW Developer
 */
public class VariableList {
    ArrayList<Variable> variableList;

    public VariableList(ArrayList<Variable> variableList) {
        this.variableList = variableList;
    }

    public VariableList() {
        variableList=new ArrayList<>();
    }
    
    public boolean isEmpty(){
        return variableList.isEmpty();
    }
    
    public boolean add(Variable v){
        for (Iterator<Variable> var = variableList.iterator(); var.hasNext();) {
            if(var.next().getName().equals(v.getName())){
                return false;
            }
        }
        return variableList.add(v);
    }
    public Variable get(String name){
        for (Variable v : variableList) {
            if(v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }
    public void setValue(String name,CNumber value){
        get(name).setNumber(value);
    }
}
