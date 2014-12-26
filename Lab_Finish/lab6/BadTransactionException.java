/* BadTransactionException.java */

/**
 *  Implements an exception that should be thrown for invalid amount.
 **/
public class BadTransactionException extends Exception {

  public int amountNumber;  // The invalid account number.

  /**
   *  Creates an exception object for nonexistent account "badAcctNumber".
   **/
  public BadTransactionException(int badAmountNumber) {
    super("Invalid amount number: " + badAmountNumber);

    amountNumber = badAmountNumber;
  }
}
