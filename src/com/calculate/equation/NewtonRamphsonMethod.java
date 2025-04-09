package com.calculate.equation;

import com.calculate.calculus.defferential.Differentiator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public class NewtonRamphsonMethod {

    private final String fx;
    private final ExpressionEvaluator _fx;
    private final Differentiator ddx_fx;

    public NewtonRamphsonMethod(String fx) throws Exception {
        this.fx = fx;
        _fx = new ExpressionEvaluator(fx);
        ddx_fx = new Differentiator(fx);
        ddx_fx.differentiate(2);
    }

    public Number findRoot(double init) throws Exception {
        return init - (_fx.evaluateAt(com.calculate.CNumber.parseNumber(init)).doubleValue() / ddx_fx.evaluateAt(com.calculate.CNumber.parseNumber(init)).doubleValue());
    }

    public static void main(String[] args) {
        try {
            double x=1;
            NewtonRamphsonMethod nrp = new NewtonRamphsonMethod("x^3-x^2-1");
            for (int i = 0; i < 10; i++) {
                x = nrp.findRoot(x).doubleValue();
                System.out.println(x);
            }
        } catch (Exception ex) {
            Logger.getLogger(NewtonRamphsonMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
