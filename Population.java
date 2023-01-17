//UNFINISHED VERSION NEW WILL BE UPDATED & SENT ONCE I FIGURE OUT HOW TO WITH
//NEW COMPUTERS

//does this upload to githubcheck
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Sidhant Malik
 *	@since	
 */
public class Population 
{
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	private boolean running;
	
	public Population()
	{
		running = true;
		cities = new ArrayList<City>();
	}
	
	public static void main(String[] args) 
	{
		Population p = new Population();
		p.run();
	}
	
	public void run()
	{
		loadFile();
	//	printIntroduction();
	//	printMenu();
		running = false;
		
	//	System.out.println("no sort-----------------------------------");
	//	for (int i = 0; i < cities.size();i++) System.out.println(cities.get(i));   
		
		sortDescendingName();
		System.out.println("descending name-----------------------------------");
//		for (int i = 0; i < cities.size();i++) System.out.println(cities.get(i));   
		
	//	sortAscendingName();
	//	System.out.println("ascending name-----------------------------------");
	//	for (int i = 0; i < cities.size();i++) System.out.println(cities.get(i));   
		
	//	sortDescendingPop();
	//	System.out.println("descending pop-----------------------------------");
	//	for (int i = 0; i < cities.size();i++) System.out.println(cities.get(i));   


	//	sortAscendingPop();
	//	System.out.println("ascending pop-----------------------------------");
	//	for (int i = 0; i < cities.size();i++) System.out.println(cities.get(i));   
		
		
		
		while (running)
		{
			int userChoice = getUserChoice();
			startOperation(userChoice);
			if (running)
				printMenu();
		}
		
	}
	
	public int getUserChoice() 
	{
		int choice = 0;
		
		//dont take 7 & 8
		choice = Prompt.getInt("Enter selection ", 0, 9);
		System.out.println("entered " + choice);
		
		System.out.println(choice + "returned");
		return choice;
	}
	
	public void loadFile()
	{
		Scanner fileScanner = FileUtils.openToRead("usPopData2017.txt");
		fileScanner.useDelimiter("[\t\n]");
		
		while (fileScanner.hasNext())
		{
			String state = "";
			String designation = "";
			String name = "";
			int population = -1;
			
			for (int i = 0; i < 4; i++) 
			{
				if (fileScanner.hasNext())
				state = fileScanner.next();
				
				if (fileScanner.hasNext())
				designation = fileScanner.next();
				
				if (fileScanner.hasNext())
				name = fileScanner.next();
				
				if (fileScanner.hasNext())
				population = fileScanner.nextInt();
			}

			cities.add(new City(name, state, designation, population));
		}
		
	}
	
	public void startOperation(int userChoiceIn)
	{
		if (userChoiceIn == 1)
			fiftyLeastPopulous();
		if (userChoiceIn == 2)
			fiftyMostPopulous();
		if (userChoiceIn == 3)
			firstFiftyByName();
		if (userChoiceIn == 4)
			lastFiftyByName();
		if (userChoiceIn == 5)
			fiftyMostPopulousByState();
		if (userChoiceIn == 6)
			citiesMatchingNameByPop();
		if (userChoiceIn == 9)
		{	
			running = false;
		}
		
	}
	
	public void fiftyLeastPopulous() 
	{
		sortAscendingPop();
		
		System.out.println("\nFifty least populous cities");
		System.out.printf("%-18s %-19s %-20s %10s\n", "State", "Type", "City", "Population");
		for (int i = 0; i < 50; i++)
		{
			String line = cities.get(i).toString();
			
			if ( i < 9)
				System.out.println(" " + (i + 1) + ": " + line);
			else
				System.out.println("" + (i + 1) + ": " + line);
		}
	}
	
	public void fiftyMostPopulous() 
	{
		sortDescendingPop();
	}
	
	public void firstFiftyByName() 
	{
		sortDescendingName();
	}
	
	public void lastFiftyByName() 
	{
		sortAscendingName();
	}
	
	public void fiftyMostPopulousByState() 
	{
		sortDescendingPop();
	}
	
	public void citiesMatchingNameByPop() 
	{
		sortDescendingPop();
	}
	

	//selection sort
	public void sortAscendingPop()
	{
		for (int i = 0; i < cities.size(); i++) 
		{
			int max = 0;
			for (int j = 1; j < cities.size() - i - 1; j++) {
				if (cities.get(j).compareTo(cities.get(max)) > 0)
					max = j;
			}
			swap(cities, max, cities.size() - 1 - i);
		}
		
		
	}
	
	
	
	//insertion sort
	public void sortAscendingName()
	{
		for (int i = 0; i < cities.size() - 1; i++) 
		{
			for (int j = i + 1; j > 0; j--) 
			{
					if (cities.get(j).getName().compareTo(cities.get(j - 1).getName()) < 0)
						swap(cities, j, j - 1);
					else
						j = 0;
			}
		}

		
	}
	
	//merge sort
	public void sortDescendingPop()
	{
		sortPop(cities, 0, cities.size() - 1);
	}
	
	public void sortPop(List<City> arr, int left, int right) 
	{
		if (left < right) {
			int middle = left + (right - left) / 2;

			sortPop(arr, left, middle);
			sortPop(arr, middle + 1, right);

			mergePop(arr, left, middle, right);
		}
	}
	
	public void mergePop(List<City> arr, int left, int middle, int right) 
	{
		int size1 = middle - left + 1;
		int size2 = right - middle;

		ArrayList<City> leftArr = new ArrayList<City>();
		ArrayList<City> rightArr = new ArrayList<City>();


		for (int i = 0; i < size1; i++) {
			leftArr.add(arr.get(left + i));
		}
		for (int i = 0; i < size2; i++) {
			rightArr.add(arr.get(middle + 1 + i));
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int index = left;

		while(leftIndex < size1 && rightIndex < size2) 
		{
			if (leftArr.get(leftIndex).compareTo(rightArr.get(rightIndex)) <= 0) 
			{
				arr.set(index, leftArr.get(leftIndex));
				leftIndex++;
			}
			else 
			{
				arr.set(index, rightArr.get(rightIndex));
				rightIndex++;
			}
			index++;
		}

		while (leftIndex < size1) 
		{
			arr.set(index, leftArr.get(leftIndex));
			leftIndex++;
			index++;
		}

		while (rightIndex < size2) 
		{
			arr.set(index, rightArr.get(rightIndex));
			rightIndex++;
			index++;
		}
	}
	
	
	//merge sort
	public void sortDescendingName()
	{
		sortName(cities, 0, cities.size() - 1);
	}
	
	public void sortName(List<City> arr, int left, int right) 
	{
		if (left < right) {
			int middle = left + (right - left) / 2;

			sortName(arr, left, middle);
			sortName(arr, middle + 1, right);

			mergeName(arr, left, middle, right);
		}
	}

	public void mergeName(List<City> arr, int left, int middle, int right) 
	{
		int size1 = middle - left + 1;
		int size2 = right - middle;

		ArrayList<City> leftArr = new ArrayList<City>();
		ArrayList<City> rightArr = new ArrayList<City>();


		for (int i = 0; i < size1; i++) {
			leftArr.add(arr.get(left + i));
		}
		for (int i = 0; i < size2; i++) {
			rightArr.add(arr.get(middle + 1 + i));
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int index = left;

		while(leftIndex < size1 && rightIndex < size2) 
		{
			if (leftArr.get(leftIndex).getName().compareTo(rightArr.get(rightIndex).getName()) <= 0) 
			{
				arr.set(index, leftArr.get(leftIndex));
				leftIndex++;
			}
			else 
			{
				arr.set(index, rightArr.get(rightIndex));
				rightIndex++;
			}
			index++;
		}

		while (leftIndex < size1) 
		{
			arr.set(index, leftArr.get(leftIndex));
			leftIndex++;
			index++;
		}

		while (rightIndex < size2) 
		{
			arr.set(index, rightArr.get(rightIndex));
			rightIndex++;
			index++;
		}
	}

	
	private void swap(List<City> arr, int x, int y) 
	{
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}
