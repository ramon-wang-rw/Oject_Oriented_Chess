//Inherited by the xiangqiKing piece and the Guard Piece
public interface RowAndColumnRestriction {
  public default boolean isValidRowAndColumn(int row, int column, ChessPiece piece) {
    if(piece instanceof ElephantPiece) {
      //variable to check if move is within the row restriction (north piece)
      boolean rowRestriction;
      if(piece.getSide() == ChessGame.Side.NORTH)
        rowRestriction = (row == 0 || row == 1 || row == 2 || row == 3 || row == 4);
      //row restriction for south piece
      else
        rowRestriction = (row == 9 || row == 8 || row == 7 || row == 6 || row == 5);
      
      return rowRestriction;
    }
    
    else {
      
      //variable to check if move is within the column restriction
      boolean columnRestriction = (column == 3 || column == 4 || column == 5);
      //variable to check if move is within the row restriction (north piece)
      boolean rowRestriction;
      if(piece.getSide() == ChessGame.Side.NORTH)
        rowRestriction = (row == 0 || row == 1 || row == 2);
      //row restriction for south piece
      else
        rowRestriction = (row == 9 || row == 8 || row == 7);
      
      //returns wheter or not the piece is within the row and column restrictions
      return rowRestriction && columnRestriction;
    }
    
  }
}