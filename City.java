/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Sidhant Malik
 *	@since	Jan 13th 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;
	private String state;
	private String designation;
	private int population;
	
	// constructor
	public City (String name, String state, String designation, int population)
	{
		this.name = name;
		this.state = state;
		this.designation = designation;
		this.population = population;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other)
	{
		if (other.population != this.population)
		{
			return this.population - other.population;
		}
		else if (other.state.equals(this.state) == false)
		{
			return this.state.compareTo(other.state);
		}
		else
		{
			return this.name.compareTo(other.name); 
		}
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other)
	{
		if (this.name.equals(other.name) && this.state.equals(other.state))
			return true;
		else 
			return false;
	}
	
	/**	Accessor methods */
	
	/**
	 * Getter method used to get the name of the current city
	 * @return The name of the current city
	 */
	public String getName()
	{		
		return name;
	}
	
	/**
	 * Getter method used to get the name of the current state
	 * @return The name of the current state
	 */
	public String getState()
	{
		return state;
	}
	
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}