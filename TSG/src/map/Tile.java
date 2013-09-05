package map;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import engine.GameInputHandler.KeyBind;
import entity.Entity;
import render.RenderableObject;
import render.Sprite;
import render.StaticSprite;
import tools.ResourceManager;

public class Tile extends Entity implements RenderableObject{

	/*
	 * Variables
	 */

	protected String textureSheetPath;
	protected int totalSpriteSheetRows,totalSpriteSheetCols;
	protected Sprite sprite;
	protected boolean passable;

	public Tile(int x, int y, int width, int height,String textureSheetPath, 
			int frameToDraw, int totRow, int totCol, boolean passable) throws IOException{
		super(x,y,width,height);

		this.textureSheetPath = textureSheetPath;
		
		this.passable = passable;
		this.totalSpriteSheetCols = totCol;
		this.totalSpriteSheetRows = totRow;

		ResourceManager.loadTexture(textureSheetPath, textureSheetPath.hashCode());

		sprite = new StaticSprite(textureSheetPath.hashCode(),super.width,super.height,frameToDraw,
				totalSpriteSheetRows,totalSpriteSheetCols);
	}

	public void renderSelf(double delta) {
		sprite.drawNextFrame(x, y, delta);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public Sprite setSprite(Sprite toSet){
		this.sprite = toSet;
		return sprite;
	}

	public boolean isRenderable() {
		
		return false;
	}

	public void setX(int x) {
	
	}

	public void setY(int y) {
		
	}

	public void setWidth(int w) {
		
	}

	public void setHeight(int h) {
		
	}

	@Override
	public void handleKeyInputs(Map<KeyBind, Boolean> keys) {
		// TODO Auto-generated method stub
		
	}

}
