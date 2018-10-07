package umleditor.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class ToolKit {
    public static void centerWindow(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        frame.setLocation(x, y);
    }
	
    public static void centerWindow(JDialog dialog) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int w = dialog.getSize().width;
        int h = dialog.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        dialog.setLocation(x, y);
    }    
}
