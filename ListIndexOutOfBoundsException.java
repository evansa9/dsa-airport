/**
 * Exception which is thrown when a List is passed an out of 
 * bounds index. 
 */
public class ListIndexOutOfBoundsException
    extends IndexOutOfBoundsException
{
    /**
     * Creates a new Exception with the given message.
     *
     * @param s The message associated with the exception.
     */
    public ListIndexOutOfBoundsException(String s)
    {
        super(s);
    }  // end constructor
}  // end ListIndexOutOfBoundsException

