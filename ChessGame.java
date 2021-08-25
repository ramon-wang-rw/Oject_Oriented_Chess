/**
 * An interface that encodes specific rules for a version of chess.
 * 
 * @author Harold Connamacher
 */
public interface ChessGame {
  /** The "side" or "team" or "player" the piece belongs to.  
    * The "players" are named by the compass positions around the board
    */
  public enum Side {NORTH, SOUTH, EAST, WEST};
  
  /** Determines if it is legal to play a given piece.
    * @param piece   the piece to be played
    * @param row     the row of the square the piece is on
    * @param column  the column of the square the piece is on
    * @return true if the piece is allowed to move on this turn
    */
  public boolean legalPieceToPlay(ChessPiece piece, int row, int column);
  
  /** Moves a piece to a new position.
    * @param piece     the piece to move
    * @param toRow     the row of the square the piece is moving to
    * @param toColumn  the column of the square the piece is moving to
    * @return true if the move was made, false if the move was not made
    */
  public boolean makeMove(ChessPiece piece, int toRow, int toColumn);
  
  /**
   * Returns whether a user can choose a different piece from the one selected or if they have to move the selected piece.
   * If this method returns false, then the <tt>legalPieceToPlay</tt> method must not return true if that piece has no
   * legal moves.  Otherwise the game could freeze with a player not permitted to change selection of a piece with no legal moves.
   * @param piece   the piece the user selected
   * @param row     the row of the square the piece is on
   * @param column  the column of the square the piece is on
   * @return true if the player can change the piece they selected and false if they cannot and must move that piece
   */
  public default boolean canChangeSelection(ChessPiece piece, int row, int column) {
    return true;
  }
  
   /**
   * Returns the number of rows in the chess game
   * @return int that represents the number of rows
   */
  public int getNumRows();
  
  /**
   * Returns the number of columns in the chess game
   * @return int that represents the number of columns
   */
  public int getNumColumns();
  
  /**
   * Takes the given board and places all the pieces for the board in the correct starting position
   * @param board is the type of chess board we are using
   */
  public void startGame(ChessBoard board);
  

  
  
  
}