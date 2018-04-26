import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Food extends Rectangle {

    public static void initFood(Rectangle food) {
        food.setX(getValidXY(Main.screenWidth) * 25); 
        food.setY(getValidXY(Main.screenHeight) * 25); 
        food.setWidth(25); 
        food.setHeight(25); 
        food.setFill(Color.ORANGE);
    }
    
    public static void setFood(Rectangle food) {
    	food.setX(getValidXY(Main.screenWidth) * 25); 
        food.setY(getValidXY(Main.screenHeight) * 25); 
    }
    
    public static int getValidXY(int widthHeight) {
    	Random rand = new Random();
    	int math =  rand.nextInt( (int) ((widthHeight / 25) - 2) ) + 1;
    	System.out.println(math);
		return math;
    }
}