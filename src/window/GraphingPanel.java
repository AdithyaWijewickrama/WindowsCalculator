package window;

import Standard.PlaceValue;
import Standard.RoundingOff;
import static com.home.FrontFrame.Exact;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import expression.Function;
import java.awt.FontMetrics;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import parser.ExpressionParser;

public class GraphingPanel extends JPanel implements MouseWheelListener, MouseMotionListener, KeyListener, Runnable {

    public int width = 1024;
    public int height = 600;

    int scaleX = 0;
    int scaleY = 0;
    double showScaleX = 0;
    double showScaleY = 0;

    public static final int FUNC = 0;
    public static final int STYLE = 1;
    public static final int COLOR = 2;
    public static final int VISIBLE = 3;

    public BufferedImage buff;
    public Graphics2D g2d;

    public ExpressionParser parser;
    public Function function;

    public double windowX, windowY, windowWidth, windowHeight;
    public Point mousePt;

    public String textBox;

    public Color AXIS_COLOR = Color.GRAY;
    public Color BACKGROUND_COLOR = Color.WHITE;
    public Color M_AXIS_COLOR = Color.BLACK;
    public Color TEXT_COLOR = Color.BLACK;
    public Font FONT=new Font(Font.DIALOG,Font.PLAIN,14);

    public ArrayList<Object[]> functions = new ArrayList<>();

    public GraphingPanel() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        validate();
        doLayout();
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(toRealX(x));
                System.out.println(toRealY(y));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePt = e.getPoint();
            }

        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                width = getWidth();
                height = getHeight();
                windowWidth = windowHeight * width / height;
                updateScale();
            }
        });
        setFocusable(true);
        setBackground(BACKGROUND_COLOR);
        requestFocusInWindow();
        setPreferredSize(new Dimension(width, 2039392));
        setMinimumSize(new Dimension(0, 0));
        setMaximumSize(new Dimension(2039392, 2039392));

        buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2d = buff.createGraphics();

        parser = new ExpressionParser();
        textBox = "0";

        windowX = 0.0;
        windowY = 0.0;
        windowHeight = 2.0;
        windowWidth = windowHeight * width / height;
        updateScale();
    }

    // Time variables
    public double yVar = 0.0;	// Constantly increasing
    public double zVar = 0.0;	// Cycles smoothly from -1 to 1
    int powX = 0;
    int powY = 0;

    public void updateScale() {
        double mxw = 50;
        double mnw = 25;
        int divX = 1;
        int divY = 1;
        double gap = 0;
        double[] mul = {.2, .4, 1};
        OUTER:
        while (true) {
            int i;
            for (i = 0; i < mul.length; i++) {
                double j = Math.pow(10, powX) * mul[i];
                gap = toScreenX(j) - toScreenX(0);
                if (gap <= mxw && gap >= mnw) {
                    showScaleX = j * 5;
                    divX = (int) gap;
                    break OUTER;
                }
            }
            if (gap > mxw) {
                powX--;
            }
            if (gap < mnw) {
                powX++;
            }
            if (i == 3) {
                mxw += mxw * .1;
            }
        }
        gap = 0;
        OUTER:
        while (true) {
            int i;
            for (i = 0; i < mul.length; i++) {
                double j = Math.pow(10, powY) * mul[i];
                gap = toScreenY(0) - toScreenY(j);
                if (gap <= mxw && gap >= mnw) {
                    showScaleY = j * 5;
                    divY = (int) gap;
                    break OUTER;
                }
            }
            if (gap > mxw) {
                powY--;
            }
            if (gap < mnw) {
                powY++;
            }
            if (i == 3) {
                mxw += mxw * .1;
            }
        }
        scaleX = divX;
        scaleY = divY;
    }

    public synchronized void updateDT(double dt) {
        yVar += dt;
        zVar = Math.sin(yVar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        buff = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        g2d = buff.createGraphics();
        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        synchronized (this) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int yAxisX = (int) toScreenX(0.0);
            int xAxisY = (int) toScreenY(0.0);
            double scaling = yAxisX % (scaleX * 5.);
            double showX = toRealX(0.) - (toRealX(0.) % showScaleX == 0 ? showScaleX : (toRealX(0.) % showScaleX));
            System.out.println("[POW]"+powX+"");
            for (int i = 0; i <= width; i += scaleX) {
                if (i > (scaling - scaleX)) {
                    g2d.setColor(TEXT_COLOR);
                    g2d.setFont(FONT);
                    g2d.drawString(Exact(new RoundingOff(showX, new PlaceValue(PlaceValue.PREFPLACE, Math.abs(powX))).getNumber()) + "", yAxisX % scaleX + i + 3, xAxisY + 17);
                    g2d.setColor(M_AXIS_COLOR);
                    scaling += scaleX * 5;
                    showX += showScaleX;
                } else {
                    g2d.setColor(AXIS_COLOR);
                }
                g2d.drawLine(yAxisX % scaleX + i, 0, yAxisX % scaleX + i, height);
                g2d.setStroke(new BasicStroke(1.0f));
            }
            showX = toRealY(0) - (toRealY(0) % showScaleY);
            scaling = xAxisY % (scaleY * 5);
            for (int i = 0; i < height; i += scaleY) {
                if (i > (scaling - scaleY)) {
                    g2d.setColor(TEXT_COLOR);
                    g2d.setFont(FONT);
                    if (showX != 0) {
                        g2d.drawString(Exact(new RoundingOff(showX, new PlaceValue(PlaceValue.PREFPLACE, Math.abs(powY))).getNumber()) + "", yAxisX + 15, xAxisY % scaleY + i);
                    }
                    g2d.setColor(M_AXIS_COLOR);
                    scaling += scaleY * 5;
                    showX -= showScaleY;
                } else {
                    g2d.setColor(AXIS_COLOR);
                }
                g2d.setStroke(new BasicStroke(1.0f));
                g2d.drawLine(0, xAxisY % scaleY + i, width, xAxisY % scaleY + i);
            }

            g2d.setColor(M_AXIS_COLOR);
            g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
            g2d.drawLine(5, xAxisY, width - 5, xAxisY);
            g2d.drawLine(yAxisX, 5, yAxisX, height - 5);

            for (Object[] func : functions) {
                if ((boolean)func[VISIBLE]) {
                    List<Double> xs = new ArrayList<>();
                    List<Double> ys = new ArrayList<>();
                    function = (Function) func[FUNC];
                    Color color = (Color) func[COLOR];
                    char style = (char) func[STYLE];
                    for (int x = 0; x < width; x++) {
                        double xx = toRealX(x);
                        
                        double yy = 0.0;
                        if (function != null) {
                            yy = function.evaluateAt(xx, yVar, zVar);
                        }
                        
                        double scaledX = x;
                        double scaledY = toScreenY(yy);
                        scaledY = Math.min(Math.max(scaledY, -5), height + 5);
                        
                        xs.add(scaledX);
                        ys.add(scaledY);
                    }
                    int[] xa = new int[xs.size()];
                    int[] ya = new int[ys.size()];
                    for (int i = 0; i < xa.length; i++) {
                        xa[i] = xs.get(i).intValue();
                    }
                    for (int i = 0; i < ya.length; i++) {
                        ya[i] = ys.get(i).intValue();
                    }
                    g2d.setColor(color);
                    Stroke stroke;
                    if (style == '_') {
                        stroke = new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
                    } else {
                        stroke = new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 0, new float[]{style == '-' ? 9 : 3}, 1);
                    }
                    g2d.setStroke(stroke);
                    g2d.drawPolyline(xa, ya, xa.length);
                }
            }
            g2d.setFont(FONT);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, height - g2d.getFontMetrics().getHeight(), width, height);
            g2d.setColor(Color.BLACK);
            g2d.drawString("f(x) = " + textBox, 0.0f, height - 10.0f);

            g2d.setFont(FONT);
            g2d.drawString("x", width - 30, xAxisY - 10);
            g2d.drawString("y", yAxisX + 10, g2d.getFontMetrics().getHeight() + 5);

            if (mousePt != null) {
                Double x = toRealX(mousePt.getX());
                Double y = toRealY(mousePt.getY());
                FontMetrics fm = g2d.getFontMetrics(FONT);
                g2d.setColor(AXIS_COLOR);
//                g2d.fillRect((int)mousePt.getX() + 20,
//                        (int)mousePt.getY() + 20, fm.stringWidth(x.toString() + "," + y.toString()), 20);
                g2d.setColor(TEXT_COLOR);
                g2d.drawString(getPoint(), (int)mousePt.getX() + 20, (int)mousePt.getY() + 20);
            }
        }
        g.drawImage(buff, 0, 0, null);

    }

    public String getPoint(){
        return "("+new RoundingOff(toRealX(mousePt.getX()), new PlaceValue(PlaceValue.PREFPLACE, 5)).getPlainString()+
                ","+new RoundingOff(toRealY(mousePt.getY()), new PlaceValue(PlaceValue.PREFPLACE, 5)).getPlainString()+")";
    }
    
    @Override
    public void run() {
        boolean running = true;

        long oldTime = 0;
        double dt = 0.0;

        while (running) {

            if (isVisible()) {
                long newTime = System.nanoTime();
                dt = (newTime - oldTime) / 1000000000.0;
                oldTime = newTime;

                updateDT(dt);
                repaint();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (textBox.length() > 0) {
                textBox = textBox.substring(0, textBox.length() - 1);
            }
        } else if (Character.isLetterOrDigit(e.getKeyChar()) || e.getKeyChar() == '^' || e.getKeyChar() == '-'
                || e.getKeyChar() == '+' || e.getKeyChar() == '*' || e.getKeyChar() == '/' || e.getKeyChar() == '('
                || e.getKeyChar() == ')' || e.getKeyChar() == '%' || e.getKeyChar() == ',' || e.getKeyChar() == '.') {
            textBox += e.getKeyChar();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Object[] func = new Object[3];
            func[FUNC] = parser.parse(textBox);
            func[COLOR] = Color.BLUE;
            func[STYLE] = '-';
            functions.add(func);
            if (function == null) {
                textBox = "";
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public double bottom() {
        return windowY - halfWindowHeight();
    }

    public double right() {
        return windowX - halfWindowWidth();
    }

    public double toRealX(int screenX) {
        return screenX / (double) width * windowWidth + right();
    }

    public double toRealY(int screenY) {
        return (height - screenY) / (double) height * windowHeight + bottom();
    }

    public double toRealX(double screenX) {
        return screenX / (double) width * windowWidth + right();
    }

    public double toRealY(double screenY) {
        return (height - screenY) / (double) height * windowHeight + bottom();
    }

    public double toScreenX(double realX) {
        return ((realX - right()) / windowWidth * width);
    }

    public double toScreenY(double realY) {
        return height - ((realY - bottom()) / windowHeight * height);
    }

    public double halfWindowWidth() {
        return windowWidth / 2.0;
    }

    public double halfWindowHeight() {
        return windowHeight / 2.0;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        updateScale();
        double scale = Math.pow(1.15, e.getPreciseWheelRotation());
        double mxReal = toRealX(e.getX());
        double myReal = toRealY(e.getY());
        double sx = (windowX - mxReal) / windowWidth;
        double sy = (windowY - myReal) / windowHeight;
        System.out.println(scale);
        windowWidth *= scale;
        windowHeight *= scale;
        windowX = mxReal + sx * windowWidth;
        windowY = myReal + sy * windowHeight;
    }

    public static void main(String[] args) {
        GraphingPanel window = new GraphingPanel();

        JFrame frame = new JFrame("Function Grapher");
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(window);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(window).start();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - mousePt.x;
        int dy = e.getY() - mousePt.y;
        windowX -= dx / (double) width * windowWidth;
        windowY += dy / (double) height * windowHeight;
        mousePt = e.getPoint();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePt = e.getPoint();
    }

}
