package exceptions;


/**
 * Excpetion for not found abonent
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class NotFoundAbonentException extends  NullPointerException{
    public NotFoundAbonentException() {
        this("Abonent not found exception");
    }

    public NotFoundAbonentException(String s) {
        super((s));
    }
}
