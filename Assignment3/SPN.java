
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
public class SPN extends Process{
    int i = 0;
    int j = 0;
    int totalProcessTime = 0;
    ArrayList<Process> finProcs = new ArrayList<Process>();

    public Process[] ShortProc(Process[] proc)
    {
        ArrayList<Process> procs = new ArrayList<Process>();
        for (i = 0; i<proc.length-1; i++ ) 
        {
            procs.add(proc[i]);
        }

        SortAT(procs);
        ////// First process that arrives //////
        totalProcessTime = 0;
        procs.get(0).setWaitingTime(0);
        totalProcessTime = totalProcessTime + procs.get(0).getServiceTime();
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
                if(procs.get(i).getArrivalTime() < totalProcessTime)
                {
                    procs.get(i).setWaitingTime(totalProcessTime - procs.get(i).getArrivalTime());
                    totalProcessTime = totalProcessTime + procs.get(i).getServiceTime();
                    finProcs.add(procs.get(i));
                    procs.remove(i);
                    executed=true;
                    break;
                }
                if(procs.get(i).getArrivalTime() == totalProcessTime)
                {
                    procs.get(i).setWaitingTime(0);
                    totalProcessTime = totalProcessTime + procs.get(i).getServiceTime();
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
    public ArrayList<Process> SortST(ArrayList<Process> procSTsort)
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
    public ArrayList<Process> SortAT(ArrayList<Process> procATsort)
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