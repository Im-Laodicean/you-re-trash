package mapeditor;

import java.awt.Graphics;

public class MapEditorMapLayer {

	protected int row,col;
	protected MapEditorTile[][] tiles;
	
	public MapEditorMapLayer(int row, int col){
		this. row = row;
		this.col = col;
		tiles = new MapEditorTile[row][col];
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

	public MapEditorTile[][] getTiles() {
		return tiles;
	}

	public void setTiles(MapEditorTile[][] tiles) {
		this.tiles = tiles;
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				tiles[i][j].draw(g);
			}
		}
	}
}
