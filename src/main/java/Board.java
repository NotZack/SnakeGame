import javafx.scene.Scene;

public class Board implements sceneInterface {

    private static Scene scene;
    
    //the lower the number the harder it is; the smaller the snake is maxes out at 9
    public static int difficulty = 1;
    public static double chunkSize = 25;
    
    /**
     * sets a local scene to be used for bordering and chunks
     * @param scene, the one and only scene
     */
    public static void setScene(Scene scene) {
        Board.scene = scene;
        getChunkSize();
    }
    
    public static double getWidth() {
        return scene.getWidth();
    }

    public static double getHeight() {
        return scene.getHeight();
    }

    public static double getXleftBoundary() {
        return 0;
    }

    public static double getXrightBoundary() {
        return scene.getWidth();
    }

    public static double getYtopBoundary() {
        return 0;
    }

    public static double getYbottomBoundary() {
        return scene.getHeight();
    }
    
    /**
     * sets the optimal chunk size for the snake based of of the screen size
     */
    private static void getChunkSize() {
        boolean acceptableChunk = false;
        chunkSize = 25;
        
        while(!acceptableChunk) {
            if(getWidth() % chunkSize <= 1) {
                chunkSize = chunkSize * difficulty;
                acceptableChunk = true;
            } 
            else 
                chunkSize --;
        }
    }

    /**
     * freezes the game when snake dies. Then adds the death text to the root node
     * @param deathType
     */
    public static void gameOverScene(String deathType) {
        Snake.dead = true;
        Main.root.getChildren().add(Scoreboard.setDeathText(deathType));
    }

}
