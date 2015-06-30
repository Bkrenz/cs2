import java.util.Comparator;

/**
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 */

/**
 *  
 *
 */
public class PatientComparator implements Comparator<Patient>{

	@Override
	public int compare(Patient o1, Patient o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
