package entity;

public abstract class Entity {

	protected int x,y,width,height;
	
	public Entity(){
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}
	
	public Entity(int x, int y, int width, int height){
		
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
	
	protected abstract void setX();
	protected abstract void setY();
	protected abstract void setWidth();
	protected abstract void setHeight();
}
