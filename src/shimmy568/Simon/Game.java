package shimmy568.Simon;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import shimmy568.Simon.Tools.Score;

public class Game extends StateBasedGame {

	public static TrueTypeFont f1, f2, f3;
	public static Score[] scoreList;

	public Game(String name) {
		super(name);
		//load();
	}

	//this starts the game and sets things like screen size and fps
	public static void main(String[] args) {
//		scoreList = new Score[10];
//		for(int i = 0; i < 10; i++){
//			scoreList[i] = new Score();
//			scoreList[i].score = 1;
//			scoreList[i].name = "bot";
//		}
//		save();
		
		load();
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game("Simon"));
			appgc.setDisplayMode(450, 450, false);
			appgc.setTargetFrameRate(100);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	//setting all the states that will be in the game
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Menu());
		addState(new PlayGame());
		addState(new HowToPlay());
		addState(new Highscores());
		addState(new EnterHighscore());
	}

	//a method to load the highscores at the start of the game
	private static void load() {
		try {
			FileInputStream fileIn = new FileInputStream("src/res/save/highscores.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			scoreList = (Score[]) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//a method to save the highscores when a new has been added
	public static void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"res/save/highscores.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(scoreList);
			out.close();
			fileOut.close();
			System.out.printf("Scores have been saved");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

}
