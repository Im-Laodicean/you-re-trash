package mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MapEditorTile {

	protected int width,height,x,y,frame;
	protected BufferedImage img;
	protected boolean passable;
	
	public MapEditorTile(int x, int y, int width, int height, int frame, boolean pass){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.passable = pass;
		this.frame = frame;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}
	
	public int getFrame(){
		return frame;
	}
	
	public void setFrame(int num){
		this.frame = num;
	}
	
	public void draw(Graphics g){
		//Draws bounds box
		g.setColor(Color.green);
		if(!passable)
			g.setColor(Color.red);
		g.drawRect(x, y, width, height);
		
		if(img != null)
			g.drawImage(img, x, y, width, height, null, null);
	}
	
}
