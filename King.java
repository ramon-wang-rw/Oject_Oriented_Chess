
public class King extends ChessPiece implements IsStraight, IsDiagonal, IsOneSpace {
  //Constructor that sets the side, label, row, column, and chessboard the piece is on
  public King(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  //determines if the move is a castle move
  public boolean isCastling(int row, int column) {
    //if king didnt moved and if the intended move is 2 to the left or right proceed
    if(this.isStraight(row,column) && 
       this.getRow() - row == 0 && Math.abs(this.getColumn() - column) == 2 && this.getMovesMade() == 0) {
      
      //The case when king moves two to the right, returns true if there is a rook at the end.
      if(this.getChessBoard().hasPiece(row,column + 2) 
         && this.getChessBoard().getPiece(row,column + 2).getMovesMade() == 0
         && this.getChessBoard().getPiece(row,column + 2).getSide() == this.getSide()
         && this.getChessBoard().getPiece(row,column + 2) instanceof Rook)
        return true;
      //The case when king moves two to the left, returns true if there is a rook at the end
      else if(this.getChessBoard().hasPiece(row,column - 1) 
         && this.getChessBoard().getPiece(row,column - 1).getMovesMade() == 0
         && this.getChessBoard().getPiece(row,column - 1).getSide() == this.getSide()
         && this.getChessBoard().getPiece(row,column - 1) instanceof Rook)
        return true;
      
      else
        return false;
    }
    
    return false;
  }
      
  
  //Overrides the isLegalNonCaptureMove, returns true when the move is one space in any direction
  //and is not capturing a piece. Also returns true when it is doing a castling move.
  public boolean isLegalNonCaptureMove(int row, int column) {
    if((this.isDiagonal(row,column) || this.isStraight(row,column)) && this.isOneSpace(row, column)) {
      if(this.getChessBoard().hasPiece(row,column))
        return false;
      else
        return true;
    }
    
    else if(this.isCastling(row, column))
      return true;
    
    else
      return false;
  }
  
  //Overrides the isLegalCaptureMove, returns true when the move is one space in any direction
  //and is capturing a piece
  public boolean isLegalCaptureMove(int row, int column) {
    if((this.isDiagonal(row,column) || this.isStraight(row,column)) && this.isOneSpace(row,column)
        && this.getChessBoard().hasPiece(row,column) 
        && (this.getChessBoard().getPiece(row,column).getSide() != this.getSide()))
      return true;
    else 
      return false;
  }
  
}