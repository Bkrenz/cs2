/*
 * BankMessage.java
 * 
 * Version: 
 *     $Id: BankMessage.java,v 1.1 2003/05/28 19:00:15 cs3 Exp csx $
 * 
 * Revisions: 
 *     $Log: BankMessage.java,v $
 *     Revision 1.1  2003/05/28 19:00:15  cs3
 *     Initial revision
 *
 */

import java.io.*;

/**
 * A data object containing all the information needed to send a
 * transaction request to the bank
 *
 * @author     James E Heliotis
 * @author     Paul Tymann
 *
 */

public class BankMessage implements Serializable {

    private String acctNum;    // the account number
    private char command;      // transaction to be applied
    private int amount;        // amount of the transaction
    private int serialNum;     // unique id for this transaction

    static int messageCounter = 0;  // used to generate serial numbers

    // Ctors

    /** 
     * Create an object containing a bank transaction request.
     *
     * @param    myAcctNum    the number of the account the transaction 
     *                        should be applied to.
     * @param    myCommand    the type of transaction requested.
     * @param    myAmount     the amount of the transaction.
     *
     * @see      BankCodes
     */

    public BankMessage( String myAcctNum, char myCommand, int myAmount ) {
	acctNum = myAcctNum;
	command = myCommand;
	amount = myAmount;
	serialNum = messageCounter++;
    }

    // Accessors

    /**
     * Get the number of the account the transaction should be applied to.
     *
     * @return    the account number of the account the transaction should
     *            be applied to.
     */

    public String getAccount() {
	return acctNum;
    }

    /**
     * Return the type of transaction.
     *
     * @return    the type of the transaction to be applied.
     *
     * @see       BankCodes
     */

    public char getCommand() {
	return command;
    }

    /**
     * Return the amount of the transaction.
     *
     * @return    the amount of the transaction.
     */

    public int getAmount() {
	return amount;
    }

    /**
     * Return the serial number assigned to this transaction.
     *
     * @return    the serial number of this transaction.
     */

    public int getSerialNum() {
	return serialNum;
    }

    // Mutators

    /**
     * Set the command.
     *
     * @param    theCommand   the command for this message.
     */

    public void setCommand( char theCommand ) {
	command = theCommand;
    }

    /**
     * Set the transaction amount.
     *
     * @param    transAmount    the transaction amount.
     */

    public void setAmount( int transAmount ) {
	amount = transAmount;
    }

    // Printing

    /**
     * Return a string representation of this transaction.
     *
     * @return    a string representation of this transaction.
     */

    public String toString() {
	return "BankMessage[" + acctNum + ",\"" + command + "\",$" 
	        + amount + " (" + serialNum + ")]";
    }

} // BankMessage
