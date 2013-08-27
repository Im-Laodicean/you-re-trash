package engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class GameLoop {
	public static boolean isFinished;
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
			double delta = timeSinceUpdate / ((double)OPTIMAL_INTERVAL);
			
			timeSinceFPSCheck+=timeSinceUpdate;
			fps++;
			
			if(timeSinceFPSCheck>=1000000000){
				System.out.println(fps);
				fps = 0;
				timeSinceFPSCheck=0;
			}
			
			if(Display.isCloseRequested()){
				isFinished = true;
			}
			GameLogic.update(delta, GameInputHandler.keysHeld);
			GameRenderManager.render();
			GameInputHandler.pollKeyboardInput();
			Display.update();
			Display.sync(20);
		}
	}
}