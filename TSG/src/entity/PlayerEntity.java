package entity;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.lwjgl.input.Keyboard;
import org.xml.sax.SAXException;

import engine.GameInputHandler;
import engine.Physics;
import engine.Physics.TrajectoryType;
import render.RenderableObject;
import render.Sprite;
import tools.ConfigFileReader;
import tools.ResourceManager;

import static engine.GameInputHandler.KeyBind;

public class PlayerEntity extends Entity implements RenderableObject{

	private String name;
	private int key;
	private Sprite player;
	private double moveSpeed;
	private List<GameInputHandler.KeyBind>keyBinds;


	public PlayerEntity(String name) throws IOException{
		super(20,20,100,100);
		this.setName(name);
		key = name.hashCode();
		ResourceManager.loadTexture("smurf_sprite.png", key);
		player = new Sprite(key, 128, 128, 4, 4, 10);
		moveSpeed = 2;
		try {
			keyBinds = ConfigFileReader.readKeyBindings();
		} catch (SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
		player.drawNextFrame(renderX, renderY, delta);
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
	public void handleKeyInputs(Map<KeyBind, Boolean> keys) {
		if(keys.size()==0)
			return;
		System.out.println(keys);
		System.out.println(keys.containsKey(keyBinds.get(0)));
		for(KeyBind kb:keyBinds){
			Boolean state = keys.get(kb);
		}
	}

}
