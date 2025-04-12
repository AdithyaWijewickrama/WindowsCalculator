package com.calculator.commoncalculator.button;

import com.amath.advacedmath.tokenizing.Token;
import com.amath.advacedmath.tokenizing.TokenList;
import com.calculator.commoncalculator.CommonNumberPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;

public class CommonButton extends JButton {

    public Character keyChar;
    public Token token;
    public TokenList tokenList;
    public CommonNumberPanel parentText;

    public CommonButton(Token token) {
        super(token.name);
        this.token = token;
        setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
        setBorder(getBorder());
        setMaximumSize(new Dimension(1000, 1000));
        setMinimumSize(new Dimension(40, 30));
        setPreferredSize(new Dimension(70, 47));
    }

    public CommonButton(TokenList tokenList) {
        super(tokenList.tokenAt(0).name);
        this.tokenList = tokenList;
        setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
        setBorder(getBorder());
        setMaximumSize(new Dimension(1000, 1000));
        setMinimumSize(new Dimension(40, 30));
        setPreferredSize(new Dimension(70, 47));
    }

    public CommonButton updateUi(ButtonUi bu) {
        setBackground(bu.BACKGROUND);
        setFont(bu.FONT);
        setBorder(bu.BORDER);
        if (bu.FONT_RESIZING) {
            setFontResizing(this);
        }
        return this;
    }

    private int currentKey;

    public void setParentTextField(CommonNumberPanel text) {
        parentText = text;
        addActionListener((ActionEvent evt) -> {
            if (tokenList != null) {
                if (currentKey == tokenList.size()) {
                    currentKey = 0;
                }
                parentText.newKey(tokenList.tokenAt(currentKey++).key);
            } else {
                parentText.newKey(token.key);
            }
        });
    }

    public static void setFontResizing(JButton button) {
        button.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                button.setFont(button.getFont().deriveFont(button.getHeight() / 3.f));
            }
        });
    }

}
