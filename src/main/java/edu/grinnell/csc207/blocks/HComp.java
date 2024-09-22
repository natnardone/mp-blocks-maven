package edu.grinnell.csc207.blocks;

import java.util.Arrays;
import java.lang.String;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
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
            str.concat(temp.row(i));
          } else { 
            str.concat(" ".repeat(temp.width()));
          }
        } else if (this.align == VAlignment.BOTTOM) {
          if (i >= (this.height() - temp.height())) {
            str.concat(temp.row(i));
          } else { 
            str.concat(" ".repeat(temp.width()));
          }
        } else if (this.align == VAlignment.CENTER) {
          int topbound = (this.height() / temp.height()) - 1; // inclusive
          int botbound = topbound + temp.height() - 1; // inclusive
          if (i <= topbound && i >= botbound) {
            str.concat(temp.row(i));
          } else { 
            str.concat(" ".repeat(temp.width()));
          }
        }
      }
      return str;
    }
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *empty
   * @return the number of rows
   */
  public int height() {
    int max = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (blocks[i].height() > max) {
        max = blocks[i].height();
      }
    }
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
    }
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
    return false;       // STUB
  } // eqv(AsciiBlock)
} // class HComp
