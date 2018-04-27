import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Movement {
    
    public static boolean up, down, left, right, enter;
    
    //The x and y offset of the snake from the initial x and y coordinates
    static int xOffset = 0;
    static int yOffset = 0;
    
    //snakeSpeed is the 'percent' of the max snake speed
    static long snakeSpeed = 25_000_000;

    //If the snake had died
    public static boolean dead = false;
    
    /**
     * Sets an event listener that detects if a key is pressed. If an arrow key is pressed than the corresponding 
     * direction variable is set to true and the others set to false. The key pressed is not set to true if the 
     * opposite direction to that key is set to true (no backwards). If enter is pressed the game resets.
     * 
     * @param scene, to set the event handler to
     */
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
    
    /**
     * Declares an animation timer that runs for every 100_000_000 - snakeSpeed milliseconds. Checks if direction key and enter key
     * values are true, then increases the corresponding xOffset/yOffset or, if enter is true, reInits. Then calls the update methods
     * checkCollision, and moveSnake.
     *@param frameTime, the timestamp of the current frame
     */
    public static void gameLoop() {
        
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0 ;
            
            @Override
            public void handle(long frameTime) {
                if (frameTime - lastUpdate >= (100_000_000 - snakeSpeed)) {
                    if(!dead) {
                        if (up) yOffset -= 25;
                        if (down) yOffset += 25;
                        if (right) xOffset += 25;
                        if (left) xOffset -= 25;
                        if (enter) Main.reInit();
                        
                        checkCollision();
                        moveSnake(xOffset, yOffset);
                    }
                    else if (enter) 
                        Main.reInit();
                    
                    lastUpdate = frameTime;
                }
                
            }
        };
        timer.start();
    }
    /**
     * Defines a for loop that starts from the last index (last chunk) of the snake, relocating it to the previous chunk of the snake
     * by adding the previous chunk's x/y and layoutX/layoutY.
     * @param xOffset, the x offset from initial x coordinate
     * @param yOffset, the y offset from initial y coordinate
     */
    private static void moveSnake(int xOffset, int yOffset) {
        if (!dead) {
            for(int i = Snake.combinedSnake.size() - 1; i > -1; i--) {
                if(i == 0) 
                	    Snake.getSnakeHead().relocate(Snake.getSnakeHead().getX() + xOffset, Snake.getSnakeHead().getY() + yOffset);
                else 
                    Snake.combinedSnake.get(i).relocate(Snake.combinedSnake.get(i - 1).getX() + Snake.combinedSnake.get(i - 1).getLayoutX(), Snake.combinedSnake.get(i - 1).getY() + Snake.combinedSnake.get(i - 1).getLayoutY());
            }
        }
            
    }
    
    /**
     * Gets the head of the snake's x and y coordinates and checks against the collision objects: the wall, the food, and the snake
     * itself.
     */
    private static void checkCollision() {
        	double currentX = Snake.getSnakeHead().getX() + Snake.getSnakeHead().getLayoutX();
        	double currentY = Snake.getSnakeHead().getY() + Snake.getSnakeHead().getLayoutY();
        	
        	//Wall collision
        if( (currentX <= Board.getXleftBoundary()) ||
            (currentX >= Board.getXrightBoundary() - 50) ||  
            (currentY <= Board.getYtopBoundary()) ||
            (currentY >= Board.getYbottomBoundary() - 50 ) ) 
        {
            up = false; down = false; left = false; right = false;
            dead = true;
            //Scoreboard.setSnakeLengthText();
        }
        else 
            dead = false;
        
        //Food collision
        if (currentX == Food.food.getX() && currentY == Food.food.getY()) {
        	    Rectangle snakeChunk = new Rectangle();
        	    Snake.setNewSnakeChunk(snakeChunk, getDirection());
    
        	    Food.initFood();
        	    //Scoreboard.setSnakeLengthText();
        }
        
        //Self collision iterates through every chunk of the snake and checks if it intersecting with the head
        for(int i = 1; i < Snake.combinedSnake.size(); i++) {
            	if( (currentX == (Snake.combinedSnake.get(i).getX() + Snake.combinedSnake.get(i).getLayoutX())) && ( currentY == (Snake.combinedSnake.get(i).getY() + Snake.combinedSnake.get(i).getLayoutY()) ) ) {
            		up = false; down = false; left = false; right = false;
                dead = true;
                //Scoreboard.setSnakeLengthText();
            	}
        }
    }
    /**
     * @returns a string representation of the direction that the head is going in.
     */
    public static String getDirection() {
        if(up) return "Up";
        else if(down) return "Down";
        else if(left) return "Left";
        else if(right) return "Right";
        else return "N/A";
    }
}
