package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.xml.sax.SAXException;

import tools.ConfigFileReader;

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
	public static Map<KeyBind, Boolean>keysHeld;
	
	public static List<KeyBind>MASTER;
	public static List<Integer>MASTER_CODES;
	
	/**Initializes the Keyboard and Mouse. 
	 * 
	 * @throws LWJGLException
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void init() throws LWJGLException, SAXException, IOException, ParserConfigurationException{
		Keyboard.create();
		keysHeld = new HashMap<KeyBind, Boolean>();
		MASTER = ConfigFileReader.readKeyBindings();
		MASTER_CODES = new ArrayList<Integer>();
		for(KeyBind kb: MASTER){
			MASTER_CODES.add(kb.getCode());
		}
		Mouse.create();
	}
	
	/**Poll the Keyboard and adds any pressed keys to the list. 
	 * Removes released keys. 
	 * 
	 */
	public static void pollKeyboardInput(){
		keysHeld.clear();
		while(Keyboard.next()){
			int mirrorArrayIndex = MASTER_CODES.indexOf(Keyboard.getEventKey());
			System.out.println(mirrorArrayIndex);
			if(mirrorArrayIndex!=-1){
				keysHeld.put(MASTER.get(mirrorArrayIndex), Keyboard.getEventKeyState());
			}
		}
	}
	
	public static class KeyBind{
		private String name;
		private int keyCode;
		public KeyBind(String name, int keyCode){
			this.name = name;
			this.keyCode = keyCode;
		}
		
		public String getName(){ 
			return name;
		}
		
		public int getCode(){
			return keyCode;
		}
		
		public String toString(){
			return name+": "+keyCode;
		}
		
		public boolean equals(Object o){
			if(!(o instanceof GameInputHandler.KeyBind))
				return false;
			else
				return ((KeyBind)o).getName().equals(getName())&&((KeyBind)o).getCode()==getCode();
		}
	}
}
