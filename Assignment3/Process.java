import java.util.*;

/**
 * @author rocolr37
 *
 */
public class Process implements Comparable<Process>{
	private int arrivalTime, serviceTime, timeRemaining, waitingTime, turnaroundTime, finishTime;
	private String name;
	private LinkedList<int[]> activity;
	
	
	public Process(String name){
		Random rand = new Random();
		this.name = name;
		arrivalTime = rand.nextInt(15);
		serviceTime = rand.nextInt(60);
		timeRemaining = serviceTime;
		waitingTime = 0;
		turnaroundTime = 0;
		activity = new LinkedList<int[]>();
	}
	
	public void setActiveTimes(int startTime, int endTime){
		int[] times = new int[2];
		times[0] = startTime;
		times[1] = endTime;
		activity.add(times);
	}

	public int getArrivalTime(){
		
		return arrivalTime;
	}
	
	public int getServiceTime(){
		return serviceTime;
	}

	public int getWaitingTime(){
		return waitingTime;
	}
	
	public int getTurnaroundTime(){
		return turnaroundTime;
	}
	
	public int getFinishTime(){
		return finishTime;
	}
	
	public void calculateTimeRemaining(int timePassed){
		if(timePassed > timeRemaining){
			timeRemaining = 0;
		}
		timeRemaining = timeRemaining - timePassed;
	}
	
	public int getTimeRemaining(){
		return timeRemaining;
	}
	
	public void setWaitingTime(int time){
		waitingTime = time;
	}
	
	public void setTurnaroundTime(int time){
		turnaroundTime = time;
	}
	
	public void setFinishTime(int time){
		finishTime = time;
	}

	public Process[] test(Process[] processes){
		for(Process p: processes){
			p.arrivalTime -= 1;
			p.serviceTime -= 1;
			p.timeRemaining -= 1;
			p.waitingTime -= 1;
			p.turnaroundTime -= 1;
		}
		return processes;
	}
	
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
	
	public int compareTo(Process compareProcess) {
		int compareArrivalTime = ((Process) compareProcess).getArrivalTime();
		return this.arrivalTime - compareArrivalTime;
	}
	
	public void print(Process[] process){
		for(int[] l : this.activity){
			System.out.println(Arrays.toString(l));
		}
	}
	
	public String toString(){
		System.out.println(name);
		for(int[] l : activity){
			System.out.println(Arrays.toString(l));
		}
		
		
		return "Waiting Time: " + arrivalTime + "\n" +
				"Service Time: " + serviceTime + "\n" + 
				"Waiting Time: " + waitingTime + "\n" +
				"Turnaround Time: " + turnaroundTime + "\n\n";
	}
}
