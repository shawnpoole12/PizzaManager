package mainClasses;

import java.util.Scanner;
import shapes.*;
import timeStamp.*;
import ingredients.*;

/**
 * Vikas Sidhu
 * March 12, 2016
 * Instructor: Rob Nash
 * CSS 162
 * This class is called MyPizzaManager and extends PizzaManager 
 *
 */

public class MyPizzaManager extends PizzaManager {
	/*
	 *  TODO: Data definitions here.  
	 *  
	 */
	ArrayList<Pizza> pizzaData = new ArrayList<Pizza>();
	
	
	
	 /* 
	  *  TODO: Overridden methods here.  
	  *  
	  */
	@Override
	protected void displayAllPizzas() {
		System.out.println(pizzaData.toString());
	}
	
	@Override
	protected char getNextChar() {
		char retVal;
		Scanner inputChar = new Scanner(System.in);
		retVal = inputChar.next().charAt(0);
		return retVal;
	}
	
	@Override 
	protected int getNextInt() {
		int retVal;
		Scanner inputInt = new Scanner(System.in);
		retVal = inputInt.nextInt();
		return retVal;
	}
	
	@Override
	protected void eatSomePizza() {
		Scanner pizzaToEat = new Scanner(System.in);
		System.out.println("What pizza do you want to eat. Pick an Index.");
		int pizzaIndex = pizzaToEat.nextInt();
		if (pizzaIndex < 0 || pizzaIndex > pizzaData.size()-1) {
			throw new PizzaException("Invalid index");
		}
		System.out.println("How much of the pizza do you want to eat. Enter as a fraction");
		String inputFraction = pizzaToEat.next();
		String[] splitFraction = inputFraction.split("/");
		int pizzaNumer = Integer.valueOf(splitFraction[0]);
		int pizzaDenom = Integer.valueOf(splitFraction[1]);
		Fraction amountOfPizzaToEat = new Fraction(pizzaNumer, pizzaDenom);
		try {
			pizzaData.get(pizzaIndex).eatSomePizza(amountOfPizzaToEat);
		}
		catch(PizzaException e){
			System.out.println("The pizza has been eaten, it will be removed");
			pizzaData.remove(pizzaIndex);
		}
	 }
	
	@Override
	protected void addRandomPizza() {
		Pizza randPizza = new Pizza();
		pizzaData.insert(randPizza, pizzaData.size());
	}
	
	@Override 
	protected void quickSortByCalories() {
		if(pizzaData.size() == 0) {
			throw new PizzaException("Can sort empty list");
		}
		quickSortByCalories(0, pizzaData.size()-1);
	}
	private void quickSortByCalories(int low, int high) {
		Pizza pivot = pizzaData.get((low+high)/2);
		int left = low;
		int right = high;
		while (left <= right) {
			while(pizzaData.get(left).compareToByCalories(pivot) < 0) {
				left++;
			}
			while(pizzaData.get(right).compareToByCalories(pivot) > 0) {
				right--;
			}
			if (low <= right) {
				pizzaData.swap(left, right);
				left++;
				right--;
			}
		}
		if (low < right) {
			quickSortByCalories(low, right);
		}
		if (left < high) {
			quickSortByCalories(left, high);
		}
	}
	
	@Override
	protected void quickSortBySize() {
		if(pizzaData.size() == 0) {
			throw new PizzaException("Can sort empty list");
		}
		quickSortBySize(0, pizzaData.size()-1);
	}
	private void quickSortBySize(int low, int high) {
		Pizza pivot = pizzaData.get((low+high)/2);
		int left = low;
		int right = high;
		while (left <= right) {
			while(pizzaData.get(left).compareToBySize(pivot) < 0) {
				left++;
			}
			while(pizzaData.get(right).compareToBySize(pivot) > 0) {
				right--;
			}
			if (low <= right) {
				pizzaData.swap(left, right);
				left++;
				right--;
			}
		}
		if (low < right) {
			quickSortBySize(low, right);
		}
		if (left < high) {
			quickSortBySize(left, high);
		}
	}
	
	@Override
	protected void quickSortByPrice() {
		if(pizzaData.size() == 0) {
			throw new PizzaException("Can sort empty list");
		}
		quickSortByPrice(0, pizzaData.size()-1);
	}
	private void quickSortByPrice(int low, int high) {
		Pizza pivot = pizzaData.get((low+high)/2);
		int left = low;
		int right = high;
		while (left <= right) {
			while(pizzaData.get(left).compareTo(pivot) < 0) {
				left++;
			}
			while(pizzaData.get(right).compareTo(pivot) > 0) {
				right--;
			}
			if (low <= right) {
				pizzaData.swap(left, right);
				left++;
				right--;
			}
		}
		if (low < right) {
			quickSortBySize(low, right);
		}
		if (left < high) {
			quickSortBySize(left, high);
		}
	}
	
	@Override
	protected int binarySearchByCalories(int cals) {
		return binarySearchByCalories(cals, 0, pizzaData.size()-1);
	}
	private int binarySearchByCalories(int cals, int low, int high) {
		if (low > high) {
			return -1;
		}
		int mid = (low + high) / 2;
		if (pizzaData.get(mid).getCalories() == cals) {
			return mid;
		}
		else if (cals > pizzaData.get(mid).getCalories()) {
			return binarySearchByCalories(cals, mid+1, high);
		}
		else {
			return binarySearchByCalories(cals, low, mid-1);
		}
	}
	
	@Override
	protected int linearSearchByDay(int day) {
		return linearSearchByDay(day, pizzaData.size()-1);
	}
	private int linearSearchByDay(int day, int end) {
		int retVal = -1;
		for (int i = 0; i < end; i++) {
			if (pizzaData.get(i).getMadeDate().getDay() == day) {
				retVal = i;
			}
		}
		return retVal;
	}
	
	@Override
	protected void removeDayOldPizzas() {
		
	}
	
	@Override
	protected void reversePizzasWithStack() {
		Stack<Pizza> reversePizzas = new Stack<Pizza>();
		for(int i = 0; i < pizzaData.size(); i++) {
			reversePizzas.push(pizzaData.remove(i));
		}
		for(int i = 0; i < reversePizzas.size(); i++) { 
			pizzaData.insert((Pizza)reversePizzas.pop(), i);
		}
	}
	
	
}
package mainClasses;
import java.text.DecimalFormat;

import shapes.*;
import timeStamp.*;
import ingredients.*;


public class Pizza implements PizzaComparable {
	private Date made;
	ArrayList<Ingredient> pizzaIngredientData = new ArrayList<Ingredient>();
	private int calorieCount = 0;
	Money priceOfPizza = new Money(0,0);
	Shape shapeOfPizza;
	Fraction amountOfPizza = new Fraction(1,1);
	
	public Fraction getRemaining() {
		return new Fraction(amountOfPizza);
	}
	public void setRemaining(Fraction f) {
		if (f == null) {
			throw new PizzaException("Null Fraction");
		}
		amountOfPizza = new Fraction(f);
	}
	public int getCalories() {
		return calorieCount;
	}
	public double getPrice() {
		return priceOfPizza.getMoney();
	}
	public double getRemainingArea() {
		double pizzaRatio = (double) amountOfPizza.getNumerator() / (double) amountOfPizza.getDenominator();
		return pizzaRatio * shapeOfPizza.getArea();
	}
	public void setShape(Shape s) {
		shapeOfPizza = (Shape) s.clone();
	}
	public Shape getShape() {
		return (Shape) shapeOfPizza.clone();
	}
	public void addIngredient(Ingredient a) {
		pizzaIngredientData.insert(a, 0);
		calorieCount += a.getCalories();
		priceOfPizza.add(a.getCost());
	}
	public void eatSomePizza(Fraction amt) {
		if (amt == null || !(amt instanceof Fraction)) {
			throw new PizzaException("Not a Fraction.");
		}
		int pizzaLeftNumerator = (amountOfPizza.getNumerator() * amt.getDenominator()) - (amountOfPizza.getDenominator() * amt.getNumerator());
		int pizzaLeftDenominator = amountOfPizza.getDenominator() * amt.getDenominator();
		Fraction remainingPizza = new Fraction(pizzaLeftNumerator, pizzaLeftDenominator);
		if (pizzaLeftNumerator == 0) {
			throw new PizzaException("There is no more pizza left, all of it has been eaten.");
		}
		else if (pizzaLeftNumerator < 0) {
			throw new PizzaException("There is negative Pizza, you can't eat more pizza then there is.");
		}
		else {
			setRemaining(remainingPizza);
		}
	}
	
	public Pizza() {
		//made = new TimeStamp(PizzaManager.getCurrentDate(), PizzaManager.getCurrentTime());
		int getRandIngredientAmount = (int) (Math.random() * 51) + 3;
		int getRandShape = (int) (Math.random() * 2);
		int getRandX = (int) (Math.random() * 300);
		int getRandY = (int) (Math.random() * 300);
		double getRandPizzaSize = (Math.random() * 100) + 20;
		Circle circularPizza = new Circle(getRandX, getRandY);
		Square squarePizza = new Square(getRandX, getRandY);
		circularPizza.setRadius(getRandPizzaSize);
		squarePizza.setSidelength(getRandPizzaSize);
		
		switch(getRandShape) {
		case 0:	setShape(circularPizza);
				break;
				
		case 1:	
				setShape(squarePizza);
				break;
		}
		
		for (int i = 0; i < getRandIngredientAmount; i++) {
			int getRandIngredient = (int) (Math.random() * 8);
		
			switch(getRandIngredient) {
			case 0:	addIngredient(new Marinara());
					break;
			case 1: addIngredient(new Alfredo());
					break;
			case 2: addIngredient(new Mozzarella());
					break;
			case 3: addIngredient(new Goat());
					break;
			case 4: addIngredient(new Pepperoni());
					break;
			case 5: addIngredient(new Sausage());
					break;
			case 6: addIngredient(new Pepper());
					break;
			case 7: addIngredient(new Olive());
					break;
			}
		}
	}
	
	
	
	
	public Date getMadeDate() { 
		return new Date(made); 
	}

	@Override
	public String toString() {
		String retVal = "The Pizza costs: $" + getPrice() + ", it has " + getCalories() + " calories, " 
						+ "Fraction remaining: " + getRemaining() + ", and area left: " + getRemainingArea()
						+ ", the shape is: " + getShape();
		return retVal;
	}

	//@Override
	public int compareTo(Object o) {
		if(o == null || !(o instanceof Pizza)) {
			throw new PizzaException("Not a Pizza.");  
		}
		Pizza that = (Pizza) o;
		return this.priceOfPizza.compareTo(that.priceOfPizza);
	}
	
	//@Override
	public int compareToBySize(Object o) {
		if (o == null || !(o instanceof Pizza)) {
			throw new PizzaException("Not a Pizza.");
		}
		Pizza that = (Pizza) o;
		if (this.getRemainingArea() == that.getRemainingArea()) {
			return 0;
		}
		else if (this.getRemainingArea() > that.getRemainingArea()) {
			return 1;
		}
		else {
			return -1;
		}
	}

	//@Overrride
	public int compareToByCalories(Object o) {
		if (o == null || !(o instanceof Pizza)) {
			throw new PizzaException("Not a Pizza.");
		}
		Pizza that = (Pizza) o;
		return this.getCalories() - that.getCalories();
	}
	
	public static void main(String[] args) {
		Pizza p1 = new Pizza();
		Pizza p2 = new Pizza();
		/*p1.addIngredient(new Goat());
		p1.addIngredient(new Pepper());
		p2.addIngredient(new Goat());
		p2.addIngredient(new Pepper());*/
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println("Pizza compare: " + p1.compareTo(p2));
		System.out.println("Pizza compare: " + p1.compareToBySize(p2));
		System.out.println("Pizza compare: " + p1.compareToByCalories(p2));
		p1.eatSomePizza(new Fraction(2,8));
		System.out.println(p1.toString());
		//System.out.println(p1.getShape().getArea());
	}
	
	private Color myColor;
	private Money cost;
	private int calories;
	private String description;
	
	public Vegetable(String desc, Money amount, int cal) {
		super(desc, amount, cal);
	}
	
	public Vegetable(String desc, Money amount, int cal, Color col) {
		super(desc, amount, cal);
		setColor(col);
	}
	
	public void setColor(Color col) {
		if (col == null) {
			throw new PizzaException("Not a color.");
		}
		myColor = col;
	}
	public Color getColor() {
		return myColor;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nColor: " + getColor();
	}
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Vegetable)) {
			throw new PizzaException("Not a vegetable");
		}
		Vegetable that = (Vegetable) o;
		return this.getCalories() == that.getCalories() &&
			   this.getCost().equals(that.getCost()) &&
			   this.getColor().equals(that.getColor());
	}
	package ingredients;

	import mainClasses.Money;
	import mainClasses.PizzaException;

	/** Vikas Sidhu
	 * March 11, 2016
	 * Instructor: Rob Nash
	 * CSS 162
	 * This class is called Ingredient and implements Comparable. It has 
	 */

	public abstract class Ingredient implements Comparable {
		
		private Money cost;
		private int calories;
		private String description;
		
		public void setCost(Money amount) {
			if (amount == null) {
				throw new PizzaException("Null Money Object");
			}
			cost = new Money(amount);
		}
		public void setCalories(int cal) {
			if (cal < 0) {
				throw new PizzaException("Invalid Calorie amount");
			}
			calories = cal;
		}
		public void setDescription(String desc) {
			description = desc;
		}
		public Money getCost() {
			return new Money(cost);
		}
		public int getCalories() {
			return calories;
		}
		public String getDescription() {
			return description;
		}
		
		public Ingredient(String desc, Money amount, int cal) {
			setDescription(desc);
			setCost(amount);
			setCalories(cal);
		}
		
		/**
		 * This method returns the ingredient as a String
		 */
		@Override
		public String toString() {
			return getDescription() + "\nCost: " + getCost() + "\nCalories: " + getCalories(); // returns the description of the ingredient, its cost, and its calories
		}
			
		/**
		 * This method compares to see if two ingredients are the same.
		 */
		//@Override
		public int compareTo(Object o) {
			if (o == null || !(o instanceof Ingredient)) {
				throw new PizzaException("Not an Ingredient.");
			}
			Ingredient that = (Ingredient) o;
			return this.getCost().compareTo(that.getCost()) + (this.getCalories() - that.getCalories());
		}
	}
	package shapes;

	import java.util.*;
	import javax.swing.*;
	import java.awt.*;

	public class Square extends Shape implements Cloneable {

		private double sidelength = 30;
		
		public Square(int a, int b) {
			super(a,b);
		}
		public Square(Square other) {
			this(other.getX(), other.getY());
		}
		public double getSidelength() {
			return sidelength;
		}
		public void setSidelength(double s) {
			sidelength = s;
		}
		
		@Override
		public double getArea() {
			return getSidelength() * getSidelength(); 
		}
		
		@Override
		public void draw(Graphics g) {
			int sideuse = (int) getSidelength();
			g.setColor(Color.cyan);
			g.drawRect(getX(), getY(), sideuse, sideuse);
			g.fillRect(getX(), getY(), sideuse, sideuse);
		}
		
		@Override
		public Object clone() {
			return new Square(this);
		}
		
		@Override
		public String toString() {
			return "Square";
		}
	}

	
	package mainClasses;

	/** 
	 * Vikas Sidhu
	 * January 29, 2016
	 * Instructor: Rob Nash
	 * CSS 162
	 * This class is called ArrayList. It has a Object array and int numElements that keeps 
	 * track of how many elements are in the array. It can add an Object anywhere in the array, 
	 * resizes the array if it isn't big enough, then it increments the numElements. It can also remove
	 * elements from anywhere in the array and decrements the numElements. It can return the size of the array.
	 * It can return whether two arrays are equal, if the array is empty, and it can return the array as
	 * a String representation.
	 */

	public class ArrayList<TBA extends Comparable> {
		
		private Object[] data = new Object[100]; // Private instance Object array that has 100 elements
		private int numElements = 0; // Private instance integer that keeps track of the number of Elements
		
		/**
		 * This method first checks to see if an numElements is equal to data.length and if it is
		 * it resizes the array, then it checks to see if data[index] is not null and if it isn't
		 * it overwrites the previous value there with the new value Object o else if it is null it sets
		 * the data[index] equal to Object o and then increments numElements
		 */
		public void insert(TBA input, int index) {
			// This if statement checks to see if the input is null or the indexes are invalid
			if (input == null || index < 0 || index > data.length-1) {
				throw new PizzaException("Cannot input null or invalid index"); // throws a new PizzaException if the above is true
			}
			// Checks to see if the numElements is equal to the size of the array
			if (numElements == data.length) {
				resize(); // resizes the array if the above if statement is true.
			}
			// Checks to see if data[index] is not null
			if (data[index] != null) {
				arrayShiftRight(data, index); // Shifts the array right
				data[index] = input; // overwrites the previous value with object o to the index specified of the array 
			}
			// does this if the above if statement isn't true
			else {
				data[index] = input; // adds the object o to the index specified of the array
			}
			numElements++; // increments the numElements
			
		}
		/**
		 * This method first checks to see if the numElements is equal to 0 or if
		 * the index is less than 0 or if it is out of bounds of the array and if it is
		 * it gives an error message then exits the program else it sets the Object retVal equal to
		 * the data[index] then it sets data[index] equal to null, then it decrements numElements
		 * and finally returns retVal
		 */
		public TBA remove(int index) {
			// Checks to see if the numElements is equal to 0 or index is less than 0 or if index is out of bounds of the array
			if (isEmpty() || index < 0 || index > data.length-1) {
				// Checks to see if the above is true and if it is throws a new Pizza Exception
				throw new PizzaException("Empty List or Invalid index.");
			}
			TBA retVal = (TBA) data[index]; // sets Object retVal equal to data[index]
			arrayShiftLeft(data, index); // sets data[index] equal to null
			numElements--; // decrements numElements
			return retVal; // returns retVal
		}
		
		/**
		 * This method simply return the size of the array which is the numElements because that is how
		 * many values in the array aren't null
		 */
		public int size() {
			return numElements; // returns numElements
		}
		
		/**
		 * This method first initializes int retVal and sets it equal to negative 1
		 * then it uses a for loop to go through the entire array to
		 * and checks to see if a value in the array is equal to Object o if it is
		 * it sets retVal equal to that index of Object o in the array and then 
		 * breaks out of the for loop and finally returns retVal
		 */
		public int indexOf(TBA target) {
			int retVal = -1; // initializes int retVal and sets it equal to -1
			// This for loop goes through the entire array
			for (int i = 0; i < data.length; i++) {
				// This if statement checks to see if a value in the array is equal to Object o
				if (target.compareTo(data[i]) == 0) {
					retVal = i; // sets retVal equal to the index of Object o if it is in the array
					break; // breaks out of the for loop
				}
			}
			return retVal; // returns retVal
		}
		
		public TBA get(int targetIndex) {
			if (targetIndex < 0 || targetIndex > data.length -1) {
				throw new PizzaException("Not a valid index.");
			}
			return (TBA) data[targetIndex];
		}
		
		public void swap(int index1, int index2) {
			if (index1 < 0 || index1 > data.length-1 || index2 < 0 || index2 > data.length-1) {
				throw new PizzaException("Not a valid index.");
			}
			Object temp = data[index1];
			data[index1] = data[index2];
			data[index2] = temp;
		}
		
		/**
		 * This method first initializes a String retVal and sets it equal to an empty string
		 * then it uses a for loop that goes through the entire array and then it uses an if statement
		 * to see if the value in the array at that element is not null and if it isn't it adds it to
		 * the retVal and finally returns the retVal
		 */
		@Override
		public String toString() {
			String retVal = ""; // initializes the String retVal and sets it equal to an empty string
			// This for loop goes through the entire array
			for (int i = 0; i < data.length; i++) {
				// Checks to see if the data[i] is not null
				if (data[i] != null) {
					retVal += data[i] + "\n"; // adds the data[i] to the retVal
				}
			}
			return retVal; // returns retVal
		}
		/**
		 * This method returns whether the Object array is empty and does so by checking to see if
		 * numElements is equal to 0 because if it is then no elements are in the array(all the values are null then)
		 */
		public boolean isEmpty() {
			return numElements == 0; // returns whether numElements equals 0
		}
		
		/**
		 * This method first initializes the boolean retVal and sets it to false
		 * then it checks to see if the Object o is null or if it isn't a instance of ArrayList and if it
		 * is either of those it returns false other wise it creates an ArrayList that and sets it equal to 
		 * Object o after o is casted to an ArrayList, then it checks to see the numElements in this ArrayList is
		 * equal to that ArrayList and if it isn't it returns the retVal which is false otherwise it goes
		 * through a for loop and then checks to see if each element in this ArrayList is equal to that ArrayList
		 * and if it is it sets the retVal to true and if it ever isn't it sets the retVal to false
		 * breaks out of the loop and then returns the retVal.	 
		 */
		@Override
		public boolean equals(Object o) {
			boolean retVal = false; // initializes the boolean retVal and sets it to false
			// This if statement checks to see if Object o is null of if it isn't an instance of ArrayList
			if (o == null || !(o instanceof ArrayList)) {
				throw new PizzaException("Null Object or not Arraylist"); // throws a new PizzaException if the above is true
			}
			
			ArrayList<TBA> that = (ArrayList<TBA>) o; // Makes a ArrayList and sets it equal to Object o after casting o to a ArrayList
			// This if statement checks to see if the two ArrayLists have the same numElements
			if (this.size() == that.size()) {
				// goes through a for loop from 0 to data.length if the above if statement is true
				for (int i = 0; i < data.length; i++) {
					// This if statements checks to see if this ArrayList's data[i] is equal to that ArrayList's data[i]
					if (this.data[i].equals(that.data[i])) {
						retVal = true; // sets retVal equal to true
					}
					// if the above if statement isn't true it does this instead
					else {
						retVal = false; // sets the retVal to false
						break; // breaks out of the loop
					}
				}
			}
			return retVal; // returns retVal
		}
		/**
		 * This method takes in a array and an integer index and then shifts the entire array
		 * right up to the index input using a for loop
		 */
		private void arrayShiftRight(Object[] data, int index) {
			// This for loop goes from the data.length-1 to index and shifts the array right
			for (int i = data.length-1; i > index; i--) {
				data[i] = data[i-1]; // sets data[i] equal to data[i-1]
			}
		}
		/**
		 * This method takes in a array and an integer index and then shifts the entire array
		 * left using a for loop 
		 */
		private void arrayShiftLeft(Object[] data, int index) {
			// This for loop goes from the index to data.length-1 and shifts the array left
			for (int i = index; i < data.length-1; i++) {
				data[i] = data[i+1]; // sets data[i] equal to data[i+1]
			}
		}
		
		/**
		 * This method is a private method because it should only be used in this class and 
		 * it should not be called by the user. It first makes a new Object array called temp
		 * and sets it's size equal to twice the Object array data's length, then it goes through
		 * a for loop and sets all the values of temp to the values in data and then finally sets data
		 * equal to temp.
		 */
		private void resize() {
			// initializes a new Object Array and sets it's number of elements to be twice that of data's length
			Object[] temp = new Object[data.length*2];
			// This for loop goes from 0 to data's length
			for (int i = 0; i < data.length; i++) {
				temp[i] = data[i]; // sets temp[i] equal to the value of data[i]
			}
			data = temp; // sets data equal to temp
		}
		/**
		 * This method checks to see if an input Object is null or if it is not an arrayList and if it is 
		 * it throws a new PizzaException otherwise it makes a new ArrayList<TBA> and sets it equal to the 
		 * input Object after it has been casted as an arrayList, and finally returns this.size() - that.size()
		 */
		//@Override
		public int compareTo(Object o) {
			// This if statement checks to see if o is null or it is not an instanceof ArrayList
			if (o == null || !(o instanceof ArrayList)) {
				throw new PizzaException("Null Object or not ArrayList"); // throws a new PizzaException if the above is true.
			}
			ArrayList<TBA> that = (ArrayList<TBA>) o; // initializes that as an ArrayList<TBA> and sets it equal to o casted to ArrayList<TBA>
			return this.size() - that.size(); // returns this.size() minus that.size()
		}
	}

	
}
