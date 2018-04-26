import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class Main extends Application {
    
    static Rectangle food;
    static Text snakeLengthText;
    static Group root;
    
    static int screenWidth = 800;
    static int screenHeight = 225;
    
    @Override
    public void start(Stage primaryStage) {
        
    	Board board = new Board();
    	Rectangle snake = new Rectangle();
    	food = new Rectangle();
         root = new Group(); 
        snakeLengthText = new Text();

        Snake.snakeInit(snake);
        Food.initFood(food);
        Scoreboard.initSnakeLengthText(snakeLengthText);
        
        root.getChildren().add(snake);
        root.getChildren().add(food);
        root.getChildren().add(snakeLengthText);
        
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        board.setScene(scene);
        
        //KeyListeners
        Movement.moveDirection(scene);
        Movement.gameLoop(scene);
    }
    
    private static void cleanup() {
    	Movement.xOffset = 0;
        Movement.yOffset = 0;
        Movement.dead = false;
        Movement.enter = false;
        
        Snake.getSnakeHead().setTranslateX(0);
        Snake.getSnakeHead().setTranslateY(0);
        
        root.getChildren().remove(3, root.getChildren().size());
    }
    
    public static void restart() {
        cleanup();
    	Snake.snakeInit(Snake.getSnakeHead());
    	Scoreboard.setSnakeLengthText();
        System.out.println("ALIVE");
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}