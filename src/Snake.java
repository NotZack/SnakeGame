import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Snake {
    static final int SNAKECHUNK = 25;
    static final int STARTINGX = 150;
    static final int STARTINGY = 75;
    
    public static void setSnake(Rectangle snake) {
        snake.setX(STARTINGX); 
        snake.setY(STARTINGY); 
        snake.setLayoutX(STARTINGX);
        snake.setLayoutY(STARTINGY);
        snake.setWidth(SNAKECHUNK + (SNAKECHUNK * Food.foodEaten)); 
        snake.setHeight(SNAKECHUNK); 
        snake.setFill(Color.GREEN);
    }
}