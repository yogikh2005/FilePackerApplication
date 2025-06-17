import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame extends JFrame implements ActionListener {
    private TextArea resultArea;
    private JButton backButton;
    private JPanel backgroundPanel;
    private HeaderPanel headerPanel;
    private JFrame parentFrame;
    private MainApplicationFrame mainFrame;
    
    public ResultFrame(JFrame parent, MainApplicationFrame main, String title, String result) {
        this.parentFrame = parent;
        this.mainFrame = main;
        
        parent.setVisible(false);
        
        initializeFrame(title);
        createComponents(result);
        layoutComponents();
        setupEventListeners();
    }
    
    private void initializeFrame(String title) {
        setTitle(title);
        setSize(410, 405);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }
    
    private void createComponents(String result) {
        headerPanel = new HeaderPanel();
        
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 100, 400, 266);
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(240, 240, 240));
        
        resultArea = new TextArea();
        resultArea.setText(result);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        
        backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 140, 0));
        backButton.setForeground(Color.WHITE);
    }
    
    private void layoutComponents() {
        resultArea.setBounds(10, 10, 380, 200);
        backButton.setBounds(150, 220, 90, 30);
        
        backgroundPanel.add(resultArea);
        backgroundPanel.add(backButton);
        
        add(headerPanel);
        add(backgroundPanel);
        setVisible(true);
    }
    
    private void setupEventListeners() {
        backButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
            parentFrame.dispose();
            mainFrame.setVisible(true);
        }
    }
}