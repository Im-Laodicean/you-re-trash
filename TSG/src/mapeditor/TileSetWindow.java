package mapeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class TileSetWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	protected TileSetPanel panel;
	protected JScrollPane scrollPane;
	protected File tileSetFile;
	
	
	public TileSetWindow(JFrame frame, TileSetPanel panel){
		//Default JFrame operations
		super("Tile Set");
		this.setSize(new Dimension(250,500));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(frame);
		
		//Adding objects to the frame
		this.panel = panel;
		scrollPane = new JScrollPane(panel);
		this.setLayout(new BorderLayout());
		this.add(scrollPane);
		
		//Menu
		menu = new JMenuBar();
		addCompsToMenu();
		this.setJMenuBar(menu);
	}
	
	protected JMenuBar menu;
	protected JMenu file;
	protected JMenuItem open;
	protected JFileChooser fc = new JFileChooser();
	
	public void addCompsToMenu(){
		file = new JMenu("File");
		file.addActionListener(this);
		
		open = new JMenuItem("Open");
		open.addActionListener(this);
		
		file.add(open);
		
		menu.add(file);
	}
	


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == open){
			 int returnVal = fc.showOpenDialog(this);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            tileSetFile = fc.getSelectedFile();
		            if(panel.setFile(tileSetFile)){
		            	
		            	repaint();
		            }
		        } else {
		            System.out.println("No tileset choosen");
		        }
		}
	}
	
}
