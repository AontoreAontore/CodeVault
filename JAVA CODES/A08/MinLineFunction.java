
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class which finds the minimum of the (at least one) ints.
 *
 * @author Md Ishfaq Alam (A00450249)
 */
public class MinLineFunction implements LineFunction {

    /**
     * Provides the name of the Line function
     *
     * @return - returns name of the Line function called "Min"
     */
    @Override
    public String getName() {
        return "min";
    }

    /**
     * Finds the minimum of the (at least one) ints.
     *
     * @param line - There may be as many ints as the user likes, and any valid
     * int value (including negative numbers) is allowed.
     *
     * @return - returns the min value
     */
    @Override
    public int processLine(String line) {

        List<Integer> list = new ArrayList<>();
        Scanner readFromLine = new Scanner(line);

        int min;

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
            min = list.get(0);
        }

        return min;
    }

}
