import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        Pane[][] cells = new Pane[Constants.BOARD_X][Constants.BOARD_Y];

        // Populated `root` with an actual grid
        IntStream.range(0, Constants.BOARD_X).forEach(r -> {
            IntStream.range(0, Constants.BOARD_Y).forEach(c -> {
                cells[r][c] = new Pane();
                cells[r][c].setPrefSize(Constants.SCALE_X, Constants.SCALE_Y);
            });
            root.addRow(r, cells[r]);
        });

        // Defaults


        // Finish the graphical setup
        Scene scene = new Scene(root, Constants.SCREEN_X, Constants.SCREEN_Y);
        scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
