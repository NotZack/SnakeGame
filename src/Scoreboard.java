import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scoreboard {

    private static Text snakeLengthText;
    private static Text snakeHighScore;
    
    private static int highScore = getHighScore();
    
    //The list that children are added from
    static ArrayList<Text> scoreboard = new ArrayList<Text>();
    
    /**
     * creates text that tells the length/highscore/etc of the snake
     * @returns the text
     */
    public static ArrayList<Text> initScoreboardText() {
        scoreboard.clear();
        
        snakeLengthText = initText(new Text());
        snakeLengthText.setText("Snake Length : " + Snake.snakeChunks.size());

        snakeHighScore = initText(new Text());
        snakeHighScore.setText("Highscore : " + highScore);
        
        getHighScore();
        return scoreboard;
    }
    
    /**
     * sets the basic values of the text, then adds them to the scoreboard list to be added as children
     * @param text, the text that values are being changed
     * @return the same text
     */
    private static Text initText(Text text) {
        text.setFont(new Font(20));
        text.setX(50);
        text.setY(50 + (scoreboard.size() * 25));
        text.setFill(Color.WHITE);
        scoreboard.add(text);
        return text;
    }

    /**
     * sets the text of the Text objects. Updates every chunk collection
     */
    public static void setSnakeText() {
        //Overwrites the old text containing the highscore
        snakeLengthText.setText("Snake Length : " + Snake.snakeChunks.size());
        snakeHighScore.setText("Highscore : " + highScore);
    }
    
    /**
     * checks to see if current snake is the highscore, then changes the score accordingly
     */
    public static void setHighScore() {
        if (highScore < Snake.snakeChunks.size()) {
            highScore = Snake.snakeChunks.size();
            
            try {
                Files.write(Paths.get("highscore.txt"), Arrays.asList(Integer.toString(highScore)), Charset.forName("UTF-8"));
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
            
    }
    
    /**
     * checks the highscore.txt file and returns the first line which is the high score
     * @returns the highScore
     */
    public static int getHighScore() {
        try {
            BufferedReader highScoreFile = new BufferedReader(new FileReader("highscore.txt"));
            int score = Integer.parseInt(highScoreFile.readLine());
            highScoreFile.close();
            return score;
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
