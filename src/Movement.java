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
                    case UP: up = true; down = false; left = false; right = false; break;
                    case DOWN: down = true; up = false; left = false; right = false; break;
                    case LEFT: left = true; up = false; down = false; right = false; break;
                    case RIGHT: right = true; up = false; down = false; left = false; break;
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
        
        /*scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if(!dead) {
                switch (key.getCode()) {
                    case UP: up = false; break;
                    case DOWN: down = false; break;
                    case LEFT: left = false; break;
                    case RIGHT:right = false; break;
                    default: break;
                }
            }
        });*/
    }
    
    public static void drawMovement(Scene scene) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!dead) {
                    if (up) yOffset -= 25f;
                    if (down) yOffset += 25f;
                    if (right) xOffset += 25f;
                    if (left)  xOffset -= 25f;
                    if (enter) {
                        Main.restart();
                        xOffset = 0;
                        yOffset = 0;
                    }
                    
                    try {
						Thread.sleep(100 - snakeSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    
                    checkCollision(scene);
                    moveSnake(xOffset, yOffset);
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
    
    private static void moveSnake(float xOffset, float yOffset) {
        if (!dead)
            Main.snake.relocate(STARTINGX + xOffset, STARTINGY + yOffset);
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
        if( currentX == Main.food.getX() && currentY == Main.food.getY()) {
        	Food.increaseFoodEaten();
        	
        	Rectangle snakeChunk = new Rectangle();
        	Snake.setSnake(snakeChunk, Food.foodEaten);
        	
        	Food.setFood(Main.food);
        	System.out.println("EATEN");
        }
    }
}
