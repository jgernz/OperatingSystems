/**
 * @author Josh Gerner
 * @author Rachel Rocole
 * @author James Ganzer
 * @author Berkley Lamb
 * @author Corey Schmitz
 * @author Brandon Harshaw
 * @author Jacob Ahnert
 * @version(20140417)
 */

import java.util.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")


public class Main extends JPanel {
	public static Process p = new Process(" ");
	static Sender send = new Sender();

	public void paintComponent(Graphics g) {		  
		int[] y = {15,20,90,105,120};
		int offset = 160;
		Font bigFont = (new Font("Arial", Font.PLAIN,15));
		Font smallFont = (new Font("Arial", Font.PLAIN,8));
		super.paintComponent(g);
		g.setColor(Color.black);

		ArrayList currentProcess = send.receive();

		//Round Robin
		g.setFont(bigFont);
		g.drawString("Round Robin",10,y[0]);
		g.drawRect(0, y[1], 470, 30);
		g.drawString("Waiting Time: " + send.getWait(),10,y[2]);
		g.drawString("Average Waiting Time: " + (send.getWait()/currentProcess.size()),10,y[3]);
		g.drawString("Turnaround Time:",10,y[4]);
		g.setFont(smallFont);
		//testing
		for(int i = 0; i < currentProcess.size(); i++){
			ArrayList temp = (ArrayList) currentProcess.get(i);
			String pname = (String) temp.get(0);
			LinkedList temp1 = (LinkedList) temp.get(1);
			for(int j = 0; j<temp1.size(); j++){
				int[] times = (int[]) temp1.get(j);
				System.out.println("Times" + Arrays.toString(times));
				int startTime = times[0];
				int finishTime = times[1];
				g.drawString(pname,(startTime + finishTime) / 2 - 5,38); //Process Name
				g.drawLine(startTime, 20, startTime, 50); // Start Line
				g.drawLine(finishTime, 20, finishTime, 50); // Finish Line
				g.drawString(Integer.toString(finishTime),finishTime - 3,65); // Ruler
			}
		}

		//FCFS
		g.setFont(bigFont); 
		g.drawString("FCFS",10,y[0] + offset);
		g.drawRect(5, y[1] + offset, 470, 30);
		g.drawString("Waiting Time:",10,y[2] + offset);
		g.drawString("Average Waiting Time:",10,y[3] + offset);
		g.drawString("Turnaround Time:",10,y[4] + offset);
		g.setFont(smallFont); 

		//SPP
		g.setFont(bigFont); 
		g.drawString("Shortest Process Preemptive",10,y[0] + offset * 2);
		g.drawRect(5, y[1] + offset * 2, 470, 30);
		g.drawString("Waiting Time:",10,y[2] + offset * 2);
		g.drawString("Average Waiting Time:",10,y[3] + offset * 2);
		g.drawString("Turnaround Time:",10,y[4] + offset * 2);
		g.setFont(smallFont); 

		//SPNP
		g.setFont(bigFont); 
		g.drawString("Shortest Process Non-Preemptive",10,y[0] + offset * 3);
		g.drawRect(5, y[1] + offset * 3, 470, 30);
		g.drawString("Waiting Time:",10,y[2] + offset * 3);
		g.drawString("Average Waiting Time:",10,y[3] + offset * 3);
		g.drawString("Turnaround Time:",10,y[4] + offset * 3);
		g.setFont(smallFont);

		//Feedback
		g.setFont(bigFont); 
		g.drawString("Feedback",10,y[0] + offset * 4);
		g.drawRect(5, y[1] + offset * 4, 470, 30);
		g.drawString("Waiting Time:",10,y[2] + offset * 4);
		g.drawString("Average Waiting Time:",10,y[3] + offset * 4);
		g.drawString("Turnaround Time:",10,y[4] + offset * 4);
		g.setFont(smallFont); 
	}


	public static void main (String args[]){
		Scanner scanner = new Scanner(System.in);


		int num = 0;

		//Asking for user input
		System.out.println("Please enter in how many processes you would like to create: ");
		System.out.print(">> ");

		//Making sure user input is valid
		try{
			num = scanner.nextInt();
			if(num < 0){
				throw new InputMismatchException();
			}
		}
		catch(InputMismatchException e){
			boolean flag = true;

			while(flag){
				flag = false;
				try{
					System.out.println("Error: Input must be an integer greater than or equal to zero. Please reenter how many processes you would like to create.");
					System.out.print(">> ");
					scanner.nextLine();
					num = scanner.nextInt();
					if(num < 0){
						throw new InputMismatchException();
					}
				}
				catch(InputMismatchException e1){
					flag = true;
				}
			}
		}

		//Creating a new array of processes
		Process[] processes = new Process[num];

		//Populating the array with new processes
		for(int i = 0; i<num; i++){
			processes[i] = new Process("'P" + i +"'");
		}

		//Creating a deep copy of the process array
		Process[] copy = new Process[num];
		copy = p.createCopy(processes);	



		//------------------------------------------
		SPN s = new SPN();
		Process[] p1 = s.ShortProc(copy);


		ArrayList returned = new ArrayList();
		LinkedList list1 = new LinkedList();


		for(int i = 0; i<p1.length; i++){
			returned.add(p.print(p1[i]));
		}

		int totalWaitingTime = 0;

		for(int i = 0; i<returned.size(); i++){
			totalWaitingTime = totalWaitingTime + (int)((ArrayList) returned.get(i)).get(4);
		}
		list1 = (LinkedList) ((ArrayList) returned.get(0)).get(1);
		int[] a = (int[]) list1.get(0);
		System.out.println(Arrays.toString(a));
		System.out.println("List1: " + list1.get(0));


		System.out.println(returned);
		System.out.println(totalWaitingTime);

		send.sending(returned);
		send.sendWait(totalWaitingTime);

		JFrame frame = new JFrame();
		frame.setTitle("DrawRect");
		frame.setSize(500, 820);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container contentPane = frame.getContentPane();
		contentPane.add(new Main());

		frame.setVisible(true);
	}			
}
