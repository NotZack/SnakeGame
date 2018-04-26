import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class Main extends Application {
    
    //Fields because food, root, and snakeLengthText need to be updated from other classes
    //static Rectangle food;
    static Group root;
    
    /**
     * The first thing that is ran during the application startup. Sets the first objects for their corresponding classes and adds them
     * to the root node. Sets the screen values, then inits snake and food with reInit. Finally then calls gameLoop to update the screen
     */
    @Override
    public void start(Stage primaryStage) {
        	// Move to Scoreboard class -> Text snakeLengthText = new Text();
        root = new Group(); 
        root.getChildren().add(Snake.getSnakeHead());
        root.getChildren().add(Food.food);
        //root.getChildren().add(snakeLengthText);
        
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Board.board.setScene(scene);
        
        reInit();
        Movement.moveDirection(scene);
        Movement.gameLoop();
    }
    
    /**
     * Resets Movement variables and dead variable so that the snake can be properly reinitialized by restart(). Removes the children
     * from the root node starting with index three, because indexes 0 - 3 contain food, snake head, and scoreboard text.
     */
    private static void cleanup() {
        Movement.xOffset = 0;
        Movement.yOffset = 0;
        Movement.dead = false;
        Movement.enter = false;
        Movement.snakeSpeed = 25_000_000;
        
        Snake.getSnakeHead().setTranslateX(0);
        Snake.getSnakeHead().setTranslateY(0);
        
        //Start at 3 to include text
        root.getChildren().remove(2, root.getChildren().size());
    }
    
    /**
     * Calls the cleanup method to reset all need variables, then reinitializes the snake head back to its original position, and 
     * scoreboard back to its initial value.
     */
    public static void reInit() {
        Snake.snakeInit(Snake.getSnakeHead());
        Food.initFood();
        cleanup();
        
        	//Scoreboard.setSnakeLengthText();
    }
 
    /**
     * launch(args) creates a new application that calls the start() method. Returns and closes the thread once exited out of.
     * @param args, command line argument
     */
    public static void main(String[] args) {
        launch(args);
    }

}