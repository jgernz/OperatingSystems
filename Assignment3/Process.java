import java.util.*;

/**
 * @author rocolr37
 *
 */
public class Process {
	private int arrivalTime, serviceTime, cpuBursts;
	
	public Process(){
		Random rand = new Random();
		arrivalTime = rand.nextInt(15);
		serviceTime = rand.nextInt(60);
		cpuBursts = rand.nextInt(3);
	}
	
	public int getArrivalTime(){
		return arrivalTime;
	}
	
	public int getserviceTime(){
		return serviceTime;
	}
	
	public int getCPUBursts(){
		return cpuBursts;
	}
}
