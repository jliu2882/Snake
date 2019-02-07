import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main extends Application {
    public static Stage stage;
    public static Game game;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
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

        // Here you go jack
        this.game = mainframe;

        // Keybinds
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> mainframe.onKeyPressed(event.getCode()));
    }

    //endgame method
    public static void endgame(Stage primaryStage)
    {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, Constants.SCREEN_X, Constants.SCREEN_Y);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            Backend.write(new ArrayList<>(Arrays.asList(game.getUsername(), String.valueOf(game.getScore()))));
        } catch (IOException err) {
            err.printStackTrace();
        }

        GridPane highScores = new GridPane();
        highScores.setHgap(10);
        highScores.setVgap(10);
        highScores.setAlignment(Pos.CENTER);

        // TODO: Could be fetched through CSVUtilities
        Label usernameHeader = new Label();
        usernameHeader.setText("Username");

        Label scoreHeader = new Label();
        scoreHeader.setText("Score");

        highScores.addRow(0, usernameHeader, scoreHeader);

        List<List<String>> scores = Backend.read()
                .stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(s -> Integer.parseInt(s.get(1)))))
                .collect(Collectors.toList());

        IntStream.range(0, Constants.MAX_SCORE_LISTING).forEach(i -> {
            if (i < scores.size()) {
                List<String> user = scores.get(i);

                Label usernameLabel = new Label();
                usernameLabel.setText(user.get(0));

                Label scoreLabel = new Label();
                scoreLabel.setText(user.get(1));

                highScores.addRow(i + 1, usernameLabel, scoreLabel);
            }
        });
        root.getChildren().add(highScores);
    }

}
