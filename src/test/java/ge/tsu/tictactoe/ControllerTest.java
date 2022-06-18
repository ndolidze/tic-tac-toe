package ge.tsu.tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ControllerTest {

  @Test
  void getButtonTextByPlayerTurn() {
    Controller controller = new Controller();
    String buttonText = controller.getButtonTextByPlayerTurn(240);
    Assertions.assertEquals(buttonText, "X");
    Assertions.assertNotEquals(buttonText, "O");
  }

  @Test
  void getButtonTextByPlayerTurn2() {
    Controller controller = new Controller();
    String buttonText = controller.getButtonTextByPlayerTurn(241);
    Assertions.assertEquals(buttonText, "O");
    Assertions.assertNotEquals(buttonText, "X");
  }


  @Test
  void getWinnerTextByLine() {
    Controller controller = new Controller();
    String winnerText = controller.getWinnerTextByLine("XXX");
    Assertions.assertNotEquals(winnerText, "O won!");
    Assertions.assertEquals(winnerText, "X won!");
  }

  @Test
  void getWinnerTextByLine2() {
    Controller controller = new Controller();
    String winnerText = controller.getWinnerTextByLine("OXX");
    Assertions.assertNotEquals(winnerText, "O won!");
    Assertions.assertNotEquals(winnerText, "X won!");
  }
}