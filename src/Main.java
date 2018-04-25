import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
 
public class Main extends Application {
    
    static Rectangle snake;
    static Rectangle food;
    Group root; 
    
    static int screenWidth = 800;
    static int screenHeight = 225;
    
    @Override
    public void start(Stage primaryStage) {
        
        snake = new Rectangle();
        food = new Rectangle();
        root = new Group(); 

        Snake.setSnake(snake, Food.foodEaten);
        Food.initFood(food);
        
        root.getChildren().add(snake);
        root.getChildren().add(food);
        
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
        Snake.setSnake(snake, Food.foodEaten);
        Movement.dead = false;
        Movement.enter = false; 
        snake.setTranslateX(0);
        snake.setTranslateY(0);
        System.out.println("ALIVE");
    }
 
    public static void main(String[] args) {
        launch(args);
    }

}