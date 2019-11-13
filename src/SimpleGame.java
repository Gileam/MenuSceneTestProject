import edu.utc.game.Game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import edu.utc.game.Scene;
import edu.utc.game.Sound;
import edu.utc.game.Text;

public class SimpleGame extends Game implements Scene {
	//Game Objects
	static int score;
	static int time;
	static long start;
	static long elasped = 0;
	boolean pauseGame = false;
	//Scenes
	static Scenes.MainMenu menu = new Scenes.MainMenu(1,1,1);
	static Scenes.MainMenu pause = new Scenes.MainMenu(1,1,1);
	static Scenes.SelectableText scoreInMenu = null;
	static updatableText scoreText = null;
	static updatableText timeText = null;
	
	public static void main(String[] args)
	{
	
		// construct a DemoGame object and launch the game loop
		// DemoGame game = new DemoGame();
		// game.gameLoop();
	
		SimpleGame game=new SimpleGame();
		game.registerGlobalCallbacks();
		
		initScene launcher = new initScene(game);
		resumeTime timer = new resumeTime(game);
		
		menu.addItem(new Scenes.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 0, 0, 0), launcher);
		menu.addItem(new Scenes.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 0, 0, 0), null);
		menu.select(0);

		
		pause.addItem(new Scenes.SelectableText(20, 20, 20, 20, "Resume", 1, 0, 0, 0, 0, 0), timer);
		pause.addItem(new Scenes.SelectableText(20, 60, 20, 20, "Quit to Main Menu", 1, 0, 0, 0, 0, 0), menu);
		pause.addItem(new Scenes.SelectableText(20, 100, 20, 20, "Quit to Desktop", 1, 0, 0, 0, 0, 0), null);
		scoreInMenu = new Scenes.SelectableText(500, 20, 20, 20, "", 0, 0, 0, 0, 0, 0);
		pause.addItem(scoreInMenu, pause);
		pause.select(0);

		scoreText = new updatableText(20,20,20,20, "B");
		timeText = new updatableText(20,60,20,20, "A");
		
		game.setScene(menu);
		game.gameLoop();
	}

	public SimpleGame()
	{
		// inherited from the Game class, this sets up the window and allows us to access
		// Game.ui
		initUI(640, 480, "DemoGame");
	}
	//This small class is a way to ensure that anything that needs to reset the game does.
	public static class initScene implements Scene{
		SimpleGame game;
		initScene(SimpleGame game){
			this.game = game;
		}
		public Scene drawFrame(int delta) {
			game.init();
			return game;
		}
	}
	//This small class is a way to ensure that anything that needs to reset the game does.
	public static class resumeTime implements Scene{
		SimpleGame game;
		resumeTime(SimpleGame game){
			this.game = game;
		}
		public Scene drawFrame(int delta) {
			start = System.nanoTime();
			return game;
		}
	}
	public void onKeyEvent(int key, int scancode, int action, int mods){
		//System.out.println("Key: " + key);
		//System.out.println("Action: " + action);
		if(key== 256 && action == 1){
			pauseGame = true;
		}
	}
	
	public void onMouseEvent(int button,int action, int mods){
		//System.out.println("Button: "+button);
		//System.out.println("Action: "+action);
		if (button==0 && action==1)
		{
			score++;
		}		
	}
	
	private void init(){
		glClearColor(1f, 1f, 1f, 1f);
		start = System.nanoTime();
		elasped = 0;
		// screen clear is black (this could go in drawFrame if you wanted it to change
		score = 0;
	}
	
	private static class updatableText extends Text{
		public updatableText(int x, int y, int w, int h, String text) {
			super(x, y, w, h, text);
			this.setColor(0f, 0f, 0f);
		}
		public void setLabel(String text){
			this.textArray = text.split("");
		}
	}
	
	public Scene drawFrame(int delta) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		time = (int) ((System.nanoTime() - start)/ 1000000000l + elasped);
		if (pauseGame){
			pauseGame = false;
			scoreInMenu.setLabel("Score: "+score);
			elasped = time;
			return pause; 
		}
		scoreText.setLabel("Score: "+score);
		scoreText.draw();
		timeText.setLabel("Time: "+time);
		timeText.draw();
        if (score > 5){
        }
		return this;
	}

}
