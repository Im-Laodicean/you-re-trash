package map;

import render.RenderableObject;

public class Map implements RenderableObject{

	protected int width,height,numberOfMapLayers;
	protected MapLayer[] mapLayers;

	public Map(int row, int col, int numOfLayers){
		this.height = row;
		this.width = col;
		this.numberOfMapLayers = numOfLayers;
		mapLayers = new MapLayer[numberOfMapLayers];
		
		for(int i = 0; i < numberOfMapLayers; i++){
			mapLayers[i] = new MapLayer(width,height);
		}
	}
	
	public void setMapLayer(int numLayer, MapLayer layer){
		mapLayers[numLayer] = layer;
	}
	
	public MapLayer getLayer(int numLayer){
		return mapLayers[numLayer];
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int num){
		this.width = num;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int num){
		this.height = num;
	}
	
	public void setNumOfMapLayers(int num){
		this.numberOfMapLayers = num;
		mapLayers = new MapLayer[num];
		for(int i = 0; i < numberOfMapLayers; i++){
			mapLayers[i] = new MapLayer(width,height);
		}
	}
	
	public int getNumberOfMapLayers(){
		return numberOfMapLayers;
	}
	
	public void renderSelf(double delta) {
		for(int i = 0; i <numberOfMapLayers; i++)
			mapLayers[i].renderSelf(delta);
	}

	
}
