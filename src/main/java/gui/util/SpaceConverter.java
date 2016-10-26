package main.java.gui.util;

import main.java.util.PointDouble;






public class SpaceConverter
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Double xDist_pixel	= null;
	private Double zDist_pixel	= null;

	private double xMin_real;
	private double xDist_real;
	private double zMax_real;
	private double zDist_real;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public SpaceConverter(double xMin_real, double xMax_real, double zMin_real, double zMax_real)
	{
		this.xMin_real		= xMin_real;
		this.xDist_real		= xMax_real - xMin_real;
		this.zMax_real		= zMax_real;
		this.zDist_real		= zMax_real - zMin_real;

		assert(this.xDist_real >= 0);
		assert(this.zDist_real >= 0);
	}


// -------------------------------------------------
// Getter/Setter
// -------------------------------------------------
	public double getXMin_real ()	{return this.xMin_real;}
	public double getXDist_real ()	{return this.xDist_real;}


	public void setPixelDimension(Integer xMin_pixel, Integer xMax_pixel, Integer zMin_pixel, Integer zMax_pixel)
	{
		this.xDist_pixel	= ((xMin_pixel == null) || (xMax_pixel == null)) ? null : new Double(xMax_pixel - xMin_pixel);
		this.zDist_pixel	= ((zMin_pixel == null) || (zMax_pixel == null)) ? null : new Double(zMax_pixel - zMin_pixel);

		assert((this.xDist_pixel == null) || (this.xDist_pixel >= 0));
		assert((this.zDist_pixel == null) || (this.zDist_pixel >= 0));
	}


	public void zoom(double xReal, double zReal, double zoomSize)
	{
		double newXDist_real	= this.xDist_real * zoomSize;
		double newZDist_real	= this.zDist_real * zoomSize;

		this.xMin_real	= xReal - newXDist_real/2;
		this.zMax_real	= zReal + newZDist_real/2;
		this.xDist_real	= newXDist_real;
		this.zDist_real	= newZDist_real;
		this.xDist_pixel= (this.xDist_pixel == null) ? null : this.xDist_pixel * zoomSize;
		this.zDist_pixel= (this.zDist_pixel == null) ? null : this.zDist_pixel * zoomSize;
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public boolean isInitialized()
	{
		return ((this.xDist_pixel != null) && (this.zDist_pixel != null));
	}


	public void convertRealToPixel(double xReal, double zReal, PointDouble result)
	{
		double tx	= (xReal - xMin_real) / xDist_real;
		double ty	= (zReal - zMax_real) / zDist_real;
		result.x	= (int)(tx	* xDist_pixel);
		result.z	= (int)(-ty	* zDist_pixel);
	}


	public void convertPixelToReal(int xPixel, double zPixel, PointDouble result)
	{
		double tx	= xPixel / xDist_pixel;
		double tz	= zPixel / zDist_pixel;
		result.x = xMin_real + tx * xDist_real;
		result.z = zMax_real - tz * zDist_real;
	}


	public void getAxesLimitsPixel(PointDouble horizontalLeft, PointDouble horizontalRight, PointDouble verticalTop, PointDouble verticalBottom)
	{
		this.convertRealToPixel(xMin_real,				0, 						horizontalLeft);
		this.convertRealToPixel(xMin_real+xDist_real,	0, 						horizontalRight);
		this.convertRealToPixel(0,						zMax_real,				verticalTop);
		this.convertRealToPixel(0,						zMax_real-zDist_real,	verticalBottom);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------

}
