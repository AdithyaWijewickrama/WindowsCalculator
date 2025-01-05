package com.calculator.commonCalculator.button;

import java.awt.Point;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AW Developer
 */
public abstract class ButtonLayout {

    public static final int COLUMN = 0;
    public static final int ROW = 1;
    public static final char BUTTON = '#';
    public static final char EMPTY = '.';
    public static final char DISABLED = '*';
    public static final char ERROR = '!';

    public int width;
    public int height;
    private String layoutString;
    private int layout = COLUMN;
    private char[][] buttonsCoordinates;
    private Point point = new Point(-1,-1);

    public ButtonLayout(String layoutString) {
        this.layoutString = layoutString;
    }
    public ButtonLayout(String layoutString,int layout) {
        this.layoutString = layoutString;
        this.layout=layout;
    }

    public final void parse() throws Exception {
        if (layoutString != null) {
            String[] a = layoutString.split("\n");
            System.out.println(Arrays.toString(a));
            int len = a[0].length();
            for (String a1 : a) {
                if (len != a1.length()) {
                    throw new ArrayStoreException("Must be a retangle given\n" + layoutString);
                }
            }
            width = len;
            height = a.length;
            buttonsCoordinates = new char[height][width];
            switch (layout) {
                case COLUMN:
                case ROW:
                    for (int i = 0; i < a.length; i++) {
                        for (int j = 0; j < a[i].length(); j++) {
                            buttonsCoordinates[i][j] = a[i].charAt(j);
                        }
                    }
                    break;
                default:
                    throw new Exception("Illiegle layout " + layout);
            }
            for (char[] buttonsCoordinate : buttonsCoordinates) {
                for (char c : buttonsCoordinate) {
                    if (c != BUTTON && c != EMPTY && c != DISABLED) {
                        throw new Exception("Illigal button type '" + Character.toString(c) + "'");
                    }
                }
            }
        }
        update();
    }

    public String getLayoutString() {
        return layoutString;
    }

    public void setLayoutString(String layoutString) {
        this.layoutString = layoutString;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public char[][] getButtonsCoordinates() {
        return buttonsCoordinates;
    }

    public void setButtonsCoordinates(char[][] buttonsCoordinates) {
        this.buttonsCoordinates = buttonsCoordinates;
    }

    public void disable(int x, int y) {
        char b = buttonsCoordinates[y][x];
        if (b != EMPTY) {
            buttonsCoordinates[y][x] = DISABLED;
        }
        update();
    }

    public void disableColumn(int column) {
        if (column > width || column < 0) {
            throw new IndexOutOfBoundsException("Index " + column + " out of bounds\n" + "width:" + width + "\nheight:" + height);
        }
        for (char[] buttonsCoordinate : buttonsCoordinates) {
            char b = buttonsCoordinate[column];
            if (b != EMPTY) {
                buttonsCoordinate[column] = DISABLED;
            }
        }
        update();
    }

    public int numberOfButtons() {
        int i = 0;
        for (char[] buttonsCoordinate : buttonsCoordinates) {
            for (char c : buttonsCoordinate) {
                if (c != EMPTY) {
                    i++;
                }
            }
        }
        return i;
    }
    
    public char get(int x,int y){
        return buttonsCoordinates[y][x];
    }
    
    public int area(){
        return width*height;
    }

    public void newIterator() {
        point = new Point(-1,-1);
    }

    public boolean hasNext() {
        return !(point.x+1>width-1&&point.y+1>height-1);
    }

    public char getNext() {
        System.out.println(point);
        if (!hasNext()) {
            return ERROR;
        }
        if (layout == COLUMN) {
            point.y++;
            if (point.y == height) {
                point.y = 0;
                point.x++;
            }
        } else if (layout == ROW) {
            point.x++;
            if (point.x == width) {
                point.x = 0;
                point.y++;
            }
        }
        if(point.x==-1)point.x++;
        if(point.y==-1)point.y++;
        return buttonsCoordinates[point.y][point.x];
    }
    
    public String toString(){
        String s="";
         for (char[] buttonsCoordinate : getButtonsCoordinates()) {
                for (char c : buttonsCoordinate) {
                    s+="[" + c + "]";
                }
                s+="\n";
            }
         return s;
    }

    public abstract void update();

    public static void main(String[] args) {
        try {
            ButtonLayout b = new ButtonLayout(""
                    + "#.###\n"
                    + "#*###\n"
                    + "#####\n"
                    + "#.###",ROW) {
                @Override
                public void update() {
                }
            };
            b.parse();
            System.out.println(b);
            while(b.hasNext()){
                System.out.println(b.getNext());
        }
        } catch (Exception ex) {
            Logger.getLogger(ButtonLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
