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
        food.setHeight(25);
        food.setWidth(25);
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
     * @param either the width of height of the scree
     * @param xy either the x or y value of the food variable
     * @return
     */
    private static double getValidXY(double widthHeight, String xy) {
        Random rand = new Random();
        double validXY;

        while(true) {
            validXY =  (rand.nextInt( (int) ((widthHeight / 25) - 2) ) + 1) * 25;
            for(int i = 0; i < Snake.snakeChunks.size(); i++) {
                if (xy.equals("x"))
                    if (validXY == Snake.snakeChunks.get(i).getX() + Snake.snakeChunks.get(i).getLayoutX() )
                        continue;
                    else
                        return validXY;
                
                else if (xy.equals("y"))
                    if (validXY == Snake.snakeChunks.get(i).getY() + Snake.snakeChunks.get(i).getLayoutY() )
                        continue;
                    else
                        return validXY;
            }
        }
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
