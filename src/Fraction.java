/** Shawn Poole
 * CSS 162 - A
 * @author Shawn
 * Johnny Lin
 */
public class Fraction 
{
	private int numerator;
	private int denominator;
	public Fraction()
	{
		numerator = 0;
		denominator = 1;
	}
	public Fraction(Fraction f)
	{
		numerator = f.numerator;
		denominator = f.denominator;
	}
	public Fraction(int num, int den)
	{
		reduce(num, den);
	}
	public int getNumerator()
	{
		return numerator;
	}
	public void setNumerator(int num)
	{
		num = numerator;
	}
	public int getDenominator()
	{
		return denominator;
	}
	public void setDenominator(int den)
	{
		den = denominator;
	}
	public String toString()
	{
		return numerator + "/" + denominator; 
	}
	private void reduce(int num, int den)
	{
		int gcd = gcd(num, den);
    	numerator = (gcd / num);
    	denominator = (gcd / den);
	}
	private int gcd(int x, int y)
	{
		int num, den;
    	num = Math.max(x, y);
    	den = Math.min(x, y);
    	int temp = 0;
		while (den != 0)
		{
			temp = den;
			den = (num % 2);
			num = temp;
		}
		return num;
	}
}
