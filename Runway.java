/**
 * Class that models a runway, with a name, size, and a queue of planes
 * waiting to takeoff.
 *
 * @author Gavin Coulthurst
 * @version 2019.12.03
 */
public class Runway
{
    private String name;
    private int size;
    private QueueSLS<Plane> planes;

    /**
     * Creates a new Runway with the given name.
     *
     * @param name The name of the Runway.
     */
    public Runway(String name)
    {
        this.name = name;
	size = 0;
        planes = new QueueSLS<Plane>();
    }

    /**
     * returns the name of the runway
     *
     * @return the name of the runway as a String
     */
     public String getName() {
	return name;
     }

     /**
      * return the size of the plane queue
      *
      * @return the int size of the queue
      */
     public int getSize() {
	     return size;
     }

    /**
     * Adds a new plane to the queue of planes waiting to takeoff.
     *
     * @param newPlane The plane to add.
     */
    public void enterRunway(Plane newPlane)
    {
        planes.enqueue(newPlane);
	size++;
    }

    /**
     * Allows a plane to takeoff from the runway.
     *
     * @return The plane that took off.
     * @throws QueueException If there are no planes to takeoff.
     */
    public Plane takeoff() throws QueueException
    {
	size--;
        return planes.dequeue();
    }

    /**
     * Checks if the plane queue is empty
     *
     * @return {@code true} if the plane queue is empty
     */
    public boolean isEmpty() {
	    return planes.isEmpty();
    }

    /**
     * Returns information about planes waiting on the runway.
     *
     * @return Information about the planes waiting to takeoff.
     */
    public String toString()
    {
        String result;
        if (planes.isEmpty())
        {
            result = "No planes are waiting to takeoff on runway " + 
                    name + "!";
        }
        else
        {
            result = "These planes are waiting for takeoff on runway " + 
                    name + " :\n" + planes.toString();
        }

        return result;
    }
}
