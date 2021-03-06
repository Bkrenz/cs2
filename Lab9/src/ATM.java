/*
 * ATM.java
 * 
 * Version: 
 *     $Id: ATM.java,v 1.1 2013/05/28 19:00:15 csci142 Exp csx $
 * 
 * Revisions:
 *     $Log: ATM.java,v $
 *     Revision 1.1  2013/05/28 19:00:15  csci142
 *     Initial revision
 *
 */

import java.io.*;
import java.net.*;

/**
 * ATM: provides user access to the bank via the network
 *
 * @author     James E Heliotis
 */

public class ATM 
{

    /**
     * Read a line of input.
     * 
     * @param in The source from which to read
     * 
     * @return the line of data that was read. (If the line length
     *         is < 1, not data is read and the program exits.)
     */

    private static String readLine( BufferedReader in )
            throws java.io.IOException 
    {
        String s = in.readLine();
        if ( s == null || s.length() < 1 ) 
        {
            System.err.println( "No input! Exiting." );
            System.exit( 1 );
        }
        return s;
    }

    /**
     * Simulate the ATM
     * 
     * @param args the command line arguments.
     *             the first parameter is the host
     *             the second parameter, if it is there, is the port.
     */

    public static void main( String[] args ) 
    {

        String host = null;
        int port = BankCodes.DEFAULT_PORT;

        // Handle command line arguments

        switch (args.length)  
        {
            case 2:
                try 
                {
                    port = ( new Integer( args[ 1 ] ) ).intValue();
                }
                catch( NumberFormatException e ) 
                {
                    System.err.println( "Non-numeric port!" );
                    System.exit( 3 );
                }
            case 1:
                host = args[0];
                break;
            default:
                System.err.println( "Usage: " + "java ATM host [port]" );
                System.exit( 1 );
        }

        // Get ready to find out what the customer wants

        System.out.println( "Welcome to your bank!" );

        BufferedReader in = 
                new BufferedReader( new InputStreamReader( System.in ) );

        try 
        {
            String s = null;

            // Get the account number

            System.out.print( "What is your account number? " );
            String account = readLine( in );

            // Do they want to deposit or withdraw?

            System.out.print( "Strike \"" 
                              + BankCodes.DEPOSIT + "\" for deposit, \"" 
                              + BankCodes.WITHDRAW + "\" for withdrawal: " );

            s = readLine( in );
            char command = s.charAt( 0 );

            // Find out how much money to deposit or withdraw

            switch ( command ) 
            {
                case BankCodes.DEPOSIT:
                    System.out.print( "How much do you wish to deposit: " );
                    break;
                case BankCodes.WITHDRAW:
                    System.out.print( "How much do you wish to withdraw: " );
                    break;
                default:
                    System.err.println( "Illegal choice. Sorry." );
                    System.exit( 2 );
            }

            s = readLine( in );
            int amount = ( new Integer( s ) ).intValue();

            // Get ready to talk to the bank teller

            Socket conn = null;                 // Socket to the bank
            ObjectOutputStream net_out = null;  // Objects to the bank
            ObjectInputStream net_in = null;    // Objects from the bank

            // -----------------------------------------------------
            // ** The section below needs to be completed -- W.C. **
            // -----------------------------------------------------

            // Create the message that specifies the transaction
            BankMessage l_Message = new BankMessage(account, command, amount);

            // Connect to the bank server
            conn = new Socket(args[0], Integer.parseInt(args[1]));

            // Write the transaction out to the server
            net_out = new ObjectOutputStream(conn.getOutputStream());
            net_in = new ObjectInputStream(conn.getInputStream());
            net_out.writeObject(l_Message);

            // Wait for the reply.  Once it arrives store a reference
            // to the reply in the variable named confirmation
            
            BankMessage confirmation = (BankMessage) net_in.readObject();

            // ------------------------------------------------------------
            // ** The rest of this program should not be changed -- W.C. **
            // ------------------------------------------------------------
            
            // Tell the user what happened

            if ( confirmation.getCommand() != BankCodes.SUCCESS ) 
            {
                System.out.println( "The Bank did not accept your request." );

                switch ( confirmation.getCommand() ) 
                {
                    case BankCodes.BAD_COMMAND:
                        System.out.println( "Internal error: Bad Command. "
                                        + "Kill the programmer." );
                        break;
                    case BankCodes.INSUFFICIENT_FUNDS:
                        System.out.println( "Insufficient Funds" );
                        break;
                    case BankCodes.BAD_ACCOUNT_NUMBER:
                        System.out.println( "Bad Account Number" );
                        break;
                    default:
                        System.out.println( "The reason \"" 
                            + confirmation.getCommand() 
                            + "\" is unknown. Kill the programmer." );
                }
            } 
            else 
            {
                System.out.println( "Your new balance is $" 
                                    + confirmation.getAmount() );
            }

            // Done, clean up

            System.out.println( "Thank you for banking with us." );

            net_out.close();
            net_in.close();
            conn.close();
        }
        catch( NumberFormatException e ) 
        {
             System.err.println( "Bad integer input: " + e );
        }
        catch( java.io.IOException e ) 
        {
             System.err.println( e );
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

} //ATM
