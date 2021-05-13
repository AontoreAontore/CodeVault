
import java.util.Comparator;

/**
 * A class to represent an oval (as an example of implementing an interface, in
 * this case Measurable). See also: Measurable.java Rectangle.java Circle.java
 *
 * @author Mark Young (A00000000) and Md Ishfaq Alam (A00450249)
 */
public class Oval implements Measurable {

    private double width;
    private double height;

    /**
     * Create an oval with the given dimensions.
     *
     * @param reqWidth the requested width (must be positive)
     * @param reqHeight the requested height (must be positive)
     */
    public Oval(double reqWidth, double reqHeight) {
        requirePositive("Width", reqWidth);
        requirePositive("Height", reqHeight);
        width = reqWidth;
        height = reqHeight;
    }

    /**
     * Change the width of this oval.
     *
     * @param reqWidth the new width (must be positive)
     */
    public void setWidth(double reqWidth) {
        requirePositive("Width", reqWidth);
        width = reqWidth;
    }

    /**
     * Change the height of this oval.
     *
     * @param reqHeight the new height (must be positive)
     */
    public void setHeight(double reqHeight) {
        requirePositive("Height", reqHeight);
        height = reqHeight;
    }

    /**
     * Get this oval's width.
     *
     * @return this oval's width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get this oval's height.
     *
     * @return this oval's height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get this oval's area.
     *
     * @return this oval's area.
     */
    public double getArea() {
        return Math.PI * width * height / 4;
    }

    /**
     * Get this oval's perimeter. NOTE: this method returns an approximation,
     * since the exact value requires summing an infinite series.
     *
     * @return an approximation of this oval's perimeter.
     */
    public double getPerimeter() {
        // use Ramanujan's approximation
        double a = width / 2;
        double b = height / 2;
        double h = Math.pow(a - b, 2) / Math.pow(a + b, 2);

        return Math.PI * (a + b) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
    }

    /**
     * Get this oval's circumference. NOTE: this method returns an
     * approximation, * since the exact value requires summing an infinite
     * series.
     *
     * @return an approximation of this oval's circumference.
     */
    public double getCircumference() {
        return getPerimeter();
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
