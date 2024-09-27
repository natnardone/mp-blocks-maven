package edu.grinnell.csc207.blocks;

/**
 * A text block divided into quarters by a single character.
 *
 * @author Natalie Nardone
 * @author Jenifer Silva
 */
public class NewBlock implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the quarters.
   */
  AsciiBlock contents;

  /**
   * The character we put around the quarters.
   */
  String quarterChar;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param blockContents
   *   The contents of the block.
   *
   * @param theChar
   *   The character that we use to surround the block.
   */
  public NewBlock(AsciiBlock blockContents, char theChar) {
    this.contents = blockContents;
    this.quarterChar = Character.toString(theChar);
  } // NewBlock(AsciiBlock, char)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    int h = this.contents.height();
    if ((i == 0) || (i == h + 2) || (i == (h / 2) + 1)) {
      // The top, middle, or bottom of the outside
      return this.quarterChar.repeat(this.contents.width() + 3);
    } else if ((i > 0) && (i < (h / 2) + 1)) {
      // Stuff within the top and middle lines
      String newString = new String("");
      String firstHalf = this.contents.row(i - 1).substring(0, this.contents.width() / 2);
      String secondHalf = this.contents.row(i - 1).substring(this.contents.width() / 2);
      newString = newString.concat(this.quarterChar + firstHalf + this.quarterChar
        + secondHalf + this.quarterChar);
      return newString;
    } else if ((i > (h / 2) + 1) && (i < (h + 2))) {
      // Stuff within the middle and bottom lines
      String newString = new String("");
      String firstHalf = this.contents.row(i - 2).substring(0, this.contents.width() / 2);
      String secondHalf = this.contents.row(i - 2).substring(this.contents.width() / 2);
      newString = newString.concat(this.quarterChar + firstHalf + this.quarterChar
        + secondHalf + this.quarterChar);
      return newString;
    } else {
      throw new Exception("Invalid row " + i);
    } // if/else
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return 3 + this.contents.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return 3 + this.contents.width();
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof NewBlock) && (this.eqv((NewBlock) other)));
  } // eqv(AsciiBlock)

    /**
   * Determine if another NewBlock is structurally equivalent to this NewBlock.
   *
   * @param other
   *   The NewBlock to compare to this NewBlock.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(NewBlock other) {
    return (this.contents.eqv(other.contents)) && (this.quarterChar.equals(other.quarterChar));
  } // eqv(NewBlock)
} // class NewBlock
