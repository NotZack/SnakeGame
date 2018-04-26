import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Movement {
    
    public static boolean up, down, left, right, enter;
    
    static int xOffset = 0;
    static int yOffset = 0;
    
    //snakeSpeed is the 'percent' of the max snake speed
    static final int snakeSpeed = 25;

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
    
    public static void gameLoop(Scene scene) {
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long frameTime) {
                if(!dead) {
                    if (up) yOffset -= 25;
                    if (down) yOffset += 25;
                    if (right) xOffset += 25;
                    if (left) xOffset -= 25;
                    if (enter) Main.restart();
                    
                    //Movement speed
                    try { Thread.sleep(100 - snakeSpeed); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                    
                    checkCollision();
                    moveSnake(xOffset, yOffset);
                }
                else if (enter) 
                    Main.restart();
            }
        };
        timer.start();
    }
    
    private static void moveSnake(int xOffset, int yOffset) {
        if (!dead) {
            for(int i = Snake.combinedSnake.size() - 1; i > -1; i--) {
                if(i == 0) {
                	Snake.getSnakeHead().relocate(Snake.getSnakeHead().getX() + xOffset, Snake.getSnakeHead().getY() + yOffset);
                }
                else {
                    Snake.combinedSnake.get(i).relocate(Snake.combinedSnake.get(i - 1).getX() + Snake.combinedSnake.get(i - 1).getLayoutX(), Snake.combinedSnake.get(i - 1).getY() + Snake.combinedSnake.get(i - 1).getLayoutY());
                }
                
                
            }
        }
            
    }
    
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
            Scoreboard.setSnakeLengthText();
            System.out.println("DEAD");
        }
        else 
            dead = false;
        
        //Food collision
        if (currentX == Main.food.getX() && currentY == Main.food.getY()) {
    	    Rectangle snakeChunk = new Rectangle();
    	    Snake.setNewSnakeChunk(snakeChunk, getDirection());

    	    Food.setFood(Main.food);
    	    Scoreboard.setSnakeLengthText();
    	    System.out.println("EATEN");
        }
        
        //Self collision
        for(int i = 1; i < Snake.combinedSnake.size(); i++) {
        	if( (currentX == (Snake.combinedSnake.get(i).getX() + Snake.combinedSnake.get(i).getLayoutX())) && ( currentY == (Snake.combinedSnake.get(i).getY() + Snake.combinedSnake.get(i).getLayoutY()) ) ) {
        		up = false; down = false; left = false; right = false;
                dead = true;
                Scoreboard.setSnakeLengthText();
                System.out.println("DEAD");
        	}
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
