import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake {
    //List contains all of the snake chunks
    public static ArrayList<Rectangle> snakeChunks = new ArrayList<Rectangle>();
    
    private static final double STARTINGX = 200;
    private static final double STARTINGY = 200;

    //creates a new snake chunk
    public static Rectangle newSnakeChunk() {
        Rectangle chunk = new Rectangle();
        snakeChunks.add(chunk);
        chunkInit(chunk);
        return chunk;
    }
    
    //sets chunk variables
    public static void chunkInit(Rectangle chunk) {
        if(snakeChunks.indexOf(chunk) == 0) {
            chunk.setLayoutX(0);
            chunk.setLayoutY(0);
            chunk.setFill(Color.GREEN);
        }
        else {
            chunk.setLayoutX(Movement.getDirectionOffset());
            chunk.setLayoutY(Movement.getDirectionOffset());
            chunk.setFill(Food.food.getFill());
        }
        
        chunk.setX(STARTINGX);
        chunk.setY(STARTINGY);
        chunk.setWidth(25);
        chunk.setHeight(25);
        
    }

}
