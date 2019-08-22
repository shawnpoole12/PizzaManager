/** Shawn Poole
 * CSS 162 - A
 * @author Shawn
 * Johnny Lin
 */
public class Ingredient 
{
	private Money cost;
	private int calories;
	private String name;
	
	public Ingredient(String description,
			Money cost, int calories)
	{
		this.cost = cost;
		this.calories = calories;
		this.name = description;
	}
	public Money getCost()
	{
		return cost;
	}
	public void setCost(Money m)
	{
		this.cost = m;
	}
	public int getCalories()
	{
		return calories;
	}
	public void setCalories(int c)
	{
		this.calories = c;
	}
}
