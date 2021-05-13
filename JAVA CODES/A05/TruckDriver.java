import java.util.Scanner;

/**
 * A program to test the Vehicle and Truck classes.
 *
 * @author Mark Young (A00000000)
 */
public class TruckDriver extends Person{

    public static void main(String[] args) {

         /* Part 0 -- This should work right away */
        
        // Person creation and toString method
        Person bob = new Person("Bob");
        Person carol = new Person("Carol");
        System.out.println("Here are our people:");
        System.out.println("\tbob is " + bob);
        System.out.println("\tcarol is " + carol);
        pause();

        // Part 1 -- Remove THIS line when your Vehicle class is created
        
        // Vehicle creation and toString method
        Vehicle v = new Vehicle("Tata", 4, bob);
        System.out.println("Here is our Vehicle:");
        System.out.println("\t" + v);
        System.out.println();
        System.out.println("Test toString(): "
            + ("Bob's 4-door Tata".equals(v.toString()) ? "PASS" : "FAIL"));

        // getters
        System.out.println("Test Vehicle Make: "
            + ("Tata".equals(v.getMake()) ? "PASS" : "FAIL"));
        System.out.println("Test Vehicle Doors: "
            + (4 == v.getNumDoors() ? "PASS" : "FAIL"));
        System.out.println("Test Vehicle Owner: "
            + (bob.hasSameName(v.getOwner()) ? "PASS" : "FAIL"));
        
        // setter
        v.setOwner(carol);
        System.out.println("Test Vehicle Owner setter: "
            + (carol.hasSameName(v.getOwner()) ? "PASS" : "FAIL"));
        pause();

        // Part 2 -- Remove THIS line when your Truck class is created
        
        // Truck creation and toString method
        Truck t1 = new Truck("BMC", 2, carol, 1.5, 1.75);
        System.out.println("Here is our Truck:");
        System.out.println("\t" + t1);
        System.out.println();
        System.out.println("Test toString(): "
            + ("Carol's 2-door BMC".equals(t1.toString()) ? "PASS" : "FAIL"));

        // check getters
        System.out.println("Test Truck Make: "
            + ("BMC".equals(t1.getMake()) ? "PASS" : "FAIL"));
        System.out.println("Test Truck Doors: "
            + (2 == t1.getNumDoors() ? "PASS" : "FAIL"));
        System.out.println("Test Truck Owner: "
            + (carol.hasSameName(t1.getOwner()) ? "PASS" : "FAIL"));
        System.out.println("Test Truck Load Limit: "
            + (1.5 == t1.getLoadLimit() ? "PASS" : "FAIL"));
        System.out.println("Test Truck Towing Limit: "
            + (1.75 == t1.getTowLimit() ? "PASS" : "FAIL"));
        System.out.println();

        // check equals method
        Truck t2 = new Truck("BMC", 2, carol, 1.5, 1.75);
        System.out.println("Test Truck equals (same): "
            + (t2.equals(t1) ? "PASS" : "FAIL"));
        Truck t3 = new Truck("BMC", 2, carol, 1.5, 2.75);
        System.out.println("Test Truck equals (diff.tow.cap.): "
            + (!t3.equals(t1) ? "PASS" : "FAIL"));
        t3 = new Truck("BMC", 2, carol, 2.5, 1.75);
        System.out.println("Test Truck equals (diff.load.cap.): "
            + (!t3.equals(t1) ? "PASS" : "FAIL"));
        t3 = new Truck("BMC", 2, bob, 1.5, 1.75);
        System.out.println("Test Truck equals (diff.own.): "
            + (t3.equals(t1) ? "PASS" : "FAIL"));
        t3 = new Truck("BMC", 4, carol, 1.5, 1.75);
        System.out.println("Test Truck equals (diff.num.doors.): "
            + (!t3.equals(t1) ? "PASS" : "FAIL"));
        t3 = new Truck("Fuso", 2, carol, 1.5, 1.75);
        System.out.println("Test Truck equals (diff.make): "
            + (!t3.equals(t1) ? "PASS" : "FAIL"));
        t3 = new Truck("BMC", 4, bob, 2.5, 2.75);
        System.out.println("Test Truck equals (all diff.): "
            + (!t3.equals(t1) ? "PASS" : "FAIL"));
        System.out.println();
        
        // check inheritance
        Object oBob = bob;
        Object oV = v;
        Object oT1 = t1;
        System.out.println("Test Person inheritance: "
            + (!(oBob instanceof Vehicle || oBob instanceof Truck) 
                    ? "PASS" : "FAIL"));
        System.out.println("Test Vehicle inheritance: "
            + (!(oV instanceof Person || oV instanceof Truck) 
                    ? "PASS" : "FAIL"));
        System.out.println("Test Truck inheritance: "
            + (!(oT1 instanceof Person) && oT1 instanceof Vehicle 
                    ? "PASS" : "FAIL"));
        pause();

        //Part 3 -- Remove THIS line when you're ready to test weird stuff

        System.out.print("Test illegal number of doors: ");
        try {
            Vehicle badDoors = new Vehicle("Toyota", 0, bob);
            System.out.println("FAIL -- no exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("FAIL -- other exception");
        }
        System.out.print("Test unowned Vehicle creation: ");
        try {
            Vehicle unowned = new Vehicle("Toyota", 4, null);
            System.out.println("PASS");
            System.out.print("Unowned toString(): ");
            System.out.println(
                "no one's 4-door Toyota".equals(unowned.toString())
                    ? "PASS"
                    : "FAIL -- wrong String");
        } catch (IllegalArgumentException iae) {
            System.out.println("FAIL -- says illegal!");
        } catch (NullPointerException e) {
            System.out.println("FAIL -- null pointer");
        } catch (Exception e) {
            System.out.println("FAIL -- other exception");
        }
        System.out.print("Test illegal load-capactity: ");
        try {
            Truck badLoad = new Truck("BMW", 2, carol, 0, 1.5);
            System.out.println("FAIL -- no exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("FAIL -- other exception");
        }
        System.out.print("Test illegal tow-capactity: ");
        try {
            Truck badTow = new Truck("BMW", 2, carol, 1.5, 0);
            System.out.println("FAIL -- no exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS");
        } catch (Exception e) {
            System.out.println("FAIL -- other exception");
        }

        Object oT2 = new Truck("BMC", 2, carol, 1.5, 1.75);
        System.out.println("Test Truck equals (object argument): "
            + (t1.equals(oT2) ? "PASS" : "FAIL"));
        System.out.println("Test Truck equals (String argument): "
            + (t1.equals("Carol's 2-door BMC") ? "FAIL" : "PASS"));
        pause();

        /* LEAVE THIS LINE IN
        ...AND THIS ONE, TOO! */
    }

    private static final Scanner KBD = new Scanner(System.in);

    /**
     * Prompt the user and wait for them to press the enter key.
     * NOTE: the input stream must be empty.
     */
    private static void pause() {
        System.out.println();
        System.out.print("Press Enter...");
        KBD.nextLine();
        System.out.println();
    }

}