
import java.lang.*;
import java.io.*;
import java.util.*;
/*
arrivalTime
serviceTime
timeRemaining
waitingTime
turnaroundTime

sort by arrival time
execute 1st process 
-change attributes
add to finished list
remove from togo list

sort by process time
while togo.isempty != false

change attributes
add to finished list
remove from togo list


*/
public class SPN{
	int i = 0;
	int j = 0;
	int totalProcessTime = 0;
	ArrayList finProcs = new ArrayList();

	public Object[] ShortProc(Object[] proc)
	{
		ArrayList procs = new ArrayList ();
		for (i = 0; i<proc.length-1; i++ ) 
		{
			procs.add(proc[i]);
		}

		SortAT(procs);
		////// First process that arrives //////
		totalProcessTime = 0;
		procs.get(0).waitingTime = 0;
		totalProcessTime = totalProcessTime + procs.get(0).serviceTime;
		// Add to finished processes and remove from Process list //
		finProcs.add(procs.get(0));
		procs.remove(0);

		SortST(procs);

		// Keep executing until all processes have executed
		while(procs.isEmpty() != true)
		{
			boolean executed=false;
			for (i=0;i<procs.size()-1 ;i++ ) 
			{
				// find the next process to execute //
				if(procs.get(i).arrivalTime < totalProcessTime)
				{
					procs.get(i).waitingTime = totalProcessTime - procs.get(i).arrivalTime;
					totalProcessTime = totalProcessTime + procs.get(i).serviceTime;
					finProcs.add(procs.get(i));
					procs.remove(i);
					executed=true;
					break;
				}
				if(procs.get(i).arrivalTime == totalProcessTime)
				{
					procs.get(i).waitingTime = 0;
					totalProcessTime = totalProcessTime + procs.get(i).serviceTime;
					finProcs.add(procs.get(i));
					procs.remove(i);
					executed=true;
					break;
				}
			}
			// If executed == true then totalProcessTime has incremented already
			// so no need to increment it again
			if(executed==false)
			{
				totalProcessTime++;
			}
		}
		return ( finProcs.toArray() );
	}

//////////////////////////////////////////////////////////////////////
///////////////////// Sort by Service Time ///////////////////////////
//////////////////////////////////////////////////////////////////////
	public ArrayList<Object> SortST(ArrayList<Object> procSTsort)
	{
		for (j = 0; j<procSTsort.size()-2; j++ ) 
		{
			for (i = j+1; i<procSTsort.size()-1; i++ ) 
			{
				if (procSTsort.get(j).serviceTime > procSTsort.get(i).serviceTime)
				{
					Object temp = procSTsort.get(i);
					procSTsort.set(i, procSTsort.get(j) );
					procSTsort.set(j, temp);
				}   
			}
		}
	}

//////////////////////////////////////////////////////////////////////
///////////////////// Sort by Arrival Time ///////////////////////////
//////////////////////////////////////////////////////////////////////
	public ArrayList<Object> SortAT(ArrayList<Object> procATsort)
	{
		for (j = 0; j<procATsort.size()-2; j++ ) 
		{
			for (i = j+1; i<procATsort.size()-1; i++ ) 
			{
				if (procATsort.get(j).arrivalTime > (procATsort.get(i)).arrivalTime)
				{
					Object temp = procATsort.get(i);
					procATsort.set(i, procATsort.get(j) );
					procATsort.set(j, temp);
				}   
			}
		}
	}
}