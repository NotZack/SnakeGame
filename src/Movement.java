import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Movement {

    public static String direction = "";
    
    private static long snakeSpeed = 0_000_000;

    /**
     * The main game loop on every tick, draws, then updates, then checks for collisions.
     * @param scene, the only scene on the stage
     */
    public static void gameLoop(Scene scene) {
        moveSnake(scene);
        
        //timer runs constantly
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0 ;

            @Override
            public void handle(long frameTime) {
                if (frameTime - lastUpdate >= (100_000_000 - snakeSpeed) ) {
                        draw();
                        update();
                        checkCollision();
                        lastUpdate = frameTime;
                }
            }
        };
        timer.start();
    }

    /**
     * Gets key input sets the direction and then updates, draws, and checks collision in that direction. This is to disallow
     * the improper updating of keys.
     * @param scene, the only scene on the stage
     */
    public static void moveSnake(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (key.getCode()) {
                case UP: 
                    if (!direction.equals("down")) direction = "up"; draw(); update(); checkCollision(); break;
                case DOWN:
                    if (!direction.equals("up")) direction = "down"; draw(); update(); checkCollision(); break;
                case LEFT: 
                    if (!direction.equals("right")) direction = "left"; draw(); update(); checkCollision(); break;
                case RIGHT: 
                    if (!direction.equals("left")) direction = "right"; draw(); update(); checkCollision(); break;
                case ENTER: 
                    Main.reInit();
                    
                default: break;
            }
        });
    }
    
    /**
     * Updates the head of the snake's layout (its translation) based on the key being pressed
     */
    private static void update() {
        switch(direction) {
            case "up": 
                Snake.snakeChunks.get(0).setLayoutY(Snake.snakeChunks.get(0).getLayoutY() -25); break;
            case "down": 
                Snake.snakeChunks.get(0).setLayoutY(Snake.snakeChunks.get(0).getLayoutY() + 25); break;
            case "left": 
                Snake.snakeChunks.get(0).setLayoutX(Snake.snakeChunks.get(0).getLayoutX() -25); break;
            case "right": 
                Snake.snakeChunks.get(0).setLayoutX(Snake.snakeChunks.get(0).getLayoutX() + 25); break;
        }
    }

    /**
     * Starting at the last snakeChunk index, relocates the chunk to the next chunk. If it is the head of the snake
     * the head is updated only by its own layouts.
     */
    private static void draw() {
            for(int i = Snake.snakeChunks.size() - 1; i > -1; i--) {
                if(i == 0)
                    Snake.snakeChunks.get(i).relocate(Snake.snakeChunks.get(i).getX() + Snake.snakeChunks.get(i).getLayoutX(),
                                                      Snake.snakeChunks.get(i).getY() + Snake.snakeChunks.get(i).getLayoutY());
                else 
                    Snake.snakeChunks.get(i).relocate(Snake.snakeChunks.get(i - 1).getX() + Snake.snakeChunks.get(i - 1).getLayoutX(),
                                                      Snake.snakeChunks.get(i - 1).getY() + Snake.snakeChunks.get(i - 1).getLayoutY());
            }
    }
    
    /**
     * Checks if the snake head is colliding with the wall, food, or itself
     */
    private static void checkCollision() {
        double currentX = Snake.snakeChunks.get(0).getX() + Snake.snakeChunks.get(0).getLayoutX();
        double currentY = Snake.snakeChunks.get(0).getY() + Snake.snakeChunks.get(0).getLayoutY();
            
        //Wall collision
        if( (currentX <= Board.getXleftBoundary() - 25) ||
            (currentX >= Board.getXrightBoundary()) ||  
            (currentY <= Board.getYtopBoundary() - 25) ||
            (currentY >= Board.getYbottomBoundary() ) ) 
        {
            Main.reInit();
        }
        
        //Food collision
        if (currentX == Food.food.getX() && currentY == Food.food.getY()) {
            Main.root.getChildren().add(Snake.newSnakeChunk());
            Food.setFood();
            draw();
            Scoreboard.setSnakeText();
        }
        
        
        //Self collision iterates through every chunk of the snake and checks if it intersecting with the head
        for(int i = 2; i < Snake.snakeChunks.size(); i++) {
            if( (currentX == (Snake.snakeChunks.get(i).getX() + Snake.snakeChunks.get(i).getLayoutX())) && ( currentY == (Snake.snakeChunks.get(i).getY() + Snake.snakeChunks.get(i).getLayoutY()) ) ) {
                Main.reInit();
            }
        }
        
    }
    
    /**
     * Sets the snake chunk x or y +-25 based on the direction that the snake is moving in.
     * @return the new layout (translation)
     */
    public static double getDirectionOffset() {
        switch(direction) {
            case "up": 
                return Snake.snakeChunks.get(Snake.snakeChunks.size()-1).getLayoutY() + 25;
            case "down": 
                return Snake.snakeChunks.get(Snake.snakeChunks.size()-1).getLayoutY() - 25;
            case "left": 
                return Snake.snakeChunks.get(Snake.snakeChunks.size()-1).getLayoutX() + 25;
            case "right": 
                return Snake.snakeChunks.get(Snake.snakeChunks.size()-1).getLayoutX() - 25;
        }
        return 0.0;
    }

}
