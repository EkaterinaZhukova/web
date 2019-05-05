package exceptions;

/**
 * Excpetion for not found service
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class NotFoundServiceException extends NullPointerException{
    public NotFoundServiceException() {
        this("Service not found exception");
    }

    public NotFoundServiceException(String s) {
        super((s));
    }
}
