
 /**
 * DoctorsOffice.java
 *
 *  $Id: $
 *
 *  Revisions:
 *  $Log: $
 *
 *
 *
 *
 **/

import java.util.*;

/**
 * A simple class to simulate the operation of a doctor's office
 * for tracking patients and medication.
 *
 * This class assigns patient identification numbers.  ID numbers start
 * at 1000 and increment by one for each new Patient object added to
 * the database.
 *
 * This class maintains two databases:  One for the current Patients
 * (the master database) and one for the inactive patients. 
 *
 * @author      Trudy Howles tmh@cs.rit.edu
 *
**/

public class DoctorsOffice {
    
	/* Class Members */
	
	private String m_OfficeName;
	private int m_PatientCounter;
	private HashMap<Integer, Patient> m_PatientList;
	
	/* End Class Members */
	

    /**
     *  Constructor for a DoctorsOffice object.
     *
     *  @param name             Name of this Dr's Office
     *
     **/

    public DoctorsOffice (String name) {
    	this.m_OfficeName = name;
    	this.m_PatientCounter = 999;
    	this.m_PatientList = new HashMap<>();
    }
    

    /**
     * Add a new patient to the office.  The identification
     * number is uniquely generated and is returned when the
     * Patient object is added to the database.  ID numbers
     * start at 1000 and increment by one for each new Patient
     * added.
     *
     *  @param firstName     first name of this patient 
     *  @param lastName      last name of this patient 
     *  @param age           age of this patient
     *  @return the ID number assigned to this Patient
     **/

    public int addPatient (String firstName, String lastName,
	int age) {
    	
    	this.m_PatientCounter++;
    	
    	this.m_PatientList.put(this.m_PatientCounter, new Patient(lastName, firstName, age));

    	return this.m_PatientCounter;

    }

    /**
     * Remove this patient from the master database.  Removed patients are
     * archived in an "inactive" database which maintains Patron
     * objects in the order in which they were removed from the master
     * database.
     * 
     * If throwing an exception, use "removePatient()" as the message.
     * 
     *  @param patientNo	Patient number assigned
     *  @exception              throws a NoSuchPatientException
     *                          if this patient does not exist
     **/

    public void removePatient (int patientNo) throws 
	NoSuchPatientException {

    }


    /**
     *  Add a new medication for this patient.
     *
     * If throwing an exception, use "addMedication()" as the message.
     *
     *  @param patientNo	Patient number
     *  @param medicationName	Name of this medication
     *  @param isGeneric	True if a generic drug
     *  @exception              throws NoSuchPatientException if 
     *                          this patient ID does not exist.
     **/

    public void addMedication(int patientNo, String medicationName,
	boolean isGeneric )
        throws NoSuchPatientException {
    	Patient l_Patient = this.m_PatientList.get(patientNo);
    	if (l_Patient == null)
    		throw new NoSuchPatientException("addMedication()");
    	else
    		l_Patient.recordNewMed(medicationName, isGeneric);
    	
    }

    /**
     * Print the medication detail for this patient. Print
     * the patient's full name (lastname COMMA SPACE firstName)
     * then each medication (each one on a new line).  To print
     * the medications, simply call your toString() method in
     * the Medication class.  
     *
     * If this patient has no medication history, print "No Medications
     * Prescribed".  
     *
     * If throwing an exception, use "printMedicationDetail()" as the message.
     *
     *  @param patientNo	Patient number
     *  @exception              throws NoSuchPatientException 
     *                          if patient does not exist.
     **/

    public void printMedicationDetail (int patientNo) 
	throws NoSuchPatientException {
    	

		Integer id = new Integer (patientNo);
		Patient p = (Patient) (this.m_PatientList.get (id));
		if (p == null)
		    throw new NoSuchPatientException("printMedicationDetail()");
		p.printMedicationHistory();

    }


    /**
     * Print all patients ordered by last name, then first name if
     * you encounter two patients with the same last name.
     *
     * To print the Patient objects, simply call your toString() method
     * in the Patient class.
     *
     **/

    public void listByName() {
    	
    	List<Patient> l_Pats = new ArrayList<>();
    	for (Patient pat : this.m_PatientList.values())
    		l_Pats.add(pat);
    	Collections.sort(l_Pats, new PatientComparator());
    	for (Patient pat : l_Pats)
    		System.out.println(pat);
    	
    	
    }


    /**
     * Print all patients ordered by age.
     *
     * To print the Patient objects, simply call your toString() method
     * in the Patient class.
     *
     **/

    public void listByAge() {
    }

    /**
     * Print all inactive patients in the order in which they were added to
     * the inactive archive.
     *
     *
     * To print the Patient objects, simply call your toString() method
     * in the Patient class.
     *
     **/

    public void listInactive() {
    }

    
    public static void main(String[] args)
    {
    	DoctorsOffice l_Office = new DoctorsOffice("Office 1");
    	
    	l_Office.addPatient("George", "Washington", 57);
    	l_Office.addPatient("John", "Adams", 61);
    	l_Office.addPatient("Thomas", "Jefferson", 57);
    	l_Office.addPatient("James", "Madison", 57);
    	l_Office.addPatient("James", "Monroe", 58);
    	l_Office.addPatient("John Quincy", "Adams", 57);
    	l_Office.addPatient("Andrew", "Jackson", 61);
    	l_Office.addPatient("Martin", "Van Buren", 54);
    	l_Office.addPatient("William Henry", "Harrison", 68);
    	l_Office.addPatient("John", "Tyler", 51);
    	l_Office.addPatient("James K", "Polk", 49);
    	l_Office.addPatient("Zachary", "Taylor", 64);
    	l_Office.addPatient("Millard", "Fillmore", 50);
    	l_Office.addPatient("Franklin", "Pierce", 48);
    	l_Office.addPatient("James", "Buchanan", 65);
    	l_Office.addPatient("Abraham", "Lincoln", 52);
    	l_Office.addPatient("Andrew", "Johnson", 46);
    	l_Office.addPatient("Ulysses S", "Grant", 54);
    	l_Office.addPatient("Rutherford B", "Hayes", 49);
    	l_Office.addPatient("James A", "Garfield", 51);
    	l_Office.addPatient("Chester A", "Arthur", 47);
    	l_Office.addPatient("Grover", "Cleveland", 55);
    	l_Office.addPatient("Benjamin", "Harrison", 55);
    	l_Office.addPatient("William", "McKinley", 54);
    	l_Office.addPatient("Theodore", "Roosevelt", 42);
    	l_Office.addPatient("William Howard", "Taft", 51);
    	l_Office.addPatient("Woodrow", "Wilson", 56);
    	l_Office.addPatient("Warren G", "Harding", 55);
    	l_Office.addPatient("Calvin", "Coolidge", 51);
    	l_Office.addPatient("Herbert", "Hoover", 54);
    	l_Office.addPatient("Franklin D", "Roosevelt", 51);
    	l_Office.addPatient("Harry S", "Truman", 60);
    	l_Office.addPatient("Dwight D", "Eisenhower", 62);
    	l_Office.addPatient("John F", "Kennedy", 43);
    	l_Office.addPatient("Lyndon B", "Johnson", 55);
    	l_Office.addPatient("Richard", "Nixon", 56);
    	l_Office.addPatient("Gerald", "Ford", 61);
    	l_Office.addPatient("Jimmy", "Carter", 52);
    	l_Office.addPatient("Ronald", "Reagan", 69);
    	l_Office.addPatient("George H W", "Bush", 64);
    	l_Office.addPatient("Bill", "Clinton", 46);
    	l_Office.addPatient("George W", "Bush", 54);
    	l_Office.addPatient("Barack", "Obama", 47);
    	
    	
    	try {
			for (int i = 0 ; i < 43 ; i++)
			{
				l_Office.addMedication(i+1000, "Prozaac", true);
			}
			l_Office.listByName();
		} catch (NoSuchPatientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    


} 

