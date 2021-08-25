import java.lang.Math; 

//Inherited by Pawns and Kings
public interface IsTwoSpace {
 
  //retrieves the row of the piece
  int getRow();
  
  //retrieves the column of the piece
  int getColumn();

  
  //determines if the move only moves the piece two spaces in any direction
  default boolean isTwoSpace(int row, int column) {
    if(Math.abs(this.getRow() - row) == 2 && Math.abs(this.getColumn() - column) == 2) 
      return true;
    else
      return false;
  }
}