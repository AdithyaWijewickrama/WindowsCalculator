package com.calculator.grapher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GraphPanel extends JPanel implements Runnable {

    private ArrayList<BasicFunction> functions;
    int mouseX;
    int mouseY;
    Point center;
    double xScaleFactor;
    double yScaleFactor;//1 pixel= 1/scaleFactor
    double xScale;
    double yScale;//1 pixel= 1/scaleFactor
    int margin = 0;
    int xGap = 5;//gap between two visible near coordiates
    int yGap = 5;
    int scaleX = 5;
    int scaleY = 5;
    int xInc = 2;
    int yInc = 2;
    double base = 1;
    double maxX = 10;
    double maxY = 10;
    double minX = -10;
    double minY = -10;
    boolean running = true;
    double ratio = 1;
    private Color BACKGROUND_COLOR = Color.WHITE;
    private Color AXIS_COLOR = Color.BLACK;
    private Color SUBGRID_COLOR = new Color(150, 150, 150);
    private Color GRID_COLOR = new Color(220, 220, 220);

    public GraphPanel() {
        this.functions = new ArrayList<>();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
//                 Handle the resize event
                int newWidth = getWidth();
                int newHeight = getHeight();
                if (xScaleFactor == 0) {
                    xScaleFactor = (newWidth - margin * 2) / Math.abs(maxX - minX);
                    yScaleFactor = (newHeight - margin * 2) / Math.abs(maxY - minY);
                    xScale = xScaleFactor;
                    yScale = yScaleFactor;
                } else {
                    xScaleFactor = (newWidth - margin * 2) / xScaleFactor;
                    yScaleFactor = (newHeight - margin * 2) / yScaleFactor;
                    xScale = xScaleFactor;
                    yScale = yScaleFactor;
                }
                if (center == null) {
                    center = new Point();
                    center.setLocation(newWidth / 2, newHeight / 2);
                }

                repaint(); // Optional: repaint the component if needed
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double x = getXOnGrid(e.getPoint().getX());
                double y = getYOnGrid(e.getPoint().getY());
                double r = -e.getPreciseWheelRotation();
                xScaleFactor += r * 2;
                yScaleFactor += r * 2 * getHeight() / getWidth();
                checkGaps();
                xScale = xScaleFactor / ((double) scaleX * base / xGap);
                yScale = yScaleFactor / ((double) scaleY * base / yGap);
                repaint();
                center.setLocation(center.getX() + (e.getPoint().getX() - getXOnPanel(x)), center.getY() + (e.getPoint().getY() - getYOnPanel(y)));
                checkMinMax();

            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getButton() == 0) {
                    int x = e.getX() - mouseX;
                    int y = e.getY() - mouseY;
                    center.setLocation(center.getX() + x, center.getY() + y);
                    mouseX = e.getX();
                    mouseY = e.getY();
                    checkMinMax();
                }
            }
        });
    }
    
    public void stopRunning(){
        running=false;
    }
    
    public void continueRunning(){
        running=true;
    }

    public void checkGaps() {
        int width = getWidth() - margin * 2;
        int height = getHeight() - margin * 2;
        if (xScaleFactor > width / 16 || yScaleFactor > height / 16) {
            xScaleFactor = width / 30;
            yScaleFactor = height / 30;
//            xScaleFactor = xScale*getPrevious(scaleX)/scaleX;
//            yScaleFactor = yScale*getPrevious(scaleY)/scaleY;
            if (getPrevious(scaleX) == 5) {
                base /= 10;
            }
            scaleX = getPrevious(scaleX);
            scaleY = getPrevious(scaleY);
        } else if (xScaleFactor < width / 30 || yScaleFactor < height / 30) {
            xScaleFactor = width / 16;
            yScaleFactor = height / 16;
//            xScaleFactor = xScale*getNext(scaleX)/scaleX;
//            yScaleFactor = yScale*getNext(scaleY)/scaleY;
            if (getNext(scaleX) == 1) {
                base *= 10;
            }
            scaleY = getNext(scaleY);
            scaleX = getNext(scaleX);
        }
    }

    public void checkMinMax() {
        minX = (center.getX() - margin) / xScaleFactor;
        minY = (center.getY() - margin) / yScaleFactor;
        maxX = (center.getX() - margin - getWidth()) / xScaleFactor;
        maxY = (center.getY() - margin - getHeight()) / yScaleFactor;

    }

    public void addFunction(BasicFunction function) {
        this.functions.add(function);
        repaint();
    }

    private int getNext(int i) {
        switch (i) {
            case 1 :
                return 2;
            case 2 :
                return 5;
            case 5 :
                return 1;
            default :
                return i;
        }
    }

    private int getPrevious(int i) {
         switch (i) {
            case 1 :
                return 5;
            case 2:
                return 1;
            case 5 :
                return 2;
            default :
                return i;
        }
    }

    private Point getLocationOnGrid(double x, double y) {
        Point p = new Point();
        p.setLocation(getXOnGrid(x), getYOnGrid(y));
        return p;
    }

    private double getXOnPanel(double x) {
        return x * xScale + center.getX();
    }

    private double getYOnPanel(double y) {
        return center.getY() - (y * yScale);
    }

    private double getXOnGrid(double x) {
        return (x - center.getX()) / xScale;
    }

    private double getYOnGrid(double y) {
        return (center.getY() - y) / yScale;
    }

    public boolean isYAxisVisible() {
        return center.getX() > margin && center.getX() < getWidth() - margin;
    }

    public boolean isXAxisVisible() {
        return center.getY() > margin && center.getY() < getHeight() - margin;
    }

    public boolean isCenterVisible() {
        return isXAxisVisible() && isYAxisVisible();
    }

    public static String sciNotaion(double num) {
        int base = (int) Math.floor(Math.log10(Math.abs(num)));
        if (base < 7) {
            num *= Math.pow(10, 7 - base);
        }
        String s = Double.toString(num);
        return String.format("%sx10^%d", s.split("E")[0], base);
    }

    public static double getXIncrement(BasicFunction f, double x) {
        double y = f.evaluateAt(com.calculate.CNumber.parseNumber(x)).doubleValue();
        double x0 = x + 1;
        double y0 = f.evaluateAt(com.calculate.CNumber.parseNumber(x0)).doubleValue();
        return (y - y0) / (x - x0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!running)return;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        float[] dashPattern = {1, 1};  // 10 pixels on, 10 pixels off
//        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, dashPattern, 0));
//        g2.setColor(Color.GREEN);

        int width = getWidth();
        int height = getHeight();
        if (center == null) {
            center = new Point();
            center.setLocation(width / 2., height / 2.);
            checkGaps();
            checkMinMax();
        }
        Point centerDisplacement = new Point();
        centerDisplacement.setLocation(center.getX() - (width / 2), center.getY() - (height / 2));

        g2.setColor(BACKGROUND_COLOR);
        g2.fill(new Rectangle2D.Double(margin, margin, width - margin * 2, height - margin * 2));
        g2.setColor(SUBGRID_COLOR);
        g2.setStroke(new BasicStroke(1));
        double fx = (center.getX() - margin) % (xScaleFactor * xGap);
        double fy = (center.getY() - margin) % (yScaleFactor * yGap);
        while (fx <= width - margin * 2) {
            g2.draw(new Line2D.Double(fx + margin, margin, fx + margin, height - margin));
            fx += xScaleFactor * xGap;
        }
        while (fy <= height - margin * 2) {
            g2.draw(new Line2D.Double(margin, fy + margin, width - margin, fy + margin));
            fy += yScaleFactor * yGap;
        }
        g2.setColor(GRID_COLOR);
        g2.setStroke(new BasicStroke(1));
        fx = (center.getX() - margin) % (xScaleFactor);
        fy = (center.getY() - margin) % (yScaleFactor);
        while (fx <= width - margin * 2) {
            g2.draw(new Line2D.Double(fx + margin, margin, fx + margin, height - margin));
            fx += xScaleFactor;
        }
        while (fy <= height - margin * 2) {
            g2.draw(new Line2D.Double(margin, fy + margin, width - margin, fy + margin));
            fy += yScaleFactor;
        }

        g2.setColor(AXIS_COLOR);
        g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));

        if (isXAxisVisible()) {
            g2.draw(new Line2D.Double(margin, center.getY(), width - margin, center.getY())); // X axis
        }
        if (isYAxisVisible()) {
            g2.draw(new Line2D.Double(center.getX(), margin, center.getX(), height - margin)); // Y axis
        }
        painNumbers(fx, fy, width, g2, height);
        if (functions.isEmpty()) {
            return;
        }
        synchronized (this) {
            for (BasicFunction function : functions) {
                if (!function.isVisible()) {
                    continue;
                }
                g2.setColor(function.getColor());
                g2.setStroke(function.getStroke());
                Path2D.Double path = new Path2D.Double();
                double inc = 1;
                double xv[] = new double[2];
                double yv[] = new double[2];
                xv[1] = margin;
                yv[1] = getYOnPanel(function.evaluateAt(com.calculate.CNumber.parseNumber(getXOnGrid(margin))).doubleValue());
                path.moveTo(xv[1], yv[1]);
                double x = margin;
                boolean lineBreak = false;
                while (true) {
                    double m = Math.abs(getXIncrement(function, x));
                    x += inc;
                    double xVal = getXOnGrid(x);
                    double yVal = function.evaluateAt(com.calculate.CNumber.parseNumber(xVal)).doubleValue();
                    if (x > width - margin) {
                        break;
                    }
                    if (yVal == Double.POSITIVE_INFINITY || yVal == Double.NEGATIVE_INFINITY || getYOnPanel(yVal) < margin || getYOnPanel(yVal) > height - margin) {
                        lineBreak = true;
                    }
                    xv[0] = xv[1];
                    xv[1] = x;
                    yv[0] = yv[1];
                    yv[1] = getYOnPanel(yVal);
                    if (lineBreak) {
                        path.moveTo(x, getYOnPanel(yVal));
                        lineBreak = false;
                        continue;
                    }
                    path.quadTo(xv[0], yv[0], xv[1], yv[1]);
                }
                g2.draw(path);

//            for (double x = -width / 2 + margin - centerDisplacement.x; x < width / 2 - margin - centerDisplacement.x; x += 1) {
//                double xValue = x / xScale; // Scale factor
//                exp.setVariable("x", xValue);
//                double yValue = exp.evaluate();
//                double scaledYValue = -yValue * yScale + centerDisplacement.y; // Scale factor and invert y-axis
//                if (margin != x + width / 2 + centerDisplacement.x) {
//                    if (x != -width / 2 && x != margin) {
//                       g2.draw(new Line2D.Double(x + width / 2 - 1 + centerDisplacement.x, previousY + height / 2, x + width / 2 + centerDisplacement.x, scaledYValue + height / 2));
//                       path.lineTo(x + width / 2 + centerDisplacement.x, scaledYValue + height / 2);
//                    }
//                }
//            }
            }
        }
    }

    protected void painNumbers(double fx, double fy, int width, Graphics2D g2, int height) {
        fx = (center.getX() - margin) % (xScaleFactor * xGap);
        fy = (center.getY() - margin) % (yScaleFactor * yGap);
        double numX = -((int) ((center.getX() - margin) / (xScaleFactor * xGap))) * scaleX;
        while (true) {
            if (fx > width - margin * 2) {
                break;
            }
            String s;
            if (0 == numX) {
                s = "0";
            } else if (Math.log10(Math.abs(numX * base)) < 5 && Math.log10(Math.abs(numX * base)) > -5) {
                s = String.format("%." + (base > 1 ? "0" : Integer.toString((int) Math.abs(Math.log10(base)))) + "f", numX * base);
            } else {
                s = sciNotaion(numX * base);
            }
            g2.setColor(BACKGROUND_COLOR);
            g2.fill(new Rectangle2D.Double((fx + margin - (s.equals("0") ? -7 : g2.getFontMetrics(getFont()).stringWidth(s) / 2)),
                    (isXAxisVisible() ? (center.getY() + 5) : (center.getY() > margin ? (getHeight() - margin - 15) : margin - 5)),
                    g2.getFontMetrics(getFont()).stringWidth(s), g2.getFontMetrics(getFont()).getHeight() - 3));
            g2.setColor(AXIS_COLOR);
            g2.drawString(s, (float) (fx + margin - (s.equals("0") ? -7 : g2.getFontMetrics(getFont()).stringWidth(s) / 2)),
                    (float) (isXAxisVisible() ? ((center.getY() + 15)) : (center.getY() > margin ? (getHeight() - margin - 5) : margin + 5)));
            fx += xScaleFactor * xGap;
            numX += scaleX;
        }
        double numY = ((int) ((center.getY() - margin) / (yScaleFactor * yGap))) * scaleY;
        while (true) {
            if (fy > height - margin * 2) {
                break;
            }

            String s;
            if (0 == numY) {
                s = "";
            } else if (Math.log10(Math.abs(numY * base)) < 5 && Math.log10(Math.abs(numY * base)) > -5) {
                s = String.format("%." + (base > 1 ? "0" : Integer.toString((int) Math.abs(Math.log10(base)))) + "f", numY * base);
            } else {
                s = sciNotaion(numY * base);
            }
            g2.drawString(s, isYAxisVisible() ? (float) (center.getX() + 5) : (center.getX() > margin ? (getWidth() - g2.getFontMetrics(getFont()).stringWidth(s) - margin) : margin + 5), (float) fy + margin);
            fy += yScaleFactor * yGap;
            numY -= scaleY;
        }
    }

    @Override
    public void run() {
        while (running) {
            if (isVisible()) {
                repaint();
            } else {
                running = false;
            }
            try {
                Thread.sleep((long) 100.0);
            } catch (InterruptedException ex) {
                Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
