import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface JavaFXChessBoardDisplay {
  /* displays empty square at given row and column
   * @param button the square that is empty
   * @param row the row of the square
   * @param column the column of the square
   */
  public void displayEmptySquare(Button button, int row, int column);
  
  /* displays a filled square at given row and column
   * @param button the square that is filled
   * @param row the row of the square
   * @param column the column of the square
   */
  public void displayFilledSquare(Button button, int row, int column, ChessPiece piece);
  
  /* adds or removes a highlight from a square
   * @param highlight is wheter or not the square should be highlighted
   * @param button the square that is being looked at
   * @param row the row the square is at
   * @param column the column the square is at
   * @param piece the piece that is on the square.
   */
  public void highlightSquare(boolean highlight, Button button, int row, int column, ChessPiece piece);
  
}