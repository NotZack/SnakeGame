import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food {

    public static Rectangle food;
    
    /**
     * Creates the new food object. Only called once
     * @returns the food rectangle
     */
    public static Rectangle newFood() {
        food = new Rectangle();
        food.setHeight(Board.chunkSize);
        food.setWidth(Board.chunkSize);
        return food;
    }

    /**
     * sets the food object x,y, and color
     */
    public static void setFood() {
        food.setX(getValidXY(Board.getWidth(), "x"));
        food.setY(getValidXY(Board.getHeight(), "y"));
        food.setFill(randomColor());
    }

    /**
     * Gets the x and y coordinates that are able to have food drawn to. Not outside the boundaries or in the snake.
     * @param widthHeight either the width of height of the screen
     * @param xy either the x or y value of the food variable
     * @return a valid x or y coordinate
     */
    private static double getValidXY(double widthHeight, String xy) {
        double validXY =  newPossibleXY(widthHeight);
        boolean valid = false;
        
        //checks the validity of the randomized possible food position
        while(!valid) {       
            for(int i = 0; i < Snake.snakeChunks.size(); i++) {
                if( (validXY == (Snake.snakeChunks.get(i).getX() + Snake.snakeChunks.get(i).getLayoutX())) && xy.equals("x") ) {
                    validXY = newPossibleXY(widthHeight);
                }
                else if ( (validXY == (Snake.snakeChunks.get(i).getY() + Snake.snakeChunks.get(i).getLayoutY())) && xy.equals("y") )
                    validXY = newPossibleXY(widthHeight);
                else {
                    valid = true;
                    return validXY;
                } 
            }
        }
        return validXY;
    }
    
    /**
     * randomizes a possible position for food
     * @param widthHeight either the width or height passed in from getValidXY
     * @return a semi-randomized double that represents a possible X/Y position for the food, valid or not
     */
    private static double newPossibleXY(double widthHeight) {
        if(((widthHeight / Board.chunkSize) - 2) > 0)
            return ((new Random().nextInt( (int) ((widthHeight / Board.chunkSize) - 2) ) + 1) * Board.chunkSize);
        else 
            return 0;
    }
    
    /**
     * randomizes a color
     * @returns the randomized color
     */
    public static Color randomColor() {
        Random rand = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();
        return Color.rgb(rand.nextInt(256), rand2.nextInt(256), rand3.nextInt(256));
    }

}
