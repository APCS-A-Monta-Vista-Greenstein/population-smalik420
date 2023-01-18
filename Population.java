import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Population - This is a program which uses a variety 
 *	of sort methods to create different sorts based off a
 *	text file with population, city, state, and town type
 *	data based on the users choice. 
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Sidhant Malik
 *	@since	Jan 13, 2023
 */
public class Population 
{
	// List of cities
	private List<City> cities;
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	// Boolean for whether program is running or not
	private boolean running;
	
	/**
	 * No-Arg Constructor initializes necessary variables to defaults
	 */
	public Population()
	{
		running = true;
		cities = new ArrayList<City>();
	}
	
	/**
	 * In main, a Population object is created and used to call run. 
	 */
	public static void main(String[] args) 
	{
		Population p = new Population();
		p.run();
	}
	
	/**
	 * In run, the file is loaded and the introduction is printed. Then
	 * the loop with the prompt asking the user for their preference is run. 
	 */
	public void run()
	{
		loadFile();
		printIntroduction();
		
		while (running)
		{
			printMenu();
			int choice = getUserChoice();
			startOperation(choice);
		}
		
		System.out.println("\nThanks for using Population!");
	}
	
	/**
	 * This method loads the file with the and creates a city
	 * object with the correct characteristics using the delimeter.
	 */
	public void loadFile()
	{
		Scanner fileScanner = FileUtils.openToRead(DATA_FILE);
		fileScanner.useDelimiter("[\t\n]");
		
		while (fileScanner.hasNext())
		{
			String state = "";
			String designation = "";
			String name = "";
			int population = -1;
			
			if (fileScanner.hasNext())
				state = fileScanner.next();
			
			if (fileScanner.hasNext())
				name = fileScanner.next();
			
			if (fileScanner.hasNext())
				designation = fileScanner.next();
			
			if (fileScanner.hasNext())
				population = fileScanner.nextInt();

			cities.add(new City(name, state, designation, population));
		}
		
	}
	
	/**
	 * This method is used to get the users choice for the sort that they
	 * want to do using the prompt class. 
	 * 
	 * @return 1 - 6 when picking sort or 9 if ending program.  
	 */
	public int getUserChoice( ) 
	{
		int choice = -1;
		boolean validFound = false; 
		while (!validFound)
		{
			choice = Prompt.getInt("Enter selection ", 1, 9);
			
			if (choice == 7 || choice == 8)
				validFound = false;
			else	
				validFound = true;
		}

		return choice;
	}
	
	/**
	 * This control method is called in run and takes in the
	 * users choice to call the appropriate sort method. If 9 
	 * is entered then the fv userChoiceIn is set to false. 
	 * 
	 * @param users sort of choice
	 */
	public void startOperation(int userChoiceIn)
	{
		if (userChoiceIn == 1) fiftyLeastPopulous();
		if (userChoiceIn == 2) fiftyMostPopulous();
		if (userChoiceIn == 3) firstFiftyByName();
		if (userChoiceIn == 4) lastFiftyByName();
		if (userChoiceIn == 5) fiftyMostPopulousByState();
		if (userChoiceIn == 6) citiesMatchingNameByPopulation();
		if (userChoiceIn == 9) running = false;
	}
	
	/**
	 * Choice 1 - This calls a selection sort to sort from least to
	 * highest population and then displays the first 50 cities
	 * formatted properly. 
	 */
	public void fiftyLeastPopulous() 
	{
		System.out.println("\nFifty least populous cities");
		long startMilliSec = System.currentTimeMillis();
		sortAscendingPopulation();
		long endMilliSec= System.currentTimeMillis();
		
		System.out.printf("    %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
		for (int i = 0; i < 50; i++)
		{
			String line = cities.get(i).toString();
			if ( i < 9)
				System.out.println(" " + (i + 1) + ": " + line);
			else
				System.out.println("" + (i + 1) + ": " + line);
		}
		
		System.out.println("\nElapsed Time " + (endMilliSec - startMilliSec) + " milliseconds");
	}
	
	/**
	 * Choice 2 - This calls a merge sort to sort from highest to
	 * least population and then displays the first 50 cities
	 * formatted properly. 
	 */
	public void fiftyMostPopulous() 
	{
		System.out.println("\nFifty most populous cities");
		long startMilliSec = System.currentTimeMillis();
		sortDescendingPopulation();
		long endMilliSec= System.currentTimeMillis();
		
		System.out.printf("    %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
		for (int i = 0; i < 50; i++)
		{
			String line = cities.get(i).toString();
			if ( i < 9)
				System.out.println(" " + (i + 1) + ": " + line);
			else
				System.out.println("" + (i + 1) + ": " + line);
		}
		
		System.out.println("\nElapsed Time " + (endMilliSec - startMilliSec) + " milliseconds");
	}
	
	/**
	 * Choice 3 - This calls a insertion sort to sort from lowest to
	 * highest alphabetical city namne order and then displays the first 50 cities
	 * formatted properly. 
	 */
	public void firstFiftyByName() 
	{
		System.out.println("\nFifty cities sorted by name");
		long startMilliSec = System.currentTimeMillis();
		sortAscendingName();
		long endMilliSec= System.currentTimeMillis();
		
		System.out.printf("    %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
		for (int i = 0; i < 50; i++)
		{
			String line = cities.get(i).toString();
			if ( i < 9)
				System.out.println(" " + (i + 1) + ": " + line);
			else
				System.out.println("" + (i + 1) + ": " + line);
		}
		
		System.out.println("\nElapsed Time " + (endMilliSec - startMilliSec) + " milliseconds");
	}
	
	/**
	 * Choice 4 - This calls a merge sort to sort from highest to
	 * lowerst alphabetical city namne order and then displays the first 50 cities
	 * formatted properly. 
	 */
	public void lastFiftyByName() 
	{
		System.out.println("\nFifty cities sorted by name descending");
		long startMilliSec = System.currentTimeMillis();
		sortDescendingName();
		long endMilliSec= System.currentTimeMillis();

		System.out.printf("    %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
		for (int i = 0; i < 50; i++)
		{
			String line = cities.get(i).toString();
			if ( i < 9)
				System.out.println(" " + (i + 1) + ": " + line);
			else
				System.out.println("" + (i + 1) + ": " + line);
		}
		
		System.out.println("\nElapsed Time " + (endMilliSec - startMilliSec) + " milliseconds");
	}
	
	/**
	 * Choice 5 - This first stores all of the states in an array list,
	 * and then uses the prompt class to take in a state from the user,
	 * and if it is a valid state, it then creates an array of the cities
	 * in that state and uses a selection to sort to sort them from 
	 * highest to lowest population before printing them out formatted. 
	 */
	public void fiftyMostPopulousByState() 
	{
		System.out.println();
		//create list of all states
		List<String> allStates = new ArrayList<String>();
		for (int i = 0; i < cities.size(); i++)
		{
			if (!(allStates.contains(cities.get(i).getState() ) ) )
			{
				allStates.add(cities.get(i).getState());
			}
			
		}
				
		boolean found = false;
		String input = "";
		while (!found)
		{
			input = Prompt.getString("Enter state name (ie. Alabama) ");
			if (allStates.contains(input))
				found = true;
			else
				System.out.println("ERROR: " + input + " is not valid");
		}
		
		List<City> newCities = new ArrayList<City>();
		
		for (int i = 0; i < cities.size(); i++)
		{
			if (cities.get(i).getState().equals(input))
			{
				newCities.add(cities.get(i));
			}
		}
		

		//selection
		for (int i = 0; i < newCities.size(); i++) 
		{
			int max = 0;
			for (int j = 1; j < newCities.size() - i; j++) 
			{
				if (newCities.get(j).compareTo(newCities.get(max)) < 0)
				{
					max = j;
				}
			}
			
			swap(newCities, max, newCities.size() - 1 - i);
		}
		

		//print results
		System.out.println("\nFifty most populous cities in " + input);
		System.out.printf("    %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
		for (int i = 0; i < 50; i++)
		{
			String line = newCities.get(i).toString();
			if ( i < 9)
				System.out.println(" " + (i + 1) + ": " + line);
			else
				System.out.println("" + (i + 1) + ": " + line);
		}
		
	}
	
	/**
	 * Choice 6 - This first stores all of the cities in an array list,
	 * and then uses the prompt class to take in a city from the user,
	 * and if it is a valid state, it then creates an array of the cities
	 * with that name and uses a selection to sort to sort them from 
	 * highest to lowest population before printing them out formatted. 
	 */
	public void citiesMatchingNameByPopulation() 
	{
		List<String> allCities = new ArrayList<String>();
		
		for (int i = 0; i < cities.size(); i++)
		{
			if (!(allCities.contains(cities.get(i).getName() ) ) )
			{
				allCities.add(cities.get(i).getName());
			}
			
		}
				
		boolean found = false;
		String input = "";
		while (!found)
		{
			input = Prompt.getString("Enter city name ");
			if (allCities.contains(input))
				found = true;
			else
				System.out.println("ERROR: " + input + " is not valid");
		}
		
		List<City> newCities = new ArrayList<City>();
		
		for (int i = 0; i < cities.size(); i++)
		{
			if (cities.get(i).getName().equals(input))
			{
				newCities.add(cities.get(i));
			}
		}
		

		//selection
		for (int i = 0; i < newCities.size(); i++) 
		{
			int max = 0;
			for (int j = 1; j < newCities.size() - i; j++) 
			{
				if (newCities.get(j).compareTo(newCities.get(max)) < 0)
				{
					max = j;
				}
			}
			
			swap(newCities, max, newCities.size() - 1 - i);
		}
	
		//print results
		for (int i = 0; i < newCities.size(); i++)
		{
			String line = newCities.get(i).toString();
			if ( i < 9)
				System.out.println(" " + (i + 1) + ": " + line);
			else
				System.out.println("" + (i + 1) + ": " + line);
		}
		
	}
	
	/**
	 * This method uses selection sort to sort the cities by 
	 * population going from low to high. 
	 */
	public void sortAscendingPopulation()
	{
		for (int i = 0; i < cities.size(); i++) 
		{
			int max = 0;
			for (int j = 1; j < cities.size() - i - 1; j++) 
			{
				if (cities.get(j).compareTo(cities.get(max)) > 0)
				{
					max = j;
				}
			}
			
			swap(cities, max, cities.size() - 1 - i);
		}
	}
	
	/**
	 * This method uses insertion sort to sort the cities by 
	 * name (alphabetically) going from low to high. 
	 */
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
	
	/**
	 * This is the main method of the descending population merge 
	 * sort, which calls mergeSortPopulation one time while passing
	 * in the outer bounds. 
	 */
	public void sortDescendingPopulation()
	{
		mergeSortPop(0, cities.size() - 1);
	}
	
	/**
	 * Merge sort pop is passed in the outer parameters and uses them
	 * to repeatedly call itself for the left and right hand half 
	 * befor eeventually calling mergePop which will merge all 
	 * the halves together. 
	 * @param int left boundary
	 * @param int right boundary
	 */
	public void mergeSortPop(int left, int right)
	{
		if (left < right)
		{
			int middle = left + (right - left ) / 2;
			
			mergeSortPop(left, middle);
			mergeSortPop(middle + 1, right);
			
			mergePop(left, middle, right);
		}
	}
	
	/**
	 * Mergepop is used to merge the different halves together, and it 
	 * works by creating temporary lists to hold the left and right hand
	 * side contents before combining both of them
	 * @param int left hand boundary
	 * @param int middle value so that it does nto need to be calculated here again
	 * @param int right hand boundary
	 */
	public void mergePop(int left, int middle, int right)
	{
		int size1 = middle - left + 1;
		int size2 = right - middle;

		ArrayList<City> leftArr = new ArrayList<City>();
		ArrayList<City> rightArr = new ArrayList<City>();


		for (int i = 0; i < size1; i++) 
		{
			leftArr.add(cities.get(left + i));
		}
		for (int i = 0; i < size2; i++) {
			rightArr.add(cities.get(middle + 1 + i));
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int index = left;

		while(leftIndex < size1 && rightIndex < size2) 
		{
			if (leftArr.get(leftIndex).compareTo(rightArr.get(rightIndex)) >= 0) 
			{
				cities.set(index, leftArr.get(leftIndex));
				leftIndex++;
			}
			else 
			{
				cities.set(index, rightArr.get(rightIndex));
				rightIndex++;
			}
			index++;
		}

		while (leftIndex < size1) 
		{
			cities.set(index, leftArr.get(leftIndex));
			leftIndex++;
			index++;
		}

		while (rightIndex < size2) 
		{
			cities.set(index, rightArr.get(rightIndex));
			rightIndex++;
			index++;
		}
	
	}
	
	/**
	 * This is the main method of the descending name merge 
	 * sort, which calls mergeSortName one time while passing
	 * in the outer bounds. 
	 */
	public void sortDescendingName()
	{
		mergeSortName(0, cities.size() - 1);
	}
	
	/**
	 * Merge sort name is passed in the outer parameters and uses them
	 * to repeatedly call itself for the left and right hand half 
	 * befor eeventually calling mergePop which will merge all 
	 * the halves together. 
	 * @param int left boundary
	 * @param int right boundary
	 */
	public void mergeSortName(int left, int right)
	{
		if (left < right)
		{
			int middle = left + (right - left ) / 2;
			
			mergeSortName(left, middle);
			mergeSortName(middle + 1, right);
			
			mergeName(left, middle, right);
		}
	}
	
	/**
	 * Mergepop is used to merge the different halves together, and it 
	 * works by creating temporary lists to hold the left and right hand
	 * side contents before combining both of them
	 * @param int left hand boundary
	 * @param int middle value so that it does nto need to be calculated here again
	 * @param int right hand boundary
	 */
	public void mergeName(int left, int middle, int right)
	{
		int size1 = middle - left + 1;
		int size2 = right - middle;

		ArrayList<City> leftArr = new ArrayList<City>();
		ArrayList<City> rightArr = new ArrayList<City>();


		for (int i = 0; i < size1; i++) 
		{
			leftArr.add(cities.get(left + i));
		}
		for (int i = 0; i < size2; i++) {
			rightArr.add(cities.get(middle + 1 + i));
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int index = left;

		while(leftIndex < size1 && rightIndex < size2) 
		{
			if (leftArr.get(leftIndex).getName().compareTo(rightArr.get(rightIndex).getName()) >= 0) 
			{
				cities.set(index, leftArr.get(leftIndex));
				leftIndex++;
			}
			else 
			{
				cities.set(index, rightArr.get(rightIndex));
				rightIndex++;
			}
			index++;
		}

		while (leftIndex < size1) 
		{
			cities.set(index, leftArr.get(leftIndex));
			leftIndex++;
			index++;
		}

		while (rightIndex < size2) 
		{
			cities.set(index, rightArr.get(rightIndex));
			rightIndex++;
			index++;
		}
	
	}
	
	/**
	 * Swap is used to swap two elements of any list passed in. 
	 * 
	 * @param List to switch
	 * @param int x the position of the first element
	 * @param int y the position of the second element
	 */
	private void swap(List<City> arr, int x, int y) 
	{
		City tempCityObj = arr.get(x);
		arr.set( x, arr.get(y) );
		arr.set(y, tempCityObj);
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
		System.out.println("\n31765 cities in database");
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("\n1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}