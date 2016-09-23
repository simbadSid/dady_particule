package main.java.gui.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;
import main.java.gui.Gui;
import main.java.util.PointDouble;





public class EventListener_mouse implements  MouseListener, MouseMotionListener
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Gui				gui;
	private SpaceConverter	spaceConverter;


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
		if (SwingUtilities.isLeftMouseButton(e))
		{
			
		}
		else if (SwingUtilities.isRightMouseButton(e))
		{
			
		}
		else
			System.out.println("Unhandeled mouse button");
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	private void printPosition(MouseEvent e)
	{
		PointDouble result = new PointDouble(-1, -1);

		try
		{
			this.spaceConverter.convertPixelToReal(e.getX(), e.getY(), result);
			this.gui.setMousePosition(result.x, result.z);
		}
		catch(Exception exc)
		{
			this.gui.setMouseExited();
		}
	}
}