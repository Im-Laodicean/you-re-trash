package entity;

import java.util.List;

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
		img.draw(x, y, width, height);
	}
	@Override
	public void handleKeyInputs(List<Integer> keys) {
		//do nothing
	}
}
