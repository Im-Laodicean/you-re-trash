package engine;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import entity.Entity;
import render.RenderableObject;


public class GameRenderManager {
	public static ArrayList<RenderableObject> toRender;
	public static void setUpGL(){
		glEnable(GL_TEXTURE_2D);

		// enable 	alpha blending
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		
		glViewport(0,0,800,600);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 600, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public static void init(){
		toRender = new ArrayList<RenderableObject>();
	}
	
	public static void render(double delta) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		for(RenderableObject ro: toRender)
			ro.renderSelf(delta);
	}

	/**DO NOT CALL THIS OUTSIDE OF GameLogic at least for now. 
	 * 
	 * @param e
	 */
	public static void addRenderable(Entity e) {
		if(!e.isRenderable())
			return;
		toRender.add((RenderableObject) e);
	}

}
