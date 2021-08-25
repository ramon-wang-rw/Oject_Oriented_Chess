public class ElephantPiece extends ChessPiece implements IsDiagonal, RowAndColumnRestriction, IsTwoSpace {
  /** Constructor that sets the side, label, row, column, and chessboard the piece is on
   * @param side the side the piece is on
   * @param label what piece it is
   * @param row the row the piece is on
   * @param columnn the column the piece is on
   * @param chessBoard the board the piece is on
   */
  public ElephantPiece(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  /** returns true if moving exactly two diagonally and not crossing the center onto an empty square
    * @row the row the piece is moving to
    * @column the column the piece is moving to
    * @return true if legal non capture move
    */
  public boolean isLegalNonCaptureMove(int row, int column) {
    if(this.isDiagonal(row,column) && this.isTwoSpace(row, column) && this.isValidRowAndColumn(row, column, this)) {
      if(this.getChessBoard().hasPiece(row,column))
        return false;
      else
        return true;
    }
    else
      return false;
  }
  
  /** returns true if moving exactly two diagonally and not crossing the center onto an enemy piece
    * @row the row the piece is moving to
    * @column the column the piece is moving to
    * @return true if legal capture move
    */
  public boolean isLegalCaptureMove(int row, int column) {
    if(this.isDiagonal(row,column) && this.isTwoSpace(row, column) && this.isValidRowAndColumn(row, column, this)
         && this.getChessBoard().hasPiece(row,column) 
         && (this.getChessBoard().getPiece(row,column).getSide() != this.getSide()))
      return true;
    else 
      return false;
  }
}