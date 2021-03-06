package JigsawPuzzlePackage;


import guiPackage.GotPuzzledGUI;

import javax.swing.*;

import java.awt.image.*;
import java.awt.event.*;
import java.io.File;

/**
 * JFrame that runs a JigsawPuzzle.  This is the front end for
 * JigsawPuzzle, the main class of the jigsaw application.
 */
public class JigsawFrame extends JFrame
{

  /** Creates and displays a simple JFrame containing a jigsaw puzzle in a
   * JScrollPane.  The frame may be resized freely.  

   *
   * Pieces can be dragged around with the mouse.  The piece (or group
   * of pieces) most recently dragged or clicked on is the active piece.
   * Press R to rotate the active piece (or group) 90 degrees clockwise.
   * Press E to rotate it 90 degrees counter-clockwise. 
   * Press S to shuffle all the pieces around the panel randomly,
   * keeping fitted pieces together.  Pieces are fitted automatically if
   * they are placed close enough, and are rotated the same way.
   */

  public static final char HELP = 'H';

  public JigsawFrame (BufferedImage image, JigsawCutter cutter, boolean rotation,JFrame frmGotPuzzled)
  {
    super ("Jigsaw Puzzle");
    puzzle = new JigsawPuzzle (image, cutter,rotation,frmGotPuzzled,this);
    setContentPane (new JScrollPane (puzzle));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();

    puzzle.addKeyListener (new KeyAdapter() {
    public void keyTyped (KeyEvent e) { keyTyped0 (e); } });
    this.dispose();
    
  }

  static JigsawPuzzle puzzle;
  /** Tells this frame to begin the puzzle.  The image is cut if it hasn't
   * already, and the pieces are placed on the board.
   */
  public void begin()
  {
    puzzle.reset();

  }
  

  // Adds a crude help dialog to the puzzle.
  private void keyTyped0 (KeyEvent e)
  {
    char ch = Character.toUpperCase (e.getKeyChar());
         if (ch==HELP)  showHelp();
    else return;
  }

  private void showHelp()
  {
    JOptionPane.showMessageDialog (this, "<html>"+
      "Drag pieces with the mouse to fit them together.  If they do,"
      +" they'll join and move as a unit from then on."
      +"<p> Keyboard commands: <br>"
      +"<table>"
      +"<tr><td>" + JigsawPuzzle.ROTATE_LEFT
        + " <td> rotate piece left 90 degrees"
      +"<tr><td>" + JigsawPuzzle.ROTATE_RIGHT
        + " <td> rotate piece right 90 degrees"
      +"<tr><td>" + JigsawPuzzle.SHUFFLE
        + " <td> shuffle all pieces (good for finding pieces accidentally"
        +    " moved off the board)"
      +"<tr><td>" + JigsawPuzzle.PREV_BG
        + " <td> change background to previous color"
      +"<tr><td>" + JigsawPuzzle.NEXT_BG
        + " <td> change background to next color"
      +"<tr><td>" + JigsawPuzzle.CLEAR
        + " <td> toggle clear mode; mouse now drags over spaces to be"
        +    " cleared of pieces; cleared pieces are placed randomly"
        +    " elsewhere"
      +"<tr><td>" + HELP
        + " <td> this help screen"
      +"</table>"
      );
  }

public static JigsawPuzzle getPuzzle() {
	return puzzle;
}

  
  
}

