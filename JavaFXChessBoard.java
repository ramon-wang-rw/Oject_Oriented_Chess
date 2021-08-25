import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.lang.IllegalArgumentException;
import java.util.List;

public class JavaFXChessBoard extends Application implements ChessBoard{
  private Button[][] squares;                          // the squares of the board
  private ChessPiece[][] pieces;                       // stores the pieces
  private ChessGame gameRules;                         // global rules for this particular game
  private JavaFXChessBoardDisplay boardDisplay;        // rules for how to draw the chess board
  
  /**
   * creates the JavaFX chessboard for whichever game is specified
   * @param primaryStage the main window
   */
  public void start(Stage primaryStage) {
    List<String> gameType = getParameters().getRaw();
    
    //Will set gamerules to european chess if the argument is chess, xiangqi if argument is xiangqi
    //and throws an exception if the command line argument is neither
    if (gameType.get(0).equals("chess")) {
      gameRules = new EuropeanChess();
      boardDisplay = new JavaFXEuropeanChessDisplay();
    }
    
    else if(gameType.get(0).equals("xiangqi")) {
      gameRules = new Xiangqi();
      boardDisplay = new JavaFXXiangqiDisplay();
    }
    
    else
      throw new IllegalArgumentException();
    
    //creates the arrays for pieces and squares based on the number of rows and columns of the game type
    pieces = new ChessPiece[gameRules.getNumColumns()][gameRules.getNumRows()];
    squares = new Button[gameRules.getNumColumns()][gameRules.getNumRows()];


    GridPane layout = new GridPane();
    MovePiece eventHandler = new MovePiece();
    
    //Goes through each row and column and creates the chess board based on the game rules
    for(int column = 0; column < gameRules.getNumColumns(); column++) {
      for(int row = 0; row < gameRules.getNumRows(); row++) {
        pieces[column][row] = null;
        squares[column][row] = new Button();
        squares[column][row].setOnAction(eventHandler);
        squares[column][row].setMinWidth(75);
        squares[column][row].setMinHeight(75);
        layout.add(squares[column][row], column,row);
        boardDisplay.displayEmptySquare(squares[column][row], row, column);
      }
    }
    
    gameRules.startGame(this);

    
    Scene scene = new Scene(layout);
    
    primaryStage.setScene(scene);
    primaryStage.show();

    
    
  }
  
  /**
   * An event handler that lets the player select and move a piece
   */
  private class MovePiece implements EventHandler<ActionEvent> {
    /*Stores wheter or not this is a piece selection*/
    private Boolean firstPick = true;
    private int pieceRow;              // remember row of selected piece
    private int pieceCol;              // remember column of selected piece
    
    
    /**
     * on a button click we either select a piece to move or move the piece
     */
    public void handle(ActionEvent e) {
      Button button = (Button)e.getSource();
      int row = -1;
      int column = -1;
      
      //figure out which row and column the button is in
      for(int c = 0; c < gameRules.getNumColumns(); c++) {
        for(int r = 0; r < gameRules.getNumRows(); r++) {
          if(squares[c][r] == button){
            row = r;
            column = c;
          }
        }
      }
      
      //if it is the first selection it will highlight the square if there is a piece and it is legal to play
      if(firstPick) {
        if(pieces[column][row] != null && getGameRules().legalPieceToPlay(pieces[column][row], row, column)){
          boardDisplay.highlightSquare(true, button, row, column, pieces[column][row]);
          firstPick = false;
          pieceRow = row;
          pieceCol = column;
        }
      }
      
      //case for second selection
      else {
        if(row == pieceRow && column == pieceCol) 
          return;
        
        //attempts to make a move and stores the result in the variable
        System.out.println(pieces[pieceCol][pieceRow].isLegalNonCaptureMove(row,column));
        boolean moveMade = getGameRules().makeMove(pieces[pieceCol][pieceRow], row, column);

        
        //if the move was made or we can change selection the square gets unhighlighted and we select another piece
        if (moveMade || getGameRules().canChangeSelection(pieces[pieceRow][pieceCol], pieceRow, pieceCol)) {
          boardDisplay.highlightSquare(false, squares[pieceCol][pieceRow], pieceRow, pieceCol, pieces[pieceCol][pieceRow]);
          firstPick = true;
        }
        
      }
        
    }
    
  }
      
  
  
  /**
   * Start the chess game
   * @param args the command line arguments that determine the type of game
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
  
  
  
  /**
   * The method retrieves the ChessGame being played on the board
   * @return ChessGame gameRules field
   */
  public ChessGame getGameRules() {
    return this.gameRules;
  }
  
  /**
   * Adds a chess piece to row and column
   * @param piece is the piece being added
   * @param row is the row the piece is being placed on
   * @param column is the column the piece is being placed on
   */
  public void addPiece(ChessPiece piece, int row, int column){
    pieces[column][row] = piece;
    piece.setLocation(row,column);
    boardDisplay.displayFilledSquare(squares[column][row], row, column, piece);
  }
  
  /**
   * Removes the piece at given row and column and returns the piece
   * @param row the row of the piece
   * @param column the column of the piece
   * @return the piece that was removed
   */
  public ChessPiece removePiece(int row, int column){
    ChessPiece save = pieces[column][row];
    pieces[column][row] = null;
    
    boardDisplay.displayEmptySquare(squares[column][row],row,column);
    
    return save;
  }
  
  /**
   * returns true if there is a piece at given row and column
   * @param row the row that is being checked
   * @param column the column that is being checked
   * @return true if there is a piece otherwise false
   */
  public boolean hasPiece(int row, int column) {
    if (pieces[column][row] != null) 
      return true;
    else
      return false;

  }
  
  /**
   * returns the piece at the given row and column
   * @param row the row the piece is on
   * @param column the column the piece is on
   * @return the piece that is being retrieved
   */
  public ChessPiece getPiece(int row, int column) {
    return pieces[column][row];
  }
  
  /**
   * Returns true if a particular square is threatened by an opposing piece.
   * @param row the row that is being checked
   * @param column the column that is being checked
   * @param piece is a piece in the game
   * @return true if the square is threatened otherwise false
   */
  public boolean squareThreatened(int row, int column, ChessPiece piece) {
    for(int c = 0; c < gameRules.getNumColumns(); c++) {
      for(int r = 0; r < gameRules.getNumRows(); r++) {
        if (hasPiece(r,c) && getPiece(r, c).getSide() != piece.getSide() &&
            getPiece(r, c).isLegalCaptureMove(row, column))
          return true;
      }
    }
    return false;
  }
      
    
}