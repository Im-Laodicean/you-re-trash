package entity;

public abstract class Entity {

	protected int x,y,width,height;
	
	protected Entity(){
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}
	
	protected Entity(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public abstract boolean isRenderable();
	
	public abstract void setX();
	public abstract void setY();
	public abstract void setWidth();
	public abstract void setHeight();
}
