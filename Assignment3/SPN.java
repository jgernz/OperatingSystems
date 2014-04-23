import java.util.*;

/**
 * @author Josh Gerner
 * @author Rachel Rocole
 * @author James Ganzer
 * @author Berkley Lamb
 * @author Corey Schmitz
 * @author Brandon Harshaw
 * @author Jacob Ahnert
 * @version(20140417)
 */
public class SPN{
    int i = 0;
    int j = 0;
    int totalProcessTime = 0;
    ArrayList<Process> finProcs = new ArrayList<Process>();

    /**
     * Run the non-preemptive Shortest Process Scheduler Algorithm
     * @param proc
     * @return
     */
    public Process[] ShortProc(Process[] proc)
    {
        ArrayList<Process> procs = new ArrayList<Process>();
        for (i = 0; i<proc.length; i++ ) 
        {
            procs.add(proc[i]);
        }

        //Sort by arrival time
        procs = SortAT(procs);

///////////////////////////////////////////////////////
        ////// First process that arrives //////

        // Set total processing time to 0, it just started
        totalProcessTime = 0;

        // No wait so waiting time is 0
        procs.get(0).setWaitingTime(0);

        // Set active times
        procs.get(0).setActiveTimes(0, procs.get(0).getServiceTime());

        // Increase total process time
        totalProcessTime = totalProcessTime + procs.get(0).getServiceTime();

        // Set turn around time
        procs.get(0).setTurnaroundTime(totalProcessTime);

        // Set Time Remaining
        procs.get(0).calculateTimeRemaining(procs.get(0).getServiceTime());

        // Add to finished processes and remove from Process list //
        finProcs.add(procs.get(0));
        procs.remove(0);

///////////////////////////////////////////////////////

        //Sort by service time
        procs = SortST(procs);

        // Keep executing until all processes have executed
        while(procs.isEmpty() != true)
        {
        	// Keeps track if a Process was executed
            boolean executed=false;

            for (i=0;i<procs.size() ;i++ ) 
            {
                // find the next process to execute //
                if(procs.get(i).getArrivalTime() < totalProcessTime)
                {
                		// Set waiting time
                    procs.get(i).setWaitingTime(totalProcessTime - procs.get(i).getArrivalTime());

                    // Set Active Times
                    procs.get(i).setActiveTimes(totalProcessTime, (totalProcessTime + procs.get(i).getServiceTime()));
                    
                    // Increase totalProcessTime
                    totalProcessTime = totalProcessTime + procs.get(i).getServiceTime();
                    
                    // Set turn around time
                    procs.get(i).setTurnaroundTime(totalProcessTime + procs.get(i).getArrivalTime());
                    
                    // Set Time Remaining
                    procs.get(i).calculateTimeRemaining(procs.get(i).getServiceTime());

                    // Add Process to finished processes
                    finProcs.add(procs.get(i));
                    
                    // Remove Process from Processes to be finished list
                    procs.remove(i);
                    
                    // Process was executed - set to true
                    executed=true;
                    break;
                }
                if(procs.get(i).getArrivalTime() == totalProcessTime)
                {
                		// Set waiting time
                    procs.get(i).setWaitingTime(0);

                    // Set Active Times
                    procs.get(i).setActiveTimes(totalProcessTime, (totalProcessTime + procs.get(i).getServiceTime()));

                   	// Increase totalProcessTime
                    totalProcessTime = totalProcessTime + procs.get(i).getServiceTime();

                    // Set turn around time
                    procs.get(i).setTurnaroundTime(totalProcessTime + procs.get(i).getArrivalTime());
                    
                    // Set Time Remaining
                    procs.get(i).calculateTimeRemaining(procs.get(i).getServiceTime());

                    // Add Process to finished processes
                    finProcs.add(procs.get(i));
                    
                    // Remove Process from Processes to be finished list
                    procs.remove(i);
                    
                    // Process was executed - set to true
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
        // Change arraylist back to an Array and return it
        Process[] returnProcs = finProcs.toArray(new Process[finProcs.size()]);
        return ( returnProcs );
    }

    /**
     * Sorts by Service Time-Smallest service time first
     * @param procSTsort
     * @return
     */
    public ArrayList<Process> SortST(ArrayList<Process> procSTsort)
    {
        for (j = 0; j<procSTsort.size(); j++ ) 
        {
            for (i = j+1; i<procSTsort.size(); i++ ) 
            {
                if (procSTsort.get(j).getServiceTime() > procSTsort.get(i).getServiceTime())
                {
                    Process temp = procSTsort.get(i);
                    procSTsort.set(i, procSTsort.get(j) );
                    procSTsort.set(j, temp);
                }   
            }
        }
        return procSTsort;
    }

    /**
     * Sorts by arrival Time-Smallest arrival time first
     * @param procATsort
     * @return
     */
    public ArrayList<Process> SortAT(ArrayList<Process> procATsort)
    {
        for (j = 0; j<procATsort.size(); j++ ) 
        {
            for (i = j+1; i<procATsort.size(); i++ ) 
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
