import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplicationFrame extends JFrame implements ActionListener {
    private HeaderPanel headerPanel;
    private JPanel backgroundPanel;
    private JButton packButton, unpackButton;
    private MenuBar menuBar;
    private Menu viewMenu;
    private MenuItem homeItem, packerItem, unpackerItem;
    
    public MainApplicationFrame() {
        initializeFrame();
        createComponents();
        setupMenu();
        layoutComponents();
        setupEventListeners();
    }
    
    private void initializeFrame() {
        setTitle("FilePacker & FileUnPacker - Main");
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
        
        packButton = new JButton("Pack Files");
        unpackButton = new JButton("Unpack Files");
        
        packButton.setBackground(new Color(245, 188, 122));
        packButton.setForeground(Color.WHITE);
        packButton.setFont(new Font("Serif", Font.BOLD, 15));
        
        unpackButton.setBackground(new Color(245, 188, 122));
        unpackButton.setForeground(Color.WHITE);
        unpackButton.setFont(new Font("Serif", Font.BOLD, 15));
    }
    
    private void setupMenu() {
        menuBar = new MenuBar();
        viewMenu = new Menu("View");
        homeItem = new MenuItem("Home");
        packerItem = new MenuItem("Packer");
        unpackerItem = new MenuItem("UnPacker");
        
        viewMenu.add(homeItem);
        viewMenu.add(packerItem);
        viewMenu.add(unpackerItem);
        menuBar.add(viewMenu);
        
        setMenuBar(menuBar);
        
        homeItem.addActionListener(this);
        packerItem.addActionListener(this);
        unpackerItem.addActionListener(this);
    }
    
    private void layoutComponents() {
        packButton.setBounds(70, 90, 120, 40);
        unpackButton.setBounds(210, 90, 120, 40);
        
        backgroundPanel.add(packButton);
        backgroundPanel.add(unpackButton);
        
        add(headerPanel);
        add(backgroundPanel);
        setVisible(true);
    }
    
    private void setupEventListeners() {
        packButton.addActionListener(this);
        unpackButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == packButton || e.getSource() == packerItem) {
            new PackerFrame(this);
        } else if (e.getSource() == unpackButton || e.getSource() == unpackerItem) {
            new UnpackerFrame(this);
        }
        // Home menu item doesn't need action as this is the home frame
    }
}
