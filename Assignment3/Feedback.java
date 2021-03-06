import java.lang.*;
import java.util.*;
import java.io.*;
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
public class Feedback{

	//4 queue levels for feedback loop

	Queue<Process> time1 = new LinkedList<Process>();
	Queue<Process> time2 = new LinkedList<Process>();
	Queue<Process> time3 = new LinkedList<Process>();
	Queue<Process> preemptive = new LinkedList<Process>();

	Process[] processes;
	private int quantum, currentTime;


	/**
	 * Constructor for Class Feedback
	 * @param processes
	 * @param quantum
	 */
	public Feedback(Process[] processes, int quantum){
		this.processes = processes;
		this.quantum  = quantum;
		currentTime = 0;
	}

	/**
	 * Run the Feedback Algorithm
	 */
	public void generateFeedback(){
		//method for feedback loop
		Arrays.sort(processes);

		for(Process process: processes){
			time1.add(process);
		}



		//first queue
		for (Process p: time1)
		{
			while(currentTime < p.getArrivalTime()){
				currentTime++;
			}

			int activeStart = currentTime;

			int j = 0;
			int burst = quantum;
			int time;

			if(p.getTimeRemaining()<burst)
			{
				burst = p.getTimeRemaining();
			}



			while(j <= burst){
				j++;
				currentTime++;
			}

			int activeFinish = currentTime;




			//update waiting times for other processes
			for(int i = 0; i < processes.length; i++){
				if(!(processes[i].equals(p))){
				if(processes[i].getFinishTime() == 0){
					if(currentTime >= processes[i].getArrivalTime()){
						if(processes[i].getWaitingTime() == 0){
							if(processes[i].getArrivalTime() < p.getArrivalTime()){
								processes[i].setWaitingTime(processes[i].getWaitingTime() + burst);
							}
							else
							processes[i].setWaitingTime(currentTime - processes[i].getArrivalTime());
						}
						else{
							processes[i].setWaitingTime(processes[i].getWaitingTime() + burst);
						}
					}
				}
				}
			}


			p.calculateTimeRemaining(burst);
			
			p.setActiveTimes(activeStart, activeFinish);
			//if finished set finish time else add to next queue
			if(p.getTimeRemaining()==0){
				p.setFinishTime(currentTime);
				p.setTurnaroundTime(currentTime - p.getArrivalTime());
			}else
				time2.add(p);

		}

		//2nd queue
		for (Process p: time2)
		{

			int activeStart = currentTime;

			int j = 0;
			int burst = quantum*2;
			int time;

			if(p.getTimeRemaining()<burst)
			{
				burst = p.getTimeRemaining();
			}



			while(j <= burst){
				j++;
				currentTime++;
			}

			int activeFinish = currentTime;




			//update waiting times for other processes
			for(int i = 0; i < processes.length; i++){
				if(!(processes[i].equals(p))){
				if(processes[i].getFinishTime() == 0){
					if(currentTime >= processes[i].getArrivalTime()){
						if(processes[i].getWaitingTime() == 0){
							if(processes[i].getArrivalTime() < p.getArrivalTime()){
								processes[i].setWaitingTime(processes[i].getWaitingTime() + burst);
							}
							else
							processes[i].setWaitingTime(currentTime - processes[i].getArrivalTime());
						}
						else{
							processes[i].setWaitingTime(processes[i].getWaitingTime() + burst);
						}
					}
				}
				}
			}



			p.calculateTimeRemaining(burst);

			p.setActiveTimes(activeStart, activeFinish);
			//if finished set finish time else add to next queue
			if(p.getTimeRemaining()==0){
				p.setFinishTime(currentTime);
				p.setTurnaroundTime(currentTime - p.getArrivalTime());
			}else
				time3.add(p);

		}


		//3rd level queue
		for (Process p: time3)
		{

			int activeStart = currentTime;

			int j = 0;
			int burst = quantum*4;
			int time;

			if(p.getTimeRemaining()<burst)
			{
				burst = p.getTimeRemaining();
			}



			while(j <= burst){
				j++;
				currentTime++;
			}

			int activeFinish = currentTime;




			//update waiting times for other processes
			for(int i = 0; i < processes.length; i++){
				if(!(processes[i].equals(p))){
				if(processes[i].getFinishTime() == 0){
					if(currentTime >= processes[i].getArrivalTime()){
						if(processes[i].getWaitingTime() == 0){
							if(processes[i].getArrivalTime() < p.getArrivalTime()){
								processes[i].setWaitingTime(processes[i].getWaitingTime() + burst);
							}
							else
							processes[i].setWaitingTime(currentTime - processes[i].getArrivalTime());
						}
						else{
							processes[i].setWaitingTime(processes[i].getWaitingTime() + burst);
						}
					}
				}
				}
			}



			p.calculateTimeRemaining(burst);

			p.setActiveTimes(activeStart, activeFinish);
			//if finished set finish time else add to next queue
			if(p.getTimeRemaining()==0){
				p.setFinishTime(currentTime);
				p.setTurnaroundTime(currentTime - p.getArrivalTime());
			}else
				preemptive.add(p);

		}


		//round robin for preemptive queue

		while(preemptive.peek() != null){
			Process process = preemptive.poll();
			while(currentTime < process.getArrivalTime()){
				currentTime++;
			}
			//record time burst starts
			int activeStart = currentTime;

			//Find burst time
			int burstTime;
			if(process.getTimeRemaining() < quantum){
				burstTime = process.getTimeRemaining();
			}
			else{
				burstTime = quantum;
			}

			//"Run" process
			int j = 0;
			while(j < burstTime){
				j++;
				currentTime++;
			}

			//record time burst finishes
			int activeFinish = currentTime;

			//update waiting times for other processes
			for(int i = 0; i < processes.length; i++){
				if(!(processes[i].equals(process))){
				if(processes[i].getFinishTime() == 0){
					if(currentTime >= processes[i].getArrivalTime()){
						if(processes[i].getWaitingTime() == 0){
							if(processes[i].getArrivalTime() < process.getArrivalTime()){
								processes[i].setWaitingTime(processes[i].getWaitingTime() + burstTime);
							}
							else
							processes[i].setWaitingTime(currentTime - processes[i].getArrivalTime());
						}
						else{
							processes[i].setWaitingTime(processes[i].getWaitingTime() + burstTime);
						}
					}
				}
				}
			}
			//update active time
			process.setActiveTimes(activeStart, activeFinish);

			//update remaining time for process
			process.calculateTimeRemaining(burstTime);

			if(process.getTimeRemaining() > 0){
				//move process to the back of the queue
				preemptive.offer(process);
			}

			else{
				//if process is finished, record finish time, remove it from the queue
				process.setFinishTime(currentTime);

				//update turn-around time
				process.setTurnaroundTime(currentTime - process.getArrivalTime());
			}
		}
	}



	/**
	 * Calls methods to run algorithm and print metrics
	 * @return processes
	 */
	public Process[] scheduleProcesses(){
		generateFeedback();
		printWaitingTimes();
		printAvgWaitingTime();
		printTurnaroundTimes();
		printAvgTurnaroundTimes();
		printFinishTimes();
		return processes;
	}

	/**
	 * Prints waiting Times for each process
	 */
	private void printWaitingTimes(){
		//print waiting times
		System.out.println("Waiting Times: ");
		for(Process process: processes){
			System.out.println(process.getWaitingTime());
		}
	}

	/**
	 * Calculates and prints average waiting time for schedule
	 */
	private void printAvgWaitingTime(){
		//calculate average waiting time
		int sum = 0;
		for(int i = 0; i < processes.length; i++){
			sum = sum + processes[i].getWaitingTime();
		}
		int avg = sum/processes.length;
		//print
		System.out.println("Average Waiting Time: " + avg);
	}

	/**
	 * Prints turn-around times for each process
	 */
	private void printTurnaroundTimes(){
		//print turn-around times
		System.out.println("Turn-Around Times: ");
		for(Process process: processes){
			System.out.println(process.getTurnaroundTime());
		}
	}

	/**
	 * Calculates and prints average turn-around time
	 */
	private void printAvgTurnaroundTimes(){
		//Calculate average turn-around time
		int sum = 0;
		for(int i = 0; i < processes.length; i++){
			sum = sum + processes[i].getTurnaroundTime();
		}
		int avg = sum/processes.length;
		//print
		System.out.println("Average Turnaround Time: " + avg);
	}

	/**
	 * Prints finish times for each process
	 */
	private void printFinishTimes(){
		//print finish times
		System.out.println("Finish Times: ");
		for(Process process: processes){
			System.out.println(process.getFinishTime());
		}
	}
}
