import java.text.DecimalFormat;
import java.text.NumberFormat;

/** Shawn Poole
 * CSS 162 - A
 * @author Shawn
 * Johnny Lin
 */
public class Money 
{
	/** Make sure that the variables 
	 * are set to private for dollars 
	 * and cents
	 */
	private int dollar;
	private int cent;
	/** Set the amount of dollars
	 * but, make cents be initialized
	 * to 0.
	 */
	public Money(int dol)
	{
		setDollar(dol);
		cent = 0;
	}
	/** Set the dollars and cents
	 * initially.
	 * 
	 */
	public Money(int dol, int cent)
	{
		setDollar(dol);
		setCent(cent);
		
	}
	/** copy constructor for the 
	 * money class that sets the dollars
	 * and cents while getting the dollars
	 * and cents
	 */
	public Money(Money other)
	{
		setDollar(other.getDollar());
		setCent(other.getCents());
	}
	/** Getters and setters for 
	 * the money with cents and dollars
	 * initialize the dollars and cents
	 */
	public int getDollar()
	{
		return dollar;
	}
	public int getCents()
	{
		return cent;
	}
	public void setDollar(int dol)
	{
		this.dollar = dol;
	}
	public void setCent(int cent)
	{
		this.cent = cent;
	}
	public double getMoney()
	{
		return (double)dollar + (cent / 100.0);
	}
	public void setMoney(int dol, int cent)
	{
		if(dol < 0)
		{
			System.out.println("Can't have negative dollars");
		}
		else
		{
			this.dollar = dol;
		}
		if (cent < 0 && cent > 99)
		{
			System.out.println("Can't have negative cents");
		}
		else
		{
			this.cent = cent;
		}
	}
	public void add(int dol)
	{
		this.dollar += dol;
	}
	/** Make sure that cents cannot be
	 * less than 0, or greater than 99.
	 * if the cents is more than 99
	 * adds to amount of dollars 
	 */
	public void add(int dol, int cents)
	{
		this.dollar += dol;
		
		if (cents < 0 && cents > 99)
		{
			cents = cent;
		}
		else
		{
			if (cents > 99)
			{
				int i = cents;
				while (i > 99)
				{
					dollar = dollar + 1;
					i = (i - 100);
				}
				cents = cent;
			}
		}
	}
	/** adds the dollars and cents
	 * taken in from the other add
	 * method and adds the two money objects
	 * together.
	 */
	public void add(Money other)
	{
		add(other.dollar, other.cent);
	}
	public boolean equals(Object o)
	{
		if (o instanceof Money)
		{
			Money obj = (Money)o;
			return dollar == obj.dollar && cent == obj.cent;
		}
		else
		{
			System.out.println("Must be instance of money class");
			return false;
		}
	}
	/** Translates the money into a string
	 * which puts it into decimal format.
	 */
	public String toString()
	{
		double money = getMoney();
		NumberFormat formatter = new DecimalFormat("0.00");
		return "$" + formatter.format(money);
	}
}
