import java.util.*;


public class RoundRobin {
	Process[] processes;
	private int quantum, currentTime;
	
	public RoundRobin(Process[] processes, int quantum){
		this.processes = processes;
		this.quantum  = quantum;
		currentTime = 0;
	}
	
	private void runScheduler(){
		Arrays.sort(processes);
		
		//create queue of processes ordered by arrival time
		Queue<Process> queue = new LinkedList<Process>();
		for(Process process: processes){
			queue.add(process);
		}
		
		//for each process in the queue, run it for quantum-calculated amount of time
		for(int i = 0; i < queue.size(); i++){
			
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
			while(currentTime < burstTime){
				currentTime++;
			}
			
			//record time burst finishes
			int activeFinish = currentTime;
			
			//update waiting times for other processes
			for(int i = 0; i < processes.length; i++){
				if(!(processes[i].equals(process))){
					processes[i].setWaitingTime(processes[i].getWaitingTime() + burstTime);
				}
			}
			
			//update active time
			process.setActiveTimes(activeStart, activeFinish);
			
			//update remaining time for process
			process.calculateTimeRemaining(burstTime);
			
			if(process.getTimeRemaining() > 0){
			//move process to the back of the queue
			  queue.remove(process);
			  queue.add(process);
			}
			
			else{
			//if process is finished, record finish time, remove it from the queue
				process.setFinishTime(currentTime);
				queue.remove(process);
				
			//update turn-around time
				process.setTurnaroundTime(currentTime);
			}
		}
	}
	
	public Process[] scheduleProcesses(){
		runScheduler();
		return processes;
	}
	
	public void printWaitingTimes(){
		//print waiting times
		System.out.println("Waiting Times: ");
		for(Process process: processes){
			System.out.println(process.getWaitingTime());
		}
	}
	
	public void printAvgWaitingTime(){
		//calculate average waiting time
		int sum = 0;
		for(int i = 0; i < processes.length; i++){
			sum = sum + processes[i].getWaitingTime();
		}
		int avg = sum/processes.length;
		//print
		System.out.println("Average Waiting Time: " + avg);
	}
	
	public void printTurnaroundTimes(){
		//print turn-around times
		System.out.println("Turn-Around Times: ");
		for(Process process: processes){
			System.out.println(process.getTurnaroundTime());
		}
	}
	
	public void printAvgTurnaroundTimes(){
		//Calculate average turn-around time
		int sum = 0;
		for(int i = 0; i < processes.length; i++){
			sum = sum + processes[i].getTurnaroundTime();
		}
		int avg = sum/processes.length;
		//print
		System.out.println("Average Turnaround Time: " + avg);
	}
	
	public void printNormalizedTurnaroundTimes(){
		//find normalized turn-around times, whatever that is
		//print them out
	}
	
	public void printFinishTimes(){
		//print finish times
		System.out.println("Finish Times: ");
		for(Process process: processes){
			System.out.println(process.getFinishTime());
		}
	}
}

