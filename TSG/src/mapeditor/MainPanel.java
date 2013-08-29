package mapeditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainPanel extends JPanel implements MouseListener{
	
	protected int width, height;
	protected int tileWidth, tileHeight;
	protected int row,col;
	protected int mapLayer;
	protected MapEditorTile selectedTile;
	protected MapEditorMap map;

	private boolean mousePressed;
	
	public MainPanel(){
		super();
		this.addMouseListener(this);
		mousePressed = false;
		this.setFocusable(true);
		makeNewMap(25,25,32,32);
	}
	
	public void makeNewMap(int row, int col, int tileHeight, int tileWidth){
		this.row = row;
		this.col = col;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		
		this.setSize(new Dimension(col*tileWidth,row*tileHeight));
		this.setPreferredSize(this.getSize());
		this.setMaximumSize(this.getSize());
		
		map = new MapEditorMap(row,col,1);
		mapLayer = 0;
	}

	public void setSelectedTile(BufferedImage select, int frame){
		selectedTile = new MapEditorTile(0,0,tileWidth,tileHeight,frame,true);
		selectedTile.img = select;
	}

	public void setVariables(){
		width = this.getWidth();
		height= this.getHeight();
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
      
		//Clear Screen
		g.setColor(Color.white);
		if(getWidth() > 0 && getHeight() > 0)
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if(map != null){
			map.draw(g);
		}
		
		//Only draw the grid if both the row and col are greater than 0, meaning that a map was map
		if(row > 0 && col > 0){
			g.setColor(Color.GREEN);
			for(int i = 0; i < col; i++){
				g.drawLine(i*tileWidth, 0, i*tileWidth, row*tileHeight);

			}
			for(int i = 0; i < row; i++){
				g.drawLine(0, i*tileHeight, col*tileWidth, i*tileHeight);
			}
			
			g.drawRect(0, 0, tileWidth*col, tileHeight*row);
		}
		
		g.setColor(Color.black);
		if(this.getMousePosition() != null){
			Point location = getMousePosition();
			int row = ((int)location.getY()/tileHeight)%tileHeight;
			int col = ((int)location.getX()/tileWidth)%tileWidth;
			g.drawString("(Row, Col): (" + row + " , "+ col + ")", 5, 10);
		}
		else{
			g.drawString("(Row, Col): (Mouse Not in Focus)", 5, 10);
		}
	}
	
	public void update(){
		if(mousePressed){
			if(getMousePosition() != null && map != null && selectedTile != null){
				int row = ((int)getMousePosition().getY()/tileHeight)%tileHeight;
				int col = ((int)getMousePosition().getX()/tileWidth)%tileWidth;
				
				int x = col * tileWidth;
				int y = row * tileHeight;
				
				selectedTile.setX(x);
				selectedTile.setY(y);
				
				map.getLayers()[mapLayer].getTiles()[row][col] = new MapEditorTile(selectedTile);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
	
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		mousePressed = true;		
	}

	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
	}

}
