package render;

import org.lwjgl.opengl.GL11;

import tools.ResourceManager;

public class Sprite {


	/*
	 * Variables
	 */
	int key, frame, totalFrames;
	int width, height, rows, columns;
	long timePerFrame;
	long lastUpdateTime, timeSinceLastFrameChange;
	double speed;
	long lastChange;
	boolean animate;
	/**Loads a spritesheet using a given texture's key. 
	 * 
	 * @param key - Key of texture
	 * @param width - width of each sprite
	 * @param height - height of each sprite
	 * @param rows - number of rows
	 * @param columns - number of columns
	 * @param speed - speed of animation in frames per second. For example, 5 speed means the animation advances 5 frames every second
	 */
	public Sprite(int key, int width, int height, int rows, int columns, double speed){
		this.key = key;
		this.width = width;
		this.height = height;
		this.rows = rows;
		this.columns = columns;
		this.speed = speed;
		frame = 0;
		totalFrames = rows*columns;

		lastUpdateTime = 0;
		timePerFrame = (long)((1/speed)*1000000000.0);
		System.out.println(timePerFrame);
		animate = false;
	}

	/**Draws the next frame in this spritesheet
	 * 
	 * @param x
	 * @param y
	 * @param delta 
	 */
	public void drawNextFrame(int x, int y, double delta){
		long curTime = System.nanoTime();
		long timeSinceUpdate = System.nanoTime() - lastUpdateTime;
		lastUpdateTime = curTime;
		//debug info
		if(lastChange==0)
			lastChange = System.nanoTime();


		//Need to bind the texture, very expensive operation
		//Color.white.bind();
		drawSubTexture(x, y);

		//Increment what is being shown AT THE APPROPRIATE RATE, given delta
		if(animate){
			timeSinceLastFrameChange += timeSinceUpdate;
			if(timeSinceLastFrameChange >= timePerFrame){
				System.out.println(timeSinceLastFrameChange);
				timeSinceLastFrameChange = 0;
				frame++;

				//again, debug
				lastChange = System.nanoTime();
			}

			//Reset if reached the max number of frames
			if(frame >= totalFrames)
				frame = 0;
		}
	}

	private void drawSubTexture(int x, int y) {
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
	}

	public void stopAnimation(){
		frame = 0;
		lastUpdateTime = 0;
		animate = false;
	}

	public void startAnimation(){
		animate = true;
	}

	public void pauseAnimation(){
		animate = false;
	}

}

