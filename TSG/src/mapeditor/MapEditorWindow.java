package mapeditor;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	private static final long serialVersionUID = -7280454809807739828L;

	protected MainPanel mainPanel;
	
	
	public Window(){
		super("Map Editor");
		super.setSize(new Dimension(1000,800));
		
		mainPanel = new MainPanel();
		this.add(mainPanel);
		
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
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		};
		
		graphics.start();
	}
	

	public static void main(String[] args) {
		new Window();
	}

}
