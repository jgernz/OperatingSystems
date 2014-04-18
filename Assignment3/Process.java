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
	
	public void reset(){
		timeRemaining = serviceTime;
		waitingTime = 0;
		turnaroundTime = 0; 
	}
}
