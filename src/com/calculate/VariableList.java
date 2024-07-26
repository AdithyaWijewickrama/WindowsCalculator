package com.calculate;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author AW Developer
 */
public class VariableList {
    HashSet<Variable> variableList;

    public VariableList(HashSet<Variable> variableList) {
        this.variableList = variableList;
    }

    public VariableList() {
        variableList=new HashSet<>();
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
}
