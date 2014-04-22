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

import java.util.*;

public class Main {

	public static void main (String args[]){
		Scanner scanner = new Scanner(System.in);
		Process p = new Process(" ");
		
		int num = 0;

		//Asking for user input
		System.out.println("Please enter in how many processes you would like to create: ");
		System.out.print(">> ");

		//Making sure user input is valid
		try{
			num = scanner.nextInt();
			if(num < 0){
				throw new InputMismatchException();
			}
		}
		catch(InputMismatchException e){
			boolean flag = true;
			
			while(flag){
				flag = false;
				try{
					System.out.println("Error: Input must be an integer greater than or equal to zero. Please reenter how many processes you would like to create.");
					System.out.print(">> ");
					scanner.nextLine();
					num = scanner.nextInt();
					if(num < 0){
						throw new InputMismatchException();
					}
				}
				catch(InputMismatchException e1){
					flag = true;
				}
			}
		}

		//Creating a new array of processes
		Process[] processes = new Process[num];
		
		//Populating the array with new processes
		for(int i = 0; i<num; i++){
			processes[i] = new Process("'P" + i +"'");
		}
		
		//Creating a deep copy of the process array
		Process[] copy = new Process[num];
		copy = p.createCopy(processes);	

		
		copy[0].setActiveTimes(1,1);
		copy[0].setActiveTimes(8,9);
		copy[1].setActiveTimes(0,2);
		copy[2].setActiveTimes(8,2);
		
		System.out.println(Arrays.toString(copy));
	}			
}
