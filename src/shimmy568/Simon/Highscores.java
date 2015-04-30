package shimmy568.Simon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Highscores extends BasicGameState{
	
	boolean pressed;
	
	//initing the button that takes you back to the main menu
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		//draw the static stuff
		Game.f1.drawString(150, 25, "Highscores");
		g.drawRect(75, 75, 300, 300);
		Menu.home.draw(g, 200, 387);
		drawScores(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		//i have to do this complicated thing for the button pressing unlike with the menu because the exit button and this one are in the same place
		//so i need to make sure that the mouse button isn't still down so you don't accidently press it when it changes states.
		if(Menu.home.update(container.getInput()) || pressed){
			pressed  = true;
			if(!container.getInput().isMouseButtonDown(0)){
				game.enterState(0);
				pressed = false;
			}
		}
	}
	
	/**
	 * A method to draw the scoreList from Game to the screen
	 * @param g the graphics object from the render method that this is called in
	 */
	private void drawScores(Graphics g){
		//the first loop is for going through each element of the list
		for(int i = 0; i < 10; i++){
			//draws the numbers for position
			g.drawString((i + 1) + ". ", 80, 80 + i * 30);
			//this is drawing the name on the left side of the box and the score on the right
			g.drawString(Game.scoreList[i].name, 110, 80 + i * 30);
			//woah that is one hell of a line                            This part is to make sure that the last number is allways in the same place not the first
			g.drawString(String .valueOf(Game.scoreList[i].score), 370 - g.getFont().getWidth(String.valueOf(Game.scoreList[i].score)), 80 + 30 * i);
		}
	}

	@Override
	public int getID() {
		return 3;
	}

}
