import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scoreboard {
	public static void initSnakeLengthText(Text snakeLengthText) {
		snakeLengthText.setFont(new Font(20));
        snakeLengthText.setText("Snake Length : " + Snake.getLength());
        snakeLengthText.setX(50);
        snakeLengthText.setY(50);
        snakeLengthText.setFill(Color.WHITE);
	}
	
	public static void setSnakeLengthText() {
		if(!Movement.dead) 
			Main.snakeLengthText.setText("Snake Length : " + Snake.getLength());
		else
			Main.snakeLengthText.setText("Snake Length : " + Snake.getLength() + "\n" + "____"    + "       ____" + "       ____" + "     ____"
																			 + "\n" + "|   _  \\" + "    | ___|"  + "     |  _  |" + "     |   _  \\"
																			 + "\n" + "|  |_|  |" + "    | __|"   + "      |  |_| |" + "     |  |_|  |"
																			 + "\n" +  "|____/"   + "    |____|"  + "    |_|  |_|" + "     |____/"); 
		
        
	}
}
