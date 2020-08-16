package Project;

public class Point {
	
	private int x;
	
	private int y;
	
	boolean attack;
	
	public Point (int num1, int num2, boolean bool)
	{
		x=num1;
		y=num2;
		attack=bool;
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	public boolean isAttack()
	{
		return attack;
	}
	public boolean matches(int num1, int num2)
	{
		if(num1==x && num2==y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	

}
