package umleditor.view;

import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import umleditor.util.Constants;


public class Canvas {
    private static final Canvas canvas = new Canvas();

    private final List<Shape> shapes = new ArrayList<>();
    private BufferedImage background;
    private final JFrame frame;
    private final CanvasComponent component;
    private Cursor curCursor;
    private static final int MIN_SIZE = 100;
    private static final int MARGIN = 10;

    class CanvasComponent extends JPanel implements MouseMotionListener {
        private int currentShapeIndex = -1;
        
        public CanvasComponent() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    int x = me.getX();
                    int y = me.getY();
                    currentShapeIndex = getRec(x, y);
//                    if (currentShapeIndex < 0) {
//                        add(x, y);
//                    }
                }

                @Override
                public void mouseClicked(MouseEvent evt) {
                    int x = evt.getX();
                    int y = evt.getY();
                    if (evt.getClickCount() >= 2) {
                        Shape currentShape = shapes.get(currentShapeIndex);
                        shapes.remove(currentShape);
                        Canvas.getInstance().repaint();
                    }
                }
            });

            addMouseMotionListener(this);
        }
        
        @Override
        public void mouseDragged(MouseEvent me) {
            int x = me.getX();
            int y = me.getY();
            if (currentShapeIndex >= 0) {
                Graphics graphics = getGraphics();
                graphics.setXORMode(getBackground());
                Shape currentShape = shapes.get(currentShapeIndex);
                currentShape.setX(x);
                currentShape.setY(y);
                Canvas.getInstance().show(currentShape);
            }
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            int x = me.getX();
            int y = me.getY();
            if (getRec(x, y) >= 0) {
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                setCursor(Cursor.getDefaultCursor());
            }
        }
        
        public int getRec(int x, int y) {
            int index = 0;
            for (Shape shape: shapes) {
                if (shape.contains(x, y)) {
                    return index;
                }
                index++;
            }

            return -1;
        }
        
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(java.awt.Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(java.awt.Color.BLACK);
            if (background != null) {
                g.drawImage(background, 0, 0, null);
            }
            for (Shape s : new ArrayList<>(shapes)) {
                Graphics2D g2 = (Graphics2D) g.create();
                s.paintShape(g2);
                g2.dispose();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            int maxx = MIN_SIZE;
            int maxy = MIN_SIZE;
            if (background != null) {
                maxx = Math.max(maxx, background.getWidth());
                maxy = Math.max(maxx, background.getHeight());
            }
            for (Shape s : shapes) {
                maxx = (int) Math.max(maxx, s.getX() + s.getWidth());
                maxy = (int) Math.max(maxy, s.getY() + s.getHeight());
            }
            return new Dimension(maxx + MARGIN, maxy + MARGIN);
        }
    }

    private Canvas() {
        component = new CanvasComponent();
        frame = new JFrame();
        frame.setTitle(Constants.DIALOG_TITLE + " " + Constants.APP_VERSION);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(800, 600));
        ToolKit.centerWindow(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(component);
        frame.pack();
        frame.setVisible(true);
    }

    public static Canvas getInstance() {
        return canvas;
    }

    public void show(Shape s) {
        if (!shapes.contains(s)) {
            shapes.add(s);
        }
        repaint();
    }

    public void repaint() {
        if (frame == null) {
            return;
        }
        Dimension dim = component.getPreferredSize();
        if (dim.getWidth() > component.getWidth()
                || dim.getHeight() > component.getHeight()) {
            frame.pack();
        } else {
            frame.repaint();
        }
    }

    /**
     * Pauses so that the user can see the picture before it is transformed.
     */
    public static void pause() {
        JFrame frame = getInstance().frame;
        if (frame == null) {
            return;
        }
        JOptionPane.showMessageDialog(frame, "Click Ok to continue");
    }

    /**
     * Takes a snapshot of the screen, fades it, and sets it as the background.
     */
    public static void snapshot() {
        Dimension dim = getInstance().component.getPreferredSize();
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, dim.width, dim.height);
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, rect.width, rect.height);
        g.setColor(java.awt.Color.BLACK);
        getInstance().component.paintComponent(g);
        float factor = 0.8f;
        float base = 255f * (1f - factor);
        RescaleOp op = new RescaleOp(factor, base, null);
        BufferedImage filteredImage
                = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        op.filter(image, filteredImage);
        getInstance().background = filteredImage;
        getInstance().component.repaint();
    }

    public void saveToDisk(String fileName) {
        Dimension dim = component.getPreferredSize();
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, dim.width, dim.height);
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(java.awt.Color.WHITE);
        g.fill(rect);
        g.setColor(java.awt.Color.BLACK);
        component.paintComponent(g);
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        try {
            ImageIO.write(image, extension, new File(fileName));
        } catch (IOException e) {
            System.err.println("Was unable to save the image to " + fileName);
        }
        g.dispose();
    }
}

