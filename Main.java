public class Main {
  public static void main(String[] args) {
    SwingEuropeanChessDisplay display = new SwingEuropeanChessDisplay();
    EuropeanChess chess = new EuropeanChess();
    SwingChessBoard board = new SwingChessBoard(display,chess);
    chess.startGame(board);
  }
}