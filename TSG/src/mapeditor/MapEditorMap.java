package mapeditor;

import java.awt.Graphics;

public class MapEditorMap {

	protected int row,col,numLayers;
	protected MapEditorMapLayer[] layers;
	
	public MapEditorMap(int row, int col, int numOfLayers){
		this.row = row;
		this.col = col;
		this.numLayers = numOfLayers;
		layers = new MapEditorMapLayer[numOfLayers];
		
		for(int i = 0; i < numOfLayers; i++){
			layers[i] = new MapEditorMapLayer(row, col);
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getNumLayers() {
		return numLayers;
	}

	public void setNumLayers(int numLayers) {
		this.numLayers = numLayers;
	}

	public MapEditorMapLayer[] getLayers() {
		return layers;
	}

	public void setLayers(MapEditorMapLayer[] layers) {
		this.layers = layers;
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < numLayers; i++){
			layers[i].draw(g);
		}
	}
	
}
