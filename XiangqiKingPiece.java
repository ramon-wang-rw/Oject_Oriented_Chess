public class XiangqiKingPiece extends ChessPiece implements IsStraight, IsOneSpace, RowAndColumnRestriction {
  /* Constructor that sets the side, label, row, column, and chessboard the piece is on
   * @param side the side the piece is on
   * @param label what piece it is
   * @param row the row the piece is on
   * @param columnn the column the piece is on
   * @param chessBoard the board the piece is on
   */
  public XiangqiKingPiece(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  /* returns true when the legal move is straight and only moves one space
   * can't move out of the 3 center columns and 3 rows top/bottom.
   * @param row the row the piece is moving to
   * @param column the column the piece is moving to
   * @return true if legal otherwise false
   */
  public boolean isLegalNonCaptureMove(int row, int column) {
    
    if(this.isStraight(row,column) && this.isOneSpace(row, column) && this.isValidRowAndColumn(row, column, this)) {
      if(this.getChessBoard().hasPiece(row,column)) 
        return false;
      else
        return true;
    }
    
    return false;
    
  }
  
  /*returns true when the legal move is straight and only moves one space onto an enemy piece
   * can't move out of the 3 center columns and 3 rows top/bottom
   * @param row the row the piece is moving to
   * @param column the column the piece is moving to
   * @return true if legal otherwise false
   */
  public boolean isLegalCaptureMove(int row, int column) {
    if(this.isStraight(row,column) && this.isOneSpace(row, column) && this.isValidRowAndColumn(row, column, this)
        && this.getChessBoard().hasPiece(row,column) 
        && (this.getChessBoard().getPiece(row,column).getSide() != this.getSide())) 
      return true;
    else
      return false;
  }
  
}