/**
 * A generic QueueSLS implementation.
 */
public class QueueSLS<T> implements QueueInterface<T>
{
    protected Node<T> tail;
    protected Node<T> head;

    /**
     * Creates a new empty Queue.
     */
    public QueueSLS()
    {
        tail = null;
        head = null;
    }

    /**
     * Tells whether the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return tail == null;
    }

    /**
     * Adds the given item to the back of the queue.
     *
     * @param newItem The item to add.
     */
    public void enqueue(T newItem)
    {
        Node<T> temp = new Node<T>(newItem);
        if (head == null)
        {
            head = temp;
        }
        else
        {
            tail.setNext(temp);
        }
        tail = temp;
    }

    /**
     * Removes and retrieves the item at the front of the queue.
     *
     * @return The item at the front of the queue. 
     * @throws QueueException If the Queue is empty.
     */
    public T dequeue() throws QueueException
    {
        T temp;
        if (tail != null)
        {
            temp = head.getItem();
            head = head.getNext();
            if (head == null)
            {
                tail = null;
            }
        }
        else
        {
            throw new QueueException("Queue exception on dequeue.");
        }
        return temp;
    }

    /**
     * Removes all items from the queue.
     */
    public void dequeueAll()
    {
        tail = null;
        head = null;
    }

    /**
     * Retrieves the item at the front of the queue.
     *
     * @return The item at the front of the queue.
     * @throws QueueException If the queue is empty.
     */
    public T peek() throws QueueException
    {
        T temp;
        if (tail != null)
        {
            temp = head.getItem();
        }
        else
        {
            throw new QueueException("Queue exception on peek.");
        }
        return temp;
    }

    /**
     * Returns the String representation of the Queue.
     *
     * @return The queue as a string in the format: "Item1\nItem2\n..."
     */
    public String toString()
    {
        String result = "";
        Node<T> current = head;
        while (current != null)
        {
            result += current.getItem().toString() + "\n";
            current = current.getNext();
        }
        return result;
    }


}
