import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Food extends Rectangle {
    public static int foodEaten = 2;

    public static void setFood(Rectangle food) {
        food.setX(150); 
        food.setY(75); 
        food.setWidth(25); 
        food.setHeight(25); 
        food.setFill(Color.ORANGE);
    }
}