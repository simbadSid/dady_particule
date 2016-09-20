package main.java.gui.util;






public class SpaceConverter
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private double xMin_pixel;
	private double xMax_pixel;
	private double yMin_pixel;
	private double yMax_pixel;

	private double xMin_real;
	private double xMax_real;
	private double yMin_real;
	private double yMax_real;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public SpaceConverter(double xMin_pixel, double xMax_pixel, double yMin_pixel, double yMax_pixel, double xMin_real, double xMax_real, double yMin_real, double yMax_real)
	{
		this.xMin_pixel	= xMin_pixel;
		this.xMax_pixel	= xMax_pixel;
		this.yMin_pixel	= yMin_pixel;
		this.yMax_pixel	= yMax_pixel;

		this.xMin_real	= xMin_real;
		this.xMax_real	= xMax_real;
		this.yMin_real	= yMin_real;
		this.yMax_real	= yMax_real;
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------


// -------------------------------------------------
// Private methods
// -------------------------------------------------

}
