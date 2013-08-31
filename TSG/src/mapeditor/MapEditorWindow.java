package mapeditor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import xml.XMLReader;

public class MapEditorWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = -7280454809807739828L;

	protected MainPanel mainPanel;
	protected TileSetPanel tileSetPanel;
	protected TileSetWindow tileSetWindow;
	protected BufferedImage selectedTile;
	protected JScrollPane scrollPane;
	
	protected JMenuBar menu;
	protected JMenu file;
	protected JMenuItem newMap,openMap,saveMap;

	public MapEditorWindow(){
		super("Map Editor");
		super.setSize(new Dimension(1000,800));

		//Set up winodws/panels
		mainPanel = new MainPanel();
		scrollPane = new JScrollPane(mainPanel);
		this.add(scrollPane);
		tileSetPanel = new TileSetPanel();
		tileSetWindow = new TileSetWindow(this,tileSetPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		setUpMenu();
		
		
		startGraphicsThread();
	}
	
	public void setUpMenu(){
		menu = new JMenuBar();
		file = new JMenu("File");
		file.addActionListener(this);
		
		newMap = new JMenuItem("New Map");
		newMap.addActionListener(this);
		
		openMap = new JMenuItem("Open Map");
		openMap.addActionListener(this);
		
		saveMap = new JMenuItem("Save Map");
		saveMap.addActionListener(this);
		
		file.add(newMap);
		file.add(openMap);
		file.add(saveMap);
		
		menu.add(file);
		
		this.setJMenuBar(menu);
	}

	public void startGraphicsThread(){
		Thread graphics = new Thread(){
			public void run(){
				while(true){
					//Updates
					mainPanel.update();
					
					if(tileSetPanel.getSelectedImage() != null)
						mainPanel.setSelectedTile(tileSetPanel.getSelectedImage(), 
								tileSetPanel.getSelectedFrame());
					
					//Repaint
					repaint();
					tileSetWindow.repaint();
					
					//Sleep
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


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newMap){
			int row = Integer.parseInt(JOptionPane.showInputDialog("Number of rows: "));
			int col = Integer.parseInt(JOptionPane.showInputDialog("Number of cols: "));
			int tile = Integer.parseInt(JOptionPane.showInputDialog("Size of tiles: : "));
			
			mainPanel.makeNewMap(row, col, tile, tile);
		}
		
		if(e.getSource() == saveMap){
			XMLReader.saveMapInXML("sdfasdf.xml", mainPanel.map);
		}
		
	}

	public static void main(String[] args) {
		new MapEditorWindow();
	}
}
