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

		System.out.println("Please enter in how many processes you would like to create: ");
		System.out.print(">> ");

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

		Process[] processes = new Process[num];
		
		for(int i = 0; i<num; i++){
			processes[i] = new Process("'P" + i +"'");
		}
		

		Process[] modified = new Process[num];
		modified = p.createCopy(processes);
		
		System.out.println("Before Modification");
		System.out.println("Original:    " + Arrays.toString(processes));
		System.out.println("Modififed: " + Arrays.toString(modified) + "\n");	
		
		modified = p.test(modified);
		
		System.out.println("After Modification");
		System.out.println("Original:    " + Arrays.toString(processes));
		System.out.println("Modififed: " + Arrays.toString(modified) + "\n");
		
		modified = p.createCopy(processes);
		
		System.out.println("After Reset");
		System.out.println("Original:    " + Arrays.toString(processes));
		System.out.println("Modififed: " + Arrays.toString(modified) + "\n");
		
		modified = p.test(modified);
		
		System.out.println("After Another Modification");
		System.out.println("Original:    " + Arrays.toString(processes));
		System.out.println("Modififed: " + Arrays.toString(modified) + "\n");
		
		modified = p.createCopy(processes);
		
		System.out.println("After Another Reset");
		System.out.println("Original:    " + Arrays.toString(processes));
		System.out.println("Modififed: " + Arrays.toString(modified) + "\n");
		
		processes[0].setActiveTimes(1,1);
		processes[0].setActiveTimes(8,9);
		processes[1].setActiveTimes(0,2);
		processes[2].setActiveTimes(8,2);
		
		System.out.println(Arrays.toString(processes));
	}			
}
