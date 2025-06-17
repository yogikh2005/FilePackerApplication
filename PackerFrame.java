import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PackerFrame extends JFrame implements ActionListener {
    private JLabel folderLabel, packFileLabel;
    private JTextField folderField, packFileField;
    private JButton submitButton, backButton;
    private JPanel backgroundPanel;
    private HeaderPanel headerPanel;
    private MainApplicationFrame parentFrame;
    
    public PackerFrame(MainApplicationFrame parent) {
        this.parentFrame = parent;
        parent.setVisible(false);
        
        initializeFrame();
        createComponents();
        layoutComponents();
        setupEventListeners();
    }
    
    private void initializeFrame() {
        setTitle("FilePacker - Pack Files");
        setSize(410, 405);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }
    
    private void createComponents() {
        headerPanel = new HeaderPanel();
        
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 100, 400, 266);
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(240, 240, 240));
        
        folderLabel = new JLabel("Directory Name:");
        packFileLabel = new JLabel("Pack File Name:");
        folderLabel.setFont(new Font("Arial", Font.BOLD, 12));
        packFileLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        folderField = new JTextField();
        packFileField = new JTextField();
        
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        
        submitButton.setBackground(new Color(87, 186, 45));
        submitButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 140, 0));
        backButton.setForeground(Color.WHITE);
    }
    
    private void layoutComponents() {
        folderLabel.setBounds(70, 50, 120, 20);
        folderField.setBounds(200, 50, 120, 20);
        packFileLabel.setBounds(70, 90, 120, 20);
        packFileField.setBounds(200, 90, 120, 20);
        submitButton.setBounds(90, 140, 90, 30);
        backButton.setBounds(210, 140, 90, 30);
        
        backgroundPanel.add(folderLabel);
        backgroundPanel.add(folderField);
        backgroundPanel.add(packFileLabel);
        backgroundPanel.add(packFileField);
        backgroundPanel.add(submitButton);
        backgroundPanel.add(backButton);
        
        add(headerPanel);
        add(backgroundPanel);
        setVisible(true);
    }
    
    private void setupEventListeners() {
        submitButton.addActionListener(this);
        backButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String folderName = folderField.getText().trim();
            String packFileName = packFileField.getText().trim();
            
            if (folderName.isEmpty() || packFileName.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all fields!", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            FilePackerService packer = new FilePackerService();
            String result = packer.packFiles(folderName, packFileName);
            
            new ResultFrame(this, parentFrame, "Packing Results", result);
            
        } else if (e.getSource() == backButton) {
            dispose();
            parentFrame.setVisible(true);
        }
    }
}
