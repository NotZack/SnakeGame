import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Snake {
    static final int SNAKECHUNK = 25;
    static final int STARTINGX = 150;
    static final int STARTINGY = 75;
    static int chunkAmount = 1;
    
    static ArrayList<Rectangle> combinedSnake = new ArrayList<Rectangle>(); 
    
    public static void setSnake(Rectangle snake, int foodEaten) {
        snake.setX(STARTINGX); 
        snake.setY(STARTINGY); 
        snake.setLayoutX(STARTINGX);
        snake.setLayoutY(STARTINGY);
        snake.setWidth(SNAKECHUNK); 
        snake.setHeight(SNAKECHUNK); 
        snake.setFill(Color.GREEN);
        
        if(combinedSnake.contains(snake)) {
        	return;
        }
        else {
        	combinedSnake.add(snake);
        	System.out.println(combinedSnake);
        }
    }
}