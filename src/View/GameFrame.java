package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final int FRAME_WIDTH = 1200, FRAME_HEIGHT = 800;

	
	public GamePanel gamePanel;

	public GameFrame()
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Tron Java - Ivan CHABAN A1");
		setResizable(false);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
		gamePanel = new GamePanel();
		
		
		gamePanel.setVisible(true);
		add(gamePanel);
		gamePanel.start();
		
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		
			gamePanel.setVisible(true);
			add(gamePanel);
			gamePanel.start();
		
	}

}
