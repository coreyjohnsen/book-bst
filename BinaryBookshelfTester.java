//////////////// P10 Binary Bookshelf //////////////////////////
//
// Title: BinaryBookshelfTester.java
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
 * This class tests that the BST and its nodes work as expected
 */
public class BinaryBookshelfTester {

  /**
   * Called when the program runs
   */
  public static void main(String[] args) {

    System.out.println(testTreeNode());
    System.out.println(testEmptyTree());
    System.out.println(testInsertBook());
    System.out.println(testGetBooksByAuthor());
    System.out.println(testContains());

  }

  /**
   * Tests the constructors and methods for TreeNode
   * 
   * @return true if all constructors and methods work as expected, false otherwise
   */
  public static boolean testTreeNode() {
    try {
      // test node with no child
      TreeNode<Integer> testNode1 = new TreeNode<Integer>(5);
      if (testNode1.getData() != 5)
        return false;
      if (testNode1.getLeft() != null || testNode1.getRight() != null)
        return false;
      // test child
      TreeNode<Integer> testNode2 = new TreeNode<Integer>(2);
      testNode1.setLeft(testNode2);
      if (testNode1.getLeft().getData() != 2 || testNode1.getRight() != null)
        return false;
      testNode1.setLeft(null);
      if (testNode1.getLeft() != null)
        return false;
      // test 3 argument constructor
      testNode1 = new TreeNode<Integer>(1);
      testNode2 = new TreeNode<Integer>(2);
      TreeNode<Integer> testNode3 = new TreeNode<Integer>(3, testNode1, testNode2);
      if (testNode3.getLeft().getData() != 1 || testNode3.getRight().getData() != 2)
        return false;

    } catch (Exception e) {
      // invalid exception
      return false;
    }
    // all tests passed
    return true;
  }

  /**
   * Tests the constructors of BinaryBookshelf
   * 
   * @return true if all constructors work as expected, false otherwise
   */
  public static boolean testEmptyTree() {
    // test invalid constructors
    try {
      BinaryBookshelf shelf = new BinaryBookshelf(new Attribute[0]);
      // no exception thrown
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      BinaryBookshelf shelf = new BinaryBookshelf(new Attribute[3]);
      // no exception thrown
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      Attribute[] attributes =
          new Attribute[] {Attribute.AUTHOR, Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.ID};
      BinaryBookshelf shelf = new BinaryBookshelf(attributes);
      // no exception thrown
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      Attribute[] attributes =
          new Attribute[] {Attribute.TITLE, Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.ID};
      BinaryBookshelf shelf = new BinaryBookshelf(attributes);
      // no exception thrown
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    // test valid cases
    try {
      Attribute[] attributes =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
      BinaryBookshelf shelf = new BinaryBookshelf(attributes);
      if (!shelf.isEmpty() || !shelf.toString().equals("") || shelf.getRoot() != null)
        return false;
      if (!shelf.getAttributeOrder().equals("1:AUTHOR 2:TITLE 3:PAGECOUNT 4:ID"))
        return false;
      if (shelf.contains(new Book(null, 10)) != false
          || shelf.contains(new Book("test", 100)) != false)
        return false;
      if (shelf.getBooksByAuthor("test").size() != 0 || !shelf.getBooksByAuthor("test").isEmpty())
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      // invalid exception
      return false;
    }
    // all tests passed
    return true;
  }

  /**
   * Tests the insertBook method of BinaryBookshelf
   * 
   * @return true inserting books works as expected, false otherwise
   */
  public static boolean testInsertBook() {
    try {
      // test adding to empty
      Attribute[] attributes =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
      BinaryBookshelf shelf = new BinaryBookshelf(attributes);
      Book book1 = new Book("Test1", 400, "Johnsen", "Corey");
      shelf.insertBook(book1);
      if (shelf.isEmpty() || !shelf.getRoot().getData().equals(book1) || shelf.size() != 1)
        return false;
      // test adding smaller
      Book book2 = new Book("Test1", 400, "Aohnsen", "Corey");
      shelf.insertBook(book2);
      if (!shelf.getRoot().getLeft().getData().equals(book2) || shelf.size() != 2)
        return false;
      // test larger
      Book book3 = new Book("Zest1", 400, "Johnsen", "Corey");
      shelf.insertBook(book3);
      if (!shelf.getRoot().getRight().getData().equals(book3) || shelf.size() != 3)
        return false;
    } catch (Exception e) {
      e.printStackTrace();
      // invalid excpetion
      return false;
    }

    // test duplicate value
    try {
      Attribute[] attributes =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
      BinaryBookshelf shelf = new BinaryBookshelf(attributes);
      Book book1 = new Book("Test1", 400, "Johnsen", "Corey");
      shelf.insertBook(book1);
      Book book4 = book1;
      shelf.insertBook(book4);
      // no exception thrown
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing
    } catch (Exception e) {
      return false;
    }

    // all tests passed
    return true;
  }

  /**
   * Tests the contains method of BinaryBookshelf
   * 
   * @return true if the contains method works as expected, false otherwise
   */
  public static boolean testContains() {
    try {
      // test non-recursive
      Attribute[] attributes =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
      BinaryBookshelf shelf = new BinaryBookshelf(attributes);
      Book book1 = new Book("Test1", 400, "Johnsen", "Corey");
      shelf.insertBook(book1);
      Book book2 = new Book("Test1", 400, "Aohnsen", "Corey");
      if (!shelf.contains(book1) || shelf.contains(book2))
        return false;


      // test recursive case
      shelf = new BinaryBookshelf(attributes);
      shelf.insertBook(book1);
      book2 = new Book("Test1", 400, "Bohnsen", "Corey");
      Book book3 = new Book("Test1", 400, "Aohnsen", "Corey");
      Book book4 = new Book("Test1", 400, "Cohnsen", "Corey");
      Book book5 = new Book("Test1", 400, "Zohnsen", "Corey");
      Book book6 = new Book("Test1", 400, "Rohnsen", "Corey");
      shelf.getRoot()
          .setLeft(new TreeNode<Book>(book2, new TreeNode<Book>(book3), new TreeNode<Book>(book4)));
      shelf.getRoot().setRight(new TreeNode<Book>(book5));
      if (!shelf.contains(book1) || !shelf.contains(book5) || !shelf.contains(book4)
          || shelf.contains(book6))
        return false;

    } catch (Exception e) {
      // invalid exception
      return false;
    }
    // all tests passed
    return true;
  }

  /**
   * Tests the getBooksByAuthor method of BinaryBookshelf
   * 
   * @return true if the getBooksByAuthor method works as expected, false otherwise
   */
  public static boolean testGetBooksByAuthor() {
    try {
      // test non-recursive
      Attribute[] attributes =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
      BinaryBookshelf shelf = new BinaryBookshelf(attributes);
      Book book1 = new Book("Test1", 400, "Johnsen", "Corey");
      shelf.insertBook(book1);
      ArrayList<Book> testList = shelf.getBooksByAuthor("Johnsen, Corey");
      if (testList.size() != 1)
        return false;
      testList = shelf.getBooksByAuthor("Lol, Nope");
      if (testList.size() != 0)
        return false;

      // test recursive case
      shelf = new BinaryBookshelf(attributes);
      shelf.insertBook(book1);
      Book book2 = new Book("Test1", 400, "Bohnsen", "Corey");
      Book book3 = new Book("Test1", 400, "Aohnsen", "Corey");
      Book book4 = new Book("Test1", 400, "Cohnsen", "Corey");
      Book book5 = new Book("Zest1", 450, "Johnsen", "Corey");
      shelf.getRoot()
          .setLeft(new TreeNode<Book>(book2, new TreeNode<Book>(book3), new TreeNode<Book>(book4)));
      shelf.getRoot().setRight(new TreeNode<Book>(book5));

      testList = shelf.getBooksByAuthor("Cohnsen, Corey");
      if (testList.size() != 1)
        return false;
      testList = shelf.getBooksByAuthor("Johnsen, Corey");
      if (testList.size() != 2)
        return false;
      testList = shelf.getBooksByAuthor("Lol, Nope");
      if (testList.size() != 0)
        return false;
    } catch (Exception e) {
      // invalid exception
      return false;
    }
    // all tests passed
    return true;
  }

}
