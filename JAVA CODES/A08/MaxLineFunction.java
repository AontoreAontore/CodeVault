
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class which finds the maximum of the (at least one) ints.
 *
 * @author Md Ishfaq Alam (A00450249)
 */
public class MaxLineFunction implements LineFunction {

    /**
     * Provides the name of the Line function
     *
     * @return - name of the line function called "Max"
     */
    @Override
    public String getName() {
        return "max";
    }

    /**
     * finds the maximum of the (at least one) ints.
     *
     * @param line - There may be as many ints as the user likes any valid int
     * value (including negative numbers) is allowed.
     * @return - returns the max value
     */
    @Override
    public int processLine(String line) {
        List<Integer> list = new ArrayList<>();
        Scanner readFromLine = new Scanner(line);

        int max;

        while (readFromLine.hasNext()) {
            if (readFromLine.hasNextInt()) {

                Integer value = readFromLine.nextInt();
                list.add(value);

            } else {
                throw new InputMismatchException("Only int "
                        + "arguments are allowed");
            }

        }
        if (list.isEmpty()) {
            throw new NoSuchElementException("No values given");
        } else {
            Collections.sort(list);
            max = list.get(list.size() - 1);
        }

        return max;
    }

}
