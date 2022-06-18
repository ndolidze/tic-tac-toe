package ge.tsu.tictactoe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionThread extends Thread {

  private String result;

  public DatabaseConnectionThread(String result) {
    this.result = result;
  }

  @Override
  public void run() {
    Connection conn = DatabaseConnection.getConnection();
    Statement statement = null;
    try {
      statement = conn.createStatement();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    int wins = 0;
    if (result.equals("X won!")) {
      ResultSet resultSet = null;
      try {
        resultSet = statement.executeQuery("SELECT wins FROM GAME_RESULT where gamer='X';");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      try {
        if (resultSet != null && resultSet.next()) {
          wins = resultSet.getObject("WINS", Integer.class) + 1;
          statement.execute(
            String.format("update public.game_result set wins=%d where gamer='X';", wins));
        } else {
          wins += 1;
          statement.execute(
            String.format("Insert into  public.game_result(gamer,wins) values ('X',%d);", wins));
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

    } else if (result.equals("O won!")) {
      ResultSet resultSet = null;
      try {
        resultSet = statement.executeQuery(
          "SELECT wins FROM public.game_result where gamer='O';");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      try {
        if (resultSet != null && resultSet.next()) {
          wins = resultSet.getObject("WINS", Integer.class) + 1;
          statement.execute(
            String.format("update public.game_result set wins=%d where gamer='O';", wins));
        } else {
          wins += 1;
          statement.execute(
            String.format("Insert into  public.game_result(gamer,wins) values ('O',%d);", wins));
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
