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
public class Process implements Comparable<Process>{
	private int arrivalTime, serviceTime, timeRemaining, waitingTime, turnaroundTime, finishTime;
	private String name;
	private LinkedList<int[]> activity;


	/**
	 * Constructor for Class Process
	 * @param name
	 */
	public Process(String name){
		Random rand = new Random();
		this.name = name;
		arrivalTime = rand.nextInt(15);
		serviceTime = rand.nextInt(50);
		timeRemaining = serviceTime;
		waitingTime = 0;
		turnaroundTime = 0;
		finishTime = 0;
		activity = new LinkedList<int[]>();
	}

	/**
	 * Stores the times that a process burst starts and ends
	 * @param startTime
	 * @param endTime
	 */
	public void setActiveTimes(int startTime, int endTime){
		int[] times = new int[2];
		times[0] = startTime;
		times[1] = endTime;
		activity.add(times);
	}



	/**
	 * Getter for arrivalTime
	 * @return int
	 */
	public int getArrivalTime(){

		return arrivalTime;
	}

	/**
	 * Getter for serviceTime
	 * @return int
	 */
	public int getServiceTime(){
		return serviceTime;
	}

	/**
	 * Getter for waitingTime
	 * @return int
	 */
	public int getWaitingTime(){
		return waitingTime;
	}

	/**
	 * Getter for turnaroundTime
	 * @return int
	 */
	public int getTurnaroundTime(){
		return turnaroundTime;
	}

	/**
	 * Getter for finishTime
	 * @return int
	 */
	public int getFinishTime(){
		return finishTime;
	}

	/**
	 * Calculates the time remaining for a process
	 * @param timePassed
	 */
	public void calculateTimeRemaining(int timePassed){
		if(timePassed > timeRemaining){
			timeRemaining = 0;
		}
		timeRemaining = timeRemaining - timePassed;
	}

	/**
	 * Getter for timeRemaining
	 * @return int
	 */
	public int getTimeRemaining(){
		return timeRemaining;
	}

	/**
	 * Setter for waitingTime
	 * @param time
	 */
	public void setWaitingTime(int time){
		waitingTime = time;
	}

	/**
	 * Setter for turnaroundTime
	 * @param time
	 */
	public void setTurnaroundTime(int time){
		turnaroundTime = time;
	}

	/**
	 * Setter for finishTime
	 * @param time
	 */
	public void setFinishTime(int time){
		finishTime = time;
	}

	/**
	 * Creates a copy of an array of processes
	 * @param processes
	 * @return Process[]
	 */
	public Process[] createCopy(Process[] processes){
		Process[] temp = new Process[processes.length];
		int i = 0;
		for(Process p: processes){
			Process temp1 = new Process(" ");

			temp1.name = p.name;
			temp1.arrivalTime = p.arrivalTime;
			temp1.serviceTime = p.serviceTime;
			temp1.timeRemaining = p.timeRemaining;
			temp1.waitingTime = p.waitingTime;
			temp1.turnaroundTime = p.turnaroundTime;

			temp[i] = temp1;
			i++;
		}
		return temp;
	}

	/**
	 * Prints an array of processes
	 * @param process
	 */
	public void print(Process[] process){
		for(int[] l : this.activity){
			System.out.println(Arrays.toString(l));
		}
	}

	public ArrayList print(Process p){
		ArrayList list = new ArrayList();
		list.add(p.name);
		
//		ArrayList a = new ArrayList();
//		for(int[] l : p.activity){		
//			a.add(Arrays.toString(l));
//		}
		
		list.add(p.activity);
		list.add(p.arrivalTime);
		list.add(p.serviceTime);
		list.add(p.waitingTime);
		list.add(p.turnaroundTime);
		
		return list;
	}
	
	
	public String toString(){
		System.out.println(name);
		for(int[] l : activity){
			System.out.println(Arrays.toString(l));
		}
		
		return "Arrival Time: " + arrivalTime + "\n" +
				"Service Time: " + serviceTime + "\n" + 
				"Waiting Time: " + waitingTime + "\n" +
				"Turnaround Time: " + turnaroundTime + "\n\n";
	}
	
	public int compareTo(Process compareProcess) {
		int compareArrivalTime = ((Process) compareProcess).getArrivalTime();
		return this.arrivalTime - compareArrivalTime;
	}
}
