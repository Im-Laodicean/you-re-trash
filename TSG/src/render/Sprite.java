package render;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import tools.ResourceManager;

public class Sprite {
	int key, frame, totalFrames;
	int width, height, rows, columns;
	/**Loads a spritesheet using a given texture's key. 
	 * 
	 * @param key - Key of texture
	 * @param width - width of each sprite
	 * @param height - height of each sprite
	 * @param rows - number of rows
	 * @param columns - number of columns
	 */
	public Sprite(int key, int width, int height, int rows, int columns){
		this.key = key;
		this.width = width;
		this.height = height;
		this.rows = rows;
		this.columns = columns;
		frame = 0;
		totalFrames = rows*columns;
	}
	
	
	/**Draws the next frame in this spritesheet
	 * 
	 * @param x
	 * @param y
	 */
	public void drawNextFrame(int x, int y){
		int totalWidth = ResourceManager.getTexture(key).getImageWidth();
		int totalHeight = ResourceManager.getTexture(key).getImageHeight();
		
		
		int curRow = frame/columns;
		int curColumn = frame%columns;
		float texX = ((float)curColumn*width)/totalWidth;
		float texY = ((float)curRow*(height))/totalHeight;
		float texW = ((float)width)/totalWidth;
		float texH = ((float)height)/totalHeight;
		System.out.println("Texture x coord: "+ texX);
		System.out.println("Texture y coord: "+ texY);
		
		Color.white.bind();
		ResourceManager.getTexture(key).bind();
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(texX,texY);
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(texX+texW,texY);
			GL11.glVertex2f(x+width,y);
			GL11.glTexCoord2f(texX+texW,texY+texH);
			GL11.glVertex2f(x+width,y+height);
			GL11.glTexCoord2f(texX,texY+texH);
			GL11.glVertex2f(x,y+height);
		GL11.glEnd();
		frame++;
		if(frame==totalFrames)
			frame = 1;
	}
	
}
