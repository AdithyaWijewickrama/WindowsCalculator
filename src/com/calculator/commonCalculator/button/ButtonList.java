package com.calculator.commonCalculator.button;

import com.tokenizing.Token;
import com.tokenizing.TokenList;
import java.util.ArrayList;

/**
 *
 * @author AW Developer
 */
public class ButtonList extends ButtonLayout {

    TokenList buttonTokens;
    ArrayList<CommonButton> buttons = new ArrayList<>();
    int currentKey = 0;

    public ButtonList(TokenList buttonTokens, String layoutString, int layout) throws Exception {
        super(layoutString, layout);
        parse();
        this.buttonTokens = buttonTokens;
        createButtons();
//        System.out.println(buttonTokens);
//        System.out.println(buttons);
    }

    public CommonButton getNextKey() {
        int c = this.currentKey / 1;
        this.currentKey++;
        char next = getNext();
        switch (next) {
            case BUTTON:
                return buttons.get(c);
            case DISABLED:
                buttons.get(c).setEnabled(false);
                return buttons.get(c);
            case EMPTY:
                return null;
            default:
                this.currentKey--;
                break;
        }
        return null;
    }

    public final void createButtons() {
        for (Token token : buttonTokens.getTokenList()) {
            System.out.println(token);
            buttons.add(new CommonButton(token));
        }
    }

    public void updateButton(int index, CommonButton btn) {
        buttons.remove(index);
        buttons.add(index, btn);
    }

    public void updateButtonToken(int index, Token btn) {
        buttonTokens.replace(index, btn);
    }

    @Override
    public void update() {
        int i = 0;
        if (buttons.isEmpty()) {
            return;
        }
        newIterator();
        OUTER:
        do {
            char next = getNext();
            System.out.println(next);
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

    public void setText(String text, int i) {
        buttons.get(i).setText(text);
    }
}
