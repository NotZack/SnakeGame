import javafx.scene.Scene;

public class Board {

    private static Scene scene;
    
    //the lower the number the harder it is; the smaller the snake is
    public static int difficulty = 3;
    public static double chunkSize = 25;
    
    public static void setScene(Scene scene) {
        Board.scene = scene;
        getChunkSize();
    }
    
    public static double getWidth() {
        if(scene != null)
            return scene.getWidth();
        else 
            return 800;
    }

    public static double getHeight() {
        if(scene != null)
            return scene.getHeight();
        else
            return 600;
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
     * sets the optimal chunk size for the snake
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

}
