public interface ChessBoard {
  
  /**
   * The method retrieves the ChessGame being played on the board
   * @return ChessGame gameRules field
   */
  public ChessGame getGameRules();
  
  /**
   * Adds a chess piece to row and column
   * @param piece is the piece being added
   * @param row is the row the piece is being placed on
   * @param column is the column the piece is being placed on
   */
  public void addPiece(ChessPiece piece, int row, int column);
  
  /**
   * Removes the piece at given row and column and returns the piece
   * @param row the row of the piece
   * @param column the column of the piece
   * @return the piece that was removed
   */
  public ChessPiece removePiece(int row, int column);
  
  /**
   * returns true if there is a piece at given row and column
   * @param row the row that is being checked
   * @param column the column that is being checked
   * @return true if there is a piece otherwise false
   */
  public boolean hasPiece(int row, int column);
  
  /**
   * returns the piece at the given row and column
   * @param row the row the piece is on
   * @param column the column the piece is on
   * @return the piece that is being retrieved
   */
  public ChessPiece getPiece(int row, int column);
  
  /**
   * Returns true if a particular square is threatened by an opposing piece.
   * @param row the row that is being checked
   * @param column the column that is being checked
   * @param piece is a piece in the game
   * @return true if the square is threatened otherwise false
   */
  public boolean squareThreatened(int row, int column, ChessPiece piece);
  
}