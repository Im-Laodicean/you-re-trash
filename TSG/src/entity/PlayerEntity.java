package entity;


import java.io.IOException;
import java.util.List;

import org.lwjgl.input.Keyboard;

import engine.Physics;
import render.RenderableObject;
import render.Sprite;
import tools.ResourceManager;

public class PlayerEntity extends Entity implements RenderableObject{

	private String name;
	private int key;
	private Sprite player;
	
	public PlayerEntity(String name) throws IOException{
		super(20,20,100,100);
		this.setName(name);
		key = name.hashCode();
		ResourceManager.loadTexture("smurf_sprite.png", key);
		player = new Sprite(key, 128, 128, 4, 4, 10);
	}
	
	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void setWidth(int w) {
		width = w;
	}

	@Override
	public void setHeight(int h) {
		height = h;
	}
	
	public void renderSelf(double delta) {
		player.drawNextFrame(x, y, delta);
	}
	@Override
	public boolean isRenderable() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void handleKeyInputs(List<Integer> keys) {
		if(keys.contains(Keyboard.KEY_D)){
			setLinearTrajectory(2, 0);
			player.startAnimation();
		}
		else if(keys.contains(Keyboard.KEY_A)){
			setLinearTrajectory(-2, 0);
			player.startAnimation();
		}
		
		if(keys.contains(Keyboard.KEY_SPACE)&&getTrajectoryType()!=Physics.BALLISTIC){
				setBallisticTrajectory();
		}
//		else{
//			setNoTrajectory();
//			player.pauseAnimation();
//		}
	}
	
}
