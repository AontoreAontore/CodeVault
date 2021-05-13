
import java.util.Arrays;
import java.util.Scanner;

/**
 *Your program uses an array to keep track of the incomes of multiple families. 
 * It also uses various methods to calculate statistics for those families.
 * @author MD ISHFAQ ALAM (A00450249)
 */
public class PovertyData {
    
    // Create global variables
    private static final Scanner KBD = new Scanner(System.in);
    private static double[] incomesOfFamilies = new double[]{};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Create variables
        int numberOfFamilies;
        double relativeNeedsIncome, absolutePovertyRate, relativePovertyRate,
               basicsNeedIncome, averageIncome, medianIncome;

        printIntroduction();

        pause();

        numberOfFamilies = numberOfFamilies();

        incomesOfFamilies = readNonNegativeIncomes(numberOfFamilies);

        basicsNeedIncome = readBasicsNeedIncome();

        averageIncome = calculateAverageIncome(incomesOfFamilies);

        medianIncome = calculateMedianIncome(incomesOfFamilies,
                numberOfFamilies);

        relativeNeedsIncome = calculateRelativeNeedsIncome(medianIncome);

        absolutePovertyRate = calculateAbsolutePovertyRate(incomesOfFamilies, 
                basicsNeedIncome, numberOfFamilies);

        relativePovertyRate = calculateRelativePovertyRate(incomesOfFamilies, 
                relativeNeedsIncome, numberOfFamilies);

        pause();

        printReport(averageIncome, basicsNeedIncome, absolutePovertyRate, 
                relativePovertyRate, medianIncome, relativeNeedsIncome);

        pause();
    }

    /**
     * A method to print the Introduction 
     */
    private static void printIntroduction() {
        System.out.print("Poverty Rate Program" + 
                "\n" + "--------------------");
        System.out.print("\n\n" + "This program reads data on "
                + "family incomes and produces a report on poverty\n"
                + "rates among the families.  ");
        System.out.print("\n\n" + "By Md Ishfaq Alam "
                + "(A00450249)" + "\n" + "Winter 2021");
      
    }

    /**
     * A method to do pauses in between programs
     */
    private static void pause() {
        
        System.out.print("\n\n" + "...press enter..." + "\n");
        KBD.nextLine();
    }

    /**
     * A method to get the number of families residing in a country
     * @return numberOfFamilies
     */
    private static int numberOfFamilies() {
        System.out.print("How many families are there in this country? ");
        int numberOfFamilies = KBD.nextInt();

        while (numberOfFamilies < 2) {
            System.out.println("There must be at least"
                    + " two families in the country");
            System.out.print("How many families are there in this country? ");
            numberOfFamilies = KBD.nextInt();
        }
        System.out.println();
        return numberOfFamilies;
    }

    /**
     * A method to read in Non negative Incomes from the family
     * @param numberOfFamilies
     * @return familyIncome
     */
    private static double[] readNonNegativeIncomes(int numberOfFamilies) {

        double[] familyIncome = new double[numberOfFamilies];

        for (int i = 0; i < numberOfFamilies; ++i) {

            System.out.print("What is the income for family # "
                    + "" + (i + 1) + " ?" + "  ");
            familyIncome[i] = KBD.nextDouble();

            while (familyIncome[i] < 0) {
                System.out.println(" each income must be non-negative"
                        + " (that is, zero is allowed as an income)");
                System.out.print("What is the income for "
                        + "family # " + (i + 1) + " ?" + "  ");
                familyIncome[i] = KBD.nextDouble();
            }

        }
        return familyIncome;
    }

    /**
     * A method to calculate Basics Needs Income
     * @return Basics Needs Income
     */
    private static double readBasicsNeedIncome() {
        System.out.println();
        System.out.print("What is the minimum income "
                + "required to support a family? ");
        double basicsNeedIncome = KBD.nextDouble();

        while (basicsNeedIncome < 0) {
            System.out.println("Cannot be less than zero");
            System.out.print("What is the minimum income "
                    + "required to support a family? ");
            basicsNeedIncome = KBD.nextInt();

        }
        return basicsNeedIncome;
    }

    /**
     * A method to print the Results of the survey taken
     * @param averageIncome
     * @param basicsNeedsIncome
     * @param absolutePovertyRate
     * @param relativePovertyRate
     * @param medianIncome
     * @param relativeNeedsIncome 
     */
    private static void printReport(double averageIncome, 
            double basicsNeedsIncome,
            double absolutePovertyRate, double relativePovertyRate, 
            double medianIncome, double relativeNeedsIncome) {

        System.out.print("Report " + "\n" + "------" + "\n\n");
        System.out.print("Average Income : $" 
                + averageIncome + "\n");
        System.out.print("Basics Need Income : $"
                + basicsNeedsIncome + "\n");
        System.out.print("Absolute Poverty Rate : " 
                + absolutePovertyRate + "%" + "\n\n");
        System.out.print("Median Income : $" + medianIncome + "\n");
        System.out.print("Relative Needs Income : $"
                + relativeNeedsIncome + "\n");
        System.out.print("Relative Poverty Rate : "
                + relativePovertyRate + "%");
    }

    /**
     * A method to calculate Average Income
     * @param incomeOfFamilies
     * @return averageIncome
     */
    private static double calculateAverageIncome(double[] incomeOfFamilies) {
        double sum, count;
        sum = 0;
        count = 0;

        for (int i = 0; i < incomeOfFamilies.length; ++i) {
            sum += incomeOfFamilies[i];
            count += 1;
        }

        double averageIncome = sum / count;
        return averageIncome;
    }

    /**
     * A method to calculate Median Income
     * @param incomeOfFamilies
     * @param numberOfFamilies
     * @return median Income
     */
    private static double calculateMedianIncome(double[] incomeOfFamilies,
            int numberOfFamilies) {
        
        Arrays.sort(incomeOfFamilies);
        double medianIncome;

        if (numberOfFamilies >= 2 && !(numberOfFamilies % 2 == 0)) {
            medianIncome = incomeOfFamilies[incomeOfFamilies.length / 2];
        } else {
            medianIncome = (incomeOfFamilies[incomeOfFamilies.length / 2] 
                    + incomeOfFamilies[(incomeOfFamilies.length / 2) - 1]) / 2;
        }
        return medianIncome;
    }

    /**
     * A method to calculate Absolute Poverty Rate
     * @param incomesForFamilies
     * @param basicsNeedIncome
     * @param numberOfFamilies
     * @return absolute poverty rate
     */
    private static double calculateAbsolutePovertyRate(
            double[] incomesForFamilies, 
            double basicsNeedIncome, int numberOfFamilies) {
        
        int count = 0;
        double absolutePovertyRate;
        
        for (int i = 0; i < numberOfFamilies; ++i) {

            if (incomesForFamilies[i] < basicsNeedIncome) {
                count += 1;
            }
        }

        absolutePovertyRate = ((double) count / (double) numberOfFamilies)*100;
        return absolutePovertyRate;
    }

    /**
     * A method to calculate Relative Poverty Rate
     * @param incomesForFamilies
     * @param relativeNeedsIncome
     * @param numberOfFamilies
     * @return relative poverty rate
     */
    private static double calculateRelativePovertyRate(
            double[] incomesForFamilies, 
            double relativeNeedsIncome, int numberOfFamilies) {
        
        double relativePovertyRate;
        int count = 0;
        
        for (int i = 0; i < numberOfFamilies; ++i) {

            if (incomesForFamilies[i] < relativeNeedsIncome) {
                count += 1;
            }
        }
        relativePovertyRate = ((double) count / (double) numberOfFamilies)*100;
        return relativePovertyRate;
    }

    /**
     * A method to calculate relative needs income
     * @param medianIncome 
     * @return relative needs income
     */
    private static double calculateRelativeNeedsIncome(double medianIncome) {
        double relativeNeedsIncome = 0.6 * medianIncome;
        return relativeNeedsIncome;
    }
}
