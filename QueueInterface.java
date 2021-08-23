/**
 * Generic interface for the queue ADT.
 */
public interface QueueInterface<T> {

  /**
   * @return True if the Queue is empty.
   */
  public boolean isEmpty();
   
  /**
   * Adds a new item to the back of a queue.
   *
   * @param newItem The item to be inserted.
   * @throws QueueException If the item cannot be added.
   */
  public void enqueue(T newItem) throws QueueException;
  
  /**
   * Retrieves and removes the front of a queue.
   *
   * @return The item that was removed.
   * @throws QueueException If the Queue is empty.
   */
  public T dequeue() throws QueueException;
  
  /**
   * Removes all items from a queue.
   */
  public void dequeueAll();

  /**
   * Retrieves the item at the front of a queue.
   *
   * @return The item at the front of the queue.
   * @throws QueueException If the queue is empty. 
   */
  public T peek() throws QueueException;
  
  /**
   * Retursn the string representation of the Queue.
   * 
   * @return The string representation of the Queue.
   */
  public String toString();
}  // end QueueInterface

