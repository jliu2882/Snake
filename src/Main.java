import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
        startGame(primaryStage);
    }
    public static void startGame(Stage primaryStage){
        //creates a scene for the start screen
        GridPane root0 = new GridPane();
        Scene scene0 = new Scene(root0,Constants.SCREEN_X, Constants.SCREEN_Y);
        root0.setAlignment(Pos.CENTER);

        //sets a text field to take username
        Text text = new Text(0, 0, "Enter a name to start. The game "
                      + System.lineSeparator() + " will get harder as your snake gets longer."+ System.lineSeparator() + "Use the arrow keys to move" +
                System.lineSeparator() + "Hold down the key to go faster");
        TextField uname = new TextField();
        uname.setPromptText("Enter your username here");//shouldn't be seen but user may mess up life so
        root0.add(uname,1,1);
        root0.add(text,0,1);

        //sets the scene and displays it
        primaryStage.setTitle("Welcome to Snake");
        primaryStage.setScene(scene0);
        primaryStage.show();

        //waits until user presses enter to start game
        scene0.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                //sets a username; probably makes edmund sad
                Game.username = (uname.getText().equals("")) ? Constants.DEFAULT_USERNAME : uname.getText();

                //creates a new scene for the main game
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
                scene.getStylesheets().add(Main.class.getResource("styles.css").toExternalForm());
                primaryStage.setTitle("Snake");
                primaryStage.setScene(scene);
                primaryStage.show();

                // For glory?
                mainframe.run();

                // Here you go jack < :) >
                game = mainframe;

                // Keybinds
                scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> mainframe.onKeyPressed(event.getCode()));
            }
        });
    }
    //endgame method
    public static void endgame(Stage primaryStage)
    {
        //sets up the scene for the leaderboard
        GridPane root = new GridPane();
        Scene scene = new Scene(root, Constants.SCREEN_X, Constants.SCREEN_Y);
        primaryStage.setTitle("Leaderboard");
        primaryStage.setScene(scene);
        primaryStage.show();

        //adds score the csv file
        try {
            Backend.write(new ArrayList<>(Arrays.asList(game.getUsername(), String.valueOf(game.getLength()))));
        } catch (IOException err) {
            err.printStackTrace();
        }

        //sets up gridpane for the highscores and populates it; too lazy to comment the rest
        //TODO comment the rest
        GridPane highScores = new GridPane();
        highScores.setHgap(10);
        highScores.setVgap(10);
        highScores.setAlignment(Pos.CENTER);

        // TODO: Could be fetched through CSVUtilities
        Label usernameHeader = new Label();
        usernameHeader.setText("Username");

        Label scoreHeader = new Label();
        scoreHeader.setText("Length");

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

        GridPane restart = new GridPane();
        restart.setHgap(10);
        restart.setVgap(10);
        restart.setAlignment(Pos.CENTER);

        Button restartButton = new Button("Restart Game");

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                startGame(primaryStage);
            }
        });

        restart.add(restartButton,0,0);
        root.getChildren().add(restart);
        root.add(highScores,0,1);
    }

}
