
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class which adds up a line of ints.
 *
 * @author Md Ishfaq Alam (A00450249)
 */
public class AddLineFunction implements LineFunction {

    /**
     * Provides the name of the Line function
     *
     * @return - returns name of the Line function called "add"
     */
    @Override
    public String getName() {
        return "add";
    }

    /**
     * Reads a series of integer from a string of line and adds them up
     *
     * @param line - String of line containing Integers. If there are no
     * arguments, then the total returned is zero. There may be as many ints as
     * the user likes and any valid int value (including negative numbers) is
     * allowed.
     * @return - sum of the integers given in the line.
     */
    @Override
    public int processLine(String line) {

        Scanner readFromLine = new Scanner(line);

        int sum = 0;

        while (readFromLine.hasNext()) {
            if (readFromLine.hasNextInt()) {

                Integer value = readFromLine.nextInt();
                sum += value;
            } else {
                throw new InputMismatchException("Only "
                        + "int arguments are allowed");
            }

        }

        return sum;

    }

}
