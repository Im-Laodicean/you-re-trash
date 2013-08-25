package entity;


import java.io.IOException;

import render.RenderableObject;
import render.Sprite;
import tools.ResourceManager;

public class PlayerEntity extends Entity implements RenderableObject{

	private String name;
	private int key;
	private Sprite player;
	public PlayerEntity(String name) throws IOException{
		super(20,20,100,100);
		this.name = name;
		key = name.hashCode();
		ResourceManager.loadTexture("test.png", key);
		player = new Sprite(key, 100, 100, 2, 2);
	}
	
	@Override
	protected void setX() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setY() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setWidth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setHeight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderSelf() {
		player.drawNextFrame(x, y);
	}
	
}
