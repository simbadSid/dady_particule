package main.java.gui;


import java.io.FileOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import main.java.app.App;
import main.java.gui.panel.*;
import main.java.gui.util.EventListener_resizer;
import main.java.gui.util.ExceptionPrinter;
import main.java.util.AngleConverter;



public class Gui
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	public final static int		zoom_indexMax			= 20;
	public final static double	zoomInitial_centerRealX	= 0;
	public final static double	zoomInitial_centerRealZ	= 0.5;
	public final static int		zoomInitial_index		= 0;

	private App				app;
	private boolean 		zoom_isPanelShown;
	private double			zoom_centerRealX;
	private double			zoom_centerRealZ;
	private int				zoom_index;
	private double			zoom_size;
	private boolean			zoomInSelected				= GuiResource.frame_panelZoom_initialSelected;
	private double			zoomFactor					= GuiResource.frame_panelZoom_initialFactor;
	private boolean			centerSetClear				= GuiResource.frame_panelZoom_initialCenterSetClear;

	private JFrame			frame;											// Main frame
	private PanelDraw		panelDrawMain;									// Top left panel
	private PanelControl	panelControl;									// Bottom left panel
	private PanelZoom		panelZoom;										// Top right panel
	private PanelCenterSet	panelCenterSet;									// Middle right panel
	private PanelDraw		panelDrawZoom;									// Bottom right panel
	private JSplitPane 		frameOrganizer_Left_Right;
	private JSplitPane 		frameOrganizerLeft;
	private JSplitPane 		frameOrganizerRightTop;
	private JSplitPane 		frameOrganizerRight;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public Gui(App app) throws NoSuchMethodException, SecurityException
	{
		this.app = app;

		// Check the hard-coded graphic parameters
		assert((GuiResource.frame_partitionWidth			> 0) && (GuiResource.frame_partitionWidth				< 1));
		assert((GuiResource.frame_partitionHeightLeft		> 0) && (GuiResource.frame_partitionHeightLeft			< 1));
		assert((GuiResource.frame_partitionHeightRight		> 0) && (GuiResource.frame_partitionHeightRight			< 1));
		assert((GuiResource.frame_partitionHeightRightTop	> 0) && (GuiResource.frame_partitionHeightRightTop		< 1));

		// Build main frame and sub panels
		this.frame			= new JFrame(GuiResource.frame_title);
		this.panelDrawMain	= new PanelDraw		(this);
		this.panelControl	= new PanelControl	(app, this);
		this.panelZoom		= new PanelZoom		(this);
		this.panelCenterSet	= new PanelCenterSet(this);
		this.panelDrawZoom	= new PanelDraw		(this);
		this.panelDrawMain.setPanelShown(true);
		this.resetZoom();

		// Organize sub panels within the main frame
		this.frameOrganizerRightTop		= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.panelZoom,				this.panelCenterSet);
		this.frameOrganizerRight		= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.frameOrganizerRightTop,this.panelDrawZoom);
		this.frameOrganizerLeft			= new JSplitPane(JSplitPane.VERTICAL_SPLIT, 	this.panelDrawMain,			this.panelControl);
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
		EventListener_resizer listenerResizer = new EventListener_resizer(this, "setSize", true);
		this.frame			.add(frameOrganizer_Left_Right);
		this.frame			.addComponentListener(listenerResizer);
		this.frame			.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame			.setVisible(true);
		this.frame			.setSize(GuiResource.frame_width_initial, GuiResource.frame_height_initial);
	}


// -------------------------------------------------
// Getter/Setter
// -------------------------------------------------
		public boolean	isZoomInSelected	()	{return this.zoomInSelected;}
		public boolean	isCenterSetClear	()	{return this.centerSetClear;}
		public double	getZoomFactor		()	{return this.zoomFactor;}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void save(FileOutputStream outputFile)
	{
		this.panelDrawMain.save(outputFile);
	}


	public void plot(double xReal, double zReal)
	{
		this.panelDrawMain.plot(xReal, zReal);
		if (this.zoom_isPanelShown)
			this.panelDrawZoom.plot(xReal, zReal);
	}


	public void setPanelZoomShown()
	{
		this.zoom_isPanelShown = !this.zoom_isPanelShown;;

		this.panelDrawZoom	.setPanelShown(this.zoom_isPanelShown);
		this.panelZoom		.setPanelZoomShown(this.zoom_isPanelShown);
	}


	public void changeAngle()
	{
		String	message		= String.format(GuiResource.frameTeta_message, AngleConverter.gradiantToDegree(App.tetaMin), AngleConverter.gradiantToDegree(App.tetaMax));
		double	tetaDegree	= -1;
		double	tetaGradiant= -1;
		String	teta;

		teta = JOptionPane.showInputDialog(null, message, GuiResource.frameTeta_title, JOptionPane.QUESTION_MESSAGE);
		if (teta == null) return;
		try
		{
			tetaDegree		= Double.parseDouble(teta);
			tetaGradiant	= AngleConverter.degreeToGradiant(tetaDegree);
			if ((tetaGradiant < App.tetaMin) || (tetaGradiant > App.tetaMax)) throw new RuntimeException();
		}
		catch(Exception e)
		{
			message = String.format(GuiResource.frameTeta_messageError, AngleConverter.gradiantToDegree(App.tetaMin), AngleConverter.gradiantToDegree(App.tetaMax));
			ExceptionPrinter.printError(message, false);
			return;
		}

		this.changeAngle(tetaDegree, tetaGradiant);
	}


	public void changeAngle(double tetaPercent)
	{
		double tetaGradiant = App.tetaMin + tetaPercent * (App.tetaMax - App.tetaMin) / 100.;
		double tetaDegree	= AngleConverter.gradiantToDegree(tetaGradiant);

		this.changeAngle(tetaDegree, tetaGradiant);
	}


	public void startPause()
	{
		if (this.app.isRunning())
		{
			this.app.pause();
			this.panelControl.pause();
		}
		else
		{
			this.app.start();
			this.panelControl.start();
		}
	}


	public void exit()
	{
		String		message		= GuiResource.frameExit_message;
		Object[]	options		= {GuiResource.frameExit_buttonLabel_Save, GuiResource.frameExit_buttonLabel_Discrad, GuiResource.frameExit_buttonLabel_Cancel};
		int			userChoice	= JOptionPane.showOptionDialog(null,
									message,
									GuiResource.frameExit_title,
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null ,     //do not use a custom Icon
									options,  //the titles of buttons
									options[0]); //default button title
		switch (userChoice)
		{
			case 0:
				boolean test = false;
				try					{SaveInterface.save(this.app, this);}
				catch(Exception e)	{e.printStackTrace();}
				if (test)	break;
				else		return;
			case 1: break;
			case 2: return;
		}
		System.exit(0);
	}


	public void resetZoom()
	{
		this.zoom_isPanelShown	= GuiResource.frame_panelDrawZoom_initialShow;
		this.zoom_centerRealX	= zoomInitial_centerRealX;
		this.zoom_centerRealZ	= zoomInitial_centerRealZ;
		this.zoom_index			= zoomInitial_index;
		this.zoom_size			= Math.pow(this.zoomFactor, -zoomInitial_index);

		this.panelDrawZoom	.setPanelShown(GuiResource.frame_panelDrawZoom_initialShow);
		this.panelZoom		.setPanelZoomShown(GuiResource.frame_panelDrawZoom_initialShow);
	}


	public void zoom()
	{
		boolean test = true;

		if (zoomInSelected)
		{
			if (this.zoom_index >= zoom_indexMax)
			{
				test = false;
			}
			else
			{
				this.zoom_index ++;
				this.zoom_size	/= this.zoomFactor;
			}
		}
		else
		{
			if (this.zoom_index <= 1)
			{
				test = false;
			}
			else
			{
				this.zoom_index --;
				this.zoom_size	*= this.zoomFactor;
			}
		}

		if (test)	this.panelDrawZoom.clearAll();
		else		ExceptionPrinter.printError(GuiResource.frameZoomError, false);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	private void changeAngle(double tetaDegree, double tetaGradiant)
	{
		boolean running = this.app.isRunning();

		if (running)	this.app.pause();
		this.panelDrawMain.clearAll();
		this.panelControl.setTeta(tetaDegree);
		this.resetZoom();
		this.app.changeAngle(tetaGradiant);
		if (running)	this.app.start();
	}


	public void setMousePosition(double xReal, double yReal)
	{
		this.panelCenterSet.setMousePosition(xReal, yReal);
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
	}
}