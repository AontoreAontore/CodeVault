
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class which calculates the factorial of the (one!) int on the line.
 *
 * @author Md Ishfaq Alam (A00450249)
 */
public class FactorialLineFunction implements LineFunction {

    /**
     * Provides the name of the Line function
     *
     * @return returns the name of the Line Function "factorial"
     */
    @Override
    public String getName() {
        return "factorial";
    }

    /**
     * Calculates the factorial of the (one!) int on the line
     *
     * @param line - String of line which can contain only one integer. No
     * negative integers allowed
     * @return - returns the factorial of a valid int provided. If the argument
     * is zero, the result returned is one
     */
    @Override
    public int processLine(String line) {
        List<Integer> factorialList = new ArrayList<>();

        Scanner readFromLine = new Scanner(line);

        int fact = 1;

        while (readFromLine.hasNext()) {

            Integer value = readFromLine.nextInt();
            factorialList.add(value);

            if (factorialList.size() > 1) {
                throw new TooManyArgumentsException("That's too "
                        + "many arguments");
            } else if (value < 0) {
                throw new IllegalArgumentException("The function didn't "
                        + "like one of your numbers");

            } else {

                for (int i = 0; i < value; ++i) {
                    fact *= value - i;
                }

            }

        }

        if (factorialList.isEmpty()) {

            throw new NoSuchElementException("You did not "
                    + "give enuf arguments");
        }

        return fact;
    }

}
