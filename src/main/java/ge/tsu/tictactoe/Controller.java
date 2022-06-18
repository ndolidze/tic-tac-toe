package ge.tsu.tictactoe;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Controller implements Initializable {

  private final Logger logger = LoggerFactory.getLogger(Controller.class);

  @FXML
  private Button button1;

  @FXML
  private Button button2;

  @FXML
  private Button button3;

  @FXML
  private Button button4;

  @FXML
  private Button button5;

  @FXML
  private Button button6;

  @FXML
  private Button button7;

  @FXML
  private Button button8;

  @FXML
  private Button button9;

  @FXML
  private Text winnerText;

  private int playerTurn = 0;

  ArrayList<Button> buttons;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    buttons = new ArrayList<>(
      Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8,
        button9));

    buttons.forEach(button -> {
      setupButton(button);
      button.setFocusTraversable(false);
    });
  }

  @FXML
  void restartGame(ActionEvent event) {
    buttons.forEach(this::resetButton);
    winnerText.setText("Tic-Tac-Toe");
  }

  public void resetButton(Button button) {
    button.setDisable(false);
    button.setText("");
  }

  private void setupButton(Button button) {
    button.setOnMouseClicked(mouseEvent -> {
      setPlayerSymbol(button);
      button.setDisable(true);
      try {
        checkIfGameIsOver();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void setPlayerSymbol(Button button) {
    button.setText(getButtonTextByPlayerTurn(playerTurn));
  }

  public void checkIfGameIsOver() throws SQLException {
    logger.info("Checking if game is over");
    String line = null;
    for (int a = 0; a < 8; a++) {
      switch (a) {
        case 0:
          line = button1.getText() + button2.getText() + button3.getText();
          break;
        case 1:
          line = button4.getText() + button5.getText() + button6.getText();
          break;
        case 2:
          line = button7.getText() + button8.getText() + button9.getText();
          break;
        case 3:
          line = button1.getText() + button5.getText() + button9.getText();
          break;
        case 4:
          line = button3.getText() + button5.getText() + button7.getText();
          break;
        case 5:
          line = button1.getText() + button4.getText() + button7.getText();
          break;
        case 6:
          line = button2.getText() + button5.getText() + button8.getText();
          break;
        case 7:
          line = button3.getText() + button6.getText() + button9.getText();
          break;
        default:
          line = null;
      }

      winnerText.setText(getWinnerTextByLine(line));
      if (!winnerText.getText().isEmpty()) {
        DatabaseConnectionThread dbThread = new DatabaseConnectionThread(winnerText.getText());
        dbThread.start();
      }

    }
  }

  public String getButtonTextByPlayerTurn(int turn) {
    if (turn % 2 == 0) {
      playerTurn = 1;
      return "X";
    } else {
      playerTurn = 0;
      return "O";
    }
  }

  public String getWinnerTextByLine(String line) {
    //X winner
    if (line.equals("XXX")) {
      return "X won!";
    }
    //O winner
    else if (line.equals("OOO")) {
      return "O won!";
    }
    return "";
  }
}