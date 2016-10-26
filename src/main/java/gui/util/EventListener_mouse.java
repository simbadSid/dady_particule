package main.java.gui.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;
import main.java.gui.Gui;
import main.java.util.PointDouble;





public class EventListener_mouse implements  MouseListener, MouseMotionListener, MouseWheelListener
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Gui				gui;
	private SpaceConverter	spaceConverter;
	private PointDouble		converterBuffer = new PointDouble(-1, -1);


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public EventListener_mouse(Gui gui, SpaceConverter spaceConverter)
	{
		this.gui			= gui;
		this.spaceConverter	= spaceConverter;
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override	public void mouseMoved(MouseEvent e)	{this.printPosition(e);}
	@Override	public void mouseDragged(MouseEvent e)	{this.printPosition(e);}
	@Override	public void mouseEntered(MouseEvent e)	{this.printPosition(e);}
	@Override	public void mouseExited(MouseEvent e)	{this.gui.setMouseExited();}
	@Override	public void mousePressed(MouseEvent e)	{}
	@Override	public void mouseReleased(MouseEvent e)	{}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (!this.spaceConverter.isInitialized())
			return;

		this.spaceConverter.convertPixelToReal(e.getX(), e.getY(), converterBuffer);

		if (SwingUtilities.isLeftMouseButton(e))
		{
			this.gui.changeInitialPoint(converterBuffer.x, converterBuffer.z);
		}
		else if (SwingUtilities.isRightMouseButton(e))
		{
			this.gui.zoom(converterBuffer.x, converterBuffer.z, null);
		}
		else
			System.out.println("Unhandeled mouse button");
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if (!this.spaceConverter.isInitialized())
			return;

		this.spaceConverter.convertPixelToReal(e.getX(), e.getY(), converterBuffer);

		if (e.getWheelRotation() < 0)
			this.gui.zoom(converterBuffer.x, converterBuffer.z, true);
		else
			this.gui.zoom(converterBuffer.x, converterBuffer.z, false);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	private void printPosition(MouseEvent e)
	{
		if (!this.spaceConverter.isInitialized())
			this.gui.setMouseExited();
		else
		{
			this.spaceConverter.convertPixelToReal(e.getX(), e.getY(), converterBuffer);
			this.gui.setMousePosition(converterBuffer.x, converterBuffer.z);
		}
	}
}