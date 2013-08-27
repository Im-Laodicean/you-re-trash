package engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import entity.Entity;

public class GameLogic {
	public static List<Entity> entities;
	
	public static void init(){
		entities = new ArrayList<Entity>();
	}
	
	public static void addEntity(Entity e){
		entities.add(e);
		if(e.isRenderable())
			GameRenderManager.addRenderable(e);
	}
	
	
	//TODO
	public static void update(double delta, List<Integer> keys) {
		for(Entity e:entities){
			e.handleKeyInputs(keys);
		}
	}
}
