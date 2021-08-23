/**
 * Class that models a plane, with a flight number, destination, and
 * a runway which it is taking off on.
 *
 * @author Gavin Coulthurst
 * @version 2019.12.03
 */
public class Plane
{
    private String flightNum;
    private String destination;
    private Runway runway;

    /**
     * Creates a new plane with the given flight number, destination,
     * and runway. 
     *
     * @param flightNum The plane's flight number.
     * @param destination The plane's destination.
     * @param runway The runway the plane will take off on.
     */
    public Plane(String flightNum, String destination, Runway runway)
    {
        this.flightNum = flightNum;
        this.destination = destination;
        this.runway = runway;
    }

    /**
     * Changes the runway the plane is taking off on.
     *
     * @param runway The new runway for the plane to take off on.
     */
    public void setRunway(Runway runway)
    {
        this.runway = runway;
    }

    /**
     * @return The plane's flight number.
     */
    public String getFlightNum()
    {
        return flightNum;
    }

    /**
     * @return The plane's destination.
     */
    public String getDestination()
    {
        return destination;
    }
    
    /**
     * @return The runway the plane will take off on. 
     */
    public Runway getRunway()
    {
        return runway;
    }

    /**
     * Returns information about the plane as a string in the 
     * given format: Flight (flightNumber) to (destination).
     *
     * @return Information about the plane the described format.
     */
    public String toString()
    {
        return "Flight " + flightNum + " to " + destination + ".";
    }
}
