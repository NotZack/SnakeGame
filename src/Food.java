import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Food extends Rectangle {
    public static int foodEaten = 0;

    public static void initFood(Rectangle food) {
        food.setX(getValidX(Main.screenWidth) * 25); 
        food.setY(getValidY(Main.screenHeight) * 25); 
        food.setWidth(25); 
        food.setHeight(25); 
        food.setFill(Color.ORANGE);
    }
    
    public static void setFood(Rectangle food) {
    	food.setX(getValidX(Main.screenWidth) * 25); 
        food.setY(getValidY(Main.screenHeight) * 25); 
    }
    
    public static void increaseFoodEaten() {
    	foodEaten += 1;
    }
    
    public static int getValidX(int width) {
    	Random rand = new Random();
    	int math =  rand.nextInt( (int) ((width / 25) - 2) ) + 1;
    	System.out.println(math);
		return math;
    }
    
    public static int getValidY(int height) {
    	Random rand = new Random();
    	int math = rand.nextInt( (int) ((height / 25) - 4) ) + 1;
    	System.out.println(math);
		return math;
    }
}