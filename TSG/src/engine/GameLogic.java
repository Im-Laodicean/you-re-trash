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
	public static List<Entity> entities;
	
	/**Initializes Entity list
	 * 
	 */
	public static void init(){
		entities = new ArrayList<Entity>();
	}
	
	/**Adds an Entity. If the Entity returns <code>true</code> on <code>isRenderable</code>,
	 * it is also added GameRenderManager through <code>addRenderable(e)</code>
	 * 
	 * @param e
	 */
	public static void addEntity(Entity e){
		entities.add(e);
		if(e.isRenderable())
			GameRenderManager.addRenderable(e);
	}
	
	
	//TODO
	public static void update(Map<Integer, Boolean> keys) {
		for(Entity e:entities){
			e.handleKeyInputs(keys);
			Physics.updatePhysics(entities);
		}
	}
}
