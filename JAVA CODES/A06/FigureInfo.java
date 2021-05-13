
import java.util.Arrays;
import java.util.Scanner;

/**
 * A program to print information on various Measurable objects
 *
 * @author Mark Young (A00000000) 
 * @author Md Ishfaq Alam (A00450249)
 */
public class FigureInfo {

    public static final int NUM_SHAPES = 4;
    public static final int FIGS_PER_SHAPE = 3;
    public static final int TOTAL_FIGS = NUM_SHAPES * FIGS_PER_SHAPE;
    public static final Scanner KBD = new Scanner(System.in);

    public static void main(String[] args) {
        // declare variables
        double width;   // width of various figures
        double height;  // height of various figures
        Rectangle aRectangle;
        Oval anOval;
        Triangle aTriangle;
        Circle aCircle;
        // make an array for up to TOTAL_FIGS measurable shapes
        Measurable[] totalFigs = new Measurable[TOTAL_FIGS];

        // make an int variable to track how many shapes have been read
        int noOfShapesRead = 1;

        // introduce yourself
        printIntroduction();

        // get Rectangles (read FIGS_PER_SHAPE of them)
        for (int i = 0; i < FIGS_PER_SHAPE; ++i) {
            System.out.print("Enter the width and height of a rectangle: ");
            width = KBD.nextDouble();
            height = KBD.nextDouble();
            KBD.nextLine();
            aRectangle = new Rectangle(width, height);

            // add it to the array of measurable shapes
            totalFigs[(noOfShapesRead++) - 1] = aRectangle;
        }

        // get Ovals (read FIGS_PER_SHAPE of them)
        for (int i = 0; i < FIGS_PER_SHAPE; ++i) {
            System.out.print("Enter the width and height of an oval: ");
            width = KBD.nextDouble();
            height = KBD.nextDouble();
            KBD.nextLine();
            anOval = new Oval(width, height);

            // add it to the array of measurable shapes
            totalFigs[(noOfShapesRead++) - 1] = anOval;
        }

        // get Triangles (read FIGS_PER_SHAPE of them)
        for (int i = 0; i < FIGS_PER_SHAPE; ++i) {
            System.out.print("Enter the width and height of a triangle: ");
            width = KBD.nextDouble();
            height = KBD.nextDouble();
            KBD.nextLine();
            aTriangle = new Triangle(width, height);

            // add it to the array of measurable shapes
            totalFigs[(noOfShapesRead++) - 1] = aTriangle;
        }

        // get Circles (read FIGS_PER_SHAPE of them)
        for (int i = 0; i < FIGS_PER_SHAPE; ++i) {
            System.out.print("Enter the diameter of a circle: ");
            width = KBD.nextDouble();
            KBD.nextLine();
            aCircle = new Circle(width / 2.0);

            // add it to the array of measurable shapes
            totalFigs[(noOfShapesRead++) - 1] = aCircle;
        }

        // Sort the array
        Arrays.sort(totalFigs);
//        Arrays.sort(totalFigs, Oval.BY_AREA);
//        Arrays.sort(totalFigs, Rectangle.BY_AREA);
//        Arrays.sort(totalFigs, Triangle.BY_AREA);

        // Print out the sorted array
        System.out.println();
        System.out.println(figureInfoHeader());
        for (int i = 0; i < 12; ++i) {
            System.out.println(className(totalFigs[i])
                    + figureInfo(totalFigs[i]));
        }
        System.out.println();

    }

    /**
     * Print the introduction to this program.
     */
    public static void printIntroduction() {
        System.out.print("\n\n"
                + "Sorted Figure Info\n"
                + "------------------\n\n"
                + "This program reads in the sizes of several shapes "
                + "then prints out\ninformation about them "
                + "in order from smallest to largest.\n\n"
                + "by Mark Young (A00000000) and "
                + "Md Ishfaq Alam (A00450249)\n\n");
        
    }

    /**
     * Create the header for the shape information table.
     *
     * @return the header for the figures table
     */
    public static String figureInfoHeader() {
        return String.format("%-14s%10s%10s%20s%20s",
                "Type",
                "Width", "Height",
                "Area", "Perimeter")
                + "\n"
                + String.format("%-14s%10s%10s%20s%20s",
                        "--------------",
                        "-----", "------",
                        "---------", "---------");
    }

    /**
     * Create the data values for one row of the shape information table, not
     * including the class name.
     *
     * @param m the object described in this row of the figure table.
     * @return one line of the figures table, showing info for the given shape.
     */
    public static String figureInfo(Measurable m) {
        return String.format("%10.1f%10.1f%20.1f%20.1f",
                m.getWidth(), m.getHeight(),
                m.getArea(), m.getPerimeter());
    }

    /**
     * Get the object's class name as a 14+ character wide String. This is for
     * the left column of the shape information table.
     *
     * @param the object we want the class name of.
     * @return the class name of the given object, formatted to width 14.
     */
    public static String className(Object o) {
        return String.format("%-14s", o.getClass().getName());
    }

}
