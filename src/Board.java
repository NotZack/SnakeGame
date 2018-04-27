import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

class Board {
    static Board board = new Board();
	static Scene scene;
    
	/**
	 * @returns the left x boundary of the screen at fullscreen regardless of the window size
	 */
    public static double getXleftBoundary() {
        return scene.getX();
    }
    
    /**
     * @returns the right x boundary of the screen at fullscreen regardless of the window size
     */
    public static double getXrightBoundary() {
        return scene.getX() + scene.getWidth();
    }
    
    /**
     * @returns the left y boundary of the screen at fullscreen regardless of the window size
     */
    public static double getYtopBoundary() {
        return scene.getY();
    }
    
    /**
     * @returns the right y boundary of the screen at fullscreen regardless of the window size
     */
    public static double getYbottomBoundary() {
        return scene.getY() + scene.getHeight();
    }

    /**
     * @param The scene that contains the root node and all the children
     */
	public void setScene(Scene scene) {
		Board.scene = scene;
	}
	
	/**
     * @returns the screen height as an int
     */
    public static int getScreenHeight() {
        return (int) scene.getHeight();
    }
    
    /**
     * @returns the screen width as an int
     */
    public static int getScreenWidth() {
        return (int) scene.getWidth();
    }

}