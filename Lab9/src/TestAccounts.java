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

public class TestAccounts {

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
  	        Scanner in = new Scanner( new FileReader( dbFilename ) );

    	    // state == 0 means we are looking at an id,
	        // state == 1 means we are looking at a balance

	        int state = 0;

	        // temp variables required for parsing

	        String id = "";
	        int initBal = 0;
 
            while ( in.hasNext() )
            {
		        switch ( state ) 
		        {
		            case 0:              // process account number
		                id = in.next() ;
		                break;
		            case 1:              // process balance and create account
		                initBal = in.nextInt();
		                accounts.put( id, new Account( id, initBal ) );
		                break;
		        }

		        state = ( state + 1 ) % 2;
		    }
        }
        catch( FileNotFoundException ex ) 
        {
            System.err.println( ex );
            System.err.println( "No accounts were initialized. Sorry." );
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

        TestAccounts test = new TestAccounts() ;
        
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
