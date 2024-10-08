package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Natalie Nardone
 * @author Jenifer Silva
 */
public class VComp implements AsciiBlock {
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
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
      int rowCount = 0;
      for (int k = 0; k < blocks.length; k++) {
        for (int j = 0; j < blocks[k].height(); j++) {
          if (rowCount == i) {
            str = blocks[k].row(j);
            String spaces = new String(" ".repeat(this.width() - str.length()));
            if (this.align == HAlignment.LEFT) {
              str = str.concat(spaces);
            } else if (this.align == HAlignment.RIGHT) {
              str = spaces.concat(str);
            } else if (this.align == HAlignment.CENTER) {
              String leftspace = new String(spaces.substring(0, spaces.length() / 2));
              String rightspace = new String(spaces.substring(spaces.length() / 2));
              str = leftspace.concat(str).concat(rightspace);
            } // if/else
            return str;
          } else {
            rowCount++;
          } // if/else
        } // for
      } // for
      return "";
    } // if/else
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int height = 0;
    for (int i = 0; i < blocks.length; i++) {
      height += blocks[i].height();
    } // for
    return height;
  } // height()
  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int max = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (blocks[i].width() > max) {
        max = blocks[i].width();
      } // if
    } // for
    return max;
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
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
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
  public boolean eqv(VComp other) {
    if (!this.align.equals(other.align)) {
      return false;
    } // if
    for (int i = 0; i < this.blocks.length; i++) {
      if (!this.blocks[i].eqv(other.blocks[i])) {
        return false;
      } // if
    } // for
    return true;
  } // eqv(VComp)
} // class VComp
