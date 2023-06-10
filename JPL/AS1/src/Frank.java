import java.util.Scanner;

public class Frank {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); // input number of days
		
		System.out.print("Enter the number of days: ");
		int days = input.nextInt();
		
		int quotient = days / 7; // store quotient of (days / 7) to calculate saving
		int remainder = days % 7;
		
		int saving = remainder * (quotient + 1); // first, add the last number like (3*2)
		
		for (; quotient > 0; quotient--) { // iterate, quotient to be 0 like (2*7)+(1*7)
			saving = saving + (quotient * 7);
		}
		
		System.out.println("Total Frank's saving (after " + days + " days): " + saving);
	}

}
