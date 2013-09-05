package entity;

import java.util.List;
import java.util.Map;

import engine.GameInputHandler;
import engine.Physics;
import render.RenderableObject;
import render.TexturedImage;

public class TestEntity extends Entity implements RenderableObject{
	private TexturedImage img;
	public TestEntity(){
		super(0,500,100,100);
		img = new TexturedImage("Link.png", this.hashCode());
		setBallisticTrajectory(6, 10);
	}
	@Override
	public boolean isRenderable() {
		return true;
	}

	@Override
	public void renderSelf(double delta) {
		img.draw(renderX, renderY, width, height);
	}
	@Override
	public void handleKeyInputs(Map<GameInputHandler.KeyBind, Boolean> keys) {
		// TODO Auto-generated method stub
		
	}
}
