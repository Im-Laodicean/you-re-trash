package entity;

import java.util.List;
import java.util.Map;

import engine.Physics;
import engine.Physics.TrajectoryType;

public abstract class Entity {
	
	protected TrajectoryType trajectoryType;
	protected int x,y,width,height;
	protected int renderX, renderY;
	protected double velocityX, velocityY;
	
	protected Entity(){
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		trajectoryType = trajectoryType.NONE;
	}
	
	protected Entity(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		trajectoryType = trajectoryType.NONE;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getRenderX(){
		return renderX;
	}
	
	public int getRenderY(){
		return renderY;
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
	
	public void setRenderX(int renderX){
		this.renderX = renderX;
	}
	
	public void setRenderY(int renderY){
		this.renderY = renderY;
	}
	
	public void setWidth(int w){
		width = w;
	}
	public void setHeight(int h){
		this.height = h;
	}
	public abstract void handleKeyInputs(Map<Integer, Boolean> keys);
	
	public void setXVelocity(double vel){
		velocityX = vel;
	}
	
	public void setYVelocity(double vel){
		velocityY = vel;
	}	
	
	
	public TrajectoryType getTrajectoryType(){
		return trajectoryType;
	}
	
	public void setNoTrajectory(){
		setXVelocity(0);
		setYVelocity(0);
		trajectoryType = TrajectoryType.NONE;
	}
	
	public void setLinearTrajectory(double initX, double initY){
		setXVelocity(initX);
		setYVelocity(initY);
		trajectoryType = TrajectoryType.LINEAR;
	}
	
	public void setBallisticTrajectory(double initX, double initY){
		setXVelocity(initX);
		setYVelocity(initY);
		trajectoryType = TrajectoryType.BALLISTIC;
	}
	
	public void setBallisticTrajectory(){
		trajectoryType = TrajectoryType.BALLISTIC;
	}
}
