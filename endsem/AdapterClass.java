import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdapterClass extends JFrame {
    private JTextArea logArea;
    private JLabel statusLabel;
    
    public AdapterClass() {
        super("Adapter Classes Demo");
        setLayout(new BorderLayout());
        setSize(400, 300);
        
        // Create components
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        statusLabel = new JLabel("Status: Move mouse or resize window");
        
        // Add components to frame
        add(new JScrollPane(logArea), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        // Using MouseAdapter (only implementing methods we need)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logArea.append("Mouse clicked at: (" + e.getX() + ", " + e.getY() + ")\n");
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                statusLabel.setText("Status: Mouse entered window");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                statusLabel.setText("Status: Mouse exited window");
            }
        });
        
        // Using MouseMotionAdapter
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                logArea.append("Mouse dragged to: (" + e.getX() + ", " + e.getY() + ")\n");
            }
        });
        
        // Using WindowAdapter
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logArea.append("Window closing...\n");
            }
            
            @Override
            public void windowActivated(WindowEvent e) {
                logArea.append("Window activated\n");
            }
            
            @Override
            public void windowDeactivated(WindowEvent e) {
                logArea.append("Window deactivated\n");
            }
        });
        
        // Using ComponentAdapter
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                logArea.append("Window resized to: " + getWidth() + "x" + getHeight() + "\n");
            }
            
            @Override
            public void componentMoved(ComponentEvent e) {
                logArea.append("Window moved to: " + getX() + ", " + getY() + "\n");
            }
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdapterClass();
            }
        });
    }
}