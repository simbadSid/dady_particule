package main.java.gui.panel;

import java.io.FileOutputStream;

import javax.swing.JPanel;

import main.java.app.App;
import main.java.gui.Gui;
import main.java.gui.GuiResource;
import main.java.gui.util.EventListener_mouse;







@SuppressWarnings("serial")
public class PanelDraw extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private App app;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelDraw(App app, Gui gui)
	{
		this.app = app;

		EventListener_mouse mouseListener = new EventListener_mouse(gui);
		this.setBackground(GuiResource.panelDraw_colorBG);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void save(FileOutputStream outputFile)
	{
// TODO
	}

	@Override
	public void setSize(int width, int height)
	{
System.out.format("width = %d, height = %d\n", width, height);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
}