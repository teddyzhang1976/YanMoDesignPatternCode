package com.company.bridgeinjavadesignmode;

/**
 * Actually, client only needs to pourCoffee(), he can pour milk added coffee or
 * fragrant coffee as he will. And he can pour different size of coffee as well
 * We can put a facade pattern in it to encapsulate it.
 */
public class Client {
	public static void main(String[] args) {
		//�ó�ţ��
		CoffeeImpSingleton coffeeImpSingleton = new CoffeeImpSingleton(new MilkCoffeeImp());
		//�б�����
		MediumCoffee mediumCoffee = new MediumCoffee();
		mediumCoffee.pourCoffee();

		//�󱭼���
		SuperSizeCoffee superSizeCoffee = new SuperSizeCoffee();
		superSizeCoffee.pourCoffee();
		
		coffeeImpSingleton = new CoffeeImpSingleton(new FragrantCoffeeImp());
		//�б�����
		mediumCoffee.pourCoffee();
		//�󱭼���
		superSizeCoffee.pourCoffee();
	}
}
