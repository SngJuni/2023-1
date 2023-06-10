import java.util.Random;
import java.util.Scanner;

public class testSystem {

	public static void main(String[] args) {
		
		int correctAnswer = 0; // variable for counting the correct answer
		
		Random randomGenerator = new Random(); // to generate random number
		for (int i = 1; i <= 5; i++) {
			int randomNum1 = randomGenerator.nextInt(100);
			int randomNum2 = randomGenerator.nextInt(100);
			
			Scanner input = new Scanner(System.in); // input the answer
			System.out.print("Question " + i + " - Calculate the addition (" + randomNum1 + " + " + randomNum2 + "): ");
			int answer = input.nextInt();
			
			if ((randomNum1 + randomNum2) == answer) { // if the answer is correct, correctAnswer plus 1
				correctAnswer++;
			}
		}
		System.out.println("Number of correct answers: " + correctAnswer); // print the number of correct answer
		
		switch (correctAnswer) { // use switch, and print the result depending on the number of correct answer
			case 0:
				System.out.println("Try again.");
				break;
			case 1:
				System.out.println("Very bad.");
				break;
			case 2:
				System.out.println("Not bad.");
				break;
			case 3:
				System.out.println("Good.");
				break;
			case 4:
				System.out.println("Very Good!");
				break;
			case 5:
				System.out.println("Excellent!");
				break;
			default:
				break;
		}
	}
}
