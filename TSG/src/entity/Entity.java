package entity;

import java.util.List;

public abstract class Entity {

	protected int x,y,width,height;
	protected double velocityX, velocityY;
	
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
	
	public double getXVelocity(){
		return velocityX;
	}
	
	public double getYVelocity(){
		return velocityY;
	}
	
	public abstract boolean isRenderable();
	
	public abstract void setX(int x);
	public abstract void setY(int y);
	public abstract void setWidth(int w);
	public abstract void setHeight(int h);
	public abstract void handleKeyInputs(List<Integer> keys);
	
	public void setXVelocity(double vel){
		velocityX = vel;
	}
	
	public void setYVelocity(double vel){
		velocityY = vel;
	}
}
