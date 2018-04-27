import javafx.scene.Scene;

public class Board {

    private static Scene scene;
    
    public static void setScene(Scene scene) {
        Board.scene = scene;
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

}
