import java.util.*;
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
public class RoundRobin {
	Process[] processes;
	private int quantum, currentTime;
	
	/**
	 * Constructor for class Round Robin
	 * @param processes
	 * @param quantum
	 */
	public RoundRobin(Process[] processes, int quantum){
		this.processes = processes;
		this.quantum  = quantum;
		currentTime = 0;
	}
	
	/**
	 * Run the Round Robin Scheduling Algorithm
	 */
	private void runScheduler(){
		Arrays.sort(processes);
		
		//create queue of processes ordered by arrival time
		Queue<Process> queue = new LinkedList<Process>();
		for(Process process: processes){
			queue.add(process);
		}
		
		//for each process in the queue, run it for quantum-calculated amount of time
		while(queue.peek() != null){
			Process process = queue.poll();
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
			  queue.offer(process);
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
		runScheduler();
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

