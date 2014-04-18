
public class RoundRobin {
	Process[] processes;
	private int quantum, currentTime;
	
	public RoundRobin(Process[] processes, int quantum){
		this.processes = processes;
		this.quantum  = quantum;
		currentTime = 0;
	}
	
	//create queue of processes ordered by arrival time
	
	//for each process in the queue, run it for quantum-calculated amount of time
	
	//remove it from the queue when it finishes, set turnaround time
	
	//for each cycle, update waiting time
	
	//if remaining time < quantum, add remaining time, otherwise, add quantum
	
	public void printWaitingTimes(){
		//print waiting times
	}
	
	public void printAvgWaitingTime(){
		//calculate average waiting time
		//print
	}
	
	public void printTurnaroundTimes(){
		//print turnaround times
	}
	
	public void printAvgTurnaroundTimes(){
		//calculate average turnaround time
		//print
	}
	
	public void printNormalizedTurnaroundTimes(){
		//find normalized turnaround times
		//print them out
	}
	
	public void printFinishTimes(){
		//print finish times
	}
}
