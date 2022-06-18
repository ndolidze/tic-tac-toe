package ge.tsu.tictactoe;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

  private final Logger logger = LoggerFactory.getLogger(Main.class);

  @Override
  public void start(Stage primaryStage) {
    try {
      logger.info("Starting game");
      Parent root = FXMLLoader.load(getClass().getResource("tic-tac-toe.fxml"));
      primaryStage.setTitle("Tic Tac Toe");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
    } catch (Exception e) {
      logger.error("Unable to load game {}", e.getMessage());
    }
  }


  public static void main(String[] args) {
    launch(args);
  }
}