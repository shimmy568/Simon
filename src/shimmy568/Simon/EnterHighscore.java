package shimmy568.Simon;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EnterHighscore extends BasicGameState{
	
	private static int currentPlayerScore;
	private TextField textIn;
	private TrueTypeFont text;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		text = new TrueTypeFont(new Font("NewTimesRoman", 0 , 20), false);
		textIn = new TextField(container, text, 125, 213, 200, 25);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		textIn.render(container, g);
		Game.f1.drawString(94, 50, "NEW HIGHSCORE");
		g.drawString("Enter Your Name Below", 120, 100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if(container.getInput().isKeyDown(Input.KEY_ENTER)){
			System.out.println("he");
			for(int i = 8; i < 0; i--){
				if(!(Game.scoreList[i].score < currentPlayerScore)){
					Game.scoreList[i + 1].name = textIn.getText();
					Game.scoreList[i + 1].score = currentPlayerScore;
					Game.save();
					game.enterState(0);
				}
			}
			Game.scoreList[0].name = textIn.getText();
			Game.scoreList[0].score = currentPlayerScore;
			Game.save();
			game.enterState(0);
		}
	}
	
	public static void setScore(int score){
		currentPlayerScore = score;
	}

	@Override
	public int getID() {
		return 4;
	}

}
