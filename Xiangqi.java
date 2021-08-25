public class Xiangqi implements ChessGame {
  //turnSide keeps track of whose turn it is
  private ChessGame.Side turnSide = Side.NORTH;
  
  //stores the number of rows for xiangqi
  private final int numRows = 10;
  
  //stores the number of columns for xiangqi
  private final int numColumns = 9;
  
  /** retrieves which turn it is
    * @return the side of the player whose current turn it is
    */
  public ChessGame.Side getTurnSide() {
    return this.turnSide;
  }
  
  /** sets the turn of the game
    * @param side is the side we are allowing to play
    */
  public void setTurnSide(ChessGame.Side side) {
    this.turnSide = side;
  }
  
  /** Determines if it is legal to play a given piece.
    * @param piece   the piece to be played
    * @param row     the row of the square the piece is on
    * @param column  the column of the square the piece is on
    * @return true if the piece is allowed to move on this turn
    */
  public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
    if(piece.getSide() == this.getTurnSide()) 
      return true;
    else 
      return false; 
  }
  
  /** Moves a piece to a new position.
    * @param piece     the piece to move
    * @param toRow     the row of the square the piece is moving to
    * @param toColumn  the column of the square the piece is moving to
    * @return true if the move was made, false if the move was not made
    */
  public boolean makeMove(ChessPiece piece, int toRow, int toColumn) {
    if(piece.isLegalMove(toRow, toColumn)) {
      
      //If there is a opposing piece on the square capture it, switches turn after move is done
      if(piece.isLegalCaptureMove(toRow, toColumn)) {
        
        //default moves for a capture move  
        piece.getChessBoard().removePiece(toRow,toColumn);
        piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
        piece.getChessBoard().addPiece(piece,toRow, toColumn);
        piece.moveDone();
        return true;
        
      }
      
      else if (piece.isLegalNonCaptureMove(toRow, toColumn)){
        piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
        piece.getChessBoard().addPiece(piece,toRow, toColumn);
        piece.moveDone();
        return true;
      }
    }
    
    //if it is not a legal move return false
    return false;
  }
  
   /**
   * Returns the number of rows in the chess game
   * @return int that represents the number of rows
   */
  public int getNumRows() {
    return numRows;
  }
  
  /**
   * Returns the number of columns in the chess game
   * @return int that represents the number of columns
   */
  public int getNumColumns() {
    return numColumns;
  }
  
  /**
   * Takes the given board and places all the pieces for the board in the correct starting position
   * @param board is the type of chess board we are using
   */
  public void startGame(ChessBoard board) {
    XiangqiKingPiece xiangqiKing = new XiangqiKingPiece(ChessGame.Side.NORTH, "X", 0,4,board);
    board.addPiece(xiangqiKing, xiangqiKing.getRow(), xiangqiKing.getColumn());
    GuardPiece guard = new GuardPiece(ChessGame.Side.NORTH, "G", 0,3, board);
    board.addPiece(guard, guard.getRow(), guard.getColumn());
    GuardPiece guard1 = new GuardPiece(ChessGame.Side.NORTH, "G",0,5,board);
    board.addPiece(guard1, guard1.getRow(), guard1.getColumn());
    ElephantPiece ele = new ElephantPiece(ChessGame.Side.NORTH, "E", 0,2,board);
    board.addPiece(ele, ele.getRow(), ele.getColumn());
    ElephantPiece ele1 = new ElephantPiece(ChessGame.Side.NORTH, "E", 0,6,board);
    board.addPiece(ele1, ele1.getRow(), ele1.getColumn());
    HorsePiece horse = new HorsePiece(ChessGame.Side.NORTH , "H", 0,1, board);
    board.addPiece(horse, horse.getRow(), horse.getColumn());
    HorsePiece horse1 = new HorsePiece(ChessGame.Side.NORTH, "H", 0,7,board);
    board.addPiece(horse1, horse1.getRow(), horse1.getColumn());
    Rook rook = new Rook(ChessGame.Side.NORTH, "R", 0,0, board);
    board.addPiece(rook, rook.getRow(), rook.getColumn());
    Rook rook1 = new Rook(ChessGame.Side.NORTH, "R", 0,8,board);
    board.addPiece(rook1, rook1.getRow(), rook1.getColumn());
    CannonPiece cannon = new CannonPiece(ChessGame.Side.NORTH, "C", 2, 1,board);
    board.addPiece(cannon, cannon.getRow(), cannon.getColumn());
    CannonPiece cannon1 = new CannonPiece(ChessGame.Side.NORTH, "C", 2, 7,board);
    board.addPiece(cannon1, cannon1.getRow(), cannon1.getColumn());
    SoldierPiece soldier = new SoldierPiece(ChessGame.Side.NORTH, "S", 3,0,board);
    board.addPiece(soldier, soldier.getRow(), soldier.getColumn());
    SoldierPiece soldier1 = new SoldierPiece(ChessGame.Side.NORTH, "S", 3,2,board);
    board.addPiece(soldier1, soldier1.getRow(), soldier1.getColumn());
    SoldierPiece soldier2 = new SoldierPiece(ChessGame.Side.NORTH, "S", 3,4,board);
    board.addPiece(soldier2, soldier2.getRow(), soldier2.getColumn());
    SoldierPiece soldier3 = new SoldierPiece(ChessGame.Side.NORTH, "S", 3,6,board);
    board.addPiece(soldier3, soldier3.getRow(), soldier3.getColumn());
    SoldierPiece soldier4 = new SoldierPiece(ChessGame.Side.NORTH, "S", 3,8,board);
    board.addPiece(soldier4, soldier4.getRow(), soldier4.getColumn());
    
    
    
    
    
    XiangqiKingPiece xiangqiKing1 = new XiangqiKingPiece(ChessGame.Side.SOUTH, "X", 9,4,board);
    board.addPiece(xiangqiKing1, xiangqiKing1.getRow(), xiangqiKing1.getColumn());
    GuardPiece guard2 = new GuardPiece(ChessGame.Side.SOUTH, "G", 9,3, board);
    board.addPiece(guard2, guard2.getRow(), guard2.getColumn());
    GuardPiece guard3 = new GuardPiece(ChessGame.Side.SOUTH, "G",9,5,board);
    board.addPiece(guard3, guard3.getRow(), guard3.getColumn());
    ElephantPiece ele2 = new ElephantPiece(ChessGame.Side.SOUTH, "E", 9,2,board);
    board.addPiece(ele2, ele2.getRow(), ele2.getColumn());
    ElephantPiece ele3 = new ElephantPiece(ChessGame.Side.SOUTH, "E", 9,6,board);
    board.addPiece(ele3, ele3.getRow(), ele3.getColumn());
    HorsePiece horse2 = new HorsePiece(ChessGame.Side.SOUTH, "H", 9,1, board);
    board.addPiece(horse2, horse2.getRow(), horse2.getColumn());
    HorsePiece horse3 = new HorsePiece(ChessGame.Side.SOUTH, "H", 9,7,board);
    board.addPiece(horse3, horse3.getRow(), horse3.getColumn());
    Rook rook2 = new Rook(ChessGame.Side.SOUTH, "R", 9,0, board);
    board.addPiece(rook2, rook2.getRow(), rook2.getColumn());
    Rook rook3 = new Rook(ChessGame.Side.SOUTH, "R", 9,8,board);
    board.addPiece(rook3, rook3.getRow(), rook3.getColumn());
    CannonPiece cannon2 = new CannonPiece(ChessGame.Side.SOUTH, "C", 7, 1,board);
    board.addPiece(cannon2, cannon2.getRow(), cannon2.getColumn());
    CannonPiece cannon3 = new CannonPiece(ChessGame.Side.SOUTH, "C", 7, 7,board);
    board.addPiece(cannon3, cannon3.getRow(), cannon3.getColumn());
    SoldierPiece soldier5 = new SoldierPiece(ChessGame.Side.SOUTH, "S", 6,0,board);
    board.addPiece(soldier5, soldier5.getRow(), soldier5.getColumn());
    SoldierPiece soldier6 = new SoldierPiece(ChessGame.Side.SOUTH, "S", 6,2,board);
    board.addPiece(soldier6, soldier6.getRow(), soldier6.getColumn());
    SoldierPiece soldier7 = new SoldierPiece(ChessGame.Side.SOUTH, "S", 6,4,board);
    board.addPiece(soldier7, soldier7.getRow(), soldier7.getColumn());
    SoldierPiece soldier8 = new SoldierPiece(ChessGame.Side.SOUTH, "S", 6,6,board);
    board.addPiece(soldier8, soldier8.getRow(), soldier8.getColumn());
    SoldierPiece soldier9 = new SoldierPiece(ChessGame.Side.SOUTH, "S", 6,8,board);
    board.addPiece(soldier9, soldier9.getRow(), soldier9.getColumn());

    
  }
}