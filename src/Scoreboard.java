import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scoreboard {

    public static Text snakeLengthText;
    
    /**
     * creates text that tells the length of the snake
     * @returns the text
     */
    public static Text initSnakeText() {
        snakeLengthText = new Text();
        snakeLengthText.setFont(new Font(20));
        snakeLengthText.setText("Snake Length : " + Snake.snakeChunks.size());
        snakeLengthText.setX(50);
        snakeLengthText.setY(50);
        snakeLengthText.setFill(Color.WHITE);
        return snakeLengthText;
    }
    
    public static void setSnakeText() {
        snakeLengthText.setText("Snake Length : " + Snake.snakeChunks.size());
    }

}
