import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            //TODO You can change the Parent if you want, sample.fxml is the css
            //TODO Make the number of boxes dynamic(We have a height and weight constant)
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(root, Constants.WIDTH2, Constants.HEIGHT2));
        primaryStage.show();

        //Game is here so yea :D
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //TODO implement logic for game here
            }
        };
        gameLoop.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
