
/**
 * This class represents what a truck should be like (subclass)
 *
 * @author Md Ishfaq Alam (A00450249)
 */
public class Truck extends Vehicle {

    //create instance variable
    private double loadLimit;
    private double towLimit;

    /**
     * Primary Constructor for creating a Truck Object, which uses the
     * superclass constructor along with two new traits load limit and TowLimit
     *
     * @param reqMake - requested Make of the vehicle
     * @param reqNumberOfDoors - requested number of doors of the vehicle
     * @param reqOwner - requested owner name of the vehicle
     * @param reqLoadLimit - requested Load limit for the truck
     * @param reqTowLimit - requested Tow limit for the truck
     */
    public Truck(String reqMake, int reqNumberOfDoors, Person reqOwner, 
            double reqLoadLimit, double reqTowLimit) {
        super(reqMake, reqNumberOfDoors, reqOwner);
        
        if (reqLoadLimit > 0) {
            loadLimit = reqLoadLimit;
        } 
        else {
            throw new IllegalArgumentException();
        }
        if (reqTowLimit > 0) {
            towLimit = reqTowLimit;
        } 
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * a getter for Load Limit
     *
     * @return
     */
    public double getLoadLimit() {
        return loadLimit;
    }

    /**
     * a getter for Tow Limit
     *
     * @return
     */
    public double getTowLimit() {
        return towLimit;
    }

    /**
     * Method from object class which was inherited by vehicle and in turn,
     * inherited by subclass truck. This method is overridden
     *
     * @param other - represents another object named 'that' for comparison with
     * this object
     * @return true if instance variables for this and that object are equal
     */
    @Override
    public boolean equals(Object other) {

        if (other instanceof Truck) {
            Truck that = (Truck) other;
            if (this.getTowLimit() != that.getTowLimit()) {
                return false;
            }
        }

        if (other instanceof Truck) {
            Truck that = (Truck) other;
            if (this.getLoadLimit() != that.getLoadLimit()) {
                return false;
            }
        }

        if (other instanceof Truck) {
            Truck that = (Truck) other;
            if (this.getNumDoors() != that.getNumDoors()) {
                return false;
            }
        }

        if (other instanceof Truck) {
            Truck that = (Truck) other;
            if (this.getMake() != that.getMake()) {
                return false;
            }
        }
        
        if (!(other instanceof Truck)){
            return false;
        }
        //Otherwise
        return true;
    }
}
