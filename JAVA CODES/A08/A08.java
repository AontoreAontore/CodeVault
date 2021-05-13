
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A program to read and execute functions that operate on lines of integers.
 * The functions are provided in separate classes, so that the program can add
 * all and only the functions it is interested in.
 *
 * @author Mark Young (A00000000) and Md Ishfaq Alam (A00450249)
 */
public class A08 {

    private static final Scanner KBD = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create variables
        String command, restOfLine;
        List<LineFunction> commandList = new ArrayList<>();
        LineFunction chosen;

        // introduce yourself
        printIntroduction();
        pause();

        // TODO: create and add command processors
        AddLineFunction add = new AddLineFunction();
        MultiplyLineFunction multiply = new MultiplyLineFunction();
        FactorialLineFunction factorial = new FactorialLineFunction();
        MaxLineFunction max = new MaxLineFunction();
        MinLineFunction min = new MinLineFunction();

        commandList.add(add);
        commandList.add(multiply);
        commandList.add(factorial);
        commandList.add(max);
        commandList.add(min);

        // enter command loop
        System.out.print(">>> ");
        command = KBD.next().toLowerCase();
        while (!command.equals("quit")) {
            restOfLine = KBD.nextLine();
            if (command.equals("help") || command.equals("?")) {
                showHelp(commandList);
            } else {
                // get the matching command in commandList
                chosen = findFunction(command, commandList);

               // TODO: apologize if the command is not recognized...
                if (!(command.startsWith("add")
                        || command.startsWith("mul") 
                        || command.startsWith("fac")
                        || command.startsWith("max")
                        || command.startsWith("min")) 
                        || command.startsWith("multiplys")) {
                    System.out.println("I am afraid "
                            + "I dont recognize " + command);
             }
             // ...otherwise execute the command and deal with any exceptions
                // that arise
                else {
                    try {
                        int total = chosen.processLine(restOfLine);
                        System.out.print("Answer: " + total + "\n");
                    } catch (InputMismatchException ime) {
                        System.out.println(ime.getMessage());

                    } catch (NoSuchElementException nsee) {
                        System.out.println(nsee.getMessage());

                    } catch (TooManyArgumentsException tmae) {
                        System.out.println(tmae.getMessage());
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }
                }

            }
            pause();

            // get next command
            System.out.print(">>> ");
            command = KBD.next().toLowerCase();

        }
    }

    /**
     * Print introduction for this program
     */
    private static void printIntroduction() {
        System.out.println("Line-Oriented Function Evaluator");
        System.out.println("--------------------------------\n");
        System.out.println("Enter commands and I will evaluate them.");
        System.out.println("Enter HELP or ? to get help.\n");
        System.out.println("By Mark Young (A00000000)");
        System.out.println("And ...");
    }

    /**
     * Show the allowed commands in this program, including the names of
     * functions.
     *
     * @param commandList the list of functions saved in this program
     */
    private static void showHelp(List<LineFunction> commandList) {
        System.out.println("\n"
                + "You may enter 'help' or '?' to get this help message.\n"
                + "You may enter 'quit' to end the program.\n"
                + "You may choose from any of the following functions:\n");
        for (LineFunction func : commandList) {
            System.out.println("\t* " + func.getName());
        }
    }

    /**
     * Prompt the user and wait for them to press the enter key
     */
    private static void pause() {
        System.out.println();
        System.out.print("...press enter...");
        KBD.nextLine();
        System.out.println();
    }

    /**
     * Return the first function that matches the given command, or null if no
     * command matches.
     *
     * TO THE STUDENT: DOCUMENT YOUR MATCHING DECISIONS!
     *
     * A match should be EITHER:
     * <ul>
     * <li> the full name of the LineFunction (e.g. command must be the full
     * word "maximum" in order to match a LineFunction named "maximum") </li>
     * <li> an abbreviation match (e.g. command "max" matches a LineFunction
     * named "maximum") </li>
     * </ul>
     * You may also require a minimum length for an abbreviation, to rule out
     * (for example) cases where command "m" matches any function.
     *
     * REVISE THE DESCRIPTION ABOVE TO DOCUMENT YOUR CHOICES!
     *
     * @param command the command to look up
     * @param commandList the list of commands available
     * @return the matching command, or null if no command matches
     */
    private static LineFunction findFunction(
            String command,
            List<LineFunction> commandList) {
        // find the matching LineFunction
        if (command.startsWith("add")) {
            return commandList.get(0);
        } else if (command.startsWith("mul")) {
            return commandList.get(1);
        } else if (command.startsWith("fac")) {
            return commandList.get(2);
        } else if (command.startsWith("max")) {
            return commandList.get(3);
        } else if (command.startsWith("min")) {
            return commandList.get(4);
        } else {
            return null;
        }

    }

}
