import java.lang.Math; 

//this interface is inherited by Pawns and Soldiers
public interface IsForward {
  //returns the row the piece is on
  int getRow();
  //returns the column the piece is on
  int getColumn();
  //returns the side the piece is on
  ChessGame.Side getSide();
  
  /** determines if the piece is moving towards the enemy side
    * @param row the row the piece is moving towards
    * @param column the column thepiece is moving towards
    * @return true if it is moving towards the enemy side otherwise false
    */
  public default boolean isForward(int row, int column) {
    if (this.getSide() == ChessGame.Side.NORTH) {
      if (row - this.getRow() > 0) 
        return true;
      
      return false;
    }
    else {
      if (this.getRow() - row > 0) 
        return true;
      
      return false;
    }
  }
}