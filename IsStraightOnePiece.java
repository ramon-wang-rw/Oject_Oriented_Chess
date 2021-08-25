import java.lang.Math; 

//this interface is inherited by Rooks, Queens, Kings, and Pawns
public interface IsStraightOnePiece {
  //returns the row the piece is on
  int getRow();
  //returns the column the piece is on
  int getColumn();
  //returns the chessboard the piece is on
  ChessBoard getChessBoard();
  
  default boolean isStraightOnePiece(int toRow, int toColumn) {
    boolean changeInRow = (this.getRow() - toRow != 0);               //Checks if there is a change in row
    boolean changeInColumn = (this.getColumn() - toColumn != 0);      //Checks if there is a change in column
    
    //if there is a change in only one dimension and there is no piece blocking it, the move is a straight move
    //and returns true, otherwise it is not a straight move and returns false
    if((changeInRow == true && changeInColumn == false) || (changeInRow == false && changeInColumn == true)) {
      int piecesBetween = 0;                                          //keeps track of how many pieces in between
      
      //checks if there is a piece in between the piece and the intended square and returns false if there is
      if(changeInRow) {
        int rowCheck = this.getRow();                                 //keeps track of row
        
        if(toRow - this.getRow() > 0) {
          for(int i = 1; i < (toRow - this.getRow()); i++) {
            
            rowCheck++;
          
            if(this.getChessBoard().hasPiece(rowCheck, this.getColumn()))
              piecesBetween++;
            
          }
          if(piecesBetween != 1)
              return false;
        }
        
        else {
          for(int i = 1; i < Math.abs(toRow - this.getRow()); i++) {
            rowCheck--;
            
             if(this.getChessBoard().hasPiece(rowCheck, this.getColumn()))
              piecesBetween++;
            
          }
          if(piecesBetween != 1)
              return false;
        }
        
      }
      
      else if(changeInColumn) {
        int columnCheck = this.getColumn();                            //keeps track of column

        if(toColumn - this.getColumn() > 0) {
          for(int i = 1; i < (toColumn - this.getColumn()); i++) {
          columnCheck++;
          
          if(this.getChessBoard().hasPiece(this.getRow(), columnCheck))
            piecesBetween++;
          }
          if(piecesBetween != 1)
              return false;
        }
        
        else {
          for(int i = 1; i < Math.abs(toColumn - this.getColumn()); i++) {
            columnCheck--;
            
            if(this.getChessBoard().hasPiece(this.getRow(), columnCheck))
            piecesBetween++;

          }
          if(piecesBetween != 1)
              return false;
        }
        
      }
      return true;
    }
    else 
      return false;
  }
}