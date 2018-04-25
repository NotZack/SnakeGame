import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Movement {
    
    public static boolean up, down, left, right, enter;
    
    static int xOffset = 0;
    static int yOffset = 0;
    
    //snakeSpeed is the 'percent' of the max snake speed
    static final int snakeSpeed = 25;
    
    static final int STARTINGX = 150;
    static final int STARTINGY = 75;

    public static boolean dead = false;
    
    public static void moveDirection(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(!dead) {
                switch (key.getCode()) {
                    case UP: if(!down) {up = true; down = false;}left = false; right = false; break;
                    case DOWN: if(!up) {down = true; up = false;} left = false; right = false; break;
                    case LEFT: if(!right) {left = true; right = false;} up = false; down = false; break;
                    case RIGHT: if(!left) {right = true; left = false;} up = false; down = false;  break;
                    case ENTER: enter = true; break;
                    default: break;
                }
            }
            else {
                switch (key.getCode()) {
                    case ENTER: enter = true; break;
                    default: break;
                }
            }
        });
    }
    
    public static void drawMovement(Scene scene) {
        int oldXOffset = xOffset;
        int oldYOffset = yOffset;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!dead) {
                    if (up) yOffset -= 25;
                    if (down) yOffset += 25;
                    if (right) xOffset += 25;
                    if (left)  xOffset -= 25;
                    if (enter) {
                        Main.restart();
                        xOffset = 0;
                        yOffset = 0;
                    }
                    
                    //Movement speed
                    try {
						Thread.sleep(100 - snakeSpeed);
					} 
                    catch (InterruptedException e) {
						e.printStackTrace();
					}
                    
                    checkCollision(scene);
                    moveSnake(xOffset, yOffset, oldXOffset, oldYOffset);
                }
                else {
                    if (enter) {
                        Main.restart();
                        xOffset = 0;
                        yOffset = 0;
                    }
                }
            }
        };
        timer.start();
    }
    
    private static void moveSnake(int xOffset, int yOffset, int oldXOffset, int oldYOffset) {
        double oldX = 0; double oldY = 0;
        if (!dead) {
            for(int i = Snake.combinedSnake.size() - 1; i > -1; i--) {
                if(i == 0) {
                    oldX = Snake.combinedSnake.get(i).getX() + oldXOffset;
                    oldY = Snake.combinedSnake.get(i).getY() + oldYOffset;
                    Snake.combinedSnake.get(i).relocate(Snake.combinedSnake.get(i).getX() + xOffset, Snake.combinedSnake.get(i).getY() + yOffset);
                }
                //Snake.combinedSnake.get(i).getX() + xOffset
                else {
                    //Snake.combinedSnake.get(i).relocate(Snake.combinedSnake.get(i + 1).getX() + xOffset, Snake.combinedSnake.get(i).getY() + yOffset);
                }
                //oldX = oldX + oldXOffset;
                //oldY = oldY + oldYOffset;
                
            }
        }
            
    }
    
    private static void checkCollision(Scene scene) {
    	    double currentX = Main.snake.getX() + Main.snake.getLayoutX();
    	    double currentY = Main.snake.getY() + Main.snake.getLayoutY();
    	
    	    //Wall collision
        if( (currentX <= Board.getXleftBoundary(scene)) ||
            (currentX >= Board.getXrightBoundary(scene) - 50) ||  
            (currentY <= Board.getYtopBoundary(scene)) ||
            (currentY >= Board.getYbottomBoundary(scene) - 50 ) ) 
        {
            up = false; down = false; left = false; right = false;
            dead = true;
            System.out.println("DEAD");
        }
        else 
            dead = false;
        
        //Food collision
        if (currentX == Main.food.getX() && currentY == Main.food.getY()) {
        	    Rectangle snakeChunk = new Rectangle();
        	    Snake.setNewSnakeChunk(snakeChunk, getDirection());
        	
        	    Food.increaseFoodEaten();
        	    Food.setFood(Main.food);
        	    System.out.println("EATEN");
        }
    }
    
    public static String getDirection() {
        if(up) return "Up";
        else if(down) return "Down";
        else if(left) return "Left";
        else if(right) return "Right";
        else return "N/A";
    }
}
