//////////////// P10 Binary Bookshelf //////////////////////////
//
// Title: TreeNode.java
// Course: CS 300 Fall 2021
//
// Author: Corey Johnsen
// Email: cjjohnsen@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * An instantiable class the represents a single node in a BST
 */
public class TreeNode<T> {

  // data
  private T data;
  private TreeNode<T> left;
  private TreeNode<T> right;

  // constructors

  /**
   * Creates a new node with set data
   * 
   * @param data is the data to store in the node
   */
  public TreeNode(T data) {
    this.data = data;
  }

  /**
   * Creates a new node with set data and children
   * 
   * @param data  is the data to store in the node
   * @param left  is the left child
   * @param right is the right child
   */
  public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  // accessors

  /**
   * Gets the data stored in the node
   * 
   * @return the data stored in the node
   */
  public T getData() {
    return data;
  }

  /**
   * Gets the left child of the node
   * 
   * @return the left child of the node
   */
  public TreeNode<T> getLeft() {
    return left;
  }

  /**
   * Gets the right child of the node
   * 
   * @return the right child of the node
   */
  public TreeNode<T> getRight() {
    return right;
  }

  // mutators

  /**
   * Sets the left child of the node
   * 
   * @param left is the new left child of the node
   */
  public void setLeft(TreeNode<T> left) {
    this.left = left;
  }

  /**
   * Sets the right child of the node
   * 
   * @param right is the new right child of the node
   */
  public void setRight(TreeNode<T> right) {
    this.right = right;
  }

  // other methods

  @Override
  /**
   * Returns the string representation of the node
   * 
   * @return the string representation of the data in the node
   */
  public String toString() {
    return data.toString();
  }

}
