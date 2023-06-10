
public class MacBook extends Product {
	
	private int memory; // definition of variables
	private String color;
	
	public MacBook(double basePrice, String model, int size, int quantity,int memory, String color) { // constructor
		super(basePrice, model, size, quantity, memory, color);
		setMemory(memory);
		setColor(color);
	}

	public void getUserSelection() { // call the parent's method to print option of model and size
									 // print out the result, if user's input has any option and charge
		super.getUserSelection();
		if (color.equals("base")) {
		} else if (color.equals("Gold")) {
			System.out.println("Color (Gold) ------ +$20");
		} else if (color.equals("Silver")) {
			System.out.println("Color (Silver) ------ +$10");
		}
		if (memory == 128) {
		} else if (memory == 256) {
			System.out.println("Memory (256) ------ +$100");
		} else if (memory == 512) {
			System.out.println("Memory (512) ------ +$300");
		}
	}
	
	public double calculateCost() { // call the parent's method to calculate option of model, size and quantity
									// calculate the cost to add the basePrice and optionPrice
		double totalCost = super.calculateCost();
		int quantity = super.getQuantity();
		double charge = 0;
		if (memory == 128) {
		} else if (memory == 256) {
			charge += 100;
		} else if (memory == 512) {
			charge += 300;
		}
		if (color.equals("base")) {
		} else if (color.equals("Gold")) {
			charge += 20;
		} else if (color.equals("Silver")) {
			charge += 10;
		}
		
		System.out.println("\n");
		totalCost = totalCost + charge;
		System.out.println("Total cost for one MacBook: $" + totalCost);
		totalCost = totalCost * quantity;
		System.out.println("Total cost (quantity X " + quantity + "): $" + totalCost);

		return 0;
	}
	
	// getter and setter method
	public int getMemory() {
		return memory;
	}
	public void setMemory(int memory) {
		this.memory = memory;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
