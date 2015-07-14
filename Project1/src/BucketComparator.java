import java.util.Comparator;

/**
 * 
 */

/**
 * @author bkrenz
 *
 */
public class BucketComparator implements Comparator<Bucket>
{

	@Override
	public int compare(Bucket b1, Bucket b2) {
		if (b1.getCapacity() == b2.getCapacity())
			return b2.getFillAmount() - b1.getFillAmount();
		return b2.getCapacity() - b1.getCapacity();
	}
	
}
