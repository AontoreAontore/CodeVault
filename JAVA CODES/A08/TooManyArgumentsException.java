
/**
 * An user defined Exception which is thrown when more than one argument
 * is provided for the factorial function
 *
 * @author Md Ishfaq Alam (A00450249)
 */
public class TooManyArgumentsException extends RuntimeException {

    /**
     * A constructor which takes in a String for providing a message of the
     * error that occurred.
     *
     * @param message - A String which contains the error message
     */
    public TooManyArgumentsException(String message) {
        super(message);

    }

}
