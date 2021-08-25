public class Pawn extends ChessPiece implements IsDiagonal, IsStraight, IsOneSpace, IsForward{
  
  //Constructor that sets the side, label, row, column, and chessboard the piece is on
  public Pawn(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }

  //Overrides the isLegalNonCaptureMove, return true if pawn is moving forward one space and if it hasn't moved before
  //it is able to move two spaces
  public boolean isLegalNonCaptureMove(int row, int column) {
    if(this.isStraight(row, column) && this.isForward(row, column) && this.isOneSpace(row,column)) {
      
      if(this.getChessBoard().hasPiece(row, column))
        return false;
      else
        return true;
    }
    
    else if(this.isStraight(row, column) && this.isForward(row, column) && this.getMovesMade() == 0
            && Math.abs(this.getRow() - row) == 2) {
      if(this.getChessBoard().hasPiece(row, column))
        return false;
      else
        return true;
    }
    
    return false;
  }
  
  //Overrides the isLegalCaptureMove, return true if pawn is moving forward diagonally one space onto a opposing piece
  public boolean isLegalCaptureMove(int row, int column) {
    if(this.isDiagonal(row, column) && this.isForward(row, column) && this.isOneSpace(row,column)
       && this.getChessBoard().hasPiece(row,column)
       && (this.getChessBoard().getPiece(row,column).getSide() != this.getSide()))
      return true;
    else
      return false;
  }
  
}