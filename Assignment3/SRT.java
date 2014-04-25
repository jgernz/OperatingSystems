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
            for (i = 0; i<p.length; i++ ) 
            {
                process.add(p[i]);
            }

            //Sort by arrival time
            process = SortST(process);
            //process = SortAT(process);
            // Set total processing time to 0, it just started
            totalProcessTime = 0;
            // No wait so waiting time is 0
            //process.get(0).setWaitingTime(0);
            // Set active times
            //process.get(0).setActiveTimes(0, process.get(0).getServiceTime());
            // Increase total process time
            //totalProcessTime = totalProcessTime + process.get(0).getServiceTime();
            // Set turn around time
            //process.get(0).setTurnaroundTime(totalProcessTime);
            // Set Time Remaining
            //process.get(0).calculateTimeRemaining(process.get(0).getServiceTime());
            // Add to finished processes and remove from Process list //
            //finProcs.add(process.get(0));
            //process.remove(0);
            // Keep executing until all processes have executed
            while(process.isEmpty() != true)
            {
                // Keeps track if a Process was executed
                boolean executed=false;

                for (i=0;i<process.size() ;i++ ) 
                {
                   
                    if(process.get(i).getServiceTime() == 0)
                    {
                        System.out.println("process burst time = 0");

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
                        process.remove(i);
                        
                        // Process was executed - set to true
                        executed=true;
                        break;
                    }


                    //if(process.get(i).getServiceTime() < process.get(i+1).getServiceTime())
                    if( interupt(process.get(i), process) == false)
                    {
                        System.out.println("interupt = false");
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
                        process.remove(i);
                        
                        // Process was executed - set to true
                        executed=true;
                        break;
                    }
                    else
                    {
                        System.out.println("interupt = true");

                            // Set waiting time
                        process.get(i).setWaitingTime(totalProcessTime - process.get(i).getArrivalTime());

                        // Set Active Times
                        process.get(i).setActiveTimes(totalProcessTime, (totalProcessTime + process.get(i).getServiceTime()));

                        // Increase totalProcessTime
                        totalProcessTime = totalProcessTime + process.get(i).getServiceTime();

                        // Set turn around time
                        process.get(i).setTurnaroundTime(totalProcessTime + process.get(i).getArrivalTime());
                        
                        

                        // Add Process to finished processes
                        finProcs.add(process.get(i));
                        // Set Time Remaining
                        process.get(i).calculateTimeRemaining( process.get(i).getServiceTime() ) ;
                        process.add( process.get(i) );
                        // Remove Process from Processes to be finished list
                        process.remove(i);
                        
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

        /*public boolean checkArray(<Process> p1, <Process> p2)
        {
            if ( (p1.getarrivalTime()) < (p2.getArrivalTime()+p2.getServiceTime()) && p1.getArrivalTime() > p2.getArrivalTime() )
            {
                if ( p1.getTimeRemaining() < p2.getTimeRemaining() )
                    {return true;}
                else
                    {return false;}
            }
            else
                {return false;}


        }*/

        public boolean interupt(Process p1, ArrayList<Process> proc)
        {
            boolean bool = false;
            for(int i = 0; i<proc.size(); i++)
            {
                if ( (proc.get(i).getArrivalTime()) < (p1.getArrivalTime()+p1.getServiceTime()) && p1.getArrivalTime() < proc.get(i).getArrivalTime() )
                {
                    System.out.println("inside first if");
                    if ( p1.getTimeRemaining() < proc.get(i).getTimeRemaining() )
                        {bool = true;}
                    else
                        {bool = false;}
                }
                else
                    {bool = false;}
            }
            return bool;

        }                      
    }


