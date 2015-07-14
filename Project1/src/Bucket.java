import java.util.ArrayList;
import java.util.Collections;


/**
 * 
 */

/**
 * @author bkrenz
 *
 */
public class Bucket {
	
	/* Class Members */
	
	private int m_Capacity;
	private int m_FillAmount;
	
	/* End Class Members */
	
	
	/* Constructors */
	
	public Bucket(int p_Capacity)
	{
		this.m_Capacity = p_Capacity;
		this.m_FillAmount = 0;
	}
	
	/* End Constructors */
	
	
	/* Class Methods */
	
	
	public int getCapacity()
	{
		return this.m_Capacity;
	}
	
	
	public int getFillAmount()
	{
		return this.m_FillAmount;
	}
	
	
	public void fill()
	{
		this.m_FillAmount = this.m_Capacity;
	}
	
	
	public void fill(Bucket p_Bucket)
	{
		int l_RemainingCapacity = this.m_Capacity - this.m_FillAmount;
		
		if ( p_Bucket.getFillAmount() <  l_RemainingCapacity)
			l_RemainingCapacity = p_Bucket.getFillAmount();
		
		p_Bucket.empty(l_RemainingCapacity);
		
		this.m_FillAmount += l_RemainingCapacity;
	}
	
	
	public void empty()
	{
		this.m_FillAmount = 0;
	}
	
	
	public void empty(int p_Amount)
	{
		this.m_FillAmount -= p_Amount;
	}
	
	
	public String toString()
	{
		return "Bucket:  Capacity - " + this.m_Capacity + " , Fill Amount - " + this.m_FillAmount;
	}
	
	/* Class Methods */
	
	
	/* Test Main */
	
	public static void main (String[] args)
	{
		Bucket b1 = new Bucket(10);
		Bucket b2 = new Bucket(5);
		Bucket b3 = new Bucket(6);
		
		ArrayList<Bucket> buckets = new ArrayList<>();
		buckets.add(b1);
		buckets.add(b2);
		buckets.add(b3);
		
		Collections.sort(buckets, new BucketComparator());
		
		b1.fill();
		b2.fill(b1);
		
		for (Bucket b : buckets)
			System.out.println(b);
		
		
	}
	
	/* End Test Main */

}






