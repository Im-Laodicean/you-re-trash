package mapeditor;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class MapEditorWindow extends JFrame{

	private static final long serialVersionUID = -7280454809807739828L;

	protected MainPanel mainPanel;
	protected TileSetPanel tileSetPanel;
	protected TileSetWindow tileSetWindow;
	protected BufferedImage selectedTile;

	public MapEditorWindow(){
		super("Map Editor");
		super.setSize(new Dimension(1000,800));


		//Set up winodws/panels
		mainPanel = new MainPanel();
		this.add(mainPanel);
		tileSetPanel = new TileSetPanel();
		tileSetWindow = new TileSetWindow(this,tileSetPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		startGraphicsThread();
	}

	public void startGraphicsThread(){
		Thread graphics = new Thread(){
			public void run(){
				while(true){
					repaint();
					tileSetWindow.repaint();
					selectedTile = tileSetPanel.getSelectedImage();
					
					if(selectedTile != null){
						mainPanel.setSelectedTile(selectedTile);
					}
					
					try {
						for(int i = 0; i < 30; i ++){
							Thread.sleep(1);
							Thread.yield();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		graphics.start();
	}


	public static void main(String[] args) {
		new MapEditorWindow();
	}

}
