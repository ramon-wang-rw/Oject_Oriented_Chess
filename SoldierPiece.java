public class SoldierPiece extends ChessPiece implements IsStraight, IsOneSpace, IsForward {
  /** Constructor that sets the side, label, row, column, and chessboard the piece is on
   * @param side the side the piece is on
   * @param label what piece it is
   * @param row the row the piece is on
   * @param columnn the column the piece is on
   * @param chessBoard the board the piece is on
   */
  public SoldierPiece(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  /** determines if the move is vertical
    * @param row the row the piece is moving to
    * @param column the column the piece is moving to 
    * @return true if the piece is only moving vertically otherwise false
    */
  public boolean isVertical(int row, int column) {
    boolean changeInRow = (this.getRow() - row != 0);               //Checks if there is a change in row
    boolean changeInColumn = (this.getColumn() - column != 0);      //Checks if there is a change in column
    
    //if there is a change in only the row  the move will be considered vertical
    if(changeInRow == true && changeInColumn == false) 
      return true;
    
    else 
      return false;
  }
  
  /** determines if the move is horizontal
    * @param row the row the piece is moving to
    * @param column the column the piece is moving to 
    * @return true if the piece is only moving horizontally otherwise false
    */
  public boolean isHorizontal(int row, int column) {
    boolean changeInRow = (this.getRow() - row != 0);               //Checks if there is a change in row
    boolean changeInColumn = (this.getColumn() - column != 0);      //Checks if there is a change in column
    
    //if there is a change in only the row  the move will be considered vertical
    if(changeInRow == false && changeInColumn == true) 
      return true;
    
    else 
      return false;
  }

 /* returns true when the legal move is vertical and only moves one space in the first half
  * or a vertical/horizontal move one space in the second half
   * @param row the row the piece is moving to
   * @param column the column the piece is moving to
   * @return true if legal otherwise false
   */
  public boolean isLegalNonCaptureMove(int row, int column) {
    
    //case for north side soldier
    if(this.getSide() == ChessGame.Side.NORTH) {
      //for the first five rows it can only move vertically forward
      if(this.getRow() <= 4) {
        if(this.isVertical(row, column) && this.isOneSpace(row, column) && this.isForward(row,column)) {
          if(this.getChessBoard().hasPiece(row,column))
            return false;
          else 
            return true;
        }
      }
      //for the last five rows it can move either vertically or horizontally
      else {
        if((this.isVertical(row, column) && this.isOneSpace(row, column) && this.isForward(row,column))
             || (this.isHorizontal(row, column) && this.isOneSpace(row, column))) {
          if(this.getChessBoard().hasPiece(row,column))
            return false;
          else
            return true;
        }
      }
    }
    
    //case for south side soldier
    if(this.getSide() == ChessGame.Side.SOUTH) {
      //for the first five rows it can only move vertically forward
      if(this.getRow() >= 5) {
        if(this.isVertical(row, column) && this.isOneSpace(row, column) && this.isForward(row,column)) {
          if(this.getChessBoard().hasPiece(row,column))
            return false;
          else 
            return true;
        }
      }
      //for the last five rows it can move either vertically or horizontally
      else {
        if((this.isVertical(row, column) && this.isOneSpace(row, column) && this.isForward(row,column))
             || (this.isHorizontal(row, column) && this.isOneSpace(row, column))) {
          if(this.getChessBoard().hasPiece(row,column))
            return false;
          else
            return true;
        }
      }
    }
    return false;
  }

  
  /** returns true when the soldier piece is moving forward vertically one space in the first half
    * or vertically and horizontally one space in the second half onto an enemy piece
   * @param row the row the piece is moving to
   * @param column the column the piece is moving to
   * @return true if legal otherwise false
   */
  public boolean isLegalCaptureMove(int row, int column) {
    //case for north side soldier
    if(this.getSide() == ChessGame.Side.NORTH) {
      //for the first five rows it can only move vertically forward
      if(this.getRow() <= 4) {
        if(this.isVertical(row, column) && this.isOneSpace(row, column) && this.isForward(row,column)
           && this.getChessBoard().hasPiece(row,column) 
           && (this.getChessBoard().getPiece(row,column).getSide() != this.getSide())) 
          return true;
        else 
          return false;
        }
      //for the last five rows it can move either vertically or horizontally
      else {
        if((this.isVertical(row, column) && this.isOneSpace(row, column) && this.isForward(row,column))
             || (this.isHorizontal(row, column) && this.isOneSpace(row, column))
          && this.getChessBoard().hasPiece(row,column) 
           && (this.getChessBoard().getPiece(row,column).getSide() != this.getSide())) 
          return true;
        else 
          return false; 
        }
    }


    
    //case for south side soldier
    if(this.getSide() == ChessGame.Side.SOUTH) {
      //for the first five rows it can only move vertically forward
      if(this.getRow() >= 5) {
        if(this.isVertical(row, column) && this.isOneSpace(row, column) && this.isForward(row,column)
           && this.getChessBoard().hasPiece(row,column) 
           && (this.getChessBoard().getPiece(row,column).getSide() != this.getSide())) 
          return true;
        else 
          return false;
        }
      //for the last five rows it can move either vertically or horizontally
      else {
        if((this.isVertical(row, column) && this.isOneSpace(row, column) && this.isForward(row,column))
             || (this.isHorizontal(row, column) && this.isOneSpace(row, column))
          && this.getChessBoard().hasPiece(row,column) 
           && (this.getChessBoard().getPiece(row,column).getSide() != this.getSide())) 
          return true;
        else 
          return false; 
        }
    }
    return false;
    
  }
}
    
