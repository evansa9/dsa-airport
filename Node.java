/**
 * Represents a Node in the different ADT implementations.
 */
public class Node<T> 
{
  private T item;
  private Node<T> next;

  /**
   * Creates a new Node for the given item.
   *
   * @param newItem The item of the node.
   */
  public Node(T newItem) 
  {
    item = newItem;
    next = null;
  } // end constructor

  /**
   * Creates a new node for the given item, with the provided
   * nextNode.
   *
   * @param newItem The item of the node.
   * @param nextNode The next node in the structure.
   */
  public Node(T newItem, Node<T> nextNode) 
  {
    item = newItem;
    next = nextNode;
  } // end constructor

  /**
   * Changes the item of the node.
   *
   * @param newItem The item to change to.
   */
  public void setItem(T newItem) 
  {
    item = newItem;
  } // end setItem
  
  /**
   * Gets the item of the node.
   *
   * @return The item of the node.
   */
  public T getItem() 
  {
    return item;
  } // end getItem

  /**
   * Sets the next node.
   *
   * @param nextNode The new next node. 
   */
  public void setNext(Node<T> nextNode) 
  {
    next = nextNode;
  } // end setNext

  /**
   * Returns the next node.
   *
   * @return The next node.
   */
  public Node<T> getNext() 
  {
    return next;
  } // end getNext
} // end class Node
