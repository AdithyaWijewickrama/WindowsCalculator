package com.calculate.number;

import com.calculate.equation.Calculate;

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
