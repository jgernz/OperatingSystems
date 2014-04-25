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

	public static Process[] copy;
	public static Process[] copy1;
	public static Process[] copy2;
	public static Process[] copy3;
	public static Process[] copy4;

	public static Sender send = new Sender();
	public static Sender send2 = new Sender();
	public static Sender send3 = new Sender();
	public static Sender send4 = new Sender();
	public static Sender send5 = new Sender();

	public static Process[] processes;

	public static SPN s;
	public static Feedback f;
	public static RoundRobin r;


	public void paintComponent(Graphics g) {

		int[] y = {15,20,90,105,120};
		int offset = 160;
		Font bigFont = (new Font("Arial", Font.PLAIN,15));
		Font smallFont = (new Font("Arial", Font.PLAIN,8));
		super.paintComponent(g);
		g.setColor(Color.black);

		ArrayList currentProcess = send.receive();


		//Shortest Process Next
		g.setFont(bigFont);
		g.drawString("Shortest Process Next",10,y[0]);
		g.drawRect(0, y[1], 470, 30);
		g.drawString("Waiting Time: " + send.getWait(),10,y[2]);
		g.drawString("Average Waiting Time: " + (send.getWait()/currentProcess.size()),10,y[3]);
		g.drawString("Turnaround Time:",10,y[4]);
		g.setFont(smallFont);
		//Number 1
		for(int i = 0; i < currentProcess.size(); i++){
			ArrayList temp = (ArrayList) currentProcess.get(i);
			String pname = (String) temp.get(0);
			LinkedList temp1 = (LinkedList) temp.get(1);
			for(int j = 0; j<temp1.size(); j++){
				int[] times = (int[]) temp1.get(j);
				//System.out.println("Times" + Arrays.toString(times));
				int startTime = times[0];
				int finishTime = times[1];
				g.drawString(pname,(startTime + finishTime) / 2 - 5,38); //Process Name
				g.drawLine(startTime, 20, startTime, 50); // Start Line
				g.drawLine(finishTime, 20, finishTime, 50); // Finish Line
				g.drawString(Integer.toString(finishTime),finishTime - 3,65); // Ruler
				g.drawString(Integer.toString(startTime),startTime - 3,65); // Ruler
			}
		}

		currentProcess = send2.receive();

		//2nd algorithm
		g.setFont(bigFont); 
		g.drawString("Round Robin",10,y[0] + offset);
		g.drawRect(0, y[1] + offset, 470, 30);
		g.drawString("Waiting Time:",10,y[2] + offset);
		g.drawString("Average Waiting Time:",10,y[3] + offset);
		g.drawString("Turnaround Time:",10,y[4] + offset);
		g.setFont(smallFont); 
		//Number 2
		for(int i = 0; i < currentProcess.size(); i++){
			ArrayList temp = (ArrayList) currentProcess.get(i);
			String pname = (String) temp.get(0);
			LinkedList temp1 = (LinkedList) temp.get(1);
			for(int j = 0; j<temp1.size(); j++){
				int[] times = (int[]) temp1.get(j);
				int startTime = times[0];
				int finishTime = times[1];
				g.drawString(pname,(startTime + finishTime) / 2 - 5,198); //Process Name
				g.drawLine(startTime, 180, startTime, 210); // Start Line
				g.drawLine(finishTime, 180, finishTime, 210); // Finish Line
				g.drawString(Integer.toString(finishTime),finishTime - 3,225); // Ruler
				g.drawString(Integer.toString(startTime),startTime - 3,225); // Ruler
			}
		}

		currentProcess = send3.receive();

		//3rd algorithm
		g.setFont(bigFont); 
		g.drawString("Third Algorithm",10,y[0] + offset * 2);
		g.drawRect(0, y[1] + offset * 2, 470, 30);
		g.drawString("Waiting Time:",10,y[2] + offset * 2);
		g.drawString("Average Waiting Time:",10,y[3] + offset * 2);
		g.drawString("Turnaround Time:",10,y[4] + offset * 2);
		g.setFont(smallFont);
		//Number 3
		for(int i = 0; i < currentProcess.size(); i++){
			ArrayList temp = (ArrayList) currentProcess.get(i);
			String pname = (String) temp.get(0);
			LinkedList temp1 = (LinkedList) temp.get(1);
			for(int j = 0; j<temp1.size(); j++){
				int[] times = (int[]) temp1.get(j);
				int startTime = times[0];
				int finishTime = times[1];
				g.drawString(pname,(startTime + finishTime) / 2 - 5,358); //Process Name
				g.drawLine(startTime, 340, startTime, 370); // Start Line
				g.drawLine(finishTime, 340, finishTime, 370); // Finish Line
				g.drawString(Integer.toString(finishTime),finishTime - 3,385); // Ruler
				g.drawString(Integer.toString(startTime),startTime - 3,385); // Ruler
			}
		}

		// get new currentProcess
		currentProcess = send4.receive();
		
		//4th Algorithm
		g.setFont(bigFont); 
		g.drawString("Fourth Algorithm",10,y[0] + offset * 3);
		g.drawRect(0, y[1] + offset * 3, 470, 30);
		g.drawString("Waiting Time:",10,y[2] + offset * 3);
		g.drawString("Average Waiting Time:",10,y[3] + offset * 3);
		g.drawString("Turnaround Time:",10,y[4] + offset * 3);
		g.setFont(smallFont);
		//Number 4
		for(int i = 0; i < currentProcess.size(); i++){
			ArrayList temp = (ArrayList) currentProcess.get(i);
			String pname = (String) temp.get(0);
			LinkedList temp1 = (LinkedList) temp.get(1);
			for(int j = 0; j<temp1.size(); j++){
				int[] times = (int[]) temp1.get(j);
				int startTime = times[0];
				int finishTime = times[1];
				g.drawString(pname,(startTime + finishTime) / 2 - 5,518); //Process Name
				g.drawLine(startTime, 500, startTime, 530); // Start Line
				g.drawLine(finishTime, 500, finishTime, 530); // Finish Line
				g.drawString(Integer.toString(finishTime),finishTime - 3,545); // Ruler
				g.drawString(Integer.toString(startTime),startTime - 3,545); // Ruler
			}
		}


		// get new currentProcess

		currentProcess = send5.receive();
		
		//5th Algorithm
		g.setFont(bigFont); 
		g.drawString("Fifth Algorithm",10,y[0] + offset * 4);
		g.drawRect(0, y[1] + offset * 4, 470, 30);
		g.drawString("Waiting Time:",10,y[2] + offset * 4);
		g.drawString("Average Waiting Time:",10,y[3] + offset * 4);
		g.drawString("Turnaround Time:",10,y[4] + offset * 4);
		g.setFont(smallFont);
		//Number 5
		for(int i = 0; i < currentProcess.size(); i++){
			ArrayList temp = (ArrayList) currentProcess.get(i);
			String pname = (String) temp.get(0);
			LinkedList temp1 = (LinkedList) temp.get(1);
			for(int j = 0; j<temp1.size(); j++){
				int[] times = (int[]) temp1.get(j);
				int startTime = times[0];
				int finishTime = times[1];
				g.drawString(pname,(startTime + finishTime) / 2 - 5,678); //Process Name
				g.drawLine(startTime, 660, startTime, 690); // Start Line
				g.drawLine(finishTime, 660, finishTime, 690); // Finish Line
				g.drawString(Integer.toString(finishTime),finishTime - 3,705); // Ruler
				g.drawString(Integer.toString(startTime),startTime - 3,705); // Ruler
			}
		}
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
		processes = new Process[num];

		//Populating the array with new processes
		for(int i = 0; i<num; i++){
			processes[i] = new Process("'P" + i +"'");
		}
		

		//Creating a deep copy of the process array
		copy = new Process[num];
		copy1 = new Process[num];
		copy2 = new Process[num];
		copy3 = new Process[num];
		copy4 = new Process[num]; 

		copy = p.createCopy(processes);
		copy1 = p.createCopy(processes);
		copy2 = p.createCopy(processes);	
		copy3 = p.createCopy(processes);	
		copy4 = p.createCopy(processes);	



		//------------------------------------------
		s = new SPN();		
		f = new Feedback(copy1, 50);
		r = new RoundRobin(copy2, 50);



		Process[] p1 = s.ShortProc(copy);
		Process[] r1 = r.scheduleProcesses();
		Process[] f1 = f.scheduleProcesses();

		System.out.println(Arrays.toString(f1));
		//System.out.println(Arrays.toString(p1));

		ArrayList returned1 = new ArrayList();
		ArrayList returned2 = new ArrayList();
		ArrayList returned3 = new ArrayList();
		ArrayList returned4 = new ArrayList();
		ArrayList returned5 = new ArrayList();

		LinkedList list1 = new LinkedList();


		for(int i = 0; i<p1.length; i++){
			returned1.add(p.print(p1[i]));
		}

		for(int i = 0; i<r1.length; i++){
			returned2.add(p.print(r1[i]));
		}

		for(int i = 0; i<f1.length; i++){
			returned3.add(p.print(f1[i]));
		}


		int totalWaitingTime1 = 0;
		int totalWaitingTime2 = 0;
		int totalWaitingTime3 = 0;
		int totalWaitingTime4 = 0;
		int totalWaitingTime5 = 0;

		for(int i = 0; i<returned1.size(); i++){
			totalWaitingTime1 = totalWaitingTime1 + (int)((ArrayList) returned1.get(i)).get(4);
		}
		for(int i = 0; i<returned2.size(); i++){
			totalWaitingTime2 = totalWaitingTime2 + (int)((ArrayList) returned2.get(i)).get(4);
		}
		for(int i = 0; i<returned3.size(); i++){
			totalWaitingTime3 = totalWaitingTime3 + (int)((ArrayList) returned3.get(i)).get(4);
		}
		
		//		list1 = (LinkedList) ((ArrayList) returned.get(0)).get(1);
		//		int[] a = (int[]) list1.get(0);
		//		System.out.println(Arrays.toString(a));
		//		System.out.println("List1: " + list1.get(0));


		//		System.out.println(returned);
		//		System.out.println(totalWaitingTime);

		send.sending(returned1);
		send.sendWait(totalWaitingTime1);
		
		send2.sending(returned2);
		send2.sendWait(totalWaitingTime2);
		
		send3.sending(returned3);
		send3.sendWait(totalWaitingTime3);

		JFrame frame = new JFrame();
		frame.setTitle("Process Simulator 2014");
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
