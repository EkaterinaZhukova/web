package exceptions;

import java.io.IOException;

/**
 * Excpetion for empty services list
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class EmptyServiceListException extends IOException {
    public EmptyServiceListException() {
        this("List of services is Empty!");
    }

    public EmptyServiceListException(String s) {
        super((s));
    }
}
