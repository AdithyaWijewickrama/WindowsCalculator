package com.calculator.graphing;

import com.calculate.VariableList;
import com.calculate.equation.Calculate;
import com.calculate.equation.ExpressionEvaluator;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import com.calculate.Number;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public class BasicFunction {

    protected String functionString;
    protected Calculate function;
    protected ExpressionEvaluator functionExpresion;
    protected String varient;
    protected Color color = SingleFunctionPanel.colorFunctions.get(0);
    protected boolean visibility = true;
    protected Stroke stroke = new BasicStroke(3.46566f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
    protected VariableList variables;

    public BasicFunction(String exp, String varient) {
        this.functionString = exp;
        this.varient = varient;
        try {
            this.functionExpresion=new ExpressionEvaluator(exp);
        } catch (Exception ex) {
            Logger.getLogger(BasicFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BasicFunction(String function, String varient, VariableList variables) {
        this(function, varient);
        this.variables = variables;
    }

    public BasicFunction(String function, String varient, Color color, Stroke stroke, VariableList variables) {
        this(function, varient);
        this.variables = variables;
        this.color = color;
        this.stroke = stroke;
    }

    public String getFunction() {
        return functionString;
    }

    public void setFunction(String function) {
        this.functionString = function;
    }

    public ExpressionEvaluator getFunctionExpresion() {
        return functionExpresion;
    }

    public void setFunctionExpresion(ExpressionEvaluator functionExpresion) {
        this.functionExpresion = functionExpresion;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public boolean isVisible() {
        return visibility;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public void setStroke(char s) {
        float[] dash = {10f, 10f};
        switch (s) {
            case '_':
                setStroke(new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
                break;
            case '-':
                setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0));
                break;
            case '.':
                setStroke(new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
                break;
        }
    }

    public VariableList getVariables() {
        return variables;
    }

    public void setVariableValue(String chr, Number value) {
        if (variables.get(chr)!= null) {
            variables.setValue(chr, value);
            assignVariableValues();
        }
    }

    public void assignVariableValues() {
        if (function != null) {
            functionExpresion.setVariableList(variables);
        }
    }

    public Number evaluateAt(Number x) {
        return functionExpresion.evaluateAt(x);
    }

    public void setVariables(VariableList variables) {
        this.variables = variables;
    }
}
