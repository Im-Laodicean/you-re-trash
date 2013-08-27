package render;

import org.lwjgl.opengl.GL11;

import tools.ResourceManager;

public class Sprite {


	/*
	 * Variables
	 */
	int key, frame, totalFrames;
	int width, height, rows, columns;
	int ticks,numberOfTicks;

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

		ticks = 0;
		numberOfTicks = 5;
	}

	/**Draws the next frame in this spritesheet
	 * 
	 * @param x
	 * @param y
	 */
	public void drawNextFrame(int x, int y){

		//Need to bind the texture, very expensive operation
		//Color.white.bind();
		ResourceManager.getTexture(key).bind();

		float textureWidth = ResourceManager.getTexture(key).getImageHeight();
		float textureHeight = ResourceManager.getTexture(key).getImageWidth();

		int curRow = frame/columns;
		int curColumn = frame%columns;

		float srcX = curColumn*height;
		float srcY = curRow*width;

		float u = srcX / textureWidth;
		float v = srcY / textureHeight;
		float u2 = (srcX + width) / textureWidth;
		float v2 = (srcY + height) / textureHeight;

		//Should switch to two triangles for more effiency 
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(u, v);
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(u, v2);
			GL11.glVertex2f(x, y + height);
			GL11.glTexCoord2f(u2, v2);
			GL11.glVertex2f(x + width, y + height);
			GL11.glTexCoord2f(u2, v);
			GL11.glVertex2f(x + width, y);
		}
		GL11.glEnd();
<<<<<<< HEAD
		frame++;
		if(frame==totalFrames)
			frame = 1;
=======

		//Increment what is being shown
		ticks++;
		if(ticks == numberOfTicks){
			ticks = 0;
			frame++;
		}

		//Reset if reached the max number of frames
		if(frame >= totalFrames)
			frame = 0;
>>>>>>> origin/god
	}

}

