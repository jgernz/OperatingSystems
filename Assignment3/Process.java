import java.util.*;

/**
 * @author rocolr37
 *
 */
public class Process {
	private int arrivalTime, serviceTime, timeRemaining, waitingTime, turnaroundTime;
	
	public Process(){
		Random rand = new Random();
		arrivalTime = rand.nextInt(15);
		serviceTime = rand.nextInt(60);
		timeRemaining = serviceTime;
		waitingTime = 0;
		turnaroundTime = 0;
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
	
	public int calculateTimeRemaining(int timePassed){
		if(timePassed > timeRemaining){
			timeRemaining = 0;
		}
		timeRemaining = timeRemaining - timePassed;
		return timeRemaining;
	}
	
	public void setWaitingTime(int time){
		waitingTime = time;
	}
	
	public void setTurnaroundTime(int time){
		turnaroundTime = time;
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
			Process temp1 = new Process();
			
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
	
	
	public String toString(){
		return arrivalTime + " " + serviceTime + " " + timeRemaining + " " + waitingTime + " " + turnaroundTime;
	}
}
