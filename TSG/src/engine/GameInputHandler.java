package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GameInputHandler {
	public static List<Integer>keysHeld;
	public static void init() throws LWJGLException{
		Keyboard.create();
		keysHeld = new ArrayList<Integer>();
		Mouse.create();
	}
	
	public static void pollKeyboardInput(){
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState())
				keysHeld.add(Keyboard.getEventKey());
			else keysHeld.remove(new Integer(Keyboard.getEventKey()));
		}
	}
}
