package shimmy568.Simon;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import shimmy568.Simon.Tools.PlayButton;

public class PlayGame extends BasicGameState {

	// making the varibles and things
	PlayButton[] buttons; // the button objects are stored in a list
	ArrayList<Integer> order; // list to store the order that the buttons need
								// to be pressed
	Random r; // random gen to get what button comes next
	boolean playing, lost, stillClicked; // press space to start
	int state = 0, time = 0, waitTill = 0, iter = 0, score; // ints
	Color lightColor; // the color of the light in the middle telling you to
						// enter or wait

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// initing the list of the buttons and other things
		buttons = new PlayButton[4];
		buttons[0] = new PlayButton("res/sounds/1.ogg", new Color(210, 0, 0)); // red
		buttons[1] = new PlayButton("res/sounds/2.ogg", new Color(0, 210, 0)); // green
		buttons[2] = new PlayButton("res/sounds/3.ogg", new Color(0, 0, 210)); // blue
		buttons[3] = new PlayButton("res/sounds/4.ogg", new Color(210, 210, 0)); // yellow
		r = new Random();
		order = new ArrayList<Integer>();
		lightColor = new Color(0, 0, 0);

		// getting the first 3 values to play
		for (int i = 0; i < 3; i++) {
			order.add(r.nextInt(4));
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		if (playing) {

			// drawing the buttons
			buttons[0].draw(g, 25, 25);
			buttons[1].draw(g, 237, 25);
			buttons[2].draw(g, 25, 237);
			buttons[3].draw(g, 237, 237);
			drawLight(g);
		} else if(!lost){
			Game.f1.drawString(72, 205, "Press Space To Start...");
		}
		if (lost) {
			Game.f1.drawString(149, 185, "Game Over");
			g.drawString("Score: " + score, (450 - g.getFont().getWidth("Score: " + score)) / 2, 225);
			g.drawString("Press Space To Exit...", 129, 245);
		}
	}

	private void drawLight(Graphics g) {
		Color c = g.getColor();
		g.setColor(lightColor);
		g.fillOval(220, 220, 10, 10);
		g.setColor(c);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		dimButtons();

		time += delta; // the current time in milliseconds

		// checks if the person has press space and if so start playing the game
		if (container.getInput().isKeyDown(Input.KEY_SPACE)) {
			playing = true;
		}
		
		if(lost){ //checks if the play pressed space once the game was over and if so moves to the enter highscore state
			if(container.getInput().isKeyDown(Input.KEY_SPACE)){
				if(Game.scoreList[9].score < score){
					EnterHighscore.setScore(score);
					game.enterState(4);
				}else{
					game.enterState(0);
				}
			}
		}
		
		if (playing) {
			if (state == 0) { // playing the pattern state

				lightRed();
				if (time > waitTill) {
					waitTill = time;
					buttons[order.get(iter)].playSound();
					waitTill = time + 300;
					iter++;
					if (iter == order.size()) {
						state = 1;
						waitTill = time + 3000;
					}

				}
			} else if (state == 1) { // rest state between 0 and 2

				lightYellow();
				if(waitNSwitch(2)){
					waitTill = time + 3000;
				}

			} else if (state == 2) { // entering the pattern state

				lightGreen();
				for (int i = 0; i < buttons.length; i++) {
					if (buttons[i].update(container.getInput()) && !buttons[i].getIsBrighter()) {
						if (i == order.get(iter)) {
							correct(i);
							stillClicked = true;
							if (iter == order.size()) {
								state = 3;
								waitTill = time + 3000;
							}
						} else {
							wrong();
						}
					}
				}
				if (time > waitTill) {
					wrong();
				}

			} else if (state == 3) { // rest state between 3 and 0

				lightYellow();
				if (waitNSwitch(0)) {
					order.add(r.nextInt(4));
				}

			}
		}
	}

	private void wrong() {
		lost = true;
		score = order.size() - 3;
		playing = false;
	}

	private void correct(int i) {
		iter++;
		waitTill = time + 3000;
		buttons[i].playSound();
	}

	private boolean waitNSwitch(int i) {
		iter = 0;
		if (time > waitTill) {
			state = i;
			waitTill = time + 1000;
			return true;
		}
		return false;
	}

	private void lightGreen() {
		lightColor.g = 1f;
		lightColor.r = 0f;
	}

	private void lightYellow() {
		lightColor.g = 1f;
		lightColor.r = 1f;
	}

	private void lightRed() {
		lightColor.r = 1f;
		lightColor.g = 0;

	}

	/**
	 * calls the dim buttons method on each of the buttons
	 */
	private void dimButtons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].dim();
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}
