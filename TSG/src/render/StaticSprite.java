package render;

import org.lwjgl.opengl.GL11;

import tools.ResourceManager;

public class StaticSprite extends Sprite{
	
	protected int frameToDraw;
	private int rowToDraw,colToDraw;
	
	public StaticSprite(int key, int width, int height,int frameToDraw,
			int totalRows, int totalColumns) {
		super(key, width, height, totalRows, totalColumns, 0);
		
		//Which specific row/col to render from the sprite sheet
		this.frameToDraw = frameToDraw;
		this.rowToDraw = frame/columns;
		this.colToDraw = frame%columns;
	}

	public void drawNextFrame(int x, int y, double delta){
		//Need to bind the texture, very expensive operation
		//Color.white.bind();
		ResourceManager.getTexture(key).bind();

		float textureWidth = ResourceManager.getTexture(key).getImageHeight();
		float textureHeight = ResourceManager.getTexture(key).getImageWidth();

		float srcX = colToDraw*height;
		float srcY = rowToDraw*width;

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
}