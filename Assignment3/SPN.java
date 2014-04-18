
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
	int i = 0;
	int j = 0;
	public Object[] ShortProc(Object[] proc)
	{
		
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

/////// Sort by Arrival Time

	public ArrayList<Object> SortAT(ArrayList<Object> procATsort)
	{
		for (j = 0; j<procATsort.size()-2; j++ ) 
		{
			for (i = j+1; i<procATsort.size()-1; i++ ) 
			{
				if (procATsort.get(j).serviceTime > procATsort(i).serviceTime)
				{
					Object temp = procATsort.get(i);
					procATsort.set(i, procATsort.get(j) );
					procATsort.set(j, temp);
				}	
			}
		}
	}
}