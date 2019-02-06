import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
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

        // Startup
        Game mainframe = new Game(cells);
        mainframe.refresh();

        // Finish the graphical setup
        Scene scene = new Scene(root, Constants.SCREEN_X, Constants.SCREEN_Y);
        scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();

        // For glory?
        mainframe.run();

        // Keybinds
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> mainframe.onKeyPressed(event.getCode()));
    }

    //endgame method
    //TODO edit the endgame scene rn:it's blank
    public static void endgame(Stage primaryStage)
    {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, Constants.SCREEN_X, Constants.SCREEN_Y);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
