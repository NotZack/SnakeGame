/**
 * Snake2.0 written by Zackary Nichol Spring 2018
 */

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 
public class Main extends Application {

    public static Group root = new Group();

    /**
     * adds the snake food and scoreboard objects into the root node and then sets the scene and scene variables. Then starts the game loop
     */
    @Override
    public void start(Stage primaryStage) {
        
        Scene scene = new Scene(root, 200, 150, Color.BLACK);
        
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //closes the application correctly when exiting out
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        Board.setScene(scene);
        reInit();
        
        Movement.gameLoop(scene);
    }

    /**
     * reinitializes the snake, direction, food, and the scoreboard.
     */
    public static void reInit() {
        Movement.direction = "";
        Snake.dead = false;
        Snake.snakeChunks = new ArrayList<Rectangle>();
        populateChildren();
        Food.setFood();
    }
    
    /**
     * adds all the needed children into the root node to be drawn
     */
    private static void populateChildren() {
        root.getChildren().clear();
        root.getChildren().add(Snake.newSnakeChunk());
        root.getChildren().add(Food.newFood());
        root.getChildren().addAll(Scoreboard.initScoreboardText());
    }

    /**
     * launches the application calling the start method by default
     * @param args the default command line argument
     */
    public static void main(String[] args) {
        launch(args);
    }

}