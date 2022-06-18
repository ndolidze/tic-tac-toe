module ge.tsu.tictactoe {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires validatorfx;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires org.slf4j;
  requires java.sql;

  opens ge.tsu.tictactoe to javafx.fxml;
  exports ge.tsu.tictactoe;
}