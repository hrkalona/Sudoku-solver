/**
 * This class defines a number with the count of it's instances in the board.
 * @author hrkalona
 */
public class NumberOrder {
  private char number; //The number.
  private int count; //Instances of this number in the sudoku board.

  /**
   * The constractor, NumberOrder, creates a number with the count of its instances on the board.
   * @param number Which number.
   * @param count The initial instance count.
   */
  public NumberOrder(char number, int count) {

      this.number = number;
      this.count = count;

  }

  /**
   * The method, getIntNumber, converts the number from character form into integer form.
   * @return The integer form of a character number.
   */
  public int getIntNumber() {
     
      return Integer.parseInt("" + number);

  }

  /**
   * The method, getCharNumber, returns the number.
   * @return The number.
   */
  public char getCharNumber() {

      return number;

  }

  /**
   * The method, getCount, returns the instances of the number in the board.
   * @return The instances of the number.
   */
  public int getCount() {

      return count;

  }

  /**
   * The method, updateCount, updates the count of the instances when a new number is added.
   */
  public void updateCount() {

      this.count++;
      
  }

}
