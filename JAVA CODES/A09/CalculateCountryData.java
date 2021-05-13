
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * a program that reads country data from a file and prints a report
 *
 * @author MD ISHFAQ ALAM (A00450249)
 */
public class CalculateCountryData {

    // Scanner, constant and a list for countries declared globally for use
    public static List<Country> countriesObject = new ArrayList<>();
    public static final int MAX_TRIES = 3;
    public static final Scanner KBD = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Print an introduction 
        printIntroduction();

        //Read data from file
        readData();

        //print report using Print f method call
        printReport(countriesObject);
    }

    /**
     * Sets up a Scanner which takes in a file object to read from for input
     *
     * @return Scanner with the appropriate object which represents the file
     */
    public static Scanner getScannerForInput() {
        //Give user three tries
        for (int i = 1; i <= MAX_TRIES; ++i) {
            try {
                System.out.print("Enter file name : ");
                String fileName = KBD.nextLine();
                File file = new File(fileName);
                return new Scanner(file);
            } catch (FileNotFoundException fnf) {
                System.out.println("I can't find that file!");
            }
        } 

        // print the quitting message after failing three times. 
        System.err.println("Too many failures.");
        System.err.println("Quitting");
        System.exit(1);

        // Just for technical reasons eh !
        return null;

    }

    /**
     * Reads in data from the input file
     *
     * @return A list of country objects
     */
    public static List<Country> readData() {

        String nameOfCountry = null;
        int populationOfCountry;
        double areaOfCountry;

        //Get the file name from the user
        Scanner fileInput = getScannerForInput();
        while (fileInput.hasNext()) {
            try {
                nameOfCountry = fileInput.nextLine();
                populationOfCountry = fileInput.nextInt();
                areaOfCountry = fileInput.nextDouble();

                Country country = new Country(nameOfCountry, 
                        populationOfCountry, areaOfCountry);

                countriesObject.add(country);

                if (fileInput.hasNext()) {
                    fileInput.nextLine();
                }

            } catch (InputMismatchException ime) {
                System.out.println("Error reading data for " + nameOfCountry);
                System.out.println("    Skipping corrupt country data.");
                fileInput.nextLine();

            } catch (IllegalArgumentException iae) {
                System.out.println("Error reading data for " + nameOfCountry);
                System.out.println("    Inappropiate population or area:" + 
                        " " + iae.getMessage() + ".");
                fileInput.nextLine();

            } catch (NoSuchElementException nse) {
                System.out.println("Error reading data for " + nameOfCountry);
                System.out.println("    Unexpected end-of-file.");

            }
        }
        fileInput.close();

        return countriesObject;
    }

    /**
     * Prints a properly formatted report from the data collected as input
     *
     * @param countries - takes in the list of country objects to gather data
     */
    public static void printReport(List<Country> countries) {

        // format the heading of the table appropiately
        System.out.printf("\n\n%-14s%17s%25s%25s\n", "Country", "Population", 
                " Area (km^2) ", "Density (/km^2)");
        System.out.printf("%-14s%17s%25s%25s\n", "-------", "----------",
                "------------", "---------------");

        // use a for each loop to get a print of the report
        for (Country Country : countries) {
            System.out.printf("%-14s%,17d%,25.0f%25.2f\n", Country.getName(), 
                    Country.getPopulation(), Country.getArea(), 
                    Country.getPopulationDensity());

        }

        // nice gap which seperates the build message from the output
        System.out.println("\n\n\n");

    }

    /**
     * Prints an Introduction about the program
     */
    public static void printIntroduction() {
        System.out.println("Country Report");
        System.out.println("--------------");
        System.out.println("\nThis program reads country data "
                + "from a file and prints a report.");
        System.out.println("\nBy MD ISHFAQ ALAM (A00450249)\n");
    }

}
