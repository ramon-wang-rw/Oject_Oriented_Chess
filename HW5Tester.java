import org.junit.*;
import static org.junit.Assert.*;

public class HW5Tester {
  
  @Test
  public void testIsLegalMove() {
    //testing if xiangqiKingPiece moves as intended (can't move out of center or the first three rows)
    JavaFXChessBoard board = new JavaFXChessBoard();
    XiangqiKingPiece king = new XiangqiKingPiece(ChessGame.Side.NORTH, "X", 2,3,board);
    assertEquals("Testing king legal non capture move", false, king.isLegalNonCaptureMove(3,3));
    assertEquals("Testing king legal non capture move", true, king.isLegalNonCaptureMove(2,4));
    
    
  }
  
}