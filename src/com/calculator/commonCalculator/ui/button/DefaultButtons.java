package com.calculator.commonCalculator.ui.button;

import com.calculate.Number;
import com.tokenizing.Token;
import com.tokenizing.TokenType;
import java.awt.event.KeyEvent;

/**
 *
 * @author AW Developer
 */
public class DefaultButtons extends Token{
    
     public static final Token CE=new Token(TokenType.SYMBOL, "CE", (char) KeyEvent.VK_DELETE);
     public static final Token C=new Token(TokenType.SYMBOL, "C", (char) KeyEvent.VK_ESCAPE);
     public static final Token BACK=new Token(TokenType.SYMBOL, "<x]", (char) KeyEvent.VK_BACK_SPACE);
     public static final Token RAND=new Token(TokenType.SYMBOL, "rand", (char) KeyEvent.VK_F1);
     public static final Token DMS=new Token(TokenType.FUNCTION_, "dms", (char) KeyEvent.VK_F2);
    
    public DefaultButtons(TokenType type, String name, char key, Number number) {
        super(type, name, key, number);
    }
    
    
    
}
