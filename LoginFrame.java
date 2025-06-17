import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton, cancelButton;
    private JPanel backgroundPanel;
    private HeaderPanel headerPanel;
    
    // Hardcoded credentials (in real app, use secure authentication)
    private static final String VALID_USERNAME = "abc";
    private static final String VALID_PASSWORD = "123";
    
    public LoginFrame() {
        initializeFrame();
        createComponents();
        layoutComponents();
        setupEventListeners();
    }
    
    private void initializeFrame() {
        setTitle("FilePacker & FileUnPacker - Login");
        setSize(410, 405);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }
    
    private void createComponents() {
        headerPanel = new HeaderPanel();
        
        // Create background panel
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 100, 400, 266);
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(240, 240, 240));
        
        // Create login components
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        
        submitButton.setBackground(new Color(87, 186, 45));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Serif", Font.BOLD, 15));
        
        cancelButton.setBackground(new Color(255, 0, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Serif", Font.BOLD, 15));
    }
    
    private void layoutComponents() {
        // Add header
        add(headerPanel);
        
        // Layout login components
        usernameLabel.setBounds(100, 50, 100, 20);
        usernameField.setBounds(200, 50, 120, 20);
        passwordLabel.setBounds(100, 90, 100, 20);
        passwordField.setBounds(200, 90, 120, 20);
        submitButton.setBounds(100, 140, 90, 30);
        cancelButton.setBounds(200, 140, 90, 30);
        
        backgroundPanel.add(usernameLabel);
        backgroundPanel.add(usernameField);
        backgroundPanel.add(passwordLabel);
        backgroundPanel.add(passwordField);
        backgroundPanel.add(submitButton);
        backgroundPanel.add(cancelButton);
        
        add(backgroundPanel);
        setVisible(true);
    }
    
    private void setupEventListeners() {
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
                dispose();
                new MainApplicationFrame();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Invalid username or password!", 
                    "Login Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            usernameField.setText("");
            passwordField.setText("");
        }
    }
}