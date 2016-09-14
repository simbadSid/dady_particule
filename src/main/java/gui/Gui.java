package main.java.gui;

import java.awt.Color;
import java.lang.reflect.Method;

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
	private PanelBeta		panelBeta;										// Bottom left panel
	private PanelZoom		panelZoom;										// Top right panel
	private PanelCenterSet	panelCenterSet;									// Middle right panel
	private PanelButton		panelButton;									// Bottom right panel
	private JSplitPane 		frameOrganizer_Left_Right;
	private JSplitPane 		frameOrganizerLeft;
	private JSplitPane 		frameOrganizerRight;
	private JSplitPane 		frameOrganizerRightTop;


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
		assert((GuiResource.frame_partitionHeightRightMiddle> 0) && (GuiResource.frame_partitionHeightRightMiddle	< 1));

		// Build main frame and sub panels
		this.frame			= new JFrame(GuiResource.frame_title);
		this.panelDraw		= new PanelDraw		();
		this.panelBeta		= new PanelBeta		();
		this.panelZoom		= new PanelZoom		();
		this.panelCenterSet	= new PanelCenterSet();
		this.panelButton	= new PanelButton	(app);

		// Organize sub panels within the main frame
		this.frameOrganizerRightTop		= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.panelZoom,				this.panelCenterSet);
		this.frameOrganizerRight		= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.frameOrganizerRightTop,this.panelButton);
		this.frameOrganizerLeft			= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.panelDraw,				this.panelBeta);
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


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	public void setSize(int width, int height)
	{
		double rightPartition = height * GuiResource.frame_partitionHeightRightTop;

		this.frameOrganizer_Left_Right	.setDividerLocation(GuiResource.frame_partitionWidth);
		this.frameOrganizerLeft			.setDividerLocation(GuiResource.frame_partitionHeightLeft);
		this.frameOrganizerRight		.setDividerLocation(GuiResource.frame_partitionHeightRight);
		this.frameOrganizerRightTop		.setDividerLocation((int)rightPartition);
	}
}