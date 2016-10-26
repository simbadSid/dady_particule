package main.java.util;





public class PointDouble
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	public double x;
	public double z;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PointDouble(double x, double z)
	{
		this.x = x;
		this.z = z;
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override
	public String toString()
	{
		return "(" + this.x + ", " + this.z + ")";
	}
}
