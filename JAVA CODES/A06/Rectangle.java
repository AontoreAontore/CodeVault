
import java.util.Comparator;

/**
 * A class to represent a rectangle (as an example of implementing an interface,
 * in this case Measurable). See also: Measurable.java Circle.java
 *
 * @author Mark Young (A00000000) and Md Ishfaq Alam (A00450249)
 */
public class Rectangle implements Measurable {

    /**
     * the width of this rectangle
     */
    private double myWidth;
    /**
     * the height of this rectangle
     */
    private double myHeight;

    /**
     * Create a rectangle with the given dimensions.
     *
     * @param width the width of the new rectangle
     * @param height the height of the new rectangle
     */
    public Rectangle(double width, double height) {
        requirePositive("Width", width);
        requirePositive("Height", height);
        myWidth = width;
        myHeight = height;
    }

    /**
     * Get this rectangle's height.
     *
     * @return the height of this rectangle
     */
    public double getHeight() {
        return myHeight;
    }

    /**
     * Get this rectangle's width.
     *
     * @return the width of this rectangle
     */
    public double getWidth() {
        return myWidth;
    }

    /**
     * Get this rectangle's perimeter.
     *
     * @return the perimeter of this rectangle
     */
    @Override
    public double getPerimeter() {
        return 2 * (myWidth + myHeight);
    }

    /**
     * Get this rectangle's area.
     *
     * @return the area of this rectangle
     */
    @Override
    public double getArea() {
        return myWidth * myHeight;
    }

    /**
     * Create a String representation of this Rectangle.
     *
     * @return a String showing this Rectangle's dimensions
     */
    @Override
    public String toString() {
        return "Rectangle (" + myWidth + "x" + myHeight + ")";
    }

    /**
     * Throw an exception if the given value is not positive.
     *
     * @param label the label to include in the exception
     * @param value the value to check
     * @throws IllegalArgumentException if value &leq; 0.0.
     */
    private static void requirePositive(String label, double value) {
        if (value <= 0.0) {
            throw new IllegalArgumentException(label + ": " + value);
        }
    }

    /**
     * definition of compareTo method which is implemented from Measurable
     *
     * @param t - a measurable object to which comparison is based upon
     * @return sorted out width
     */
    @Override
    public int compareTo(Measurable t) {
        return Double.compare(getArea(), t.getArea());
    }

    /**
     * a comparator which compares one measurable object with the other
     */
//    public static final Comparator<Measurable> BY_AREA
//            = (one, other)
//            -> Double.compare(one.getArea(), other.getArea());

}
