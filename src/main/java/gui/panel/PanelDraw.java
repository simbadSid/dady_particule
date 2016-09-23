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
	private PointDouble		converterBuffer = new PointDouble(-1, -1);

// TODO
private Color			colorDraw = Color.YELLOW;



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
		this.drawable.setColor(this.colorDraw);
		this.repaint();
	}


	public void plot(double xReal, double zReal)
	{
		this.spaceConverter.convertRealToPixel(xReal, zReal, this.converterBuffer);

		this.drawable.drawLine((int)converterBuffer.x, (int)converterBuffer.z, (int)converterBuffer.x, (int)converterBuffer.z);
// TODO obtimize
		this.repaint();
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


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	private boolean isPanelShown()
	{
		return this.colorBG.equals(GuiResource.panelDraw_colorBG_show);
	}
}
