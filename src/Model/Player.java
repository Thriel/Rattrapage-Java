package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Player {

	private String name;
	private int direction;
	private LengthNode head;
	private Color color;

	public static int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;


	public static int PIX_PER_STEP = 5, THIKNES = 5;

	public Player(String name, int direction, Color color, int x, int y)
	{
		this.name = name;
		this.direction = direction;
		this.color = color;
		head = new LengthNode(x, y, null);
	}

	public String getName() {
		return name;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}



	public int getDirection() {
		return direction;
	}



	public LengthNode getHead() {
		return head;
	}

	public void step()
	{
		switch(direction)
		{
		case 1: 		//haut
			head = new LengthNode(head.getX(), head.getY() - PIX_PER_STEP, head);
			break;
		case 2: 		//bas
			head = new LengthNode(head.getX(), head.getY() + PIX_PER_STEP, head);
			break;
		case 3: 		//gauche
			head = new LengthNode(head.getX() - PIX_PER_STEP, head.getY(), head);
			break;
		case 4: 		//droite
			head = new LengthNode(head.getX() + PIX_PER_STEP, head.getY(), head);
			break;
		}

	}



	public boolean loosed(Player other, int maxWidth, int maxHeight)
	{
		//joueur se touche lui-même
		for(LengthNode p = head.getNext(); p.getNext() != null ; p = p.getNext())
			if(head.sameValues(p))
				return true;
		//il touche un autre joueur
		for(LengthNode p = other.getHead(); p.getNext() != null ; p = p.getNext())
			if(head.sameValues(p))
				return true;
		//il touche les bordures
		if(head.getX() < 0 || head.getY() < 0 || head.getX() > maxWidth + THIKNES || head.getY() > maxHeight + THIKNES)
			return true;

		return false;
	}
	
	public void isWinner(long uptime) {
		
		float Uptime = uptime;
		Uptime = Uptime/1000;
		String Time = Float.toString(Uptime);
		
		try {
			String url="jdbc:mysql://chabanvpn.ovh:3306/tron?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
		    String user="tron";
		    String password="root";
		   
		    Connection conn = null;
		    conn = DriverManager.getConnection(url, user, password);
		    System.out.println("[SQL] - Connecté à la BDD!");
		    
		    CallableStatement cStmt = conn.prepareCall("{call add_record(?, ?)}");
		    cStmt.setString(1, this.getName());
		    cStmt.setString(2, Time);
		    cStmt.execute();
		    System.out.println("[SQL] - Enregistré sur la BDD!");


		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		System.out.println(">>> Le joueur " + this.getName() + " à gagné!");
		System.out.println(">>> Durée d'éxécution: " + Uptime + "s");
		
		JOptionPane.showMessageDialog(null, "Le joueur " + this.getName() + " à gagné!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	}


	public void draw(Graphics g)
	{
		g.setColor(color);
		LengthNode p = head;
		for(;p != null; p=p.getNext()) {
			g.fillRect(p.getX(), p.getY(), THIKNES, THIKNES);	//tous les joueurs
		}
	}
	
	
}




