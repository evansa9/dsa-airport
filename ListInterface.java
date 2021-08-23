/**
 * Basic interface for a generic list.
 */
public interface ListInterface<T> 
{
  /**
   * @return True if empty, false otherwise.
   */
  boolean isEmpty();

  /**
   * @return The size of the list.
   */
  int size();

  /**
   * Method that adds the given item at the specified index.
   *
   * @param item The item to add.
   * @param index The index to add the item at.
   * @throws ListIndexOutOfBoundsException if the index is not valid.
   */
  void add(int index, T item) 
                  throws ListIndexOutOfBoundsException;

  /**
   * Method that gets the item at the given index.
   *
   * @param index The index of the item.
   * @return The item at the given index.
   * @throws ListIndexOutOfBoundsException if the index is not valid.
   */
  T get(int index) 
                    throws ListIndexOutOfBoundsException;

  /**
   * Method that removes the item at the given index.
   *
   * @param index The index of the item.
   * @throws ListIndexOutOfBoundsException If the index is not valid.
   */
  void remove(int index) 
                     throws ListIndexOutOfBoundsException;

  /**
   * Method that removes all of the items from the list.
   */
  void removeAll();
}  // end ListInterface
