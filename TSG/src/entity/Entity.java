package entity;

import java.util.List;

import engine.Physics;

public abstract class Entity {
	
	protected int trajectoryType;
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
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setWidth(int w){
		width = w;
	}
	public void setHeight(int h){
		this.height = h;
	}
	public abstract void handleKeyInputs(List<Integer> keys);
	
	public void setXVelocity(double vel){
		velocityX = vel;
	}
	
	public void setYVelocity(double vel){
		velocityY = vel;
	}	
	
	
	public int getTrajectoryType(){
		return trajectoryType;
	}
	
	public void setNoTrajectory(){
		setXVelocity(0);
		setYVelocity(0);
		trajectoryType = Physics.NONE;
	}
	
	public void setLinearTrajectory(double initX, double initY){
		setXVelocity(initX);
		setYVelocity(initY);
		trajectoryType = Physics.LINEAR;
	}
	
	public void setBallisticTrajectory(double initX, double initY){
		setXVelocity(initX);
		setYVelocity(initY);
		trajectoryType = Physics.BALLISTIC;
	}
	
	public void setBallisticTrajectory(){
		trajectoryType = Physics.BALLISTIC;
	}
}
