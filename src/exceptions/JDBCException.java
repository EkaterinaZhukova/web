package exceptions;


/**
 * JDBC exception class
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class JDBCException extends Exception{
    public JDBCException() {
        this("Something went wrong with JDBC ");
    }
    public JDBCException(String s) {
        super((s));
    }
}
