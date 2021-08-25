import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.geometry.Insets;


public class JavaFXEuropeanChessDisplay implements JavaFXChessBoardDisplay {
  /** The primary color of the checkerboard */
  public static Color red = Color.RED;
  
  /** The secondary color of the checkerboard */
  public static Color black = Color.BLACK;
  
  /* The color of the SOUTH player */
  public static Color southPlayerColor = Color.YELLOW;
  
  /* The color of the NORTH player */
  public static Color northPlayerColor = Color.GREEN;
  
  /* The color of the EAST player */
  public static Color eastPlayerColor = Color.WHITE;
  
  /* The color of the WEST player */
  public static Color westPlayerColor = Color.GRAY;
  
  /** The color used to highlight a square */
  public static Color highlightColor = Color.BLUE;
  
  /* displays empty square at given row and column
   * @param button the square that is empty
   * @param row the row of the square
   * @param column the column of the square
   */
  public void displayEmptySquare(Button button, int row, int column) {

    BackgroundFill f1 = new BackgroundFill(red, CornerRadii.EMPTY, Insets.EMPTY);
    BackgroundFill f2 = new BackgroundFill(black, CornerRadii.EMPTY, Insets.EMPTY);
    if ((row+column) % 2 == 1) {
      Background background = new Background(f1);
      button.setBackground(background);
      button.setText("");
    }
    else {
      Background background = new Background(f2);
      button.setBackground(background);
      button.setText("");
    }

    
  }
  
  /* displays a filled square at given row and column
   * @param button the square that is filled
   * @param row the row of the square
   * @param column the column of the square
   */
  public void displayFilledSquare(Button button, int row, int column, ChessPiece piece){
    Color pieceColor;
    
    switch (piece.getSide()) {
      case NORTH:   pieceColor = northPlayerColor;
                    break;
      case SOUTH:   pieceColor = southPlayerColor;
                    break;
      case EAST:    pieceColor = eastPlayerColor;
                    break;
      default:      pieceColor = westPlayerColor;
    }
    Insets space = new Insets(1);
    
    BackgroundFill f1 = new BackgroundFill(pieceColor, CornerRadii.EMPTY, space);
    Background background = new Background(f1);
    Font font = new Font(25);
    
    button.setBackground(background);
    button.setText(piece.getLabel());
    button.setFont(font);
    
  }
  
  /* adds or removes a highlight from a square
   * @param highlight is wheter or not the square should be highlighted
   * @param button the square that is being looked at
   * @param row the row the square is at
   * @param column the column the square is at
   * @param piece the piece that is on the square.
   */
  public void highlightSquare(boolean highlight, Button button, int row, int column, ChessPiece piece){
    if(highlight) {
      BackgroundFill f1 = new BackgroundFill(highlightColor, CornerRadii.EMPTY, Insets.EMPTY);
      Background background = new Background(f1);
      button.setBackground(background);
    }
    
    else if(piece == null)
      displayEmptySquare(button, row, column);
    else
      displayFilledSquare(button, row, column, piece); 
  }
  
}