public class CannonPiece extends ChessPiece implements IsStraight, IsStraightOnePiece {
  /** Constructor that sets the side, label, row, column, and chessboard the piece is on
   * @param side the side the piece is on
   * @param label what piece it is
   * @param row the row the piece is on
   * @param columnn the column the piece is on
   * @param chessBoard the board the piece is on
   */
  public CannonPiece(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  /** returns true if piece is moving straight without any piece blocking it onto an empty square
    * @param row the row the piece is moving to
    * @param column the column the piece is moving to
    * @return true if the move is a non Capture move
    */
  public boolean isLegalNonCaptureMove(int row, int column) {
    if(this.isStraight(row,column)) {
      if(this.getChessBoard().hasPiece(row,column))
        return false;
      else
        return true;
    }
    else
      return false;
  }
  
  /** returns true if piece is moving straight with only one piece in between onto an enemy piece
    * @param row the row the piece is moving to
    * @param column the column the piece is moving to
    * @return true if the move is a legal capture move
    */
  public boolean isLegalCaptureMove(int row, int column) {
    if(this.isStraightOnePiece(row,column) && this.getChessBoard().hasPiece(row,column) &&
       (this.getChessBoard().getPiece(row,column).getSide() != this.getSide()))
      return true;
    else 
      return false;
  }
  
}