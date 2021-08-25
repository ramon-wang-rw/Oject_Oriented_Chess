public abstract class ChessPiece {
  
  private ChessGame.Side side;                    //Field for which player the piece belongs to
  private String label;                           //Field that stores the name of the piece
  private Object icon = null;                     //Field that stores the image of the piece
  private int row;                                //Field that stores what row the piece is on
  private int column;                             //Field that stores what column the piece is on
  private ChessBoard chessBoard;                  //Field that stores which chessboard the piece is on
  private int movesMade;                          //Field that stores the amount of moves made by the piece 
 
  //Constructor that takes input for the side, label, row, column and the chessboard of the chess piece
  public ChessPiece(ChessGame.Side side, String label, int row, int column, ChessBoard chessBoard) {
    this.side = side;
    this.label = label;
    this.row = row;
    this.column = column;
    this.chessBoard = chessBoard;
  }
    
  //returns which player the piece belongs to
  public ChessGame.Side getSide() {
    return this.side;
  }
  
  //returns the label of the piece
  public String getLabel() {
    return this.label;
  }
  
  //returns the Icon of the piece
  public Object getIcon() {
    return this.icon;
  }
  
  //sets the current location of the chess piece
  public void setLocation(int row, int column) {
    this.row = row;
    this.column = column;
  }
  
  //returns if the piece is legally allowed to move from current to input location
  public boolean isLegalMove(int toRow, int toColumn) {
    if (isLegalNonCaptureMove(toRow, toColumn) || isLegalCaptureMove(toRow, toColumn))
      return true;
    else
      return false;
  }
  
  //returns the chessboard the piece is on
  public ChessBoard getChessBoard() {
    return this.chessBoard;
  }
  
  //returns the row the piece is on
  public int getRow() {
    return this.row;
  }
  
  // returns the column the piece is on
  public int getColumn() {
    return this.column;
  }
  
  //returns if it is legal to move the piece in a non capture move
  public abstract boolean isLegalNonCaptureMove(int row, int column);
  
  //returns if it is legal to move the piece in a capture move
  public abstract boolean isLegalCaptureMove(int row, int column);
  
  //retrieves the amount of moves made by the piece
  public int getMovesMade() {
    return this.movesMade;
  }
  
  //sets the amount of moves made by the piece
  public void setMovesMade(int movesMade) {
    this.movesMade = movesMade;
  }
  
  //handles processing once move is completed
  public void moveDone() {
    
    //handles for European Chess
    if(this.getChessBoard().getGameRules() instanceof EuropeanChess) {
      EuropeanChess europeanChess = (EuropeanChess)this.getChessBoard().getGameRules(); 
      //Switches turn after move is done
      if(europeanChess.getTurnSide() == ChessGame.Side.NORTH)
        europeanChess.setTurnSide(ChessGame.Side.SOUTH);
      else
        europeanChess.setTurnSide(ChessGame.Side.NORTH);
    
      //incrememnts move made by piece by 1
      this.setMovesMade(this.getMovesMade() + 1);
    }
    
    //handles for Xiangqi
    else if(this.getChessBoard().getGameRules() instanceof Xiangqi) {
      Xiangqi xiangqi = (Xiangqi)this.getChessBoard().getGameRules();
      //switches turn after move is done
      if(xiangqi.getTurnSide() == ChessGame.Side.NORTH)
        xiangqi.setTurnSide(ChessGame.Side.SOUTH);
      else 
        xiangqi.setTurnSide(ChessGame.Side.NORTH);
    }
    
  }
  
}
