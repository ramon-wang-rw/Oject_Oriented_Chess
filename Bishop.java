public class Bishop extends ChessPiece implements IsDiagonal {
  
  //Constructor that sets the side, label, row, column, and chessboard the piece is on
  public Bishop(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  //Overrides the isLegalNonCaptureMove, returns true when the move is diagonal and is not capturing a piece
  public boolean isLegalNonCaptureMove(int row, int column) {
    if(this.isDiagonal(row,column)) {
      if(this.getChessBoard().hasPiece(row,column))
        return false;
      else
        return true;
    }
    else
      return false;
  }
  
  //Overrides the isLegalCaptureMove, returns true when the move is diagonal and is capturing a piece
  public boolean isLegalCaptureMove(int row, int column) {
    if(this.isDiagonal(row,column) && this.getChessBoard().hasPiece(row,column) &&
       (this.getChessBoard().getPiece(row,column).getSide() != this.getSide()))
      return true;
    else 
      return false;
  }
  
}