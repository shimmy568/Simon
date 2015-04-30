package shimmy568.Simon.Tools;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class PlayButton extends Button{
	
	private Audio s;
	private Color c;
	private boolean brighter;
	
	/**
	 * the constructor for the buttons used in the playGame class note these buttons don't use png textures so the
	 * sound path passed to the parent constructor will just trigger the try catch and not cause an error while the 
	 * constructor here runs
	 * @param soundPath the path where the sounds for the buttons will be loaded from
	 */
	public PlayButton(String soundPath, Color c){
		super(soundPath);
		try {
			s = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(soundPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = 187;
		height = 187;
		this.c = c;
	}
	
	
	@Override
	public void draw(Graphics g, int x, int y){
		this.x = x;
		this.y = y;
		//the temp varible stores what the color was before the method was run so it can turn it back afterwards to not break anything
		Color temp = g.getColor();
		g.setColor(c);
		g.fillRect(x, y, width, height);
		g.setColor(temp);
	}
	
	private void getLighter(){
		brighter = true;
		c.r += 0.20f;
		c.g += 0.20f;
		c.b += 0.20f;
	}
	
	private void getDimmer(){
		c.r -= 0.20f;
		c.g -= 0.20f;
		c.b -= 0.20f;
	}
	
	/**
	 * Method to play sound whenever the game needs
	 */
	public void playSound(){
		s.playAsSoundEffect(1f, 1f, false);
		getLighter();
	}
	public void stopSound(){
		s.stop();
	}
	
	public boolean getIsBrighter(){
		return brighter;
	}
	
	//dims the buttons if the 0.25 second sound is no longer playing and it was played before this call
	public void dim(){
		if(!s.isPlaying() && brighter){
			getDimmer();
			brighter = false;
		}
	}
}
