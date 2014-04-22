import java.lang.*;
import java.util.*;
import java.io.*;


public class Feedback{

	//4 queue levels for feedback loop
	
	Queue<Process> time1 = new LinkedList<Process>();
	Queue<Process> time2 = new LinkedList<Process>();
	Queue<Process> time3 = new LinkedList<Process>();
	Queue<Process> preemptive = new LinkedList<Process>();
	Queue<Process> finalProc = new LinkedList<Process>();
	
	
	public Queue<Process> generateFeedbackSort(ArrayList<Process> proc){
	//method for feedback loop
		
		
		//sort arraylist by arrival time
		Collections.sort(proc, new Comparator<Process>() {
	        
			@Override
			public int compare(Process proc1, Process proc2) {
				// TODO Auto-generated method stub
				return Integer.compare(proc1.getArrivalTime(), proc2.getArrivalTime());
			}
	    });
		
		for (Process p: proc)
		{
			if(p.getServiceTime()<=8)
				time1.add(p);
			else if(p.getServiceTime()<=16)
				time2.add(p);
			else if(p.getServiceTime() <= 32)
				time3.add(p);
			else
				preemptive.add(p);
		}
		
		finalProc.addAll(time1);
		finalProc.addAll(time2);
		finalProc.addAll(time3);
		finalProc.addAll(preemptive);
		
		return finalProc;
	
	}

}

