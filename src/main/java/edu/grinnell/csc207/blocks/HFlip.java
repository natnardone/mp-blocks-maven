package edu.grinnell.csc207.blocks;
/**
 * A horizontally flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Natalie Nardone
 * @author Jenifer Silva
 */
public class HFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   */
  public HFlip(AsciiBlock original) {
    this.block = original;
  } // HFlip(AsciiBlock)

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
    String finalstr = new String("");
    String originalStr = new String(block.row(i));

    for (int a = originalStr.length() - 1; a >= 0; a--) {
      finalstr = finalstr.concat(String.valueOf(originalStr.charAt(a)));
    } // for
    return finalstr;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return block.width();
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
    return ((other instanceof HFlip) && (this.eqv((HFlip) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another HFlip is structurally equivalent to this HFlip.
   *
   * @param other
   *   The HFlip to compare to this HFlip.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(HFlip other) {
    return (this.block.eqv(other.block));
  } // eqv(HFlip)
} // class HFlip
