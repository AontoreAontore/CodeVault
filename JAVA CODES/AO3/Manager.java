import java.util.Scanner;
/**
 *
 * @author MD ISHFAQ ALAM (A00450249)
 */
public class Manager {

    /**
     * @param args the command line arguments
     */
    
     private static final Scanner KBD = new Scanner(System.in);
    private static final int NUM_DAYS = 3;
    
    public static void main(String[] args) {
       Cabin[] cabins;
        double totalIncome = 0.00;
        String transaction;

        // introduce yourself
        System.out.println("\n\n"
                + "Resort Manager\n"
                + "--------------\n\n"
                + "This program creates cabins and lets them out.");
        pause();

        // set rates
        readRates();
        pause();

        // create cabins
        cabins = buildCabins();
        reportBuild(cabins);
        pause();

        // run simulation
        for (int day = 1; day <= NUM_DAYS; ++day) {
            // ask for first transaction of day
            System.out.print("Next Transaction [in/out/close]? ");
            transaction = KBD.nextLine().toLowerCase();

            // go until transaction is close
            while (!"close".startsWith(transaction)) {
                if ("in".startsWith(transaction)) {
                    checkIn(cabins);
                } else if ("out".startsWith(transaction)) {
                    checkOut(cabins);
                } else {
                    System.out.println("The only valid transactions are "
                            + "in, out or close.");
                    System.out.println("    in -- check new customers in");
                    System.out.println("    out -- check customers out");
                    System.out.println("    close -- end the day");
                }
                System.out.println();
                System.out.print("Next Transaction [in/out/close]? ");
                transaction = KBD.nextLine().toLowerCase();
            }

            // report day's income
            totalIncome += getAndReportIncome(cabins);
            pause();
        }

        // report total income for the period
        reportTotalIncome(totalIncome);
        pause();
    }

    /**
     * Read the rates for this resort from the user.
     */
    private static void readRates() {
        Cabin.setBaseRate(readARate("basic rental charge"));
        Cabin.setExtraRoomRate(readARate("charge for each extra bedroom"));
        Cabin.setExtraPersonRate(readARate("charge for each extra person"));
    }

    /**
     * Read a non-negative number representing a charge.
     *
     * @param item the item being charged for
     */
    private static double readARate(String item) {
        double rate;
        String prompt = "What is the " + item + "? ";

        // get a non-negative number
        System.out.print(prompt);
        rate = KBD.nextDouble();
        KBD.nextLine();
        while (rate < 0) {
            System.out.println("Rates cannot be negative!");
            System.out.print(prompt);
            rate = KBD.nextDouble();
            KBD.nextLine();
        }

        return rate;
    }

    /**
     * Create the cabins for this resort.
     *
     * @return an array of Cabins of various sizes
     */
    private static Cabin[] buildCabins() {
        int numCabins, numRooms;
        String name;
        Cabin[] result;

        // get number of Cabins
        System.out.print("How many cabins at this resort? ");
        numCabins = KBD.nextInt();
        KBD.nextLine();
        while (numCabins < 1) {
            System.out.println("You must have at least one cabin!");
            System.out.print("How many cabins at this resort? ");
            numCabins = KBD.nextInt();
            KBD.nextLine();
        }

        // create the Cabins
        result = new Cabin[numCabins];
        for (int i = 0; i < numCabins; ++i) {
            // get its name
            System.out.print("What is cabin " + (i + 1) + "'s name? ");
            name = KBD.nextLine();
            
            // get its number of bedrroms
            System.out.print("How many bedrooms does " + name + " have? ");
            numRooms = KBD.nextInt();
            KBD.nextLine();
            while (numRooms < 1) {
                System.out.println("You must have at least one bedroom!");
                System.out.print("How many bedrooms does " + name + " have? ");
                numRooms = KBD.nextInt();
                KBD.nextLine();
            }
            
            // create the Cabin
            result[i] = new Cabin(name, numRooms);
        }

        return result;
    }

    /**
     * Check new customers into a Cabin. Find an empty Cabin big enuf for the
     * group and rent it to them.
     *
     * @param cabins the Cabins to choose from
     */
    private static void checkIn(Cabin[] cabins) {
        int numGuests, roomNumber;

        // ask for number of guests
        System.out.print("How many guests are there in this group? ");
        numGuests = KBD.nextInt();
        KBD.nextLine();


        // find the first Cabin with sufficient room
        roomNumber = -1;    // no room yet!
        for (int i = 0; i < cabins.length && roomNumber < 0; ++i) {
            if (!cabins[i].isOccupied()
                    && cabins[i].getMaxOccupancy() >= numGuests) {
                roomNumber = i;
            }
        }

        // assign to found room or report failure
        if (roomNumber < 0) {
            System.out.println("I'm sorry, "
                    + "but we cannot accomodate your party.");
        } else {
            System.out.println("We will put you in " 
                    + cabins[roomNumber].getName());
            cabins[roomNumber].setOccupants(numGuests);
            System.out.println("The rent for " + numGuests + " guests "
                    + "is " + cabins[roomNumber].getCurrentRent());
        }
    }

    /**
     * Check customers out of their Cabin.
     *
     * @param cabins the Cabins at this resort
     */
    private static void checkOut(Cabin[] cabins) {
        int roomNumber;

        // find their room
        System.out.print("What room are you leaving? ");
        roomNumber = getCabinByName(cabins, KBD.nextLine());
        while (roomNumber < 0 || !cabins[roomNumber].isOccupied()) {
            System.out.println("That can't be right!");
            if (roomNumber < 0) {
                System.out.println("There is no such cabin.");
            } else {
                System.out.println("That room is not occupied.");
            }
            System.out.println("Please try again.");
            System.out.print("What room are you leaving? ");
            roomNumber = getCabinByName(cabins, KBD.nextLine());
        }

        // empty the room and thank them for their business
        cabins[roomNumber].removeOccupants();
        System.out.println("Thank you. Please come again.");
    }

    /**
     * Look up a Cabin by name.
     *
     * @param cabins the Cabins at this resort
     * @param name the name of the Cabin
     * @return the location of the named Cabin in the given array; or -1 if no
     * Cabin has that name
     */
    private static int getCabinByName(Cabin[] cabins, String name) {
        int roomNumber = -1;
        for (int i = 0; i < cabins.length && roomNumber < 0; ++i) {
            if (cabins[i].getName().startsWith(name)) {
                roomNumber = i;
            }
        }
        return roomNumber;
    }

    /**
     * Report on the Cabins available at this resort.
     *
     * @param cabins the Cabins at this resort
     */
    private static void reportBuild(Cabin[] cabins) {
        // title
        System.out.println();
        System.out.println("Cabin Name  Num Rooms  Max Occ.  Basic Rental");
        System.out.println("----------  ---------  --------  ------------");
        
        // lines
        for (int i = 0; i < cabins.length; ++i) {
            reportBuildLine(cabins[i]);
        }
        
        // extra space
        System.out.println();
    }

    /**
     * Report the statistics of one Cabin at this resort.
     *
     * @param cabin the Cabin to report on
     */
    private static void reportBuildLine(Cabin cabin) {
        System.out.printf("%-12s%9d%10d%14.2f\n",
                cabin.getName(),
                cabin.getNumRooms(),
                cabin.getMaxOccupancy(),
                cabin.getBasicRent());
    }

    /**
     * Get and report the income for the Cabins in this resort.
     *
     * @param cabins the Cabins in this resort
     */
    private static double getAndReportIncome(Cabin[] cabins) {
        double total = 0.00;

        // title
        System.out.println();
        System.out.println("Cabin Name  Guests  Base Rate  Surcharge  "
                + "Rent for Night");
        System.out.println("----------  ------  ---------  ---------  "
                + "--------------");
        
        // lines
        for (int i = 0; i < cabins.length; ++i) {
            reportIncomeLine(cabins[i]);
            total += cabins[i].getCurrentRent();
        }
        
        // extra space
        System.out.println();
        return total;
    }

    /**
     * Report on the income from one Cabin
     *
     * @param cabin the Cabin to report on
     */
    private static void reportIncomeLine(Cabin cabin) {
        System.out.printf("%-12s%6d%11.2f%11.2f%12.2f\n",
                cabin.getName(),
                cabin.getNumOccupants(),
                cabin.getBasicRent(),
                cabin.getSurcharge(),
                cabin.getCurrentRent());
    }

    /**
     * Report the total income over the time period.
     *
     * @param income the amount of income
     */
    private static void reportTotalIncome(double income) {
        System.out.println("The total income over " + NUM_DAYS + " days was "
                + "$" + String.format("%3.2f", income) + ".");
    }

    /**
     * Prompt the user and wait for them to press the enter key.
     */
    private static void pause() {
        System.out.print("\nPress enter...");
        KBD.nextLine();
        System.out.println();
    }

}