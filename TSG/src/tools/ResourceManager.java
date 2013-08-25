package tools;

import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {

	public static HashMap<Integer,Texture> textureManager;
	
	static{
		textureManager = new HashMap<Integer,Texture>();
	}
	
	
	public static boolean loadTexture(String filePath, int key) throws IOException{
		if(!textureManager.containsKey(key)){//If the manager doesn't contain the key
			Texture tempTexture = TextureLoader.getTexture(filePath.substring(filePath.length()-3)
					,ResourceLoader.getResourceAsStream(filePath));//Loads resource with Slick-util
			textureManager.put(key, tempTexture);
			return true;
		}
		else{
			//If the key is duplicate
			return false;
		}
	}
	
	public static Texture getTexture(int key){
		if(textureManager.containsKey(key))
			return textureManager.get(key);
		else
			return null;
	}
	
	public static boolean unloadTexture(int key){
		if(textureManager.containsKey(key)){
			textureManager.remove(key);
			return true;
		}
		else{
			return false;
		}
	}
}