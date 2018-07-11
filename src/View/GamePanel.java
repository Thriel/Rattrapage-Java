package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Controller.GameLogic;
import Model.Player;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameLogic logic;
	
	public static final int W = 1200, H = 800;


	


	Timer timerGeneral;
	Timer timerBullet;

	public GamePanel() {
		
		
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		setSize(W, H);
	
		
		
		logic = new GameLogic(W, H);
		timerGeneral = new Timer(30, this);

	}

	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		logic.draw(g);
	}


	
	public void start(){
		
		logic.restart();
		timerGeneral.start();
		System.out.println(">>> Taille de la grille: " + getWidth() + " X " + getHeight());
	}
	
	

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==timerGeneral)
		{
			
			if(logic.stepAndOk())
				repaint();
			else
			{
				timerGeneral.stop();	
			}
			repaint();
		}
		if(e.getSource() == timerBullet)
		{
			repaint();
		}

		

	}

	public void keyPressed(KeyEvent e) {
		/*
		 * Touches du joueur 1
		 */
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(logic.getP1().getDirection() != Player.UP)
				logic.setP1Direction(Player.DOWN);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			if(logic.getP1().getDirection() != Player.DOWN)
				logic.setP1Direction(Player.UP);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(logic.getP1().getDirection() != Player.RIGHT)
				logic.setP1Direction(Player.LEFT);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(logic.getP1().getDirection() != Player.LEFT)
				logic.setP1Direction(Player.RIGHT);
		}

		/*
		 * Touches du joueur 2
		 */
		if(e.getKeyCode() == KeyEvent.VK_S){
			if(logic.getP2().getDirection() != Player.UP)
				logic.setP2Direction(Player.DOWN);
		}
		else if(e.getKeyCode() == KeyEvent.VK_Z){
			if(logic.getP2().getDirection() != Player.DOWN)
				logic.setP2Direction(Player.UP);
		}
		else if(e.getKeyCode() == KeyEvent.VK_Q){
			if(logic.getP2().getDirection() != Player.RIGHT)
				logic.setP2Direction(Player.LEFT);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			if(logic.getP2().getDirection() != Player.LEFT)
				logic.setP2Direction(Player.RIGHT);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F2)
		{
			start();
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



}
