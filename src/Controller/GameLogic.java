package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import Model.Player;

public class GameLogic {
	private Player player1, player2;
	private int width, height;
	private Color colorP1, colorP2;
	
	//Coordonnées de départ des joueurs
	private int P1StartX = 900;
	private int P1StartY = 400;
	private int P2StartX = 300;
	private int P2StartY = 400;

	public GameLogic(int maxWidth, int maxHeight)
	{
		width = maxWidth;
		height = maxHeight;
		colorP1 = new Color(3,3,173);
		colorP2 = new Color(173, 3, 3 );

		player1 = new Player("BLEU",Player.LEFT, colorP1, P1StartX, P1StartY);  //commence de la droite
		player2 = new Player("ROUGE", Player.RIGHT, colorP2, P2StartX, P2StartY);  //commence de la gauche 
	}


	public void setP1Direction(int direct)
	{
		player1.setDirection(direct);
	}

	public void setP2Direction(int direct)
	{
		player2.setDirection(direct);
	}

	public String getP1Name()
	{
		return player1.getName();
	}

	public String getP2Name()
	{
		return player2.getName();
	}

	public Player getP1(){
		return player1;
	}

	public Player getP2(){
		return player2;
	}


	public boolean stepAndOk()
	{

		player1.step();
		player2.step();
		//Permet de connaître le Uptime du Jeu, et donc la durée de la partie.
		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		long uptime = rb.getUptime();
		
		if(player1.loosed(player2, width, height))
		{
			player2.isWinner(uptime);
			return false;
		}
		else if(player2.loosed(player1, width, height))
		{
			player1.isWinner(uptime);
			return false;
		}
		return true;
	}


	public void  restart()
	{
		player1 = new Player(player1.getName(), Player.LEFT, colorP1,P1StartX, P1StartY);
		player2 = new Player(player2.getName(), Player.RIGHT, colorP2,P2StartX, P2StartY);
	}



	public void draw(Graphics g)
	{
		player1.draw(g);
		player2.draw(g);
	}

}
