import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Movement {
    
    public static boolean up, down, left, right, enter;
    
    static int xOffset = 0;
    static int yOffset = 0;
    
    static final int snakeSpeed = 5;
    
    static final int STARTINGX = 150;
    static final int STARTINGY = 75;

    public static boolean dead = false;
    
    public static void moveDirection(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(!dead) {
                switch (key.getCode()) {
                    case UP: up = true; break;
                    case DOWN: down = true; break;
                    case LEFT: left = true; break;
                    case RIGHT: right = true; break;
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
        
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if(!dead) {
                switch (key.getCode()) {
                    case UP:    up = false; break;
                    case DOWN:  down = false; break;
                    case LEFT:  left = false; break;
                    case RIGHT: right = false; break;
                    default: break;
                }
            }
        });
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
        if( (Main.snake.getX() + Main.snake.getLayoutX()) <= Board.getXleftBoundary(scene) ||
            (Main.snake.getX() + Main.snake.getLayoutX()) >= Board.getXrightBoundary(scene) ||  
            (Main.snake.getY() + Main.snake.getLayoutY()) <= Board.getYtopBoundary(scene) ||
            ((Main.snake.getY() + Main.snake.getLayoutY()) - 25) >= Board.getYbottomBoundary(scene) ) 
        {
            
            up = false; down = false; left = false; right = false;
            dead = true;
            System.out.println("DEAD");
        }
        else {
            dead = false;
        }
    }
}
