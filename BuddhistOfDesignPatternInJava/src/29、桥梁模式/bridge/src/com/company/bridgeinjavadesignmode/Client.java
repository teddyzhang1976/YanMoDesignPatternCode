package com.company.bridgeinjavadesignmode;

/**
 * Actually, client only needs to pourCoffee(), he can pour milk added coffee or
 * fragrant coffee as he will. And he can pour different size of coffee as well
 * We can put a facade pattern in it to encapsulate it.
 */
public class Client {
	public static void main(String[] args) {
		//拿出牛奶
		CoffeeImpSingleton coffeeImpSingleton = new CoffeeImpSingleton(new MilkCoffeeImp());
		//中杯加奶
		MediumCoffee mediumCoffee = new MediumCoffee();
		mediumCoffee.pourCoffee();

		//大杯加奶
		SuperSizeCoffee superSizeCoffee = new SuperSizeCoffee();
		superSizeCoffee.pourCoffee();
		
		coffeeImpSingleton = new CoffeeImpSingleton(new FragrantCoffeeImp());
		//中杯加奶
		mediumCoffee.pourCoffee();
		//大杯加奶
		superSizeCoffee.pourCoffee();
	}
}
