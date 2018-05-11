import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scoreboard {

    private static Text snakeLengthText;
    private static Text snakeHighScore;
    
    private static int highScore = getHighScore();;
    
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
        
        
        setHighScore();
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
        if (highScore <= Snake.snakeChunks.size()) {
            highScore = Snake.snakeChunks.size();
            
            try {
                Files.write(Paths.get("highscore.txt"), Arrays.asList(encrypt(Integer.toString(highScore))), Charset.forName("UTF-8"));
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
            
    }
    
    public static Text setDeathText(String deathType) {
        Text deathText = new Text();
        deathText.setFont(new Font(20));
        if (Snake.snakeChunks.size() == 1)
            deathText.setText("GAME OVER You were " + Snake.snakeChunks.size() + " chunk long." + "\n          Are you even trying?");
        else
            if(deathType.equals("wall"))
                deathText.setText("GAME OVER You were " + Snake.snakeChunks.size() + " chunks long." + "\n        Where are you trying to go?");
            else if(deathType.equals("self"))
                deathText.setText("GAME OVER You were " + Snake.snakeChunks.size() + " chunks long." + "\n        Stop hitting yourself?");
        
        deathText.setX((Board.getWidth() / 2) - deathText.getText().length() - 100);
        deathText.setY((Board.getHeight() / 2));
        deathText.setFill(Color.WHITE);
        return deathText;
    }
    /**
     * checks the highscore.txt file and returns the first line which is the high score
     * @returns the highScore
     */
    public static int getHighScore() {
        
        try {
            Scanner highScoreFile = new Scanner(new File("highscore.txt"));

            if(!highScoreFile.hasNextLine()) {
                highScoreFile.close();
                return 1;
            }
            
            int score = Integer.parseInt(decrypt(highScoreFile.nextLine()));
            highScoreFile.reset();
            highScoreFile.close();
            return score;

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static String encrypt(String score) {
        String b64encoded = Base64.getEncoder().encodeToString(score.getBytes());

        // reverse the string
        String reverse = new StringBuffer(b64encoded).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) 
           tmp.append((char)(reverse.charAt(i) + OFFSET));

        return tmp.toString();
    }
    
    public static String decrypt(String score) {
        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < score.length(); i++)
           tmp.append((char)(score.charAt(i) - OFFSET));

        String reversed = new StringBuffer(tmp.toString()).reverse().toString();
        return new String(Base64.getDecoder().decode(reversed));
     }

    

}
