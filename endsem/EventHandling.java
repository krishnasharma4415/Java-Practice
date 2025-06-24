import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EventHandling extends JFrame {
    private JTextField inputField;
    private JTextArea displayArea;
    private JButton actionButton;
    private JLabel statusLabel;
    
    public EventHandling() {
        // Set up the frame
        super("Complete Event Handling Demo");
        setLayout(new BorderLayout());
        setSize(500, 400);
        
        // Create components
        inputField = new JTextField(20);
        actionButton = new JButton("Click Me");
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        statusLabel = new JLabel("Status: Ready");
        
        // Create panels for organization
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Input: "));
        topPanel.add(inputField);
        topPanel.add(actionButton);
        
        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        // Add event listeners
        addWindowListener(new WindowEventHandler());
        actionButton.addActionListener(new ActionEventHandler());
        inputField.addKeyListener(new KeyEventHandler());
        displayArea.addMouseListener(new MouseEventHandler());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    // ActionListener for button
    class ActionEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                displayArea.append("Button clicked: " + text + "\n");
                inputField.setText("");
            } else {
                displayArea.append("Button clicked with empty input\n");
            }
            statusLabel.setText("Status: Button action performed");
        }
    }
    
    // KeyListener for text field
    class KeyEventHandler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            statusLabel.setText("Status: Key typed: " + e.getKeyChar());
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                actionButton.doClick(); // Simulate button click
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                inputField.setText("");
                statusLabel.setText("Status: Input cleared");
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            // Not used in this example
        }
    }
    
    // MouseListener for display area
    class MouseEventHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            displayArea.append("Mouse clicked at: (" + e.getX() + ", " + e.getY() + ")\n");
            statusLabel.setText("Status: Mouse clicked in display area");
            
            // Right-click context menu
            if (e.getButton() == MouseEvent.BUTTON3) {
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem clearItem = new JMenuItem("Clear");
                clearItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        displayArea.setText("");
                    }
                });
                popupMenu.add(clearItem);
                popupMenu.show(displayArea, e.getX(), e.getY());
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            statusLabel.setText("Status: Mouse pressed in display area");
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            statusLabel.setText("Status: Mouse released in display area");
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            statusLabel.setText("Status: Mouse entered display area");
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            statusLabel.setText("Status: Mouse exited display area");
        }
    }
    
    // WindowListener for frame
    class WindowEventHandler extends WindowAdapter {
        @Override
        public void windowOpened(WindowEvent e) {
            displayArea.append("Window opened\n");
        }
        
        @Override
        public void windowClosing(WindowEvent e) {
            displayArea.append("Window closing\n");
            // Additional cleanup could be done here
        }
        
        @Override
        public void windowIconified(WindowEvent e) {
            System.out.println("Window minimized");
        }
        
        @Override
        public void windowDeiconified(WindowEvent e) {
            System.out.println("Window restored");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventHandling();
            }
        });
    }
}