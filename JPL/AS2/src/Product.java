
public class Product {
	
	private double basePrice; // definition of variables
	private double totalCost;
	private String model;
	private int quantity;
	private int size;
	
	public Product() { // constructor to initialization
		basePrice = 0;
		totalCost = 0;
		model = null;
		quantity = 0;
		size = 0;
	}
	public Product(double basePrice, String model, int size, int quantity,int memory, String color) { // constructor for MacBook
		setBasePrice(basePrice);
		setModel(model);
		setSize(size);
		setQuantity(quantity);
	}
	public Product(double basePrice, String model, int quantity,int memory, String color) { // constructor for iPhone
		setBasePrice(basePrice);
		setModel(model);
		setQuantity(quantity);
	}
	public Product(double basePrice, String model, int quantity) { // constructor for AirPods
		setBasePrice(basePrice);
		setModel(model);
		setQuantity(quantity);
	}
	
	public void getUserSelection() { // print out the result, if user's input has any option and charge
		System.out.println("====== check ======");
		if (model.equals("Air")) { // if MacBook
		} else if (model.equals("Pro")) {
			System.out.println("Model (Pro) ----- +$200");
		}
		else if (model.equals("iPhone12")) { // if iPhone
		} else if (model.equals("iPhone13")) {
			System.out.println("Model (iPhone13) ----- +$200");
		}
		else if (model.equals("AirPods3")) { // if AirPods
		} else if (model.equals("AirPodsPro")) {
			System.out.println("Model (AirPodsPro) ----- +$100");
		} else if (model.equals("AirPodsMax")) {
			System.out.println("Model (AirPodsMax) ----- +$200");
		}
		if (size == 13) { // if MacBook
		} else if (size == 15) {
			System.out.println("Size (15) ----- +$200");
		}
	}
	
	public double calculateCost() { // calculate the cost to add the basePrice and optionPrice
		double charge = 0;
		if (model.equals("Air")) { // if MacBook
		} else if (model.equals("Pro")) {
			charge += 200;
		}
		else if (model.equals("iPhone12")) { // if iPhone
		} else if (model.equals("iPhone13")) {
			charge += 200;
		}
		else if (model.equals("AirPods3")) { // if AirPods
		} else if (model.equals("AirPodsPro")) {
			charge += 100;
		} else if (model.equals("AirPodsMax")) {
			charge += 200;
		}
		if (size == 13) { // if MacBook
		} else if (size == 15) {
			charge += 200;
		}
		
		totalCost = basePrice + charge;
		return totalCost;
	}
	
	//getter and setter method
	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
