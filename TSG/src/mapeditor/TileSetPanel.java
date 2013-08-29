package mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TileSetPanel extends JPanel implements MouseListener{

	private static final long serialVersionUID = 4025159182460789096L;
	private String imagePath;
	private BufferedImage img;
	private ArrayList<BufferedImage> frames;
	private int width, height;
	private BufferedImage selectedFrame;

	public TileSetPanel(){
		super();
		this.setSize(new Dimension(500,500));
		this.setPreferredSize(new Dimension(500,500));
		imagePath = "";
		img = null;
		this.addMouseListener(this);

		frames = new ArrayList<BufferedImage>();
		width = 32;
		height = 32;
	}

	public boolean setFile(File file){
		this.imagePath = file.getPath();
		if(tryAndLoadImg(file) && img != null){
			this.setSize(new Dimension(img.getWidth(),img.getHeight()));
			this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
			breakImageIntoSubImages();
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

			g.setColor(Color.green);
			for(int i = 0; i < img.getWidth()/width; i++){
				g.drawLine(i*width, 0, i*width, img.getHeight());

			}
			for(int i = 0; i < img.getHeight() /height; i++){
				g.drawLine(0, i*height, img.getWidth(), i*height);
			}
			
			//Draw selected box
			if(this.getMousePosition() != null){
				int row = ((int)this.getMousePosition().getY()/height)%height;
				int col = ((int)this.getMousePosition().getX()/width)%width;
			
				g.setColor(Color.red);
				g.drawRect(col*width, row*height, width, height);
			}
			
		}


	}

	public void breakImageIntoSubImages(){
		if(img != null){
			for(int i = 0; i < img.getHeight()/height; i++){
				for(int j = 0; j < img.getWidth()/width; j++){
					frames.add(img.getSubimage(j*width, i*height, width, height));
				}
			}
		}
		else{
			return;
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getPoint() != null){
			int row = ((int)e.getPoint().getY()/height)%height;
			int col = ((int)e.getPoint().getX()/width)%width;
			
			int frame = 0;
			
			selectedFrame = frames.get(frame);
		}

	}

	public BufferedImage getSelectedImage(){
		return selectedFrame;
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
