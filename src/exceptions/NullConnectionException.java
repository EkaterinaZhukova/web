package exceptions;

/**
 * Null connection excpetion
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class NullConnectionException extends Exception {
    public NullConnectionException(){
        this("Returned connection is null");
    }

    public NullConnectionException(String s){
        super(s);
    }
}
