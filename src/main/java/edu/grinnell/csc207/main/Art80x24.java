package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.NewBlock;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.VFlip;
import edu.grinnell.csc207.blocks.Line;

import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Natalie Nardone
 * @author Jenifer Silva
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    Rect rec1 = new Rect(' ', 7, 4);
    NewBlock newRec1 = new NewBlock(rec1, '-');
    Rect rec4 = new Rect('.', 7, 4);
    NewBlock newRec4 = new NewBlock(rec4, '-');
    Rect rec2 = new Rect('o', 7, 4);
    NewBlock newRec2 = new NewBlock(rec2, '-');
    Rect rec3 = new Rect('O', 7, 4);
    NewBlock newRec3 = new NewBlock(rec3, '-');

    HComp comp1 = new HComp(VAlignment.CENTER,
        new AsciiBlock[] {newRec1, newRec4, newRec2, newRec3});
    HFlip rightSide = new HFlip(comp1);
    HComp comp2 = new HComp(VAlignment.CENTER, new AsciiBlock[] {comp1, rightSide});

    Line line1 = new Line("<<<<****88888888****>>>>");
    Line line2 = new Line("<<<<****8888888888888888****>>>>");
    Line line3 = new Line("<<<<****888888888888888888888888****>>>>");
    Line line4 = new Line("<<<<****88888888888888888888888888888888****>>>>");
    Line line5 = new Line("<<<<****8888888888888888888888888888888888888888****>>>>");

    VComp topHalf = new VComp(HAlignment.CENTER,
        new AsciiBlock[] {comp2, line1, line2, line3, line4, line5});

    VFlip bottomPart = new VFlip(topHalf);
    VComp comp4 = new VComp(HAlignment.CENTER, new AsciiBlock[] {topHalf, bottomPart});

    AsciiBlock.print(pen, comp4);
    pen.close();
  } // main(String[])
} // class Art80x24
