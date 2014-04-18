
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

		totalProcessTime = procs.get(0).arrivalTime;
		procs.get(0).waitingTime = totalProcessTime;
		finProcs.add(procs.get(0));
		procs.remove(0);

		SortST(procs);

		while(procs.isEmpty() != true)
		{
			bool executed=false;
			for (i=0;i<procs.size()-1 ;i++ ) 
			{
				if(procs.get(i).arrivalTime == totalProcessTime)
				{
					procs.get(i).waitingTime = totalProcessTime;
					finProcs.add(procs.get(i));
					procs.remove(i);
					executed=true;
				}
			}
			if(executed==false)
			{
				totalProcessTime++;
			}
		}
		return ( finProcs.toArray() )
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
				if (procSTsort.get(j).serviceTime > procSTsort(i).serviceTime)
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
				if (procATsort.get(j).arrivalTime > procATsort(i).arrivalTime)
				{
					Object temp = procATsort.get(i);
					procATsort.set(i, procATsort.get(j) );
					procATsort.set(j, temp);
				}	
			}
		}
	}
}