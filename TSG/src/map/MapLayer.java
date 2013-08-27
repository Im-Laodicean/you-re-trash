package map;

import render.RenderableObject;

public class MapLayer implements RenderableObject{

	protected Tile[][] tiles;
	protected int width,height;

	public MapLayer(int width, int height){
		this.width = width;
		this.height = height;
		tiles = new Tile[height][width];
	}
	
	public void setTiles(Tile[][] toSet){
		this.tiles = toSet;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public Tile getTile(int row, int col){
		return tiles[row][col];
	}
	
	public void setTile(int row, int col, Tile toSet){
		tiles[row][col] = toSet;
	}
	
	public void renderSelf(double delta) {
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				tiles[i][j].renderSelf(delta);
	}
}