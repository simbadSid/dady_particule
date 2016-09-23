package main.java.gui.util;

import main.java.util.PointDouble;






public class SpaceConverter
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Double xMin_pixel;
	private Double xDist_pixel;
	private Double zMin_pixel;
	private Double zDist_pixel;

	private double xMin_real;
	private double xDist_real;
	private double zMin_real;
	private double zMax_real;
	private double zDist_real;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public SpaceConverter(double xMin_real, double xMax_real, double zMin_real, double zMax_real)
	{
		this.xMin_real		= xMin_real;
		this.xDist_real		= xMax_real - xMin_real;
		this.zMin_real		= zMin_real;
		this.zMax_real		= zMax_real;
		this.zDist_real		= zMax_real - zMin_real;
	}


// -------------------------------------------------
// Getter/Setter
// -------------------------------------------------
	public void setPixelDimension(Integer xMin_pixel, Integer xMax_pixel, Integer zMin_pixel, Integer zMax_pixel)
	{
		this.xMin_pixel		= (xMin_pixel == null) ? null : new Double(xMin_pixel);
		this.zMin_pixel		= (zMin_pixel == null) ? null : new Double(zMin_pixel);
		this.xDist_pixel	= ((xMin_pixel == null) || (xMax_pixel == null)) ? null : new Double(xMax_pixel - xMin_pixel);
		this.zDist_pixel	= ((zMin_pixel == null) || (zMax_pixel == null)) ? null : new Double(zMax_pixel - zMin_pixel);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void convertRealToPixel(double xReal, double zReal, PointDouble result)
	{
		double tx	= (xReal - xMin_real) / xDist_real;
		double ty	= (zReal - zMax_real) / zDist_real;
		result.x	= (int)(tx	* xDist_pixel);
		result.z	= (int)(-ty	* zDist_pixel);
		
		
/*		
		double tx	= xReal / xDist_pixel;
		double tz	= zReal / zDist_pixel;
		result.x = (xMin_pixel + tx * (xDist_real));
		result.z = (zMin_pixel + tz * (zDist_real));
*/

/*
		double xProportion = (xReal - this.xMin_real) / xDist_real;
		double zProportion = (zReal - this.zMin_real) / zDist_real;

		double xPixel = xProportion * xDist_pixel + this.xMin_pixel;
		double zPixel = zProportion * zDist_pixel + this.zMin_pixel;

		result.x = xPixel;
		result.z = zPixel;
*/
	}


	//	xMax	
	//	xMin			xMin_real
	//	dx				xDist_real
	//	yMax
	//	yMin			zMin_real
	//	dy				zDist_real
	
	//	widthWindow		xDist_pixel
	//	heightWindow	zDist_pixel
	
	public void convertPixelToReal(int xPixel, double zPixel, PointDouble result)
	{
		double tx	= xPixel / xDist_pixel;
		double tz	= zPixel / zDist_pixel;
		result.x = xMin_real + tx * xDist_real;
		result.z = zMax_real - tz * zDist_real;

/*
		double tx	= xPixel / xDist_real;
		double tz	= zPixel / zDist_real;
		result.x = (xMin_real + tx * (xDist_pixel));
		result.z = (zMin_real + tz * (zDist_pixel));
*/

/*
		double xProportion = (xPixel - this.xMin_pixel) / xDist_pixel;
		double zProportion = (zPixel - this.zMin_pixel) / zDist_pixel;

		double xReal = xProportion * xDist_real + this.xMin_real;
		double zReal = zProportion * zDist_real + this.zMin_real;

		result.x = xReal;
		result.z = -zReal;
*/
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------

}
