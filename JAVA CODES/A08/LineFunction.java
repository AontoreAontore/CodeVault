
/**
 * An interface representing a function that takes a line of integer arguments
 * (as a String). The getName method returns a name for this function (such as
 * "add", "multiply", "maximum"). It should be in lower case letters, and a
 * single word.
 *
 * The processLine method evaluates the function on the given arguments. It
 * scans the arguments out of the given line and processes them as required. 
 * The result of the evaluation is returned as he method value. 
 *
 * The processLine method throws exceptions when its expectations are not met.
 * <ul>
 * <li> InputMismatchException if any argument is not an int
 * <li> NoSuchElementException if there aren't enuf arguments
 * <li> TooManyArgumentsException if there are too many arguments
 * </ul>
 * Note that the first two kinds of exceptions are thrown by the Scanner class
 * nextInt method under the same or similar circumstances. The last Exception
 * class is unique to this application and is defined in this same package.
 *
 * @author Mark Young (A00000000)
 */
public interface LineFunction {
    
    /**
     * Return the name of this LineFunction object. This should be the name of
     * the function this LineFunction object calculates. For example, factorial,
     * leastCommonMultiple, greatestCommonDivisor, ....
     * 
     * @return the name of this function
     */
    public String getName();
    
    /**
     * Process the given line as the arguments to this function. The line should
     * consist entirely of integer numerals.
     * 
     * @param line the line of arguments for this function
     * @return the value of this function applied to those arguments
     * @throws InputMismatchException if any processed argument is not an int
     * @throws NoSuchElementException if there are too few arguments
     * @throws TooManyArgumentsException if there are too many arguments
     */
    public int processLine(String line);

}