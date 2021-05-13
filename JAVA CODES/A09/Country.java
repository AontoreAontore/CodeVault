
/**
 * A class holding some information about a country.
 * 
 * @author Mark Young (A00000000)
 */
public class Country {

    private String name;
    private int population;
    private double area;

    /**
     * Create a country with the given information.
     * 
     * @param name          the country's common name
     * @param population    the country's population (total)
     * @param area          the country's area (in km^2)
     */
    public Country(String name, int population, double area) {
        // make sure area and population are OK!
        requirePositivePopulation(population);
        requirePositiveArea(area);
        
        // save country's information
        this.name = name;
        this.population = population;
        this.area = area;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the value of population
     *
     * @return the value of population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Set the value of population
     *
     * @param population new value of population
     */
    public void setPopulation(int population) {
        requirePositivePopulation(population);
        this.population = population;
    }

    /**
     * Get the value of area
     *
     * @return the value of area in sq. km.
     */
    public double getArea() {
        return area;
    }

    /**
     * Set the value of area
     *
     * @param area new value of area in sq. km
     */
    public void setArea(double area) {
        requirePositiveArea(area);
        this.area = area;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Calculate and return the population density
     *
     * @return population density in people per sq. km.
     */
    public double getPopulationDensity() {
        return population / area;
    }
    
    /**
     * Throw an exception if the population is not positive.
     *
     * @param population the supposed population
     * @throws IllegalArgumentException if population &le; 0
     */
    private static void requirePositivePopulation(int population) {
        if (population <= 0) {
            throw new IllegalArgumentException("Illegal population: " 
                    + population);
        }
    }

    /**
     * Throw an exception if the area is not positive.
     *
     * @param area the supposed area
     * @throws IllegalArgumentException if area &le; 0.0
     */
    private static void requirePositiveArea(double area) {
        if (area <= 0.0) {
            throw new IllegalArgumentException("Illegal area: "
                    + ": " + area);
        }
    }

}