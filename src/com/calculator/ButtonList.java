package com.calculator;

import com.Tokenizing.Token;
import com.Tokenizing.TokenList;
import java.util.ArrayList;

/**
 *
 * @author AW Developer
 */
public class ButtonList extends ButtonLayout {

    TokenList buttonTokens;
    ArrayList<CommonKey> buttons;
    int currentKey = 0;

    public ButtonList(TokenList buttonTokens, String layoutString,int layout) {
        super(layoutString,layout);
        this.buttonTokens = buttonTokens;
    }

    public CommonKey getNextKey() {
        char next = getNext();
        switch (next) {
            case BUTTON:
                return buttons.get(currentKey);
            case DISABLED:
                buttons.get(currentKey).setEnabled(false);
                return buttons.get(currentKey);
            case EMPTY:
                return null;
            default:
                break;
        }
        return null;
    }

    public void createButtons(int layout) {
        for (Token token : buttonTokens.getTokenList()) {
            buttons.add(new CommonKey(token));
        }
    }

    @Override
    public void update() {
        int i = 0;
        newIterator();
        OUTER:
        do {
            char next = getNext();
            switch (next) {
                case BUTTON:
                    buttons.get(i++).setEnabled(true);
                    break;
                case DISABLED:
                    buttons.get(i++).setEnabled(false);
                    break;
                case ERROR:
                    break OUTER;
            }
        } while (hasNext());
    }
    
    public void setText(String text,int i){
        buttons.get(i).setText(text);
    }
}
