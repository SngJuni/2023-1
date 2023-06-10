import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String start = "==== Welcome Apple Korea ====" + "\n" + // start question
					   "What do you want to buy?" + "\n" +
					   "Chose 1 for MacBook" + "\n" +
					   "Chose 2 for iPhone" + "\n" +
					   "Chose 3 for AirPods" + "\n" +
					   "Chose 0 for Exit" + "\n" +
					   "Please enter your choice: ";
		String question = "Do you want to purchase something else (Yse: 1, No: 0): "; // after purchase, ask another purchase
		String end = "Thank you for your purchase!"; // all purchases is done
		
		int choice = 1; // if this value is 0, the iteration will be terminated
		while (choice != 0) {
			Scanner input = new Scanner(System.in);
			System.out.print(start);
			
			choice = input.nextInt();
			
			if (choice == 0) { // terminate this iteration
				break;
			} else if (choice == 1) { // when user input 1, and keep going the process of purchase
				double basePrice = 1000;
				System.out.println("=================================");
				System.out.println("Base cost of the MacBook: $1000");
				System.out.print("Which model do you want (Pro or Air): ");
				input.nextLine();
				String model = input.nextLine();
				System.out.print("Size (13, 15): ");
				int size = input.nextInt();
				System.out.print("Color (base, Gold, Silver): ");
				input.nextLine();
				String color = input.nextLine();
				System.out.print("Memory (128, 256, 512): ");
				int memory = input.nextInt();
				System.out.print("How many do you want to buy: ");
				int quantity = input.nextInt();
				
				MacBook macbook = new MacBook(basePrice, model, size, quantity, memory, color); // construct the class to use user's inputs 
				macbook.getUserSelection(); // call the method
				macbook.calculateCost();    // call the method
			} else if (choice == 2) { // when user input 2, and keep going the process of purchase
				double basePrice = 800;
				System.out.println("=================================");
				System.out.println("Base cost of the MacBook: $800");
				System.out.print("Which model do you want (iPhone12 or iPhone13): ");
				input.nextLine();
				String model = input.nextLine();
				System.out.print("Color (base, Gold, Silver): ");
				String color = input.nextLine();
				System.out.print("Memory (64, 128, 256, 512): ");
				int memory = input.nextInt();
				System.out.print("How many do you want to buy: ");
				int quantity = input.nextInt();
				
				iPhone iphone = new iPhone(basePrice, model, quantity, memory, color); // construct the class to use user's inputs
				iphone.getUserSelection(); // call the method
				iphone.calculateCost(); // call the method
			} else if (choice == 3) { // when user input 3, and keep going the process of purchase
				double basePrice = 120;
				System.out.println("=================================");
				System.out.println("Base cost of the MacBook: $120");
				System.out.print("Which model do you want (AirPods3 or AirPodsPro or AirPodsMax): ");
				input.nextLine();
				String model = input.nextLine();
				System.out.print("How many do you want to buy: ");
				int quantity = input.nextInt();
				
				AirPods airpods = new AirPods(basePrice, model, quantity); // construct the class to use user's inputs
				airpods.getUserSelection(); // call the method
				airpods.calculateCost(); // call the method
			}
			
			System.out.print(question); // after the purchase, ask another purchase
			int answer = input.nextInt();
			
			choice = answer;
		}

		
		System.out.println(end); // when all purchases are done
	}

}
