import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Snake {
    static final int SNAKECHUNK = 25;
    static final int STARTINGX = 150;
    static final int STARTINGY = 75;

    static Rectangle snakeHead = new Rectangle();
    
    //A list containing every chunk of the snake including the head
    static ArrayList<Rectangle> combinedSnake = new ArrayList<Rectangle>(); 
    
    /**
     * Initializes the snake variables. Resets combinedSnake because this method is only called when starting or restarting.
     * @param the head of the snake that will always be the first index of combinedSnake.
     */
    public static void snakeInit(Rectangle snake) {
        snake.setX(STARTINGX); 
        snake.setY(STARTINGY); 
        snake.setLayoutX(STARTINGX);
        snake.setLayoutY(STARTINGY);
        snake.setWidth(SNAKECHUNK); 
        snake.setHeight(SNAKECHUNK); 
        snake.setFill(Color.GREEN);
        combinedSnake = new ArrayList<Rectangle>();
        combinedSnake.add(snake);
    }
    
    /**
     * Creates a new snake chunk from a rectangle in the correct direction from the last index of the combinedSnake list. 
     * It then sets the chunk's variables and adds it to the root node. Also increases snake speed.
     * @param chunk, the rectangle that is added to the snake as a chunk
     * @param direction, the direction that the chunk is added on to
     */
    public static void setNewSnakeChunk(Rectangle chunk, String direction) {
        int directionYmod = 0;
        int directionXmod = 0;
        
        switch(direction) {
            case "Up": directionYmod = 25; directionXmod = 0; break;   
            case "Down": directionYmod = -25; directionXmod = 0; break;
            case "Left": directionXmod = 25; directionYmod = 0; break;
            case "Right": directionXmod = -25; directionYmod = 0; break;
        }
        
        chunk.setTranslateX(0);
        chunk.setTranslateY(0);
        chunk.setX(combinedSnake.get(combinedSnake.size()-1).getX() + directionXmod); 
        chunk.setY(combinedSnake.get(combinedSnake.size()-1).getY() + directionYmod); 
        chunk.setWidth(SNAKECHUNK); 
        chunk.setHeight(SNAKECHUNK); 
        chunk.setFill(Food.randomColor);
	    combinedSnake.add(chunk);
	    
	    Main.root.getChildren().add(chunk);
	    
	    if(combinedSnake.size() % 5 == 0)
	        Movement.snakeSpeed += 5_000_000;
       
    }

    /**
     * @returns the length of the snake from the combinedSnake list
     */
    public static int getLength() {
    	    return combinedSnake.size();
    }

    /**
     * @returns if not set, the first index of combinedSnake is set to snake head, and then returns the first index.
     */
    public static Rectangle getSnakeHead() {
        if (combinedSnake.isEmpty()) 
            combinedSnake.add(snakeHead);
    	    return combinedSnake.get(0);
    	}
}