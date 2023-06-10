import java.util.Scanner;

public class SKKUBANK {
	static double calculateBalance(double init, double rate, double count) { // function to calculate balance
		double result = 0;
		rate = rate / 100;
		
		result = init + (init * rate); // first calculate
		
		for (int i = 1; i < count; i++) { // from second iteration to last iteration
			result = result + (result * rate);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("Please, enter following information:");
		
		Scanner input = new Scanner(System.in); // input the information of user to use Scanner
												// and store the information to each variable
		System.out.print("Your name: ");
		String name = input.nextLine(); 
		
		System.out.print("Initial Balance: ");
		double initialBalance = input.nextDouble(); // to calculate the balance, use double type
		
		System.out.print("Percentage: ");
		double percentage = input.nextDouble();
		
		System.out.print("Number of year: ");
		double year = input.nextDouble();
		
		System.out.println("====================================");
		
		double futureBalance = calculateBalance(initialBalance, percentage, year); // call the function to calculate
		
		System.out.println("User name: " + name);  // print all the result
		System.out.println("Percentage: " + percentage);
		System.out.println("Your Balance (after " + year + " years): " + futureBalance);	
	}
}
