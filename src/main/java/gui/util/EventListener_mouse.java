package main.java.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Method;
import java.util.Set;

import main.java.gui.Gui;





public class EventListener_mouse implements  MouseListener, MouseMotionListener
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Gui gui;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public EventListener_mouse(Gui gui)
	{
		this.gui = gui;
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override	public void mouseMoved(MouseEvent e)	{this.gui.setMousePosition(e.getX(), e.getY());}
	@Override	public void mouseDragged(MouseEvent e)	{this.gui.setMousePosition(e.getX(), e.getY());}
	@Override	public void mouseEntered(MouseEvent e)	{this.gui.setMousePosition(e.getX(), e.getY());}
	@Override	public void mouseExited(MouseEvent e)	{this.gui.setMouseExited();}


	@Override
	public void mouseClicked(MouseEvent e)
	{
// TODO 
	}


	@Override
	public void mousePressed(MouseEvent e)
	{
// TODO 
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
// TODO 
	}
}
