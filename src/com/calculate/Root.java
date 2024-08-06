package com.calculate;

import com.calculate.equation.Calculate;

/**
 *
 * @author AW Developer
 */
public class Root extends Calculate{

    Number value;
    
    public Root(Number value) {
        super(null, null);
        this.value=value;
    }

    @Override
    public Number doTheMath() {
        return value;
    }
    
}
