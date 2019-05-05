package exceptions;

import java.io.IOException;

/**
 * Excpetion for empty abonents list
 * @author Ekaterina Zhukova
 * @version 1.0
 */
public class EmptyAbonentsListException extends IOException {
    public EmptyAbonentsListException() {
        this("List of abonents is Empty!");
    }

    public EmptyAbonentsListException(String s) {
        super((s));
    }
}
