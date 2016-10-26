package main.java.gui.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.swing.JPanel;
import main.java.app.App;
import main.java.gui.Gui;
import main.java.gui.GuiResource;
import main.java.gui.util.EventListener_mouse;
import main.java.gui.util.EventListener_resizer;
import main.java.gui.util.SpaceConverter;
import main.java.util.PointDouble;





@SuppressWarnings("serial")
public class PanelDraw extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private SpaceConverter	spaceConverter;

	private BufferedImage 	bufferDraw;
	private Graphics2D		drawable;
	private Color			colorBG;
	private PointDouble		bufferConverter		= new PointDouble(-1, -1);
	private Color			colorAxes			= GuiResource.frame_panelDraw_colorAxes;
	private Color			colorParabolLimit	= GuiResource.frame_panelDraw_colorParabolaLimit;

// TODO
private Color			colorDraw = Color.YELLOW;
private boolean			printAxes			= GuiResource.frame_panelDraw_printAxes;
private boolean			printParabolaLimit	= GuiResource.frame_panelDraw_printParabolLimit;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelDraw(Gui gui) throws NoSuchMethodException, SecurityException
	{
		this.spaceConverter	= new SpaceConverter(App.xMin, App.xMax, App.zMin, App.zMax);

		EventListener_resizer	listenerResizer	= new EventListener_resizer(this, "setSize", false);
		EventListener_mouse		mouseListener	= new EventListener_mouse(gui, this.spaceConverter);

		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		this.addMouseWheelListener(mouseListener);
		this.addComponentListener(listenerResizer);
		this.clearAll();
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override
	public void paintComponent(Graphics g) 
	{
		g.drawImage(this.bufferDraw, UNDEFINED_CONDITION, UNDEFINED_CONDITION, null);
	}


	public void save(FileOutputStream outputFile)
	{
// TODO
	}


	public void setPanelShown (boolean show)
	{
		if (show == true)
		{
			this.colorBG = GuiResource.panelDraw_colorBG_show;
			this.spaceConverter.setPixelDimension(0, super.getWidth(), 0, super.getHeight());
		}
		else
		{
			this.colorBG = GuiResource.panelDraw_colorBG_hide;
			this.spaceConverter.setPixelDimension(null, null, null, null);
		}

		this.clearAll();
	}


	public void clearAll()
	{
		int width	= this.getWidth();
		int height	= this.getHeight();

		if ((width <= 0) || (height <= 0)) return;

		this.bufferDraw = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.drawable	= this.bufferDraw.createGraphics();

		this.drawable.setColor(this.colorBG);
		this.drawable.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (this.isPanelShown())
		{
			if (this.printAxes)				this.plotAxes();
			if (this.printParabolaLimit)	this.plotParabolaLimit();
		}

		this.drawable.setColor(this.colorDraw);
		this.repaint();
	}


	public void zoom(double xReal, double zReal, double zoomSize)
	{
		this.spaceConverter.zoom(xReal, zReal, zoomSize);
	}


	@Override
	public void setSize(int width, int height)
	{
		super.setSize(width, height);

		// Used to initialize the graphical processor
		if (this.bufferDraw == null)
			this.clearAll();
		if (this.isPanelShown())
			this.spaceConverter.setPixelDimension(0, width, 0, height);

//TODO resize the picture
this.clearAll();
	}


	public void plot(double xReal, double zReal)
	{
		this.spaceConverter.convertRealToPixel(xReal, zReal, this.bufferConverter);

		this.drawable.drawLine((int)bufferConverter.x, (int)bufferConverter.z, (int)bufferConverter.x, (int)bufferConverter.z);
// TODO obtimize
		this.repaint();
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	private boolean isPanelShown()
	{
		return this.colorBG.equals(GuiResource.panelDraw_colorBG_show);
	}


	private void plotAxes()
	{
		PointDouble horizontalLeft	= new PointDouble(-1, -1);
		PointDouble horizontalRight	= new PointDouble(-1, -1);
		PointDouble verticalTop		= new PointDouble(-1, -1);
		PointDouble verticalBottom	= new PointDouble(-1, -1);

		this.spaceConverter.getAxesLimitsPixel(horizontalLeft, horizontalRight, verticalTop, verticalBottom);
		this.drawable.setColor(this.colorAxes);
		this.drawable.drawLine((int)horizontalLeft.x,	(int)horizontalLeft.z,	(int)horizontalRight.x,	(int)horizontalRight.z);
		this.drawable.drawLine((int)verticalTop.x,		(int)verticalTop.z,		(int)verticalBottom.x,	(int)verticalBottom.z);
	}


	private void plotParabolaLimit()
	{
		double step		= this.spaceConverter.getXDist_real() * GuiResource.frame_drawStep;
		double limit	= this.spaceConverter.getXMin_real() + this.spaceConverter.getXDist_real();
		double x0Real	= this.spaceConverter.getXMin_real();
		double z0Real	= this.parabolaLimitFunction(x0Real);
		this.spaceConverter.convertRealToPixel(x0Real, z0Real, bufferConverter);
		double x0Pixel	= bufferConverter.x;
		double z0Pixel	= bufferConverter.z;
		double x1Real	= x0Real, z1Real;

		this.drawable.setColor(this.colorParabolLimit);
		while (x0Real < limit)
		{
			x1Real	= x1Real + step;
			z1Real	= this.parabolaLimitFunction(x1Real);
			this.spaceConverter.convertRealToPixel(x1Real, z1Real, bufferConverter);
			this.drawable.drawLine((int)x0Pixel, (int)z0Pixel, (int)bufferConverter.x, (int)bufferConverter.z);
			x0Real	= x1Real;
			z0Real	= z1Real;
			x0Pixel	= bufferConverter.x;
			z0Pixel	= bufferConverter.z;
		}
	}


	private double parabolaLimitFunction(double x)
	{
		return x*x;
	}
}
