package com.calculate;

import com.calculate.equation.Calculate;

/**
 *
 * @author AW Developer
 */
public class Root extends Calculate{

    CNumber value;
    
    public Root(CNumber value) {
        super(null, null);
        this.value=value;
    }

    @Override
    public CNumber doTheMath() {
        return value;
    }
    
}
