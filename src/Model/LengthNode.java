package Model;

public class LengthNode {

	
	private int x, y;
	private LengthNode next;
	
	public LengthNode(int x, int y, LengthNode next)
	{
		this.x = x;
		this.y = y;
		this.next = next;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public LengthNode getNext() {
		return next;
	}

	public void setNext(LengthNode next) {
		this.next = next;
	}
	
	public boolean sameValues(LengthNode other)
	{
		if(other.getX() == this.getX() && this.getY() == other.getY())
			return true;
		else
			return false;
	}
	

	
	
}
