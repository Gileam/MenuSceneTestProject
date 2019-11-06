import edu.utc.game.Game;
import edu.utc.game.GameObject;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL11;

//import MouseGame.Box;
//import MouseGame.Bullet;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;
import edu.utc.game.XYPair;

public class SimpleGame extends Game implements Scene {
	
	//private static java.util.Random rand=new java.util.Random();
	private boolean gotClick=false;
	// private List<Box> boxes;
	//private Box currBox;
	
	public static void main(String [] arg)
	{
	
		// construct a DemoGame object and launch the game loop
		// DemoGame game = new DemoGame();
		// game.gameLoop();
	
		SimpleGame game=new SimpleGame();
		game.registerGlobalCallbacks();

		//SimpleMenu menu = new SimpleMenu();
		//menu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		//menu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		//menu.select(0);

		//game.setScene(menu);
		game.gameLoop();
	}

	
	// DemoGame instance data
	
	//List<GameObject> targets;
	//Player player;
	int GotClicks=0;
	public SimpleGame()
	{
		initUI(640,480,"Simple Game");
    	GL11.glClearColor(1,1,1,0);
    	int GotClicks=0;
    	//boxes = new java.util.LinkedList<Box>();
		//currBox = new Box();
    	//bullets=new java.util.LinkedList<Bullet>();
    	Game.ui.showMouseCursor(true);
    	GLFW.glfwSetMouseButtonCallback(Game.ui.getWindow(), 
				new GLFWMouseButtonCallback()
				{
					public void invoke(long window, int button, int action, int mods)
					{
						if (button==1 && action==GLFW.GLFW_PRESS)
						{
							
							gotClick=true;
							
							if(GotClicks>=5)
							{
								//System.out.println("test");
							}
							
					
						}
					}
			
				});
		
	}
		
	long start = System.nanoTime();
	public Scene drawFrame(int delta) 
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        
        
        if (gotClick)
		{
			System.out.println("detected a click event"); //checks if the mouse if clicked
			long finish = System.nanoTime();
			long timeElapsed = finish - start;
			System.out.println(timeElapsed);
			if(GotClicks>=5)
			{
				//return to victory screen
			}
			
		}
        
		
        return this;
    }
	
	
	public Scene draw()
	{
	Game.ui.showMouseCursor(true);
	GLFW.glfwSetMouseButtonCallback(Game.ui.getWindow(), 
			new GLFWMouseButtonCallback()
			{
				public void invoke(long window, int button, int action, int mods)
				{
					if (button==1 && action==GLFW.GLFW_PRESS)
					{
						
						gotClick=true;
						
						if(GotClicks>=5)
						{
							System.out.println("test");
						}
						
				
					}
				}
		
			}
	
	
	}
	
}
	
	