import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Snake {
    static final int SNAKECHUNK = 25;
    static final int STARTINGX = 150;
    static final int STARTINGY = 75;
    static int chunkAmount = 1;
    
    static ArrayList<Rectangle> combinedSnake = new ArrayList<Rectangle>(); 
    
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
    
    public static void setNewSnakeChunk(Rectangle chunk, String direction) {
        int directionYmod = 0;
        int directionXmod = 0;
        System.out.println(direction);
        
        switch(direction) {
            case "Up": directionYmod = 25; directionXmod = 0; break;   
            case "Down": directionYmod = -25; directionXmod = 0; break;
            case "Left": directionXmod = 25; directionYmod = 0; break;
            case "Right": directionXmod = -25; directionYmod = 0; break;
        }
        
        System.out.println(combinedSnake.get(combinedSnake.size()-1).getX());
        chunk.setTranslateX(0);
        chunk.setTranslateY(0);
        chunk.setX(combinedSnake.get(combinedSnake.size()-1).getX() + directionXmod); 
        chunk.setY(combinedSnake.get(combinedSnake.size()-1).getY() + directionYmod); 
        chunk.setWidth(SNAKECHUNK); 
        chunk.setHeight(SNAKECHUNK); 
        chunk.setFill(Color.BLUE);
    	    combinedSnake.add(chunk);
    	    
    	    Main.root.getChildren().add(chunk);
       
    }

    public static void increaseLength() {
        
        
    }
}