import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scoreboard {

    //Score text that is always visible
    private static Text snakeLengthText;
    private static Text snakeHighScore;
    
    //Fields used for encryption
    private static final String encryptionKey = "ABCDEFGHIJKLMNOP";
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    
    private static int highScore = getHighScore();;
    
    //The list that children are added from
    static ArrayList<Text> scoreboard = new ArrayList<Text>();
    
    /**
     * creates text that tells the length/highscore/etc of the snake and initializes that text
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
     * @return the same text as the argument
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
        //setText overwrites the old text
        snakeLengthText.setText("Snake Length : " + Snake.snakeChunks.size());
        snakeHighScore.setText("Highscore : " + highScore);
    }
    
    /**
     * checks to see if current snake is the highscore, and if so, it sets the highScore, then writes it (encrypted) to the 
     * highscore.txt file.
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
    
    /**
     * sets the text that is seen when the snake dies
     * @param deathType, how the snake died, used to allow for different death messages
     * @returns the deathText object so it can be added into the root node
     */
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
    
    /**
     * encrypts the highScore into an unreadable mess of characters
     * @param plainText the highScore being encrypted
     * @returns the encrypted high score
     */
    public static String encrypt(String plainText) {
        String encryptedText = "";
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
             System.err.println("Encrypt Exception : "+E.getMessage());
        }
        return encryptedText;
    }
    
    /**
     * decrypts the encrypted high score into its original not encrypted self
     * @param encryptedText the encrypted high score
     * @return the decrypted high score
     */
    public static String decrypt(String encryptedText) {
        String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("decrypt Exception : "+E.getMessage());
        }
        return decryptedText;
    }
}
