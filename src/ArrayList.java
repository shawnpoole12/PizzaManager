/** Shawn Poole
 * CSS 162 - A
 * @author Shawn
 * Johnny Lin
 */
public class ArrayList <Pizza>
{
	private Object[] data = new Object[100];
	// Store the data of the array
	
	private int numElements = 0;
	
	// add more elements to the index
	public void insert(Object obj, int index)
	{
		if (index < 0)
		{
			System.out.println("Index not in range");
			// just in case the list is out of the range
			
			System.exit(-1);
		}
		if (numElements == data.length)
		{
			// increase the size of the array as needed
			doubleInSize();
		}
		numElements++;
		for (int i = numElements; i > index; i--)
		{
			data[i] = data[i - 1];
		}
		data[index] = obj;
	}
	/** This will increase the size of the
	* array list just in case the range is 
	* not large enough
	*/
	public void doubleInSize()
	{
		Object[] var = new Object[data.length * 2];
		// increase the size of the array list.
		for (int i = 0; i < data.length; i++)
		{
			var[i] = data[i];
		}
		data = var;
	}
	/** This method will remove elements from the 
	 * array structure as needed. Based on the 
	 * size of the index
	 */
	public Object remove(int index)
	{
		if (index < 0)
		{
			// check if the index is out of range
			System.err.println("Out of range");
			System.exit(-1);
		}
		Object returnValue = data[index];
		for (int i = index; i < numElements; i++)
		{
			data[i] = data[i + 1];
		}
		numElements--;
		return returnValue;
	}
	
	/** returns the initial size of the 
	* elements within the array type 
	* list which just be the number of 
	* elements within the list
	*/
	public int size()
	{
		return numElements;
	}
	
	// Returns the given size of the array 
	public int amount()
	{
		return data.length;
	}
	public String toString()
	{
		String returnValue = "";
		for (int i = 0; i < numElements; i++)
		{
			if (data[i] != null)
			{
				returnValue = returnValue + data[i] + " : ";
			}
		}
		return returnValue;
	}
	/** if the list doesn't contain any elements,
	* it will return true and it will return 
	* false if the list still has elements within 
	* it.
	*/
	public boolean isEmpty()
	{
		if (numElements == 0)
		{
			return true;
		}
		else 
		{
			return false;
		}
		// Check if the array list is empty
	}
	public int indexOf(Object obj)
	{
		for (int i = 0; i < numElements; i++)
		{
			if (data[i].equals(obj))
			{
				return i;
			}
		}
		return -1;
	}
	@Override
	
	/** This will test if the the list equals 
	* null and will print out the value of null.
	*/
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			System.err.println("Object has a value of null");
			System.exit(-1);
		}
		if (!(obj instanceof ArrayList)) return false;
		ArrayList var1 = (ArrayList) obj;
		if (var1.numElements != numElements)
		{
			return false;
		}
		for (int i = 0; i < numElements; i++)
		{
			if (var1.data[i] != data[i])
			{
				return false;
			}
		}
		return true;
	}
}
