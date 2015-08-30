package exceptions;

public class MultipleBeansWithSameNameException extends Exception {

    public MultipleBeansWithSameNameException(String name) {
        super("multiple beans with name: " + name);
    }
}
