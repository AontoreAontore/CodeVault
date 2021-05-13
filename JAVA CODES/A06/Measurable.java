/**
 * An interface for methods that return
 * the perimeter and area of an object.
 *
 * @author Mark Young (A00000000)
 */
public interface Measurable extends Comparable<Measurable> {

    /** 
     * The width of this measurable object.
     *
     * @return  the width of this object
     */
    public double getWidth();
 
    /** 
     * The height of this measurable object.
     *
     * @return  the height of this object
     */
    public double getHeight();

    /** 
     * The perimeter of this measurable object.
     *
     * @return  the permimeter of this object
     */
    public double getPerimeter();
 
    /** 
     * The area of this measurable object.
     *
     * @return  the area of this object
     */
    public double getArea();

} 