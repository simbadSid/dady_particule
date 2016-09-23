package main.java.util;





public class AngleConverter
{
	public static double degreeToGradiant(double degree)
	{
		return degree * Math.PI / 180;
	}


	public static double gradiantToDegree(double gradiant)
	{
		return gradiant * 180. / Math.PI;
	}
}
