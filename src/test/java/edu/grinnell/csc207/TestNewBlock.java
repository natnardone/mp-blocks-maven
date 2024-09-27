package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.NewBlock;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HComp;

/**
 * Tests of the new block.
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Test NewBlock on an Empty block.
   */
  @Test
  public void testEmpty() {
    AsciiBlock empty = new Empty();
    AsciiBlock quarterEmpty = new NewBlock(empty, 'A');

    assertEquals(3, quarterEmpty.width(),
        "Quartered Empty block has width of 3");
    assertEquals(3, quarterEmpty.height(),
        "Quartered Empty block has height of 3");
    assertEquals("""
                 AAA
                 AAA
                 AAA
                 """,
     TestUtils.toString(quarterEmpty),
        "Quartered Empty block has correct output");
  } // testEmpty()

  /**
   * Test NewBlock on single row.
   */
  @Test
  public void testRow() throws Exception {
    Line line1 = new Line("Hello");
    AsciiBlock quarter1= new NewBlock(line1, '#');

    assertEquals(8, quarter1.width(),
        "Quartered single line block has width of 8");
    assertEquals(4, quarter1.height(),
        "Quartered single line block has height of 4");
    assertEquals("""
                 ########
                 ########
                 #He#llo#
                 ########
                 """,
     TestUtils.toString(quarter1),
        "Quartered single line block has correct output");

  } // testRow()

/**
   * Test NewBlock on single character.
   */
  @Test
  public void testSingle() throws Exception {
    Line line2 = new Line("I");
    AsciiBlock quarter2= new NewBlock(line2, '#');

    assertEquals(4, quarter2.width(),
        "Quartered single char block has width of 4");
    assertEquals(4, quarter2.height(),
        "Quartered single char block has height of 4");
    assertEquals("""
                 ####
                 ####
                 ##I#
                 ####
                 """,
     TestUtils.toString(quarter2),
        "Quartered single char block has correct output");

  } // testLines()

  /**
   * Test NewBlock on single column.
   */
  @Test
  public void testColumn() throws Exception {
    Rect line3 = new Rect('7', 1, 7);
    AsciiBlock quarter3= new NewBlock(line3, '#');
    
    assertEquals(4, quarter3.width(),
        "Quartered single column block has width of 4");
    assertEquals(10, quarter3.height(),
        "Quartered single column block has height of 10");
    assertEquals("""
                 ####
                 ##7#
                 ##7#
                 ##7#
                 ####
                 ##7#
                 ##7#
                 ##7#
                 ##7#
                 ####
                 """,
     TestUtils.toString(quarter3),
        "Quartered single column block has correct output");
  } // testColumn()

  /**
   * Test NewBlock with Grid.
   */
  @Test
  public void testGrid(){
    Line line4 = new Line("Hello");
    Grid gridQuarter = new Grid(line4, 4, 4);
    AsciiBlock quarter4 = new NewBlock(gridQuarter, '0');
    
    assertEquals(23, quarter4.width(),
        "Quartered grid has width of 23");
    assertEquals(7, quarter4.height(),
        "Quartered grid has height of 7");
    assertEquals("""
                 00000000000000000000000
                 0HelloHello0HelloHello0
                 0HelloHello0HelloHello0
                 00000000000000000000000
                 0HelloHello0HelloHello0
                 0HelloHello0HelloHello0
                 00000000000000000000000
                 """,
     TestUtils.toString(quarter4),
        "Quartered grid has correct output");
  } // testGrid()

  /**
   * Test NewBlock with many combinations.
   */
  @Test
  public void testMany(){
    Line line5 = new Line("AAAAAAAAA");
    Line line6 = new Line("BBBBB");
    Line line7 = new Line("CC");
    HComp comp = new HComp(VAlignment.CENTER, new AsciiBlock[] {line5, line6, line7});
    Surrounded surroundedComp = new Surrounded(comp, '$');
    AsciiBlock quarter5 = new NewBlock(surroundedComp, '0');
    Boxed boxedQuarter = new Boxed(quarter5);
    
    assertEquals(23, boxedQuarter.width(),
        "Boxed quarter has height of 23");
    assertEquals(8, boxedQuarter.height(),
        "Boxed quarter has width of 8");
    assertEquals("""
                 /---------------------\\
                 |000000000000000000000|
                 |0$$$$$$$$$0$$$$$$$$$0|
                 |000000000000000000000|
                 |0$AAAAAAAA0ABBBBBCC$0|
                 |0$$$$$$$$$0$$$$$$$$$0|
                 |000000000000000000000|
                 \\---------------------/
                 """,
     TestUtils.toString(boxedQuarter),
        "Boxed quarter has correct output");
  } // testGrid()


} // class TestNewBlock
