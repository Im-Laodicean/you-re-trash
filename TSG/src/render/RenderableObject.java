package render;

public abstract class RenderableObject {
	
	//Variables
	protected int x, y, width, height;
	public abstract void renderSelf();
	
	//Constructors
	protected RenderableObject(){
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}
	
	protected RenderableObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}