
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class which multiplies the numbers on the line
 *
 * @author Md Ishfaq Alam (A00450249)
 */
public class MultiplyLineFunction implements LineFunction {

    /**
     * Provides the name of the line function
     *
     * @return - returns the name of the line function "multiply"
     */
    @Override
    public String getName() {
        return "multiply";
    }

    /**
     * Multiplies the numbers on the line
     *
     * @param line - There may be as many ints as the user likes, and any valid
     * int value (including negative numbers) is allowed.
     * @return - returns the product of the integers(mult). If there are no
     * arguments, then the product returned is one.
     *
     */
    @Override
    public int processLine(String line) {

        Scanner readFromLine = new Scanner(line);

        int mult = 1;

        while (readFromLine.hasNext()) {
            if (readFromLine.hasNextInt()) {

                Integer value = readFromLine.nextInt();
                mult *= value;

            } else {
                throw new InputMismatchException("Only int "
                        + "arguments are allowed");
            }

        }

        return mult;
    }

}
