/**
 * An instantiable class representing information about a Book for our LinkedBookshelf project.
 * 
 * @author hobbes
 *
 */
public class Book {

  // data
  private static int idGenerator = 0;
  private String authorLastname;
  private String authorFirstname;
  private String title;
  private int pageCount;

  /**
   * The ID value for this Book.
   */
  public final int ID;

  /**
   * A constructor for a Book with no recorded author
   * 
   * @param title     the title of the Book
   * @param pageCount the number of pages in the Book
   */
  public Book(String title, int pageCount) {
    if (pageCount <= 0)
      throw new IllegalArgumentException("Invalid page count");
    this.title = title;
    this.pageCount = pageCount;
    this.ID = idGenerator++;

    // set defaults for an anonymous book
    this.authorFirstname = "";
    this.authorLastname = "";
  }

  /**
   * A constructor for a Book with an author
   * 
   * @param title     the title of the Book
   * @param pageCount the number of pages in the Book
   * @param last      the author's last name
   * @param first     the author's first name
   */
  public Book(String title, int pageCount, String last, String first) {
    this(title, pageCount);
    this.authorFirstname = first;
    this.authorLastname = last;
  }

  /**
   * Accessor for the author value
   * 
   * @return the author of this Book, as "Lastname, Firstname"
   */
  public String getAuthor() {
    return authorLastname + ", " + authorFirstname;
  }

  /**
   * Accessor for the title value
   * 
   * @return the title of this Book
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Accessor for the pageCount value
   * 
   * @return the number of pages in this Book
   */
  public int getPageCount() {
    return this.pageCount;
  }

  /**
   * A nonstandard compareTo - based on the attribute argument, returns a comparison of this Book to
   * otherBook based on one of four possible pieces of data.
   * 
   * @param otherBook the Book to compare this one to
   * @param a         the attribute on which to compare them
   * @return negative if this Book is greater than otherBook, positive if less than otherBook, 0 if
   *         equal.
   */
  public int compareTo(Book otherBook, Attribute a) {
    switch (a) {
      case ID:
        return this.ID - otherBook.ID;
      case TITLE:
        return this.title.compareTo(otherBook.title);
      case AUTHOR:
        return this.getAuthor().compareTo(otherBook.getAuthor());
      case PAGECOUNT:
        return this.pageCount - otherBook.pageCount;
      default:
        return 0;
    }
  }

  /**
   * Constructs a String representation of this Book's data for printing
   * 
   * @return the String representation of this Book
   */
  public String toString() {
    return this.ID + ": \"" + this.title + "\", " + this.getAuthor() + " (" + this.pageCount + ")";
  }

  /**
   * Resets the ID generator for testing purposes. Call this method at the beginning of every test
   * method you write to assure that all Book ID numbers begin from 0 again.
   */
  public static void resetGenerator() {
    idGenerator = 0;
  }

  @Override
  /**
   * Determines if two books are the same
   * 
   * @param other is the Book to compare to
   * @return true if all attributes of both books are the same, false otherwise
   */
  public boolean equals(Object other) {

    if (other instanceof Book) {
      // return this.getAuthor().equals(((Book) other).getAuthor()) &&
      // this.getTitle().equals(((Book) other).getTitle()) &&
      // this.getPageCount() == ((Book) other).getPageCount() && this.ID == (Book) other.ID;
      return this.compareTo((Book) other, Attribute.AUTHOR) == 0
          && this.compareTo((Book) other, Attribute.TITLE) == 0
          && this.compareTo((Book) other, Attribute.ID) == 0
          && this.compareTo((Book) other, Attribute.PAGECOUNT) == 0;
    } else {
      return false;
    }
  }


}
