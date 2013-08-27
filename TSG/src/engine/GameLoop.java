package engine;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import render.RenderableObject;


public class GameLoop {
	public static boolean isFinished;
	public static void init() throws LWJGLException{
		isFinished = false;
		
		createDisplay();
		
		GameRenderManager.setUpGL();
		
		GameLogic.init();
		GameRenderManager.init();
	}
	
	private static void createDisplay() throws LWJGLException{
		Display.setTitle("Game Engine v0.02");

		Display.setFullscreen(false);
		Display.setVSyncEnabled(true);
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.create();
	}
	
	
	public static void run(){
		while(!isFinished){
			if(Display.isCloseRequested()){
				isFinished = true;
			}
			GameLogic.update();
			GameRenderManager.render();
			Display.update();
			Display.sync(20);
		}
	}
}