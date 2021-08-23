/**
 * Linked structure implementation of a list.
 */
public class ListSLS<T> implements ListInterface<T> 
{
  // reference to linked list of items
  private Node<T> head; 

  /**
   * Creates a new ListSLS
   */
  public ListSLS() 
  {
    head = null;
  }  // end default constructor

  /**
   * @return True if the List is empty, false otherwise.
   */
  public boolean isEmpty() 
  {
    return head == null;
  }  // end isEmpty

  /**
   * @return The size of the list.
   */
  public int size() 
  {
    int total = 0;
    Node<T> current = head;
    while (current != null)
    {
      current = current.getNext();
      total++;
    }
    return total;
  }  // end size

  /**
   * Method that finds the Node at the given index.
   *
   * @param index The index of the Node.
   * @return The Node at the given index.
   */
  private Node<T> find(int index) 
  {
  // --------------------------------------------------
  // Locates a specified node in a linked list.
  // Precondition: index is the number of the desired
  // node. Assumes that 0 <= index <= numItems 
  // Postcondition: Returns a reference to the desired 
  // node.
  // --------------------------------------------------
    Node<T> curr = head;
    for (int skip = 0; skip < index; skip++) 
    {
      curr = curr.getNext();
    } // end for
    return curr;
  } // end find

  /**
   * Method that gets the item at the given index.
   *
   * @param index The index of the item.
   * @return The item at the given index.
   * @throws ListIndexOutOfBoundsException if the index is not valid.
   */
  public T get(int index) throws ListIndexOutOfBoundsException 
  {
    if (index >= 0 && index < size()) 
    {
      // get reference to node, then data in node
      Node<T> curr = find(index);
      T dataItem = curr.getItem();
      return dataItem;
    } 
    else 
    {
      throw new ListIndexOutOfBoundsException("List index out of bounds exception on get");
    } // end if
  } // end get

  /**
   * Method that adds the given item at the given index.
   *
   * @param index The index to add the item at.
   * @param item The item to add.
   * @throws ListIndexOutOfBoundsException if the index is not valid.
   */
  public void add(int index, T item) throws ListIndexOutOfBoundsException 
  {
    if (index >= 0 && index <= size()) 
    {
      if (index == 0) 
      {
        // insert the new node containing item at
        // beginning of list
        Node<T> newNode = new Node<T>(item, head);
        head = newNode;
      } 
      else 
      {
        Node<T> prev = find(index-1);
        // insert the new node containing item after 
        // the node that prev references
        Node<T> newNode = new Node<T>(item, prev.getNext());
        prev.setNext(newNode);
      } // end if
    } 
    else 
    {
      throw new ListIndexOutOfBoundsException("List index out of bounds exception on add");
    } // end if
  }  // end add

  /**
   * Method that removes the item at the given index.
   *
   * @param index The index of the item to remove.
   * @throws ListIndexOutOfBoundsException if the index is not valid.
   */
  public void remove(int index) throws ListIndexOutOfBoundsException 
  {
    if (index >= 0 && index < size()) 
    {
      if (index == 0) 
      {
        // delete the first node from the list
        head = head.getNext();
      } 
      else 
      {
        Node<T> prev = find(index-1);
        // delete the node after the node that prev
        // references, save reference to node
        Node<T> curr = prev.getNext(); 
        prev.setNext(curr.getNext());
      } // end if
    } // end if
    else 
    {
      throw new ListIndexOutOfBoundsException("List index out of bounds exception on remove");
    } // end if
  }   // end remove

  /**
   * Method that removes all items from the list.
   */
  public void removeAll() 
  {
    // setting head to null causes list to be
    // unreachable and thus marked for garbage 
    // collection
    head = null;
  } // end removeAll

  /**
   * Method that converts the list into a string, seperating the String representations
   * of each item with a newline.
   *
   * @return A string of the format "Item1\nItem2\nItem3\n..."
   */
  public String toString()
  {
    String result = "";

    Node<T> current = head;
    while(current != null)
    {
      result += (current.getItem() + "\n");
      current = current.getNext();
    }

    return result;
  }

} // end ListReferenceBased
