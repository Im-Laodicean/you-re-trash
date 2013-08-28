package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel{

	private static final long serialVersionUID = -2462774421884075753L;
	
	protected int width, height;
	
	public MainPanel(){
		super();
		
	}
	
	public void setVariables(){
		width = this.getWidth();
		height= this.getHeight();
	}
	
	public void paint(Graphics g){
		//Clear Screen
		g.setColor(Color.white);
		if(getWidth() > 0 && getHeight() > 0)
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
