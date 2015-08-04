/*
 * BankCodes.java
 * 
 * Version: 
 *     $Id: BankCodes.java,v 1.1 2003/05/28 19:00:15 cs3 Exp csx $
 * 
 * Revisions: 
 *     $Log: BankCodes.java,v $
 *     Revision 1.1  2003/05/28 19:00:15  cs3
 *     Initial revision
 *
 */

import java.lang.*;

/**
 * BankCodes: a set of communication codes for ATMs and Banks
 *
 * @author     James E Heliotis
 *
 */

public interface BankCodes {

    // Public constants

    /**
     * The default port on which the bank listens for ATM connections.
     */

    public static final int DEFAULT_PORT = 4999;

    // Commands

    /**
     * Withdraw the specified amount from the specified account.
     */

    public static final char WITHDRAW = 'W';

    /**
     * Deposit the specified amount into the specified account.
     */

    public static final char DEPOSIT = 'D';

    // Replies

    /**
     * Indicates that the transaction was successful.
     */

    public static final char SUCCESS = 'O';

    /**
     * Indicates that the transaction contained a bad command code.
     */

    public static final char BAD_COMMAND = 'C';

    /**
     * Indicates that the transaction attempted to overdraw an account.
     */

    public static final char INSUFFICIENT_FUNDS = 'I';

    /**
     * Indicates that the transaction supplied an invalid account number.
     */

    public static final char BAD_ACCOUNT_NUMBER = 'A';

} // BankCodes
