package mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TileSetPanel extends JPanel implements MouseListener{

	private static final long serialVersionUID = 4025159182460789096L;
	private String imagePath;
	private BufferedImage img;
	
	public TileSetPanel(){
		super();
		this.setSize(new Dimension(500,500));
		this.setPreferredSize(new Dimension(500,500));
		imagePath = "";
		img = null;
	}
	
	public boolean setFile(File file){
		this.imagePath = file.getPath();
		if(tryAndLoadImg(file) && img != null){
			this.setSize(new Dimension(img.getWidth(),img.getHeight()));
			this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
			return true;
		}
		else{
			System.out.println("Image loading failed...");
			return false;
		}
	}
	
	private boolean tryAndLoadImg(File file){
		try{
			if(!imagePath.isEmpty()){
				img = ImageIO.read(file);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void paint(Graphics g){
		//Clear Screen
		g.setColor(Color.black);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		
		//Draw if img is loaded
		if(img != null){
			g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
