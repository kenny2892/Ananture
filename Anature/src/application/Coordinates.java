package application;

import java.io.Serializable;

public class Coordinates implements Serializable
{
	private static final long serialVersionUID = -6610445021830796677L;
	public double x, y;
	
	public Coordinates(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
}