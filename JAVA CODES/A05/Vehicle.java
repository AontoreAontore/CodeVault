
/**
 * This class represents what a vehicle looks like (superclass)
 *
 * @author Md Ishfaq Alam (A00450249)
 */
public class Vehicle {

    // create Instance variables
    private final String make;
    private int numberOfDoors;
    private Person owner;

    /**
     * Primary constructor which creates a vehicle object
     *
     * @param reqMake - requested make of the vehicle
     * @param reqNumberOfDoors
     * @param reqOwner
     */
    public Vehicle(String reqMake, int reqNumberOfDoors, Person reqOwner) {
        owner = reqOwner;
        make = reqMake;
        if (reqNumberOfDoors > 0) {
            numberOfDoors = reqNumberOfDoors;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * A getter for Make
     *
     * @return
     */
    public String getMake() {
        return make;
    }

    /**
     * a getter for number of doors
     *
     * @return
     */
    public int getNumDoors() {
        return numberOfDoors;

    }

    /**
     * a getter for owner name
     *
     * @return
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * a setter for Owner
     *
     * @param newName - new name of the owner
     */
    public void setOwner(Person newName) {
        owner = newName;
    }

    /**
     * A to String() method which is inherited from Object and overridden to
     * produce output strings as required
     *
     * @return String
     */
    @Override
    public String toString() {
        if (owner == null) {
            return "no one's " + numberOfDoors + "-door " + make;
        } else {
            return owner.getName() + "'s " + numberOfDoors + "-door " + make;
        }
    }
}
