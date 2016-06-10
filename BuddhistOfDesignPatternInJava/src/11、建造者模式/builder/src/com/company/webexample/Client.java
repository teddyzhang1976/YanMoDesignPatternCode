package com.company.webexample;
/**
 * 
 * @author teddy
 * Builder Pattern:
 * Definition:
 * 	Construct a complex object from simple objects step by step
 * Where to use & benefits.
 * 	1. Make a complex object by specifying only its type and content. The build
 * 	   object is shielded from the details of its construction. 
 * 	2. Want to decouple the process of building a complex object from the parts
 * 	   that make up the object. 
 *	3. Isolate code for construction and representation.
 *	4. Give you finer control over the construction process.
 * Related roles:
 * 	1.	The complex object. (The type and its content only, here is House.java)
 * 	2.	The object builder.	(The separate parts to build a house. Here are IHouseBuilder and its two concreteClass.
 * 	3.	The director. (A director is charge of operate the builder in some order to build different house.here is WorkShop.java. 
 */
public class Client {
  
    public static void main(String[] args) {
       
       HouseBuilder one = new OneStoryHouse("2 bedrooms, 2.5 baths, 2-car garage, 1500 sqft");
       HouseBuilder two = new TwoStoryHouse("4 bedrooms, 4 baths, 3-car garage, 5000 sqft");

       WorkShop shop = new WorkShop();
       shop.construct(one);
       shop.construct(two);
    
   
       System.out.println("Check house building progress: \n");
       System.out.println(one.showProgress());
       System.out.println(two.showProgress());
   }
}

