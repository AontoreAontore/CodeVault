
/**
 * Cabin class which creates cabin object with rooms in it.
 * @author Md Ishfaq Alam
 */
public class Cabin {

    // Create Instance Variables
    private static final int MAX_PEOPLE_PER_ROOM = 3;
    private final int numRooms;
    private static final int USUAL_PEOPLE_PER_ROOM = 2;
    private final java.lang.String name;
    private static double basicRentalCharge;
    private static double extraRoomRate;
    private static double extraPersonRate;
    private int numOccupants;


    /**
     * Create a Cabin with the given name and number of bedrooms. The number of
     * rooms must be positive (at least one).
     *
     * @param name the name of this Cabin
     * @param numRooms the number of bedrooms for this Cabin
     * @throws IllegalArgumentException if the number of rooms is less than one
     */
    public Cabin(String name, int numRooms) {
        this.name = name;
        if (numRooms < 1) {
            throw new IllegalArgumentException("Invalid number of rooms : "
                    + "" + numRooms);
        } else {
            this.numRooms = numRooms;
        }
    }

    /**
     * Get the name of this Cabin.
     *
     * @return the name of this Cabin
     */
    public String getName() {
        return name;
    }

    /**
     * Get the number of bedrooms in this Cabin.
     *
     * @return the number of bedrooms in this Cabin
     */
    public int getNumRooms() {
        return numRooms;
    }

    /**
     * Get the current number of occupants.
     *
     * @return the number of people registered in this Cabin
     */
    public int getNumOccupants() {
        return numOccupants;
    }

    /**
     * Calculate how much rent this room is bringing in. If the room is
     * unoccupied, then it's not bringing in any rent at all. Otherwise, it's
     * bringing in the basic rent plus any surcharge for extra people.
     *
     * @return 0.00 if the room is unoccupied; basic rent plus surcharge (if
     * any) for occupied cabins
     */
    public double getCurrentRent() {
        double currentRate = 0;
        if (isOccupied()) {
            if (numOccupants == 1) {
                currentRate = getBasicRent();
            } else if (numOccupants > 1) {
                currentRate = getBasicRent() + getSurcharge();
            }

        } else {
            currentRate = 0;
        }
        return currentRate;
    }

    /**
     * The basic rent for this Cabin, based on number of rooms. This is the
     * amount the Cabin rents for when there are no extra people in it.
     *
     * @return the basic rate for renting this room
     */
    public double getBasicRent() {
        double basicRent;
        if (numRooms == 1) {
            basicRent = basicRentalCharge;
        } else {
            basicRent = (numRooms - 1) * extraRoomRate + basicRentalCharge;
        }
        return basicRent;
    }

    /**
     * The surcharge for this Cabin, based on the number of people in it. The
     * surcharge is the extraPersonRate times the number of people over the
     * usual occupancy for this Cabin.
     *
     * @return how much extra is charged for having extra people in the Cabin
     */
    public double getSurcharge() {
        double surcharge = 0;
        if (numOccupants == numRooms * 2) {
            surcharge = 0;
        }

        for (int i = 1; i <= numRooms; ++i) {
            if (numOccupants == (numRooms * 2) + i) {
                surcharge = extraPersonRate * i;
            }
        }
        return surcharge;
    }

    /**
     * Return whether this room is occupied.
     *
     * @return true if the number of occupants in this room is positive
     */
    public boolean isOccupied() {
        boolean occupied;
        if (numOccupants > 0) {
            occupied = true;
        } else {
            occupied = false;
        }
        return occupied;
    }

    /**
     * Rent this Cabin to a group of the given size.
     *
     * @param numOccupants the number of people to register in this Cabin
     * @throws IllegalArgumentException if the number of occupants is less than
     * one or more than the maximum occupancy for this Cabin.
     */
    public void setOccupants(int numOccupants) {
        if (!(1 <= numOccupants && numOccupants <= getMaxOccupancy())) {
            throw new IllegalArgumentException();
        } else {
            this.numOccupants = numOccupants;
        }
    }

    /**
     * Mark this room as open for rental.
     */
    public void removeOccupants() {
        this.numOccupants = 0;
    }

    /**
     * Get the maximum capacity of this Cabin. The maximum capacity of each
     * bedroom is specified by MAX_PEOPLE_PER_ROOM. Thus the maximum occupancy
     * of a Cabin is that many people times the number of rooms in this Cabin.
     *
     * @return the maximum number of people who may stay in this Cabin at one
     * time
     */
    public int getMaxOccupancy() {
        int maxOccupancy;
        maxOccupancy = this.numRooms * MAX_PEOPLE_PER_ROOM;
        return maxOccupancy;
    }

    /**
     * Set the basic rental rate for all Cabins. This is the rental rate for a
     * one-room Cabin before surcharges. The actual rent for any Cabin depends
     * on how many rooms it has and how many people are staying in it.
     *
     * @param rate the rental rate for a basic Cabin
     */
    public static void setBaseRate(double rate) {
        basicRentalCharge = rate;
    }

    /**
     * Set the amount the rate rises for every bedroom after the first. A
     * one-room Cabin charges the baseRate. This is how much extra the basic
     * rent is for each extra bedroom in the Cabin.
     *
     * @param rate how much extra each bedroom is in rent
     */
    public static void setExtraRoomRate(double rate) {
        extraRoomRate = rate;
    }

    /**
     * Set the surcharge for each extra person in the Cabin. Cabins normally
     * hold up to two people per bedroom (see USUAL_PEOPLE_PER_ROOM). Each
     * person beyond that number is charged this amount.
     *
     * @param rate how much extra each person raises the rent
     */
    public static void setExtraPersonRate(double rate) {
        extraPersonRate = rate;
    }

}
