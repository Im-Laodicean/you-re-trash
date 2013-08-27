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
		this.setName(name);
		key = name.hashCode();
		ResourceManager.loadTexture("Riven.png", key);
		player = new Sprite(key, this.getWidth(), this.getHeight(), 4, 4);
	}
	
	@Override
	public void setX() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight() {
		// TODO Auto-generated method stub
	}
	
	public void renderSelf() {
		player.drawNextFrame(x, y);
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
	
}
