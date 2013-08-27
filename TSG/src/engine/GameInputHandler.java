package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**Polls the keyboard and mouse (needs to be implemented) for the keys presed
 * and buttons pressed. Stores keys in a list that can be retrieved
 * 
 * @author John
 *
 */
public class GameInputHandler {
	/**List of all keys pressed as of last poll. 
	 * 
	 */
	public static List<Integer>keysHeld;
	
	/**Initializes the Keyboard and Mouse. 
	 * 
	 * @throws LWJGLException
	 */
	public static void init() throws LWJGLException{
		Keyboard.create();
		keysHeld = new ArrayList<Integer>();
		Mouse.create();
	}
	
	/**Poll the Keyboard and adds any pressed keys to the list. 
	 * Removes released keys. 
	 * 
	 */
	public static void pollKeyboardInput(){
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState())
				keysHeld.add(Keyboard.getEventKey());
			else keysHeld.remove(new Integer(Keyboard.getEventKey()));
		}
	}
}
