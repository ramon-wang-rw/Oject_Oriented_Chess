public class EuropeanChess implements ChessGame {
  //turnSide keeps track of whose turn it is
  private ChessGame.Side turnSide = Side.NORTH;
  
  //stores the number of rows for european chess
  private final int numRows = 8;
  
  //stores the number of columns for european chess
  private final int numColumns = 8;
  
  //retrieves turnSide
  public ChessGame.Side getTurnSide() {
    return this.turnSide;
  }
  
  //sets turnSide
  public void setTurnSide(ChessGame.Side side) {
    this.turnSide = side;
  }
  
  //Checks if piece belongs to the player of the current turn
  public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
    if(piece.getSide() == this.getTurnSide()) 
      return true;
    else 
      return false; 
  }
  
  //Checks if move is legal and makes the necessary move specific to the piece
  public boolean makeMove(ChessPiece piece, int toRow, int toColumn) {
    if(piece.isLegalMove(toRow, toColumn)) {
      
      //If there is a opposing piece on the square capture it, switches turn after move is done
      if(piece.isLegalCaptureMove(toRow, toColumn)) {
        
        //checks if pawn reached the opposing side through a non capture move, 
        //prompt the user if it did to input a new piece that isn't a king
        if(piece instanceof Pawn) {
          
          //case for north side pawn
          if(piece.getSide() == ChessGame.Side.NORTH) {
            if(toRow == numRows - 1) {
              //stores the user input of their desired piece
              String newPiece = javax.swing.JOptionPane.showInputDialog("Upgrade to New Piece!(CAN NOT BE KING)");
              //keeps track of wheter or not the input is valid
              boolean isValidInput = false;      
              
              while(isValidInput == false) {
                if(newPiece.equals("bishop") || newPiece.equals("Bishop")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Bishop(piece.getSide(), "B", toRow, toColumn, 
                                                            piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("rook") || newPiece.equals("Rook")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Rook(piece.getSide(), "R", toRow, toColumn, 
                                                          piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("knight") || newPiece.equals("Knight")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Knight(piece.getSide(), "N", toRow, toColumn, 
                                                            piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("queen") || newPiece.equals("Queen")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Queen(piece.getSide(), "Q", toRow, toColumn, 
                                                           piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                newPiece = javax.swing.JOptionPane.showInputDialog("Please Input a Valid Piece");
              }
            }
          }
          
          //case for south side pawn
          else if(piece.getSide() == ChessGame.Side.SOUTH) {
            if(toRow == 0) {
              String newPiece = javax.swing.JOptionPane.showInputDialog("Upgrade to New Piece!(CAN NOT BE KING)");
              boolean isValidInput = false;
              
              while(isValidInput == false) {
                if(newPiece.equals("bishop") || newPiece.equals("Bishop")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Bishop(piece.getSide(), "B", toRow, toColumn, 
                                                            piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("rook") || newPiece.equals("Rook")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Rook(piece.getSide(), "R", toRow, toColumn, 
                                                          piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("knight") || newPiece.equals("Knight")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Knight(piece.getSide(), "N", toRow, toColumn, 
                                                            piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("queen") || newPiece.equals("Queen")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Queen(piece.getSide(), "Q", toRow, toColumn, 
                                                           piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                newPiece = javax.swing.JOptionPane.showInputDialog("Please Input a Valid Piece");
              }
            }
          }
          
        }
        
        //default moves for a capture move  
        piece.getChessBoard().removePiece(toRow,toColumn);
        piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
        piece.getChessBoard().addPiece(piece,toRow, toColumn);
        piece.moveDone();
        return true;
      }
      
      
      //moves the piece to the empty square, switches turn after move is done
      else if (piece.isLegalNonCaptureMove(toRow, toColumn)){
        
        //checks if King is performing the castle move and if it is perform the moves and return true
        if (piece instanceof King) {
          King check = (King)piece;
          if(check.isCastling(toRow, toColumn)) {
            
            int columnHolder = piece.getColumn();
            
            //king moves two to the left
            if(columnHolder < toColumn) {
              ChessPiece rookHolder = piece.getChessBoard().removePiece(toRow,toColumn + 2);
              piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
              piece.getChessBoard().addPiece(piece,toRow, toColumn);
              piece.getChessBoard().addPiece(rookHolder, toRow, toColumn - 1);
              
              if(piece.getChessBoard().squareThreatened(toRow, toColumn, piece) 
                   || piece.getChessBoard().squareThreatened(toRow, toColumn - 1, piece)) {
                piece.getChessBoard().removePiece(toRow, toColumn);
                piece.getChessBoard().removePiece(toRow, toColumn - 1);
                piece.getChessBoard().addPiece(piece,piece.getRow(), columnHolder);
                piece.getChessBoard().addPiece(rookHolder, toRow, toColumn + 1);
                return false;
              }
              
              rookHolder.setMovesMade(1);
              piece.moveDone();
              return true;
            }
            
            //king moves two to the right
            else {
              ChessPiece rookHolder = piece.getChessBoard().removePiece(toRow,toColumn - 1);
              piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
              piece.getChessBoard().addPiece(piece,toRow, toColumn);
              piece.getChessBoard().addPiece(rookHolder, toRow, toColumn + 1); 
              
              if(piece.getChessBoard().squareThreatened(toRow, toColumn, piece) 
                   || piece.getChessBoard().squareThreatened(toRow, toColumn + 1, piece)) {
                piece.getChessBoard().removePiece(toRow, toColumn);
                piece.getChessBoard().removePiece(toRow, toColumn + 1);
                piece.getChessBoard().addPiece(piece,piece.getRow(), columnHolder);
                piece.getChessBoard().addPiece(rookHolder, toRow, toColumn - 1);
                return false;
              }
              
              rookHolder.setMovesMade(1);
              piece.moveDone();
              return true;
            }
          }
        }
        
        //checks if pawn reached the opposing side through non-capture move, 
        //prompt the user if it did to input a new piece that isn't a king
        if(piece instanceof Pawn) {
          
          //case for north side pawn
          if(piece.getSide() == ChessGame.Side.NORTH) {
            if(toRow == numRows - 1) {
              //stores the user input of their desired piece
              String newPiece = javax.swing.JOptionPane.showInputDialog("Upgrade to New Piece!(CAN NOT BE KING)");
              //keeps track of wheter or not the input is valid
              boolean isValidInput = false;
              
              while(isValidInput == false) {
                if(newPiece.equals("bishop") || newPiece.equals("Bishop")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Bishop(piece.getSide(), "B", toRow, toColumn, 
                                                            piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("rook") || newPiece.equals("Rook")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Rook(piece.getSide(), "R", toRow, toColumn, 
                                                          piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("knight") || newPiece.equals("Knight")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Knight(piece.getSide(), "N", toRow, toColumn, 
                                                            piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("queen") || newPiece.equals("Queen")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Queen(piece.getSide(), "Q", toRow, toColumn, 
                                                           piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                newPiece = javax.swing.JOptionPane.showInputDialog("Please Input a Valid Piece");
              }
            }
          }
          //case for south side pawn
          else if(piece.getSide() == ChessGame.Side.SOUTH) {
            if(toRow == 0) {
              String newPiece = javax.swing.JOptionPane.showInputDialog("Upgrade to New Piece!(CAN NOT BE KING)");
              boolean isValidInput = false;
              
              while(isValidInput == false) {
                if(newPiece.equals("bishop") || newPiece.equals("Bishop")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Bishop(piece.getSide(), "B", toRow, toColumn, 
                                                            piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("rook") || newPiece.equals("Rook")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Rook(piece.getSide(), "R", toRow, toColumn, 
                                                          piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("knight") || newPiece.equals("Knight")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Knight(piece.getSide(), "N", toRow, toColumn, 
                                                            piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                
                else if(newPiece.equals("queen") || newPiece.equals("Queen")) {
                  piece.getChessBoard().removePiece(piece.getRow(),piece.getColumn());
                  piece.getChessBoard().addPiece(new Queen(piece.getSide(), "Q", toRow, toColumn, 
                                                           piece.getChessBoard()),toRow, toColumn);
                  piece.moveDone();
                  return true;
                }
                newPiece = javax.swing.JOptionPane.showInputDialog("Please Input a Valid Piece");
              }
            }
          }
          
        }

        //Prevents King from moving to a threatened square
        if(piece instanceof King) {
          int rowHolder = piece.getRow();
          int colHolder = piece.getColumn();
          piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
          piece.getChessBoard().addPiece(piece,toRow, toColumn);
          if(piece.getChessBoard().squareThreatened(toRow, toColumn,piece)) {
            piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
            piece.getChessBoard().addPiece(piece,rowHolder, colHolder);
            return false;
          }
        }
        
        //default actions for a non capture move
        piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
        piece.getChessBoard().addPiece(piece,toRow, toColumn);
        piece.moveDone();
        return true;
      }
      
    }
    
    return false;
  }
  
   /**
   * Returns the number of rows in the chess game
   * @return int that represents the number of rows
   */
  public int getNumRows() {
    return this.numRows;
  }
  
   /**
   * Returns the number of columns in the chess game
   * @return int that represents the number of columns
   */
  public int getNumColumns() {
    return this.numColumns;
  }
  
  /**
   * Takes the given board and places all the pieces for the board in the correct starting position
   * @param board is the type of chess board we are using
   */
  public void startGame(ChessBoard board) {
    
    Pawn pawn = new Pawn(ChessGame.Side.SOUTH, "P", 6,0,board);
    board.addPiece(pawn, pawn.getRow(), pawn.getColumn());
    Pawn pawn2 = new Pawn(ChessGame.Side.SOUTH, "P", 6,1,board);
    board.addPiece(pawn2, pawn2.getRow(), pawn2.getColumn());
    Pawn pawn3 = new Pawn(ChessGame.Side.SOUTH, "P", 6,2,board);
    board.addPiece(pawn3, pawn3.getRow(), pawn3.getColumn());
    Pawn pawn4 = new Pawn(ChessGame.Side.SOUTH, "P", 6,3,board);
    board.addPiece(pawn4, pawn4.getRow(), pawn4.getColumn());
    Pawn pawn5 = new Pawn(ChessGame.Side.SOUTH, "P", 6,4,board);
    board.addPiece(pawn5, pawn5.getRow(), pawn5.getColumn());
    Pawn pawn6 = new Pawn(ChessGame.Side.SOUTH, "P", 6,5,board);
    board.addPiece(pawn6, pawn6.getRow(), pawn6.getColumn());
    Pawn pawn7 = new Pawn(ChessGame.Side.SOUTH, "P", 6,6,board);
    board.addPiece(pawn7, pawn7.getRow(), pawn7.getColumn());
    Pawn pawn8 = new Pawn(ChessGame.Side.SOUTH, "P", 6,7,board);
    board.addPiece(pawn8, pawn8.getRow(), pawn8.getColumn());
    
    Knight knight = new Knight(ChessGame.Side.SOUTH, "N", 7,1, board);
    board.addPiece(knight, knight.getRow(), knight.getColumn());
    Knight knight1 = new Knight(ChessGame.Side.SOUTH, "N", 7,6, board);
    board.addPiece(knight1, knight1.getRow(), knight1.getColumn());
    
    Rook rook = new Rook(ChessGame.Side.SOUTH, "R", 7, 0, board);
    board.addPiece(rook, rook.getRow(), rook.getColumn());
    Rook rook1 = new Rook(ChessGame.Side.SOUTH, "R", 7,7, board);
    board.addPiece(rook1, rook1.getRow(), rook1.getColumn());
    
    Bishop bishop = new Bishop(ChessGame.Side.SOUTH, "B", 7, 2, board);
    board.addPiece(bishop, bishop.getRow(), bishop.getColumn());
    Bishop bishop1 = new Bishop(ChessGame.Side.SOUTH, "B", 7, 5, board);
    board.addPiece(bishop1, bishop1.getRow(), bishop1.getColumn());
    
    Queen queen = new Queen(ChessGame.Side.SOUTH, "Q", 7,4, board);
    board.addPiece(queen, queen.getRow(), queen.getColumn());
    
    King king = new King(ChessGame.Side.SOUTH, "K", 7, 3, board);
    board.addPiece(king, king.getRow(), king.getColumn());
    
    Pawn pawn9 = new Pawn(ChessGame.Side.NORTH, "P", 1,0,board);
    board.addPiece(pawn9, pawn9.getRow(), pawn9.getColumn());
    Pawn pawn10 = new Pawn(ChessGame.Side.NORTH, "P", 1,1,board);
    board.addPiece(pawn10, pawn10.getRow(), pawn10.getColumn());
    Pawn pawn11 = new Pawn(ChessGame.Side.NORTH, "P", 1,2,board);
    board.addPiece(pawn11, pawn11.getRow(), pawn11.getColumn());
    Pawn pawn12 = new Pawn(ChessGame.Side.NORTH, "P", 1,3,board);
    board.addPiece(pawn12, pawn12.getRow(), pawn12.getColumn());
    Pawn pawn13 = new Pawn(ChessGame.Side.NORTH, "P", 1,4,board);
    board.addPiece(pawn13, pawn13.getRow(), pawn13.getColumn());
    Pawn pawn14 = new Pawn(ChessGame.Side.NORTH, "P", 1,5,board);
    board.addPiece(pawn14, pawn14.getRow(), pawn14.getColumn());
    Pawn pawn15 = new Pawn(ChessGame.Side.NORTH, "P", 1,6,board);
    board.addPiece(pawn15, pawn15.getRow(), pawn15.getColumn());
    Pawn pawn16 = new Pawn(ChessGame.Side.NORTH, "P", 1,7,board);
    board.addPiece(pawn16, pawn16.getRow(), pawn16.getColumn());
    
    Knight knight2 = new Knight(ChessGame.Side.NORTH, "N", 0,1, board);
    board.addPiece(knight2, knight2.getRow(), knight2.getColumn());
    Knight knight3 = new Knight(ChessGame.Side.NORTH, "N", 0,6, board);
    board.addPiece(knight3, knight3.getRow(), knight3.getColumn());
    
    Rook rook2 = new Rook(ChessGame.Side.NORTH, "R", 0, 0, board);
    board.addPiece(rook2, rook2.getRow(), rook2.getColumn());
    Rook rook3 = new Rook(ChessGame.Side.NORTH, "R", 0,7, board);
    board.addPiece(rook3, rook3.getRow(), rook3.getColumn());
    
    Bishop bishop2 = new Bishop(ChessGame.Side.NORTH, "B", 0, 2, board);
    board.addPiece(bishop2, bishop2.getRow(), bishop2.getColumn());
    Bishop bishop3 = new Bishop(ChessGame.Side.NORTH, "B", 0, 5, board);
    board.addPiece(bishop3, bishop3.getRow(), bishop3.getColumn());
    
    Queen queen1 = new Queen(ChessGame.Side.NORTH, "Q", 0,4, board);
    board.addPiece(queen1, queen1.getRow(), queen1.getColumn());
    
    King king1 = new King(ChessGame.Side.NORTH, "K", 0, 3, board);
    board.addPiece(king1, king1.getRow(), king1.getColumn());
  }
  
  
}