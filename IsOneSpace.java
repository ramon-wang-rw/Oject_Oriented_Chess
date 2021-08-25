import java.lang.Math; 

//Inherited by Pawns and Kings
public interface IsOneSpace {
 
  //retrieves the row of the piece
  int getRow();
  
  //retrieves the column of the piece
  int getColumn();

  
  //determines if the move only moves the piece one space in any direction
  default boolean isOneSpace(int row, int column) {
    if(Math.abs(this.getRow() - row) <= 1 && Math.abs(this.getColumn() - column) <= 1) 
      return true;
    else
      return false;
  }
}