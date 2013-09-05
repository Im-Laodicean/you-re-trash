package engine;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.xml.sax.SAXException;


public class GameLoop {
	public static final int NOMINAL_FPS = 60;
	public static final double LOGIC_STEP_TIME = 1/120.0;
	public static final long OPTIMAL_INTERVAL = 1000000000 / NOMINAL_FPS;
	/**Determines if the game is to quit. 
	 * 
	 */
	public static boolean isFinished;

	/**Creates the Display, sets up the appropriate OpenGL parameters,
	 * and initializes the render manager and input handler, as well as the logic engine.
	 * 
	 * @throws LWJGLException
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void init() throws LWJGLException, SAXException, IOException, ParserConfigurationException{
		isFinished = false;

		createDisplay();

		GameRenderManager.setUpGL();

		GameLogic.init();
		GameRenderManager.init();
		GameInputHandler.init();
		Physics.init(1);
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
		long timeSinceFPSCheck = 0;
		int fps = 0;
		
		
		double accumulator = 0;


		while(!isFinished){
			long curTime = System.nanoTime();
			long timeSinceUpdate = curTime - lastLoopTime;
			lastLoopTime = curTime;
			
			
			accumulator += timeSinceUpdate/1000000000.0;
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
			//update game state the appropriate number of times
			//depending on the number amount of time taken to render
			while(accumulator>=LOGIC_STEP_TIME){
				GameLogic.update(GameInputHandler.keysHeld);
				accumulator -= LOGIC_STEP_TIME;
			}
			
			double alpha = accumulator/LOGIC_STEP_TIME;
			
			GameLogic.interpolate(alpha);
			
			//render
			double delta = timeSinceUpdate/OPTIMAL_INTERVAL;
			GameRenderManager.render(delta);
			//poll the keyboard
			GameInputHandler.pollKeyboardInput();

			//update the display
			Display.update();
		}
	}
}