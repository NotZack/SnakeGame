import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class Main extends Application {
    
    static Rectangle snake;
    static Rectangle food;
    static Group root; 
    static Text snakeLengthText;
    
    static int screenWidth = 800;
    static int screenHeight = 225;
    
    @Override
    public void start(Stage primaryStage) {
        
        snake = new Rectangle();
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
        
        screenHeight = (int) scene.getHeight();
        screenWidth = (int) scene.getWidth();
        
        //KeyListeners
        Movement.moveDirection(scene);
        Movement.drawMovement(scene);
    }
    
    public static void restart() {
    	Food.foodEaten = 0;
        Snake.snakeInit(snake);
        Movement.dead = false;
        Movement.enter = false; 
        root.getChildren().remove(3, root.getChildren().size());
        Scoreboard.setSnakeLengthText();
        snake.setTranslateX(0);
        snake.setTranslateY(0);
        System.out.println("ALIVE");
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}