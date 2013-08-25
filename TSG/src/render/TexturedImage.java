package render;


import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import tools.ResourceManager;

public class TexturedImage extends RenderableObject{

	protected String imagePath;
	protected String key;

	//Basic constructor
	public TexturedImage(int x, int y, int width, int height, String imagePath, String key){
		super(x,y,width,height);
		
		this.imagePath = imagePath;
		this.key = key;
		
		try {
			ResourceManager.loadTexture(imagePath, key);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Sets width and height to image dimensions
	public TexturedImage(int x, int y, String imagePath, String key){
		this.imagePath = imagePath;
		this.key = key;
		
		try {
			ResourceManager.loadTexture(imagePath, key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		super.x = x;
		super.y = y;
		super.width = ResourceManager.getTexture(key).getImageWidth();
		super.height = ResourceManager.getTexture(key).getImageHeight();
		System.out.println(key + ": " + width + "  , " + height);
		
	}

	public void renderSelf() {
		Color.white.bind();
		ResourceManager.getTexture(key).bind();
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+width,y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+width,y+height);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x,y+height);
		GL11.glEnd();
	}


	//Getters and Setters
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
