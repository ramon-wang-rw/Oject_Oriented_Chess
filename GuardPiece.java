public class GuardPiece extends ChessPiece implements IsDiagonal, IsOneSpace, RowAndColumnRestriction {
   /** Constructor that sets the side, label, row, column, and chessboard the piece is on
   * @param side the side the piece is on
   * @param label what piece it is
   * @param row the row the piece is on
   * @param columnn the column the piece is on
   * @param chessBoard the board the piece is on
   */
  public GuardPiece(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  /** returns true if it is moving one square diagonally to an empty square
    * can't move out of center or first three rows
    * @param row is the row the piece is moving to
    * @param column is the column the piece is moving to
    * @return true if the move is legal non capture
    */
  public boolean isLegalNonCaptureMove(int row, int column) {
    if(this.isDiagonal(row,column) && this.isOneSpace(row,column) && this.isValidRowAndColumn(row,column, this)) {
      if(this.getChessBoard().hasPiece(row,column))
        return false;
      else
        return true;
    }
    else
      return false;
  }
  
  /**returns true if it is moving one square diagonally to an enemy piece
    * can't move out of center or first three rows
    * @param row is the row the piece is moving to
    * @param column is the column the piece is moving to
    * @return true if the move is legal capture
    */
  public boolean isLegalCaptureMove(int row, int column) {
    if(this.isDiagonal(row,column) && this.isOneSpace(row,column) && this.isValidRowAndColumn(row,column,this)
         && this.getChessBoard().hasPiece(row,column) &&
       (this.getChessBoard().getPiece(row,column).getSide() != this.getSide()))
      return true;
    else 
      return false;
  }
  
}