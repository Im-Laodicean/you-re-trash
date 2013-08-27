package entity;

import java.util.List;

import render.RenderableObject;
import render.TexturedImage;

public class TestEntity extends Entity implements RenderableObject{
	private TexturedImage img;
	public TestEntity(){
		super(0,0,100,100);
		img = new TexturedImage("Link.png", this.hashCode());
	}
	@Override
	public boolean isRenderable() {
		return true;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWidth(int w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeight(int h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderSelf() {
		img.draw(x, y, width, height);
	}
	@Override
	public void handleKeyInputs(List<Integer> keys) {
		//do nothing
	}

}
