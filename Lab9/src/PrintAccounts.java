/*
 * TestAccounts.java
 * 
 * Version: 
 *     $Id: TestAccounts.java,v 1.1 2013/05/28 19:00:15 csci142 Exp csx $
 * 
 * Revisions: 
 *     $Log: TestAccounts.java,v $
 *     Revision 1.1  2013/05/28 19:00:15  csc142
 *     Initial revision
 *
 */

import java.util.*;
import java.io.*;

/**
 * A simple program to test the Account class.  The program
 * reads text based data from an input file and creates the
 * corresponding accounts.  After all the accounts have been
 * created, they are printed out.
 *
 * @author     James E Heliotis
 * @author     Trudy Howles
 * @author     Paul Tymann
 * @author     Rob Duncan
 */

public class PrintAccounts {

    // Storage for the accounts.

    private Map <String, Account> accounts = 
	        new TreeMap <String, Account> ();

    /**
     * Creates accounts and stores them into the map.
     * Data for the accounts is taken from the file whose
     * name is given as a parameter.
     *
     * Note:  It is assumed that the data file is in the
     *        correct format.
     */

    private void loadDatabase( String dbFilename ) 
    {
        try 
        { 
  	        ObjectInputStream l_OIS = new ObjectInputStream( new FileInputStream(dbFilename));
  	        this.accounts = (TreeMap) l_OIS.readObject();
  	        l_OIS.close();
        }
        catch( FileNotFoundException ex ) 
        {
            System.err.println( ex );
            System.err.println( "No accounts were initialized. Sorry." );
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    /**
     * The main method.  The program expects a single command line
     * argument that gives the name of the file that contains the
     * data that will be read by the program.
     *
     * @param    args    Command line arguments (name of file to read).
     */

    public static void main( String[] args ) 
    {
	    // Usage check

	    if ( args.length != 1 ) 
	    {
	        System.err.println( "Usage:  java TestAccounts datafile" );
	        System.exit( 1 );
	    }

        PrintAccounts test = new PrintAccounts() ;
        
        // Load the information
	    test.loadDatabase( args[ 0 ] );

	    // Print out the accounts.
	    Iterator it = test.accounts.values().iterator();

	    System.out.println( "Id\t\tBalance" );
	    System.out.println( "--\t\t-------" );

	    while ( it.hasNext() ) 
	    {
	        System.out.println( it.next() );
        }
    }

} // TestAccounts
