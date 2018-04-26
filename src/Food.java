import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Food extends Rectangle {

    static Rectangle food = new Rectangle();
    /**
     * Initializes the food rectangle, setting its x, y, width, height, and color.
     * @param food, the first and only food rectangle in the root node
     */
    public static void initFood() {
        food.setX(getValidXY(Board.getScreenWidth())); 
        food.setY(getValidXY(Board.getScreenHeight())); 
        food.setWidth(25); 
        food.setHeight(25); 
        food.setFill(Color.ORANGE);
    }
    
    /**
     * Calculates the possible x/y coordinates of the food rectanlge
     * @param widthHeight, depending on how its being called, either the width of the height of the screen.
     * @returns the amount of increments of 25 along the width or height of the screen that determines the x/y value of the food.
     * disallows the positions touching the wall.
     */
    public static int getValidXY(int widthHeight) {
        	Random rand = new Random();
        	int math =  (rand.nextInt( (int) ((widthHeight / 25) - 2) ) + 1) * 25;
		return math;
    }
}