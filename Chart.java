import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Chart extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.black);
    
    //Round Robin
    g.drawString("Round Robin",10,15);
    g.drawRect(5, 20, 470, 30);
    g.drawString("Waiting Time:",10,65);
    g.drawString("Average Waiting Time:",10,80);
    g.drawString("Turnaround Time",10,95);
    g.drawString("Normalized Turnaround Time:",10,110);
    
    //FCFS
    g.drawString("FCFS",10,154);
    g.drawRect(5, 160, 470, 30);
    g.drawString("Waiting Time:",10,205);
    g.drawString("Average Waiting Time:",10,220);
    g.drawString("Turnaround Time",10,235);
    g.drawString("Normalized Turnaround Time:",10,250);
    
    //SPP
    g.drawString("Shortest Process Preemptive",10,294);
    g.drawRect(5, 300, 470, 30);
    g.drawString("Waiting Time:",10,345);
    g.drawString("Average Waiting Time:",10,360);
    g.drawString("Turnaround Time",10,375);
    g.drawString("Normalized Turnaround Time:",10,390);
    
    //SPNP
    g.drawString("Shortest Process Non-Preemptive",10,434);
    g.drawRect(5, 440, 470, 30);
    g.drawString("Waiting Time:",10,485);
    g.drawString("Average Waiting Time:",10,500);
    g.drawString("Turnaround Time",10,515);
    g.drawString("Normalized Turnaround Time:",10,530);
  }
 
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("DrawRect");
    frame.setSize(500, 600);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    Container contentPane = frame.getContentPane();
    contentPane.add(new Chart());
 
    frame.setVisible(true);
  }
}
