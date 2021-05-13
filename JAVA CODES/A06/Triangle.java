
import java.util.Comparator;

/**
 * A class to represent a right triangle (as an example of implementing an
 * interface, in this case Measurable). See also: Measurable.java Rectangle.java
 *
 * @author Mark Young (A00000000) and Md Ishfaq Alam (A00450249)
 */
public class Triangle implements Measurable {

    private double width;
    private double height;

    /**
     * Create a right triangle with the given dimensions.
     *
     * @param reqWidth the requested width (must be positive)
     * @param reqHeight the requested height (must be positive)
     */
    public Triangle(double reqWidth, double reqHeight) {
        requirePositive("Width", reqWidth);
        requirePositive("Height", reqHeight);
        width = reqWidth;
        height = reqHeight;
    }

    /**
     * Change the width of this triangle.
     *
     * @param reqWidth the new width (must be positive)
     */
    public void setWidth(double reqWidth) {
        requirePositive("Width", reqWidth);
        width = reqWidth;
    }

    /**
     * Change the height of this triangle.
     *
     * @param reqHeight the new height (must be positive)
     */
    public void setHeight(double reqHeight) {
        requirePositive("Height", reqHeight);
        height = reqHeight;
    }

    /**
     * Get this triangle's width.
     *
     * @return this triangle's width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get this triangle's height.
     *
     * @return this triangle's height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get this triangle's area.
     *
     * @return this triangle's area.
     */
    public double getArea() {
        return width * height / 2;
    }

    /**
     * Get this triangle's perimeter.
     *
     * @return this triangle's perimeter.
     */
    public double getPerimeter() {
        return width + height + getHypotenuse();
    }

    /**
     * Get the length of this triangle's hypotenuse.
     *
     * @return this triangle's hypotenuse.
     */
    public double getHypotenuse() {
        return Math.hypot(width, height);
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
