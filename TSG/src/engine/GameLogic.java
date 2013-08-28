package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import entity.Entity;

/**Handles all interactions with <code>Entity</code>s 
 * 
 * @author John
 *
 */
public class GameLogic {
	
	/**List of all entities
	 * 
	 */
	public static List<Entity> currentEntities, previousEntities;
	
	/**Initializes Entity list
	 * 
	 */
	public static void init(){
		currentEntities = new ArrayList<Entity>();
		previousEntities = new ArrayList<Entity>();
	}
	
	/**Adds an Entity. If the Entity returns <code>true</code> on <code>isRenderable</code>,
	 * it is also added GameRenderManager through <code>addRenderable(e)</code>
	 * 
	 * @param e
	 */
	public static void addEntity(Entity e){
		currentEntities.add(e);
		previousEntities.add(e);
		if(e.isRenderable())
			GameRenderManager.addRenderable(e);
	}
	
	
	//TODO
	public static void update(Map<Integer, Boolean> keys) {
		previousEntities = currentEntities.subList(0, currentEntities.size());
		for(Entity e:currentEntities){
			e.handleKeyInputs(keys);
			Physics.updatePhysics(currentEntities);
		}
	}
	
	public static void interpolate(double alpha){
		Physics.interpolate(currentEntities, previousEntities, alpha);
	}
}
