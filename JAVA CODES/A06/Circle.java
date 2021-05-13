
import java.util.Comparator;

/**
 * A class to represent a circle (as an example of implementing an interface, in
 * this case Measurable). See also: Measurable.java Rectangle.java
 *
 * @author Mark Young (A00000000) and Md Ishfaq Alam (A00450249)
 */
public class Circle implements Measurable {

    private double radius;

    /**
     * Create a circle with the given radius.
     *
     * @param reqRadius the radius of the new circle
     */
    public Circle(double reqRadius) {
        requirePositive(reqRadius);
        radius = reqRadius;
    }

    /**
     * This circle's radius.
     *
     * @return the radius of this circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This circle's width.
     *
     * @return the width of this circle.
     */
    public double getWidth() {
        return getDiameter();
    }

    /**
     * This circle's height.
     *
     * @return the height of this circle.
     */
    public double getHeight() {
        return getDiameter();
    }

    /**
     * This circle's circumference.
     *
     * @return the circumference of this circle
     */
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    /**
     * This circle's diameter.
     *
     * @return the diameter of this circle
     */
    public double getDiameter() {
        return 2 * radius;
    }

    /**
     * This circle's area.
     *
     * @return the area of this circle
     */
    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * This circle's perimeter.
     *
     * @return the perimeter of this circle
     */
    @Override
    public double getPerimeter() {
        return getCircumference();
    }

    /**
     * Convert thsi circle to a String.
     *
     * @return a String representing this circle
     */
    @Override
    public String toString() {
        return "Circle (r = " + radius + ")";
    }

    /**
     * Throw an exception if the given value is not positive.
     *
     * @param value the value to check
     * @throws IllegalArgumentException if value &leq; 0.0.
     */
    private static void requirePositive(double value) {
        if (value <= 0.0) {
            throw new IllegalArgumentException("Radius: " + value);
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
