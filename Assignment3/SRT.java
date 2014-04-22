/*try and do the shortest remaining time


arrivalTime
serviceTime
timeRemaining
waitingTime
turnaroundTime
*/

import java.lang.*;
import java.io.*;
import java.util.*;
public class SRT
{
    int i = 0;
    int j = 0;
    int totalProcessTime = 0;
    ArrayList<Process> finProcs = new ArrayList<Process>();
    
    public Process[] shortProcSRT(Process[] p)
    {
        ArrayList<Process> process = new ArrayList<Process>();
        for (i = 0; i<p.length-1; i++ ) 
        {
            process.add(p[i]);
        }
        finProcs = SortAT(process);
        Process[] arr = finProcs.toArray(new Process[finProcs.size()]);
       return arr;
    }
    
    
    public ArrayList<Process> SortAT(ArrayList<Process> procATsort)
    {
        for (j = 0; j<procATsort.size()-2; j++ ) 
        {
            for (i = j+1; i<procATsort.size()-1; i++ ) 
            {
                if (procATsort.get(j).getArrivalTime() > (procATsort.get(i)).getArrivalTime())
                {
                    Process temp = procATsort.get(i);
                    procATsort.set(i, procATsort.get(j) );
                    procATsort.set(j, temp);
                }   
            }
        }
        return procATsort;
    }
    
    
}


