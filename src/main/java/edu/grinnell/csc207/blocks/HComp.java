package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Natalie Nardone
 * @author Jenifer Silva
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if ((i < 0) || (i >= this.height())) {
      // Outside of normal bounds
      throw new Exception("Invalid row " + i);
    } else {
      String str = new String("");

      for (int x = 0; x < blocks.length; x++) {
        AsciiBlock temp = blocks[x];
        if (this.align == VAlignment.TOP) {
          if (i < temp.height()) {
            str = str.concat(temp.row(i));
          } else {
            str = str.concat(" ".repeat(temp.width()));
          } // if/else
        } else if (this.align == VAlignment.BOTTOM) {
          if (i >= (this.height() - temp.height())) {
            str = str.concat(temp.row(i - (this.height() - temp.height())));
          } else {
            str = str.concat(" ".repeat(temp.width()));
          } // if/else
        } else if (this.align == VAlignment.CENTER) {
          int topbound = (this.height() - temp.height()) / 2; // inclusive
          int botbound = topbound + temp.height() - 1; // inclusive
          if (i >= topbound && i <= botbound) {
            str = str.concat(temp.row(i - topbound));
          } else {
            str = str.concat(" ".repeat(temp.width()));
          } // if/else
        } // if/else
      } // for
      return str;
    } // if/else
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int max = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (blocks[i].height() > max) {
        max = blocks[i].height();
      } // if
    } // for
    return max;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int width = 0;
    for (int i = 0; i < blocks.length; i++) {
      width += blocks[i].width();
    } // for
    return width;
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
    return ((other instanceof HComp) && (this.eqv((HComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another HComp is structurally equivalent to this HComp.
   *
   * @param other
   *   The HComp to compare to this HComp.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(HComp other) {
    return (this.align.equals(other.align)) && (Arrays.equals(this.blocks, other.blocks));
  } // eqv(HComp)
} // class HComp
