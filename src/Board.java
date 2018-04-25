import javafx.scene.Scene;

class Board {
    
    public static double getXleftBoundary(Scene scene) {
        return scene.getX();
    }
    
    public static double getXrightBoundary(Scene scene) {
        return scene.getX() + scene.getWidth();
    }
    
    public static double getYtopBoundary(Scene scene) {
        return scene.getY();
    }
    
    public static double getYbottomBoundary(Scene scene) {
        return scene.getY() + scene.getHeight();
    }
}