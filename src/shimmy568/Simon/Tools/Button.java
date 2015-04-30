package shimmy568.Simon.Tools;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Button {
	Image rest, active;
	int x, y, width, height;
	boolean mouseOver;
	public Button(String path) {
		try {
			//each button has it's own package and is named as 0 for unmoused over and 1 for moused over
			rest = new Image(path + "0.png");
			active = new Image(path + "1.png");
			
			//gets the width and height for images for hitboxes
			width = rest.getWidth();
			height = rest.getHeight();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}catch(RuntimeException e){
			System.out.println("Images were not found if not ment for play buttons this is an error"); //remove
		}
		
	}
	
	/**
	 * draws the button at a given cord
	 * @param g the graphics object from render to draw the button
	 * @param x the x cord for the button
	 * @param y the y cord for the button
	 */
	public void draw(Graphics g, int x, int y){
		//gets the cords of where it's drawn for the update method
		this.x = x;
		this.y = y;
		//if the button has a mouse within it's boundaries it draws the active version
		if(mouseOver){
			g.drawImage(active, x, y);
		}else{
			g.drawImage(rest, x, y);
		}
	}
	
	/**
	 * A method that gets if the mouse is over the button and if it's clicked it returns true for the update loop to handle the results
	 * @param in the input object gotten from the container in update when called
	 * @return whether the button has been clicked or not
	 */
	public boolean update(Input in){
		if(in.getMouseX() - x > 0 && in.getMouseX() - x < width && in.getMouseY() - y > 0 && in.getMouseY() - y < height){ //this is true if the mouse is on top of the last drawn place of the button
			mouseOver = true;
			if(in.isMouseButtonDown(0)){
				return true;
			}
		}else{
			mouseOver = false;
		}
		return false;
	}
}
