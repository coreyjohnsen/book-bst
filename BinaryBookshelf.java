//////////////// P10 Binary Bookshelf //////////////////////////
//
// Title: BinaryBookshelf.java
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

import java.util.ArrayList;

/**
 * An instantiable class that represents a BST for Books
 */
public class BinaryBookshelf {

  private TreeNode<Book> root;
  private int size;
  private Attribute[] sortList;

  // constructors

  /**
   * Makes a new BinaryBookshelf with a set order of attributes to compare
   * 
   * @param sortList is the order of attributes to compare
   */
  public BinaryBookshelf(Attribute[] sortList) {
    if (sortList.length != 4 || sortList[0] != Attribute.AUTHOR)
      throw new IllegalArgumentException("invalid attribute list");
    for (int i = 0; i < sortList.length; i++) {
      for (int j = i + 1; j < sortList.length; j++) {
        if (sortList[j] == sortList[i])
          throw new IllegalArgumentException("invalid attribute list");
      }
    }
    this.sortList = sortList;
  }

  /**
   * Gets the size of the BinaryBookshelf O(1) complexity
   * 
   * @returns the number of nodes in the tree
   */
  public int size() {
    return this.size;
  }

  /**
   * Determines if the tree is empty O(1) complexity
   * 
   * @returns true if the tree has size 0 and a null root, false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0 && this.root == null;
  }

  /**
   * Gets the order that attributes are compare in
   * 
   * @return a string displaying the order that attributes are compare in
   */
  public String getAttributeOrder() {
    String returnedString = "";
    for (int i = 1; i <= sortList.length; i++) {
      returnedString += i + ":" + sortList[i - 1] + " ";
    }
    return returnedString.strip();
  }

  /**
   * Determines if the bookshelf contains a specified book O(logN) complexity
   * 
   * @param book is the Book to search for
   * @return true if the tree contains book, false otherwise
   */
  public boolean contains(Book book) {
    if (this.root == null)
      return false;
    return containsHelper(book, this.root);
  }

  /**
   * Helper method for contains the recursively searches the tree
   * 
   * @param book    is the book to search for
   * @param current is the current node of the search
   * @return true if the book is found in this subtree, false otherwise
   */
  protected boolean containsHelper(Book book, TreeNode<Book> current) {
    if (current.getData().equals(book)) {
      // base case1
      return true;
    } else {
      if (compareToHelper(current.getData(), book) < 0 && current.getRight() != null)
        return containsHelper(book, current.getRight()); // book is greater than current
      else if (compareToHelper(current.getData(), book) > 0 && current.getLeft() != null)
        return containsHelper(book, current.getLeft()); // book is less than current
      else
        return false; // book not in tree (base case 2)
    }
  }


  /**
   * A helper method for compareTo that compares two books in the specified order of attributes
   * 
   * @param one is the first book to compare
   * @param two is the second book to compare
   * @return an int < 0 if one is less than two, int > 0 if one is greater than two, 0 if equal
   */
  protected int compareToHelper(Book one, Book two) {
    for (int i = 0; i < sortList.length; i++) {
      if (one.compareTo(two, sortList[i]) != 0)
        return one.compareTo(two, sortList[i]);
    }
    return 0;
  }


  /**
   * Gets all the books by a specified author
   * 
   * @param authorName is the author to look for
   * @return an ArrayList containing all the books in the bookshelf written by authorName
   */
  public ArrayList<Book> getBooksByAuthor(String authorName) {
    if (this.root == null)
      return new ArrayList<Book>();
    return getBooksByAuthorHelper(authorName, this.root);
  }


  /**
   * Helper method for getBooksByAuthor that recursively searches a subtree
   * 
   * @param authorName is the author to look for
   * @param current    is the current node
   * @return an ArrayList containing all the books in the subtree written by authorName
   */
  protected ArrayList<Book> getBooksByAuthorHelper(String authorName, TreeNode<Book> current) {
    ArrayList<Book> books = new ArrayList<Book>();
    if (authorName.compareTo(current.getData().getAuthor()) == 0) {
      // add current and check left and right
      books.add(current.getData());
      if (current.getRight() != null)
        books.addAll(getBooksByAuthorHelper(authorName, current.getRight()));
      if (current.getLeft() != null)
        books.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));
    }
    if (authorName.compareTo(current.getData().getAuthor()) > 0 && current.getRight() != null)
      books.addAll(getBooksByAuthorHelper(authorName, current.getRight())); // check right
    else if (authorName.compareTo(current.getData().getAuthor()) < 0 && current.getLeft() != null)
      books.addAll(getBooksByAuthorHelper(authorName, current.getLeft())); // check left

    return books;
  }

  /**
   * Returns a string representation of the bookshelf O(N) complexity
   * 
   * @return a String that displays all books in the tree
   */
  public String toString() {
    if (this.root == null)
      return "";
    return toStringHelper(this.root);
  }

  /**
   * Helper method for toString that recursively gets the string representation of a subtree
   * 
   * @return a String that displays all books in the subtree
   */
  protected String toStringHelper(TreeNode<Book> current) {
    String returned = "";
    if (current.getLeft() != null) // left child
      returned += toStringHelper(current.getLeft()) + "\n";
    returned += current.getData().toString() + "\n"; // self
    if (current.getRight() != null)
      returned += toStringHelper(current.getRight()) + "\n"; // right child
    
    return returned.strip();
  }

  /**
   * Returns the root of the tree
   * 
   * @return the node that is the root of the tree
   */
  protected TreeNode<Book> getRoot() {
    return this.root;
  }

  /**
   * Clears the bookshelf
   */
  public void clear() {
    this.size = 0;
    this.root = null;
  }

  /**
   * Inserts a book in the correct location of the tree
   * 
   * @param book is the book to add to the tree
   */
  public void insertBook(Book book) {
    if (isEmpty())
      this.root = new TreeNode<Book>(book); // root is null, set root to new book
    else
      insertBookHelper(book, this.root); // insert in correct location

    this.size++;
  }

  /**
   * Helper method for insertBook that recursively searches for the correct location to add the book
   * to
   * 
   * @param book    is the book to add
   * @param current is the current node
   */
  protected void insertBookHelper(Book book, TreeNode<Book> current) {
    if (book.equals(current.getData()))
      throw new IllegalArgumentException("Book already in tree"); // book is duplicate
    if (compareToHelper(book, current.getData()) < 0) { // book is less than current
      if (current.getLeft() == null)
        current.setLeft(new TreeNode<Book>(book)); // book is new left child
      else
        insertBookHelper(book, current.getLeft()); // do same on left child
    } else { // book is greater than current
      if (current.getRight() == null)
        current.setRight(new TreeNode<Book>(book)); // book is new right child
      else
        insertBookHelper(book, current.getRight()); // do same on right child
    }
  }

}


