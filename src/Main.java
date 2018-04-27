import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 
public class Main extends Application {

    static Group root = new Group();

    /**
     * adds the snake food and scoreboard objects into the root node and then sets the scene and scene variables. Then starts the game loop
     */
    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(Snake.newSnakeChunk());
        root.getChildren().add(Food.newFood());
        root.getChildren().add(Scoreboard.initSnakeText());
        
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        
        Board.setScene(scene);
        Food.setFood();
        
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();

        Movement.gameLoop(scene);
    }

    /**
     * reinitializes the snake, direction, food, and the scoreboard.
     */
    public static void reInit() {
        Movement.direction = "";
        Snake.snakeChunks = new ArrayList<Rectangle>();
        root.getChildren().clear();
        root.getChildren().add(Snake.newSnakeChunk());
        root.getChildren().add(Food.newFood());
        root.getChildren().add(Scoreboard.initSnakeText());
        Food.setFood();
        
    }

    /**
     * launches the application calling the start method by default
     * @param args the default command line argument
     */
    public static void main(String[] args) {
        launch(args);
    }

}