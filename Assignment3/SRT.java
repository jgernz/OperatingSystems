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
            process = SortAT(process);
            
            ArrayList<Process> tempRemaining = new ArrayList<Process>();
            while(process.isEmpty() != true)
            {
                // Keeps track if a Process was executed
                boolean executed=false;

                for (i=1;i<process.size()-1 ;i++ ) 
                {
                    // find the next process to execute //
                    if(process.get(i).getServiceTime() < process.get(i-1).getTimeRemaining())
                    {
                            // Set waiting time
                        process.get(i).setWaitingTime(totalProcessTime - process.get(i).getArrivalTime());

                        // Set Active Times
                        process.get(i).setActiveTimes(totalProcessTime, (totalProcessTime + process.get(i).getServiceTime()));
                        
                        // Increase totalProcessTime
                        totalProcessTime = totalProcessTime + process.get(i).getServiceTime();
                        
                        // Set turn around time
                        process.get(i).setTurnaroundTime(totalProcessTime + process.get(i).getArrivalTime());
                        
                        // Set Time Remaining
                        process.get(i).calculateTimeRemaining(process.get(i).getServiceTime());

                        // Add Process to finished processes
                        finProcs.add(process.get(i));
                        
                        // Remove Process from Processes to be finished list
                        /////process.remove(i);
                        process = SortRT(process);
                        // Process was executed - set to true
                        executed=true;
                        break;
                    }
                    if(process.get(i).getArrivalTime() == totalProcessTime)
                    {
                            // Set waiting time
                        process.get(i).setWaitingTime(0);

                        // Set Active Times
                        process.get(i).setActiveTimes(totalProcessTime, (totalProcessTime + process.get(i).getServiceTime()));

                        // Increase totalProcessTime
                        totalProcessTime = totalProcessTime + process.get(i).getServiceTime();

                        // Set turn around time
                        process.get(i).setTurnaroundTime(totalProcessTime + process.get(i).getArrivalTime());
                        
                        // Set Time Remaining
                        process.get(i).calculateTimeRemaining(process.get(i).getServiceTime());

                        // Add Process to finished processes
                        finProcs.add(process.get(i));
                        
                        // Remove Process from Processes to be finished list
                        if (process.get(i).getTimeRemaining() == 0)
                        {
                            process.remove(i);
                        }
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
            Process[] arr = finProcs.toArray(new Process[finProcs.size()]);
           return (arr);
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

        public ArrayList<Process> SortRT(ArrayList<Process> procRTsort)
        {
            for (j = 0; j<procRTsort.size()-2; j++ ) 
            {
                for (i = j+1; i<procRTsort.size()-1; i++ ) 
                {
                    if (procRTsort.get(j).getTimeRemaining() > (procRTsort.get(i)).getServiceTime())
                    {
                        Process temp = procRTsort.get(i);
                        procRTsort.set(i, procRTsort.get(j) );
                        procRTsort.set(j, temp);
                    }   
                }
            }
            return procRTsort;
        }
        
        
    }


