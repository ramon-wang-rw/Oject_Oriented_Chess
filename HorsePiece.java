public class HorsePiece extends ChessPiece {
  /** Constructor that sets the side, label, row, column, and chessboard the piece is on
   * @param side the side the piece is on
   * @param label what piece it is
   * @param row the row the piece is on
   * @param columnn the column the piece is on
   * @param chessBoard the board the piece is on
   */
  public HorsePiece(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    super(side, label, row, column, chessBoard);
  }
  
  /* Ensures that the intended move is valid for a horse piece
   * @param row the row it is moving to
   * @param column the column it is moving to
   * @return true if the intended move is valid otherwise false
   */
  public boolean isValidHorseMove(int row, int column) {
    //first variation of the L-Shaped move
    boolean lShape1 = this.getRow() - row == 2 && this.getColumn() - column == 1;
    //second variation
    boolean lShape2 = this.getRow() - row == 2 && this.getColumn() - column == -1;
    //third variation
    boolean lShape3 = this.getRow() - row == 1 && this.getColumn() - column == 2;
    //fourth variation
    boolean lShape4 = this.getRow() - row == 1 && this.getColumn() - column == -2;
    //fifth variation
    boolean lShape5 = this.getRow() - row == -2 && this.getColumn() - column == 1;
    //sixth variation
    boolean lShape6 = this.getRow() - row == -2 && this.getColumn() - column == -1;
    //seventh variation
    boolean lShape7 = this.getRow() - row == -1 && this.getColumn() - column == 2;
    //eighth variation
    boolean lShape8 = this.getRow() - row == -1 && this.getColumn() - column == -2;
    
    //Variables store wheter or not the horse piece is being blocked
    boolean block1 = true;
    boolean block2 = true;
    boolean block3 = true;
    boolean block4 = true;
    
    //if statements ensure that checking the blocks don't violate the pieces array
    if(this.getRow() == 0 && this.getColumn() == 0) {
      block2 = this.getChessBoard().hasPiece(this.getRow() + 1, this.getColumn());
      block3 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() + 1);
    }
    else if(this.getRow() == 0 && this.getColumn() != this.getChessBoard().getGameRules().getNumColumns() - 1) {
      block2 = this.getChessBoard().hasPiece(this.getRow() + 1, this.getColumn());
      block3 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() + 1);
      block4 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() - 1);
    }
    else if(this.getRow() == 0 && this.getColumn() == this.getChessBoard().getGameRules().getNumColumns() - 1) {
      block2 = this.getChessBoard().hasPiece(this.getRow() + 1, this.getColumn());
      block4 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() - 1);
    }
    else if(this.getRow() == this.getChessBoard().getGameRules().getNumRows() - 1 && this.getColumn() == 0) {
      block1 = this.getChessBoard().hasPiece(this.getRow() - 1, this.getColumn());
      block3 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() + 1);
    }
    else if(this.getRow() == this.getChessBoard().getGameRules().getNumRows() - 1
           && this.getColumn() != this.getChessBoard().getGameRules().getNumColumns() - 1) {
      block1 = this.getChessBoard().hasPiece(this.getRow() - 1, this.getColumn());
      block3 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() + 1);
      block4 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() - 1);
    }
    else if(this.getRow() == this.getChessBoard().getGameRules().getNumRows() - 1
              && this.getColumn() == this.getChessBoard().getGameRules().getNumColumns() - 1) {
      block1 = this.getChessBoard().hasPiece(this.getRow() - 1, this.getColumn());
      block4 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() - 1);
    }
    else if(this.getColumn() == 0) {
      block1 = this.getChessBoard().hasPiece(this.getRow() - 1, this.getColumn());
      block2 = this.getChessBoard().hasPiece(this.getRow() + 1, this.getColumn());
      block3 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() + 1);
    }
    else if(this.getColumn() == this.getChessBoard().getGameRules().getNumColumns() - 1) {
      block1 = this.getChessBoard().hasPiece(this.getRow() - 1, this.getColumn());
      block2 = this.getChessBoard().hasPiece(this.getRow() + 1, this.getColumn());
      block4 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() - 1);
    }
    else {
      block1 = this.getChessBoard().hasPiece(this.getRow() - 1, this.getColumn()); //up
      block2 = this.getChessBoard().hasPiece(this.getRow() + 1, this.getColumn()); //down
      block3 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() + 1); //right
      block4 = this.getChessBoard().hasPiece(this.getRow(), this.getColumn() - 1); //left
    }
    
    //Checks if the specific move variation is being blocked by a piece
    if((lShape1 || lShape2) && !block1)
      return true;
    else if((lShape3 || lShape7) && !block4)
      return true;
    else if((lShape5 || lShape6) && !block2)
      return true;
    else if((lShape4 || lShape8) && !block3)
      return true;
    else
      return false;
  }
    
  
  
  /** returns true if moving in an L shape without a piece blocking it on the up/down/left/right direction
    * should move to empty square
    * @row the row the piece is moving to
    * @column the column the piece is moving to
    * @return true if it is a legal non capture move
    */
  public boolean isLegalNonCaptureMove(int row, int column) {
    
    if(this.isValidHorseMove(row,column)) {
      if(this.getChessBoard().hasPiece(row,column))
        return false;
      else
        return true;
    }
    else
      return false;
  }
  
  /** returns true if moving in an L shape without a piece blocking it on the up/down/left/right direction
    * should move to enemy piece
    * @row the row the piece is moving to
    * @column the column the piece is moving to
    * @return true if it is a legal capture move
    */
  public boolean isLegalCaptureMove(int row, int column) {
    if(this.isValidHorseMove(row,column) && this.getChessBoard().hasPiece(row,column) &&
       (this.getChessBoard().getPiece(row,column).getSide() != this.getSide()))
      return true;
    else 
      return false;
  }
}