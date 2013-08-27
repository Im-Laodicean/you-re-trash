package entity;

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

	@Override
	public void renderSelf() {
		img.draw(x, y, width, height);
	}

}
