
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HeaderPanel extends JPanel implements Runnable {
    private JLabel titleLabel, dateLabel, timeLabel;
    private Thread clockThread;
    
    public HeaderPanel() {
        initializePanel();
        createComponents();
        startClock();
    }
    
    private void initializePanel() {
        setBounds(0, 0, 400, 100);
        setLayout(null);
        setBackground(new Color(200, 220, 240));
        setBorder(BorderFactory.createEtchedBorder());
    }
    
    private void createComponents() {
        titleLabel = new JLabel("FilePacker & FileUnPacker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(100, 20, 250, 20);
        
        Calendar cal = Calendar.getInstance();
        dateLabel = new JLabel("Date: " + cal.get(Calendar.DATE) + "/" + 
                              (cal.get(Calendar.MONTH) + 1) + "/" + 
                              cal.get(Calendar.YEAR));
        dateLabel.setBounds(250, 45, 120, 20);
        
        timeLabel = new JLabel();
        timeLabel.setBounds(250, 65, 120, 20);
        
        add(titleLabel);
        add(dateLabel);
        add(timeLabel);
    }
    
    private void startClock() {
        clockThread = new Thread(this);
        clockThread.start();
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
                String timeString = formatter.format(cal.getTime());
                timeLabel.setText("Time: " + timeString);
                
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}