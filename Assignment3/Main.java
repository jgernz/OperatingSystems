/**
 * @author Josh Gerner
 * @author Rachel Rocole
 * @author James Ganzer
 * @author Berkley Lamb
 * @author Corey Schmitz
 * @author Brandon Harshaw
 * @author Jacob Ahnert
 */

import java.util.*;

public class Main {

	public static void main (String args[]){
		Scanner scanner = new Scanner(System.in);
		Process p = new Process();
		int num = 0;

		System.out.println("Please enter in how many processes you would like to create: ");
		System.out.println(">> ");

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

		Process[] processess = new Process[num];
		
		for(int i = 0; i<num; i++){
			processess[i] = new Process();
		}
		
		
		System.out.println(Arrays.toString(processess));
	}			
}
