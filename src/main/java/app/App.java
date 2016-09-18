package main.java.app;

import java.io.FileOutputStream;

import main.java.gui.Gui;

public class App
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	public final static double betaMin	= 1;
	public final static double betaMax	= 9;

	private Gui		gui;
	private boolean	zoomInSelected	= true;
	private boolean	centerSetClear	= true;
	private double	zoomFactor			= 2;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public App()
	{
		
	}


// -------------------------------------------------
// Getter/Setter
// -------------------------------------------------
	public void setGui				(Gui gui)			{this.gui				= gui;}
	public void setZoomInSelection	(boolean choice)	{this.zoomInSelected	= choice;}

	public boolean	isZoomInSelected	()	{return this.zoomInSelected;}
	public boolean	isCenterSetClear	()	{return this.centerSetClear;}
	public double	getZoomFactor		()	{return this.zoomFactor;}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void save(FileOutputStream outputFile)
	{
// TODO		
	}


}