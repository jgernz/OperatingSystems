
import java.lang.*;
import java.io.*;
import java.util.*;
/*
arrivalTime
serviceTime
timeRemaining
waitingTime
turnaroundTime
*/
public class SPN{
	public Object[] ShortProc(Object[] proc)
	{
		int i =0;
		ArrayList procs = new ArrayList ();
		for (i = 0; i<proc.length-1; i++ ) 
		{
			procs.add(proc[i]);
		}

		while(procs.isEmpty() != true)
		{
			//dance
		}




	}


/////// Sort by Service Time

	public ArrayList<Object> SortST(ArrayList<Object> procSTsort)
	{
		for (i = 0; i<procSTsort.size()-1; i++ ) 
		{
			if (procSTsort.get(i).serviceTime > procSTsort(i+1).serviceTime)
			{
				Object temp = procSTsort.get(i+1);
				procSTsort.set(i+1, procSTsort.get(i) );
				procSTsort.set(i, temp);
			}	
		}
	}

/////// Sort by Arrival Time

	public ArrayList<Object> SortAT(ArrayList<Object> procATsort)
	{
		for (i = 0; i<procATsort.size()-1; i++ ) 
		{
			if (procATsort.get(i).arrivalTime > procATsort(i+1).arrivalTime)
			{
				Object temp = procATsort.get(i+1);
				procATsort.set(i+1, procATsort.get(i) );
				procATsort.set(i, temp);
			}	
		}
	}
}