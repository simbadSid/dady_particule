package main.java.gui;


import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import main.java.app.App;
import main.java.gui.panel.*;
import main.java.gui.util.EventListener_resizer;



public class Gui
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private JFrame			frame;											// Main frame
	private PanelDraw		panelDraw;										// Top left panel
	private PanelControl	panelControl;									// Bottom left panel
	private PanelZoom		panelZoom;										// Top right panel
	private PanelCenterSet	panelCenterSet;									// Middle right panel
	private PanelZoomPlot	panelZoomPlot;									// Bottom right panel
	private JSplitPane 		frameOrganizer_Left_Right;
	private JSplitPane 		frameOrganizerLeft;
	private JSplitPane 		frameOrganizerRightTop;
	private JSplitPane 		frameOrganizerRight;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public Gui(App app) throws NoSuchMethodException, SecurityException
	{
		// Check the hard-coded graphic parameters
		assert((GuiResource.frame_partitionWidth			> 0) && (GuiResource.frame_partitionWidth				< 1));
		assert((GuiResource.frame_partitionHeightLeft		> 0) && (GuiResource.frame_partitionHeightLeft			< 1));
		assert((GuiResource.frame_partitionHeightRight		> 0) && (GuiResource.frame_partitionHeightRight			< 1));
		assert((GuiResource.frame_partitionHeightRightTop	> 0) && (GuiResource.frame_partitionHeightRightTop		< 1));

		// Build main frame and sub panels
		this.frame			= new JFrame(GuiResource.frame_title);
		this.panelDraw		= new PanelDraw		(app, this);
		this.panelControl	= new PanelControl	(app, this);
		this.panelZoom		= new PanelZoom		(app);
		this.panelCenterSet	= new PanelCenterSet(app);
		this.panelZoomPlot	= new PanelZoomPlot	();

		// Organize sub panels within the main frame
		this.frameOrganizerRightTop		= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.panelZoom,				this.panelCenterSet);
		this.frameOrganizerRight		= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.frameOrganizerRightTop,this.panelZoomPlot);
		this.frameOrganizerLeft			= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.panelDraw,				this.panelControl);
		this.frameOrganizer_Left_Right	= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,	this.frameOrganizerLeft,	this.frameOrganizerRight);

		// Set dividers parameters
		this.frameOrganizerRightTop		.setEnabled(GuiResource.frame_organizerEnabled);
		this.frameOrganizerRight		.setEnabled(GuiResource.frame_organizerEnabled);
		this.frameOrganizerLeft			.setEnabled(GuiResource.frame_organizerEnabled);
		this.frameOrganizer_Left_Right	.setEnabled(GuiResource.frame_organizerEnabled);
		this.frameOrganizerRightTop		.setDividerSize(GuiResource.frame_organizerSize);
		this.frameOrganizerRight		.setDividerSize(GuiResource.frame_organizerSize);
		this.frameOrganizerLeft			.setDividerSize(GuiResource.frame_organizerSize);
		this.frameOrganizer_Left_Right	.setDividerSize(GuiResource.frame_organizerSize);

		// Add the action listener to the main frame
		Method resizerMethod = this.getClass().getMethod("setSize", int.class, int.class);
		EventListener_resizer listenerResizer = new EventListener_resizer(this, resizerMethod);
		this.frame			.add(frameOrganizer_Left_Right);
		this.frame			.addComponentListener(listenerResizer);
		this.frame			.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame			.setVisible(true);
		this.frame			.setSize(GuiResource.frame_width_initial, GuiResource.frame_height_initial);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void save(FileOutputStream outputFile)
	{
		this.panelDraw.save(outputFile);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	public void setMousePosition(double x, double y)
	{
		double translatedX = x;		// TODO
		double translatedY = y;		// TODO

		this.panelCenterSet.setMousePosition(translatedX, translatedY);
	}


	public void setMouseExited()
	{
		this.panelCenterSet.setMouseExited();
	}


	public void setSize(int width, int height)
	{
		this.frameOrganizer_Left_Right	.setDividerLocation(GuiResource.frame_partitionWidth);
		this.frameOrganizerLeft			.setDividerLocation(GuiResource.frame_partitionHeightLeft);
		this.frameOrganizerRight		.setDividerLocation(GuiResource.frame_partitionHeightRight);
		this.frameOrganizerRightTop		.setDividerLocation(GuiResource.frame_partitionHeightRightTop);

		double widthPanelDraw		= this.frameOrganizer_Left_Right.getLastDividerLocation()	- GuiResource.frame_secure;
		double heightPanelDraw		= this.frameOrganizerLeft.getLastDividerLocation()			- GuiResource.frame_secure;
		double widthPanelZoomPlot	= height - this.frameOrganizerRight.getLastDividerLocation()- GuiResource.frame_secure;
		double heightPanelZoomPlot	= width - widthPanelDraw									- GuiResource.frame_secure;

		this.panelDraw		.setSize((int)widthPanelDraw,		(int)heightPanelDraw);
		this.panelZoomPlot	.setSize((int)widthPanelZoomPlot,	(int)heightPanelZoomPlot);
	}
}