import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake {
    
    //List contains all of the snake chunks
    public static ArrayList<Rectangle> snakeChunks = new ArrayList<Rectangle>();
    public static boolean dead = false;

    /**
     * creates a new rectangle, adds it to the chunks list, then calls the initialize
     * @returns the chunk to be initialized and added to the list
     */
    public static Rectangle newSnakeChunk() {
        Rectangle chunk = new Rectangle();
        snakeChunks.add(chunk);
        chunkInit(chunk);
        return chunk;
    }
    
    /**
     * sets the initial coordinates etc. of the chunk
     * @param the rectangle that is a chunk of the snake
     */
    public static void chunkInit(Rectangle chunk) {
        if (snakeChunks.indexOf(chunk) == 0) 
            chunk.setFill(Color.GREEN);
        else 
            chunk.setFill(Food.food.getFill());
        
        chunk.setLayoutX(Movement.getDirectionOffset());
        chunk.setLayoutY(Movement.getDirectionOffset());
        
        chunk.setX(Board.chunkSize * new Random().nextInt((int) (Board.getWidth() / Board.chunkSize)));
        chunk.setY(Board.chunkSize * new Random().nextInt((int) (Board.getHeight() / Board.chunkSize)));

        chunk.setWidth(Board.chunkSize);
        chunk.setHeight(Board.chunkSize);
        
        //sees if this is has been the longest snake
        Scoreboard.setHighScore();
    }

}
