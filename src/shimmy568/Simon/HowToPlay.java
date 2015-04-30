package shimmy568.Simon;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HowToPlay extends BasicGameState{

	Image screen;
	private boolean pressed;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		screen = new Image("res/img/howToPlay/HowToPlay.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawImage(screen, 0, 0);
		Menu.home.draw(g, 200, 387);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int g)
			throws SlickException {
		
		if(Menu.home.update(container.getInput()) || pressed){
			pressed  = true;
			if(!container.getInput().isMouseButtonDown(0)){
				game.enterState(0);
				pressed = false;
			}
		}
		
	}

	@Override
	public int getID() {
		return 2;
	}

}
