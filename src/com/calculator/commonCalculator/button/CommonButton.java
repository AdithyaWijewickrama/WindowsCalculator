package com.calculator.commonCalculator.button;

import com.calculator.commonCalculator.CommonNumberPanel;
import com.tokenizing.Token;
import com.tokenizing.TokenList;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author AW Developer
 */
public class CommonButton extends JButton {

    public Character keyChar;
    public Token token;
    public TokenList tokenList;
    public CommonNumberPanel parentText;

    public CommonButton(Token token) {
        super(token.name);
        this.token = token;
//        setBackground(getUIColor());
        setFont(new java.awt.Font("Yu Gothic UI", Font.PLAIN, 15));
        setBorder(getBorder());
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(40, 30));
//        setName(keyChar.toString());
        setPreferredSize(new java.awt.Dimension(70, 47));
//        addComponentListener(new ComponentAdapter() {
//            public void componentResized(ComponentEvent e) {
//
//            }
//        });
    }

    public CommonButton(TokenList tokenList) {
        super(tokenList.tokenAt(0).name);
        this.tokenList = tokenList;
//        setBackground(getUIColor());
        setFont(new java.awt.Font("Yu Gothic UI", Font.PLAIN, 15));
        setBorder(getBorder());
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(40, 30));
//        setName(keyChar.toString());
        setPreferredSize(new java.awt.Dimension(70, 47));
//        addComponentListener(new ComponentAdapter() {
//            public void componentResized(ComponentEvent e) {
//
//            }
//        });
    }

    private int currentKey;

    public void setParentTextField(CommonNumberPanel text) {
        parentText = text;
        addActionListener((java.awt.event.ActionEvent evt) -> {
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

}
