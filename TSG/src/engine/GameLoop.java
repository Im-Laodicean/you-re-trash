package engine;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import render.RenderableObject;


public class GameEngine {
	public static ArrayList<RenderableObject> toRender;
	public static boolean isFinished;
	public static void init() throws LWJGLException{
		toRender = new ArrayList<RenderableObject>();
		isFinished = false;
		
		Display.setTitle("Game Engine v0.01");

		Display.setFullscreen(false);
		Display.setVSyncEnabled(true);
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.create();
		
		setUpGL();
	}
	
	public static void setUpGL(){
		glEnable(GL_TEXTURE_2D);

		// enable alpha blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		
		GL11.glViewport(0,0,800,600);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public static void addToRenderables(RenderableObject r){
		toRender.add(r);
		System.out.println(r.getClass().toString());
	}
	
	private static void render(){
		for(RenderableObject r : toRender){
			r.renderSelf();
			System.out.println(r.getClass().toString()+" rendered");
		}
	}
	
	public static void run(){
		while(!isFinished){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			if(Display.isCloseRequested()){
				isFinished = true;
			}
			render();
			Display.update();
			Display.sync(20);
		}
	}
}