public class Knight extends ChessPiece {
  //Constructor that sets the side, label, row, column, and chessboard the piece is on
  public Knight(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  //Overrides the isLegalNonCaptureMove, returns true when the move is L-Shaped and is not capturing a piece
  public boolean isLegalNonCaptureMove(int row, int column) {
    
    //determines if movement is the first variation of the L-Shaped move
    boolean lShape1 = Math.abs(this.getRow() - row) == 2 && Math.abs(this.getColumn() - column) == 1;
    //determines if movement is the second variation of the L-Shaped move
    boolean lShape2 = Math.abs(this.getRow() - row) == 1 && Math.abs(this.getColumn() - column) == 2;
    
    if(lShape1 || lShape2) {
      if(this.getChessBoard().hasPiece(row,column))
        return false;
      else
        return true;
    }
    else
      return false;
  }
  
  //Overrides the isLegalCaptureMove, returns true when the move is L-Shaped and is capturing a piece
  public boolean isLegalCaptureMove(int row, int column) {
    
    //determines if movement is the first variation of the L-Shaped move
    boolean lShape1 = Math.abs(this.getRow() - row) == 2 && Math.abs(this.getColumn() - column) == 1;
    //determines if movement is the second variation of the L-Shaped move
    boolean lShape2 = Math.abs(this.getRow() - row) == 1 && Math.abs(this.getColumn() - column) == 2;
    
    if((lShape1 || lShape2) && this.getChessBoard().hasPiece(row,column) &&
       this.getChessBoard().getPiece(row,column).getSide() != this.getSide()){
      return true;
    }
    else 
      return false;
  }
  
}