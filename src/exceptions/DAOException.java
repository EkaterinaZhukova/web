package exceptions;

/**
 * DAOException class
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class DAOException extends Exception {
    public DAOException() {
        this("DAO exception!");
    }
    public DAOException(String s) {
        super((s));
    }
}
