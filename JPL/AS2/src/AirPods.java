
public class AirPods extends Product {
	
	public AirPods(double basePrice, String model, int quantity) { // constructor
		super(basePrice, model, quantity);
	}
	
	public void getUserSelection() { // call the parent's method to print option of model
		super.getUserSelection();
	}
	
	public double calculateCost() { // call the parent's method to calculate option of model and quantity
									// calculate the cost to add the basePrice and optionPrice
		double totalCost = super.calculateCost();
		int quantity = super.getQuantity();
		
		System.out.println("\n");
		System.out.println("Total cost for one iPhone: $" + totalCost);
		totalCost = totalCost * quantity;
		System.out.println("Total cost (quantity X " + quantity + "): $" + totalCost);

		return 0;
	}
	
}
