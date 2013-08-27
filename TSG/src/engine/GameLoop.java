package engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class GameLoop {
	/**Determines if the game is to quit. 
	 * 
	 */
	public static boolean isFinished;
	
	/**Creates the Display, sets up the appropriate OpenGL parameters,
	 * and initializes the render manager and input handler, as well as the logic engine.
	 * 
	 * @throws LWJGLException
	 */
	public static void init() throws LWJGLException{
		isFinished = false;
		
		createDisplay();
		
		GameRenderManager.setUpGL();
		
		GameLogic.init();
		GameRenderManager.init();
		GameInputHandler.init();
	}
	
	private static void createDisplay() throws LWJGLException{
		Display.setTitle("Game Engine v0.02");

		Display.setFullscreen(false);
		Display.setVSyncEnabled(true);
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.create();
	}
	
	/**Runs the game using a 60 FPS cap and variable time-steps
	 * 
	 */
	public static void run(){
		long lastLoopTime = System.nanoTime();//first loop time
		final int NOMINAL_FPS = 60;
		final long OPTIMAL_INTERVAL = 1000000000 / NOMINAL_FPS;
		long timeSinceFPSCheck = 0;
		int fps = 0;
		
		
		while(!isFinished){
			long curTime = System.nanoTime();
			long timeSinceUpdate = curTime - lastLoopTime;
			lastLoopTime = curTime;
			
			//if we run at NOMINAL_FPS, then delta should be around 1
			double delta = timeSinceUpdate / ((double)OPTIMAL_INTERVAL);
			
			timeSinceFPSCheck+=timeSinceUpdate;
			fps++;
			//mostly debug purposes
			if(timeSinceFPSCheck>=1000000000){
				System.out.println(fps);
				fps = 0;
				timeSinceFPSCheck=0;
			}
			
			if(Display.isCloseRequested()){
				isFinished = true;
			}
			//update game state with appropriate delta 
			//and the keys it needs to respond to
			GameLogic.update(delta, GameInputHandler.keysHeld);
			//render
			GameRenderManager.render(delta);
			//poll the keyboard
			GameInputHandler.pollKeyboardInput();
			
			//update the display
			Display.update();
		}
	}
}