package shimmy568.Simon;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import shimmy568.Simon.Tools.Button;

public class Menu extends BasicGameState{
	
	
	//initing the buttons up here because they are called in more than one method
	Button play, howTo, highscores, exit;
	public static Button home;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		//initing the fonts for the game class as you need to have it run slick code in a slick method which the game class doesn't have
		Game.f1 = new TrueTypeFont(new Font("NewTimesRoman", 0 , 30), false);
		Game.f2 = new TrueTypeFont(new Font("NewTimesRoman", 0 , 50), false);
		Game.f3 = new TrueTypeFont(new Font("NewTimesRoman", 0 , 12), false);
		
		//Making the buttons for the menu
		play = new Button("res/img/menu/playGame/");
		howTo = new Button("res/img/menu/howTo/");
		highscores = new Button("res/img/menu/highscores/");
		exit = new Button("res/img/menu/exit/");
		home = new Button("res/img/menu/home/");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		
		
		//System.out.println(g.getFont().getHeight("NEW HIGHSCORE") + " " + g.getFont().getWidth("NEW HIGHSCORE"));
		
		//Draws the text 
		Game.f2.drawString(154, 30, "Simon");
		Game.f3.drawString(5, 430, "By: Shimmy568");
		
		//Draws the buttons
		play.draw(g, 125, 144);
		howTo.draw(g, 125, 219);
		highscores.draw(g, 125, 294);
		exit.draw(g, 125, 369);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		//Updates the buttons and checks if they got clicked
		if(play.update(container.getInput())){
			game.enterState(1);
		}
		if(howTo.update(container.getInput())){
			game.enterState(2);
		}
		if(highscores.update(container.getInput())){
			game.enterState(3);
		}
		if(exit.update(container.getInput())){
			container.exit();
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
