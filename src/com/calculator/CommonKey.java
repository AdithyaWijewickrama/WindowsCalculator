package com.calculator;

import com.Tokenizing.Token;
import static com.calculator.graphing.Graphical.getUIColor;
import javax.swing.JButton;

/**
 *
 * @author AW Developer
 */
public class CommonKey extends JButton {

    public Character keyChar;
    public Token token;
    public CommonNumberPanel parentText;

    public CommonKey(Token token) {
        super(token.name);
        this.token = token;
        setBackground(getUIColor());
        setFont(new java.awt.Font("Tahoma", 1, 14));
        setBorder(javax.swing.BorderFactory.createLineBorder(getBackground()));
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(40, 30));
        setName(keyChar.toString());
        setPreferredSize(new java.awt.Dimension(70, 47));

    }

    public void setParentTextField(CommonNumberPanel text) {
        parentText = text;
        addActionListener((java.awt.event.ActionEvent evt) -> {
            parentText.newKey(token.key);
        });
    }

}
