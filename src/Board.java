import javafx.scene.Scene;

class Board {
	
	static Scene scene;
    
    public static double getXleftBoundary() {
        return scene.getX();
    }
    
    public static double getXrightBoundary() {
        return scene.getX() + scene.getWidth();
    }
    
    public static double getYtopBoundary() {
        return scene.getY();
    }
    
    public static double getYbottomBoundary() {
        return scene.getY() + scene.getHeight();
    }

	public void setScene(Scene scene) {
		Board.scene = scene;
	}
}