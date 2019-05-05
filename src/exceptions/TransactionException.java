package exceptions;


/**
 * Transaction exception
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class TransactionException extends Exception {
    public TransactionException() {
        this("Transaction failed!");
    }
    public TransactionException(String s) {
        super((s));
    }
}
