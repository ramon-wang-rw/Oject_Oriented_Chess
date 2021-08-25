import java.lang.Math; 

//this interface is inherited by Bishops, Queens, Kings, and Pawns
public interface IsDiagonal {
  //returns the row the piece is on
  int getRow();
  //returns the column the piece is on
  int getColumn();
  //returns the chessboard the piece is on
  ChessBoard getChessBoard();
  
  //determines wheter or not inputed move is diagonal
  default boolean isDiagonal(int toRow, int toColumn) {
    if(Math.abs(toRow - this.getRow()) == Math.abs(toColumn - this.getColumn())) {
      
      //each if statement checks if there is a piece in between the intended square and the piece
      if(toRow - this.getRow() > 0 && toColumn - this.getColumn() > 0) {
        int rowCheck = this.getRow();             //keeps track of the row we are looking at
        int columnCheck = this.getColumn();       //keeps track of the column we are looking at
        for(int i = 1; i < (toRow - this.getRow()); i++) {
          rowCheck++;
          columnCheck++;
          if(this.getChessBoard().hasPiece(rowCheck, columnCheck))
            return false;
        }
      }
      
      else if(toRow - this.getRow() > 0 && toColumn - this.getColumn() < 0) {
        int rowCheck = this.getRow();
        int columnCheck = this.getColumn();
        for(int i = 1; i < (toRow - this.getRow()); i++) {
          rowCheck++;
          columnCheck--;
          if(this.getChessBoard().hasPiece(rowCheck, columnCheck))
            return false;
        }
      }
      
      else if(toRow - this.getRow() < 0 && toColumn - this.getColumn() > 0) {
        int rowCheck = this.getRow();
        int columnCheck = this.getColumn();
        for(int i = 1; i < (toColumn - this.getColumn()); i++) {
          rowCheck--;
          columnCheck++;
          if(this.getChessBoard().hasPiece(rowCheck, columnCheck))
            return false;
        }
      }
      
      else if(toRow - this.getRow() < 0 && toColumn - this.getColumn() < 0) {
        int rowCheck = this.getRow();
        int columnCheck = this.getColumn();
        for(int i = 1; i < Math.abs((toRow - this.getRow())); i++) {
          rowCheck--;
          columnCheck--;
          if(this.getChessBoard().hasPiece(rowCheck, columnCheck))
            return false;
        }
      }
      
      return true;
    }
    
    else
      return false;
  }
}

